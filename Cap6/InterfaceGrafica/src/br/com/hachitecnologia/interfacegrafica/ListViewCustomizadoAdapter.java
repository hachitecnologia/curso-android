package br.com.hachitecnologia.interfacegrafica;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewCustomizadoAdapter extends BaseAdapter {

	private Context contexto;

	// Lista dos itens a serem apresentados na tela
	private List<ListViewCustomizadoItem> itens;

	public ListViewCustomizadoAdapter(Context contexto,
			List<ListViewCustomizadoItem> itens) {
		this.contexto = contexto;
		this.itens = itens;
	}

	/**
	 * Quantidade de itens a serem apresentados na ListView.
	 */
	@Override
	public int getCount() {
		return itens.size();
	}

	/**
	 * Posição atual de um item.
	 */
	@Override
	public Object getItem(int posicao) {
		return itens.get(posicao);
	}

	/**
	 * ID de um item. Em nosso caso usaremos sua posição como ID.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Aqui é onde a mágica é feita: cada item da nossa lista de itens será
	 * adaptado para ser apresentado em uma linha da ListView. É aqui que
	 * montamos nossa View customizada.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Item a ser apresentado na posição atual da ListView.
		ListViewCustomizadoItem item = itens.get(position);

		// Criando uma instância de View a partir de um arquivo de layout.
		LayoutInflater inflater = (LayoutInflater) contexto
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_listview_customizado,
				null);

		// Imagem do item a ser apresentada na posição atual da ListView.
		ImageView imagem = (ImageView) view.findViewById(R.id.imagem_activity);
		imagem.setImageResource(item.getImagemActivity());

		// Texto do item a ser apresentado na posição atual da ListView.
		TextView texto = (TextView) view.findViewById(R.id.nome_activity);
		texto.setText(item.getNomeActivity());

		return view;
	}

}
