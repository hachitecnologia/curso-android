package br.com.hachitecnologia.interfacegrafica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class InterfaceGraficaActivity extends Activity {

	private EditText nome;
	private CheckBox maiorIdade;
	private RadioButton sexoMasculino;
	private DatePicker dataNascimento;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interface_grafica);

		// Referenciando as Views através de seus IDs
		nome = (EditText) findViewById(R.id.nome);
		maiorIdade = (CheckBox) findViewById(R.id.maior_idade);
		sexoMasculino = (RadioButton) findViewById(R.id.sexo_masculino);
		dataNascimento = (DatePicker) findViewById(R.id.data_nascimento);

		Button salvar = (Button) findViewById(R.id.salvar);
		// Evento do botão Salvar
		salvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Recupera os valores informados pelo usuário
				String valorNome = nome.getText().toString();
				String valorMaiorIdade = maiorIdade.isChecked() ? "sim" : "não";
				String valorSexo = sexoMasculino.isChecked() ? "Masculino"
						: "Feminino";
				String valorDataNascimento = dataNascimento.getDayOfMonth()
						+ "/" + (dataNascimento.getMonth() + 1) + "/"
						+ dataNascimento.getYear();

				// Define parâmetros a serem passados para a Activity de
				// resultado
				Intent i = new Intent(getApplicationContext(),
						InterfaceGraficaResultadoActivity.class);
				i.putExtra("nome", valorNome);
				i.putExtra("maiorIdade", valorMaiorIdade);
				i.putExtra("sexo", valorSexo);
				i.putExtra("dataNascimento", valorDataNascimento);

				// Inicia a Activity com o resultado
				startActivity(i);

			}

		});

		Button sair = (Button) findViewById(R.id.sair);

		// Evento do botão Sair
		sair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// O botão Sair apenas finaliza a Activity
				finish();
			}
		});
	}

}
