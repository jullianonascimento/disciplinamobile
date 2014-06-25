package br.ufg.inf.es.sinoa.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.dao.RemetenteDAO;
import br.ufg.inf.es.sinoa.ui.adapter.AdapterListaNoticias;
import br.ufg.inf.es.sinoa.vo.Notificacao;
import br.ufg.inf.es.sinoa.vo.Remetente;

public class TelaNotificacoes extends Activity implements OnItemClickListener {
	
	private ListView listView;
	private AdapterListaNoticias adapterListaNoticias;
	private List<Notificacao> notificacoes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_notificacoes);
				
		listView = (ListView) findViewById(R.id.lista);
		listView.setOnItemClickListener(this);
		registerForContextMenu(listView);
				
		criarListView();
	}
	
	private void criarListView() {
		ordenarListaPor(NotificacaoDAO.NOME_TABELA+"."+NotificacaoDAO.COLUNA_ID, NotificacaoDAO.ASCENDENTE);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tela_notificacoes, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.dataAscendente) {
            ordenarListaPor(NotificacaoDAO.COLUNA_DATA, NotificacaoDAO.ASCENDENTE);
            return true;
        } else if (id == R.id.dataDescendente) {
        	ordenarListaPor(NotificacaoDAO.COLUNA_DATA, NotificacaoDAO.DESCENDENTE);
            return true;
        } else if (id == R.id.remetenteAscendente) {
        	ordenarListaPor(RemetenteDAO.COLUNA_NOME, NotificacaoDAO.ASCENDENTE);
            return true;
        } else if (id == R.id.remetenteDescendente) {
        	ordenarListaPor(RemetenteDAO.COLUNA_NOME, NotificacaoDAO.DESCENDENTE);
            return true;
        } else if (id == R.id.pesquisar) {
        	alertDialogPesquisar();
        	return true;
        }

        return super.onOptionsItemSelected(item);
    }

	public void ordenarListaPor(String tipoOrdenacao, String organizacao) {
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);
		
		Intent intent = getIntent();
		String tipoNotificacao = intent.getStringExtra(NotificacaoDAO.COLUNA_TIPO);
		int idDisciplina = intent.getIntExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		int idUsuario = intent.getIntExtra(NotificacaoDAO.COLUNA_ID_USUARIO, Notificacao.AUSENTE);
		
		notificacoes = notificacaoDAO.recuperarNotificacaoGenerico(idDisciplina, idUsuario, tipoNotificacao, tipoOrdenacao, organizacao);
				
		if (notificacoes.isEmpty() || notificacoes.equals(null)){
			Toast toast = Toast.makeText(this, "Não há notificações a serem listadas.", Toast.LENGTH_LONG);
			toast.show();
			finish();
		} else {
			adapterListaNoticias = new AdapterListaNoticias(this, notificacoes);
			listView.setAdapter(adapterListaNoticias);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int posicao, long arg3) {
		Notificacao notificacaoClicada = adapterListaNoticias.getItem(posicao);
		
		alteraStatusNotificacao(notificacaoClicada, Notificacao.LIDA);
		
		String data = notificacaoClicada.getData();
		
		String titulo = notificacaoClicada.getTitulo();
		String texto = notificacaoClicada.getTexto();
		
		int idRemetente = notificacaoClicada.getIdRemetente();
		
		RemetenteDAO remetenteDAO = RemetenteDAO.getInstance(this);
		Remetente remetente = remetenteDAO.recuperarRemetentePorId(idRemetente);
		String nomeRemetente = remetente.getNome();
		
		iniciaExibeNotificacao(data, nomeRemetente, titulo, texto);
	}
	
	public void iniciaExibeNotificacao(String data, String remetente, String titulo, String texto) {
		Intent intent = new Intent(this, TelaExibeNotificacao.class);
		intent.putExtra("data", data);
		intent.putExtra("remetente", remetente);
		intent.putExtra("titulo", titulo);
		intent.putExtra("texto", texto);
		startActivity(intent);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	      super.onCreateContextMenu(menu, v, menuInfo);
	      if (v.getId() == R.id.lista) {
	          MenuInflater inflater = getMenuInflater();
	          inflater.inflate(R.menu.menu_contexto_lista, menu);
	      }
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		 
		 int posicaoSelecionada = menuinfo.position; //position in the adapter
	     Notificacao notificacaoClicada = adapterListaNoticias.getItem(posicaoSelecionada);
		 int id = item.getItemId();
	      
	     if (id == R.id.itemMarcarNaoLida){
	    	 alteraStatusNotificacao(notificacaoClicada, Notificacao.NAOLIDA);
	    	 return true;
	     } else if (id == R.id.itemMarcarLida){
	    	 alteraStatusNotificacao(notificacaoClicada, Notificacao.LIDA);
	    	 return true;
	     } else if (id == R.id.itemExcluir){
	    	 confirmaExclusao(notificacaoClicada);
	    	 return true;
	     }
	      
	     return super.onOptionsItemSelected(item);  
	}
	
	public void alteraStatusNotificacao(Notificacao notificacaoClicada, String status){		
		notificacaoClicada.setStatus(status);
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);
		notificacaoDAO.editar(notificacaoClicada);
		adapterListaNoticias.notifyDataSetChanged();
	}
	
	public void excluir(Notificacao notificacaoClicada){
		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(this);
		notificacaoDAO.deletar(notificacaoClicada); //apaga do banco
		adapterListaNoticias.removeItem(notificacaoClicada); //apaga da view
		adapterListaNoticias.notifyDataSetChanged();
		
		if (notificacoes.isEmpty() || notificacoes.equals(null)){
			Toast toast = Toast.makeText(this, "Não há notificações a serem listadas.", Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
	}
	
	public void confirmaExclusao(final Notificacao notificacao){
		
		new AlertDialog.Builder(this)
	    .setTitle("Excluir Notificação")
	    .setMessage("Deseja excluir a notificação? \n"
	    		+ "Esta ação não pode ser revertida.")
	    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton) {
	             excluir(notificacao);
	         }
	    })
	    .setNegativeButton("Não", null).show();
		
	}
	
	
	public void alertDialogPesquisar(){
		final EditText input = new EditText(this);

		new AlertDialog.Builder(this)
		    .setTitle("Pesquisar")
		    .setMessage("Digite um título para localizar uma notificação:")
		    .setView(input)
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton) {
		             pesquisar(input.getText().toString()); 
		         }
		    })
		    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton) {
		             // Do nothing.
		         }
		    }).show();
	}
	
	public void pesquisar(String texto){
		int textlength = texto.length();
		List<Notificacao> pesquisa = notificacoes;
		Notificacao[] arrayNotificacoes = notificacoes.toArray(new Notificacao[notificacoes.size()]);
        pesquisa.clear();
 
        for (int i = 0; i < arrayNotificacoes.length; i++ ) {
            if (textlength <= arrayNotificacoes[i].getTitulo().length()) {
                if (texto.equalsIgnoreCase((String)arrayNotificacoes[i].getTitulo().subSequence(0, textlength))) {
                	pesquisa.add(arrayNotificacoes[i]);
                }
            }
        }
        
        if (pesquisa.isEmpty() || pesquisa.equals(null)){
			Toast toast = Toast.makeText(this, "Não há notificações a serem listadas.", Toast.LENGTH_LONG);
			toast.show();
			finish();
		} else {
	        adapterListaNoticias = new AdapterListaNoticias(this, pesquisa);
			listView.setAdapter(adapterListaNoticias);
		}
	}

}
