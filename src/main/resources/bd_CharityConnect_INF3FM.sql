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
	id				BIGINT			    NOT NULL IDENTITY(1,1) PRIMARY KEY,
	cpf				VARCHAR(11)		    NOT NULL,
	email			VARCHAR(100)	    UNIQUE	 NOT NULL,
	nome			VARCHAR(100)		NOT NULL,
	sobrenome		VARCHAR(100)		NOT NULL,
	senha			VARCHAR(100)	    NOT NULL,
	uf				CHAR(2)			    NOT NULL,
	dataNasc		DATETIME		    NOT NULL,
	cep				VARCHAR(9)		    NOT NULL,
	dataCadastro	DATETIME		    NULL,
	telefone		VARCHAR(15)		    NOT NULL,
	nivelAcesso		CHAR(10)		    NULL, --ADMIN ou USER
	statusAdmin		VARCHAR(20)		    NOT NULL,
)

--ONG:
CREATE TABLE ONG
(
	id				BIGINT		    	NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nome			VARCHAR(100)	    NOT NULL,
	nomeRep			VARCHAR(100)	    NOT NULL,
	email			VARCHAR(100)	    UNIQUE	 NOT NULL,
	senha			VARCHAR(100)	    NOT NULL,
	telefone		VARCHAR(15)		    NOT NULL,
	descAtuacao		VARCHAR(300)	    NOT NULL,
    interesse       VARCHAR(300)        NOT NULL,
	foto			VARBINARY(MAX)	    NULL,
	cep				VARCHAR(9)		    NOT NULL,
	dataCadastro	DATETIME		    NOT NULL,
	uf				CHAR(2)			    NOT NULL,
    cidade          VARCHAR(40)         NOT NULL,
	endereco		VARCHAR(40)         NOT NULL,
	bairro			VARCHAR(40)         NOT NULL,
	cnpj			VARCHAR(18)		    NOT NULL,
	statusONG		VARCHAR(20)		    NOT NULL,
)

--AprovacaoONG
CREATE TABLE AprovacaoONG
(
	id				BIGINT			    NOT NULL IDENTITY(1,1) PRIMARY KEY,
	dataAprovacao	DATETIME		    NOT NULL,
	statusAprovacao	VARCHAR(20)		    NOT NULL,
	admin_id		BIGINT			    NOT NULL,
	ong_id			BIGINT			    NOT NULL,

	FOREIGN KEY (admin_id)	REFERENCES Administrador(id),
	FOREIGN KEY (ong_id)	REFERENCES ONG(id)
)

--ReprovacaoONG
CREATE TABLE ReprovacaoONG
(
	id				 BIGINT				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	dataReprovacao	 DATETIME			NOT NULL,
	statusReprovacao VARCHAR(20)		NOT NULL,
	admin_id		 BIGINT				NOT NULL,
	ong_id			 BIGINT				NOT NULL,

	FOREIGN KEY (admin_id)	REFERENCES Administrador(id),
	FOREIGN KEY (ong_id)	REFERENCES ONG(id)
)

--Categoria:
CREATE TABLE Categoria
(
	id				BIGINT			    NOT NULL IDENTITY(1,1) PRIMARY KEY,
	genero			VARCHAR(100)	    NOT NULL,
)

--Contato:
CREATE TABLE Contato
(
	id				BIGINT			    NOT NULL IDENTITY(1,1) PRIMARY KEY,
	motivoContato	VARCHAR(100)	    NOT NULL,
	dataContato		DATETIME		    NOT NULL,
	pergunta		VARCHAR(500)	    NOT NULL,
	nome			CHAR(100)		    NOT NULL,
	sobrenome		CHAR(100)		    NOT NULL,
	email			VARCHAR(100)	    NOT NULL,
	telefone		VARCHAR(20)		    NOT NULL,
)

--Inserts das Tabelas:

--ONG:
INSERT INTO ONG (nome, nomerep, email, senha, telefone, descAtuacao, interesse, foto, cep, dataCadastro, uf, cidade, endereco, bairro, cnpj, statusONG) 
VALUES ('zezo', 'nomerep','aaa@aaa.com', '111', '11952303304', 'somos uma ong teste', 'teste interesse', null, '1231234', '2021-01-08T00:00:00', 'rj', 'teste', 'endereco', 'bairro', '11111111111111', 'ativo')

--Administrador:
INSERT INTO Administrador (cpf, email, nome, sobrenome, senha, uf, dataNasc, cep, dataCadastro, telefone, nivelAcesso, statusAdmin) 
VALUES ('11111111111', 'AA@AA.COM', 'aaa', 'aaa', '11212', 'pe', '2021-01-08T00:00:00', '1234567', '2021-01-08T00:00:00', '11952303304', 'admin', 'ativo')

--Aprovacao:
INSERT INTO AprovacaoONG (dataAprovacao, statusAprovacao, ong_id, admin_id)
VALUES('2021-01-08T00:00:00', 'aprovado', 1, 1)

--Reprovacao:
INSERT INTO ReprovacaoONG (dataReprovacao, statusReprovacao, ong_id, admin_id)
VALUES('2021-01-08T00:00:00', 'reprovado', 1, 1)

--Categoria:
INSERT INTO Categoria (genero) 
VALUES ('DINHEIRO OU ROUPA')

--Contato:
INSERT INTO Contato	(motivoContato, dataContato, pergunta, nome, sobrenome, email, telefone)
VALUES ('TESTE', '2021-01-08T00:00:00', 'PERGUNTA TESTE', 'NOME', 'SOBRENOME', 'EMAIL@EMAIL.COM', '11952303304')

--Selects:

--ONG
SELECT * FROM ONG
--Administrador
SELECT * FROM Administrador
--Categoria
SELECT * FROM Categoria
--ContatoSuporte
SELECT * FROM AprovacaoONG
--ReprovaçãoONG
SELECT * FROM ReprovacaoONG
--Contato
SELECT * FROM Contato

DROP TABLE ONG


--Alterações feita com base na atualização em andamento do MER/DER, não mexer



--Protótipo SQL Charity Connect by ZsTech^
--https://github.com/WellingtonZs

--__________       ___________           .__       /\  
--\____    /  _____\__    ___/___   ____ |  |__   /  \ 
--  /     /  /  ___/ |    |_/ __ \_/ ___\|  |  \  \/\/ 
-- /     /_  \___ \  |    |\  ___/\  \___|   Y  \      
--/_______ \/____  > |____| \___  >\___  >___|  /      
--        \/     \/             \/     \/     \/       