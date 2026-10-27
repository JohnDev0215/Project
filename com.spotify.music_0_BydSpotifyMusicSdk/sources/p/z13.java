package p;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class z13 implements Runnable {
    public final /* synthetic */ int c;
    public Object e;
    public final Object k;

    public /* synthetic */ z13(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.c) {
            case 0:
                synchronized (((i03) this.k).c) {
                    try {
                        Object obj = ((i03) this.k).d;
                        if (((dq1) obj) != null) {
                            ((dq1) obj).onSuccess(((wc0) this.e).c());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 1:
                i30.a((i30) this.k, (com.google.firebase.crashlytics.internal.settings.a) this.e);
                return;
            case 2:
                pl2 pl2Var = ((b7) this.e).c;
                if (pl2Var != null) {
                    pl2Var.a((vk2) this.k);
                    return;
                }
                b7 b7Var = (b7) this.e;
                vk2 vk2Var = (vk2) this.k;
                HashSet hashSet = b7Var.d;
                if (hashSet.size() < 50) {
                    hashSet.add(vk2Var);
                    return;
                }
                return;
            case 3:
                ((io.reactivex.rxjava3.internal.operators.single.f) this.k).e.onError((Throwable) this.e);
                return;
            case 4:
                ((io.reactivex.rxjava3.internal.operators.single.f) this.k).e.onSuccess(this.e);
                return;
            case 5:
                ((zp) ((yp) this.k)).B((kotlinx.coroutines.b) this.e);
                return;
            case 6:
                ((zp) ((yp) this.e)).B((kotlinx.coroutines.android.a) this.k);
                return;
            case 7:
                int i = 0;
                while (true) {
                    try {
                        ((Runnable) this.e).run();
                    } catch (Throwable th2) {
                        ux2.m(EmptyCoroutineContext.c, th2);
                    }
                    ba1 ba1Var = (ba1) this.k;
                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = ba1.f105p;
                    Runnable runnableK0 = ba1Var.K0();
                    if (runnableK0 == null) {
                        return;
                    }
                    this.e = runnableK0;
                    i++;
                    if (i >= 16 && ba1Var.k.I0()) {
                        ba1Var.k.G0(ba1Var, this);
                        return;
                    }
                    break;
                }
                break;
            default:
                vr2.k((c00) this.e).k(kotlin.a.b((Exception) this.k));
                return;
        }
    }

    public /* synthetic */ z13(Object obj, Object obj2, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }
}
