package androidx.media;

import defpackage.kl3;
import defpackage.ml3;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(kl3 kl3Var) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        ml3 ml3VarN = audioAttributesCompat.a;
        if (kl3Var.h(1)) {
            ml3VarN = kl3Var.n();
        }
        audioAttributesCompat.a = (AudioAttributesImpl) ml3VarN;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, kl3 kl3Var) {
        kl3Var.getClass();
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.a;
        kl3Var.o(1);
        kl3Var.w(audioAttributesImpl);
    }
}
