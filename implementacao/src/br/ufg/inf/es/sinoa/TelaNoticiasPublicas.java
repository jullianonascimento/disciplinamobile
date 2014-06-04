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
import android.widget.ListView;

public class TelaNoticiasPublicas extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias_publicas);
		

		Notificacao notificacao = new Notificacao(0, "publica", "27", "CGA", "Preenchimento", "O preenchimento", "false", "");
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);

		notificacaoDAO.salvar(notificacao);

		List<Notificacao> notificacoesNaBase = notificacaoDAO.recuperarTodas();

		Notificacao notificacaoRecuperada = notificacoesNaBase.get(0);
		Log.i("noticia", notificacaoRecuperada.toString());
		
		
		
		/*reinicia o banco de dados
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(this);
		SQLiteDatabase db = persistenceHelper.getWritableDatabase();
		PersistenceHelper.reiniciaBanco(db);
		

		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);

		Notificacao notificacao0 = new Notificacao(12, "publica", "27/05/2014", "Reitoria",
				"Informativo da Reitoria", "A reitoria da UFG informa que...", "false", "");
		Notificacao notificacao1 = new Notificacao(1, "comunicado", "28/05/2014", "Pr�-Reitoria",
				"Comunicado Local de Prova", "Os locais de prova do processo seletivo x...", "false", null);
		Notificacao notificacao2 = new Notificacao();
		
		Log.i("noticias-publicas", "vai criar notificacao");
		//notificacaoDAO.salvar(notificacao0);
		//notificacaoDAO.salvar(notificacao1);
		notificacaoDAO.salvar(notificacao2);

		final List<Notificacao> notificacoesNoBanco = notificacaoDAO.recuperarTodas();
		
		*/

		final Notificacao[] arrayNotificacoes = notificacoesNaBase
				.toArray(new Notificacao[notificacoesNaBase.size()]);

		/*Log.i("noticias-publicas", "vai fechar conexao");
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
				String texto = notificacaoClicada.getTexto();

				iniciaExibeNoticia(data, remetente, texto);

				/*Toast.makeText(TelaNoticiasPublicas.this,
						"clique simples sobre a posi��o " + posicao,
						Toast.LENGTH_LONG).show();*/
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
/*
	public void criaNoticiasFicticias(NotificacaoDAO noticiaDAO) {

		Notificacao noticia0 = new Notificacao(
				0,
				"23/05/2014",
				"CGA UFG",
				"Processo Seletivo destina-se ao preenchimento de vagas para mudan�a de curso, "
						+ "de habilita��o, de turno e reingresso\n A Universidade Federal de "
						+ "Goi�s (UFG) publicou o edital para preenchimento de vagas dispon�veis "
						+ "nos cursos de gradua��o. As vagas s�o para mudan�a de curso, de habilita��o, "
						+ "de turno e reingresso, destinadas ao segundo semestre do ano letivo de 2014. "
						+ "Os candidatos poder�o se inscrever do dia 26/05 a 30/05.");

		Notificacao noticia1 = new Notificacao(1, "27/05/2014", "Reitoria",
				"A reitoria da UFG informa que...");

		Notificacao noticia2 = new Notificacao(
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

	public void criaComunicadosFicticios(NotificacaoDAO noticiaDAO) {

		Notificacao noticia0 = new Notificacao(0, "23/05/2014", "CS UFG",
				"Comunicado local de prova...");

		Notificacao noticia1 = new Notificacao(1, "27/05/2014", "Reitoria",
				"A reitoria da UFG comunica que...");

		Notificacao noticia2 = new Notificacao(
				2,
				"22/05/14",
				"CS UFG",
				"Isen��o do pagamento da inscri��o do Processo Seletivo 2014-2.\n Tendo em vista as "
						+ "manifesta��es ocorridas na Regional Jata�, o prazo para entrega da documenta��o requerida "
						+ "para o PROGRAMA DE ISEN��O DO PAGAMENTO DE INSCRI��O PARA O PROCESSO SELETIVO 2014-2 ser� "
						+ "prorrogado por tr�s dias ap�s a normalidade das atividades nesta regional. As datas "
						+ "de entrega da documenta��o nos demais campus permanecem inalteradas.");

		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(this);
		SQLiteDatabase db = persistenceHelper.getWritableDatabase();
		PersistenceHelper.reiniciaBanco(db);

		noticiaDAO.salvar(noticia0);
		noticiaDAO.salvar(noticia1);
		noticiaDAO.salvar(noticia2);
	}
	*/
}
