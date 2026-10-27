package androidx.media;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2604a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2605b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2606c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2607d = -1;

    public int a() {
        return this.f2605b;
    }

    public int b() {
        int i7 = this.f2606c;
        int iC = c();
        if (iC == 6) {
            i7 |= 4;
        } else if (iC == 7) {
            i7 |= 1;
        }
        return i7 & 273;
    }

    public int c() {
        int i7 = this.f2607d;
        return i7 != -1 ? i7 : AudioAttributesCompat.a(false, this.f2606c, this.f2604a);
    }

    public int d() {
        return this.f2604a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.f2605b == audioAttributesImplBase.a() && this.f2606c == audioAttributesImplBase.b() && this.f2604a == audioAttributesImplBase.d() && this.f2607d == audioAttributesImplBase.f2607d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2605b), Integer.valueOf(this.f2606c), Integer.valueOf(this.f2604a), Integer.valueOf(this.f2607d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f2607d != -1) {
            sb.append(" stream=");
            sb.append(this.f2607d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f2604a));
        sb.append(" content=");
        sb.append(this.f2605b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f2606c).toUpperCase());
        return sb.toString();
    }
}
