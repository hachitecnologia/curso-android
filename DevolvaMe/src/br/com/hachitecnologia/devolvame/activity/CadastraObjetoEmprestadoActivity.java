package br.com.hachitecnologia.devolvame.activity;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import br.com.hachitecnologia.devolvame.R;
import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Alarme;
import br.com.hachitecnologia.devolvame.util.Util;

public class CadastraObjetoEmprestadoActivity extends Activity {

	private ObjetoEmprestado objetoEmprestado;
	private EditText campoObjeto;
	private TextView campoNomePessoa;
	private Button botaoSelecionarContato;
	private Button botaoSalvar;
	private Button botaoCancelar;
	private ImageView campoFotoObjeto;
	private CheckBox campoLembreteAtivo;
	private DatePicker campoLembreteData;
	private TimePicker campoLembreteHora;
	private TextView labelLembreteDataHora;
	private TextView labelLembreteData;
	private TextView labelLembreteHora;

	private static final int ID_RETORNO_SELECIONA_CONTATO = 1234;
	private static final int ID_RETORNO_TIRA_FOTO_OBJETO = 5678;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Define o Layout Resource da Activity
		setContentView(R.layout.activity_cadastra_objeto_emprestado);

		campoObjeto = (EditText) findViewById(R.id.cadastro_objeto_campo_objeto);
		campoNomePessoa = (TextView) findViewById(R.id.cadastro_objeto_campo_pessoa);
		botaoSelecionarContato = (Button) findViewById(R.id.botao_selecionar_contato);
		campoFotoObjeto = (ImageView) findViewById(R.id.foto_objeto);
		campoLembreteAtivo = (CheckBox) findViewById(R.id.cadastro_objeto_campo_lembrete_ativo);
		campoLembreteData = (DatePicker) findViewById(R.id.cadastra_objeto_campo_lembrete_data);
		campoLembreteHora = (TimePicker) findViewById(R.id.cadastra_objeto_campo_lembrete_hora);
		labelLembreteDataHora = (TextView) findViewById(R.id.cadastro_objeto_label_lembrete_data_hora);
		labelLembreteData = (TextView) findViewById(R.id.cadastro_objeto_label_lembrete_data);
		labelLembreteHora = (TextView) findViewById(R.id.cadastro_objeto_label_lembrete_hora);
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

			// Remove da tela o botão "Selecionar contato na lista"
			botaoSelecionarContato.setVisibility(View.GONE);

			/**
			 * Alteramos a TextView "cadastro_objeto_campo_pessoa" definindo em
			 * seu texto o nome do contato que consultamos no Content Provider e
			 * definimos a visibilidade desta View para VISIBLE, para que ela
			 * fique visível para o usuário.
			 */
			campoNomePessoa.setText(objetoEmprestado.getContato().getNome());
			campoNomePessoa.setVisibility(View.VISIBLE);

			if (objetoEmprestado.getFoto() != null)
				campoFotoObjeto
						.setImageBitmap(Util
								.converteByteArrayPraBitmap(objetoEmprestado
										.getFoto()));

			/*
			 * Habilita/desabilita o lembrete de acordo com a informação do
			 * registro salvo no banco de dados.
			 */
			campoLembreteAtivo.setChecked(objetoEmprestado.isLembreteAtivo());
			/*
			 * Preenche o componente de data do lembrete com a data do registro
			 * salvo no banco de dados.
			 */
			Calendar cal = objetoEmprestado.getDataLembrete();
			campoLembreteData.updateDate(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

			/*
			 * Preenche o componente de hora do lembrete com a hora do registro
			 * salvo no banco de dados.
			 */
			campoLembreteHora.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
			campoLembreteHora.setCurrentMinute(cal.get(Calendar.MINUTE));

			/*
			 * Habilita/desabilita os componentes de definição do lembrete de
			 * acordo com as informações do registro salvo no banco de dados.
			 */
			habilitaDesabilitaCamposLembrete(objetoEmprestado.isLembreteAtivo());

		}

