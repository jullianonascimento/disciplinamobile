package br.ufg.inf.es.sinoa.vo;

public class Notificacao {

	private int id;
	private String tipo;
	private String data;
	private int idRemetente;
	private String titulo;
	private String texto;
	private String status;
	private int idDisciplina;
	private int idUsuario;

	// constantes do status
	public static String LIDA = "true";
	public static String NAOLIDA = "false";

	// constantes do tipo da notificacao
	public static String PÚBLICA = "publica";
	public static String COMUNICADO = "comunicado";
	public static String NOTA_FREQUENCIA = "nota";
	public static String AVISO_BIBLIOTECA = "biblioteca";
	public static String AVISO_PROVA = "aviso";

	// construtor do banco
	public Notificacao(int id, String tipo, String data, int idRemetente,
			String titulo, String texto, String status, int idDisciplina,
			int idUsuario) {
		this.id = id;
		this.tipo = tipo;
		this.data = data;
		this.idRemetente = idRemetente;
		this.titulo = titulo;
		this.texto = texto;
		this.status = status;
		this.idDisciplina = idDisciplina;
		this.idUsuario = idUsuario;
	}

	public Notificacao(int id, String tipo, String data, int idRemetente,
			String titulo, String texto, int idDisciplina, int idUsuario) {
		this.id = id;
		this.tipo = tipo;
		this.data = data;
		this.idRemetente = idRemetente;
		this.titulo = titulo;
		this.texto = texto;
		this.status = NAOLIDA;
		this.idDisciplina = idDisciplina;
		this.idUsuario = idUsuario;
	}

	public Notificacao(int id, String tipo, String data, int idRemetente,
			String titulo, String texto) {
		this.id = id;
		this.tipo = tipo;
		this.data = data;
		this.idRemetente = idRemetente;
		this.titulo = titulo;
		this.texto = texto;
		this.status = NAOLIDA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getIdRemetente() {
		return idRemetente;
	}

	public void setIdRemetente(int idRemetente) {
		this.idRemetente = idRemetente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return titulo;
	}
}