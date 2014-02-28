package tk.zeryter.p2pchat.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import tk.zeryter.p2pchat.android.fragments.MessageView;
import tk.zeryter.p2pchat.android.fragments.Prefrences;

public class P2PChatMain extends Activity {

    //static switches
    public static boolean running = false;

    //Global variables
    public static String versionName;
    public static P2PChatMain mainActivity;

    //Fragments
    public static Fragment messageViewFragment = new MessageView();
    public static Fragment preferencesFragment = new Prefrences();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Log.i("p2pchat","Build: " + versionName);

        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragmentView,messageViewFragment,"messageViewFragment").commit();
        currentFragmentTag = "messageViewFragment";

        mainActivity = this;
    }

    @Override
    protected void onStart() {
        super.onStart();

        //The app is not running so we tell the system it is
        running = true;

    }

    //The overflow menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(tk.zeryter.p2pchat.android.R.menu.main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d("p2pchat","preferences selected");
                setFragment(preferencesFragment, "preferencesFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String currentFragmentTag;

    public void setFragment(Fragment fragment, String tag) {
        getFragmentManager().beginTransaction().add(R.id.fragmentView,fragment,tag).addToBackStack(currentFragmentTag).commit();
        currentFragmentTag = tag;
    }

}

