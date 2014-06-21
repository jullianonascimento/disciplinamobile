package br.ufg.inf.es.sinoa;

public class Notificacao {
	 
    private int id;
    private String tipo;
    private String data;
    private String remetente;
    private String titulo;
    private String texto;
    private String status;
    private String disciplina;
    
    public static String lida = "true";
    public static String naoLida = "false";
 
    public Notificacao() {
 
    }
     
    public Notificacao(int id, String tipo, String data, String remetente,
    		String titulo, String texto, String status, String disciplina) {
        super();
        this.setId(id);
        this.setTipo(tipo);
        this.setData(data);
        this.setRemetente(remetente);
        this.setTitulo(titulo);
        this.setTexto(texto);
        this.setStatus(status);
        this.setDisciplina(disciplina);
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

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
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

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
 
	@Override
	public String toString() {
		return titulo;
	}
 
   
} 