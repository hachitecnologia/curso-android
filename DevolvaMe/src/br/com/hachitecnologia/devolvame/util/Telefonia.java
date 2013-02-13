package br.com.hachitecnologia.devolvame.util;

import java.util.ArrayList;

import android.telephony.SmsManager;

public class Telefonia {

	/**
	 * Método responsável por enviar mensagens SMS.
	 * 
	 * @param numeroTelefone
	 * @param mensagem
	 */
	public static void enviaSMS(String numeroTelefone, String mensagem) {
		SmsManager sms = SmsManager.getDefault();
		// Divide a mensagem em partes
		ArrayList<String> mensagemArray = sms.divideMessage(mensagem);
		// Envia cada parte da mensagem em um SMS
		sms.sendMultipartTextMessage(numeroTelefone, null, mensagemArray, null,
				null);
	}

}
