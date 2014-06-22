package br.ufg.inf.es.sinoa.persistence;

import br.ufg.inf.es.sinoa.dao.DisciplinaDAO;
import br.ufg.inf.es.sinoa.dao.NotificacaoDAO;
import br.ufg.inf.es.sinoa.dao.RemetenteDAO;
import br.ufg.inf.es.sinoa.dao.UsuarioDAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper {
	 
    public static final String NOME_BANCO =  "BancoSINOA";
    public static final int VERSAO =  6;
     
    private static PersistenceHelper instance;
     
    private PersistenceHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
     
    public static PersistenceHelper getInstance(Context context) {
        if(instance == null)
            instance = new PersistenceHelper(context);
         
        return instance;
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDAO.SCRIPT_CRIACAO_TABELA_USUARIO);
        db.execSQL(DisciplinaDAO.SCRIPT_CRIACAO_TABELA_DISCIPLINA);
        db.execSQL(RemetenteDAO.SCRIPT_CRIACAO_TABELA_REMETENTE);
        db.execSQL(NotificacaoDAO.SCRIPT_CRIACAO_TABELA_NOTIFICACAO);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NotificacaoDAO.SCRIPT_DELECAO_TABELA);
        db.execSQL(RemetenteDAO.SCRIPT_DELECAO_TABELA);
        db.execSQL(DisciplinaDAO.SCRIPT_DELECAO_TABELA);
        db.execSQL(UsuarioDAO.SCRIPT_DELECAO_TABELA);
        onCreate(db);
    }
 
}
