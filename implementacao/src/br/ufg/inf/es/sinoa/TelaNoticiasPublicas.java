package br.ufg.inf.es.sinoa;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TelaNoticiasPublicas extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias_publicas);

		NoticiaDAO noticiaDAO = NoticiaDAO.getInstance(this);

		criaNoticiasFicticias(noticiaDAO);

		final List<Noticia> noticiasNoBanco = noticiaDAO.recuperarTodas();

		final Noticia[] vetorNoticias = noticiasNoBanco
				.toArray(new Noticia[noticiasNoBanco.size()]);

		noticiaDAO.fecharConexao();

		ListView listView = (ListView) findViewById(R.id.lista);

		ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(this,
				android.R.layout.simple_list_item_1, vetorNoticias);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int posicao, long arg3) {

				// Noticia noticiaClicada = noticiasNoBanco.get(posicao);
				Noticia noticiaClicada = vetorNoticias[posicao];
				String data = noticiaClicada.getData();
				String remetente = noticiaClicada.getRemetente();
				String texto = noticiaClicada.getTexto();

				iniciaExibeNoticia(data, remetente, texto);

				Toast.makeText(TelaNoticiasPublicas.this,
						"clique simples sobre a posi��o " + posicao,
						Toast.LENGTH_LONG).show();

			}
		});

		listView.setLongClickable(true);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				/*
				 * Toast.makeText(TelaNoticiasPublicas.this, "clique longo!",
				 * Toast.LENGTH_LONG).show();
				 */
				return false;
			}

		});
	}

	public void iniciaExibeNoticia(String data, String remetente, String texto) {
		Intent intent = new Intent(this, TelaExibeNoticia.class);
		intent.putExtra("data", data);
		intent.putExtra("remetente", remetente);
		intent.putExtra("texto", texto);
		startActivity(intent);
	}

	public void criaNoticiasFicticias(NoticiaDAO noticiaDAO) {

		Noticia noticia0 = new Noticia(
				0,
				"23/05/2014",
				"CGA UFG",
				"Processo Seletivo destina-se ao preenchimento de vagas para mudan�a de curso, "
						+ "de habilita��o, de turno e reingresso\n A Universidade Federal de "
						+ "Goi�s (UFG) publicou o edital para preenchimento de vagas dispon�veis "
						+ "nos cursos de gradua��o. As vagas s�o para mudan�a de curso, de habilita��o, "
						+ "de turno e reingresso, destinadas ao segundo semestre do ano letivo de 2014. "
						+ "Os candidatos poder�o se inscrever do dia 26/05 a 30/05.");

		Noticia noticia1 = new Noticia(1, "27/05/2014", "Reitoria",
				"A reitoria da UFG informa que...");

		Noticia noticia2 = new Noticia(
				2,
				"22/05/14",
				"Pr�-Reitoria",
				"Inscri��es abertas do Programa Institucional de Bolsas de P�s-Gradua��o\n "
						+ "As Pr�-Reitorias de P�s-Gradua��o (PRPG), de Administra��o e Finan�as (Proad) "
						+ "e de Desenvolvimento Institucional e Recursos Humanos (Prodirh) da UFG abriram "
						+ "inscri��es para candidaturas a bolsas do Programa Institucional de Bolsas de "
						+ "P�s-Gradua��o n�veis mestrado e doutorado. Servidores da UFG podem se inscrever "
						+ "at� o dia 3 de junho, na secretaria da PRPG, no pr�dio da Reitoria, localizado "
						+ "no C�mpus Samambaia.");

		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(this);
		SQLiteDatabase db = persistenceHelper.getWritableDatabase();
		PersistenceHelper.reiniciaBanco(db);

		noticiaDAO.salvar(noticia0);
		noticiaDAO.salvar(noticia1);
		noticiaDAO.salvar(noticia2);
	}
}