		/**
		 * O botao salvar irá salvar o objeto no banco de dados.
		 */
		botaoSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Injeta no objeto "objetoEmprestado" os dados informados pelo
				// usuário
				objetoEmprestado.setObjeto(campoObjeto.getText().toString());

				// Define no objeto "ObjetoEmprestado" a data/horário do
				// lembrete
				Calendar cal = Calendar.getInstance();
				cal.set(campoLembreteData.getYear(),
						campoLembreteData.getMonth(),
						campoLembreteData.getDayOfMonth(),
						campoLembreteHora.getCurrentHour(),
						campoLembreteHora.getCurrentMinute());
				objetoEmprestado.setDataLembrete(cal);

				// Instancia o DAO para persistir o objeto
				ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(
						getApplicationContext());
				// Salva o objeto no banco de dados
				dao.salva(objetoEmprestado);

				// Mostra para o usuário uma mensagem de sucesso na operação
				Toast.makeText(getApplicationContext(),
						"Objeto salvo com sucesso!", Toast.LENGTH_LONG).show();

				// Define o Alarme
				if (objetoEmprestado.isLembreteAtivo())
					// Agenda o Alarme, caso o usuário tenha habilitado o
					// lembrete
					Alarme.defineAlarme(objetoEmprestado,
							getApplicationContext());
				else
					// Cancela o Alarme, caso o usuário tenha desabilitado o
					// lembrete
					Alarme.cancelaAlarme(objetoEmprestado,
							getApplicationContext());

				// Depois de salvar, vai para a Lista dos objetos emprestados
				startActivity(new Intent(getApplicationContext(),
						ListaObjetosEmprestadosActivity.class));
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

