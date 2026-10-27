package D8;

import n7.AbstractC1985B;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1252m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f1253n;
    public final /* synthetic */ r o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(p pVar, r rVar, int i7) {
        super(0);
        this.f1252m = i7;
        this.f1253n = pVar;
        this.o = rVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1252m) {
            case 0:
                return AbstractC1985B.q(this.f1253n.f1257a.keySet(), this.o.o());
            default:
                return AbstractC1985B.q(this.f1253n.f1258b.keySet(), this.o.p());
        }
    }
}
