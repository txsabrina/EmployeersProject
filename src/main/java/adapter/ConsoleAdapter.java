package adapter;

import domain.Funcionario;
import usecase.FuncionarioService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ConsoleAdapter {
    private final FuncionarioService funcionarioService;
    private final Scanner scanner;

    public ConsoleAdapter(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        inicializarFuncionarios();

        while (true) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    removerFuncionario();
                    break;
                case 3:
                    aumentarSalarios();
                    break;
                case 4:
                    agruparPorFuncao();
                    break;
                case 5:
                    listarAniversariantes();
                    break;
                case 6:
                    imprimirFuncionarioMaisVelho();
                    break;
                case 7:
                    listarFuncionariosOrdenadosPorNome();
                    break;
                case 8:
                    imprimirTotalSalarios();
                    break;
                case 9:
                    imprimirSalariosEmSalariosMinimos();
                    break;
                case 0:
                    System.out.println("Saindo");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void inicializarFuncionarios() {
        funcionarioService.adicionarFuncionario(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarioService.adicionarFuncionario(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarioService.adicionarFuncionario(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarioService.adicionarFuncionario(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarioService.adicionarFuncionario(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarioService.adicionarFuncionario(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarioService.adicionarFuncionario(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarioService.adicionarFuncionario(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarioService.adicionarFuncionario(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarioService.adicionarFuncionario(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    private void exibirMenu() {
        System.out.println("1 - Listar funcionários");
        System.out.println("2 - Remover funcionário");
        System.out.println("3 - Aumentar salários em 10%");
        System.out.println("4 - Agrupar funcionários por função");
        System.out.println("5 - Listar aniversariantes dos meses 10 e 12");
        System.out.println("6 - Imprimir funcionário mais velho");
        System.out.println("7 - Listar funcionários em ordem alfabética");
        System.out.println("8 - Imprimir total dos salários");
        System.out.println("9 - Imprimir quantos salários mínimos cada funcionário ganha");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }


    private void listarFuncionarios() {
        System.out.println("\nLista de funcionários:");
        funcionarioService.listarFuncionarios().forEach(System.out::println);
    }

    private void removerFuncionario() {
        System.out.print("Digite o nome do funcionário a ser removido: ");
        String nome = scanner.nextLine();
        try {
            funcionarioService.removerFuncionarioPorNome(nome);
            System.out.println("Funcionário removido com sucesso");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void aumentarSalarios() {
        funcionarioService.aumentarSalarios(new BigDecimal("0.10"));
        System.out.println("Salários aumentados em 10%");
    }

    private void agruparPorFuncao() {
        System.out.println("\nFuncionários agrupados por função:");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarioService.agruparPorFuncao();
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });
    }

    private void listarAniversariantes() {
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarioService.listarAniversariantesDosMeses(10, 12).forEach(System.out::println);
    }

    private void imprimirFuncionarioMaisVelho() {
        Optional<Funcionario> funcionarioMaisVelho = funcionarioService.buscarFuncionarioMaisVelho();
        if (funcionarioMaisVelho.isPresent()) {
            Funcionario funcionario = funcionarioMaisVelho.get();
            System.out.println("Funcionário mais velho: " + funcionario.getNome());
        } else {
            System.out.println("Não encontrado");
        }
    }

    private void listarFuncionariosOrdenadosPorNome() {
        System.out.println("\nLista de funcionários em ordem alfabética:");
        funcionarioService.listarFuncionariosOrdenadosPorNome().forEach(System.out::println);
    }

    private void imprimirTotalSalarios() {
        BigDecimal totalSalarios = funcionarioService.calcularTotalSalarios();
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios));
    }

    private void imprimirSalariosEmSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarioService.calcularSalariosEmSalariosMinimos(salarioMinimo).forEach((funcionario, qtdSalariosMinimos) -> {
            System.out.println(funcionario.getNome() + ": " + qtdSalariosMinimos + " salários mínimos");
        });
    }
}
