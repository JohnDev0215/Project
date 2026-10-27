package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f802a = versionedParcel.p(audioAttributesImplBase.f802a, 1);
        audioAttributesImplBase.f803b = versionedParcel.p(audioAttributesImplBase.f803b, 2);
        audioAttributesImplBase.f804c = versionedParcel.p(audioAttributesImplBase.f804c, 3);
        audioAttributesImplBase.d = versionedParcel.p(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.x(false, false);
        versionedParcel.F(audioAttributesImplBase.f802a, 1);
        versionedParcel.F(audioAttributesImplBase.f803b, 2);
        versionedParcel.F(audioAttributesImplBase.f804c, 3);
        versionedParcel.F(audioAttributesImplBase.d, 4);
    }
}
