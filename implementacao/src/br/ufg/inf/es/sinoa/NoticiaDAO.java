package br.ufg.inf.es.sinoa;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoticiaDAO {

	public static final String NOME_TABELA = "Noticias";
	public static final String COLUNA_ID = "id";
	public static final String COLUNA_DATA = "data";
	public static final String COLUNA_REMETENTE = "remetente";
	public static final String COLUNA_TEXTO = "texto";

	public static final String SCRIPT_CRIACAO_TABELA_NOTICIAS = "CREATE TABLE "
			+ NOME_TABELA + "(" + COLUNA_ID + " INTEGER PRIMARY KEY,"
			+ COLUNA_DATA + " TEXT," + COLUNA_REMETENTE + " TEXT,"
			+ COLUNA_TEXTO + " TEXT" + ")";

	public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS "
			+ NOME_TABELA;

	private SQLiteDatabase dataBase = null;

	private static NoticiaDAO instance;

	public static NoticiaDAO getInstance(Context context) {
		if (instance == null)
			instance = new NoticiaDAO(context);
		return instance;
	}

	private NoticiaDAO(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void salvar(Noticia noticia) {
		ContentValues values = gerarContentValuesNoticia(noticia);
		dataBase.insert(NOME_TABELA, null, values);
	}

	public List<Noticia> recuperarTodas() {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Noticia> noticias = construirNoticiaPorCursor(cursor);

		return noticias;
	}

	public void deletar(Noticia noticia) {

		String[] valoresParaSubstituir = { String.valueOf(noticia.getId()) };

		dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
	}

	public void editar(Noticia noticia) {
		ContentValues valores = gerarContentValuesNoticia(noticia);

		String[] valoresParaSubstituir = { String.valueOf(noticia.getId()) };

		dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?",
				valoresParaSubstituir);
	}

	public void fecharConexao() {
		if (dataBase != null && dataBase.isOpen())
			dataBase.close();
	}

	private List<Noticia> construirNoticiaPorCursor(Cursor cursor) {
		List<Noticia> noticias = new ArrayList<Noticia>();
		if (cursor == null) {
			return noticias;
		}

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexID = cursor.getColumnIndex(COLUNA_ID);
					int indexData = cursor.getColumnIndex(COLUNA_DATA);
					int indexRemetente = cursor
							.getColumnIndex(COLUNA_REMETENTE);
					int indexTexto = cursor.getColumnIndex(COLUNA_TEXTO);

					int id = cursor.getInt(indexID);
					String data = cursor.getString(indexData);
					String remetente = cursor.getString(indexRemetente);
					String texto = cursor.getString(indexTexto);

					Noticia noticia = new Noticia(id, data, remetente, texto);

					noticias.add(noticia);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return noticias;
	}

	private ContentValues gerarContentValuesNoticia(Noticia noticia) {
		ContentValues values = new ContentValues();
        values.put(COLUNA_DATA, noticia.getData());
        values.put(COLUNA_REMETENTE, noticia.getRemetente());
        values.put(COLUNA_TEXTO, noticia.getTexto());
 
        return values;
    }
}
