package br.ufg.inf.es.sinoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TelaExibeNoticia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_exibe_noticia);

		recebeNoticia();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exibe_noticia, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void recebeNoticia() {
		Intent intent = getIntent();

		String data = intent.getStringExtra("data");
		TextView textViewData = (TextView) findViewById(R.id.textViewData);
		textViewData.setText(data);

		String remetente = intent.getStringExtra("remetente");
		TextView textViewRemetente = (TextView) findViewById(R.id.textViewRemetente);
		textViewRemetente.setText(remetente);

		String mensagem = intent.getStringExtra("texto");
		TextView textViewTexto = (TextView) findViewById(R.id.textViewTexto);
		textViewTexto.setText(mensagem);

	}

}
