package b.q;

import androidx.media.AudioAttributesCompat;
import java.util.Arrays;

/* JADX INFO: compiled from: AudioAttributesImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3000a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3001b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3002c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3003d = -1;

    public int a() {
        return this.f3001b;
    }

    public int b() {
        int i2 = this.f3002c;
        int iC = c();
        if (iC == 6) {
            i2 |= 4;
        } else if (iC == 7) {
            i2 |= 1;
        }
        return i2 & 273;
    }

    public int c() {
        int i2 = this.f3003d;
        return i2 != -1 ? i2 : AudioAttributesCompat.a(false, this.f3002c, this.f3000a);
    }

    public int d() {
        return this.f3000a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f3001b == cVar.a() && this.f3002c == cVar.b() && this.f3000a == cVar.d() && this.f3003d == cVar.f3003d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3001b), Integer.valueOf(this.f3002c), Integer.valueOf(this.f3000a), Integer.valueOf(this.f3003d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f3003d != -1) {
            sb.append(" stream=");
            sb.append(this.f3003d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f3000a));
        sb.append(" content=");
        sb.append(this.f3001b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f3002c).toUpperCase());
        return sb.toString();
    }
}
