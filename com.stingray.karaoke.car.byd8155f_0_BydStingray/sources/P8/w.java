package p8;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public final class w implements Iterator {
    public final Stack l = new Stack();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public t f20993m;

    public w(AbstractC2129d abstractC2129d) {
        while (abstractC2129d instanceof y) {
            y yVar = (y) abstractC2129d;
            this.l.push(yVar);
            abstractC2129d = yVar.f20998n;
        }
        this.f20993m = (t) abstractC2129d;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final t next() {
        t tVar;
        t tVar2 = this.f20993m;
        if (tVar2 == null) {
            throw new NoSuchElementException();
        }
        while (true) {
            Stack stack = this.l;
            if (!stack.isEmpty()) {
                AbstractC2129d abstractC2129d = ((y) stack.pop()).o;
                while (abstractC2129d instanceof y) {
                    y yVar = (y) abstractC2129d;
                    stack.push(yVar);
                    abstractC2129d = yVar.f20998n;
                }
                tVar = (t) abstractC2129d;
                if (tVar.f20991m.length != 0) {
                    break;
                }
            } else {
                tVar = null;
                break;
            }
        }
        this.f20993m = tVar;
        return tVar2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f20993m != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
