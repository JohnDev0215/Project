package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: renamed from: d7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0643d7 {
    public static Z6 a = new C1693w6();
    public static ThreadLocal b = new ThreadLocal();
    public static ArrayList c = new ArrayList();

    public static C0971j1 a() {
        C0971j1 c0971j1;
        WeakReference weakReference = (WeakReference) b.get();
        if (weakReference != null && (c0971j1 = (C0971j1) weakReference.get()) != null) {
            return c0971j1;
        }
        C0971j1 c0971j12 = new C0971j1();
        b.set(new WeakReference(c0971j12));
        return c0971j12;
    }
}
