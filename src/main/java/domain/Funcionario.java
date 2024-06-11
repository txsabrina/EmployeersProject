package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal aumento = salario.multiply(percentual);
        salario = salario.add(aumento);
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() +
                ", Data de Nascimento: " + getDataNascimento().toString() +
                ", Salário: " + String.format("%,.2f", salario) +
                ", Função: " + funcao;
    }
}
