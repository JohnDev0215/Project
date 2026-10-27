package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SQ extends ID implements JD {
    public final /* synthetic */ UQ a;

    public SQ(UQ uq) {
        this.a = uq;
    }

    @Override // defpackage.JD
    public void b(int i, int i2) {
        k();
    }

    @Override // defpackage.ID, defpackage.JD
    public void c(int i, int i2) {
        k();
    }

    @Override // defpackage.ID, defpackage.JD
    public void e(int i, int i2) {
        k();
    }

    @Override // defpackage.ID, defpackage.JD
    public void f(int i, int i2) {
        k();
    }

    public final void k() {
        boolean zA = this.a.a();
        UQ uq = this.a;
        uq.d = uq.a.isScrollInProgress();
        if (zA != this.a.a()) {
            ((HP) this.a.c).c();
        }
    }
}
