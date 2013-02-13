package br.com.hachitecnologia.interfacegrafica;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends ListActivity {

	private static final String[] OPCOES_DO_MENU = new String[] {
			"Trabalhando com interfaces gráficas", "Sair" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Define o Adapter que irá mostrar os dados na ListView.
		 */
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, OPCOES_DO_MENU));
	}

	/**
	 * Evento a ser disparado ao clicar em um dos itens da ListView.
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this, InterfaceGraficaActivity.class));
			break;
		default:
			finish();
		}
	}

}
