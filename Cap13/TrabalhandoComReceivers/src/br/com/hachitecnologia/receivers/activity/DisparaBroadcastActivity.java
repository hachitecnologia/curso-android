package br.com.hachitecnologia.receivers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.hachitecnologia.receivers.R;

public class DisparaBroadcastActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dispara_broadcast);
		// Botão que irá disparar o broadcast
		Button botao = (Button) findViewById(R.id.botao_dispara_broadcast);
		// Definindo o evento do botão
		botao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Criamos uma Intent com a Action "MEU_BROADCAST_RECEIVER"
				Intent intent = new Intent("MEU_BROADCAST_RECEIVER");
				/**
				 *  Inserimos uma String na Intent com uma mensagem 
				 *  que será enviada ao Broadcast Receiver.
				 */
				intent.putExtra("mensagem", "Minha mensagem");
				// Envia um broadcast de forma ordenada
				sendOrderedBroadcast(intent, null);
			}
		});
		
	}

}

