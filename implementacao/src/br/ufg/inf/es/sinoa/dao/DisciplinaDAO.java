package br.ufg.inf.es.sinoa.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.es.sinoa.persistence.PersistenceHelper;
import br.ufg.inf.es.sinoa.vo.Disciplina;

public class DisciplinaDAO {

	public static final String NOME_TABELA = "Disciplina";
	public static final String COLUNA_CODIGO = "codigo";
	public static final String COLUNA_NOME = "nome";
	public static final String COLUNA_ID_USUARIO = "id_usuario";

	public static final String SCRIPT_CRIACAO_TABELA_DISCIPLINA = "CREATE TABLE "
			+ NOME_TABELA + "(" + COLUNA_CODIGO + " INTEGER PRIMARY KEY,"
			+ COLUNA_NOME + " TEXT," + COLUNA_ID_USUARIO + " INTEGER, "
			+ "FOREIGN KEY(" + COLUNA_ID_USUARIO + ") REFERENCES " 
			+ UsuarioDAO.NOME_TABELA + "(" + UsuarioDAO.COLUNA_MATRICULA + ") )";

	public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS "
			+ NOME_TABELA;

	private SQLiteDatabase dataBase = null;

	private static DisciplinaDAO instance;

	public static DisciplinaDAO getInstance(Context context) {
		if (instance == null)
			instance = new DisciplinaDAO(context);
		return instance;
	}

	private DisciplinaDAO(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void salvar(Disciplina disciplina) {
		ContentValues values = gerarContentValuesDisciplina(disciplina);
		dataBase.insert(NOME_TABELA, null, values);
	}

	public List<Disciplina> recuperarTodas() {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Disciplina> disciplinas = construirDisciplinaPorCursor(cursor);

		return disciplinas;
	}
	
	public List<Disciplina> recuperarDisciplinasPorMatricula(int matricula) {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_ID_USUARIO + " = " + matricula;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Disciplina> disciplinas = construirDisciplinaPorCursor(cursor);

		return disciplinas;
	}

	public void deletarTodasDisciplinas() {
		String query = "DELETE FROM " + NOME_TABELA;
		dataBase.execSQL(query);
	}

	public void deletar(Disciplina disciplina) {

		String[] valoresParaSubstituir = { String.valueOf(disciplina
				.getCodigo()) };

		dataBase.delete(NOME_TABELA, COLUNA_CODIGO + " =  ?",
				valoresParaSubstituir);
	}

	public void editar(Disciplina disciplina) {
		ContentValues valores = gerarContentValuesDisciplina(disciplina);

		String[] valoresParaSubstituir = { String.valueOf(disciplina
				.getCodigo()) };

		dataBase.update(NOME_TABELA, valores, COLUNA_CODIGO + " = ?",
				valoresParaSubstituir);
	}

	public void fecharConexao() {
		if (dataBase != null && dataBase.isOpen())
			dataBase.close();
	}

	private List<Disciplina> construirDisciplinaPorCursor(Cursor cursor) {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		if (cursor == null)
			return disciplinas;

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexCodigo = cursor.getColumnIndex(COLUNA_CODIGO);
					int indexNome = cursor.getColumnIndex(COLUNA_NOME);
					int indexIdUsuario = cursor
							.getColumnIndex(COLUNA_ID_USUARIO);

					int codigo = cursor.getInt(indexCodigo);
					String nome = cursor.getString(indexNome);
					int idUsuario = cursor.getInt(indexIdUsuario);

					Disciplina disciplina = new Disciplina(codigo, nome,
							idUsuario);

					disciplinas.add(disciplina);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return disciplinas;
	}

	private ContentValues gerarContentValuesDisciplina(Disciplina disciplina) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_CODIGO, disciplina.getCodigo());
		values.put(COLUNA_NOME, disciplina.getNome());
		values.put(COLUNA_ID_USUARIO, disciplina.getIdUsuario());

		return values;
	}
}
