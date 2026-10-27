package androidx.media;

import android.media.AudioAttributes;
import b.q.b;
import b.z.a;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static b read(a aVar) {
        b bVar = new b();
        bVar.f2998a = (AudioAttributes) aVar.r(bVar.f2998a, 1);
        bVar.f2999b = aVar.p(bVar.f2999b, 2);
        return bVar;
    }

    public static void write(b bVar, a aVar) {
        aVar.x(false, false);
        aVar.H(bVar.f2998a, 1);
        aVar.F(bVar.f2999b, 2);
    }
}
