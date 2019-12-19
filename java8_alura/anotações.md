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
- interfaces que tem apenas um método