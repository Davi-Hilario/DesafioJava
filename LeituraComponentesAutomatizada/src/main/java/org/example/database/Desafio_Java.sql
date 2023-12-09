CREATE DATABASE IF NOT EXISTS Desafio_Java;
USE Desafio_Java;
-- DROP DATABASE IF EXISTS Desafio_Java;

-- ------------------- Criação das Tabelas -------------------

-- Tabela Servidor
CREATE TABLE IF NOT EXISTS Desafio_Java.Servidor
(
    id_servidor INT PRIMARY KEY AUTO_INCREMENT,
    nome_servidor VARCHAR(120),
    ipv6_servidor VARCHAR(39),
    mac_address CHAR(17),
    so_servidor VARCHAR(120)
);

-- Tabela Componente 
CREATE TABLE IF NOT EXISTS Desafio_Java.Componente
(
	id_componente INT PRIMARY KEY AUTO_INCREMENT,
    nome_componente VARCHAR(120)
);

-- Tabela Medida
CREATE TABLE IF NOT EXISTS Desafio_Java.Medida
(
	id_medida INT PRIMARY KEY AUTO_INCREMENT,
    nome_medida VARCHAR(30),
    simbolo_medida VARCHAR(5)
);

-- Taela ComponenteMedida
CREATE TABLE IF NOT EXISTS Desafio_Java.Componente_Medida(
	id_componente_medida INT PRIMARY KEY AUTO_INCREMENT,
    nome_componente_medida VARCHAR(120),
    tipo VARCHAR(80),
    valor_alerta_emergencia INT,
    valor_alerta_perigo INT,
    valor_alerta_prevencao INT,
    fk_componente INT NOT NULL, 
    fk_medida INT NOT NULL,
    FOREIGN KEY(fk_componente) REFERENCES Desafio_Java.Componente(id_componente),
    FOREIGN KEY(fk_medida) REFERENCES Desafio_Java.Medida(id_medida)
);

-- Taela ComponenteServidor
CREATE TABLE IF NOT EXISTS Desafio_Java.Componente_Servidor(
	id_componente_servidor INT PRIMARY KEY AUTO_INCREMENT,
    fk_servidor INT NOT NULL, 
    fk_componente_medida INT NOT NULL,
    FOREIGN KEY(fk_componente_medida) REFERENCES Desafio_Java.Componente_Medida(id_componente_medida),
    FOREIGN KEY(fk_servidor) REFERENCES Desafio_Java.Servidor(id_servidor)
);

-- Tabela Registro
CREATE TABLE IF NOT EXISTS Desafio_Java.Registro
(
	id_registro INT PRIMARY KEY AUTO_INCREMENT,
    fk_componente_servidor INT,
    valor_registro VARCHAR(45),
    momento_registro DATETIME,
    FOREIGN KEY(fk_componente_servidor) REFERENCES Desafio_Java.Componente_Servidor(id_componente_servidor)
);

-- ------------------- Inserindo Dados -------------------

-- Tabela Componente
INSERT INTO Desafio_Java.Componente VALUES
(NULL, "Cpu"),
(NULL, "Memoria"),
(NULL, "Disco"),
(NULL, "Rede");

-- Tabela Medida
INSERT INTO Desafio_Java.Medida VALUES
(NULL, "Temperatura", "°C"),
(NULL, "PorcentagemUso", "%"),
(NULL, "tamanhoGigaBytes", "Gb"),
(NULL, "Frequencia", "Hz"),
(NULL, "Latencia", "ms"),
(NULL, "bytesEnviados", "B"),
(NULL, "bytesRecebidos", "B");

-- Tabela Componente Medida
INSERT INTO Desafio_Java.Componente_Medida VALUES
(NULL, "Uso da CPU (%)", "USO_PORCENTAGEM_CPU", 95, 85, 75, 1, 2),
(NULL, "Frequência da CPU (Htz)", "FREQUENCIA_CPU", NULL, NULL, NULL, 1, 4),
(NULL, "Uso da Memória (%)", "USO_MEMORIA_PORCENTAGEM", 80, 75, 70, 2, 2),
(NULL, "Uso do Disco (%)", "USO_DISCO_PORCENTAGEM", 95, 90, 85, 3, 2),
(NULL, "Bytes Enviados", "BYTES_ENVIADOS_REDE", NULL, NULL, NULL, 4, 6),
(NULL, "Bytes Recebidos", "BYTES_RECEBIDOS_REDE", NULL, NULL, NULL, 4, 7),
(NULL, 'Temperatura da CPU (%)', 'TEMPERATURA_CPU', NULL, NULL, NULL, 1, 1);

-- ------------------- Selects -------------------

SELECT * FROM Desafio_Java.Servidor;
SELECT * FROM Desafio_Java.Componente;
SELECT * FROM Desafio_Java.Medida;
SELECT * FROM Desafio_Java.Componente_Medida;
SELECT * FROM Desafio_Java.Componente_Servidor;
SELECT * FROM Desafio_Java.Registro;

-- ------------------- Views -------------------

-- View Componentes Servidores
CREATE OR REPLACE VIEW Desafio_Java.View_Componentes_Servidores
AS
SELECT
	s.nome_servidor `Servidor`, 
    s.so_servidor `SO`,
    s.mac_address `Mac-Address`,
    cm.tipo `Tipo`,
    cs.id_componente_servidor `IdComponenteServidor`,
    c.nome_componente `Componente`,
    m.nome_medida `Medida`
FROM Desafio_Java.Servidor s
    JOIN Desafio_Java.Componente_Servidor cs on cs.fk_servidor = s.id_servidor
    JOIN Desafio_Java.Componente_Medida cm on cm.id_componente_medida = cs.fk_componente_medida
    JOIN Desafio_Java.Componente c on cm.fk_componente = c.id_componente
    JOIN Desafio_Java.Medida m on cm.fk_medida = m.id_medida;

-- ------------------- Selects Views -------------------

SELECT * FROM Desafio_Java.View_Componentes_Servidores;