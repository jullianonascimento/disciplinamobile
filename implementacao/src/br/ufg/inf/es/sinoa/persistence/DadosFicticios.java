package br.ufg.inf.es.sinoa.persistence;

import android.content.Context;
import br.ufg.inf.es.sinoa.dao.DisciplinaDAO;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.dao.RemetenteDAO;
import br.ufg.inf.es.sinoa.dao.UsuarioDAO;
import br.ufg.inf.es.sinoa.vo.Disciplina;
import br.ufg.inf.es.sinoa.vo.Notificacao;
import br.ufg.inf.es.sinoa.vo.Remetente;
import br.ufg.inf.es.sinoa.vo.Usuario;

public class DadosFicticios {

	private static Usuario julliano;
	private static Usuario rafael;

	private static Disciplina concorrencia;
	private static Disciplina mobile;
	private static Disciplina web;
	private static Disciplina persistencia;
	private static Disciplina integracao;

	private static Remetente biblioteca;
	private static Remetente coordenador;
	private static Remetente direcao;
	private static Remetente proReitoria;
	private static Remetente reitoria;
	private static Remetente professorAlfredo;
	private static Remetente professoraMarcia;

	private static Notificacao preenchimento;
	private static Notificacao vestibular;
	private static Notificacao comunicadoLocalProva;
	private static Notificacao notaConcorrencia;
	private static Notificacao avisoBiblioteca;
	private static Notificacao avisoProvaIntegracao;

	public static void criaUsuariosFicticios(Context context) {

		julliano = new Usuario(110245, "Julliano Rosa Nascimento",
				"jullianonascimento@inf.ufg.br", "admin",
				"Engenharia de Software");
		rafael = new Usuario(127141, "Rafael Dias Correa",
				"faelcorrea49@gmail.com", "123456", "Matemática");

		UsuarioDAO usuarioDAO = UsuarioDAO.getInstance(context);

		usuarioDAO.deletarTodosUsuarios();

		usuarioDAO.salvar(julliano);
		usuarioDAO.salvar(rafael);
	}

	public static void criaDisciplinasFicticias(Context context) {

		concorrencia = new Disciplina(5275,
				"Desenvolvimento de software concorrente",
				julliano.getMatricula());
		mobile = new Disciplina(5277,
				"Desenvolvimento de software para dispositivos móveis",
				julliano.getMatricula());
		web = new Disciplina(5278, "Desenvolvimento de software para a web",
				julliano.getMatricula());
		persistencia = new Disciplina(5276,
				"Desenvolvimento de software para persistência",
				julliano.getMatricula());
		integracao = new Disciplina(5280, "Integração de Aplicações",
				julliano.getMatricula());

		DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstance(context);

		disciplinaDAO.deletarTodasDisciplinas();

		disciplinaDAO.salvar(concorrencia);
		disciplinaDAO.salvar(mobile);
		disciplinaDAO.salvar(web);
		disciplinaDAO.salvar(persistencia);
		disciplinaDAO.salvar(integracao);
	}

	public static void criarRemetentesFicticios(Context context) {

		biblioteca = new Remetente(10, Remetente.BIBLIOTECA, Remetente.ATIVADO,
				julliano.getMatricula());
		coordenador = new Remetente(11, Remetente.COORDENADOR_CURSO,
				Remetente.ATIVADO, julliano.getMatricula());
		direcao = new Remetente(12, Remetente.DIRECAO_CURSO, Remetente.ATIVADO,
				julliano.getMatricula());
		proReitoria = new Remetente(13, Remetente.PRO_REITORIA,
				Remetente.ATIVADO, julliano.getMatricula());
		reitoria = new Remetente(14, Remetente.REITORIA, Remetente.DESATIVADO,
				julliano.getMatricula());

		// Docentes
		professorAlfredo = new Remetente(15, "Alfredo", Remetente.ATIVADO,
				110245);
		professoraMarcia = new Remetente(16, "Márcia", Remetente.ATIVADO, 110245);

		RemetenteDAO remetenteDAO = RemetenteDAO.getInstance(context);

		remetenteDAO.deletarTodosRemetentes();

		remetenteDAO.salvar(biblioteca);
		remetenteDAO.salvar(coordenador);
		remetenteDAO.salvar(direcao);
		remetenteDAO.salvar(proReitoria);
		remetenteDAO.salvar(reitoria);
		remetenteDAO.salvar(professorAlfredo);
		remetenteDAO.salvar(professoraMarcia);
	}

