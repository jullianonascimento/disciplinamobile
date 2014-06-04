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
		Notificacao notificacao1 = new Notificacao(1, "comunicado", "28/05/2014", "Pró-Reitoria",
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
						"clique simples sobre a posição " + posicao,
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
				"Processo Seletivo destina-se ao preenchimento de vagas para mudança de curso, "
						+ "de habilitação, de turno e reingresso\n A Universidade Federal de "
						+ "Goiás (UFG) publicou o edital para preenchimento de vagas disponíveis "
						+ "nos cursos de graduação. As vagas são para mudança de curso, de habilitação, "
						+ "de turno e reingresso, destinadas ao segundo semestre do ano letivo de 2014. "
						+ "Os candidatos poderão se inscrever do dia 26/05 a 30/05.");

		Notificacao noticia1 = new Notificacao(1, "27/05/2014", "Reitoria",
				"A reitoria da UFG informa que...");

		Notificacao noticia2 = new Notificacao(
				2,
				"22/05/14",
				"Pró-Reitoria",
				"Inscrições abertas do Programa Institucional de Bolsas de Pós-Graduação\n "
						+ "As Pró-Reitorias de Pós-Graduação (PRPG), de Administração e Finanças (Proad) "
						+ "e de Desenvolvimento Institucional e Recursos Humanos (Prodirh) da UFG abriram "
						+ "inscrições para candidaturas a bolsas do Programa Institucional de Bolsas de "
						+ "Pós-Graduação níveis mestrado e doutorado. Servidores da UFG podem se inscrever "
						+ "até o dia 3 de junho, na secretaria da PRPG, no prédio da Reitoria, localizado "
						+ "no Câmpus Samambaia.");

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
				"Isenção do pagamento da inscrição do Processo Seletivo 2014-2.\n Tendo em vista as "
						+ "manifestações ocorridas na Regional Jataí, o prazo para entrega da documentação requerida "
						+ "para o PROGRAMA DE ISENÇÃO DO PAGAMENTO DE INSCRIÇÃO PARA O PROCESSO SELETIVO 2014-2 será "
						+ "prorrogado por três dias após a normalidade das atividades nesta regional. As datas "
						+ "de entrega da documentação nos demais campus permanecem inalteradas.");

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
