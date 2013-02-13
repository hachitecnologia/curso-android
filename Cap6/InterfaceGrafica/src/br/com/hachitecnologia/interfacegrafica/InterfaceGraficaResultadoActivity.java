package br.com.hachitecnologia.interfacegrafica;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InterfaceGraficaResultadoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interface_grafica_resultado);

		// Trata valores recebidos
		String valorNome = getIntent().getExtras().getString("nome");
		String valorMaiorIdade = getIntent().getExtras()
				.getString("maiorIdade");
		String valorSexo = getIntent().getExtras().getString("sexo");
		String valorDataNascimento = getIntent().getExtras().getString(
				"dataNascimento");

		// Define valores nas Views a serem apresentadas na tela
		TextView nome = (TextView) findViewById(R.id.nome_informado);
		nome.setText(valorNome);

		TextView sexo = (TextView) findViewById(R.id.sexo_informado);
		sexo.setText(valorSexo);

		TextView dataNascimento = (TextView) findViewById(R.id.data_nascimento_informada);
		dataNascimento.setText(valorDataNascimento);

		TextView maiorIdade = (TextView) findViewById(R.id.maior_idade_informado);
		maiorIdade.setText(valorMaiorIdade);

	}

}
