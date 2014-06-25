package br.ufg.inf.es.sinoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.ufg.inf.es.sinoa.persistence.DadosFicticios;
import br.ufg.inf.es.sinoa.ui.activity.LoginActivity;
import br.ufg.inf.es.sinoa.ui.activity.TelaVisitante;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//colocando dados ficticios no banco
		DadosFicticios.criaUsuariosFicticios(this);
		DadosFicticios.criaDisciplinasFicticias(this);
		DadosFicticios.criarRemetentesFicticios(this);
		DadosFicticios.criaNotificacoesFicticias(this);
	}

	public void iniciaTelaVisitante(View view) {
		Intent intent = new Intent(this, TelaVisitante.class);
		startActivity(intent);
	}

	public void iniciaTelaLogin(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.itemSair) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
