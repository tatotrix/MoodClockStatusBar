package br.taiguara.moodclockstatusbar;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.graphics.Color;
import android.os.SystemClock;
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
		
		//Do a reflection in object and get the current instance
		findAndHookMethod("com.android.systemui.statusbar.policy.Clock", 
				lpparam.classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) 
            		throws Throwable {
            	
            	 prefs = new XSharedPreferences(PACKAGE_NAME, PREFERENCE);
            	
            	 //Get values in preference
            	 String texto = prefs.getString("pref_texto_relogio", "");
            	 int estilo = prefs.getInt("pref_estilo_fonte",0);
            	 int tamanho = prefs.getInt("pref_tamanho_fonte",10);
            	 Boolean posicao = prefs.getBoolean("pref_position", false);
            	 Boolean textoEsquerdo = prefs.getBoolean("pref_texto_esquerdo", false);
            	 String cor = prefs.getString("pref_color", "#FA8809");//color orange by default
            	 
            	 TextView tv = (TextView) param.thisObject; //Get current object
				 String text = tv.getText().toString();
				 
				//Concat a data/hour + text that user define.
				 if(textoEsquerdo)
					 tv.setText(texto + text); 
				 else
					 tv.setText(text + texto); 
					 
				 tv.setTextColor(Color.parseColor(cor)); // Define a cor que o usuário escolheu.
				 
				// Invert the clock in 180 degrees.
				 if(posicao)
					 tv.setRotation(180); 
				 else
					 tv.setRotation(0);
					 
				 tv.setTypeface(tv.getTypeface(), estilo); //Change font style
				 
				 tv.setTextSize(tamanho); //Change font size
				 
				 SystemClock.uptimeMillis();

            }//Final afterHookedMethod
            
            
		});// Final findAndHookMethod
		
		//SystemClock.uptimeMillis();
		
	}// Final handledLoadPackage
	
	
}
