package br.com.hachitecnologia.receivers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiverA extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Mensagem recebida pela Intent que disparou o broadcast
		String msg = intent.getStringExtra("mensagem");
		// Apresenta a mensagem na tela, através de um Toast
		Toast.makeText(context, "BroadcastReceiverA: " + msg, Toast.LENGTH_LONG)
				.show();
	}

}
