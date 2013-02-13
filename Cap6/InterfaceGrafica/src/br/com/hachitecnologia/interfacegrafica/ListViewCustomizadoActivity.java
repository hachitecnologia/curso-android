package br.com.hachitecnologia.interfacegrafica;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListViewCustomizadoActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Cria uma lista com os itens a serem mostrados na tela.
		 */
		List<ListViewCustomizadoItem> itens = new ArrayList<ListViewCustomizadoItem>();
		itens.add(new ListViewCustomizadoItem(
				"Trabalhando com interfaces gráficas", R.drawable.ic_launcher));
		itens.add(new ListViewCustomizadoItem("Sair", R.drawable.ic_launcher));

		/**
		 * Define o Adapter que irá mostrar os dados na ListView.
		 */
		setListAdapter(new ListViewCustomizadoAdapter(this, itens));
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