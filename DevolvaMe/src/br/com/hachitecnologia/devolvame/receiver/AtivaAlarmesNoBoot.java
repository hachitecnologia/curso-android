package br.com.hachitecnologia.devolvame.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AtivaAlarmesNoBoot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Define uma nova Intent, que irá chamar o Service
		// "AtivaAlarmesService"
		Intent i = new Intent(
				"br.com.hachitecnologia.devolvame.action.ATIVA_ALARMES");
		// Executa o Service
		context.startService(i);
	}
}
