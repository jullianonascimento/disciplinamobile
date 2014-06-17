package br.ufg.inf.es.sinoa;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaNotificacoes extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_notificacoes);
		
		ordenarListaPor(NotificacaoDAO.COLUNA_ID);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tela_notificacoes, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ordenarPorData) {
            ordenarListaPor(NotificacaoDAO.COLUNA_DATA);
            return true;
        } else if (id == R.id.ordenarPorRemetente) {
        	ordenarListaPor(NotificacaoDAO.COLUNA_REMETENTE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	public void ordenarListaPor(String tipoOrdenacao) {
		Intent intent = getIntent();
		String tipoNotificacao = intent.getStringExtra("tipo");
				
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);
		
		List<Notificacao> notificacoesNaBase = notificacaoDAO.recuperarNotificacao(tipoNotificacao, tipoOrdenacao);

		final Notificacao[] arrayNotificacoes = notificacoesNaBase.toArray(new Notificacao[notificacoesNaBase.size()]);
	
		ArrayAdapter<Notificacao> adapter = new ArrayAdapter<Notificacao>(this,
				android.R.layout.simple_list_item_checked, arrayNotificacoes);
				
		ListView listView = (ListView) findViewById(R.id.lista);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int posicao, long arg3) {

				Notificacao notificacaoClicada = arrayNotificacoes[posicao];
				String data = notificacaoClicada.getData();
				String remetente = notificacaoClicada.getRemetente();
				String titulo = notificacaoClicada.getTitulo();
				String texto = notificacaoClicada.getTexto();

				iniciaExibeNotificacao(data, remetente, titulo, texto);
			}
		});
	}
	
	public void iniciaExibeNotificacao(String data, String remetente, String titulo, String texto) {
		Intent intent = new Intent(this, TelaExibeNotificacao.class);
		intent.putExtra("data", data);
		intent.putExtra("remetente", remetente);
		intent.putExtra("titulo", titulo);
		intent.putExtra("texto", texto);
		startActivity(intent);
	}
}
