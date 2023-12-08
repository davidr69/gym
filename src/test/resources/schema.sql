CREATE TABLE category (
    id INTEGER,
    description VARCHAR(32)
);

CREATE TABLE muscle (
    id INTEGER,
    description VARCHAR(32)
);

CREATE TABLE exercise (
    id INTEGER,
    description VARCHAR(32),
    muscle INTEGER
);

CREATE TABLE progress (
    id INTEGER,
    exercise INTEGER,
    mydate DATE,
    rep1 INTEGER,
    rep2 INTEGER,
    weight NUMERIC(5,1)
);
