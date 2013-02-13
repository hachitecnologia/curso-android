package br.com.hachitecnologia.devolvame.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;

public class Alarme {
	
	/**
	 * Método responsável por agendar um Alarme.
	 * @param objeto
	 * @param context
	 */
	public static void defineAlarme(ObjetoEmprestado objeto, Context context) {
		AlarmManager alarmManager = (AlarmManager) 
			context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, objeto.getDataLembrete().getTimeInMillis(), 			getPendingIntent(objeto, context));
	}
	
	/**
	 * Método responsável por definir a PendingIntent que será usada 
	 * para criar e cancelar um Alarme. Criamos um método com essa 
	 * responsabilidade para reaproveitar o código na hora de executar 
	 * estas duas tarefas.
	 * @param objeto
	 * @param context
	 * @return
	 */
	private static PendingIntent getPendingIntent(ObjetoEmprestado objeto, Context context) {
		/*
		 *  Texto da mensagem que será enviada como lembrete, via SMS. Esta mensagem será 
		 *  enviada como Extra para o Service encarregado de enviar o SMS.
		 */
		String textoMensagemSMS = "Você pegou emprestado o meu objeto \"%s\" e ainda não " +
				"o devolveu. Por favor, devolva-me o quanto antes. Obrigado. ";
		textoMensagemSMS = String.format(textoMensagemSMS, objeto.getObjeto());
		
		// Texto que será mostrado na notificação
		String textoNotificacao = "Lembrete do objeto \"%s\" enviado para %s.";
		textoNotificacao = String.format(textoNotificacao, objeto.getObjeto(), 			objeto.getContato().getNome());
		// Título da Notificação
		String tituloNotificacao = "Lembrete enviado para %s"; 
		tituloNotificacao = String.format(tituloNotificacao, objeto.getContato().getNome());
		
		/*
		 *  Define a Intent que irá invocar o Service responsável pelo envio do 
		 *  SMS e disparo da Notificação. 
		 */
		Intent intent = new Intent("br.com.hachitecnologia.devolvame.action.ENVIA_SMS_LEMBRETE");
		/*
		 *  Injeta na Intent os parâmetros necessários para o envio do SMS e disparo 
		 *  da Notificação. 
		 */
		intent.putExtra("textoMensagemSMS", textoMensagemSMS);
		intent.putExtra("numeroTelefone", objeto.getContato().getTelefone());
		intent.putExtra("textoNotificacao", textoNotificacao);
		intent.putExtra("tituloNotificacao", tituloNotificacao);
		
		PendingIntent pendingIntent = PendingIntent.getService(context, 
			objeto.getId().intValue(), intent, 0);
		
		return pendingIntent;
	}
	
	/**
	 * Método responsável por cancelar um Alarme, se necessário.
	 * @param objeto
	 * @param context
	 */
	public static void cancelaAlarme(ObjetoEmprestado objeto, Context context) {
		AlarmManager alarmManager = (AlarmManager) 
			context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(getPendingIntent(objeto, context));
	}

}
