package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IB implements Runnable {
    public final /* synthetic */ QB s;

    public IB(QB qb) {
        this.s = qb;
    }

    @Override // java.lang.Runnable
    public void run() {
        QB qb = this.s;
        qb.c();
        while (true) {
            C1478sB c1478sB = (C1478sB) qb.h.poll();
            if (c1478sB == null) {
                return;
            }
            if (!c1478sB.a()) {
                qb.i(c1478sB);
            }
        }
    }
}
