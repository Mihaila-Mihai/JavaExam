CREATE TABLE IF NOT EXISTS public.ticket
(
    id          integer          NOT NULL DEFAULT nextval('ticket_id_seq'::regclass),
    event_date  date,
    event_title character varying(255) COLLATE pg_catalog."default",
    event_venue character varying(255) COLLATE pg_catalog."default",
    price       double precision NOT NULL,
    type        smallint,
    CONSTRAINT ticket_pkey PRIMARY KEY (id)
)