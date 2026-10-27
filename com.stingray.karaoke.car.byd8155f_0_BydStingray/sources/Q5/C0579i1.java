package Q5;

import F0.C0221p;
import H7.InterfaceC0321f;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import i6.C1415d;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.i1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0579i1 implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6881m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6882n;
    public final /* synthetic */ C1415d o;

    public C0579i1(int i7, int i10, int i11, C1415d c1415d) {
        this.l = i7;
        this.f6881m = i10;
        this.f6882n = i11;
        this.o = c1415d;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            String strX = AbstractC2673a.X(this.l, c0761q);
            String strX2 = AbstractC2673a.X(this.f6881m, c0761q);
            String strX3 = AbstractC2673a.X(this.f6882n, c0761q);
            String strX4 = AbstractC2673a.X(R.string.Global_Cancel, c0761q);
            c0761q.Q(-396649267);
            C1415d c1415d = this.o;
            boolean zH = c0761q.h(c1415d);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new C0221p(0, c1415d, C1415d.class, "onClear", "onClear()V", 0, 12);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            com.bumptech.glide.d.d(str, strX, strX2, strX3, strX4, (InterfaceC2772a) ((InterfaceC0321f) objG), null, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }
}
