package br.com.hachitecnologia.devolvame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import br.com.hachitecnologia.devolvame.util.Notificacao;
import br.com.hachitecnologia.devolvame.util.Telefonia;

public class EnviaSMSLembreteService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		/*
		 * Recebe como Extra da Intent os parâmetros necessários para o envio do
		 * SMS e disparo da Notificação.
		 */
		String textoMensagemSMS = intent.getStringExtra("textoMensagemSMS");
		String numeroTelefone = intent.getStringExtra("numeroTelefone");
		String textoNotificacao = intent.getStringExtra("textoNotificacao");
		String tituloNotificacao = intent.getStringExtra("tituloNotificacao");

		// Envia o SMS
		Telefonia.enviaSMS(numeroTelefone, textoMensagemSMS);
		// Dispara a Notificação
		Notificacao.mostraNotificacao(tituloNotificacao, textoNotificacao,
				getApplicationContext());

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
