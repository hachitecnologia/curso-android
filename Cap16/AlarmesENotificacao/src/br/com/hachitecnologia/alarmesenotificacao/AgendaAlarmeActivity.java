package br.com.hachitecnologia.alarmesenotificacao;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AgendaAlarmeActivity extends Activity {

	private Button botao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_alarme);

		// Faz referência ao botão do arquivo de Layout
		botao = (Button) findViewById(R.id.botao_agendar_alarme);
		// Define o evento do botão
		botao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				 * Horário em que o Alarme será disparado. Em mosso caso, em 30
				 * segundo após o clique no botão.
				 */
				long horaDoDisparo = System.currentTimeMillis() + 30000;

				/*
				 * Define a Intent que será executada quando o Alarme for
				 * disparado. Em nosso caso, chamaremos o Service
				 * MostraNotificacaoService para mostrar uma Notificação
				 * informando que o Alarme foi disparado.
				 */
				Intent i = new Intent(
						"br.com.hachitecnologia.action.MOSTRA_NOTIFICACAO");
				PendingIntent pendingIntent = PendingIntent.getService(
						getApplicationContext(), 0, i, 0);

				// Define o Alarme
				AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, horaDoDisparo,
						pendingIntent);

				// Desabilita o botão para impedir cliques repetidos.
				botao.setEnabled(false);

				// Mostra um Toast informando que o Alarme foi agendado.
				Toast.makeText(getApplicationContext(), "Alarme agendado!",
						Toast.LENGTH_LONG).show();

			}
		});

	}

}
