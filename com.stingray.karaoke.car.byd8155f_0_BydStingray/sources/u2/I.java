package u2;

import android.os.Bundle;
import android.os.Parcelable;
import c5.InterfaceC1027d;
import d5.AbstractC1095q;
import d5.C1078G;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import x.InterfaceC2612A;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class I implements InterfaceC1027d, h2.g, InterfaceC2612A {
    public final /* synthetic */ int l;

    public /* synthetic */ I(int i7) {
        this.l = i7;
    }

    @Override // x.InterfaceC2612A
    public float a(float f10) {
        float f11;
        float f12;
        switch (this.l) {
            case 6:
                if (f10 < 0.36363637f) {
                    return 7.5625f * f10 * f10;
                }
                if (f10 < 0.72727275f) {
                    float f13 = f10 - 0.54545456f;
                    f11 = 7.5625f * f13 * f13;
                    f12 = 0.75f;
                } else if (f10 < 0.90909094f) {
                    float f14 = f10 - 0.8181818f;
                    f11 = 7.5625f * f14 * f14;
                    f12 = 0.9375f;
                } else {
                    float f15 = f10 - 0.95454544f;
                    f11 = 7.5625f * f15 * f15;
                    f12 = 0.984375f;
                }
                return f11 + f12;
            default:
                return f10;
        }
    }

    @Override // h2.g
    public void accept(Object obj) {
        switch (this.l) {
            case 1:
                ((Y) obj).f22767b.a();
                break;
            default:
                ((ExecutorService) obj).shutdown();
                break;
        }
    }

    @Override // c5.InterfaceC1027d
    public Object apply(Object obj) {
        switch (this.l) {
            case 0:
                return d5.I.w(AbstractC1095q.x(new I(2), ((InterfaceC2490A) obj).j().f22882b));
            case 1:
            default:
                n0 n0Var = (n0) obj;
                n0Var.getClass();
                Bundle bundle = new Bundle();
                d5.d0 d0Var = n0Var.f22882b;
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(d0Var.o);
                C1078G c1078gListIterator = d0Var.listIterator(0);
                while (c1078gListIterator.hasNext()) {
                    arrayList.add(((e2.g0) c1078gListIterator.next()).d());
                }
                bundle.putParcelableArrayList(n0.f22880e, arrayList);
                return bundle;
            case 2:
                return Integer.valueOf(((e2.g0) obj).c);
            case 3:
                return Long.valueOf(((Y2.a) obj).f10630b);
            case 4:
                return Long.valueOf(((Y2.a) obj).c);
        }
    }
}
