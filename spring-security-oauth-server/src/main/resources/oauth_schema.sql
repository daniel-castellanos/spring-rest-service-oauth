--drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);


--This can be changed

CREATE  TABLE oauth_users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled BOOLEAN NOT NULL DEFAULT TRUE ,
  PRIMARY KEY (username));

CREATE TABLE oauth_user_roles (
      username VARCHAR(50) NOT NULL,
      role VARCHAR(50) NOT NULL,
      CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES oauth_users(username));
      CREATE UNIQUE INDEX ix_auth_username ON oauth_user_roles (username,role);


INSERT INTO oauth_user_roles (username, role)
VALUES ('test', 'USER');