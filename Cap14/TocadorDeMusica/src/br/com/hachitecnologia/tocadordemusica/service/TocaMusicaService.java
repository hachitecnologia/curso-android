package br.com.hachitecnologia.tocadordemusica.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

public class TocaMusicaService extends Service {

	private MediaPlayer mediaPlayer;
	// Posição atual da música em milissegundos
	private int posicao = 0;
	// Música a ser tocada, localizada no diretório "assets"
	private static final String MUSICA = "born_to_be_wild.mp3";
	private IBinder binder = new TocaMusicaBinder(this);

	/**
	 * Método responsável por tocar a Música
	 */
	public void tocaMusica() {
		/*
		 * Se a música foi pausada, dizemos ao MediaPlayer que a música deve ser
		 * iniciada a partir da posição em que ela parou de tocar.
		 */
		if (posicao > 0)
			mediaPlayer.seekTo(posicao);
		/*
		 * Se a música NÃO foi pausada, dizemos o MediaPlayer para tocar a
		 * música a partir do início.
		 */
		else {
			mediaPlayer = new MediaPlayer();
			try {
				AssetFileDescriptor afd = getApplicationContext().getAssets()
						.openFd(MUSICA);
				mediaPlayer.setDataSource(afd.getFileDescriptor(),
						afd.getStartOffset(), afd.getLength());
				mediaPlayer.prepare();
			} catch (Exception e) {
				// TODO
			}
		}
		// Pede para o MediaPlayer tocar a música
		mediaPlayer.start();
	}

	/**
	 * Método responsável por parar a música
	 */
	public void paraMusica() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			// Ao parar a música, retorna para a sua posição inicial (0)
			posicao = 0;
		}
	}

	/**
	 * Método responsável por pausar a música
	 */
	public void pausaMusica() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			// Ao pausar a música, guarda a posição em que ela parou de tocar
			posicao = mediaPlayer.getCurrentPosition();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

}