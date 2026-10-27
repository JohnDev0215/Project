package b.q;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

/* JADX INFO: compiled from: AudioAttributesImplApi21.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AudioAttributes f2998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2999b = -1;

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f2998a.equals(((b) obj).f2998a);
        }
        return false;
    }

    public int hashCode() {
        return this.f2998a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f2998a;
    }
}
