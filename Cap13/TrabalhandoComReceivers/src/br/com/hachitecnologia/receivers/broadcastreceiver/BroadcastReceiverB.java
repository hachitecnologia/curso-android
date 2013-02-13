package br.com.hachitecnologia.receivers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiverB extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Mensagem recebida pela Intent que disparou o broadcast
		String msg = intent.getStringExtra("mensagem");
		// Apresenta a mensagem na tela, através de um Toast
		Toast.makeText(context, "BroadcastReceiverB: " + msg, Toast.LENGTH_LONG)
				.show();
		// Define um novo objeto Bundle
		Bundle b = new Bundle();
		// Adiciona ao Bundle uma String com uma mensagem
		b.putString("mensagem", "Minha nova mensagem");
		// Disponibiliza o Bundle para os demais Broadcast Receivers a serem
		// executados.
		setResultExtras(b);
	}

}
