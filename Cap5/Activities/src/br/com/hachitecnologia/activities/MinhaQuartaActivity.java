package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MinhaQuartaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Button botao = new Button(getApplicationContext());
		botao.setText("Abrir outra Activity");

		/**
		 * Definindo o evento a ser disparado ao clicar no botão. Em nosso caso
		 * o evento irá disparar outra Activity.
		 */
		botao.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MinhaTerceiraActivity.class);
				startActivity(i);
			}
		});
		setContentView(botao);
	}
}
