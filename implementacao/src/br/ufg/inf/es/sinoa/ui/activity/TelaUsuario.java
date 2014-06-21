package br.ufg.inf.es.sinoa.ui.activity;

import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaUsuario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_usuario);

	}
	
	public void iniciaTelaNotificacoes(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra("tipo", "prova");
        startActivity(intent);
	}
	
	public void iniciaTelaNotas(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra("tipo", "nota");
        startActivity(intent);
	}
	
	public void iniciaTelaAvisosBiblioteca(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra("tipo", "biblioteca");
        startActivity(intent);
	}
	
	public void iniciaConfiguracoes(View v){
		Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_usuario, menu);
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
*/

}
