package defpackage;

import android.content.Context;

/* JADX INFO: renamed from: Qf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0292Qf {
    public static C0292Qf b = new C0292Qf();
    public C0274Pf a = null;

    public static C0274Pf a(Context context) {
        C0274Pf c0274Pf;
        C0292Qf c0292Qf = b;
        synchronized (c0292Qf) {
            if (c0292Qf.a == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                c0292Qf.a = new C0274Pf(context);
            }
            c0274Pf = c0292Qf.a;
        }
        return c0274Pf;
    }
}
