package com.literalura;

import com.literalura.model.*;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import com.literalura.service.ConsumoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var teclado = new Scanner(System.in);
		var continuar = true;

		while (continuar) {
			System.out.println("""
                -------------------------------------------------
                Escolha uma opção:
                1 - Buscar livro por título
                2 - Listar todos os livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros por idioma
                0 - Sair
                -------------------------------------------------
                """);

			try {
				var opcao = teclado.nextInt();
				teclado.nextLine(); // Consome a quebra de linha

				switch (opcao) {
					case 1:
						buscarLivroPorTitulo(teclado);
						break;
					case 2:
						listarLivrosRegistrados();
						break;
					case 3:
						listarAutoresRegistrados();
						break;
					case 4:
						listarAutoresVivosNoAno(teclado);
						break;
					case 5:
						listarLivrosPorIdioma(teclado);
						break;
					case 0:
						System.out.println("Saindo da aplicação...");
						continuar = false;
						break;
					default:
						System.out.println("Opção inválida!");
						break;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Entrada inválida! Por favor, digite um número.");
				teclado.nextLine(); // Limpa o buffer do scanner para a próxima entrada
			}
		}
	}

	private void buscarLivroPorTitulo(Scanner teclado) {
		var consumoApi = new ConsumoApi();
		System.out.println("Digite o título do livro que você quer buscar:");
		var titulo = teclado.nextLine();
		var json = consumoApi.obterDados("https://gutendex.com/books/?search=" + titulo.replace(" ", "%20"));
		DadosBusca dados = consumoApi.converterDados(json, DadosBusca.class);

		if (dados.resultados().isEmpty()) {
			System.out.println("Nenhum livro encontrado com este título.");
		} else {
			DadosLivro dadosLivro = dados.resultados().get(0);
			if (livroRepository.existsByTitulo(dadosLivro.titulo())) {
				System.out.println("Livro já está cadastrado.");
			} else {
				Autor autor = null;
				if (!dadosLivro.autores().isEmpty()) {
					DadosAutor dadosAutor = dadosLivro.autores().get(0);
					Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());
					if (autorExistente.isPresent()) {
						autor = autorExistente.get();
					} else {
						autor = new Autor(dadosAutor);
						autorRepository.save(autor); // Salva o novo autor
					}
				}

				Livro livro = new Livro(dadosLivro);
				livro.setAutor(autor);
				livroRepository.save(livro); // Salva o livro

				System.out.println("Livro encontrado e salvo no banco de dados:");
				System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor().getNome() + " | Idioma: " + livro.getIdioma());
			}
		}
	}

	@Transactional
	public void listarLivrosRegistrados() {
		List<Livro> livros = livroRepository.findAllComAutor();
		System.out.println("---- LIVROS REGISTRADOS ----");
		livros.forEach(livro -> {
			System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor().getNome() + " | Idioma: " + livro.getIdioma());
		});
		System.out.println("----------------------------");
	}

	private void listarAutoresRegistrados() {
		List<Autor> autores = autorRepository.findAll();
		System.out.println("---- AUTORES REGISTRADOS ----");
		autores.forEach(autor -> System.out.println("Nome: " + autor.getNome() + " | Nascimento: " + autor.getAnoDeNascimento() + " | Falecimento: " + autor.getAnoDeFalecimento()));
		System.out.println("----------------------------");
	}

	private void listarAutoresVivosNoAno(Scanner teclado) {
		System.out.println("Digite o ano:");
		var ano = teclado.nextInt();
		List<Autor> autoresVivos = autorRepository.findByAnoDeFalecimentoIsNullOrAnoDeFalecimentoGreaterThan(ano);
		System.out.println("---- AUTORES VIVOS NO ANO " + ano + " ----");
		autoresVivos.forEach(autor -> System.out.println("Nome: " + autor.getNome()));
		System.out.println("----------------------------");
	}

	private void listarLivrosPorIdioma(Scanner teclado) {
		System.out.println("Digite o idioma (ex: en, fr, pt):");
		var idioma = teclado.nextLine();
		List<Livro> livros = livroRepository.findByIdioma(idioma);
		System.out.println("---- LIVROS NO IDIOMA " + idioma.toUpperCase() + " ----");
		livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor().getNome()));
		System.out.println("----------------------------");
	}
}