package br.com.hachitecnologia.devolvame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.hachitecnologia.devolvame.R;
import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;

public class CadastraObjetoEmprestadoActivity extends Activity {

	private ObjetoEmprestado objetoEmprestado;
	private EditText campoObjeto;
	private EditText campoNomePessoa;
	private EditText campoTelefone;
	private Button botaoSalvar;
	private Button botaoCancelar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Define o Layout Resource da Activity
		setContentView(R.layout.activity_cadastra_objeto_emprestado);

		campoObjeto = (EditText) findViewById(R.id.cadastro_objeto_campo_objeto);
		campoNomePessoa = (EditText) findViewById(R.id.cadastro_objeto_campo_pessoa);
		campoTelefone = (EditText) findViewById(R.id.cadastro_objeto_campo_telefone);
		botaoSalvar = (Button) findViewById(R.id.cadastra_objeto_botao_salvar);
		botaoCancelar = (Button) findViewById(R.id.cadastra_objeto_botao_cancelar);

		/**
		 * Recebe o objeto recebido como parâmetro da ListView para edição.
		 */
		objetoEmprestado = (ObjetoEmprestado) getIntent().getSerializableExtra(
				"itemSelecionadoParaEdicao");

		if (objetoEmprestado == null) {
			// Instancia um novo objeto do tipo ObjetoEmprestado
			objetoEmprestado = new ObjetoEmprestado();
		} else {
			campoObjeto.setText(objetoEmprestado.getObjeto());
			campoNomePessoa.setText(objetoEmprestado.getContato().getNome());
			campoTelefone.setText(objetoEmprestado.getContato().getTelefone());
		}

		/**
		 * O botao salvar irá salvar o objeto no banco de dados.
		 */
		botaoSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Injeta no objeto "objetoEmprestado" os dados informados pelo
				// usuário
				objetoEmprestado.setObjeto(campoObjeto.getText().toString());
				objetoEmprestado.getContato().setNome(
						campoNomePessoa.getText().toString());
				objetoEmprestado.getContato().setTelefone(
						campoTelefone.getText().toString());

				// Instancia o DAO para persistir o objeto
				ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(
						getApplicationContext());
				// Salva o objeto no banco de dados
				dao.salva(objetoEmprestado);

				// Mostra para o usuário uma mensagem de sucesso na operação
				Toast.makeText(getApplicationContext(),
						"Objeto salvo com sucesso!", Toast.LENGTH_LONG).show();
				
				// Depois de salvar, vai para a Lista dos objetos emprestados
				startActivity(new Intent(getApplicationContext(), ListaObjetosEmprestadosActivity.class));
			}

		});

		/**
		 * O botao cancelar apenas finaliza a Activity.
		 */
		botaoCancelar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

	}
}
