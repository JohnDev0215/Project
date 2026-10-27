package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: renamed from: Cg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0042Cg {
    public static SharedPreferences a;

    public static SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (a == null) {
                a = (SharedPreferences) AbstractC0329Sg.a(new CallableC0024Bg(context));
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }
}
