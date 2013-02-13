package br.com.hachitecnologia.devolvame.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import br.com.hachitecnologia.devolvame.dao.DBHelper;

public class ObjetosEmprestadosProvider extends ContentProvider {

	private DBHelper dbHelper;

	// Authority que iremos usar no URI do nosso Content Provider
	private static final String AUTHORITY = "br.com.hachitecnologia.devolvame.provider";
	// Path padrão que iremos usar no URI do nosso Content Provider
	public static final String PATH = "objeto_emprestado";
	// URI
	public static final Uri URI = Uri.parse("content://" + AUTHORITY + "/"
			+ PATH);

	// Identificador de busca por todos os registros
	private static final int BUSCA_TODOS = 1;
	// Identificador de busca por registro de ID específico
	private static final int BUSCA_POR_ID = 2;

	/**
	 * Declaramos o UriMatcher, que será usado para definir as URIs que iremos
	 * aceitar em nosso Content Provider.
	 */
	private static UriMatcher uriMatcher;

	/**
	 * Definimos os possíveis URIs que serão aceitos para realizar uma busca
	 * através do nosso Content Provider.
	 */
	static {
		// Inicializamos nosso UriMatcher
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		// Definimos o URI para a busca abrangente
		uriMatcher.addURI(AUTHORITY, PATH, BUSCA_TODOS);
		// Definimos o URI para a busca por ID
		uriMatcher.addURI(AUTHORITY, PATH + "/#", BUSCA_POR_ID);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// Iniciamos a conexão com o banco de dados em modo leitura
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		/**
		 * Identifica a URI recebida e executa a ação solicitada.
		 */
		switch (uriMatcher.match(uri)) {
		// Realiza a busca por ID
		case BUSCA_POR_ID:
			// Recupera o ID enviado na URI
			String id = uri.getPathSegments().get(1);

			return db.query(PATH, projection, "_id = ?", new String[] { id },
					null, null, sortOrder);
			/**
			 * Realiza uma busca geral, abrangendo todos os registros (de acordo
			 * com os parâmetros recebidos)
			 */
		case BUSCA_TODOS:
			return db.query(PATH, projection, selection, selectionArgs, null,
					null, sortOrder);
			// Lança uma Exception caso tenha recebido uma URI não esperada
		default:
			throw new IllegalArgumentException("URI inválido!");
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Inicia a conexão com o banco de dados, em modo de gravação
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(PATH, null, values);

		/**
		 * Retorna ao solicitante a URI recebida junto com o ID do registro
		 * inserido.
		 */
		return ContentUris.withAppendedId(URI, id);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO A immplementar, caso necessário
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO A implementar, caso necessário
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		/**
		 * Identifica o tipo do dado que estamos retornando
		 */
		switch (uriMatcher.match(uri)) {
		// Tipo de retorno da busca geral
		case BUSCA_TODOS:
			return "vnd.android.cursor.dir/vnd."
					+ "br.com.hachitecnologia.devolvame.provider/objeto_emprestado";
			// Tipo de retorno da busca por ID
		case BUSCA_POR_ID:
			return "vnd.android.cursor.item/vnd."
					+ "br.com.hachitecnologia.devolvame.provider/objeto_emprestado";
			// Lança uma Exception caso receba um URI não esperado
		default:
			throw new IllegalArgumentException("URI inválido");
		}
	}
}
