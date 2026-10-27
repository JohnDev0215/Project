package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class V1 {
    public static String d;
    public static T1 g;
    public final Context a;
    public final NotificationManager b;
    public static final Object c = new Object();
    public static Set e = new HashSet();
    public static final Object f = new Object();

    public V1(Context context) {
        this.a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    public final void a(U1 u1) {
        synchronized (f) {
            if (g == null) {
                g = new T1(this.a.getApplicationContext());
            }
            g.c.obtainMessage(0, u1).sendToTarget();
        }
    }
}
