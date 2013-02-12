package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LoggingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView texto = new TextView(getApplicationContext());
		texto.setText("Emitindo mensagens de Log com o LogCat. "
				+ "Veja na View LogCat as mensagens de Log emitidas por esta Activity.");

		// Log de informação (info)
		Log.i("Trabalhando com Logs", "Mensagem de informação");

		// Log de aviso (warn)
		Log.w("Trabalhando com Logs", "Mensagem de aviso");

		// Log de erro (error)
		Log.e("Trabalhando com Logs", "Mensagem de erro");

		// Log de depuração (debug)
		Log.d("Trabalhando com Logs", "Mensagem de depuração");

		// Log de depuração detalhada (verbose)
		Log.v("Trabalhando com Logs", "Mensagem de depuração detalhada");

		setContentView(texto);

	}
}
