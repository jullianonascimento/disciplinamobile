package br.ufg.inf.es.sinoa.ui.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.dao.DisciplinaDAO;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.dao.UsuarioDAO;
import br.ufg.inf.es.sinoa.vo.Disciplina;
import br.ufg.inf.es.sinoa.vo.Notificacao;

public class TelaDisciplinas extends Activity implements OnItemClickListener {
	
	private ListView listView;
	private List<Disciplina> disciplinas;
	ArrayAdapter<Disciplina> adapter;
	int matriculaUsuarioLogado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_disciplinas);

		listView = (ListView) findViewById(R.id.listViewDisciplinas);
		listView.setOnItemClickListener(this);
		
		Intent intent = getIntent();
		matriculaUsuarioLogado = intent.getIntExtra(UsuarioDAO.COLUNA_MATRICULA, 0);
		criarListView(matriculaUsuarioLogado);
	}

	private void criarListView(int matricula) {
		DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance(this);
		disciplinas = disciplinaDAO.recuperarDisciplinasPorMatricula(matricula);
		
		adapter = new ArrayAdapter<Disciplina>(this, android.R.layout.simple_list_item_1, disciplinas);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
		Disciplina disciplinaClicada = adapter.getItem(posicao);
		int codigoDisciplina = disciplinaClicada.getCodigo();
		
		iniciaTelaNotificacoesPorDisciplina(codigoDisciplina);
	}

	public void iniciaTelaNotificacoesPorDisciplina(int codigoDisciplina){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.TODAS);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, codigoDisciplina);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, matriculaUsuarioLogado);
        startActivity(intent);
	}

}
