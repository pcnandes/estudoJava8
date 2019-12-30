package java8;

public class Aluno {
	private String nome;
	private Long cpf;
	
	public Aluno(String nome, long cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return this.nome + "  ";
	}
	
}
