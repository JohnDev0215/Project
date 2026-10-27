package F0;

import T.C0761q;
import android.graphics.Matrix;
import android.view.View;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class L extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final L f2047n = new L(2, 0);
    public static final L o = new L(2, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final L f2048p = new L(2, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final L f2049q = new L(2, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2050m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ L(int i7, int i10) {
        super(i7);
        this.f2050m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2050m) {
            case 0:
                L0.o oVar = (L0.o) obj2;
                L0.j jVar = ((L0.o) obj).d;
                L0.u uVar = L0.r.f3951n;
                Object objValueOf = jVar.l.get(uVar);
                if (objValueOf == null) {
                    objValueOf = Float.valueOf(0.0f);
                }
                float fFloatValue = ((Number) objValueOf).floatValue();
                Object objValueOf2 = oVar.d.l.get(uVar);
                if (objValueOf2 == null) {
                    objValueOf2 = Float.valueOf(0.0f);
                }
                break;
            case 1:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                }
                break;
            case 2:
                ((L0) obj).f2051a.getMatrix((Matrix) obj2);
                break;
            default:
                ((Matrix) obj2).set(((View) obj).getMatrix());
                break;
        }
        return m7.q.f19267a;
    }
}
