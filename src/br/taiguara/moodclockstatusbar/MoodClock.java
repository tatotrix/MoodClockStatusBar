package br.taiguara.moodclockstatusbar;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.graphics.Color;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MoodClock implements IXposedHookLoadPackage {
	
	public static final String PREFERENCE = "moodclockprefs";
	private static final String PACKAGE_NAME = MoodClock.class.getPackage().getName();
	private XSharedPreferences prefs;
	
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.android.systemui"))
			return;
		
		//Faz uma reflection no objeto e captura a intancia atual
		findAndHookMethod("com.android.systemui.statusbar.policy.Clock", 
				lpparam.classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) 
            		throws Throwable {
            	
            	 prefs = new XSharedPreferences(PACKAGE_NAME, PREFERENCE);
            	
            	 //Obtem os valores gravados na preferencia
            	 String texto = prefs.getString("pref_texto_relogio", "");
            	 int estilo = prefs.getInt("pref_estilo_fonte",0);
            	 int tamanho = prefs.getInt("pref_tamanho_fonte",10);
            	 Boolean hide = prefs.getBoolean("pref_hide", false);
            	 Boolean textoEsquerdo = prefs.getBoolean("pref_texto_esquerdo", false);
            	 String cor = prefs.getString("pref_color", "#FA8809");//color laranja como default
            	 
            	 TextView tv = (TextView) param.thisObject; //Obtem o abjeto atual
				 String text = tv.getText().toString();
				 
				 if(textoEsquerdo)
					 tv.setText(texto + text); //Concatena a data/hora + o texto que o usuário definir.
				 else
					 tv.setText(text + texto); //Concatena a data/hora + o texto que o usuário definir.
					 
				 tv.setTextColor(Color.parseColor(cor)); // Define a cor que o usuário escolheu.
				 tv.setRotation(hide ? 180 : 0); // Inverte o relogio em 180 graus.
				 tv.setTypeface(tv.getTypeface(), estilo); // Alterar tipo da fonte.
				 tv.setTextSize(tamanho);
				 
				 //SystemClock.uptimeMillis();

            }//Final afterHookedMethod
            
            
            
		});// Final findAndHookMethod
		
		//SystemClock.uptimeMillis();
		
	}// Final handledLoadPackage
	
	
	
}
