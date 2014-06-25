package br.ufg.inf.es.sinoa.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.vo.Notificacao;

public class TelaVisitante extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_visitante);
	}
	
	public void iniciaTelaNoticiasPublicas(View view) {
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.PÚBLICA);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, Notificacao.AUSENTE);
		startActivity(intent);
	}

	
	public void iniciaTelaComunicados(View view) {
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.COMUNICADO);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, Notificacao.AUSENTE);
		startActivity(intent);
	}
}
