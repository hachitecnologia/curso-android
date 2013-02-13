package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MinhaOitavaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Obtendo o objeto Intent para recuperar os parâmetros recebidos
		Intent i = getIntent();

		// Recuperando o parametro recebido da Activity anterior
		String parametroRecebido = i.getExtras().getString("MeuParametro");

		// Criando a View TextView que irá mostar na tela a String recebida
		TextView texto = new TextView(getApplicationContext());
		texto.setText(parametroRecebido);

		// Definindo a View que será apresentada na tela
		setContentView(texto);
	}
}
