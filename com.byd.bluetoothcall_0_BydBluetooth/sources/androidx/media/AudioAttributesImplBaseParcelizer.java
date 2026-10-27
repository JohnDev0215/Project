package androidx.media;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static c read(androidx.versionedparcelable.a aVar) {
        c cVar = new c();
        cVar.f1585a = aVar.p(cVar.f1585a, 1);
        cVar.f1586b = aVar.p(cVar.f1586b, 2);
        cVar.f1587c = aVar.p(cVar.f1587c, 3);
        cVar.f1588d = aVar.p(cVar.f1588d, 4);
        return cVar;
    }

    public static void write(c cVar, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.F(cVar.f1585a, 1);
        aVar.F(cVar.f1586b, 2);
        aVar.F(cVar.f1587c, 3);
        aVar.F(cVar.f1588d, 4);
    }
}
