import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// 2 – Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Funcionario extends Pessoa {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private BigDecimal salario;
    private String funcao;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salario, funcao);
    }

    @Override
    public String toString() {
        return "\nNome = " + getNome() +
                "\nData de Nascimento = " + getDataNascimento().format(formatter) +
                "\nSalário = " + getSalario().toString().replace(".", ",") +
                "\nFunção = " + getFuncao() + "\n";
    }

    public static final class Builder {
        private BigDecimal salario;
        private String funcao;
        private String nome;
        private LocalDate dataNascimento;

        public Builder salario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Builder funcao(String funcao) {
            this.funcao = funcao;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Funcionario build() {
            Funcionario funcionario = new Funcionario();
            funcionario.setSalario(salario);
            funcionario.setFuncao(funcao);
            funcionario.setNome(nome);
            funcionario.setDataNascimento(dataNascimento);
            return funcionario;
        }
    }
}
