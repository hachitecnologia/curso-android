package br.com.hachitecnologia.tocadordemusica.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.hachitecnologia.tocadordemusica.R;
import br.com.hachitecnologia.tocadordemusica.service.TocaMusicaServiceConnection;

public class TocadorDeMusicaActivity extends Activity {

	private Button botaoTocaMusica;
	private Button botaoParaMusica;
	private Button botaoPausaMusica;

	private Intent intent;
	private TocaMusicaServiceConnection connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tocador_de_musica);

		// Inicia o Service
		iniciaService();
		// Conecta ao Service
		conectaAoService();

		botaoTocaMusica = (Button) findViewById(R.id.botao_toca_musica);
		botaoParaMusica = (Button) findViewById(R.id.botao_para_musica);
		botaoPausaMusica = (Button) findViewById(R.id.botao_pausa_musica);

		// Evento do botão "Tocar"
		botaoTocaMusica.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Pede para o Service tocar a música
				connection.getTocaMusicaService().tocaMusica();
				// Desativa o botão "Tocar" e ativa os demais
				ativaEDesativaBotoesDoTocador(true);
			}
		});

		// Evento do botão "Parar"
		botaoParaMusica.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Pede para o Service parar a música
				connection.getTocaMusicaService().paraMusica();
				// Ativa o botão "Tocar" e desativa os demais
				ativaEDesativaBotoesDoTocador(false);
			}
		});

		// Evento do botão "Pause"
		botaoPausaMusica.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Pede para o Service pausar a música
				connection.getTocaMusicaService().pausaMusica();
				// Ativa o botão "Tocar" e desativa os demais
				ativaEDesativaBotoesDoTocador(false);
			}
		});

	}

	/**
	 * Método responsável por iniciar o Service
	 */
	private void iniciaService() {
		intent = new Intent("br.com.hachitecnologia.action.TOCAR_MUSICA");
		startService(intent);
	}

	/**
	 * Método responsável por conectar ao Service
	 */
	private void conectaAoService() {
		connection = new TocaMusicaServiceConnection();
		bindService(intent, connection, 0);
	}

	/**
	 * Método que irá ativar/desativar os botões do Tocador de música.
	 * 
	 * @param musicaEstaTocando
	 */
	private void ativaEDesativaBotoesDoTocador(boolean musicaEstaTocando) {
		botaoTocaMusica.setEnabled(!musicaEstaTocando);
		botaoParaMusica.setEnabled(musicaEstaTocando);
		botaoPausaMusica.setEnabled(musicaEstaTocando);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Desconecta do Service quando a Activity é destruída
		unbindService(connection);
	}

}
