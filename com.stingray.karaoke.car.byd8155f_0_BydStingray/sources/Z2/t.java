package z2;

import e2.C1208p;
import h2.AbstractC1343A;
import k3.X0;
import n2.C1907g;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import o2.C2025b;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m2.b f24376m;

    public /* synthetic */ t(m2.b bVar, int i7, long j3) {
        this.l = 1;
        this.f24376m = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        m2.b bVar = this.f24376m;
        int i7 = this.l;
        bVar.getClass();
        switch (i7) {
            case 0:
                int i10 = AbstractC1343A.f15195a;
                o2.e eVar = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l.f19776s;
                eVar.Z(eVar.Y(), 1016, new C2025b(24));
                break;
            case 1:
                int i11 = AbstractC1343A.f15195a;
                o2.e eVar2 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l.f19776s;
                eVar2.Z(eVar2.W((C2492C) eVar2.o.f2768q), 1018, new C2025b(9));
                break;
            case 2:
                int i12 = AbstractC1343A.f15195a;
                o2.e eVar3 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l.f19776s;
                eVar3.Z(eVar3.W((C2492C) eVar3.o.f2768q), 1021, new C2025b(15));
                break;
            case 3:
                int i13 = AbstractC1343A.f15195a;
                o2.e eVar4 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l.f19776s;
                eVar4.Z(eVar4.Y(), 1030, new X0(29));
                break;
            case 4:
                int i14 = AbstractC1343A.f15195a;
                C1923x c1923x = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l;
                c1923x.getClass();
                o2.e eVar5 = c1923x.f19776s;
                eVar5.Z(eVar5.Y(), 1015, new C2025b(22));
                break;
            case 5:
                int i15 = AbstractC1343A.f15195a;
                C1923x c1923x2 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l;
                c1923x2.getClass();
                o2.e eVar6 = c1923x2.f19776s;
                eVar6.Z(eVar6.Y(), 1017, new C2025b(18));
                break;
            default:
                int i16 = AbstractC1343A.f15195a;
                o2.e eVar7 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l.f19776s;
                eVar7.Z(eVar7.Y(), 1019, new C2025b(8));
                break;
        }
    }

    public /* synthetic */ t(m2.b bVar, long j3, int i7) {
        this.l = 2;
        this.f24376m = bVar;
    }

    public /* synthetic */ t(m2.b bVar, C1208p c1208p, C1907g c1907g) {
        this.l = 5;
        this.f24376m = bVar;
    }

    public /* synthetic */ t(m2.b bVar, Object obj, int i7) {
        this.l = i7;
        this.f24376m = bVar;
    }

    public /* synthetic */ t(m2.b bVar, String str, long j3, long j10) {
        this.l = 0;
        this.f24376m = bVar;
    }
}
