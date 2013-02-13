package br.com.hachitecnologia.alarmesenotificacao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import br.com.hachitecnologia.alarmesenotificacao.AgendaAlarmeActivity;
import br.com.hachitecnologia.alarmesenotificacao.R;

public class MostraNotificacaoService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// Título do Alarme
		String titulo = "Alarme disparado";
		// Mensagem do Alarme
		String mensagem = "O alarme foi disparado.";

		// Definindo o objeto Notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, System.currentTimeMillis());

		/*
		 * Criando a PendingIntent definindo a Intent que será executada quando
		 * o usuário clicar sobre a Notificação. Em nosso caso, será invocada a
		 * Activity AgendaAlarmeActivity.
		 */
		Intent i = new Intent(getApplicationContext(),
				AgendaAlarmeActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, i, 0);

		// Definimos os dados da Notificação
		notification.setLatestEventInfo(getApplicationContext(), titulo,
				mensagem, pendingIntent);
		// Cancela automaticamente a Notificação quando o usuário clicar sobre
		// ela
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// Define som de toque, alerta vibratório e LED para os padrões do
		// dispositivo
		notification.defaults = Notification.DEFAULT_ALL;

		// Agenda a Notificação
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
