package I;

import A7.o;
import i.C1395b;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2900m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f2901n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i7, Object obj, boolean z6) {
        super(0);
        this.f2900m = i7;
        this.o = obj;
        this.f2901n = z6;
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [A7.j, z7.a] */
    /* JADX WARN: Type inference failed for: r1v9, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f2900m) {
            case 0:
                ((InterfaceC2782k) this.o).b(Boolean.valueOf(!this.f2901n));
                break;
            case 1:
                C1395b c1395b = (C1395b) this.o;
                c1395b.f15131a = this.f2901n;
                ?? r12 = c1395b.c;
                if (r12 != 0) {
                    r12.a();
                }
                break;
            default:
                if (this.f2901n) {
                    ((o) this.o).a();
                }
                break;
        }
        return q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public b(InterfaceC2772a interfaceC2772a, boolean z6) {
        super(0);
        this.f2900m = 2;
        this.f2901n = z6;
        this.o = (o) interfaceC2772a;
    }
}
