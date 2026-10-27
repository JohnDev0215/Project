package Q8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator, B7.a {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7219m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7220n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7221p;

    public d(String str) {
        A7.m.f(str, "string");
        this.l = str;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i7;
        int i10;
        int i11 = this.f7219m;
        if (i11 != 0) {
            return i11 == 1;
        }
        if (this.f7221p < 0) {
            this.f7219m = 2;
            return false;
        }
        String str = this.l;
        int length = str.length();
        int length2 = str.length();
        for (int i12 = this.f7220n; i12 < length2; i12++) {
            char cCharAt = str.charAt(i12);
            if (cCharAt == '\n' || cCharAt == '\r') {
                i7 = (cCharAt == '\r' && (i10 = i12 + 1) < str.length() && str.charAt(i10) == '\n') ? 2 : 1;
                length = i12;
                this.f7219m = 1;
                this.f7221p = i7;
                this.o = length;
                return true;
            }
        }
        i7 = -1;
        this.f7219m = 1;
        this.f7221p = i7;
        this.o = length;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f7219m = 0;
        int i7 = this.o;
        int i10 = this.f7220n;
        this.f7220n = this.f7221p + i7;
        return this.l.subSequence(i10, i7).toString();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
