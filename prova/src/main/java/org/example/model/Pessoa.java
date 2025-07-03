package org.example.model;

/**
 * Representa uma entidade Pessoa no sistema.
 * <p>
 * Esta é a classe base que armazena informações fundamentais sobre um indivíduo,
 * como seu identificador único, nome e e-mail. Ela serve como superclasse
 * para entidades mais específicas, como {@link Funcionario}, que herdam
 * seus atributos.
 *
 * @author Seu Nome
 * @version 1.0
 */
public class Pessoa {

        /**
         * O identificador único da pessoa.
         * Este valor é geralmente gerado automaticamente pelo banco de dados (AUTO_INCREMENT).
         */
        private int id;

        /**
         * O nome completo da pessoa.
         */
        private String nome;

        /**
         * O endereço de e-mail da pessoa.
         * Usado para contato e deve ser único no sistema.
         */
        private String email;

        /**
         * Retorna o ID da pessoa.
         * @return o identificador único da pessoa.
         */
        public int getId() {
                return id;
        }

        /**
         * Define o ID da pessoa.
         * Este método é usado principalmente para popular o objeto com dados do banco de dados.
         * @param id o novo ID a ser atribuído.
         */
        public void setId(int id) {
                this.id = id;
        }

        /**
         * Retorna o nome da pessoa.
         * @return o nome completo da pessoa.
         */
        public String getNome() {
                return nome;
        }

        /**
         * Define o nome da pessoa.
         * @param nome o novo nome a ser atribuído.
         */
        public void setNome(String nome) {
                this.nome = nome;
        }

        /**
         * Retorna o e-mail da pessoa.
         * @return o endereço de e-mail.
         */
        public String getEmail() {
                return email;
        }

        /**
         * Define o e-mail da pessoa.
         * @param email o novo endereço de e-mail a ser atribuído.
         */
        public void setEmail(String email) {
                this.email = email;
        }

        /**
         * Retorna uma representação textual do objeto Pessoa.
         * O formato é "Pessoa [ID=valor, Nome=valor, Email=valor]".
         * É útil para depuração e exibição de logs.
         *
         * @return uma String formatada com os dados da pessoa.
         */
        @Override
        public String toString() {
                return "Pessoa [ID=" + id + ", Nome=" + nome + ", Email=" + email + "]";
        }
}