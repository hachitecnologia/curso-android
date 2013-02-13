package br.com.hachitecnologia.receivers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastReceiverA extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Recebe o objeto Bundle disponibilizado pelo Broadcast Receiver
		// executado anteriormente.
		Bundle b = getResultExtras(true);
		// Recupera a mensagem recebida no Bundle
		String msg = b.getString("mensagem");
		// Apresenta a mensagem na tela, através de um Toast
		Toast.makeText(context, "BroadcastReceiverA: " + msg, Toast.LENGTH_LONG)
				.show();
		// Altera o conteúdo da mensagem do Bundle
		b.putString("mensagem", "Minha mensagem modificada");
		// Disponibiliza o Bundle alterado para os próximos Broadcast Receivers
		setResultExtras(b);
	}

}
