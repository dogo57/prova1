package org.example.main;

import org.example.dao.FuncionarioDAO;
import org.example.dao.PessoaDAO;
import org.example.dao.ProjetoDAO;
import org.example.model.Funcionario;
import org.example.model.Pessoa;
import org.example.model.Projeto;

/**
 * Classe principal da aplicação.
 * <p>
 * O objetivo desta classe é servir como ponto de entrada (entry point) para demonstrar
 * e testar as funcionalidades de persistência de dados implementadas nas classes DAO
 * (Data Access Object).
 * <p>
 * Ela executa uma série de operações para simular o fluxo de uso do sistema,
 * incluindo cadastros bem-sucedidos e tentativas de operações que devem falhar
 * de acordo com as regras de negócio definidas.
 *
 * @author Seu Nome
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que inicia a execução dos testes.
     * <p>
     * Este método instancia os DAOs e executa os seguintes cenários de teste:
     * <ol>
     *     <li><b>Testes de Criação:</b> Simula o cadastro sequencial de uma pessoa, um funcionário
     *     e um projeto, demonstrando o fluxo de sucesso ("happy path").</li>
     *     <li><b>Testes de Falha (Regras de Negócio):</b> Executa operações que violam
     *     intencionalmente as regras de negócio para garantir que as validações estão funcionando:
     *         <ul>
     *             <li>Tenta criar um funcionário para uma pessoa inexistente (Regra 1).</li>
     *             <li>Tenta criar um projeto vinculado a um funcionário inexistente (Regra 2).</li>
     *             <li>Tenta excluir um funcionário que está vinculado a um projeto (Regra 3).</li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {

        // Instancia os objetos de acesso a dados para cada entidade.
        PessoaDAO pessoaDAO = new PessoaDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO();

        // --- INÍCIO DOS TESTES DE CRIAÇÃO BEM-SUCEDIDA ---
        System.out.println("--- INICIANDO CADASTROS (FLUXO DE SUCESSO) ---");

        // 1. Cria uma nova instância de Pessoa e a persiste no banco de dados.
        Pessoa p1 = new Pessoa();
        p1.setNome("Ana Silva");
        p1.setEmail("ana.silva@email.com");
        pessoaDAO.create(p1); // Espera-se que esta operação funcione.

        // NOTA: Em uma aplicação real, o método create deveria retornar o ID gerado pelo banco.
        // Para simplificar este teste, assumimos que o primeiro registro inserido terá o ID 1,
        // especialmente se a tabela foi limpa antes da execução.
        int idPessoaCriada = 1;

        // 2. Cria um funcionário, vinculando-o à pessoa criada anteriormente.
        Funcionario f1 = new Funcionario();
        f1.setId(idPessoaCriada); // Vincula ao ID da pessoa Ana Silva.
        f1.setMatricula("F001");
        f1.setDepartamento("TI");
        funcionarioDAO.create(f1); // Espera-se que esta operação funcione.

        // 3. Cria um projeto, vinculando-o ao funcionário recém-criado.
        Projeto proj1 = new Projeto();
        proj1.setNome("Sistema de Vendas");
        proj1.setDescricao("Desenvolvimento do novo sistema de vendas da empresa.");
        proj1.setId_funcionario(idPessoaCriada); // Vincula ao ID do funcionário f1.
        projetoDAO.create(proj1); // Espera-se que esta operação funcione.


        // --- INÍCIO DOS TESTES DE VALIDAÇÃO DE REGRAS DE NEGÓCIO ---
        System.out.println("\n--- INICIANDO TESTES DE REGRAS DE NEGÓCIO (FLUXO DE ERRO) ---");

        // Teste da Regra 1: Tentar criar um funcionário com um ID de pessoa que não existe.
        System.out.println("\n-> Testando Regra 1: Criar funcionário com pessoa inexistente...");
        Funcionario f2_erro = new Funcionario();
        f2_erro.setId(99); // ID de pessoa presumivelmente inexistente.
        f2_erro.setMatricula("F002");
        f2_erro.setDepartamento("RH");
        funcionarioDAO.create(f2_erro); // Deve exibir uma mensagem de erro e falhar.

        // Teste da Regra 2: Tentar criar um projeto vinculado a um funcionário que não existe.
        System.out.println("\n-> Testando Regra 2: Criar projeto com funcionário inexistente...");
        Projeto proj2_erro = new Projeto();
        proj2_erro.setNome("Sistema de RH");
        proj2_erro.setDescricao("Portal do colaborador.");
        proj2_erro.setId_funcionario(99); // ID de funcionário presumivelmente inexistente.
        projetoDAO.create(proj2_erro); // Deve exibir uma mensagem de erro e falhar.

        // Teste da Regra 3: Tentar excluir um funcionário que está vinculado a um projeto.
        System.out.println("\n-> Testando Regra 3: Excluir funcionário vinculado a projeto...");
        funcionarioDAO.delete(idPessoaCriada); // Deve falhar, pois Ana Silva (ID 1) está no "Sistema de Vendas".

        System.out.println("\n--- FIM DOS TESTES ---");
    }
}