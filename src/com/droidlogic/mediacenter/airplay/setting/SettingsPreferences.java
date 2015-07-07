package com.droidlogic.mediacenter.airplay.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.util.Log;

import com.droidlogic.mediacenter.airplay.AirPlayService;
import com.droidlogic.mediacenter.R;

public class SettingsPreferences extends SettingsPreferenceFragment implements
        OnSharedPreferenceChangeListener, OnPreferenceChangeListener {
        private static final String TAG               = "SettingsPreferences";
        public static final String  KEY_START_SERVICE = "start_airplay";
        public static final String  KEY_BOOT_CFG      = "boot_airplay";
        private SwitchPreference    mStartServicePref;
        private SwitchPreference    mBootCfgPref;
        // private AirplayProxy        mAirplayProxy;

        @Override
        public void onCreate ( Bundle icicle ) {
            super.onCreate ( icicle );
            addPreferencesFromResource ( R.xml.settings_airplay );
            mStartServicePref = ( SwitchPreference ) findPreference ( KEY_START_SERVICE );
            mBootCfgPref = ( SwitchPreference ) findPreference ( KEY_BOOT_CFG );
            //mAirplayProxy = AirplayProxy.getInstance(getActivity());
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onStart() {
            super.onStart();
            getPreferenceScreen().getSharedPreferences()
            .registerOnSharedPreferenceChangeListener ( this );
            setPreferenceListeners ( this );
        }

        @Override
        public void onStop() {
            getPreferenceScreen().getSharedPreferences()
            .unregisterOnSharedPreferenceChangeListener ( this );
            setPreferenceListeners ( null );
            super.onStop();
        }

        @Override
        public boolean onPreferenceChange ( Preference preference, Object newValue ) {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public void onSharedPreferenceChanged ( SharedPreferences sharedPreferences,
                                                String key ) {
            Intent intent = new Intent ( getActivity(), AirPlayService.class );
            if ( key.equals ( KEY_START_SERVICE ) ) {
                if ( mStartServicePref.isChecked() ) {
                    Log.d ( TAG, "start airplay service" );
                    getActivity().startService ( intent );
                } else if ( !mBootCfgPref.isChecked() ) {
                    Log.d ( TAG, "stop airplay service" );
                    getActivity().stopService ( intent );
                }
            } else if ( key.equals ( KEY_BOOT_CFG ) ) {
                if ( mBootCfgPref.isChecked() ) {
                    Log.d ( TAG, "start airplay service=======" );
                    getActivity().startService ( intent );
                } else if ( !mStartServicePref.isChecked() ) {
                    Log.d ( TAG, "stop airplay service=======" );
                    getActivity().stopService ( intent );
                }
            }
        }

        private void setPreferenceListeners ( OnPreferenceChangeListener listener ) {
            mStartServicePref.setOnPreferenceChangeListener ( listener );
            mBootCfgPref.setOnPreferenceChangeListener ( listener );
        }
}
