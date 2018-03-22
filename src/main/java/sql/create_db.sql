CREATE TABLE account (
    account_id                  NUMERIC NOT NULL,
    user_id                     NUMERIC NOT NULL,
    name                        CHARACTER VARYING(255) NOT NULL,
    balance                     NUMERIC
);

CREATE TABLE user (
    user_id                     NUMERIC NOT NULL,
    name                        CHARACTER VARYING(255) NOT NULL
);

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (account_id);

ALTER TABLE ONLY user
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);

ALTER TABLE ONLY account
    ADD CONSTRAINT account_fkey FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE ON DELETE CASCADE;
