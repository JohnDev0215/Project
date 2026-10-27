package i8;

import h8.m;
import h8.n;
import o8.C2057b;
import o8.C2061f;

/* JADX INFO: renamed from: i8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1419d implements m {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1421f f15790m;

    public /* synthetic */ C1419d(C1421f c1421f, int i7) {
        this.l = i7;
        this.f15790m = c1421f;
    }

    private final void a(C2061f c2061f, Object obj) {
    }

    private final void b(C2061f c2061f, t8.f fVar) {
    }

    private final void c(C2061f c2061f, t8.f fVar) {
    }

    private final void d(C2061f c2061f, t8.f fVar) {
    }

    private final void e() {
    }

    private final void f() {
    }

    private final void g() {
    }

    private final void h(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
    }

    private final void n(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
    }

    private final void o(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
    }

    @Override // h8.m
    public final void i() {
        int i7 = this.l;
    }

    @Override // h8.m
    public final void j(C2061f c2061f, t8.f fVar) {
        int i7 = this.l;
    }

    @Override // h8.m
    public final n k(C2061f c2061f) {
        switch (this.l) {
            case 0:
                String strB = c2061f.b();
                if ("d1".equals(strB)) {
                    return new C1418c(this, 0);
                }
                if ("d2".equals(strB)) {
                    return new C1418c(this, 1);
                }
                return null;
            case 1:
                if ("b".equals(c2061f.b())) {
                    return new C1418c(this, 2);
                }
                return null;
            default:
                String strB2 = c2061f.b();
                if ("data".equals(strB2) || "filePartClassNames".equals(strB2)) {
                    return new C1420e(this, 0);
                }
                if ("strings".equals(strB2)) {
                    return new C1420e(this, 1);
                }
                return null;
        }
    }

    @Override // h8.m
    public final m l(C2057b c2057b, C2061f c2061f) {
        switch (this.l) {
        }
        return null;
    }

    @Override // h8.m
    public final void m(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
        int i7 = this.l;
    }

    @Override // h8.m
    public final void q(C2061f c2061f, Object obj) {
        switch (this.l) {
            case 0:
                String strB = c2061f.b();
                boolean zEquals = "k".equals(strB);
                C1421f c1421f = this.f15790m;
                if (zEquals) {
                    if (obj instanceof Integer) {
                        EnumC1416a enumC1416a = (EnumC1416a) EnumC1416a.f15776m.get((Integer) obj);
                        if (enumC1416a == null) {
                            enumC1416a = EnumC1416a.UNKNOWN;
                        }
                        c1421f.f15798g = enumC1416a;
                    }
                    break;
                } else if ("mv".equals(strB)) {
                    if (obj instanceof int[]) {
                        c1421f.f15794a = (int[]) obj;
                    }
                    break;
                } else if ("xs".equals(strB)) {
                    if (obj instanceof String) {
                        String str = (String) obj;
                        if (!str.isEmpty()) {
                            c1421f.f15795b = str;
                        }
                    }
                    break;
                } else if ("xi".equals(strB)) {
                    if (obj instanceof Integer) {
                        c1421f.c = ((Integer) obj).intValue();
                    }
                    break;
                } else if ("pn".equals(strB) && (obj instanceof String) && !((String) obj).isEmpty()) {
                    c1421f.getClass();
                    break;
                }
                break;
            case 1:
                break;
            default:
                String strB2 = c2061f.b();
                boolean zEquals2 = "version".equals(strB2);
                C1421f c1421f2 = this.f15790m;
                if (!zEquals2) {
                    if ("multifileClassName".equals(strB2)) {
                        c1421f2.f15795b = obj instanceof String ? (String) obj : null;
                    }
                } else if (obj instanceof int[]) {
                    c1421f2.f15794a = (int[]) obj;
                }
                break;
        }
    }
}
