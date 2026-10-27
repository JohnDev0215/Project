package defpackage;

/* JADX INFO: renamed from: um, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1617um extends AbstractC1507sm {
    @Override // defpackage.AbstractC1507sm
    public void a(Object obj, int i, AbstractC0731el abstractC0731el) {
        ((C1562tm) obj).b((i << 3) | 2, abstractC0731el);
    }

    @Override // defpackage.AbstractC1507sm
    public Object b(Object obj) {
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) obj;
        C1562tm c1562tm = abstractC0065Dl.unknownFields;
        if (c1562tm != C1562tm.f) {
            return c1562tm;
        }
        C1562tm c1562tm2 = new C1562tm(0, new int[8], new Object[8], true);
        abstractC0065Dl.unknownFields = c1562tm2;
        return c1562tm2;
    }

    @Override // defpackage.AbstractC1507sm
    public Object d() {
        return new C1562tm(0, new int[8], new Object[8], true);
    }

    @Override // defpackage.AbstractC1507sm
    public void e(Object obj, Object obj2) {
        ((AbstractC0065Dl) obj).unknownFields = (C1562tm) obj2;
    }

    @Override // defpackage.AbstractC1507sm
    public boolean f(InterfaceC1123lm interfaceC1123lm) {
        return false;
    }
}
