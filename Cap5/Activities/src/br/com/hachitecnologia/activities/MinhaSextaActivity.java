package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MinhaSextaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Button botao = new Button(getApplicationContext());
		botao.setText("Retornar informação");

		/**
		 * Definindo o evento a ser disparado ao clicar no botão. Em nosso caso,
		 * o evento irá finalizar a Activity atual retornando informações para a
		 * Activity anterior.
		 */
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Define os dados que serão retornados para a Activity
				// chamadora
				Intent i = getIntent();
				i.putExtra("ParametroRetorno",
						"Conteúdo do parâmetro de retorno.");

				// Define o Result Code para ser enviado para a Activity
				// chamadora
				setResult(1, i);
				// Finaliza a Activity atual
				finish();
			}
		});

		/**
		 * Definindo a View a ser apresentada na tela. Em nosso caso, será
		 * apresentado apenas um Button.
		 */
		setContentView(botao);

	}
}
