package br.ufg.inf.es.sinoa.vo;

public class Disciplina {

	private int codigo;
	private String nome;
	private int idUsuario;
	
	public Disciplina(int codigo, String nome, int idUsuario){
		this.codigo = codigo;
		this.nome = nome;
		this.idUsuario = idUsuario;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Override
	public String toString(){
		return nome;
	}
}
