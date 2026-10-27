package b1;

import androidx.media.AudioAttributesCompat;
import java.util.Arrays;

/* JADX INFO: compiled from: AudioAttributesImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2029a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2030b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2031c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2032d = -1;

    public int a() {
        return this.f2030b;
    }

    public int b() {
        int i8 = this.f2031c;
        int iC = c();
        if (iC == 6) {
            i8 |= 4;
        } else if (iC == 7) {
            i8 |= 1;
        }
        return i8 & 273;
    }

    public int c() {
        int i8 = this.f2032d;
        return i8 != -1 ? i8 : AudioAttributesCompat.a(false, this.f2031c, this.f2029a);
    }

    public int d() {
        return this.f2029a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2030b == cVar.a() && this.f2031c == cVar.b() && this.f2029a == cVar.d() && this.f2032d == cVar.f2032d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2030b), Integer.valueOf(this.f2031c), Integer.valueOf(this.f2029a), Integer.valueOf(this.f2032d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f2032d != -1) {
            sb.append(" stream=");
            sb.append(this.f2032d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f2029a));
        sb.append(" content=");
        sb.append(this.f2030b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f2031c).toUpperCase());
        return sb.toString();
    }
}
