package androidx.media;

import android.media.AudioAttributes;
import defpackage.kl3;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(kl3 kl3Var) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.a = (AudioAttributes) kl3Var.l(audioAttributesImplApi26.a, 1);
        audioAttributesImplApi26.b = kl3Var.j(audioAttributesImplApi26.b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, kl3 kl3Var) {
        kl3Var.getClass();
        AudioAttributes audioAttributes = audioAttributesImplApi26.a;
        kl3Var.o(1);
        kl3Var.u(audioAttributes);
        kl3Var.t(audioAttributesImplApi26.b, 2);
    }
}
