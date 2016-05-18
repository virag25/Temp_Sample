package common;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// Interface to save values in shared preferences and also for retreive values from sharedpreferences
public interface PreferenceManager {
	public SharedPreferences getPreferences();
	public Editor editPreferences();
	
	public void storeStringValueInPreferences(String key, String value);
	public String getStringValueFromPreferences(String key);
	
	public void storeBooleanValueInPreferences(String key, boolean value);
	public boolean getBooleanValueFromPreferences(String key);

	public void storeIntegerValueInPreferences(String key, int value);
	public int getIntegerValueFromPreferences(String key);
	
	public void storeFloatValueInPreferences(String key, float value);
	public float getFloatValueFromPreferences(String key);
}
