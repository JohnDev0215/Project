package F0;

import K.AbstractC0338f;
import N0.C0411f;
import T.C0735d;
import T.C0752l0;
import T.C0761q;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import e0.C1178r;
import java.util.Arrays;
import java.util.List;
import z.C2751b;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class W extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2144m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2145n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f2146p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(int i7, G.I i10, Object obj) {
        super(2);
        this.f2144m = 1;
        this.o = i10;
        this.f2145n = i7;
        this.f2146p = obj;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2144m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f2145n | 1);
                AndroidCompositionLocals_androidKt.a((AndroidComposeView) this.o, (b0.b) this.f2146p, (C0761q) obj, iZ);
                break;
            case 1:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    ((G.I) this.o).e(this.f2145n, this.f2146p, c0761q, 0);
                }
                break;
            case 2:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f2145n | 1);
                AbstractC0338f.a((C0411f) this.o, (List) this.f2146p, (C0761q) obj, iZ2);
                break;
            case 3:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f2145n | 1);
                K.X.c((O.K) this.o, (b0.b) this.f2146p, (C0761q) obj, iZ3);
                break;
            case 4:
                ((Number) obj2).intValue();
                int iZ4 = C0735d.Z(this.f2145n | 1);
                b0.b bVar = (b0.b) this.f2146p;
                Q.c1.a((N0.L) this.o, bVar, (C0761q) obj, iZ4);
                break;
            case 5:
                ((Number) obj2).intValue();
                C0752l0[] c0752l0Arr = (C0752l0[]) this.o;
                C0752l0[] c0752l0Arr2 = (C0752l0[]) Arrays.copyOf(c0752l0Arr, c0752l0Arr.length);
                int iZ5 = C0735d.Z(this.f2145n | 1);
                C0735d.b(c0752l0Arr2, (InterfaceC2785n) this.f2146p, (C0761q) obj, iZ5);
                break;
            case 6:
                ((Number) obj2).intValue();
                int iZ6 = C0735d.Z(this.f2145n | 1);
                C0735d.a((C0752l0) this.o, (InterfaceC2785n) this.f2146p, (C0761q) obj, iZ6);
                break;
            case 7:
                ((Number) obj2).intValue();
                int iZ7 = C0735d.Z(this.f2145n) | 1;
                ((b0.b) this.f2146p).h(this.o, (C0761q) obj, iZ7);
                break;
            case 8:
                ((Number) obj2).intValue();
                int iZ8 = C0735d.Z(this.f2145n | 1);
                h6.i.b((C1178r) this.o, (List) this.f2146p, (C0761q) obj, iZ8);
                break;
            case 9:
                ((Number) obj2).intValue();
                int iZ9 = C0735d.Z(this.f2145n | 1);
                b0.b bVar2 = (b0.b) this.f2146p;
                h6.j.g((d0.h) this.o, bVar2, (C0761q) obj, iZ9);
                break;
            case 10:
                ((Number) obj2).intValue();
                int iZ10 = C0735d.Z(this.f2145n | 1);
                ((x.x0) this.o).a(this.f2146p, (C0761q) obj, iZ10);
                break;
            case 11:
                ((Number) obj2).intValue();
                h6.j.b(C0735d.Z(this.f2145n | 1), (C0761q) obj, (g0.o) this.o, (InterfaceC2782k) this.f2146p);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ11 = C0735d.Z(this.f2145n | 1);
                ((z.h) this.o).a((C2751b) this.f2146p, (C0761q) obj, iZ11);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(b0.b bVar, Object obj, int i7) {
        super(2);
        this.f2144m = 7;
        this.f2146p = bVar;
        this.o = obj;
        this.f2145n = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ W(Object obj, Object obj2, int i7, int i10) {
        super(2);
        this.f2144m = i10;
        this.o = obj;
        this.f2146p = obj2;
        this.f2145n = i7;
    }
}
