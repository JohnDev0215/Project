package E0;

import D.AbstractC0143z;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: E0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0152b extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1608m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0153c f1609n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0152b(C0153c c0153c, int i7) {
        super(0);
        this.f1608m = i7;
        this.f1609n = c0153c;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1608m) {
            case 0:
                this.f1609n.J0();
                return m7.q.f19267a;
            default:
                C0153c c0153c = this.f1609n;
                g0.m mVar = c0153c.f1617y;
                A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.modifier.ModifierLocalConsumer");
                AbstractC0143z abstractC0143z = (AbstractC0143z) mVar;
                D.D d = (D.D) c0153c.E(D.Y.f1059a);
                if (A7.m.a(d, abstractC0143z.f1120a)) {
                    return m7.q.f19267a;
                }
                abstractC0143z.f1120a = d;
                throw null;
        }
    }
}
