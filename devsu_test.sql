

CREATE TABLE public.tbl_cliente (
    id uuid NOT NULL,
    estado boolean,
    password character varying(255),
    persona_id uuid
);


ALTER TABLE public.tbl_cliente OWNER TO postgres;


CREATE TABLE public.tbl_cuenta (
    id uuid NOT NULL,
    estado boolean,
    numero_cuenta character varying(255) NOT NULL,
    saldo_disponible numeric(19,2),
    saldo_inicial numeric(19,2),
    tipo character varying(255),
    cliente_id uuid
);


ALTER TABLE public.tbl_cuenta OWNER TO postgres;


CREATE TABLE public.tbl_movimiento (
    id uuid NOT NULL,
    estado boolean,
    fecha_creacion date,
    tipo character varying(255),
    valor numeric(19,2),
    cuenta_id uuid
);


ALTER TABLE public.tbl_movimiento OWNER TO postgres;

--

CREATE TABLE public.tbl_persona (
    id uuid NOT NULL,
    direccion character varying(255),
    fecha_nacimiento date,
    identificacion character varying(255),
    nombre character varying(255),
    primer_apellido character varying(255),
    segundo_apellido character varying(255) NOT NULL,
    telefono character varying(255)
);


ALTER TABLE public.tbl_persona OWNER TO postgres;


ALTER TABLE ONLY public.tbl_cliente
    ADD CONSTRAINT tbl_cliente_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.tbl_cuenta
    ADD CONSTRAINT tbl_cuenta_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.tbl_movimiento
    ADD CONSTRAINT tbl_movimiento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tbl_persona
    ADD CONSTRAINT tbl_persona_pkey PRIMARY KEY (id);

--

ALTER TABLE ONLY public.tbl_cliente
    ADD CONSTRAINT "FK6f3fcdxkm7j341k0ma7getreg" FOREIGN KEY (persona_id) REFERENCES public.tbl_persona(id);

ALTER TABLE ONLY public.tbl_cuenta
    ADD CONSTRAINT "FKct9ca0qas6cq9we5aiygc3tdu" FOREIGN KEY (cliente_id) REFERENCES public.tbl_cliente(id);


ALTER TABLE ONLY public.tbl_movimiento
    ADD CONSTRAINT "FKswfa4b4si1ppau4olwbymsu8d" FOREIGN KEY (cuenta_id) REFERENCES public.tbl_cuenta(id);


