package androidx.media;

import p.ys2;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(ys2 ys2Var) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.a = ys2Var.f(audioAttributesImplBase.a, 1);
        audioAttributesImplBase.b = ys2Var.f(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = ys2Var.f(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = ys2Var.f(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, ys2 ys2Var) {
        ys2Var.getClass();
        ys2Var.j(audioAttributesImplBase.a, 1);
        ys2Var.j(audioAttributesImplBase.b, 2);
        ys2Var.j(audioAttributesImplBase.c, 3);
        ys2Var.j(audioAttributesImplBase.d, 4);
    }
}
