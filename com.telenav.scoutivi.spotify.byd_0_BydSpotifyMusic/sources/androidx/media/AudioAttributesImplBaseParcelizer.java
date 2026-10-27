package androidx.media;

import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(a aVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f2604a = aVar.p(audioAttributesImplBase.f2604a, 1);
        audioAttributesImplBase.f2605b = aVar.p(audioAttributesImplBase.f2605b, 2);
        audioAttributesImplBase.f2606c = aVar.p(audioAttributesImplBase.f2606c, 3);
        audioAttributesImplBase.f2607d = aVar.p(audioAttributesImplBase.f2607d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, a aVar) {
        aVar.x(false, false);
        aVar.F(audioAttributesImplBase.f2604a, 1);
        aVar.F(audioAttributesImplBase.f2605b, 2);
        aVar.F(audioAttributesImplBase.f2606c, 3);
        aVar.F(audioAttributesImplBase.f2607d, 4);
    }
}
