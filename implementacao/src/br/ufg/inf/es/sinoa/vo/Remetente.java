package br.ufg.inf.es.sinoa.vo;

public class Remetente {

	private int id;
	private String nome;
	private String ativo;
	private int idUsuario;
	
	// constantes da ativação dos remetentes
	public static String ATIVADO = "true";
	public static String DESATIVADO = "false";

	// constantes dos remetentes padrão
	public static String COORDENADOR_CURSO = "Coordenador do curso";
	public static String DIRECAO_CURSO = "Direção de unidade do curso";
	public static String BIBLIOTECA = "Biblioteca";
	public static String PRO_REITORIA = "Pró-reitoria";
	public static String REITORIA = "Reitoria";

	public Remetente(int id, String nome, String ativo, int idUsuario) {
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return nome;
	}
}
