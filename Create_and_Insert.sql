CREATE TABLE pessoa
(
  id serial,
  nome character varying(25) NOT NULL,
  idade integer NOT NULL,
  CONSTRAINT pessoa_pkey PRIMARY KEY (id)
);

CREATE TABLE filme
(
  id serial,
  nome character varying(25) NOT NULL,
  genero character varying(25) NOT NULL,
  ano integer,
  duracao double precision NOT NULL,
  diretor integer,
  CONSTRAINT filme_pkey PRIMARY KEY (id),
  CONSTRAINT filme_diretor_fkey FOREIGN KEY (diretor)
      REFERENCES pessoa(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.usuario
(
  login character varying(25),
  senha character varying(25),
  id serial,,
  email character varying(50),
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE ator_filme
(
  id serial,
  ator_id integer,
  filme_id integer,
  CONSTRAINT ator_filme_pkey PRIMARY KEY (id),
  CONSTRAINT ator_filme_ator_id_fkey FOREIGN KEY (ator_id)
      REFERENCES pessoa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ator_filme_filme_id_fkey FOREIGN KEY (filme_id)
      REFERENCES filme(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.usuario_avaliacao
(
  id serial,,
  usuario_id integer,
  filme_id integer,
  avaliacao integer,
  CONSTRAINT usuario_avaliacao_pkey PRIMARY KEY (id),
  CONSTRAINT usuario_avaliacao_filme_id_fkey FOREIGN KEY (filme_id)
      REFERENCES filme (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT usuario_avaliacao_usuario_id_fkey FOREIGN KEY (usuario_id)
      REFERENCES usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT chk_avaliacao CHECK (avaliacao = ANY (ARRAY[1, 2, 3, 4, 5]))
);

insert into filme (nome, genero, ano, duracao) values ('Grande Hotel Budapeste', 'Comedia', 2015, 130);
insert into filme (nome, genero, ano, duracao) values ('Suspeitos', 'Drama', 2011, 143);
insert into filme (nome, genero, ano, duracao) values ('A chegada', 'Ficcao', 2017, 144);
insert into filme (nome, genero, ano, duracao) values ('LEGO Batman', 'Animacao', 2017, 140);
insert into filme (nome, genero, ano, duracao) values ('Procurando Dory', 'Animacao', 2015, 130);
insert into filme (nome, genero, ano, duracao) values ('Questao de Tempo', 'Romance', 2012, 125);
insert into filme (nome, genero, ano, duracao) values ('50 tons mais escuros', 'Drama', 2017, 133);
insert into filme (nome, genero, ano, duracao) values ('Clube da Luta', 'Drama', 1999, 143);

insert into pessoa (nome, idade) values ('Tom Ford', 40);
insert into pessoa (nome, idade) values ('Wes Anderson', 33);
insert into pessoa (nome, idade) values ('Denis Villeneuve', 44);
insert into pessoa (nome, idade) values ('Andrew Stanton', 34);
insert into pessoa (nome, idade) values ('Richard Curtis', 21);
insert into pessoa (nome, idade) values ('James Foley', 31);
insert into pessoa (nome, idade) values ('Edward Norton', 40);
insert into pessoa (nome, idade) values ('Ralph Flennes', 55);
insert into pessoa (nome, idade) values ('Amy Adams', 25);

insert into usuario (login,senha,email) values ('sergioflits', 'pop12', 'sergioflits@outlook.com');
insert into usuario (login,senha,email) values ('baiano', 'acaraje', 'baianoze@outlook.com');

insert into ator_filme(ator_id, filme_id) values (7, 1);
insert into ator_filme(ator_id, filme_id) values (8, 1);
insert into ator_filme(ator_id, filme_id) values (9, 3);
insert into ator_filme(ator_id, filme_id) values (7, 8);


insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (1,1,4);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (1,2,4);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (1,3,5);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (1,4,5);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (2,1,4);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (2,4,4);
insert into usuario_avaliacao (usuario_id,filme_id,avaliacao) values (2,5,5);

























