package org.example.model;

/**
 * Representa um projeto a ser desenvolvido na empresa.
 * <p>
 * Cada projeto possui um identificador único, um nome, uma descrição detalhada
 * e está obrigatoriamente vinculado a um funcionário responsável, referenciado
 * pelo seu ID.
 *
 * @author Seu Nome
 * @version 1.0
 * @see Funcionario
 */
public class Projeto {

        /**
         * O identificador único do projeto.
         * Este valor é normalmente gerado pelo banco de dados (AUTO_INCREMENT).
         */
        private int id;

        /**
         * O nome ou título do projeto.
         */
        private String nome;

        /**
         * Uma descrição mais detalhada sobre os objetivos e o escopo do projeto.
         */
        private String descricao;

        /**
         * A chave estrangeira que vincula o projeto a um funcionário.
         * Este ID deve corresponder ao ID de um registro existente na tabela 'funcionario'.
         */
        private int id_funcionario;

        /**
         * Retorna o ID do projeto.
         * @return o identificador único do projeto.
         */
        public int getId() {
                return id;
        }

        /**
         * Define o ID do projeto.
         * Usado principalmente para popular o objeto com dados do banco de dados.
         * @param id o novo ID a ser atribuído.
         */
        public void setId(int id) {
                this.id = id;
        }

        /**
         * Retorna o nome do projeto.
         * @return o nome do projeto.
         */
        public String getNome() {
                return nome;
        }

        /**
         * Define o nome do projeto.
         * @param nome o novo nome a ser atribuído.
         */
        public void setNome(String nome) {
                this.nome = nome;
        }

        /**
         * Retorna a descrição do projeto.
         * @return a descrição detalhada do projeto.
         */
        public String getDescricao() {
                return descricao;
        }

        /**
         * Define a descrição do projeto.
         * @param descricao a nova descrição a ser atribuída.
         */
        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        /**
         * Retorna o ID do funcionário responsável pelo projeto.
         * @return o ID do funcionário vinculado.
         */
        public int getId_funcionario() {
                return id_funcionario;
        }

        /**
         * Define o ID do funcionário responsável pelo projeto.
         * @param id_funcionario o ID do funcionário a ser vinculado.
         */
        public void setId_funcionario(int id_funcionario) {
                this.id_funcionario = id_funcionario;
        }

        /**
         * Retorna uma representação em String do objeto Projeto.
         * <p>
         * O formato inclui todos os atributos da classe, facilitando a
         * visualização dos dados para fins de depuração e log.
         *
         * @return uma String formatada com os dados do projeto.
         */
        @Override
        public String toString() {
                return "Projeto [ID=" + id + ", Nome=" + nome + ", Descrição=" + descricao +
                        ", ID Funcionário Responsável=" + id_funcionario + "]";
        }
}