package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

/* JADX INFO: compiled from: AudioAttributesImplApi21.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AudioAttributes f1583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1584b = -1;

    b() {
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f1583a.equals(((b) obj).f1583a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1583a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f1583a;
    }
}
