CREATE TABLE email_message
(
    id      BIGSERIAL PRIMARY KEY,
    message VARCHAR(1000),
    sent_at TIMESTAMP WITH TIME ZONE NOT NULL
)