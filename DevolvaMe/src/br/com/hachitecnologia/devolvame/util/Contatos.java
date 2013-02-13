package br.com.hachitecnologia.devolvame.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import br.com.hachitecnologia.devolvame.modelo.Contato;

public class Contatos {

	/**
	 * Consulta um contato através de seu ID.
	 * 
	 * @param id
	 * @param context
	 * @return
	 */
	public static Contato getContato(int id, Context context) {

		ContentResolver cr = context.getContentResolver();
		/**
		 * Consultamos no Content Provider do aplicativo de Contatos do Android
		 * o contato que tenha o ID recebido como parâmetro.
		 */
		Cursor cursor = cr.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID
				+ " = ?", new String[] { String.valueOf(id) }, null);

		Contato contato = null;

		// Iteramos sobre o Cursor para obter os dados desejados
		if (cursor.moveToFirst()) {
			contato = new Contato();
			// Obtém o ID do contato
			contato.setId(id);
			// Obtém o Nome do contato
			contato.setNome(cursor.getString(cursor
					.getColumnIndex(Phone.DISPLAY_NAME)));
			// Obtém o Telefone do contato
			contato.setTelefone(cursor.getString(cursor
					.getColumnIndex(Phone.NUMBER)));
		}

		// Fechamos o Cursor
		cursor.close();

		return contato;
	}
}