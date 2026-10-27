package E;

import C0.U;
import E0.H;
import T.X;
import h2.AbstractC1347d;
import java.util.ArrayList;
import l3.C1764u;
import n0.C1877f;
import n0.C1883l;
import p0.C2071b;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class s extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1404m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f1405n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f1406p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f1407q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(ArrayList arrayList, u uVar, boolean z6, X x8) {
        super(1);
        this.o = arrayList;
        this.f1406p = uVar;
        this.f1405n = z6;
        this.f1407q = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1404m) {
            case 0:
                U u4 = (U) obj;
                ArrayList arrayList = (ArrayList) this.o;
                int size = arrayList.size();
                int i7 = 0;
                while (true) {
                    u uVar = (u) this.f1406p;
                    boolean z6 = this.f1405n;
                    if (i7 >= size) {
                        if (uVar != null) {
                            uVar.l(u4, z6);
                        }
                        ((X) this.f1407q).getValue();
                        return m7.q.f19267a;
                    }
                    u uVar2 = (u) arrayList.get(i7);
                    if (uVar2 != uVar) {
                        uVar2.l(u4, z6);
                    }
                    i7++;
                }
                break;
            default:
                H h10 = (H) obj;
                h10.a();
                if (((Boolean) ((InterfaceC2772a) this.o).a()).booleanValue()) {
                    boolean z10 = this.f1405n;
                    C1883l c1883l = (C1883l) this.f1407q;
                    C1877f c1877f = (C1877f) this.f1406p;
                    if (z10) {
                        C2071b c2071b = h10.l;
                        long jS = c2071b.S();
                        h3.y yVar = c2071b.f20638m;
                        long jY = yVar.y();
                        yVar.m().m();
                        try {
                            ((C1764u) yVar.f15499m).T(-1.0f, 1.0f, jS);
                            p0.d.t(h10, c1877f, c1883l);
                        } finally {
                            AbstractC1347d.o(yVar, jY);
                        }
                    } else {
                        p0.d.t(h10, c1877f, c1883l);
                    }
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(InterfaceC2772a interfaceC2772a, boolean z6, C1877f c1877f, C1883l c1883l) {
        super(1);
        this.o = interfaceC2772a;
        this.f1405n = z6;
        this.f1406p = c1877f;
        this.f1407q = c1883l;
    }
}
