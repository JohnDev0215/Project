package defpackage;

/* JADX INFO: renamed from: xB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1753xB implements Runnable {
    public final /* synthetic */ CharSequence s;
    public final /* synthetic */ int t;
    public final /* synthetic */ QB u;

    public RunnableC1753xB(QB qb, CharSequence charSequence, int i) {
        this.u = qb;
        this.s = charSequence;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        QB qb = this.u;
        CharSequence charSequence = this.s;
        int i = this.t;
        qb.i = 0;
        qb.e.Z(charSequence, i, true, 0);
    }
}
