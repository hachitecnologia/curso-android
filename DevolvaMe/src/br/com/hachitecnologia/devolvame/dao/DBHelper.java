package br.com.hachitecnologia.devolvame.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// Nome do banco de dados
	private static final String NOME_DO_BANCO = "devolva_me";

	// Versão atual do banco de dados
	private static final int VERSAO_DO_BANCO = 2;

	public DBHelper(Context context) {
		super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
	}

	/**
	 * Cria a tabela “objeto_emprestado” no banco de dados, caso ela não exista.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE objeto_emprestado ("
				+ "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
				+ ",objeto TEXT NOT NULL"
				+ ",contato_id INTEGER NOT NULL"
				+ ",data_emprestimo INTEGER NOT NULL"
				+ ");";
		db.execSQL(sql);
	}

	/**
	 * Atualiza a estrutura da tabela no banco de dados, caso sua versão tenha
	 * mudado.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS objeto_emprestado";
		db.execSQL(sql);
		onCreate(db);
	}
}