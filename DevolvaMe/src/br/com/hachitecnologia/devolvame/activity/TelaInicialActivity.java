package br.com.hachitecnologia.devolvame.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaInicialActivity extends ListActivity {

	// Opções que serão apresentadas na ListView da tela principal.
	private static final String[] OPCOES_DO_MENU = new String[] {
			"Emprestar objeto", "Listar objetos emprestados", "Sair" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Define um ArrayAdapter com as opções definidas no Array de String
		 * OPCOES_DO_MENU.
		 */
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, OPCOES_DO_MENU));
	}

	/**
	 * Inicia os eventos de acordo com a opção selecionada pelo usuário na
	 * ListView.
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		// Evento da primeira opção apresentada na ListView: Emprestar objeto
		case 0:
			startActivity(new Intent(getApplicationContext(),
					CadastraObjetoEmprestadoActivity.class));
			break;
		// Evento da segunda opção apresentada na ListView: Listar objetos
		// emprestados
		case 1:
			startActivity(new Intent(getApplicationContext(),
					ListaObjetosEmprestadosActivity.class));
			break;
		// Evento da terceira opção apresentada na ListView: Sair
		default:
			finish();
		}
	}

}
