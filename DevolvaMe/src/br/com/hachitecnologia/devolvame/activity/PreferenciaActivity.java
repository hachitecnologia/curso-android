package br.com.hachitecnologia.devolvame.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import br.com.hachitecnologia.devolvame.R;

public class PreferenciaActivity extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Define o arquivo de layout da tela de Preferências
		addPreferencesFromResource(R.xml.preferencia);
	}
	
}
