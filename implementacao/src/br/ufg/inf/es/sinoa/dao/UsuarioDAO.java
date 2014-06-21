package br.ufg.inf.es.sinoa.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.es.sinoa.persistence.PersistenceHelper;
import br.ufg.inf.es.sinoa.vo.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {
		 
	    public static final String NOME_TABELA = "Usuario";
	    public static final String COLUNA_MATRICULA = "matricula";
	    public static final String COLUNA_NOME = "nome";
	    public static final String COLUNA_EMAIL = "email";
	    public static final String COLUNA_SENHA = "senha";
	    public static final String COLUNA_CURSO = "curso";	 
	 
	    public static final String SCRIPT_CRIACAO_TABELA_USUARIO = "CREATE TABLE " + NOME_TABELA + "("
	            + COLUNA_MATRICULA + " INTEGER PRIMARY KEY," + COLUNA_NOME + " TEXT," + COLUNA_EMAIL + " TEXT,"
	            + COLUNA_SENHA + " TEXT," + COLUNA_CURSO + " TEXT" +")";
	 
	    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
	 
	    private SQLiteDatabase dataBase = null;
	 
	    private static UsuarioDAO instance;
	     
	    public static UsuarioDAO getInstance(Context context) {
	        if(instance == null)
	            instance = new UsuarioDAO(context);
	        return instance;
	    }
	 
	    private UsuarioDAO(Context context) {
	        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
	        dataBase = persistenceHelper.getWritableDatabase();
	    }
	 
	    public void salvar(Usuario usuario) {
	        ContentValues values = gerarContentValuesUsuario(usuario);
	        dataBase.insert(NOME_TABELA, null, values);
	    }
	 
	    public List<Usuario> recuperarTodos() {
	        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
	        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
	        List<Usuario> usuarios = construirUsuarioPorCursor(cursor);
	 
	        return usuarios;
	    }
	    
	    /*
	    public Usuario recuperarUsuarioPorMatricula(int matricula) {
	        String query = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_MATRICULA + " = " + matricula;
	        Cursor cursor = dataBase.rawQuery(query, null);
	        List<Usuario> usuarios = construirUsuarioPorCursor(cursor);
	        
	        if (usuarios.isEmpty()){
	        	return null;
	        } else {
	        	usuarios.get(0);
	        }
	        
	        return null;
	    }*/
	    
	    public void deletarTodosUsuarios(){
	    	String query = "DELETE FROM " + NOME_TABELA;
	    	dataBase.execSQL(query);
	    }
	 
	    public void deletar(Usuario usuario) {
	 
	        String[] valoresParaSubstituir = {
	                String.valueOf(usuario.getMatricula())
	        };
	 
	        dataBase.delete(NOME_TABELA, COLUNA_MATRICULA + " =  ?", valoresParaSubstituir);
	    }
	 
	    public void editar(Usuario usuario) {
	        ContentValues valores = gerarContentValuesUsuario(usuario);
	 
	        String[] valoresParaSubstituir = {
	                String.valueOf(usuario.getMatricula())
	        };
	 
	        dataBase.update(NOME_TABELA, valores, COLUNA_MATRICULA + " = ?", valoresParaSubstituir);
	    }
	 
	    public void fecharConexao() {
	        if(dataBase != null && dataBase.isOpen())
	            dataBase.close(); 
	    }
	 
	    private List<Usuario> construirUsuarioPorCursor(Cursor cursor) {
	        List<Usuario> usuarios = new ArrayList<Usuario>();
	        if(cursor == null)
	            return usuarios;
	         
	        try {
	 
	            if (cursor.moveToFirst()) {
	                do {
	 
	                	int indexMatricula = cursor.getColumnIndex(COLUNA_MATRICULA);
						int indexNome = cursor.getColumnIndex(COLUNA_NOME);
						int indexEmail = cursor.getColumnIndex(COLUNA_EMAIL);
						int indexSenha = cursor.getColumnIndex(COLUNA_SENHA);
						int indexCurso = cursor.getColumnIndex(COLUNA_CURSO);

						int matricula = cursor.getInt(indexMatricula);
						String nome = cursor.getString(indexNome);
						String email = cursor.getString(indexEmail);
						String senha = cursor.getString(indexSenha);
						String curso = cursor.getString(indexCurso);
						
	                    Usuario usuario = new Usuario(matricula, nome, email, senha, curso);
	 
	                    usuarios.add(usuario);
	 
	                } while (cursor.moveToNext());
	            }
	             
	        } finally {
	            cursor.close();
	        }
	        return usuarios;
	    }
	 
	    private ContentValues gerarContentValuesUsuario(Usuario usuario) {
	        ContentValues values = new ContentValues();
	        values.put(COLUNA_MATRICULA, usuario.getMatricula());
	        values.put(COLUNA_NOME, usuario.getNome());
	        values.put(COLUNA_EMAIL, usuario.getEmail());
	        values.put(COLUNA_SENHA, usuario.getSenha());
	        values.put(COLUNA_CURSO, usuario.getCurso());
	 
	        return values;
	    }
	}
