package br.com.hachitecnologia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MinhaQuintaActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Button botao = new Button(getApplicationContext());
        botao.setText("Abrir outra Activity");
        
        /**
         *  Definindo o evento a ser disparado ao clicar no botão. 
         *  Em nosso caso, o evento irá disparar outra Activity.
         */
        botao.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
				
			// Definindo na Intent a Activity que será chamada ao clicar no botão
			Intent i = new Intent(getApplicationContext(), MinhaSextaActivity.class);
				
			// Inicia uma Activity definindo o valor do Request Code para 1.
			startActivityForResult(i, 1);	
		}
	});
        
        setContentView(botao);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	// Recupera o parâmetro recebido
    	String parametroRecebido = data.getExtras().getString("ParametroRetorno");
    	/**
    	 *  Mostra o conteúdo do parâmetro recebido na saída de Log, 
    	 *  para ser visualizado no LogCat.
    	 */
    	Log.i("ParametroRetorno", parametroRecebido);
    }
}

