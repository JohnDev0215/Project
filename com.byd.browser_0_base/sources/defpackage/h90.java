package defpackage;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: DeviceConditions.java */
/* JADX INFO: loaded from: classes.dex */
public final class h90 {
    public final boolean a;
    public final int b;
    public final boolean c;
    public final int d;
    public final boolean e;

    public h90(int i, int i2, boolean z, boolean z2, boolean z3) {
        this.d = 0;
        this.a = z;
        this.b = i;
        this.c = z2;
        this.d = i2;
        this.e = z3;
    }

    public static h90 a(Context context) {
        Intent intentG = xz.g(context, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentG == null) {
            return new h90();
        }
        int intExtra = intentG.getIntExtra("status", -1);
        boolean z = intExtra == 2 || intExtra == 5;
        int intExtra2 = intentG.getIntExtra("scale", -1);
        int iRound = intExtra2 == 0 ? 0 : Math.round((intentG.getIntExtra("level", -1) * 100) / intExtra2);
        int iB = b(context);
        boolean zIsPowerSaveMode = ((PowerManager) context.getSystemService("power")).isPowerSaveMode();
        boolean zIsActiveNetworkMetered = ((ConnectivityManager) context.getSystemService("connectivity")).isActiveNetworkMetered();
        c(context);
        return new h90(iRound, iB, z, zIsPowerSaveMode, zIsActiveNetworkMetered);
    }

    public static int b(Context context) {
        int i;
        NetworkChangeNotifier networkChangeNotifier = NetworkChangeNotifier.f;
        int currentConnectionType = networkChangeNotifier != null ? networkChangeNotifier.getCurrentConnectionType() : 6;
        if (currentConnectionType == 6) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    i = 2;
                } else if (type == 0) {
                    i = 3;
                } else {
                    currentConnectionType = 7;
                    if (type != 7) {
                        return 0;
                    }
                }
                return i;
            }
        }
        return currentConnectionType;
    }

    public static boolean c(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager == null || keyguardManager.isKeyguardLocked()) {
            return false;
        }
        return ((PowerManager) context.getSystemService("power")).isInteractive();
    }

    public h90() {
        this.d = 0;
        this.c = true;
        this.e = true;
    }
}
