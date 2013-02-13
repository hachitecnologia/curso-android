package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MinhaSetimaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Button botao = new Button(getApplicationContext());
		botao.setText("Abrir outra Activity");
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						MinhaOitavaActivity.class);

				/**
				 * Injetando uma String no objeto Bundle que será passado como
				 * parâmetro para outra Activity.
				 */
				Bundle bundle = new Bundle();
				bundle.putString("MeuParametro", "Conteúdo do meu parâmetro!");

				// Injetando o objeto Bundle na Intent
				i.putExtras(bundle);

				// Invocando outra Activity
				startActivity(i);
			}
		});

		// Definindo a View que será apresentada na tela
		setContentView(botao);
	}
}