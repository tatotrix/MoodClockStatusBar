package br.taiguara.moodclock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import br.taiguara.smileyclock.R;
import net.margaritov.preference.colorpicker.ColorPickerPreference;

@SuppressLint({ "WorldReadableFiles", "DefaultLocale" })
public class MoodClockActivity extends PreferenceActivity {
	
	public static final String PREFERENCE = "clockprefs";
	private EditTextPreference etpRelogio;
	private ColorPickerPreference cor; 
	private SwitchPreference chkHide;
	private ListPreference lstEstilo; //Estilo a fonte
	private ListPreference lstTamanho; //Tamanho a fonte
	private SwitchPreference chkTextoEsquerdo;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getPreferenceManager().setSharedPreferencesMode(MODE_WORLD_READABLE);
		addPreferencesFromResource(R.xml.preferences);
		

			
		etpRelogio = (EditTextPreference) findPreference("pref_texto_relogio");
		etpRelogio.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putString(preference.getKey(), (String) newValue);
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		
		lstEstilo = (ListPreference) findPreference("pref_estilo_fonte");
		lstEstilo.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putInt(preference.getKey(), Integer.parseInt(newValue.toString()));
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		
		lstTamanho = (ListPreference) findPreference("pref_tamanho_fonte");
		lstTamanho.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Toast.makeText(getApplicationContext(), "Valor: " + Integer.parseInt(newValue.toString()) , Toast.LENGTH_LONG).show();
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putInt(preference.getKey(), Integer.parseInt(newValue.toString()));
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		
		cor = (ColorPickerPreference) findPreference("pref_color");
		cor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Converte de inteiro para hex
				String cor = "#" + Integer.toHexString((int) newValue).toUpperCase().substring(2);
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putString(preference.getKey(), cor);
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		chkTextoEsquerdo = (SwitchPreference) findPreference("pref_texto_esquerdo");
		chkTextoEsquerdo.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putBoolean(preference.getKey(), (boolean) newValue);
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		
		chkHide = (SwitchPreference) findPreference("pref_hide");
		chkHide.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				
				SharedPreferences mPrefs = getSharedPreferences(PREFERENCE, Context.MODE_WORLD_READABLE);
				
				//Gravando as preferencias
			     Editor editor = mPrefs.edit();
			     editor.putBoolean(preference.getKey(), (boolean) newValue);
			     editor.commit();
				
				//Toast.makeText(getApplicationContext(), "Reinicie o celular para que as alterações funcionem!", Toast.LENGTH_LONG).show();
				return true;
			}
		});// Finaliza Evento Change do chk
		
		
	}// Finaliza OnCreated
}// Finaliza a classe Activity