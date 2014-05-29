package br.ufg.inf.es.sinoa;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TelaNoticiasPublicas extends Activity {
	
	String[] noticias = { "Noticia 1", "Noticia 2", "Noticia 3",
	"Noticia 4"};
	
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias_publicas);
		
		//código para criar a noticia, salvar e recuperar
		Noticia noticia0 = new Noticia(
				0,
				"23/05/2014",
				"CGA UFG",
				"Processo Seletivo destina-se ao preenchimento de vagas para mudança de curso, "
				+ "de habilitação, de turno e reingresso\n A Universidade Federal de "
				+ "Goiás (UFG) publicou o edital para preenchimento de vagas disponíveis "
				+ "nos cursos de graduação. As vagas são para mudança de curso, de habilitação, "
				+ "de turno e reingresso, destinadas ao segundo semestre do ano letivo de 2014. "
				+ "Os candidatos poderão se inscrever do dia 26/05 a 30/05.");

		Noticia noticia1 = new Noticia(1,"27/05/2014", "Reitoria", "A reitoria da UFG informa que...");
		
		NoticiaDAO noticiaDAO = NoticiaDAO.getInstance(this);
		
		noticiaDAO.salvar(noticia0);
		noticiaDAO.salvar(noticia1);
		
		List<Noticia> noticiasNoBanco = noticiaDAO.recuperarTodas();
		
		Noticia noticiaRecuperada = noticiasNoBanco.get(1);
		Log.i("noticia", noticiaRecuperada.getTexto());
		
		final Object arrayNoticias[] = noticiasNoBanco.toArray();
		final Noticia [] vetorNoticias = noticiasNoBanco.toArray(new Noticia[noticiasNoBanco.size()]); 
		
		noticiaDAO.fecharConexao();

		
		ListView listView = (ListView) findViewById(R.id.lista);
		
		ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(this,
				android.R.layout.simple_list_item_1, vetorNoticias);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				String textoNoticia = vetorNoticias[posicao].toString();
				iniciaExibeNoticia(textoNoticia);
				
				/*Toast.makeText(TelaNoticiasPublicas.this,
						"clique simples sobre " + noticias[posicao],
						Toast.LENGTH_LONG).show();*/
			}
		});

		listView.setLongClickable(true);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				/*Toast.makeText(TelaNoticiasPublicas.this,
						"clique longo!",
						Toast.LENGTH_LONG).show();*/
				return false;
			}

		});
	}
	
	public void iniciaExibeNoticia(String textoNoticia){
		Intent intent = new Intent(this, TelaExibeNoticia.class);
		intent.putExtra("mensagem", textoNoticia);
        startActivity(intent);
	}
}
