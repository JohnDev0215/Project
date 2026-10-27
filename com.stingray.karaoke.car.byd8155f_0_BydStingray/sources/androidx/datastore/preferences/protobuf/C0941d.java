package androidx.datastore.preferences.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0941d implements Iterator {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f11832m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f11833n;
    public final /* synthetic */ Iterable o;

    public C0941d(C0944g c0944g) {
        this.o = c0944g;
        this.f11833n = c0944g.size();
    }

    public byte a() {
        try {
            byte[] bArr = ((p8.t) this.o).f20991m;
            int i7 = this.f11832m;
            this.f11832m = i7 + 1;
            return bArr[i7];
        } catch (ArrayIndexOutOfBoundsException e10) {
            throw new NoSuchElementException(e10.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (this.f11832m < this.f11833n) {
                }
                break;
            default:
                if (this.f11832m < this.f11833n) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                int i7 = this.f11832m;
                if (i7 >= this.f11833n) {
                    throw new NoSuchElementException();
                }
                this.f11832m = i7 + 1;
                return Byte.valueOf(((C0944g) this.o).r(i7));
            default:
                return Byte.valueOf(a());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public C0941d(p8.t tVar) {
        this.o = tVar;
        this.f11833n = tVar.f20991m.length;
    }
}
