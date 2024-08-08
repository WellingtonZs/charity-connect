USE MASTER IF EXISTS(
SELECT * FROM SYS.databases 
WHERE NAME = 'bd_CharityConnect_INF3FM')

DROP DATABASE bd_CharityConnect_INF3FM
GO
CREATE DATABASE bd_CharityConnect_INF3FM
GO

USE bd_CharityConnect_INF3FM

--Criação das tabelas:

--Administrador:
CREATE TABLE Administrador
(
	id				BIGINT			NOT NULL IDENTITY(1,1) PRIMARY KEY,
	cpf				VARCHAR(11)		NOT NULL,
	email			VARCHAR(100)	UNIQUE	 NOT NULL,
	nome			VARCHAR(100)	NOT NULL,
	sobrenome		VARCHAR(100)	NOT NULL,
	senha			VARCHAR(100)	NOT NULL,
	uf				CHAR(2)			NOT NULL,
	dataNasc		DATETIME		NOT NULL,
	cep				CHAR(7)			NOT NULL,
	dataCadastro	DATETIME		NOT NULL,
	telefone		VARCHAR(20)		NOT NULL,
	nivelAcesso		CHAR(10)		NULL, --ADMIN ou USER
	foto			VARBINARY(MAX)	NULL,
	statusAdmin		VARCHAR(20)		NOT NULL,
)

--ONG:
CREATE TABLE ONG
(
	id				BIGINT			NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nome			VARCHAR(100)	NOT NULL,
	email			VARCHAR(100)	UNIQUE	NOT NULL,
	senha			VARCHAR(100)	NOT NULL,
	telefone		VARCHAR(20)		NOT NULL,
	descAtuacao		VARCHAR(300)	NOT NULL,
	foto			VARBINARY(MAX)	NULL,
	cep				CHAR(7)			NOT NULL,
	dataCadastro	DATETIME		NOT NULL,
	sobreNos		VARCHAR(200)	NOT NULL,
	uf				CHAR(2)			NOT NULL,
	cnpj			VARCHAR(14)		NOT NULL,
	statusONG		VARCHAR(20)		NOT NULL,
)

--AprovacaoONG
CREATE TABLE AprovacaoONG
(
	id				BIGINT			NOT NULL IDENTITY(1,1) PRIMARY KEY,
	dataAprovacao	DATETIME		NOT NULL,
	statusAprovacao	CHAR(20)		NOT NULL,
	admin_id		BIGINT			NOT NULL,
	ong_id			BIGINT			NOT NULL,

	FOREIGN KEY (admin_id)	REFERENCES Administrador(id),
	FOREIGN KEY (ong_id)	REFERENCES ONG(id)
)

--ReprovacaoONG
CREATE TABLE ReprovacaoONG
(
	id				 BIGINT				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	dataReprovacao	 DATETIME			NOT NULL,
	statusReprovacao CHAR(20)			NOT NULL,
	admin_id		 BIGINT				NOT NULL,
	ong_id			 BIGINT				NOT NULL,

	FOREIGN KEY (admin_id)	REFERENCES Administrador(id),
	FOREIGN KEY (ong_id)	REFERENCES ONG(id)
)

--CategoriaRoupas:
CREATE TABLE Categoria
(
	id				BIGINT			NOT NULL IDENTITY(1,1) PRIMARY KEY,
	genero			VARCHAR(100)	NOT NULL,
)

--Inserts das Tabelas:

--ONG:
INSERT INTO ONG (nome, email, senha, telefone, descAtuacao, foto, cep, dataCadastro, sobreNos, uf, cnpj, statusONG) 
VALUES ('zezo', 'aaa@aaa.com', '111', '11952303304', 'somos uma ong teste', null, '1231234', '2021-01-08T00:00:00', 'aaaa', 'rj', '11111111111111', 'ativo')

INSERT INTO ONG (nome, email, senha, telefone, descAtuacao, foto, cep, dataCadastro, sobreNos, uf, cnpj, statusONG) 
VALUES ('leandro', 'bbb@aaa.com', '222', '11952303307', 'somos uma ong teste 2', null, '1231237', '2022-02-08T00:00:00', 'bbbb', 'sp', '22222222222222', 'ativo')

INSERT INTO ONG (nome, email, senha, telefone, descAtuacao, foto, cep, dataCadastro, sobreNos, uf, cnpj, statusONG) 
VALUES ('caua', 'ccc@aaa.com', '333', '11952303388', 'somos uma ong teste 3', null, '1231237', '2022-02-08T00:00:00', 'cccc', 'go', '33333333333333', 'ativo')

--Administrador:
INSERT INTO Administrador (cpf, email, nome, sobrenome, senha, uf, dataNasc, cep, dataCadastro, telefone, nivelAcesso, foto, statusAdmin) 
VALUES ('11111111111', 'AA@AA.COM', 'aaa', 'aaa', '11212', 'pe', '2021-01-08T00:00:00', '1234567', '2021-01-08T00:00:00', '11952303304', 'admin', null, 'ativo')

--Aprovacao:
INSERT INTO AprovacaoONG (dataAprovacao, statusAprovacao, ong_id, admin_id)
VALUES('2021-01-08T00:00:00', 'aprovado', 1, 1)

--Reprovacao:
INSERT INTO ReprovacaoONG (dataReprovacao, statusReprovacao, ong_id, admin_id)
VALUES('2021-01-08T00:00:00', 'reprovado', 1, 1)

--Categoria:
INSERT INTO Categoria (genero) 
VALUES ('DINHEIRO OU ROUPA')

--Selects:

--ONG
SELECT * FROM ONG
--Administrador
SELECT * FROM Administrador
--CategoriaRoupas
SELECT * FROM Categoria
--ContatoSuporte
SELECT * FROM AprovacaoONG
--ReprovaçãoONG
SELECT * FROM ReprovacaoONG


--Alterações feita com base na atualização em andamento do MER/DER, não mexer



--Protótipo SQL Charity Connect by ZsTech^
--https://github.com/WellingtonZs

--__________       ___________           .__       /\  
--\____    /  _____\__    ___/___   ____ |  |__   /  \ 
--  /     /  /  ___/ |    |_/ __ \_/ ___\|  |  \  \/\/ 
-- /     /_  \___ \  |    |\  ___/\  \___|   Y  \      
--/_______ \/____  > |____| \___  >\___  >___|  /      
--        \/     \/             \/     \/     \/       