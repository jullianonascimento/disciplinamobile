package br.ufg.inf.es.sinoa.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.dao.RemetenteDAO;
import br.ufg.inf.es.sinoa.ui.adapter.AdapterRemetentes;
import br.ufg.inf.es.sinoa.vo.Remetente;

public class TelaRemetentes extends Activity implements OnItemClickListener {

	private ListView listView;
	private AdapterRemetentes adapterRemetentes;
	private List<Remetente> remetentes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_remetentes);

		listView = (ListView) findViewById(R.id.listViewRemetentes);
		listView.setOnItemClickListener(this);
		criarListView();
	}

	private void criarListView() {
		RemetenteDAO remetenteDAO = RemetenteDAO.getInstance(this);
		remetentes = remetenteDAO.recuperarTodos();
		adapterRemetentes = new AdapterRemetentes(this, remetentes);
		listView.setAdapter(adapterRemetentes);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int posicao, long arg3) {
		Remetente remetenteClicado = adapterRemetentes.getItem(posicao);
		
		if (remetenteClicado.getAtivo().equals(Remetente.ATIVADO)){
			confirmaDesativacaoRemetente(remetenteClicado);
		} else {
			alteraStatusRemetente(remetenteClicado);
		}
	}

	public void alteraStatusRemetente(Remetente remetente) {

		if (remetente.getAtivo().equals(Remetente.ATIVADO)) {
			remetente.setAtivo(Remetente.DESATIVADO);
		} else {
			remetente.setAtivo(Remetente.ATIVADO);
		}
		
		RemetenteDAO remetenteDAO = RemetenteDAO.getInstance(this);
		remetenteDAO.editar(remetente);
		adapterRemetentes.notifyDataSetChanged();
	}
	
	
	public void confirmaDesativacaoRemetente(Remetente remetente){
		
		final Remetente rem = remetente;
		
		new AlertDialog.Builder(this)
	    .setTitle("Desativar Remetente")
	    .setMessage("Deseja desativar " +rem.getNome()+ "?")
	    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton) {
	             alteraStatusRemetente(rem);
	         }
	    })
	    .setNegativeButton("Não",  null).show();
		
	}

}
