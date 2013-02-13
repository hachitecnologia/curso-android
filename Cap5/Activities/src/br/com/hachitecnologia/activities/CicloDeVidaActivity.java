package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CicloDeVidaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("CicloDeVida", "Método onCreate() chamado");

		// Texto a ser mostrado na tela
		TextView texto = new TextView(getApplicationContext());
		texto.setText("Um texto qualquer");

		// Define a TextView que criamos como View padrão
		setContentView(texto);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("CicloDeVida", "Método onStart() chamado");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("CicloDeVida", "Método onResume() chamado");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("CicloDeVida", "Método onPause() chamado");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("CicloDeVida", "Método onStop() chamado");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("CicloDeVida", "Método onDestroy() chamado");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("CicloDeVida", "Método onRestart() chamado");
	}

}