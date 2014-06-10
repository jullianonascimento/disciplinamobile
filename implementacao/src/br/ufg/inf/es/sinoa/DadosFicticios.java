package br.ufg.inf.es.sinoa;

import android.content.Context;

public class DadosFicticios {

	public static void criaNotificacoesFicticias(Context context) {

		Notificacao notificacao0 = new Notificacao(0, "publica", "27/05/2014",
				"Reitoria", "Processo Seletivo Para Preenchimento de Vagas UFG 2014-1", 
				"O Reitor da Universidade Federal de Goi�s (UFG) informa que se encontra aberto"
				+ " o Processo Seletivo Para Preenchimento de Vagas Dispon�veis 2014-1.", "false", "");
		Notificacao notificacao1 = new Notificacao(0, "comunicado",
				"28/05/2014", "Centro de Sele��o", "Processo Seletivo 2014/1 Comunicado Local de Prova do VHCE",
				"O Centro de Sele��o informa que o local de prova do VHCE ser� divulgado a partir das 18:00h do dia 30 "
				+ "de junho de 2014.", "false", "");
		Notificacao notificacao2 = new Notificacao(0, "publica",
				"29/05/2014", "Acessoria de Comunica��o", "Provas da segunda etapa do Processo Seletivo 2014-2",
				"O processo seletivo 2014-2 ter� suas provas da segunda etapa realizadas nos dias 8 e 9 de junho "
				+ "de 2014.", "false", "");
		Notificacao notificacao3 = new Notificacao(0, "nota",
				"30/05/2014", "Professor Jo�o", "Nota Final de Estruturas de Dados I",
				"Nota: 8,0\nFrequ�ncia: 58", "false", "Estruturas de Dados I");
		Notificacao notificacao4 = new Notificacao(0, "biblioteca",	"31/05/2014", "Biblioteca",
				"Vencimento do empr�stimo do Livro Engenharia de Software - 9a Edi��o",
				"Informamos que o empr�stimo do livro Engenharia de Software - 9a Edi��o "
				+ "2011 - Ian Sommerville tem sua data limite na pr�xima ter�a-feira dia 10/06/2014.",
				"false", "");
		Notificacao notificacao5 = new Notificacao(0, "prova",
				"01/06/2014", "Professor Jo�o", "Prova 1 de Estruturas de Dados I chegando",
				"Este aviso � para lembr�-lo que a primeira prova de Estruturas de Dados I"
				+ " acontecer� na pr�xima segunda-feira dia 09/06/2014.", "false", 
				"Estruturas de Dados I");

		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(context);

		notificacaoDAO.deletarTodasNotificacoes();

		notificacaoDAO.salvar(notificacao0);
		notificacaoDAO.salvar(notificacao1);
		notificacaoDAO.salvar(notificacao2);
		notificacaoDAO.salvar(notificacao3);
		notificacaoDAO.salvar(notificacao4);
		notificacaoDAO.salvar(notificacao5);

		// notificacaoDAO.fecharConexao();
	}

}
