package org.example.model;

/**
 * Representa um funcionário da empresa.
 * <p>
 * Esta classe estende a classe {@link Pessoa}, herdando seus atributos básicos
 * como id, nome e e-mail. Ela adiciona informações específicas de um funcionário,
 * como a matrícula e o departamento ao qual ele pertence.
 * <p>
 * A relação de herança "Funcionario é uma Pessoa" é uma peça central deste modelo.
 * O ID de um funcionário é o mesmo ID da pessoa correspondente.
 *
 * @author Seu Nome
 * @version 1.0
 * @see Pessoa
 */
public class Funcionario extends Pessoa {

        /**
         * Código único de identificação do funcionário dentro da empresa.
         * Exemplo: "F001", "F007".
         */
        private String matricula;

        /**
         * A área ou setor da empresa onde o funcionário está alocado.
         * Exemplo: "TI", "RH", "Marketing".
         */
        private String departamento;

        /**
         * Retorna a matrícula do funcionário.
         * @return a matrícula do funcionário.
         */
        public String getMatricula() {
                return matricula;
        }

        /**
         * Define a matrícula do funcionário.
         * @param matricula a nova matrícula a ser atribuída.
         */
        public void setMatricula(String matricula) {
                this.matricula = matricula;
        }

        /**
         * Retorna o departamento do funcionário.
         * @return o nome do departamento.
         */
        public String getDepartamento() {
                return departamento;
        }

        /**
         * Define o departamento do funcionário.
         * @param departamento o novo departamento a ser atribuído.
         */
        public void setDepartamento(String departamento) {
                this.departamento = departamento;
        }

        /**
         * Retorna uma representação em String do objeto Funcionario.
         * <p>
         * O formato inclui os atributos herdados de Pessoa (ID, Nome, Email)
         * e os atributos específicos de Funcionario (Matrícula, Departamento),
         * facilitando a visualização dos dados para depuração e logs.
         *
         * @return uma String formatada com os dados do funcionário.
         */
        @Override
        public String toString() {
                return "Funcionario [ID=" + getId() + ", Nome=" + getNome() + ", Email=" + getEmail() +
                        ", Matrícula=" + matricula + ", Departamento=" + departamento + "]";
        }
}