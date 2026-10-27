package androidx.media;

import b.q.c;
import b.z.a;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static c read(a aVar) {
        c cVar = new c();
        cVar.f3000a = aVar.p(cVar.f3000a, 1);
        cVar.f3001b = aVar.p(cVar.f3001b, 2);
        cVar.f3002c = aVar.p(cVar.f3002c, 3);
        cVar.f3003d = aVar.p(cVar.f3003d, 4);
        return cVar;
    }

    public static void write(c cVar, a aVar) {
        aVar.x(false, false);
        aVar.F(cVar.f3000a, 1);
        aVar.F(cVar.f3001b, 2);
        aVar.F(cVar.f3002c, 3);
        aVar.F(cVar.f3003d, 4);
    }
}
