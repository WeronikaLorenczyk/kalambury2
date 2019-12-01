CREATE TABLE players (
	login       varchar(50) unique not NULL PRIMARY KEY,
	password    varchar(200) not NULL,
	age         integer,
	best_score  integer default 0
 );

INSERT INTO players VALUES
('a','a',1),
('b','b',2);

CREATE SEQUENCE games_id INCREMENT BY 1 START WITH 1;

CREATE TABLE games(
    id          integer PRIMARY KEY DEFAULT nextval('games_id'),
    name        varchar(50) unique not NULL,
    finished    bool default false
);

INSERT INTO games  (name, finished) VALUES
('example',false),
('finished', true);

CREATE SEQUENCE player_game_id INCREMENT BY 1 START WITH 1;

CREATE TABLE player_game(
    id          integer PRIMARY KEY DEFAULT nextval('player_game_id'),
    login       varchar(50) not NULL,
    game_id    integer not NULL,
    score       integer not NULL DEFAULT 0,

    CONSTRAINT fk_player FOREIGN KEY ( login ) REFERENCES players( login ) on delete cascade,
    CONSTRAINT fk_game FOREIGN KEY ( game_id ) REFERENCES games( id ) on delete cascade
);

INSERT INTO player_game (login,game_id,score) VALUES
('a',2,4),
('b',1,1);

CREATE TABLE words(
    word        varchar(50) unique not NULL PRIMARY KEY,
    level       integer not NULL default 0
);

INSERT INTO words VALUES
('cat',1),
('dog',1),
('rabbit',2);