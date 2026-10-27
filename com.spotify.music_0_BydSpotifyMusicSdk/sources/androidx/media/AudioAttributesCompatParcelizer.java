package androidx.media;

import p.at2;
import p.ys2;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(ys2 ys2Var) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        at2 at2VarH = audioAttributesCompat.a;
        if (ys2Var.e(1)) {
            at2VarH = ys2Var.h();
        }
        audioAttributesCompat.a = (AudioAttributesImpl) at2VarH;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, ys2 ys2Var) {
        ys2Var.getClass();
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.a;
        ys2Var.i(1);
        ys2Var.l(audioAttributesImpl);
    }
}
