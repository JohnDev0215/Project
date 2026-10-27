package defpackage;

import android.content.SharedPreferences;
import defpackage.xz;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class y52 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        SharedPreferences sharedPreferences = xz.a.a;
        int i = sharedPreferences.getInt("android_restore_status", 0);
        if (i != 5) {
            oj2.U(i, 5, "Android.RestoreResult");
            sharedPreferences.edit().putInt("android_restore_status", 5).apply();
        }
    }
}
