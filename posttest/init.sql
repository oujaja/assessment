-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE user_ticket (
                        id VARCHAR(10) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        ownLotteryId INTEGER[] NOT NULL
);

CREATE TABLE lottery (
                        id VARCHAR(255) PRIMARY KEY,
                        ticket VARCHAR(6) UNIQUE NOT NULL CHECK (ticket ~ '^[0-9]{6}$'),
                        price INTEGER NOT NULL
);

INSERT INTO user_ticket (id, name, ownLotteryId) VALUES
                                                     ('0000000001', 'somchai', '{1,2}'),
                                                     ('1234567891', 'somsak', '{1,3}'),
                                                     ('9992457123', 'sommai', '{3}');

INSERT INTO lottery (id, ticket, price) VALUES
                                                    ('1', '123456', 80),
                                                    ('2', '123457', 80),
                                                    ('3', '123458', 80);