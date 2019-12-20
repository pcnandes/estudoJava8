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


# Strean
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


# Optional
- usado para trabalhar melhor com nulls

ex.: 
> Optional<Curso> curso = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny();
REFAZER O video 5