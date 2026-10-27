package ts.auth;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AuthSdk {
    private static final String TAG = "AuthSdk";

    private native int DoLicAuthOnline(String str);

    private native int GetAuthStatus();

    private native int SetConfigDir(String str);

    static {
        Log.v(TAG, "static");
        System.loadLibrary("ts_auth_jni");
    }

    public AuthSdk() {
        Log.v(TAG, TAG);
    }

    public int Init(String configDir) {
        Log.v(TAG, "Init");
        return SetConfigDir(configDir);
    }

    public int getStatus() {
        Log.v(TAG, "getStatus");
        return GetAuthStatus();
    }

    public int onlineAuth(String vehicleID) {
        Log.v(TAG, "onlineAuth");
        return DoLicAuthOnline(vehicleID);
    }
}
