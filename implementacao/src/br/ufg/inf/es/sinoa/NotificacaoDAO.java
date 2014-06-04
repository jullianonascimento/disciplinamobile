package br.ufg.inf.es.sinoa;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NotificacaoDAO {
	 
	 
    public static final String NOME_TABELA = "Notificacao";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_REMETENTE = "remetente";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_TEXTO = "texto";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_DISCIPLINA = "disciplina";
 
 
    public static final String SCRIPT_CRIACAO_TABELA_NOTIFICACAO = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_TIPO + " TEXT," + COLUNA_DATA + " TEXT,"
            + COLUNA_REMETENTE + " TEXT," + COLUNA_TITULO + " TEXT," + COLUNA_TEXTO + " TEXT," 
            + COLUNA_STATUS + " TEXT," +COLUNA_DISCIPLINA + " TEXT" +")";
 
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
 
    private SQLiteDatabase dataBase = null;
 
    private static NotificacaoDAO instance;
     
    public static NotificacaoDAO getInstance(Context context) {
        if(instance == null)
            instance = new NotificacaoDAO(context);
        return instance;
    }
 
    private NotificacaoDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }
 
    public void salvar(Notificacao notificacao) {
        ContentValues values = gerarContentValuesNotificacao(notificacao);
        dataBase.insert(NOME_TABELA, null, values);
    }
 
    public List<Notificacao> recuperarTodas() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Notificacao> notificacoes = construirNotificacaoPorCursor(cursor);
 
        return notificacoes;
    }
 
    public void deletar(Notificacao notificacao) {
 
        String[] valoresParaSubstituir = {
                String.valueOf(notificacao.getId())
        };
 
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
 
    public void editar(Notificacao notificacao) {
        ContentValues valores = gerarContentValuesNotificacao(notificacao);
 
        String[] valoresParaSubstituir = {
                String.valueOf(notificacao.getId())
        };
 
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
 
    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close(); 
    }
 
 
    private List<Notificacao> construirNotificacaoPorCursor(Cursor cursor) {
        List<Notificacao> notificacoes = new ArrayList<Notificacao>();
        if(cursor == null)
            return notificacoes;
         
        try {
 
            if (cursor.moveToFirst()) {
                do {
 
                	int indexID = cursor.getColumnIndex(COLUNA_ID);
					int indexTipo = cursor.getColumnIndex(COLUNA_TIPO);
					int indexData = cursor.getColumnIndex(COLUNA_DATA);
					int indexRemetente = cursor.getColumnIndex(COLUNA_REMETENTE);
					int indexTitulo = cursor.getColumnIndex(COLUNA_TITULO);
					int indexTexto = cursor.getColumnIndex(COLUNA_TEXTO);
					int indexStatus = cursor.getColumnIndex(COLUNA_STATUS);
					int indexDisciplina = cursor.getColumnIndex(COLUNA_DISCIPLINA);

					int id = cursor.getInt(indexID);
					String tipo = cursor.getString(indexTipo);
					String data = cursor.getString(indexData);
					String titulo = cursor.getString(indexTitulo);
					String remetente = cursor.getString(indexRemetente);
					String texto = cursor.getString(indexTexto);
					String status = cursor.getString(indexStatus);
					String disciplina = cursor.getString(indexDisciplina);
 
                    Notificacao notificacao = new Notificacao(id, tipo, data, remetente, titulo, texto, status, disciplina);
 
                    notificacoes.add(notificacao);
 
                } while (cursor.moveToNext());
            }
             
        } finally {
            cursor.close();
        }
        return notificacoes;
    }
 
    private ContentValues gerarContentValuesNotificacao(Notificacao notificacao) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TIPO, notificacao.getTipo());
        values.put(COLUNA_DATA, notificacao.getData());
        values.put(COLUNA_REMETENTE, notificacao.getRemetente());
        values.put(COLUNA_TITULO, notificacao.getTitulo());
        values.put(COLUNA_TEXTO, notificacao.getTexto());
        values.put(COLUNA_STATUS, notificacao.getStatus());
        values.put(COLUNA_DISCIPLINA, notificacao.getDisciplina());
 
        return values;
    }
}
