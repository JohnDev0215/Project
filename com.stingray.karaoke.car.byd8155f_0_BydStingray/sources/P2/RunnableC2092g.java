package p2;

import e2.C1208p;
import h2.AbstractC1343A;
import l8.C1789g;
import n2.C1907g;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import o2.C2025b;

/* JADX INFO: renamed from: p2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC2092g implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1789g f20711m;

    public /* synthetic */ RunnableC2092g(C1789g c1789g, int i7, long j3, long j10) {
        this.l = 3;
        this.f20711m = c1789g;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1789g c1789g = this.f20711m;
        int i7 = this.l;
        c1789g.getClass();
        switch (i7) {
            case 0:
                int i10 = AbstractC1343A.f15195a;
                C1923x c1923x = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l;
                c1923x.getClass();
                o2.e eVar = c1923x.f19776s;
                eVar.Z(eVar.Y(), 1007, new o2.d(13));
                break;
            case 1:
                int i11 = AbstractC1343A.f15195a;
                o2.e eVar2 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar2.Z(eVar2.Y(), 1010, new C2025b(3));
                break;
            case 2:
                int i12 = AbstractC1343A.f15195a;
                o2.e eVar3 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar3.Z(eVar3.Y(), 1032, new o2.d(10));
                break;
            case 3:
                int i13 = AbstractC1343A.f15195a;
                o2.e eVar4 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar4.Z(eVar4.Y(), 1011, new C2025b(0));
                break;
            case 4:
                int i14 = AbstractC1343A.f15195a;
                o2.e eVar5 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar5.Z(eVar5.Y(), 1031, new C2025b(19));
                break;
            case 5:
                int i15 = AbstractC1343A.f15195a;
                o2.e eVar6 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar6.Z(eVar6.Y(), 1029, new C2025b(23));
                break;
            case 6:
                int i16 = AbstractC1343A.f15195a;
                o2.e eVar7 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar7.Z(eVar7.Y(), 1014, new C2025b(26));
                break;
            case 7:
                int i17 = AbstractC1343A.f15195a;
                C1923x c1923x2 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l;
                c1923x2.getClass();
                o2.e eVar8 = c1923x2.f19776s;
                eVar8.Z(eVar8.Y(), 1009, new C2025b(21));
                break;
            case 8:
                int i18 = AbstractC1343A.f15195a;
                o2.e eVar9 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar9.Z(eVar9.Y(), 1008, new C2025b(5));
                break;
            default:
                int i19 = AbstractC1343A.f15195a;
                o2.e eVar10 = ((SurfaceHolderCallbackC1920u) c1789g.f18861m).l.f19776s;
                eVar10.Z(eVar10.Y(), 1012, new o2.d(23));
                break;
        }
    }

    public /* synthetic */ RunnableC2092g(C1789g c1789g, long j3) {
        this.l = 1;
        this.f20711m = c1789g;
    }

    public /* synthetic */ RunnableC2092g(C1789g c1789g, C1208p c1208p, C1907g c1907g) {
        this.l = 7;
        this.f20711m = c1789g;
    }

    public /* synthetic */ RunnableC2092g(C1789g c1789g, Object obj, int i7) {
        this.l = i7;
        this.f20711m = c1789g;
    }

    public /* synthetic */ RunnableC2092g(C1789g c1789g, String str, long j3, long j10) {
        this.l = 8;
        this.f20711m = c1789g;
    }
}
