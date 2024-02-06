CREATE TABLE address
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    city            VARCHAR(255) NULL,
    street          VARCHAR(255) NULL,
    number          INT NULL,
    zip_code        VARCHAR(255) NULL,
    geo_location_id BIGINT NULL,
    user_id         BIGINT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE geo_location
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    latitude DOUBLE NULL,
    longitude DOUBLE NULL,
    CONSTRAINT pk_geolocation PRIMARY KEY (id)
);

CREATE TABLE name
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    first_name      VARCHAR(255) NULL,
    last_name       VARCHAR(255) NULL,
    CONSTRAINT pk_name PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    name            VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    token           VARCHAR(255) NULL,
    user_id         BIGINT NULL,
    expiry_date     datetime NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                BIGINT NOT NULL,
    created_at        datetime NULL,
    last_updated_at   datetime NULL,
    is_deleted        BIT(1) NOT NULL,
    email             VARCHAR(255) NULL,
    hashed_password   VARCHAR(255) NULL,
    username          VARCHAR(255) NULL,
    name_id           BIGINT NULL,
    phone             VARCHAR(255) NULL,
    is_email_verified BIT(1) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_address
(
    user_id    BIGINT NOT NULL,
    address_id BIGINT NOT NULL
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE user_address
    ADD CONSTRAINT uc_user_address_address UNIQUE (address_id);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEO_LOCATION FOREIGN KEY (geo_location_id) REFERENCES geo_location (id);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);

ALTER TABLE user_address
    ADD CONSTRAINT fk_useadd_on_address FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user_address
    ADD CONSTRAINT fk_useadd_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);