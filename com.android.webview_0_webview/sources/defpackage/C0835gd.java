package defpackage;

import android.os.Looper;

/* JADX INFO: renamed from: gd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0835gd {
    public final HandlerC0779fd a;
    public volatile Object b;
    public volatile C0668dd c;

    public C0835gd(Looper looper, Object obj, String str) {
        this.a = new HandlerC0779fd(this, looper);
        Cif.g(obj, "Listener must not be null");
        this.b = obj;
        Cif.e(str);
        this.c = new C0668dd(obj, str);
    }
}
