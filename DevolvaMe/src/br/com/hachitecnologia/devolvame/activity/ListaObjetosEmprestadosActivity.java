package br.com.hachitecnologia.devolvame.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.hachitecnologia.devolvame.R;
import br.com.hachitecnologia.devolvame.adapter.ListaObjetosEmprestadosAdapter;
import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.listener.ListaObjetosEmprestadosListener;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Telefonia;

public class ListaObjetosEmprestadosActivity extends Activity {

	// ID da opção "Ligar" do menu de contexto
	private static final int MENU_LIGAR = Menu.FIRST + 1;

	// ID da opção "Apagar" do menu de contexto
	private static final int MENU_APAGAR = Menu.FIRST;

	// ID da opção "Enviar SMS" do menu de contexto
	private static final int MENU_ENVIAR_SMS = Menu.FIRST + 2;

	/**
	 * Variável de instância do tipo ListView que fará referência à ListView do
	 * nosso Resource Layout e será manipulado via código para ser preenchido
	 * com os dados consultados no banco de dados.
	 */
	private ListView listaObjetosEmprestados;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Definimos o Resource Layout a ser controlado pela Activity
		setContentView(R.layout.activity_lista_objetos_emprestados);
		/**
		 * Fazemos a ligação entre o objeto "listaObjetosEmprestados" e a View
		 * ListView do nosso Resource Layout.
		 */
		listaObjetosEmprestados = (ListView) findViewById(R.id.lista_objetos_emprestados);

		// Registra o Context Menu para a ListView da nossa tela
		registerForContextMenu(listaObjetosEmprestados);
	}

	/**
	 * Retorna o objeto ListView já preenchido com a lista de objetos
	 * emprestados.
	 */
	public ListView getListaObjetosEmprestados() {
		return listaObjetosEmprestados;
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Chama o método que irá montar a ListView na tela.
		montaListView();
	}

	/**
	 * Cria o menu de contexto.
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		// Adiciona a opção "Apagar" ao Context Menu
		menu.add(0, MENU_APAGAR, 0, "Apagar");
		// Adiciona a opção "Ligar" ao Context Menu
		menu.add(0, MENU_LIGAR, 0, "Ligar");
		// Adiciona a opção "Enviar SMS" ao Context Menu
		menu.add(0, MENU_ENVIAR_SMS, 0, "Enviar SMS");
	}

	/**
	 * Monta a ListView com os dados a serem apresentados na tela.
	 */
	private void montaListView() {

		// Consulta os objetos cadastrados no banco de dados através do DAO
		ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(
				getApplicationContext());

		// Guarda os objetos consultados em uma List
		final List<ObjetoEmprestado> objetosEmprestados = dao.listaTodos();

		// Instancia o Adapter que irá adaptar os dados na ListView
		ArrayAdapter<ObjetoEmprestado> adapter = new ListaObjetosEmprestadosAdapter(
				this, android.R.layout.simple_list_item_1, objetosEmprestados);

		/**
		 * Define o Adapter que irá adaptar os dados consultados no banco de
		 * dados à nossa ListView do Resource Layout.
		 * 
		 * @param adapter
		 */
		listaObjetosEmprestados.setAdapter(adapter);

		/**
		 * Ativa o Listener que contém o evento a ser disparado ao clicar sobre
		 * um item da nossa ListView
		 */
		listaObjetosEmprestados
				.setOnItemClickListener(new ListaObjetosEmprestadosListener(
						this));
	}

	/**
	 * Remove o objeto selecionado.
	 */
	private void remove(ObjetoEmprestado objeto) {
		// Cria uma nova instância do DAO
		ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(
				getApplicationContext());

		// Remove o objeto do banco de dados
		dao.remove(objeto);

		/**
		 * Atualiaza a ListView para que o objeto removido não seja mais
		 * apresentado na tela.
		 */
		montaListView();
	}

	/**
	 * Dispara o evento da opção selecionada no menu de contexto.
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		/**
		 * A classe "AdapterView.AdapterContextMenuInfo" irá nos ajudar a
		 * extrair algumas informações do nosso Context Menu, como por exemplo a
		 * posição da ListView em que a opção do menu foi clicada pelo usuário.
		 * Precisaremos exatamente dessa informação para saber qual o item da
		 * ListView que o usuário deseja interagir.
		 */
		AdapterView.AdapterContextMenuInfo info;
		info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		// Trata a ação da opção "Apagar" do menu de contexto
		if (item.getItemId() == MENU_APAGAR) {

			// Obtemos o objeto que o usuário deseja remover através da sua
			// posição na ListView
			ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmprestados()
					.getItemAtPosition(info.position);

			// Chama o método que irá remover o objeto do banco de dados
			remove(objeto);

			/**
			 * Mostra uma mensagem na tela informando ao usuário que a operação
			 * foi realizada com sucesso
			 */
			Toast.makeText(getApplicationContext(),
					"Registro removido com sucesso!", Toast.LENGTH_LONG).show();

			/**
			 * Após tratar com sucesso o evento de uma opção do menu de
			 * contexto, o retorno deve ser sempre "true"
			 */
			return true;
		}

		// Trata a ação da opção "Ligar" do menu de contexto
		if (item.getItemId() == MENU_LIGAR) {

			// Obtemos o objeto selecionado pelo usuário, na ListView
			ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmprestados()
					.getItemAtPosition(info.position);

			// Efetua a chamada para o número de telefone cadastrado
			Intent i = new Intent(Intent.ACTION_CALL);
			i.setData(Uri.parse("tel:" + objeto.getContato().getTelefone()));
			startActivity(i);
		}

		// Trata a ação da opção "Enviar SMS" do menu de contexto
		if (item.getItemId() == MENU_ENVIAR_SMS) {

			// Obtemos o objeto selecionado pelo usuário na ListView
			ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmprestados()
					.getItemAtPosition(info.position);

			// Envia uma mensagem SMS de lembrete para o número de telefone
			// cadastrado
			String mensagem = "Olá, você pegou emprestado o meu objeto \""
					+ objeto.getObjeto()
					+ "\" e ainda não o devolveu. Por favor, devolva-me o quanto antes.";
			Telefonia.enviaSMS(objeto.getContato().getTelefone(), mensagem);
			Toast.makeText(getApplicationContext(), "Lembrete enviado.",
					Toast.LENGTH_LONG).show();
		}

		return super.onContextItemSelected(item);
	}

}
