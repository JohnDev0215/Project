package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class EJ extends AbstractC1706wJ {
    public BJ b;
    public C1816yJ c;

    @Override // defpackage.AbstractC1706wJ
    public final void a(WI wi, int i) {
        wi.c(16, i);
        wi.c(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            wi.i(this.b, i + 8, false);
        } else {
            if (i2 != 1) {
                return;
            }
            wi.i(this.c, i + 8, false);
        }
    }
}
