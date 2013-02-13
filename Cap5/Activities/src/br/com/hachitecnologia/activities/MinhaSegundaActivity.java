package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MinhaSegundaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView texto = new TextView(getApplicationContext());
		texto.setText(getString(R.string.minha_mensagem));
		setContentView(texto);
	}

}
