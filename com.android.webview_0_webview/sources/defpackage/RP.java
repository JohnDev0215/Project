package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RP extends AbstractRunnableC0550bQ {
    public final /* synthetic */ AbstractRunnableC0550bQ u;
    public final /* synthetic */ SP v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RP(SP sp, AbstractRunnableC0550bQ abstractRunnableC0550bQ) {
        super(sp.u.q);
        this.v = sp;
        this.u = abstractRunnableC0550bQ;
    }

    @Override // defpackage.AbstractRunnableC0550bQ
    public void a() {
        this.v.u.c.postOnAnimation(this.u);
    }
}
