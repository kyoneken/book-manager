# 書籍管理システム

## 概要
書籍の管理を行うシステムです。書籍の登録、編集、削除、検索ができます。

## 環境

- Java: 21
- Gradle: 8
- Docker

## 動作方法

### 事前準備

1. [docker-compose](compose.yaml)でPostgreSQLサーバを準備
    ```
    # 起動
    docker-compose up -d
    ```
    ```
    # 終了
    docker-compose down
    ```

2. Flywayでテーブルマイグレーションする

    ```
    ./gradlew bootRun
    ```

    ※暫定

### 実行

1. gradleで実行します
    ```
    ./gradlew bootRun
    ```
2. データ準備参考

    1. 著者登録

        ```
        curl http://localhost:8080/author -X POST -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Kotlin 太郎", "email":"test1@test.com"}'
        ```
    2. 書籍登録
        ```
        curl http://localhost:8080/book -X POST -H "Content-Type: application/json; charset=utf-8" -d "{"title":"Kotlinの本", "isbn":"9786789023332", "publishedDate":"2024-05-02", "authorId":1}"
        ```
    3. 書籍リスト取得

        ```
        curl http://localhost:8080/book/search -X GET  -H "Content-Type: application/json; charset=utf-8" 
        ```


### 検証

1. Lint

```
./gradlew ktlintCheck
```

2. Unit test
```
./gradlew test
```

## 開発参考

### テーブルコード自動生成

1. テーブルからコード生成
    
    1. DDLを変更した場合はEntityを自動生成します
    ```
    ./gradlew jooqCodegen
    ```

### Ktlintフォーマット

1. コードをフォーマットします

    ```
    ./gradlew ktlintFormat
    ```

- [ ] IDEへの組み込み
