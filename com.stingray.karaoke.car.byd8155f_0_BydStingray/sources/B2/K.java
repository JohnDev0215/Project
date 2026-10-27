package B2;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f518b;
    public final int c;
    public final int d;

    public K(int i7, int i10, int i11, byte[] bArr) {
        this.f517a = i7;
        this.f518b = bArr;
        this.c = i10;
        this.d = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || K.class != obj.getClass()) {
            return false;
        }
        K k10 = (K) obj;
        return this.f517a == k10.f517a && this.c == k10.c && this.d == k10.d && Arrays.equals(this.f518b, k10.f518b);
    }

    public final int hashCode() {
        return ((((Arrays.hashCode(this.f518b) + (this.f517a * 31)) * 31) + this.c) * 31) + this.d;
    }
}
