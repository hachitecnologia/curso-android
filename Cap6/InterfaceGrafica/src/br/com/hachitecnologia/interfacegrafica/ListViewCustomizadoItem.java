package br.com.hachitecnologia.interfacegrafica;

public class ListViewCustomizadoItem {

	// Nome da Activity a ser apresentado na ListView
	private String nomeActivity;

	// Imagem da Activity a ser apresentada na ListView
	private int imagemActivity;

	public ListViewCustomizadoItem(String nomeActivity, int imagemActivity) {
		this.nomeActivity = nomeActivity;
		this.imagemActivity = imagemActivity;
	}

	public String getNomeActivity() {
		return nomeActivity;
	}

	public int getImagemActivity() {
		return imagemActivity;
	}

}
