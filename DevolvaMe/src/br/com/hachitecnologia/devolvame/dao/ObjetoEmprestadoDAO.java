package br.com.hachitecnologia.devolvame.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;

public class ObjetoEmprestadoDAO {

	private DBHelper dbHelper;

	public ObjetoEmprestadoDAO(Context context) {
		dbHelper = new DBHelper(context);
	}

	/**
	 * Adiciona objeto no banco de dados.
	 */
	public void adiciona(ObjetoEmprestado objeto) {
		// Encapsula no objeto do tipo ContentValues os valores a serem
		// persistidos no banco de dados
		ContentValues values = new ContentValues();
		values.put("objeto", objeto.getObjeto());
		values.put("data_emprestimo", System.currentTimeMillis());
		values.put("pessoa", objeto.getContato().getNome());
		values.put("telefone", objeto.getContato().getTelefone());

		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Insere o registro no banco de dados
		long id = db.insert("objeto_emprestado", null, values);
		objeto.setId(id);

		// Encerra a conexão com o banco de dados
		db.close();
	}

	/**
	 * Lista todos os registros da tabela “objeto_emprestado”
	 */
	public List<ObjetoEmprestado> listaTodos() {

		// Cria um List para guardar os objetos consultados no banco de dados
		List<ObjetoEmprestado> objetos = new ArrayList<ObjetoEmprestado>();

		// Instancia uma nova conexão com o banco de dados em modo leitura
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		// Executa a consulta no banco de dados
		Cursor c = db.query("objeto_emprestado", null, null, null, null, null,
				"objeto ASC");

		/**
		 * Percorre o Cursor, injetando os dados consultados em um objeto do
		 * tipo ObjetoEmprestado e adicionando-os na List
		 */
		try {
			while (c.moveToNext()) {
				ObjetoEmprestado objeto = new ObjetoEmprestado();
				objeto.setId(c.getLong(c.getColumnIndex("_id")));
				objeto.setObjeto(c.getString(c.getColumnIndex("objeto")));
				objeto.getContato().setNome(
						c.getString(c.getColumnIndex("pessoa")));
				objeto.getContato().setTelefone(
						c.getString(c.getColumnIndex("telefone")));
				objetos.add(objeto);
			}

		} finally {
			// Encerra o Cursor
			c.close();
		}

		// Encerra a conexão com o banco de dados
		db.close();

		// Retorna uma lista com os objetos consultados
		return objetos;
	}

	/**
	 * Altera o registro no banco de dados.
	 */
	public void atualiza(ObjetoEmprestado objeto) {
		// Encapsula no objeto do tipo ContentValues os valores a serem
		// atualizados no banco de dados
		ContentValues values = new ContentValues();
		values.put("objeto", objeto.getObjeto());
		values.put("pessoa", objeto.getContato().getNome());
		values.put("telefone", objeto.getContato().getTelefone());

		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Atualiza o registro no banco de dados
		db.update("objeto_emprestado", values, "_id=?", new String[] { objeto
				.getId().toString() });

		// Encerra a conexão com o banco de dados
		db.close();
	}

	/**
	 * Salva objeto no banco de dados. Caso o objeto não exista no banco de
	 * dados, ele o adiciona. Caso o objeto exista no banco de dados, apenas
	 * atualiza os valores dos campos modificados.
	 * 
	 * @param objeto
	 */
	public void salva(ObjetoEmprestado objeto) {
		/**
		 * Se o ID do objeto é nulo é porque ele ainda não existe no banco de
		 * dados, logo subentende-se que queremos adicionar o objeto no banco de
		 * dados. Sendo assim, chamaremos o método adiciona() já definido no
		 * DAO.
		 */
		if (objeto.getId() == null) {
			adiciona(objeto);
			/**
			 * Caso o objeto possua um ID é porque ele já existe no banco de
			 * dados, logo subentende-se que queremos alterar seus dados no
			 * banco de dados. Sendo assim, chamaremos o método atualiza() já
			 * definido no DAO.
			 */
		} else {
			atualiza(objeto);
		}
	}

	/**
	 * Remove um registro no banco de dados.
	 */
	public void remove(ObjetoEmprestado objeto) {
		// Instancia uma conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Remove o registro no banco de dados
		db.delete("objeto_emprestado", "_id=?", new String[] { objeto.getId()
				.toString() });

		// Encerra a conexão com o banco de dados
		db.close();
	}

}
