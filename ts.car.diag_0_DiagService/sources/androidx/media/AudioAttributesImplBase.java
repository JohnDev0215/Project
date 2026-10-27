package androidx.media;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int f802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int f803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int f804c;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int d;

    /* JADX INFO: compiled from: caradapter-bug */
    static class a implements AudioAttributesImpl.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f805a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f806b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f807c = 0;
        private int d = -1;

        a() {
        }

        @Override // androidx.media.AudioAttributesImpl.a
        @NonNull
        public /* bridge */ /* synthetic */ AudioAttributesImpl.a a(int i) {
            c(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.a
        @NonNull
        public AudioAttributesImpl b() {
            return new AudioAttributesImplBase(this.f806b, this.f807c, this.f805a, this.d);
        }

        @NonNull
        public a c(int i) {
            if (i == 10) {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }
            this.d = i;
            return this;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public AudioAttributesImplBase() {
        this.f802a = 0;
        this.f803b = 0;
        this.f804c = 0;
        this.d = -1;
    }

    public int a() {
        return this.f803b;
    }

    public int b() {
        int i = this.f804c;
        int iC = c();
        if (iC == 6) {
            i |= 4;
        } else if (iC == 7) {
            i |= 1;
        }
        return i & 273;
    }

    public int c() {
        int i = this.d;
        return i != -1 ? i : AudioAttributesCompat.a(false, this.f804c, this.f802a);
    }

    public int d() {
        return this.f802a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.f803b == audioAttributesImplBase.a() && this.f804c == audioAttributesImplBase.b() && this.f802a == audioAttributesImplBase.d() && this.d == audioAttributesImplBase.d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f803b), Integer.valueOf(this.f804c), Integer.valueOf(this.f802a), Integer.valueOf(this.d)});
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f802a));
        sb.append(" content=");
        sb.append(this.f803b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f804c).toUpperCase());
        return sb.toString();
    }

    AudioAttributesImplBase(int i, int i2, int i3, int i4) {
        this.f802a = 0;
        this.f803b = 0;
        this.f804c = 0;
        this.d = -1;
        this.f803b = i;
        this.f804c = i2;
        this.f802a = i3;
        this.d = i4;
    }
}
