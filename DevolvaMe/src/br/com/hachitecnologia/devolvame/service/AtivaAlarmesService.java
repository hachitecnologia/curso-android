package br.com.hachitecnologia.devolvame.service;

import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Alarme;

public class AtivaAlarmesService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(
				getApplicationContext());
		// Consulta todos os registros que precisam ter o Alarme ativado
		List<ObjetoEmprestado> objetos = dao
				.consultaObjetosComAlarmeASerDisparado();

		// Ativa o Alarme para cada registro retornado na consulta
		for (ObjetoEmprestado objeto : objetos) {
			Alarme.defineAlarme(objeto, getApplicationContext());
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
