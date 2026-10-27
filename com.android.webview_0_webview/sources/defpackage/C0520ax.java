package defpackage;

/* JADX INFO: renamed from: ax, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0520ax extends AbstractC0687dx {
    public final long[] k;

    public C0520ax(C0632cx c0632cx, long[] jArr, C0799fx c0799fx) {
        super(c0632cx, c0799fx);
        this.k = jArr;
    }

    @Override // defpackage.Su
    public Object c() {
        r("ContentRemovedTask.removeContent");
        C0743ex c0743exP = p();
        if (c0743exP != null) {
            c0743exP.a.notifyViewsDisappeared(this.i.c().b, this.k);
        }
        return Boolean.TRUE;
    }
}
