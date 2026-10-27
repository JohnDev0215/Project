package androidx.media;

import android.media.AudioAttributes;
import p.ys2;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(ys2 ys2Var) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.a = (AudioAttributes) ys2Var.g(audioAttributesImplApi21.a, 1);
        audioAttributesImplApi21.b = ys2Var.f(audioAttributesImplApi21.b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, ys2 ys2Var) {
        ys2Var.getClass();
        ys2Var.k(audioAttributesImplApi21.a, 1);
        ys2Var.j(audioAttributesImplApi21.b, 2);
    }
}
