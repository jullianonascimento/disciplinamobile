package br.ufg.inf.es.sinoa;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TelaNotificacoes extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_notificacoes);
				
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);
				
		List<Notificacao> notificacoesNaBase = notificacaoDAO.recuperarTodas();

		final Notificacao[] arrayNotificacoes = notificacoesNaBase.toArray(new Notificacao[notificacoesNaBase.size()]);

		/*Não devo fechar a conexão enquanto o aplicativo estiver aberto
		notificacaoDAO.fecharConexao();
		*/
		
		ListView listView = (ListView) findViewById(R.id.lista);

		ArrayAdapter<Notificacao> adapter = new ArrayAdapter<Notificacao>(this,
				android.R.layout.simple_list_item_1, arrayNotificacoes);
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

				iniciaExibeNoticia(data, remetente, titulo, texto);
			}
		});

		listView.setLongClickable(true);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				
				Notificacao notificacaoClicada = arrayNotificacoes[posicao];
				NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(TelaNotificacoes.this);
				notificacaoDAO.deletar(notificacaoClicada);
				
				  Toast.makeText(TelaNotificacoes.this, "clique longo sobre " + notificacaoClicada.getTitulo(),
				  Toast.LENGTH_LONG).show();
				  
				 
				return false;
			}

		});
		
	}

	public void iniciaExibeNoticia(String data, String remetente, String titulo, String texto) {
		Intent intent = new Intent(this, TelaExibeNotificacao.class);
		intent.putExtra("data", data);
		intent.putExtra("remetente", remetente);
		intent.putExtra("titulo", titulo);
		intent.putExtra("texto", texto);
		startActivity(intent);
	}
}
