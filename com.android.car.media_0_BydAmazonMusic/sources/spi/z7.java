package spi;

import android.car.utils.Logs;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.blankj.utilcode.util.Utils;
import java.util.Timer;

/* JADX INFO: compiled from: NetworkUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z7 {
    public static y7 a;
    public static Timer b;
    public static volatile boolean c = a();
    public static volatile boolean d;

    static {
        new Handler(Looper.getMainLooper());
    }

    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Utils.getApp().getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return z;
        }
        boolean zHasCapability = networkCapabilities.hasCapability(16);
        if (!zHasCapability || !z) {
            Logs.e("NetworkUtils", "isNetUsableByApi:" + zHasCapability + ";isConn:" + z);
        }
        if (z) {
            return zHasCapability;
        }
        return false;
    }

    public static void b(boolean z) {
        if (z) {
            d = false;
        }
        y7 y7Var = a;
        if (y7Var != null) {
            y7Var.cancel();
            a = null;
        }
        Timer timer = b;
        if (timer != null) {
            timer.cancel();
            b = null;
        }
    }
}
