package java8;

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
