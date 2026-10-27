package p;

import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g71 implements zd0 {
    public static final e71 f;
    public static final e71 g;
    public final HashMap a;
    public final HashMap b;
    public final d71 c;
    public boolean d;
    public static final d71 e = new d71(0);
    public static final f71 h = new f71();

    /* JADX WARN: Type inference failed for: r0v1, types: [p.e71] */
    /* JADX WARN: Type inference failed for: r0v2, types: [p.e71] */
    static {
        final int i = 0;
        f = new rs2() { // from class: p.e71
            @Override // p.yd0
            public final void a(Object obj, Object obj2) {
                switch (i) {
                    case 0:
                        ((ss2) obj2).d((String) obj);
                        break;
                    default:
                        ((ss2) obj2).e(((Boolean) obj).booleanValue());
                        break;
                }
            }
        };
        final int i2 = 1;
        g = new rs2() { // from class: p.e71
            @Override // p.yd0
            public final void a(Object obj, Object obj2) {
                switch (i2) {
                    case 0:
                        ((ss2) obj2).d((String) obj);
                        break;
                    default:
                        ((ss2) obj2).e(((Boolean) obj).booleanValue());
                        break;
                }
            }
        };
    }

    public g71() {
        HashMap map = new HashMap();
        this.a = map;
        HashMap map2 = new HashMap();
        this.b = map2;
        this.c = e;
        this.d = false;
        map2.put(String.class, f);
        map.remove(String.class);
        map2.put(Boolean.class, g);
        map.remove(Boolean.class);
        map2.put(Date.class, h);
        map.remove(Date.class);
    }

    public final zd0 a(Class cls, yo1 yo1Var) {
        this.a.put(cls, yo1Var);
        this.b.remove(cls);
        return this;
    }
}
