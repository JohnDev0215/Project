package e1;

import F0.RunnableC0238y;
import android.os.Handler;
import android.os.Looper;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class k extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13791m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f13792n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(x xVar, int i7) {
        super(1);
        this.f13791m = i7;
        this.f13792n = xVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13791m) {
            case 0:
                C0.r rVarJ = ((C0.r) obj).j();
                A7.m.c(rVarJ);
                this.f13792n.l(rVarJ);
                break;
            case 1:
                a1.j jVar = new a1.j(((a1.j) obj).f11037a);
                x xVar = this.f13792n;
                xVar.m2setPopupContentSizefhxjrPA(jVar);
                xVar.m();
                break;
            default:
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj;
                x xVar2 = this.f13792n;
                Handler handler = xVar2.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    interfaceC2772a.a();
                } else {
                    Handler handler2 = xVar2.getHandler();
                    if (handler2 != null) {
                        handler2.post(new RunnableC0238y(interfaceC2772a, 1));
                    }
                }
                break;
        }
        return m7.q.f19267a;
    }
}
