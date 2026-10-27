package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class QP extends AbstractRunnableC0550bQ {
    public final /* synthetic */ SP u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QP(SP sp) {
        super(sp.u.q);
        this.u = sp;
    }

    @Override // defpackage.AbstractRunnableC0550bQ
    public void a() {
        VP vp = this.u.u;
        vp.c.removeView(vp.l);
        this.u.u.c.invalidate();
        VP vp2 = this.u.u;
        if (vp2.g) {
            vp2.d.run();
            this.u.u.g = false;
        }
        VP vp3 = this.u.u;
        VP vp4 = vp3.j;
        if (vp4 != null) {
            vp4.f();
            vp3.j = null;
        }
    }
}
