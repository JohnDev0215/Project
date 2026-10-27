package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AudioAttributes f2602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2603b;

    public AudioAttributesImplApi21() {
        this.f2603b = -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f2602a.equals(((AudioAttributesImplApi21) obj).f2602a);
        }
        return false;
    }

    public int hashCode() {
        return this.f2602a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f2602a;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i7) {
        this.f2602a = audioAttributes;
        this.f2603b = i7;
    }
}
