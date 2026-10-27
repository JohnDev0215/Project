package defpackage;

import android.net.ConnectivityManager;

/* JADX INFO: renamed from: gs, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0850gs implements InterfaceC0521ay {
    public final ConnectivityManager a;
    public boolean b;
    public InterfaceC0794fs c;

    public C0850gs() {
        C0682ds c0682ds = new C0682ds();
        this.a = (ConnectivityManager) Is.a.getSystemService("connectivity");
        this.c = c0682ds;
    }
}
