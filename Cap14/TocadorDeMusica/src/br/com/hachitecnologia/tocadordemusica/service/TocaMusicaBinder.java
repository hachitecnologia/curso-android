package br.com.hachitecnologia.tocadordemusica.service;

import android.os.Binder;

public class TocaMusicaBinder extends Binder {

	private TocaMusicaService tocaMusicaService;

	public TocaMusicaBinder(TocaMusicaService tocaMusicaService) {
		this.tocaMusicaService = tocaMusicaService;
	}

	/**
	 * Método responsável por permitir o acesso ao Service.
	 * 
	 * @return
	 */
	public TocaMusicaService getTocaMusicaService() {
		return tocaMusicaService;
	}

}
