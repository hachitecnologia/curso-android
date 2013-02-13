package br.com.hachitecnologia.devolvame.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencias {
	
	/**
	 * Retorna o RingTone definido na tela de Preferências.
	 * @param context
	 * @return
	 */
	public static String getSomDeToqueDaNotificacao(Context context) {
		SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
		return preferencias.getString("toque_notificacao", "default ringtone");
	}

}
