package l3;

import android.media.AudioAttributes;
import java.util.Objects;

/* JADX INFO: renamed from: l3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1745a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AudioAttributes f18565a;

    public C1745a(AudioAttributes audioAttributes) {
        this.f18565a = audioAttributes;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1745a) {
            return Objects.equals(this.f18565a, ((C1745a) obj).f18565a);
        }
        return false;
    }

    public final int hashCode() {
        AudioAttributes audioAttributes = this.f18565a;
        audioAttributes.getClass();
        return audioAttributes.hashCode();
    }

    public final String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f18565a;
    }
}
