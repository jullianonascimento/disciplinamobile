package br.ufg.inf.es.sinoa.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.ufg.inf.es.sinoa.MainActivity;
import br.ufg.inf.es.sinoa.R;

public class TelaConfiguracoes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_configuracoes);
	}
	
	public void iniciaTelaOrigemRemetentes(View v){
		Intent intent = new Intent(this, TelaRemetentes.class);
        startActivity(intent);
	}
	
	public void logout(View v){
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
	}

}
