package br.ufg.inf.es.sinoa;


public class Noticia {
	
	private int id;
	private String data;
	private String remetente;
	private String texto;
	
	public Noticia() {
		
	}
	
	public Noticia(int id, String data, String remetente, String texto) {
		super();
		this.id = id;
		this.data = data;
		this.remetente = remetente;
		this.texto = texto;
	}
	
	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
	
	public String getRemetente() {
		return remetente;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
    public String toString() {
        return data + " " +  remetente + " " + texto;
    }
	
}
