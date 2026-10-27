package androidx.media;

import defpackage.W7;
import defpackage.X4;
import defpackage.Y7;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(W7 w7) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        Y7 y7G = audioAttributesCompat.a;
        if (w7.d(1)) {
            y7G = w7.g();
        }
        audioAttributesCompat.a = (X4) y7G;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, W7 w7) {
        Objects.requireNonNull(w7);
        X4 x4 = audioAttributesCompat.a;
        w7.h(1);
        w7.k(x4);
    }
}
