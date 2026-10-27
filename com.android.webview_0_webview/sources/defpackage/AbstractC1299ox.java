package defpackage;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: ox, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1299ox {
    public static final WeakHashMap a = new WeakHashMap();
    public static final Object b = new Object();
    public static Context c;

    public static Context a(Context context) {
        C1244nx c1244nx;
        if (context instanceof C1244nx) {
            return context;
        }
        synchronized (b) {
            WeakReference weakReference = (WeakReference) a.get(context);
            c1244nx = weakReference == null ? null : (C1244nx) weakReference.get();
            if (c1244nx == null) {
                c1244nx = new C1244nx(context);
                a.put(context, new WeakReference(c1244nx));
            }
        }
        return c1244nx;
    }
}
