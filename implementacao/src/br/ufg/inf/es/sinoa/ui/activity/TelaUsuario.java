package br.ufg.inf.es.sinoa.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.dao.UsuarioDAO;
import br.ufg.inf.es.sinoa.vo.Notificacao;
import br.ufg.inf.es.sinoa.vo.Usuario;

public class TelaUsuario extends Activity {
	
	public static Usuario USUARIOLOGADO;
	private int matriculaUsuarioLogado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_usuario);

		Intent intent = getIntent();
		matriculaUsuarioLogado = intent.getIntExtra("matricula", 0);
		
		atualizaMensagem(matriculaUsuarioLogado);
	}
	
	private void atualizaMensagem(int matricula) {
		UsuarioDAO usuarioDAO = UsuarioDAO.getInstance(this);
		Usuario usuario = usuarioDAO.recuperarUsuarioPorMatricula(matricula);
		
		TextView mensagemBemVindo = (TextView) findViewById(R.id.textViewBemVindoUsuario);
		mensagemBemVindo.setText("Bem-vindo " + usuario.getNome());
	}

	public void iniciaTelaNotificacoes(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.AVISO_PROVA);
        startActivity(intent);
	}
	
	public void iniciaTelaNotas(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.NOTA_FREQUENCIA);
        startActivity(intent);
	}
	
	public void iniciaTelaAvisosBiblioteca(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.AVISO_BIBLIOTECA);
        startActivity(intent);
	}
	
	public void iniciaTelaDisciplinas(View v){
		Intent intent = new Intent(this, TelaDisciplinas.class);
		intent.putExtra("matricula", matriculaUsuarioLogado);
        startActivity(intent);
	}
	
	public void iniciaConfiguracoes(View v){
		Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_usuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
*/

}