

CREATE TABLE groups
(
    id   SERIAL NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

CREATE TABLE request
(
    id          UUID NOT NULL,
    template_id UUID,
    status      SMALLINT,
    type        SMALLINT,
    email       VARCHAR(255),
    message      VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    user_id     UUID,
    CONSTRAINT pk_request PRIMARY KEY (id)
);

CREATE TABLE requirement
(
    id                  UUID    NOT NULL,
    name                VARCHAR(255),
    description         VARCHAR(255),
    is_mandatory        BOOLEAN NOT NULL,
    requirement_type_id UUID,
    template_id         UUID,
    CONSTRAINT pk_requirement PRIMARY KEY (id)
);

CREATE TABLE requirement_field
(
    id             UUID NOT NULL,
    request_id     UUID,
    requirement_id UUID,
    value          VARCHAR(255),
    CONSTRAINT pk_requirementfield PRIMARY KEY (id)
);

CREATE TABLE requirement_type
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_requirementtype PRIMARY KEY (id)
);

CREATE TABLE template
(
    id            UUID NOT NULL,
    name          VARCHAR(255),
    description   VARCHAR(255),
    category      VARCHAR(255),
    department_id UUID,
    CONSTRAINT pk_template PRIMARY KEY (id)
);

CREATE TABLE template_groups
(
    templates_id UUID NOT NULL REFERENCES template (id),
    groups_id    INT NOT NULL REFERENCES groups (id),
    CONSTRAINT pk_templategroups PRIMARY KEY (templates_id, groups_id)
);

ALTER TABLE request
    ADD CONSTRAINT FK_REQUEST_ON_TEMPLATE FOREIGN KEY (template_id) REFERENCES template (id);

ALTER TABLE requirement_field
    ADD CONSTRAINT FK_REQUIREMENTFIELD_ON_REQUEST FOREIGN KEY (request_id) REFERENCES request (id);

ALTER TABLE requirement_field
    ADD CONSTRAINT FK_REQUIREMENTFIELD_ON_REQUIREMENT FOREIGN KEY (requirement_id) REFERENCES requirement (id);

ALTER TABLE requirement
    ADD CONSTRAINT FK_REQUIREMENT_ON_REQUIREMENTTYPE FOREIGN KEY (requirement_type_id) REFERENCES requirement_type (id);

ALTER TABLE requirement
    ADD CONSTRAINT FK_REQUIREMENT_ON_TEMPLATE FOREIGN KEY (template_id) REFERENCES template (id);