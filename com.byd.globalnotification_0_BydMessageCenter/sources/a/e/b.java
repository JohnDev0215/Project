package a.e;

import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: loaded from: classes.dex */
public class b<E> extends g<E, E> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ c f449d;

    public b(c cVar) {
        this.f449d = cVar;
    }

    @Override // a.e.g
    public void a() {
        this.f449d.clear();
    }

    @Override // a.e.g
    public Object b(int i, int i2) {
        return this.f449d.f453b[i];
    }

    @Override // a.e.g
    public Map<E, E> c() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // a.e.g
    public int d() {
        return this.f449d.f454c;
    }

    @Override // a.e.g
    public int e(Object obj) {
        return this.f449d.indexOf(obj);
    }

    @Override // a.e.g
    public int f(Object obj) {
        return this.f449d.indexOf(obj);
    }

    @Override // a.e.g
    public void g(E e2, E e3) {
        this.f449d.add(e2);
    }

    @Override // a.e.g
    public void h(int i) {
        this.f449d.g(i);
    }

    @Override // a.e.g
    public E i(int i, E e2) {
        throw new UnsupportedOperationException("not a map");
    }
}
