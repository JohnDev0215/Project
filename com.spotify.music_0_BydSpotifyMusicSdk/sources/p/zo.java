package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zo implements Iterator {
    @Override // java.util.Iterator
    public final Object next() {
        yo yoVar = (yo) this;
        int i = yoVar.c;
        if (i >= yoVar.e) {
            throw new NoSuchElementException();
        }
        yoVar.c = i + 1;
        return Byte.valueOf(yoVar.k.h(i));
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
