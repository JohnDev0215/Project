package androidx.media;

import android.media.AudioAttributes;
import defpackage.W7;
import defpackage.Y4;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static Y4 read(W7 w7) {
        Y4 y4 = new Y4();
        y4.a = (AudioAttributes) w7.f(y4.a, 1);
        y4.b = w7.e(y4.b, 2);
        return y4;
    }

    public static void write(Y4 y4, W7 w7) {
        Objects.requireNonNull(w7);
        w7.j(y4.a, 1);
        w7.i(y4.b, 2);
    }
}
