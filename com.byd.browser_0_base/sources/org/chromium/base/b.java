package org.chromium.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: ObserverList.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Iterable<E> {
    public int g;
    public int h;
    public boolean i;
    public final ArrayList e = new ArrayList();
    public final boolean j = true;
    public final ThreadUtils.a f = new ThreadUtils.a();

    /* JADX INFO: compiled from: ObserverList.java */
    public class a implements Iterator {
        public int e;
        public int f;
        public boolean g;

        public a() {
            b.this.g++;
            this.e = b.this.e.size();
        }

        public final void b() {
            if (this.g) {
                return;
            }
            this.g = true;
            b bVar = b.this;
            int i = bVar.g - 1;
            bVar.g = i;
            if (i > 0 || !bVar.i) {
                return;
            }
            bVar.i = false;
            ArrayList arrayList = bVar.e;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    return;
                }
                if (arrayList.get(size) == null) {
                    arrayList.remove(size);
                }
            }
        }

        public final void c() {
            b bVar = b.this;
            if (bVar.j) {
                bVar.f.getClass();
            }
            b();
            bVar.g++;
            this.e = bVar.e.size();
            this.g = false;
            this.f = 0;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            b bVar = b.this;
            if (bVar.j) {
                bVar.f.getClass();
            }
            int i = this.f;
            while (i < this.e && bVar.e.get(i) == null) {
                i++;
            }
            if (i < this.e) {
                return true;
            }
            b();
            return false;
        }

        @Override // java.util.Iterator
        public final E next() {
            ArrayList arrayList;
            b bVar = b.this;
            if (bVar.j) {
                bVar.f.getClass();
            }
            while (true) {
                int i = this.f;
                int i2 = this.e;
                arrayList = bVar.e;
                if (i >= i2 || arrayList.get(i) != null) {
                    break;
                }
                this.f++;
            }
            int i3 = this.f;
            if (i3 < this.e) {
                this.f = i3 + 1;
                return (E) arrayList.get(i3);
            }
            b();
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean a(E e) {
        if (this.j) {
            this.f.getClass();
        }
        if (e == null) {
            return false;
        }
        ArrayList arrayList = this.e;
        if (arrayList.contains(e)) {
            return false;
        }
        arrayList.add(e);
        this.h++;
        return true;
    }

    public final boolean b(E e) {
        if (this.j) {
            this.f.getClass();
        }
        return this.e.contains(e);
    }

    public final boolean c(E e) {
        ArrayList arrayList;
        int iIndexOf;
        if (this.j) {
            this.f.getClass();
        }
        if (e == null || (iIndexOf = (arrayList = this.e).indexOf(e)) == -1) {
            return false;
        }
        if (this.g == 0) {
            arrayList.remove(iIndexOf);
        } else {
            this.i = true;
            arrayList.set(iIndexOf, null);
        }
        this.h--;
        return true;
    }

    public final void clear() {
        if (this.j) {
            this.f.getClass();
        }
        this.h = 0;
        int i = this.g;
        ArrayList arrayList = this.e;
        if (i == 0) {
            arrayList.clear();
            return;
        }
        int size = arrayList.size();
        this.i |= size != 0;
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.set(i2, null);
        }
    }

    public final a d() {
        if (this.j) {
            this.f.getClass();
        }
        return new a();
    }

    public final boolean isEmpty() {
        if (this.j) {
            this.f.getClass();
        }
        return this.h == 0;
    }

    @Override // java.lang.Iterable
    public final Iterator<E> iterator() {
        if (this.j) {
            this.f.getClass();
        }
        return new a();
    }
}
