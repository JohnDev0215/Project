package androidx.media;

import defpackage.kl3;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(kl3 kl3Var) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.a = kl3Var.j(audioAttributesImplBase.a, 1);
        audioAttributesImplBase.b = kl3Var.j(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = kl3Var.j(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = kl3Var.j(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, kl3 kl3Var) {
        kl3Var.getClass();
        kl3Var.t(audioAttributesImplBase.a, 1);
        kl3Var.t(audioAttributesImplBase.b, 2);
        kl3Var.t(audioAttributesImplBase.c, 3);
        kl3Var.t(audioAttributesImplBase.d, 4);
    }
}
