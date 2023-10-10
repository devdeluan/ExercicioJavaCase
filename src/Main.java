import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<PessoaFisica> listaPf= new ArrayList<>();
        PessoaFisica metodosPf = new PessoaFisica();

        ArrayList<PessoaJuridica> listaPj = new ArrayList<>();
        PessoaJuridica metodosPj = new PessoaJuridica();


        System.out.println("Bem vindo ao sistema!!");

        Scanner leitor = new Scanner(System.in);
        int opcao;

        do {

            System.out.print("Escolha uma opçao: 1 - Pessoa Física / 2 - Pessoa Jurídica / 0 - Sair ");
            opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    int opcaoPf;
                    do {

                        System.out.println("Escolha uma opçao: 1 - Cadastrar pessoa Física / 2 - Listar pessoa física / 0 - Voltar ao menu anterior");
                        opcaoPf = leitor.nextInt();

                        switch (opcaoPf){
                            case 1:
                                PessoaFisica novaPf = new PessoaFisica();

                                Endereco novoEndPf = new Endereco();

                                System.out.println("Digite o nome da Pessoa física: ");
                                novaPf.nome = leitor.next();

                                System.out.println("Digite o CPF: ");
                                novaPf.cpf = leitor.next();

                                System.out.println("Digite o rendimento mensal (Digite somente numero): ");
                                novaPf.rendimento = leitor.nextInt();

                                System.out.println("Digite a data de Nascimento (dd/MM/aaaa): ");

                                LocalDate date = LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                Period periodo = Period.between(date, LocalDate.now());

                                novaPf.dataNascimento = date;

                                if (periodo.getYears() >= 18) {
                                    System.out.println("A pessoa tem mais de 18 anos");
                                } else {
                                    System.out.println("A pessoa tem menos de 18 anos. Retornando MENU ...");
                                    break;
                                }

                                System.out.println("Digite o logradouro: ");
                                novoEndPf.logradouro = leitor.next();

                                System.out.println("Digite o numero: ");
                                novoEndPf.numero = leitor.next();

                                System.out.println("Este endereço é comercial? S/N: ");
                                String endCom;
                                endCom = leitor.next();

                                if (endCom.equalsIgnoreCase("S")){
                                    novoEndPf.enderecoComercial = true;
                                } else {
                                    novoEndPf.enderecoComercial = false;
                                }

                                novaPf.endereco = novoEndPf;

                                listaPf.add(novaPf);
                                System.out.println("Cadastro realizado com sucesso!!");


                                break;
                            case 2:
                                if (listaPf.size() > 0 ){

                                    for (PessoaFisica cadaPf: listaPf){
                                        System.out.println();
                                        System.out.println("Nome: " + cadaPf.nome);
                                        System.out.println("CPF: " + cadaPf.cpf);
                                        System.out.println("Endereço: " + cadaPf.endereco.logradouro + ", " + cadaPf.endereco.numero);
                                        System.out.println("Data de Nascimento: " + cadaPf.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                        System.out.println("Imposto a ser pago: " + metodosPf.CalcularImposto(cadaPf.rendimento));
                                        System.out.println();
                                        System.out.println("Digite o 0 para continuar");
                                        leitor.nextLine();
                                    }

                                    opcaoPf = leitor.nextInt();

                                } else {
                                    System.out.println("Lista vazia!!");
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao menu anterior!!");
                                break;
                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }

                    } while(opcaoPf != 0);
                    break;
                case 2:

                    int opcaoPj;
                    do {
                        System.out.println("Digite uma opção: 1-Cadastrar PJ / 2-Listar PJ / 0-Voltar");
                        opcaoPj = leitor.nextInt();

                        switch (opcaoPj) {
                            case 1:
                                PessoaJuridica novaPj = new PessoaJuridica();
                                Endereco novoEndPj = new Endereco();

                                System.out.println("Digite a razão social: ");
                                novaPj.razaoSocial = leitor.nextLine();

                                System.out.println("Digite o cnpj: ");
                                novaPj.cnpj = leitor.nextLine();

                                System.out.println("Digite o rendimento: ");
                                novaPj.rendimento = leitor.nextFloat();

                                System.out.println("Digite o logradouro");
                                novoEndPj.logradouro = leitor.next();

                                System.out.println("Digite o numero");
                                novoEndPj.numero = leitor.next();

                                System.out.println("Este endereço é comercial? S/N:");
                                String endCom = leitor.next();

//                                if (endCom.equalsIgnoreCase("s")){
                                if (endCom.equals("S") || endCom.equals("s")) {
                                    novoEndPj.enderecoComercial = true;
                                } else {
                                    novoEndPj.enderecoComercial = false;
                                }

                                novaPj.endereco = novoEndPj;

                                listaPj.add(novaPj);

                                System.out.println("Cadastro realizado com sucesso!");
                                break;


                            case 2:

                                if (listaPj.size() > 0) {

                                    for (PessoaJuridica cadaPj : listaPj) {
                                        System.out.println("Nome: " + cadaPj.razaoSocial);
                                        System.out.println("CPF: " + cadaPj.cnpj);
                                        System.out.println("Imposto a ser pago: " + metodosPj.CalcularImposto(cadaPj.rendimento));
                                        System.out.println("Endereço: " + cadaPj.endereco.logradouro + "-" + cadaPj.endereco.numero);

                                        System.out.println();
                                        System.out.println("Aperte ENTER para continuar");
                                        leitor.nextLine();
                                    }

                                } else {
                                    System.out.println("Lista vazia!");
                                }
                                break;

                            case 0:
                                System.out.println("Volta");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                         }

                        }while(opcaoPj != 0);

                        break;

                case 0:
                    System.out.println("Obrigado por utilizar o nosso sistema! Forte abraço! ");
                    break;
                default:
                    System.out.println("Opção inválida, por favor digite uma opção válida!");
                    break;
            }
        } while (opcao != 0);

    }
}