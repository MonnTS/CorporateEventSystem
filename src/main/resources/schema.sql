CREATE TABLE LOGIN
(
    id         INTEGER AUTO_INCREMENT NOT NULL,
    role_id    INTEGER     NOT NULL,
    first_name VARCHAR(46) NOT NULL,
    last_name  VARCHAR(46) NOT NULL,
    email      varchar(60) NOT NULL,
    password   VARCHAR(60) NOT NULL,
    CONSTRAINT "pk_person-login" PRIMARY KEY (id)
);

CREATE TABLE ROLES
(
    id   INTEGER AUTO_INCREMENT NOT NULL,
    role varchar(12) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE EVENT
(
    id          INTEGER AUTO_INCREMENT NOT NULL,
    name        VARCHAR(99) NOT NULL,
    place       VARCHAR(99) NOT NULL,
    description CLOB        NOT NULL,
    picture     BLOB        NOT NULL,
    date        DATE        NOT NULL,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE EVENT_PARTICIPANTS
(
    id         INTEGER AUTO_INCREMENT NOT NULL,
    event_id   INTEGER NOT NULL,
    person_id  INTEGER NOT NULL,
    key_access CLOB    NOT NULL,
    CONSTRAINT "pk_event-participant" PRIMARY KEY (id)
);

CREATE TABLE MESSAGES
(
    id        INTEGER AUTO_INCREMENT NOT NULL,
    sender_id INTEGER NOT NULL,
    target_id INTEGER NOT NULL,
    content   VARCHAR(255),
    is_sent   BOOLEAN NOT NULL,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);

ALTER TABLE LOGIN
    ADD FOREIGN KEY (role_id)
        REFERENCES ROLES (id);

ALTER TABLE EVENT_PARTICIPANTS
    ADD FOREIGN KEY (person_id)
        REFERENCES LOGIN (id);

ALTER TABLE EVENT_PARTICIPANTS
    ADD FOREIGN KEY (event_id)
        REFERENCES EVENT (id);


ALTER TABLE MESSAGES
    ADD FOREIGN KEY (sender_id)
        REFERENCES LOGIN (id);

ALTER TABLE MESSAGES
    ADD FOREIGN KEY (target_id)
        REFERENCES LOGIN (id);