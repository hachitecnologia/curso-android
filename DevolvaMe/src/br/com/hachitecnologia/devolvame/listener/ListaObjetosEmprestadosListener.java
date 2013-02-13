package br.com.hachitecnologia.devolvame.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import br.com.hachitecnologia.devolvame.activity.CadastraObjetoEmprestadoActivity;
import br.com.hachitecnologia.devolvame.activity.ListaObjetosEmprestadosActivity;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;

public class ListaObjetosEmprestadosListener implements
		AdapterView.OnItemClickListener {

	private final ListaObjetosEmprestadosActivity activity;

	/**
	 * Nosso Construtor deverá receber como parâmetro a instância da Activity
	 * ListaObjetosEmprestadosActivity para ter acesso ao objeto ListView
	 * contendo a lista de objetos emprestados. Isto nos permitirá pegar na
	 * lista o item que foi selecionado pelo usuário.
	 * 
	 */
	public ListaObjetosEmprestadosListener(
			ListaObjetosEmprestadosActivity activity) {
		this.activity = activity;
	}

	/**
	 * Método que será disparado quando o usuário selecionar o item na ListView.
	 */
	public void onItemClick(AdapterView<?> adapterView, View view, int posicao,
			long idPosicao) {
		// Declarando a Intent que irá invocar a Activity
		// "CadastraObjetoEmprestadoActivity"
		Intent i = new Intent(activity, CadastraObjetoEmprestadoActivity.class);

		/**
		 * Passando o objeto selecionado pelo usuário, na ListView, como
		 * parâmetro para a Activity "CadastraObjetoEmprestadoActivity", para
		 * que esta possa editar as informações do registro selecionado.
		 */
		i.putExtra("itemSelecionadoParaEdicao", (ObjetoEmprestado) activity
				.getListaObjetosEmprestados().getItemAtPosition(posicao));

		// Invoca a Activity definida em nossa Intent
		activity.startActivity(i);
	}
}
