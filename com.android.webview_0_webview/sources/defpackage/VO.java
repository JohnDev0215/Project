package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VO extends Su {
    public final int h;
    public final /* synthetic */ XO i;

    public VO(XO xo, int i) {
        this.i = xo;
        this.h = i;
    }

    @Override // defpackage.Su
    public Object c() {
        return this.i.d(this.h);
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        QO qo = (QO) obj;
        if (this.i.c.get(this.h) == null) {
            return;
        }
        XO xo = this.i;
        int i = this.h;
        xo.b(i, qo);
        xo.c.remove(i);
    }
}
