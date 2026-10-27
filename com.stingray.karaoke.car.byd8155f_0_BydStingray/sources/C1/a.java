package C1;

import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.S;
import java.util.ArrayList;
import java.util.List;
import w8.C2607b;
import x1.AbstractC2663b;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable, io.sentry.backpressure.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f927n;

    public a(F1 f12) {
        this.l = 2;
        this.f926m = 0;
        this.f927n = f12;
    }

    @Override // io.sentry.backpressure.a
    public int a() {
        return this.f926m;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                AbstractC2663b abstractC2663b = (AbstractC2663b) ((C2607b) this.f927n).f23345m;
                if (abstractC2663b != null) {
                    abstractC2663b.g(this.f926m);
                }
                break;
            case 1:
                ArrayList arrayList = (ArrayList) this.f927n;
                int size = arrayList.size();
                int i7 = 0;
                if (this.f926m == 1) {
                    while (i7 < size) {
                        ((T1.g) arrayList.get(i7)).b();
                        i7++;
                    }
                } else {
                    while (i7 < size) {
                        ((T1.g) arrayList.get(i7)).a();
                        i7++;
                    }
                }
                break;
            default:
                boolean zF = E.f15834a.f();
                F1 f12 = (F1) this.f927n;
                if (zF) {
                    if (this.f926m > 0) {
                        f12.getLogger().k(EnumC1495p1.DEBUG, "Health check positive, reverting to normal sampling.", new Object[0]);
                    }
                    this.f926m = 0;
                } else {
                    int i10 = this.f926m;
                    if (i10 < 10) {
                        this.f926m = i10 + 1;
                        f12.getLogger().k(EnumC1495p1.DEBUG, "Health check negative, downsampling with a factor of %d", Integer.valueOf(this.f926m));
                    }
                }
                S executorService = f12.getExecutorService();
                if (!executorService.f()) {
                    executorService.r(this, 10000);
                }
                break;
        }
    }

    @Override // io.sentry.backpressure.a
    public void start() {
        S executorService = ((F1) this.f927n).getExecutorService();
        if (executorService.f()) {
            return;
        }
        executorService.r(this, 500);
    }

    public a(C2607b c2607b, int i7) {
        this.l = 0;
        this.f927n = c2607b;
        this.f926m = i7;
    }

    public a(List list, int i7, Throwable th) {
        this.l = 1;
        l9.l.F(list, "initCallbacks cannot be null");
        this.f927n = new ArrayList(list);
        this.f926m = i7;
    }
}
