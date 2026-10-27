package Y;

/* JADX INFO: loaded from: classes.dex */
public final class p extends n {
    public final O8.h o;

    public p(O8.h hVar) {
        this.o = hVar;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i7 = this.f10604n;
        this.f10604n = i7 + 2;
        Object[] objArr = this.l;
        return new b(this.o, objArr[i7], objArr[i7 + 1]);
    }
}
