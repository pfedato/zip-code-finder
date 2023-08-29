import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int menuOption = 1;
        boolean quit = false;
        String zipCode;
        String menu = """
                ****************************
                Bem vindo ao SRC - Sistema de Rastreamento de CEPs!
                
                Escolha uma opção no menu abaixo:                
                1 - Busque o CEP
                2 - Busque o CEP e grave o endereço
                0 - SAIR
                ****************************
                
                Digite a opção:                
                """;
        Scanner readingMenu = new Scanner(System.in);
        Scanner readingZipCodeInput = new Scanner(System.in);
        ZipCodeQuery zipCodeQuery = new ZipCodeQuery();

        while (!quit) {
            System.out.println(menu);
            menuOption = readingMenu.nextInt();

            if (menuOption == 1) {

                try {
                    System.out.println("Digite o CEP: ");
                    zipCode = readingZipCodeInput.nextLine();
                    Address newAddress = zipCodeQuery.searchAddress(zipCode);
                    System.out.println(newAddress);

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Finalizando a aplicação");

                }
            } else if (menuOption == 2) {
                System.out.println("Digite o CEP: ");
                zipCode = readingZipCodeInput.nextLine();

                try {
                    Address newAddress = zipCodeQuery.searchAddress(zipCode);
                    System.out.println(newAddress);
                    FileGenerator generator = new FileGenerator();
                    generator.saveJson(newAddress);
                    menuOption = Integer.parseInt(zipCode);
                    System.out.println("Arquivo gerado com sucesso!");

                } catch (RuntimeException | IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Finalizando a aplicação");
                    menuOption = 0;

                }
            } else if (menuOption == 0) {
                quit = true;
                break;
            } else {
                System.out.println("Opção inválida. Escolha entre as opções disponíveis.");

            }
        }
    }
}
