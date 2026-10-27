package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OB implements Runnable {
    public final /* synthetic */ CharSequence s;
    public final /* synthetic */ int t;
    public final /* synthetic */ boolean u;
    public final /* synthetic */ QB v;

    public OB(QB qb, CharSequence charSequence, int i, boolean z) {
        this.v = qb;
        this.s = charSequence;
        this.t = i;
        this.u = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        QB qb = this.v;
        CharSequence charSequence = this.s;
        int i = this.t;
        int i2 = this.u ? qb.i | Integer.MIN_VALUE : 0;
        qb.i = 0;
        qb.e.Z(charSequence, i, false, i2);
    }
}
