package tk.zeryter.p2pchat.android;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class P2PChatMain extends Activity {

    //static switches
    public static boolean running = false;

    //Global variables
    public static String versionName;

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        //The app is not running so we tell the system it is
        running = true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(tk.zeryter.p2pchat.android.R.menu.main, menu);
	return true;
    }

}

