package androidx.media;

import defpackage.W7;
import defpackage.Z4;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static Z4 read(W7 w7) {
        Z4 z4 = new Z4();
        z4.a = w7.e(z4.a, 1);
        z4.b = w7.e(z4.b, 2);
        z4.c = w7.e(z4.c, 3);
        z4.d = w7.e(z4.d, 4);
        return z4;
    }

    public static void write(Z4 z4, W7 w7) {
        Objects.requireNonNull(w7);
        w7.i(z4.a, 1);
        w7.i(z4.b, 2);
        w7.i(z4.c, 3);
        w7.i(z4.d, 4);
    }
}
