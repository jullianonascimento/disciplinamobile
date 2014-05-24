package br.ufg.inf.es.sinoa;

import java.util.Date;

public class Noticia {

	private Date data;
	private String remetente;
	private String texto;
	
	public Noticia(Date data, String remetente, String texto) {
		this.data = data;
		this.remetente = remetente;
		this.texto = texto;
	}
	
	public Date getData() {
		return data;
	}
	
	public String getRemetente() {
		return remetente;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
