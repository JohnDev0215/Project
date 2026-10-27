package C0;

import F0.AbstractC0227s0;
import F0.C0210j0;
import K.J0;
import O.InterfaceC0480k;
import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import androidx.compose.ui.node.Owner;
import g1.AbstractC1274a;
import java.util.Arrays;
import n3.C1945j;
import z.C2751b;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: C0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0114v extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f916m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f917n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f918p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f919q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0114v(int i7, int i10, Object obj, Object obj2, Object obj3) {
        super(2);
        this.f916m = i10;
        this.f917n = obj;
        this.f918p = obj2;
        this.f919q = obj3;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f916m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.o | 1);
                b0.b bVar = (b0.b) this.f918p;
                K k10 = (K) this.f919q;
                c0.a((g0.o) this.f917n, bVar, k10, (C0761q) obj, iZ);
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.o | 1);
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f919q;
                c0.c((f0) this.f918p, (g0.o) this.f917n, interfaceC2785n, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.o | 1);
                AbstractC0227s0.a((Owner) this.f917n, (C0210j0) this.f919q, (b0.b) this.f918p, (C0761q) obj, iZ3);
                break;
            case 3:
                ((Number) obj2).intValue();
                int iZ4 = C0735d.Z(this.o | 1);
                b0.b bVar2 = (b0.b) this.f918p;
                ((G.e0) this.f917n).e(this.f919q, bVar2, (C0761q) obj, iZ4);
                break;
            case 4:
                ((Number) obj2).intValue();
                int iZ5 = C0735d.Z(this.o | 1);
                O.K k11 = (O.K) this.f919q;
                b0.b bVar3 = (b0.b) this.f918p;
                K.X.e((g0.o) this.f917n, k11, bVar3, (C0761q) obj, iZ5);
                break;
            case 5:
                ((Number) obj2).intValue();
                Object[] objArr = (Object[]) this.f918p;
                Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
                int iZ6 = C0735d.Z(this.o | 1);
                ((J0) this.f917n).b(objArrCopyOf, (InterfaceC2782k) this.f919q, (C0761q) obj, iZ6);
                break;
            case 6:
                ((Number) obj2).intValue();
                int iZ7 = C0735d.Z(this.o | 1);
                g0.c cVar = (g0.c) this.f919q;
                b0.b bVar4 = (b0.b) this.f918p;
                AbstractC0904a.e((InterfaceC0480k) this.f917n, cVar, bVar4, (C0761q) obj, iZ7);
                break;
            case 7:
                ((Number) obj2).intValue();
                int iZ8 = C0735d.Z(this.o) | 1;
                Object obj3 = this.f917n;
                Object obj4 = this.f919q;
                ((b0.b) this.f918p).i(obj3, obj4, (C0761q) obj, iZ8);
                break;
            case 8:
                ((Number) obj2).intValue();
                int iZ9 = C0735d.Z(this.o | 1);
                b0.b bVar5 = (b0.b) this.f918p;
                ((d0.h) this.f917n).e(this.f919q, bVar5, (C0761q) obj, iZ9);
                break;
            case 9:
                ((Number) obj2).intValue();
                int iZ10 = C0735d.Z(this.o | 1);
                b0.b bVar6 = (b0.b) this.f918p;
                AbstractC1274a.g((InterfaceC2772a) this.f917n, (e1.t) this.f919q, bVar6, (C0761q) obj, iZ10);
                break;
            case 10:
                ((Number) obj2).intValue();
                int iZ11 = C0735d.Z(this.o | 1);
                b0.b bVar7 = (b0.b) this.f918p;
                h6.j.e((C1945j) this.f917n, (d0.h) this.f919q, bVar7, (C0761q) obj, iZ11);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ12 = C0735d.Z(this.o | 1);
                b0.b bVar8 = (b0.b) this.f918p;
                z.n.a((C2751b) this.f919q, (g0.o) this.f917n, bVar8, (C0761q) obj, iZ12);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0114v(Object obj, Object obj2, b0.b bVar, int i7, int i10) {
        super(2);
        this.f916m = i10;
        this.f917n = obj;
        this.f919q = obj2;
        this.f918p = bVar;
        this.o = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0114v(Object obj, Object obj2, Object obj3, int i7, int i10, boolean z6) {
        super(2);
        this.f916m = i10;
        this.f918p = obj;
        this.f917n = obj2;
        this.f919q = obj3;
        this.o = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0114v(C2751b c2751b, g0.o oVar, b0.b bVar, int i7) {
        super(2);
        this.f916m = 11;
        this.f919q = c2751b;
        this.f917n = oVar;
        this.f918p = bVar;
        this.o = i7;
    }
}
