package mvvm.bsv.vn.basemvvm.utils;

import android.util.Log;

import mvvm.bsv.vn.basemvvm.BuildConfig;

public class LogUtil {
    //TAG Class
    static String TAG = "BSV";


    // --------------------------------------------------------
    // Show Log info
    synchronized public static void log(String content) {

        if (BuildConfig.DEBUG) {
            Log.i("", "--------------------------------------");
            Log.i(TAG, "--- "+content);
        }
    }

}
