package A;

import T.C0773y;
import android.content.res.Configuration;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.List;
import p0.C2071b;
import y.C2716v;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class L0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f68m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f69n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ L0(T.X x8, int i7) {
        super(1);
        this.f68m = i7;
        this.f69n = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        T.X x8 = this.f69n;
        switch (this.f68m) {
            case 0:
                break;
            case 1:
                Configuration configuration = new Configuration((Configuration) obj);
                C0773y c0773y = AndroidCompositionLocals_androidKt.f11687a;
                x8.setValue(configuration);
                break;
            case 2:
                N.f fVar = (N.f) obj;
                x8.setValue(fVar.c ? fVar.f4253b : fVar.f4252a);
                break;
            case 3:
                List list = (List) obj;
                if (x8 != null) {
                    x8.setValue(list);
                }
                break;
            case 4:
                ((InterfaceC2782k) x8.getValue()).b(new m0.c(((m0.c) obj).f18880a));
                break;
            default:
                E0.H h10 = (E0.H) obj;
                h10.a();
                float fY = h10.y(((C2716v) x8.getValue()).f24013a);
                C2071b c2071b = h10.l;
                float fB = m0.f.b(c2071b.e()) - (fY / 2);
                p0.d.q(h10, ((C2716v) x8.getValue()).f24014b, h6.e.a(0.0f, fB), h6.e.a(m0.f.d(c2071b.e()), fB), fY, 0.0f, 496);
                break;
        }
        return qVar;
    }
}
