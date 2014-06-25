package br.ufg.inf.es.sinoa.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.ufg.inf.es.sinoa.persistence.PersistenceHelper;
import br.ufg.inf.es.sinoa.vo.Notificacao;
import br.ufg.inf.es.sinoa.vo.Remetente;

public class NotificacaoDAO {
	 
    public static final String NOME_TABELA = "Notificacao";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_ID_REMETENTE = "id_remetente";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_TEXTO = "texto";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID_DISCIPLINA = "id_disciplina";
    public static final String COLUNA_ID_USUARIO = "id_usuario";
    
    public static final String ASCENDENTE = "ASC"; 
    public static final String DESCENDENTE = "DESC"; 
 
    public static final String SCRIPT_CRIACAO_TABELA_NOTIFICACAO = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_TIPO + " TEXT," + COLUNA_DATA + " TEXT,"
            + COLUNA_ID_REMETENTE + " INTEGER," + COLUNA_TITULO + " TEXT," + COLUNA_TEXTO + " TEXT," 
            + COLUNA_STATUS + " TEXT," + COLUNA_ID_DISCIPLINA + " INTEGER," + COLUNA_ID_USUARIO + " INTEGER," 
            + "FOREIGN KEY(" + COLUNA_ID_REMETENTE + ") REFERENCES " 
			+ RemetenteDAO.NOME_TABELA + "(" + RemetenteDAO.COLUNA_ID + "),"
            + "FOREIGN KEY(" + COLUNA_ID_DISCIPLINA + ") REFERENCES " 
			+ DisciplinaDAO.NOME_TABELA + "(" + DisciplinaDAO.COLUNA_CODIGO + "),"
			+ "FOREIGN KEY(" + COLUNA_ID_USUARIO + ") REFERENCES " 
			+ UsuarioDAO.NOME_TABELA + "(" + UsuarioDAO.COLUNA_MATRICULA + ") )";
 
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
    
    public List<Notificacao> recuperarNotificacaoGenerico(int idDisciplina, int idUsuario, String tipoNotificacao, 
    		String tipoOrdenacao, String organizacao) {
        
    	String querySelect = "SELECT Notificacao.id,tipo,data,id_remetente,titulo,texto,status,id_disciplina,Notificacao.id_usuario,nome "
    			+ "FROM " + NOME_TABELA + "," +RemetenteDAO.NOME_TABELA+ " "
    			+ "WHERE " +NOME_TABELA+"."+COLUNA_ID_REMETENTE+ " = " +RemetenteDAO.NOME_TABELA+ "." +RemetenteDAO.COLUNA_ID+ " ";
        
    	 if (tipoNotificacao.equals(Notificacao.TODAS)){
        	 tipoNotificacao = "nota','aviso";
         }
         
         String queryTipo = " AND " + COLUNA_TIPO + " IN ('" + tipoNotificacao + "')  ";
         
         String queryIdDisciplina = " AND " + COLUNA_ID_DISCIPLINA + " IN (" + idDisciplina + ") ";
         
         if (idDisciplina == Notificacao.AUSENTE){
        	 queryIdDisciplina = "";
         }
         
         String queryRemetenteAtivo = "SELECT "+RemetenteDAO.COLUNA_ID+" FROM "+RemetenteDAO.NOME_TABELA+" WHERE "+RemetenteDAO.COLUNA_ATIVO+" = '"+Remetente.ATIVADO+"'";
         String queryIdRemetente = " AND " + COLUNA_ID_REMETENTE + " IN (" + queryRemetenteAtivo + ")";
         
         if (idUsuario == Notificacao.AUSENTE){
        	 queryIdRemetente = "";
         }
         
         String orderBy = " ORDER BY " + tipoOrdenacao + " " + organizacao;
         
        String finalQuery = querySelect + queryTipo + queryIdDisciplina + queryIdRemetente + orderBy;
                 
        Log.i("query", finalQuery);
        Cursor cursor = dataBase.rawQuery(finalQuery, null);
        List<Notificacao> notificacoes = construirNotificacaoPorCursor(cursor);
 
        return notificacoes;
    }
    
    public void deletarTodasNotificacoes(){
    	String query = "DELETE FROM " + NOME_TABELA;
    	dataBase.execSQL(query);
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
					int indexIdRemetente = cursor.getColumnIndex(COLUNA_ID_REMETENTE);
					int indexTitulo = cursor.getColumnIndex(COLUNA_TITULO);
					int indexTexto = cursor.getColumnIndex(COLUNA_TEXTO);
					int indexStatus = cursor.getColumnIndex(COLUNA_STATUS);
					int indexIdDisciplina = cursor.getColumnIndex(COLUNA_ID_DISCIPLINA);
					int indexIdUsuario = cursor.getColumnIndex(COLUNA_ID_USUARIO);

					int id = cursor.getInt(indexID);
					String tipo = cursor.getString(indexTipo);
					String data = new StringBuilder(cursor.getString(indexData)).reverse().toString();
					String titulo = cursor.getString(indexTitulo);
					int remetente = cursor.getInt(indexIdRemetente);
					String texto = cursor.getString(indexTexto);
					String status = cursor.getString(indexStatus);
					int idDisciplina = cursor.getInt(indexIdDisciplina);
					int idUsuario = cursor.getInt(indexIdUsuario);
 
                    Notificacao notificacao = new Notificacao(id, tipo, data, remetente, titulo, 
                    		texto, status, idDisciplina, idUsuario);
 
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
        values.put(COLUNA_ID, notificacao.getId());
        values.put(COLUNA_TIPO, notificacao.getTipo());
        values.put(COLUNA_DATA, new StringBuilder(notificacao.getData()).reverse().toString()); // a data salva invertida facilita a ordenação
        values.put(COLUNA_ID_REMETENTE, notificacao.getIdRemetente());
        values.put(COLUNA_TITULO, notificacao.getTitulo());
        values.put(COLUNA_TEXTO, notificacao.getTexto());
        values.put(COLUNA_STATUS, notificacao.getStatus());
        values.put(COLUNA_ID_DISCIPLINA, notificacao.getIdDisciplina());
        values.put(COLUNA_ID_USUARIO, notificacao.getIdUsuario());
 
        return values;
    }
}
