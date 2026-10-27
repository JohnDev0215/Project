package g1;

import F0.RunnableC0238y;
import android.os.Handler;
import android.os.Looper;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g1.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1284k extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f14730m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1285l f14731n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1284k(C1285l c1285l, int i7) {
        super(1);
        this.f14730m = i7;
        this.f14731n = c1285l;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f14730m) {
            case 0:
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj;
                if (A7.m.a(Looper.myLooper(), Looper.getMainLooper())) {
                    interfaceC2772a.a();
                } else {
                    C1285l c1285l = this.f14731n;
                    Handler handler = c1285l.f14732m;
                    if (handler == null) {
                        handler = new Handler(Looper.getMainLooper());
                        c1285l.f14732m = handler;
                    }
                    handler.post(new RunnableC0238y(interfaceC2772a, 2));
                }
                break;
            default:
                this.f14731n.o = true;
                break;
        }
        return m7.q.f19267a;
    }
}
