#Java 8

## Default methods
Agora no java 8 as interfaces podem ter metodos implementados (https://docs.oracle.com/javase/8/docs/api/java/util/List.html)

- método default é um metodo de interface que você não precisa implementar na sua classe se não quiser, pois você terá já essa implementação default;
- Esses metodos só podem acessar metodos estaticos ou metodos da classe passada para eles;

ex.: Interface lista de arraylist
public interface List<E> extends Collection<E> {
...
	default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
...
}

## Classes anonimas
Não é novidade do java8, mas sao classes instanciadas diretamente no momento de uso.


## Interfaces Funcionais
- interface que possui apenas um método abstrato;
- além desse método ela pode ter outros métodos, contanto que sejam default ou 'static';
- apenas interfaces funcionais podem receber lambdas;
- é preciso que o lambda seja compativel com a interface funcional, ou seja, recebe e retorna atributos compativeis;

## Lambda
- funciona para qualquer interface que tenha apenas um método abstrato;

Ex.:
>
public class LambdaEx2 {
	public static void main(String[] args) {
		// execucao normal
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		        System.out.println("Executando um Runnable");
		    }
		}).start();
		// execucao lambda
		new Thread(() -> System.out.println("Executando um Runnable com lambda")).start();
	}
}

## Method referance

Dizemos que Comparator.comparing recebe um lambda
Na verdade, ela recebe uma instância de uma interface funcional. No caso é a interface Function que tem apenas um método, o apply.
>
metodo default em Comparator
palavras.sort(Comparator.comparing(s -> s.length()));

- Com method referece temos uma forma ainda mais sucinta de escrever

> palavras.sort(Comparator.comparing(String::length));

Ainda mais curta usando import statico
> import static java.util.Comparator.*;
palavras.sort(comparing(String::length));

Outros exemplos
> palavras.forEach(s -> System.out.println(s));
palavras.forEach(System.out::println);

> Function<Usuario, String> funcao = u -> u.getNome();
Function<Usuario, String> funcao = Usuario::getNome;

obs.: Nem sempre é possivel usar method reference

ex:
> s -> s.substring(0, 5)


## Strean
Streams sao fluxos de dados. Streams foram criadas para separar o tratamendo de dados, como listas. No lugar de aplicar metodos diretamente nas lsitas, vc é obrigado
a coloca-lá em uma strem, trabalhar os dados e depois retorna-lá para lista novamente.

- Stream são usadas principalmente para trabalhar com coleções

ex.: 
> cursos.stream()
	.filter(c -> c.getAlunos() >= 100)
	.map(Curso::getAlunos)
	.forEach(System.out::println);

- Na stream os metodos sao empilhados, onde o seguinte recebe o resultado do anterior.

ex.:
> int sum = cursos.stream()
	.filter(c -> c.getAlunos() >= 100)
	.mapToInt(Curso::getAlunos)
	.sum();

Obs.: repare que apos usar o mapToInt retorna um inteiro e logo abaixo já posso usar um sum() direto. Ou seja meu objeto foi transformado em um inteiro no meio do processo.

### Mais novidades
- Lista imutável
> Collections.unmodifiableList(Arrays.asList("joao", "fernanda"));

- of -> transforma objetos em um array e ja deixa a lista imutável; Pode ser chamado de List, Set e Map
> return List.of(joao, fernanda);

- flatMap -> o map retornaria um array, criando um array de array. Já o flatMap retorna o próprio objeto modificado.
> public static List<String> transformaEmListaDeNomes(List<Aluno> lista) {
	return lista
			.stream()
			.flatMap(a -> Stream.ofNullable(a.getNome()))
			.map(s -> s.toUpperCase())
			.collect(Collectors.toList());
	}



## Optional
- usado para trabalhar melhor com nulls

metodos:
- get -> retorna o elemento, se o mesmo nao existir retorna exception
- optionalCurso.orElse(null); -> pega se existir e indica oq passar caso nao exista;
- optionalCurso.ifPresent(c -> System.out.println(c.getNome())); -> executa uma funcao caso exista o atributo
- optionalCurso.ifPresentOrElse(System.out::println,
				() -> System.out.println("não encontrado!"));
- or -> permite executar uma funcao caso o retorno seja null
> Optional<Aluno> alunoRecuperado = alunoServico.listarPorCpf(0L)
				.or(() -> alunoServico.listarPorCpf(11111L))
				.or(() -> alunoServico.listarPorCpf(1L));

ex.: 
> Optional<Curso> curso = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny();


## Nova api de data

Trabalhando com datas
> LocalDate futuro = LocalDate.of(2020, Month.JUNE, 4);
> int anos = futuro.getYear() - data.getYear();

> Period periodo = Period.between(data, futuro);
		System.out.println(periodo.getYears() + periodo.getDays() + periodo.getMonths());
		
> System.out.println(futuro.plusYears(5));
  System.out.println(futuro.minusDays(5));
  
> DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  System.out.println(formatador.format(futuro));
  
Trabalhando com horas
> DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
  LocalDateTime agora = LocalDateTime.now();
  LocalTime hora = LocalTime.of(15, 30, minute)
  
	
## Jshell
Permite executar comando a partir do console. Semelahnto ao JS no navegador

> jshell

## Var
Permite criar uma variável sem definir o tipo
EX.:

> Optional<Aluno> aluno = alunoServico.listarPorCpf(1L);

> // java sabe que o retnor será uma lista de alunos
> var aluno = alunoServico.listarPorCpf(1L);

