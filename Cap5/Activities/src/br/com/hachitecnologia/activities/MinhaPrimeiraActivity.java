package br.com.hachitecnologia.activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MinhaPrimeiraActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minha_primeira);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_minha_primeira, menu);
		return true;
	}

}
