-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE user_ticket (
                        id VARCHAR(10) PRIMARY KEY not null ,
                        name VARCHAR(255) NOT NULL

);

CREATE TABLE lottery (
                        id SERIAL PRIMARY KEY,
                        ticket VARCHAR(6) UNIQUE NOT NULL CHECK (ticket ~ '^[0-9]{6}$'),
                        price INTEGER NOT NULL,
                        amount INTEGER
);



INSERT INTO user_ticket (id, name)VALUES
                                                    ('0000000001', 'somchai'),
                                                    ('1234567891', 'somsak'),
                                                    ('9992457123', 'sommai');

INSERT INTO lottery ( ticket, price,amount) VALUES
                                                    ('123456', 80,1),
                                                    ( '123457', 80,1),
                                                    ( '123458', 80,1);


CREATE TABLE user_ticket_lottos
 (
     id SERIAL PRIMARY KEY,
     user_ticket_id VARCHAR(10),
     lottery_id     VARCHAR(10)
 );

INSERT INTO user_ticket_lottos (user_ticket_id,lottery_id)VALUES
                                                         ('0000000001','123456'),
                                                         ('0000000001','123457'),
                                                         ('1234567891','123456'),
                                                         ('1234567891','123458'),
                                                         ('9992457123','123458');


/*CREATE TABLE user_ticket_lottos
(
    id             SERIAL PRIMARY KEY,
    user_ticket_id VARCHAR(10),
    lottery_id     INTEGER
);

INSERT INTO transactional (user_ticket_id,lottery_id)VALUES
                                                    ('0000000001',1),
                                                    ('0000000001',2),
                                                    ('1234567891',1),
                                                    ('1234567891',3),
                                                    ('9992457123',3);

 */