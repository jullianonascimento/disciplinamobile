package br.ufg.inf.es.sinoa.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufg.inf.es.sinoa.persistence.PersistenceHelper;
import br.ufg.inf.es.sinoa.vo.Remetente;

public class RemetenteDAO {
	
	public static final String NOME_TABELA = "Remetente";
	    public static final String COLUNA_ID = "id";
	    public static final String COLUNA_NOME = "nome";
	    public static final String COLUNA_ATIVO = "ativo";
	    public static final String COLUNA_ID_USUARIO = "id_usuario";
	 
	    public static final String SCRIPT_CRIACAO_TABELA_REMETENTE = "CREATE TABLE " + NOME_TABELA + "("
	            + COLUNA_ID + " INTEGER PRIMARY KEY," + COLUNA_NOME + " TEXT," + COLUNA_ATIVO + " TEXT,"
	            + COLUNA_ID_USUARIO + " INTEGER," 
				+ "FOREIGN KEY(" + COLUNA_ID_USUARIO + ") REFERENCES " 
				+ UsuarioDAO.NOME_TABELA + "(" + UsuarioDAO.COLUNA_MATRICULA + ") )";
	 
	    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
	 
	    private SQLiteDatabase dataBase = null;
	 
	    private static RemetenteDAO instance;
	     
	    public static RemetenteDAO getInstance(Context context) {
	        if(instance == null)
	            instance = new RemetenteDAO(context);
	        return instance;
	    }
	 
	    private RemetenteDAO(Context context) {
	        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
	        dataBase = persistenceHelper.getWritableDatabase();
	    }
	 
	    public void salvar(Remetente remetente) {
	        ContentValues values = gerarContentValuesRemetente(remetente);
	        dataBase.insert(NOME_TABELA, null, values);
	    }
	 
	    public List<Remetente> recuperarTodos() {
	        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
	        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
	        List<Remetente> remetentes = construirRemetentePorCursor(cursor);
	 
	        return remetentes;
	    }
	    
	    public Remetente recuperarRemetentePorId(int id) {
	    	String query = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_ID + " = " + id;
	        Cursor cursor = dataBase.rawQuery(query, null);
	        List<Remetente> remetentes = construirRemetentePorCursor(cursor);
	        Remetente remetenteEncontrado = null;
	        
	        try {
	        	remetenteEncontrado = remetentes.get(0);
	        } catch (Exception e) {
	        	return null;
	        }
	        
	        return remetenteEncontrado;
	    }
	    	    
	    public void deletarTodosRemetentes(){
	    	String query = "DELETE FROM " + NOME_TABELA;
	    	dataBase.execSQL(query);
	    }
	 
	    public void deletar(Remetente remetente) {
	 
	        String[] valoresParaSubstituir = {
	                String.valueOf(remetente.getId())
	        };
	 
	        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
	    }
	 
	    public void editar(Remetente remetente) {
	        ContentValues valores = gerarContentValuesRemetente(remetente);
	 
	        String[] valoresParaSubstituir = {
	                String.valueOf(remetente.getId())
	        };
	 
	        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
	    }
	 
	    public void fecharConexao() {
	        if(dataBase != null && dataBase.isOpen())
	            dataBase.close(); 
	    }
	 
	    private List<Remetente> construirRemetentePorCursor(Cursor cursor) {
	        List<Remetente> remetentes = new ArrayList<Remetente>();
	        if(cursor == null)
	            return remetentes;
	         
	        try {
	 
	            if (cursor.moveToFirst()) {
	                do {
	 
	                	int indexID = cursor.getColumnIndex(COLUNA_ID);
						int indexNome = cursor.getColumnIndex(COLUNA_NOME);
						int indexAtivo = cursor.getColumnIndex(COLUNA_ATIVO);
						int indexIdUsuario = cursor.getColumnIndex(COLUNA_ID_USUARIO);

						int id = cursor.getInt(indexID);
						String nome = cursor.getString(indexNome);
						String ativo = cursor.getString(indexAtivo);
						int idUsuario = cursor.getInt(indexIdUsuario);
	 
	                    Remetente remetente = new Remetente(id, nome, ativo, idUsuario);
	 
	                    remetentes.add(remetente);
	 
	                } while (cursor.moveToNext());
	            }
	             
	        } finally {
	            cursor.close();
	        }
	        return remetentes;
	    }
	 
	    private ContentValues gerarContentValuesRemetente(Remetente remetente) {
	        ContentValues values = new ContentValues();
	        values.put(COLUNA_ID, remetente.getId());
	        values.put(COLUNA_NOME, remetente.getNome());
	        values.put(COLUNA_ATIVO, remetente.getAtivo());
	        values.put(COLUNA_ID_USUARIO, remetente.getIdUsuario());
	 
	        return values;
	    }

}
