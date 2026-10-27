package h;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.d0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class m extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15110m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f15111n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(n nVar, int i7) {
        super(0);
        this.f15110m = i7;
        this.f15111n = nVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f15110m) {
            case 0:
                n nVar = this.f15111n;
                return new d0(nVar.getApplication(), nVar, nVar.getIntent() != null ? nVar.getIntent().getExtras() : null);
            case 1:
                this.f15111n.reportFullyDrawn();
                return m7.q.f19267a;
            case 2:
                n nVar2 = this.f15111n;
                return new o(nVar2.f15119q, new m(nVar2, 1));
            default:
                n nVar3 = this.f15111n;
                x xVar = new x(new RunnableC1332d(nVar3, 1));
                if (Build.VERSION.SDK_INT >= 33) {
                    if (A7.m.a(Looper.myLooper(), Looper.getMainLooper())) {
                        nVar3.l.M0(new C1335g(xVar, nVar3));
                    } else {
                        new Handler(Looper.getMainLooper()).post(new A2.m(nVar3, 3, xVar));
                    }
                }
                return xVar;
        }
    }
}
