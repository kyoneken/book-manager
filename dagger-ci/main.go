package main

import (
	"context"
	"fmt"
	"path/filepath"

	"dagger.io/dagger"
)

func main() {
	if err := unitTest(context.Background()); err != nil {
		fmt.Println(err)
	}
}

// Unit test task.
func unitTest(ctx context.Context) error {
	fmt.Println("Testing with Dagger")

	// Dagger client初期化
	client, err := dagger.Connect(ctx)
	if err != nil {
		return err
	}
	defer client.Close()

	// Dockerコンテナイメージ取得
	container := client.Container().From("openjdk:21-jdk-slim")

	currentDir, err := filepath.Abs(".")
	if err != nil {
		return err
	}
	projectRoot := filepath.Dir(currentDir)
	src := client.Host().Directory(projectRoot)

	// TODO: ベースイメージにJDK、Gradle、必要なパッケージを用意する方針に変更する
	container = container.WithExec([]string{"apt-get", "update"})
	container = container.WithExec([]string{"apt-get", "install", "-y", "findutils"})

	container = container.WithMountedDirectory("/app", src)
	container = container.WithWorkdir("/app")

	// Test task
	fmt.Println("===== Testing with Dagger =====")
	container = container.WithExec([]string{"./gradlew", "test"})
	stdOut, err := container.Stdout(ctx)
	if err != nil {
		fmt.Println("Failed test")
		fmt.Println(err)
		return err
	}
	fmt.Println(stdOut)

	fmt.Println("===== Testing is successful! =====")
	return nil
}
