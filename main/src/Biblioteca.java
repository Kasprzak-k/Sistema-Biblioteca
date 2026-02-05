import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros disponíveis");
            System.out.println("6 - Buscar livro por título/autor");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> emprestarLivro();
                case 4 -> devolverLivro();
                case 5 -> listarLivrosDisponiveis();
                case 6 -> buscarLivro();
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        livros.add(new Livro(titulo, autor, isbn));
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        usuarios.add(new Usuario(scanner.nextLine()));
    }

    private static void emprestarLivro() {
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn) && livro.isDisponivel()) {
                livro.emprestar();
                System.out.println("Livro emprestado com sucesso.");
                return;
            }
        }
        System.out.println("Livro não encontrado ou indisponível.");
    }

    private static void devolverLivro() {
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn) && !livro.isDisponivel()) {
                livro.devolver();
                System.out.println("Livro devolvido com sucesso.");
                return;
            }
        }
        System.out.println("Livro não encontrado ou já disponível.");
    }

    private static void listarLivrosDisponiveis() {
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        }
    }

    private static void buscarLivro() {
        System.out.print("Texto de busca: ");
        String busca = scanner.nextLine().toLowerCase();

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(busca) ||
                    livro.getAutor().toLowerCase().contains(busca)) {
                System.out.println(
                        livro.getTitulo() + " - " + livro.getAutor() +
                                " | Disponível: " + livro.isDisponivel()
                );
            }
        }
    }
}
