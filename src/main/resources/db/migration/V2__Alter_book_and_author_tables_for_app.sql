ALTER TABLE book ADD CONSTRAINT uk_book_isbn UNIQUE (isbn);
CREATE INDEX idx_book_title ON book (title varchar_pattern_ops);
CREATE INDEX idx_book_author_id ON book (author_id);