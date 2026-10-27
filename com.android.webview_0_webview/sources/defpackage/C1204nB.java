package defpackage;

/* JADX INFO: renamed from: nB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1204nB extends AbstractC1381qN {
    public final String a;
    public final int b;

    public C1204nB(String str, int i) {
        this.a = str;
        this.b = i;
    }

    @Override // defpackage.InterfaceC1326pN
    public String a() {
        return this.a;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public boolean d() {
        return this.b == 0;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public boolean isEnabled() {
        int i = this.b;
        return i == 2 || i == 0;
    }
}
