import model.Funcionarios;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 3 – Deve conter uma classe Principal para executar as seguintes ações:
**/
public class Main {
    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionarios> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionarios("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
        funcionarios.add(new Funcionarios("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"));
        funcionarios.add(new Funcionarios("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"));
        funcionarios.add(new Funcionarios("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
        funcionarios.add(new Funcionarios("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionarios("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
        funcionarios.add(new Funcionarios("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"));
        funcionarios.add(new Funcionarios("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"));
        funcionarios.add(new Funcionarios("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85), "Eletricista"));
        funcionarios.add(new Funcionarios("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2799.93), "Gerente"));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        System.out.println("* LISTA DE FUNCIONÁRIOS *");
        funcionarios.forEach(System.out::println);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10"))));
//        System.out.println("-------------------------------------------------------------------");
//        funcionarios.forEach(System.out::println);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionarios>> funcionariosAgrupadosPorFuncao = new HashMap<>();

        for (Funcionarios funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();

            if (!funcionariosAgrupadosPorFuncao.containsKey(funcao)) {
                funcionariosAgrupadosPorFuncao.put(funcao, new ArrayList<>());
            }

            funcionariosAgrupadosPorFuncao.get(funcao).add(funcionario);
        }

        // 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("-------------------------------------------------------------------");
        System.out.println("* FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO *");
        for (Map.Entry<String, List<Funcionarios>> entry : funcionariosAgrupadosPorFuncao.entrySet()) {
            System.out.println(entry.getKey());
            for (Funcionarios funcionario : entry.getValue()) {
                System.out.println(funcionario);
            }
        }

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("-------------------------------------------------------------------");
        System.out.println("* ANIVERSARIANTES DE OUTUBRO E DEZEMBRO *");
        funcionarios.forEach(funcionario -> {
            if (funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12) {
                System.out.println(funcionario);
            }
        });

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("-------------------------------------------------------------------");
        Funcionarios funcionarioMaiorIdade = funcionarios.stream().min(Comparator.comparing(Funcionarios::getDataNascimento)).orElseThrow();
        LocalDate dataAtual = LocalDate.now();
        int idadeFuncionario = dataAtual.getYear() - funcionarioMaiorIdade.getDataNascimento().getYear();
        if (dataAtual.isBefore(
                funcionarioMaiorIdade.getDataNascimento().plusYears(idadeFuncionario))) {
            idadeFuncionario--;
        }
        System.out.println("Funcionário de Maior Idade: " + funcionarioMaiorIdade.getNome() +" | Idade: " + idadeFuncionario + " anos.");

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("-------------------------------------------------------------------");
        System.out.println("* LISTA DE FUNCIONÁRIOS EM ORDEM ALFABÉTICA *");
        funcionarios.sort(Comparator.comparing(Funcionarios::getNome,String.CASE_INSENSITIVE_ORDER));
        funcionarios.forEach(System.out::println);

        //3.11 – Imprimir o total dos salários dos funcionários.
        System.out.println("-------------------------------------------------------------------");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        List<BigDecimal> salariosFuncionarios = funcionarios.stream().map(Funcionarios::getSalario).toList();
        System.out.println("Total dos Salários dos Funcionários: R$" + numberFormat.format(salariosFuncionarios.stream().reduce(BigDecimal.ZERO, BigDecimal::add)));

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("-------------------------------------------------------------------");
        System.out.println("* SALÁRIOS MÍNIMOS POR FUNCIONÁRIO *");
        BigDecimal salarioMinimo = new BigDecimal(1212);
        funcionarios.forEach(funcionario -> {
            System.out.println(funcionario.getNome() + ": " + funcionario.getSalario().divideToIntegralValue(salarioMinimo).intValue()+ " salário(s) mínimo(s).");
        });
    }
}