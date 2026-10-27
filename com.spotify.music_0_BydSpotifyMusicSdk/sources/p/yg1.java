package p;

import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public final class yg1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ wg1 e;
    public final /* synthetic */ wg1 k;

    public /* synthetic */ yg1(wg1 wg1Var, wg1 wg1Var2, int i) {
        this.c = i;
        this.k = wg1Var;
        this.e = wg1Var2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        wg1 wg1Var = this.k;
        wg1 wg1Var2 = this.e;
        switch (i) {
            case 0:
                jg1 jg1Var = (jg1) ((bh1) wg1Var.a).m.remove(wg1Var2.a());
                if (jg1Var != null) {
                    jg1Var.d.a().unlinkToDeath(jg1Var, 0);
                }
                break;
            default:
                IBinder iBinderA = wg1Var2.a();
                jg1 jg1Var2 = (jg1) ((bh1) wg1Var.a).m.remove(iBinderA);
                if (jg1Var2 != null) {
                    iBinderA.unlinkToDeath(jg1Var2, 0);
                }
                break;
        }
    }
}
