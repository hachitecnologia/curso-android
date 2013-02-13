package br.com.hachitecnologia.devolvame.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.com.hachitecnologia.devolvame.R;
import br.com.hachitecnologia.devolvame.activity.TelaInicialActivity;

public class Notificacao {

	public static void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		// Tempo em que a Notificação será disparada
		long tempoDefinido = System.currentTimeMillis();

		// Objeto Notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, tempoDefinido);

		// Intent que será disparada quando o usuário clicar sobre a Notificação
		Intent intent = new Intent(context, TelaInicialActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);

		// Configurando os dados da Notificação
		notification.setLatestEventInfo(context, titulo, mensagem,
				pendingIntent);

		// Oculta a notificação após o usuário clicar sobre ela
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// Define o som de toque, alerta vibratório e LED padrões do dispositivo
		notification.defaults = Notification.DEFAULT_ALL;

		// Agendando a Notificação
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

}
