package U5;

import T.G;
import X5.K;
import n3.C1934I;
import t6.C2443d;
import v6.C2556d;
import v6.C2559g;
import x.N;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements InterfaceC2782k {
    public final /* synthetic */ int l;

    public /* synthetic */ i(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                N n10 = (N) obj;
                A7.m.f(n10, "$this$keyframes");
                n10.f23440a = 400;
                n10.a(Float.valueOf(1.3f), 25);
                n10.a(Float.valueOf(1.0f), 400);
                return m7.q.f19267a;
            case 1:
                C1934I c1934i = (C1934I) obj;
                A7.m.f(c1934i, "$this$navigate");
                c1934i.c = true;
                return m7.q.f19267a;
            case 2:
                C1934I c1934i2 = (C1934I) obj;
                A7.m.f(c1934i2, "$this$navigate");
                c1934i2.c = true;
                return m7.q.f19267a;
            case 3:
                C1934I c1934i3 = (C1934I) obj;
                A7.m.f(c1934i3, "$this$navigate");
                c1934i3.c = true;
                return m7.q.f19267a;
            case 4:
                ((C1934I) obj).f19833b = true;
                return m7.q.f19267a;
            case 5:
                A7.m.f((G) obj, "$this$DisposableEffect");
                return new K();
            case 6:
                A7.m.f((j6.a) obj, "it");
                return m7.q.f19267a;
            case 7:
                A7.m.f((String) obj, "it");
                return m7.q.f19267a;
            case 8:
                A7.m.f((String) obj, "it");
                return m7.q.f19267a;
            case 9:
                Integer num = (Integer) obj;
                num.intValue();
                return num;
            case 10:
                C2443d c2443d = (C2443d) obj;
                A7.m.f(c2443d, "it");
                return c2443d.c;
            case 11:
                C2559g c2559g = (C2559g) obj;
                A7.m.f(c2559g, "it");
                return c2559g.a();
            default:
                C2556d c2556d = (C2556d) obj;
                A7.m.f(c2556d, "it");
                return String.valueOf(c2556d.l);
        }
    }
}
