package br.ufg.inf.es.sinoa;

import java.util.Date;

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

public class TelaNoticiasPublicas extends Activity {
	
	String[] noticias = { "Noticia 1", "Noticia 2", "Noticia 3",
	"Noticia 4"};

	private Date data = new Date();
	private String remetente;
	private String texto;
	
	Noticia objetoNoticia = new Noticia(data, remetente, texto);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias_publicas);

		
		ListView listView = (ListView) findViewById(R.id.lista);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, noticias);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				
				iniciaExibeNoticia(noticias[posicao]);
				
				Toast.makeText(TelaNoticiasPublicas.this,
						"clique simples sobre " + noticias[posicao],
						Toast.LENGTH_LONG).show();
			}
		});

		listView.setLongClickable(true);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Toast.makeText(TelaNoticiasPublicas.this,
						"clique longo!",
						Toast.LENGTH_LONG).show();
				return false;
			}

		});
	}
	
	public void iniciaExibeNoticia(String noticia){
		Intent intent = new Intent(this, TelaExibeNoticia.class);
		intent.putExtra("mensagem", noticia);
        startActivity(intent);
	}
}
