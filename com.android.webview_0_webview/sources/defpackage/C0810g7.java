package defpackage;

/* JADX INFO: renamed from: g7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0810g7 extends AbstractC0475a7 {
    public C0866h7 a;

    public C0810g7(C0866h7 c0866h7) {
        this.a = c0866h7;
    }

    @Override // defpackage.AbstractC0475a7, defpackage.Y6
    public void c(Z6 z6) {
        C0866h7 c0866h7 = this.a;
        if (c0866h7.d0) {
            return;
        }
        c0866h7.F();
        this.a.d0 = true;
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
        C0866h7 c0866h7 = this.a;
        int i = c0866h7.c0 - 1;
        c0866h7.c0 = i;
        if (i == 0) {
            c0866h7.d0 = false;
            c0866h7.m();
        }
        z6.v(this);
    }
}
