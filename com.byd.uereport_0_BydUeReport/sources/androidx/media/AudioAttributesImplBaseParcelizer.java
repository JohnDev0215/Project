package androidx.media;

import b1.c;
import m1.a;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static c read(a aVar) {
        c cVar = new c();
        cVar.f2029a = aVar.p(cVar.f2029a, 1);
        cVar.f2030b = aVar.p(cVar.f2030b, 2);
        cVar.f2031c = aVar.p(cVar.f2031c, 3);
        cVar.f2032d = aVar.p(cVar.f2032d, 4);
        return cVar;
    }

    public static void write(c cVar, a aVar) {
        aVar.x(false, false);
        aVar.F(cVar.f2029a, 1);
        aVar.F(cVar.f2030b, 2);
        aVar.F(cVar.f2031c, 3);
        aVar.F(cVar.f2032d, 4);
    }
}
