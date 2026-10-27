package U5;

import C0.C0102i;
import E.C0147d;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: U5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0804a implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C0804a f9096m = new C0804a(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0804a f9097n = new C0804a(1);
    public final /* synthetic */ int l;

    public /* synthetic */ C0804a(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                g0.o oVar = (g0.o) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(oVar, "popupModifier");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(oVar) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    h6.q.a(l9.l.W(R.drawable.high_score, c0761q, 0), null, androidx.compose.foundation.layout.c.o(oVar, m6.c.f19252a.k0(c0761q)), null, C0102i.f903b, 0.0f, null, c0761q, 24624, 104);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f((C0147d) obj, "$this$item");
                if ((iIntValue2 & 17) == 16 && c0761q2.x()) {
                    c0761q2.K();
                }
                break;
        }
        return m7.q.f19267a;
    }
}
