package d8;

import C0.T;
import E0.AbstractC0156f;
import c8.C1036a;
import k0.C1590b;
import k0.C1591c;
import l0.C1727r;
import n3.C1945j;
import o3.C2042o;
import o8.C2058c;
import y.C2693N;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1134u extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13623m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f13624n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1134u(Object obj, int i7, Object obj2) {
        super(0);
        this.f13623m = i7;
        this.f13624n = obj;
        this.o = obj2;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13623m) {
            case 0:
                V7.b bVar = ((C1036a) ((N0.q) this.f13624n).f4399m).f12412b;
                C2058c c2058c = ((C1135v) this.o).o.f8767q;
                bVar.getClass();
                A7.m.f(c2058c, "packageFqName");
                break;
            case 1:
                ((C1590b) this.f13624n).f17612A.b((C1591c) this.o);
                break;
            case 2:
                ((A7.z) this.f13624n).l = ((C1727r) this.o).H0();
                break;
            case 3:
                ((C2042o) this.f13624n).e((C1945j) this.o, false);
                break;
            case 4:
                ((U8.p) this.f13624n).p(this.o);
                break;
            default:
                ((A7.z) this.f13624n).l = AbstractC0156f.i((C2693N) this.o, T.f869a);
                break;
        }
        return m7.q.f19267a;
    }
}
