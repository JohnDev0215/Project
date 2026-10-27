package androidx.media;

import java.util.Arrays;

/* JADX INFO: compiled from: AudioAttributesImplBase.java */
/* JADX INFO: loaded from: classes.dex */
class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1585a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1586b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1587c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1588d = -1;

    c() {
    }

    public int a() {
        return this.f1586b;
    }

    public int b() {
        int i = this.f1587c;
        int iC = c();
        if (iC == 6) {
            i |= 4;
        } else if (iC == 7) {
            i |= 1;
        }
        return i & 273;
    }

    public int c() {
        int i = this.f1588d;
        return i != -1 ? i : AudioAttributesCompat.a(false, this.f1587c, this.f1585a);
    }

    public int d() {
        return this.f1585a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f1586b == cVar.a() && this.f1587c == cVar.b() && this.f1585a == cVar.d() && this.f1588d == cVar.f1588d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f1586b), Integer.valueOf(this.f1587c), Integer.valueOf(this.f1585a), Integer.valueOf(this.f1588d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f1588d != -1) {
            sb.append(" stream=");
            sb.append(this.f1588d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.f1585a));
        sb.append(" content=");
        sb.append(this.f1586b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f1587c).toUpperCase());
        return sb.toString();
    }
}