	public static void criaNotificacoesFicticias(Context context) {

		preenchimento = new Notificacao(
				0,
				Notificacao.PÚBLICA,
				"27/05/2014",
				reitoria.getId(),
				"Processo Seletivo Para Preenchimento de Vagas UFG 2014-1",
				"O Reitor da Universidade Federal de Goiás (UFG) informa que se encontra aberto"
						+ " o Processo Seletivo Para Preenchimento de Vagas Disponíveis 2014-1. "
						+ "Veja o edital em http://www.vestibular.ufg.br/2014/preenchimento/index.php/editais/18-edital-n-083-2013");
		vestibular = new Notificacao(
				1,
				Notificacao.PÚBLICA,
				"28/05/2014",
				proReitoria.getId(),
				"Provas da segunda etapa do Processo Seletivo 2014-2",
				"O processo seletivo 2014-2 terá suas provas da segunda etapa realizadas nos dias 8 e 9 de junho "
						+ "de 2014.");
		comunicadoLocalProva = new Notificacao(
				2,
				Notificacao.COMUNICADO,
				"29/05/2014",
				proReitoria.getId(),
				"Processo Seletivo 2014/1 Comunicado Local de Prova do VHCE",
				"O Centro de Seleção informa que o local de prova do VHCE será divulgado a partir das 18:00h do dia 30 "
						+ "de junho de 2014.");
		notaConcorrencia = new Notificacao(3, Notificacao.NOTA_FREQUENCIA,
				"30/05/2014", professoraMarcia.getId(),
				"Nota Final de Desenvolvimento de Software para Concorrência",
				"Nota: 8,0\nFrequência: 58\nParabéns você foi aprovado.",
				concorrencia.getCodigo(), julliano.getMatricula());
		avisoBiblioteca = new Notificacao(
				4,
				Notificacao.AVISO_BIBLIOTECA,
				"31/05/2014",
				biblioteca.getId(),
				"Vencimento do empréstimo do Livro Engenharia de Software - 9a Edição",
				"Informamos que o empréstimo do livro Engenharia de Software - 9a Edição "
						+ "2011 - Ian Sommerville tem sua data limite na próxima terça-feira dia 10/06/2014.");
		avisoBiblioteca.setIdUsuario(julliano.getMatricula());
		avisoProvaIntegracao = new Notificacao(
				5,
				Notificacao.AVISO_PROVA,
				"01/06/2014",
				professorAlfredo.getId(),
				"Prova 2 de Integração de Aplicações chegando",
				"Este aviso é para lembrá-lo que a primeira prova de Integração de Aplicações"
						+ " acontecerá na próxima segunda-feira dia 09/06/2014.",
				integracao.getCodigo(), julliano.getMatricula());

		NotificacaoDAO notificacaoDAO = NotificacaoDAO.getInstance(context);

		notificacaoDAO.deletarTodasNotificacoes();

		notificacaoDAO.salvar(preenchimento);
		notificacaoDAO.salvar(vestibular);
		notificacaoDAO.salvar(comunicadoLocalProva);
		notificacaoDAO.salvar(notaConcorrencia);
		notificacaoDAO.salvar(avisoBiblioteca);
		notificacaoDAO.salvar(avisoProvaIntegracao);

		// notificacaoDAO.fecharConexao();
	}

}
