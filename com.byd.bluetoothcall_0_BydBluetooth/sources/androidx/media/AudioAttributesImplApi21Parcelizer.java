package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static b read(androidx.versionedparcelable.a aVar) {
        b bVar = new b();
        bVar.f1583a = (AudioAttributes) aVar.r(bVar.f1583a, 1);
        bVar.f1584b = aVar.p(bVar.f1584b, 2);
        return bVar;
    }

    public static void write(b bVar, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.H(bVar.f1583a, 1);
        aVar.F(bVar.f1584b, 2);
    }
}
