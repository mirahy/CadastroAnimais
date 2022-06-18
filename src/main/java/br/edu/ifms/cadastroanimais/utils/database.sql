CREATE TABLE public.animais
(
    "ID" integer NOT NULL,
    "NOME" character varying(100) NOT NULL,
    "RACA" character varying(50) NOT NULL,
    "TAMANHO" character varying(50) NOT NULL,
    "SEXO" character varying(10) NOT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS public.animais
    OWNER to postgres;