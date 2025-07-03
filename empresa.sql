-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.32-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para empresa
DROP DATABASE IF EXISTS `empresa`;
CREATE DATABASE IF NOT EXISTS `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `empresa`;

-- Copiando estrutura para tabela empresa.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` int(11) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `departamento` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricula` (`matricula`),
  CONSTRAINT `fk_funcionario_pessoa` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.funcionario: ~4 rows (aproximadamente)
DELETE FROM `funcionario`;
INSERT INTO `funcionario` (`id`, `matricula`, `departamento`) VALUES
	(1, 'F001', 'TI'),
	(2, 'F007', 'Marketing'),
	(3, 'F021', 'RH'),
	(5, 'F033', 'TI');

-- Copiando estrutura para tabela empresa.pessoa
DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.pessoa: ~6 rows (aproximadamente)
DELETE FROM `pessoa`;
INSERT INTO `pessoa` (`id`, `nome`, `email`) VALUES
	(1, 'Carlos Santana', 'carlos.santana@email.com'),
	(2, 'Beatriz Oliveira', 'beatriz.oliveira@email.com'),
	(3, 'Daniel Alves', 'daniel.alves@email.com'),
	(4, 'Fernanda Costa', 'fernanda.costa@email.com'),
	(5, 'Ricardo Gomes', 'ricardo.gomes@email.com'),
	(6, 'Ana Silva', 'ana.silva@email.com');

-- Copiando estrutura para tabela empresa.projeto
DROP TABLE IF EXISTS `projeto`;
CREATE TABLE IF NOT EXISTS `projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  `id_funcionario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projeto_funcionario` (`id_funcionario`),
  CONSTRAINT `fk_projeto_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela empresa.projeto: ~5 rows (aproximadamente)
DELETE FROM `projeto`;
INSERT INTO `projeto` (`id`, `nome`, `descricao`, `id_funcionario`) VALUES
	(1, 'Desenvolvimento do App Mobile', 'Criar o novo aplicativo institucional para Android e iOS.', 1),
	(2, 'Campanha de Lançamento Q3', 'Planejamento e execução da campanha de marketing para o terceiro trimestre.', 2),
	(3, 'Sistema de Ponto Eletrônico', 'Implementação do novo sistema de registro de ponto online.', 1),
	(4, 'Recrutamento de Novos Talentos 2025', 'Processo seletivo para contratação de 10 novos desenvolvedores.', 3),
	(5, 'Sistema de Vendas', 'Desenvolvimento do novo sistema de vendas da empresa.', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
