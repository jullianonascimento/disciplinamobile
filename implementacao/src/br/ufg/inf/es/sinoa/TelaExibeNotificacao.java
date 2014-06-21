package br.ufg.inf.es.sinoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TelaExibeNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_exibe_notificacao);
		recebeNoticia();
	}
	
	public void recebeNoticia() {
		Intent intent = getIntent();

		String data = intent.getStringExtra("data");
		TextView textViewData = (TextView) findViewById(R.id.textViewData);
		textViewData.setText(data);

		String remetente = intent.getStringExtra("remetente");
		TextView textViewRemetente = (TextView) findViewById(R.id.textViewRemetente);
		textViewRemetente.setText(remetente);
		
		String titulo = intent.getStringExtra("titulo");
		TextView textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
		textViewTitulo.setText(titulo);

		String mensagem = intent.getStringExtra("texto");
		TextView textViewTexto = (TextView) findViewById(R.id.textViewTexto);
		textViewTexto.setText(mensagem);
	}

}
