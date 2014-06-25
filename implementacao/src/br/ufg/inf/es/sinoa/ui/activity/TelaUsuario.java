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
	
	private int matriculaUsuarioLogado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_usuario);

		Intent intent = getIntent();
		matriculaUsuarioLogado = intent.getIntExtra(UsuarioDAO.COLUNA_MATRICULA, 0);
		atualizaMensagem();
	}
	
	private void atualizaMensagem() {
		UsuarioDAO usuarioDAO = UsuarioDAO.getInstance(this);
		Usuario usuario = usuarioDAO.recuperarUsuarioPorMatricula(matriculaUsuarioLogado);
		TextView mensagemBemVindo = (TextView) findViewById(R.id.textViewBemVindoUsuario);
		String nome = usuario.getNome();
		mensagemBemVindo.setText("Bem-vindo " +nome.substring(0, nome.indexOf(" ")));
	}

	public void iniciaTelaNotificacoes(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.AVISO);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, matriculaUsuarioLogado);
        startActivity(intent);
	}
	
	public void iniciaTelaNotas(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.NOTA_FREQUENCIA);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, matriculaUsuarioLogado);
        startActivity(intent);
	}
	
	public void iniciaTelaAvisosBiblioteca(View v){
		Intent intent = new Intent(this, TelaNotificacoes.class);
		intent.putExtra(NotificacaoDAO.COLUNA_TIPO, Notificacao.AVISO_BIBLIOTECA);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_DISCIPLINA, Notificacao.AUSENTE);
		intent.putExtra(NotificacaoDAO.COLUNA_ID_USUARIO, matriculaUsuarioLogado);
        startActivity(intent);
	}
	
	public void iniciaTelaDisciplinas(View v){
		Intent intent = new Intent(this, TelaDisciplinas.class);
		intent.putExtra(UsuarioDAO.COLUNA_MATRICULA, matriculaUsuarioLogado);
        startActivity(intent);
	}
	
	public void iniciaConfiguracoes(View v){
		Intent intent = new Intent(this, TelaConfiguracoes.class);
        startActivity(intent);
	}
}
