package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MinhaTerceiraActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView texto = new TextView(getApplicationContext());
		texto.setText("Activity invocada através de outra Activity.");
		setContentView(texto);
	}
}
