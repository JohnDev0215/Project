package androidx.media;

import android.media.AudioAttributes;
import p.ys2;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(ys2 ys2Var) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.a = (AudioAttributes) ys2Var.g(audioAttributesImplApi26.a, 1);
        audioAttributesImplApi26.b = ys2Var.f(audioAttributesImplApi26.b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, ys2 ys2Var) {
        ys2Var.getClass();
        ys2Var.k(audioAttributesImplApi26.a, 1);
        ys2Var.j(audioAttributesImplApi26.b, 2);
    }
}