		/**
		 * O botão "Selecionar contato na lista" irá abrir a Activity de
		 * Contatos do Android para que o usuário possa selecionar um contato na
		 * lista.
		 */
		botaoSelecionarContato.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * Na Intent, definimos a Action Intent.ACTION_PICK do Android,
				 * usada para retornar um determinado item selecionado em uma
				 * lista.
				 */
				Intent i = new Intent(Intent.ACTION_PICK);
				/**
				 * Passamos a lista de contatos, do aplicativo de Contatos
				 * nativo do Android, para que o usuário possa selecionar um
				 * contato na lista.
				 */
				i.setData(Contacts.CONTENT_URI);
				// Abre a lista com os contatos para seleção
				startActivityForResult(i, ID_RETORNO_SELECIONA_CONTATO);
			}
		});

		/**
		 * Ao clicar sobre o nome do Contato selecionado, o usuário será
		 * direcionado para a Activity de Contatos do Android, permitindo-o
		 * selecionar um outro contato.
		 */
		campoNomePessoa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * Na Intent, definimos a Action Intent.ACTION_PICK do Android,
				 * usada para retornar um determinado item selecionado em uma
				 * lista.
				 */
				Intent i = new Intent(Intent.ACTION_PICK);
				/**
				 * Passamos a lista de contatos, do aplicativo de Contatos
				 * nativo do Android, para que o usuário possa selecionar um
				 * contato na lista.
				 */
				i.setData(Contacts.CONTENT_URI);
				// Abre a lista com os contatos para seleção
				startActivityForResult(i, ID_RETORNO_SELECIONA_CONTATO);
			}
		});

		campoFotoObjeto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(i, ID_RETORNO_TIRA_FOTO_OBJETO);
			}
		});

		campoLembreteAtivo
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						habilitaDesabilitaCamposLembrete(isChecked);
						objetoEmprestado.setLembreteAtivo(isChecked);
					}
				});

	}

	/**
	 * Trata o resultado vindo de uma Action chamada através do método
	 * startActivityForResult().
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		/**
		 * Trata o retorno vindo da tela de seleção de contatos.
		 */
		case (ID_RETORNO_SELECIONA_CONTATO):
			if (resultCode == Activity.RESULT_OK) {
				/**
				 * Após selecionado o contato pelo usuário, recebemos uma URI
				 * que irá apontar para o contato selecionado.
				 */
				Uri contactData = data.getData();
				/**
				 * Usaremos um ContentResolver para consultar os dados do
				 * contato selecionado no ContentProvider do aplicativo de
				 * Contatos do Android.
				 */
				ContentResolver contentResolver = getContentResolver();
				/**
				 * Executamos a query e atribuímos o resultado em um Cursor para
				 * navegarmos sobre os dados retornados pelo ContentProvider. Na
				 * query passamos apenas a URI, sem definir nenhum parâmetro
				 * adicional, já que a URI retornada pela Action aponta
				 * diretamente para o contato selecionado.
				 */
				Cursor cursor = contentResolver.query(contactData, null, null,
						null, null);
				// Iteramos sobre o Cursor para obter os dados desejados
				if (cursor.moveToFirst()) {
					// Obtém o ID do contato
					objetoEmprestado.getContato().setId(
							cursor.getInt(cursor.getColumnIndex(Phone._ID)));
					// Obtém o Nome do contato
					objetoEmprestado.getContato().setNome(
							cursor.getString(cursor
									.getColumnIndex(Phone.DISPLAY_NAME)));

					/**
					 * Após selecionado o contato, não há mais necessidade de
					 * mostrar o botão "Selecionar contato na lista". Portanto,
					 * iremos esconder o botão definindo sua visibilidade para
					 * GONE.
					 */
					botaoSelecionarContato.setVisibility(View.GONE);

					/**
					 * Alteramos a TextView "cadastro_objeto_campo_pessoa"
					 * definindo em seu texto o nome do contato selecionado e
					 * definimos a visibilidade desta View para VISIBLE, para
					 * que ela fique visível para o usuário.
					 */
					campoNomePessoa.setText(objetoEmprestado.getContato()
							.getNome());
					campoNomePessoa.setVisibility(View.VISIBLE);
				}

				// Fechamos o Cursor
				cursor.close();
			}
			break;

		/**
		 * Trata o retorno vindo da captura de imagem através da câmera.
		 */
		case ID_RETORNO_TIRA_FOTO_OBJETO:
			if (data != null) {
				// Recupera o objeto Bundle recebido do aplicativo de fotos.
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					/*
					 * Recupera o objeto Bitmap com a foto capturada, recebido
					 * através do objeto Bundle.
					 */
					Bitmap foto = (Bitmap) bundle.get("data");
					/*
					 * Define a imagem no componente ImageView da tela, para ser
					 * apresentada ao usuário.
					 */
					campoFotoObjeto.setImageBitmap(foto);
					/*
					 * Converte o objeto Bitmap com a foto em um array de bytes
					 * (byte[]) e o injeta no objeto ObjetoEmprestado para ser
					 * persistido no banco de dados.
					 */
					objetoEmprestado.setFoto(Util.converteBitmapPraByteArray(
							foto, 70));
				}
			}
			break;

		}
	}

	/**
	 * Método responsável por habilitar/desabilitar os componentes da tela para
	 * definição do lembrete, caso o usuário os tenha ativado/desativado.
	 * 
	 * @param lembreteAtivo
	 */
	private void habilitaDesabilitaCamposLembrete(boolean lembreteAtivo) {
		// Caso o usuário tenha ativado o lembrete, habilita os campos
		if (lembreteAtivo) {
			campoLembreteData.setVisibility(View.VISIBLE);
			campoLembreteHora.setVisibility(View.VISIBLE);
			labelLembreteDataHora.setVisibility(View.VISIBLE);
			labelLembreteData.setVisibility(View.VISIBLE);
			labelLembreteHora.setVisibility(View.VISIBLE);
			// Caso o usuário tenha desativado o lembrete, desabilita os campos
		} else {
			campoLembreteData.setVisibility(View.GONE);
			campoLembreteHora.setVisibility(View.GONE);
			labelLembreteDataHora.setVisibility(View.GONE);
			labelLembreteData.setVisibility(View.GONE);
			labelLembreteHora.setVisibility(View.GONE);
		}
	}

}
