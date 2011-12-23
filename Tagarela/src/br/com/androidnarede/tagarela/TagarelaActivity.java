package br.com.androidnarede.tagarela;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Aplicação que sintetiza texto em voz utilizando 
 * o poderoso mecanismo Text-to-Speech (TTS) 
 * de Android.
 * 
 * @author ramonrabello
 */
public class TagarelaActivity extends Activity implements OnInitListener {
	
	// classe responsável por tratar a conversão
	// de texto para fala (Text-toSpeech)
	private TextToSpeech tts;
	
	private EditText texto;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        texto = (EditText) findViewById(R.id.texto);
        tts = new TextToSpeech(this, this);
    }
    
    /**
     * Método que é chamado quando o usuário
     * clica no botão para sintetizar a voz.
     * 
     * @param v
     */
    public void falar(View v){
    	String textoInformado = texto.getText().toString(); 
    	tts.speak(textoInformado, TextToSpeech.QUEUE_FLUSH, null);
    	tts.synthesizeToFile(textoInformado, null, "/sdcard/androidnarede.wav");
    }

    /**
     * Chamado quando o mecanismo
     * TTS estiver sido inicializado.
     */
	@Override
	public void onInit(int status) {
		
		if (status == TextToSpeech.SUCCESS){
			tts.setLanguage(Locale.getDefault());
		} else {
			Toast.makeText(this,"Ops! O mecanismo de TTS não está instalado :(", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
    public void onDestroy() {
        
		// finalizando e desalocando o recurso
		
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }

}