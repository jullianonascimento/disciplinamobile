package br.ufg.inf.es.sinoa.vo;

public class Usuario {
	
	private int matricula;
	private String nome;
	private String email;
	private String senha;
	private String curso;
	
	public Usuario(int matricula, String nome, String email, String senha, String curso){
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.curso = curso;
	}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return nome;
	}
}
