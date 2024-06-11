package usecase;

import domain.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionarioPorNome(String nome) {
        boolean removido = funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
        if (!removido) {
            throw new NoSuchElementException("Funcionário não encontrado");
        }
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void aumentarSalarios(BigDecimal percentual) {
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(percentual));
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> listarAniversariantesDosMeses(int... meses) {
        List<Integer> mesesList = Arrays.stream(meses).boxed().collect(Collectors.toList());
        return funcionarios.stream()
                .filter(funcionario -> mesesList.contains(funcionario.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    public Optional<Funcionario> buscarFuncionarioMaisVelho() {
        return funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public List<Funcionario> listarFuncionariosOrdenadosPorNome() {
        return funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).collect(Collectors.toList());
    }

    public BigDecimal calcularTotalSalarios() {
        return funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Funcionario, BigDecimal> calcularSalariosEmSalariosMinimos(BigDecimal salarioMinimo) {
        return funcionarios.stream().collect(Collectors.toMap(
                funcionario -> funcionario,
                funcionario -> funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP)
        ));
    }
}
