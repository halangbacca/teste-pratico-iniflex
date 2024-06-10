import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 3 – Deve conter uma classe Principal para executar as seguintes ações:
public class Principal {
    private static Double salarioTotal = 0.0;

    public static void main(String[] args) {

        List<Funcionario> listaDeFuncionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela.
        Funcionario maria = new Funcionario.Builder()
                .nome("Maria")
                .dataNascimento(LocalDate.parse("2000-10-18"))
                .salario(new BigDecimal("2009.44"))
                .funcao("Operador")
                .build();
        listaDeFuncionarios.add(maria);

        Funcionario joao = new Funcionario.Builder()
                .nome("João")
                .dataNascimento(LocalDate.parse("1990-05-12"))
                .salario(new BigDecimal("2284.38"))
                .funcao("Operador")
                .build();
        listaDeFuncionarios.add(joao);

        Funcionario caio = new Funcionario.Builder()
                .nome("Caio")
                .dataNascimento(LocalDate.parse("1961-05-02"))
                .salario(new BigDecimal("9836.14"))
                .funcao("Coordenador")
                .build();
        listaDeFuncionarios.add(caio);

        Funcionario miguel = new Funcionario.Builder()
                .nome("Miguel")
                .dataNascimento(LocalDate.parse("1988-10-14"))
                .salario(new BigDecimal("19119.88"))
                .funcao("Diretor")
                .build();
        listaDeFuncionarios.add(miguel);

        Funcionario alice = new Funcionario.Builder()
                .nome("Alice")
                .dataNascimento(LocalDate.parse("1995-01-05"))
                .salario(new BigDecimal("2234.68"))
                .funcao("Recepcionista")
                .build();
        listaDeFuncionarios.add(alice);

        Funcionario heitor = new Funcionario.Builder()
                .nome("Heitor")
                .dataNascimento(LocalDate.parse("1999-11-19"))
                .salario(new BigDecimal("1582.72"))
                .funcao("Operador")
                .build();
        listaDeFuncionarios.add(heitor);

        Funcionario arthur = new Funcionario.Builder()
                .nome("Arthur")
                .dataNascimento(LocalDate.parse("1993-03-31"))
                .salario(new BigDecimal("4071.84"))
                .funcao("Contador")
                .build();
        listaDeFuncionarios.add(arthur);

        Funcionario laura = new Funcionario.Builder()
                .nome("Laura")
                .dataNascimento(LocalDate.parse("1994-07-08"))
                .salario(new BigDecimal("3017.45"))
                .funcao("Gerente")
                .build();
        listaDeFuncionarios.add(laura);

        Funcionario heloisa = new Funcionario.Builder()
                .nome("Heloísa")
                .dataNascimento(LocalDate.parse("2003-05-24"))
                .salario(new BigDecimal("1606.85"))
                .funcao("Eletricista")
                .build();
        listaDeFuncionarios.add(heloisa);

        Funcionario helena = new Funcionario.Builder()
                .nome("Helena")
                .dataNascimento(LocalDate.parse("1996-09-02"))
                .salario(new BigDecimal("2799.93"))
                .funcao("Gerente")
                .build();
        listaDeFuncionarios.add(helena);

        // 3.2 – Remover o funcionário "João" da lista.
        listaDeFuncionarios.remove(1);

        // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        // informação de data deve ser exibido no formato dd/mm/aaaa
        // informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        System.out.println("Imprimir todos os funcionários com todas suas informações");
        System.out.println(listaDeFuncionarios);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        listaDeFuncionarios.forEach(funcionario -> {
            funcionario.setSalario(funcionario.getSalario()
                    .add(funcionario.getSalario()
                            .multiply(new BigDecimal("0.1"))));
        });

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a "função" e o valor a "lista de funcionários".
        var mapFuncionarios = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("\nImprimir os funcionários, agrupados por função");
        System.out.println(mapFuncionarios);

        // 3.7 - Não existe

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12");
        listaDeFuncionarios.forEach(funcionario -> {
            if (funcionario.getDataNascimento().getMonth().getValue() == 10 ||
                    (funcionario.getDataNascimento().getMonth().getValue() == 12)) {
                System.out.println("- " + funcionario.getNome());
            }
        });

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Optional<Funcionario> funcionarioMaiorIdade = listaDeFuncionarios.stream()
                .min(Comparator.comparing(Pessoa::getDataNascimento));

        if (funcionarioMaiorIdade.isPresent()) {
            System.out.println(
                    "\nO funcionário com maior idade é " + funcionarioMaiorIdade.get().getNome() + " e este possui "
                            + (LocalDate.now().getYear() - funcionarioMaiorIdade.get().getDataNascimento().getYear()
                            + " anos de idade!"));
        } else {
            throw new RuntimeException();
        }

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nLista de funcionários por ordem alfabética");
        System.out.println(listaDeFuncionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .collect(Collectors.toList()));

        // 3.11 – Imprimir o total dos salários dos funcionários.
        listaDeFuncionarios.forEach(funcionario -> {
            salarioTotal += funcionario.getSalario().doubleValue();
        });

        // Arredonda o valor para duas casas decimais
        System.out.printf("\nO total dos salários dos funcionários é de: R$ %.2f %n\n", salarioTotal);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        listaDeFuncionarios.forEach(funcionario -> {
            var qtdSalarioMinimo = funcionario.getSalario()
                    .divide(new BigDecimal("1212.00"), RoundingMode.HALF_DOWN);
            System.out.println("O(A) funcionário(a) "
                    + funcionario.getNome() + " ganha o equivalente a "
                    + qtdSalarioMinimo + " salários mínimos!");
        });
    }
}
