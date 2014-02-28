package tk.zeryter.p2pchat.android.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import tk.zeryter.p2pchat.android.R;

/**
 * Owen Holloway, Zeryt. 9:14 PM, 28/02/14
 */
public class Prefrences extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("p2pchat","adding Pref from Res");
        addPreferencesFromResource(R.xml.preferences);
    }
}
