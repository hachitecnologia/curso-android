package br.com.hachitecnologia.devolvame.activity;

import br.com.hachitecnologia.devolvame.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaInicialActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final String[] OPCOES_DO_MENU = new String[] {
				getString(R.string.opcao_emprestar_objeto), 
				getString(R.string.opcao_listar_objetos_emprestados), 
				getString(R.string.opcao_preferencias),
				getString(R.string.opcao_sair) };

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
			startActivity(new Intent(
					"br.com.hachitecnologia.devolvame.action.CADASTRA_OBJETO"));
			break;
		// Evento da segunda opção apresentada na ListView: Listar objetos
		// emprestados
		case 1:
			startActivity(new Intent(
					"br.com.hachitecnologia.devolvame.action.LISTA_OBJETOS"));
			break;
		// Evento da terceira opção apresentada na ListView: Preferências
		case 2:
			startActivity(new Intent(
					"br.com.hachitecnologia.devolvame.action.PREFERENCIAS"));
			break;
		// Evento da terceira opção apresentada na ListView: Sair
		default:
			finish();
		}
	}

}
