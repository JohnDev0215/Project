package Q5;

import T.C0761q;
import com.stingray.drivemodedetector.R;
import g1.AbstractC1274a;
import z7.InterfaceC2772a;
import z7.InterfaceC2789r;

/* JADX INFO: loaded from: classes.dex */
public final class C implements InterfaceC2789r {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C f6284m = new C(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C f6285n = new C(1);
    public static final C o = new C(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C f6286p = new C(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C f6287q = new C(4);
    public final /* synthetic */ int l;

    public /* synthetic */ C(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2789r
    public final Object k(g0.o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, Object obj, Integer num2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                int iIntValue = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "$unused$var$");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(oVar) ? 4 : 2;
                }
                if ((iIntValue & 8195) == 8194 && c0761q.x()) {
                    c0761q.K();
                } else {
                    AbstractC1274a.i(oVar, c0761q, iIntValue & 14);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                int iIntValue2 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "$unused$var$");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(oVar) ? 4 : 2;
                }
                if ((iIntValue2 & 8195) == 8194 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    com.bumptech.glide.c.g(oVar, Integer.valueOf(R.drawable.ic_like), null, c0761q2, iIntValue2 & 14, 4);
                }
                break;
            case 2:
                C0761q c0761q3 = (C0761q) obj;
                int iIntValue3 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "$unused$var$");
                if ((iIntValue3 & 6) == 0) {
                    iIntValue3 |= c0761q3.f(oVar) ? 4 : 2;
                }
                if ((iIntValue3 & 8195) == 8194 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    com.bumptech.glide.c.g(oVar, Integer.valueOf(R.drawable.ic_history), null, c0761q3, iIntValue3 & 14, 4);
                }
                break;
            case 3:
                C0761q c0761q4 = (C0761q) obj;
                int iIntValue4 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "$unused$var$");
                if ((iIntValue4 & 6) == 0) {
                    iIntValue4 |= c0761q4.f(oVar) ? 4 : 2;
                }
                if ((iIntValue4 & 8195) == 8194 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    com.bumptech.glide.c.g(oVar, Integer.valueOf(R.drawable.ic_star), null, c0761q4, iIntValue4 & 14, 4);
                }
                break;
            default:
                C0761q c0761q5 = (C0761q) obj;
                int iIntValue5 = num2.intValue();
                A7.m.f(oVar, "tileModifier");
                A7.m.f(interfaceC2772a, "$unused$var$");
                if ((iIntValue5 & 6) == 0) {
                    iIntValue5 |= c0761q5.f(oVar) ? 4 : 2;
                }
                if ((iIntValue5 & 8195) == 8194 && c0761q5.x()) {
                    c0761q5.K();
                } else {
                    com.bumptech.glide.c.g(oVar, null, null, c0761q5, iIntValue5 & 14, 6);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
