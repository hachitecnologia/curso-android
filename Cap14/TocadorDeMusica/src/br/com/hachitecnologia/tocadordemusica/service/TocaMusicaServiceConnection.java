package br.com.hachitecnologia.tocadordemusica.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class TocaMusicaServiceConnection implements ServiceConnection {

	private TocaMusicaService tocaMusicaService;

	@Override
	public void onServiceConnected(ComponentName component, IBinder binder) {
		TocaMusicaBinder tocaMusicaBinder = (TocaMusicaBinder) binder;
		this.tocaMusicaService = tocaMusicaBinder.getTocaMusicaService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO
	}

	public TocaMusicaService getTocaMusicaService() {
		return tocaMusicaService;
	}

}