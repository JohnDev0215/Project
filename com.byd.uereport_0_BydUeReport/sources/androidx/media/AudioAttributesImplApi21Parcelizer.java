package androidx.media;

import android.media.AudioAttributes;
import b1.b;
import m1.a;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static b read(a aVar) {
        b bVar = new b();
        bVar.f2027a = (AudioAttributes) aVar.r(bVar.f2027a, 1);
        bVar.f2028b = aVar.p(bVar.f2028b, 2);
        return bVar;
    }

    public static void write(b bVar, a aVar) {
        aVar.x(false, false);
        aVar.H(bVar.f2027a, 1);
        aVar.F(bVar.f2028b, 2);
    }
}
