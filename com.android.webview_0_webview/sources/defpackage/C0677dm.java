package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: dm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0677dm implements InterfaceC1178mm {
    public final InterfaceC0509am a;
    public final AbstractC1507sm b;
    public final boolean c;
    public final AbstractC1561tl d;

    public C0677dm(AbstractC1507sm abstractC1507sm, AbstractC1561tl abstractC1561tl, InterfaceC0509am interfaceC0509am) {
        this.b = abstractC1507sm;
        Objects.requireNonNull((C1616ul) abstractC1561tl);
        this.c = false;
        this.d = abstractC1561tl;
        this.a = interfaceC0509am;
    }

    @Override // defpackage.InterfaceC1178mm
    public final boolean a(Object obj) {
        Objects.requireNonNull((C1616ul) this.d);
        throw null;
    }

    @Override // defpackage.InterfaceC1178mm
    public void b(Object obj, Object obj2) {
        AbstractC1288om.C(this.b, obj, obj2);
    }

    @Override // defpackage.InterfaceC1178mm
    public void c(Object obj, InterfaceC1123lm interfaceC1123lm, C1506sl c1506sl) {
        AbstractC1507sm abstractC1507sm = this.b;
        AbstractC1561tl abstractC1561tl = this.d;
        abstractC1507sm.b(obj);
        Objects.requireNonNull((C1616ul) abstractC1561tl);
        throw null;
    }

    @Override // defpackage.InterfaceC1178mm
    public boolean d(Object obj, Object obj2) {
        Objects.requireNonNull((C1617um) this.b);
        C1562tm c1562tm = ((AbstractC0065Dl) obj).unknownFields;
        Objects.requireNonNull((C1617um) this.b);
        return c1562tm.equals(((AbstractC0065Dl) obj2).unknownFields);
    }

    @Override // defpackage.InterfaceC1178mm
    public int e(Object obj) {
        Objects.requireNonNull((C1617um) this.b);
        C1562tm c1562tm = ((AbstractC0065Dl) obj).unknownFields;
        int iC = c1562tm.d;
        if (iC == -1) {
            iC = 0;
            for (int i = 0; i < c1562tm.a; i++) {
                int i2 = c1562tm.b[i] >>> 3;
                AbstractC0731el abstractC0731el = (AbstractC0731el) c1562tm.c[i];
                iC += AbstractC1342pl.c(3, abstractC0731el) + AbstractC1342pl.y(2, i2) + (AbstractC1342pl.x(1) * 2);
            }
            c1562tm.d = iC;
        }
        return iC + 0;
    }

    @Override // defpackage.InterfaceC1178mm
    public Object f() {
        return ((AbstractC1891zl) ((AbstractC0065Dl) this.a).c(EnumC0047Cl.NEW_BUILDER, null, null)).a();
    }

    @Override // defpackage.InterfaceC1178mm
    public void g(Object obj, InterfaceC0192Km interfaceC0192Km) {
        Objects.requireNonNull((C1616ul) this.d);
        throw null;
    }

    @Override // defpackage.InterfaceC1178mm
    public void h(Object obj) {
        Objects.requireNonNull((C1617um) this.b);
        ((AbstractC0065Dl) obj).unknownFields.e = false;
        Objects.requireNonNull((C1616ul) this.d);
        throw null;
    }

    @Override // defpackage.InterfaceC1178mm
    public int i(Object obj) {
        Objects.requireNonNull((C1617um) this.b);
        return ((AbstractC0065Dl) obj).unknownFields.hashCode();
    }
}
