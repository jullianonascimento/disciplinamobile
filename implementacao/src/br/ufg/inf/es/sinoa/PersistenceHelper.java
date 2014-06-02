package br.ufg.inf.es.sinoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper {
	 
    public static final String NOME_BANCO =  "BancoNoticias";
    public static final int VERSAO =  1;
     
    private static PersistenceHelper instance;
     
    private PersistenceHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
     
    public static PersistenceHelper getInstance(Context context) {
        if(instance == null)
            instance = new PersistenceHelper(context);
         
        return instance;
    }
    
    public static void reiniciaBanco(SQLiteDatabase db){
    	 db.execSQL(NoticiaDAO.SCRIPT_DELECAO_TABELA);
    	 db.execSQL(NoticiaDAO.SCRIPT_CRIACAO_TABELA_NOTICIAS);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoticiaDAO.SCRIPT_CRIACAO_TABELA_NOTICIAS);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NoticiaDAO.SCRIPT_DELECAO_TABELA);
        onCreate(db);
    }
 
}
