package br.ufg.inf.es.sinoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaVisitante extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_visitante);
	}
	
	public void iniciaTelaNoticiasPublicas(View view) {
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra("tipo", "pública");
		startActivity(intent);
	}

	
	public void iniciaTelaComunicados(View view) {
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra("tipo", "comunicado");
		startActivity(intent);
	}

}
