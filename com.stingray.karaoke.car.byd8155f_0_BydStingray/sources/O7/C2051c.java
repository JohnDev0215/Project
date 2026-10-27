package o7;

import A7.m;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: o7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2051c extends U1.c implements Iterator, B7.a {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f20545p;

    public C2051c(C2053e c2053e, int i7) {
        this.f20545p = i7;
        m.f(c2053e, "map");
        this.o = c2053e;
        this.f8960m = -1;
        this.f8961n = c2053e.f20554s;
        d();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f20545p) {
            case 0:
                c();
                int i7 = this.l;
                C2053e c2053e = (C2053e) this.o;
                if (i7 >= c2053e.f20552q) {
                    throw new NoSuchElementException();
                }
                this.l = i7 + 1;
                this.f8960m = i7;
                C2052d c2052d = new C2052d(c2053e, i7);
                d();
                return c2052d;
            case 1:
                c();
                int i10 = this.l;
                C2053e c2053e2 = (C2053e) this.o;
                if (i10 >= c2053e2.f20552q) {
                    throw new NoSuchElementException();
                }
                this.l = i10 + 1;
                this.f8960m = i10;
                Object obj = c2053e2.l[i10];
                d();
                return obj;
            default:
                c();
                int i11 = this.l;
                C2053e c2053e3 = (C2053e) this.o;
                if (i11 >= c2053e3.f20552q) {
                    throw new NoSuchElementException();
                }
                this.l = i11 + 1;
                this.f8960m = i11;
                Object[] objArr = c2053e3.f20549m;
                m.c(objArr);
                Object obj2 = objArr[this.f8960m];
                d();
                return obj2;
        }
    }
}
