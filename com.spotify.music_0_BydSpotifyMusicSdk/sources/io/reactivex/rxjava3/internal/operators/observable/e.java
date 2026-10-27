package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Iterator {
    public Object c;
    public final /* synthetic */ f e;

    public e(f fVar) {
        this.e = fVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        this.c = this.e.e;
        return !NotificationLite.h(r0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        try {
            if (this.c == null) {
                this.c = this.e.e;
            }
            if (NotificationLite.h(this.c)) {
                throw new NoSuchElementException();
            }
            if (NotificationLite.i(this.c)) {
                throw io.reactivex.rxjava3.internal.util.c.f(NotificationLite.g(this.c));
            }
            Object obj = this.c;
            this.c = null;
            return obj;
        } catch (Throwable th) {
            this.c = null;
            throw th;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Read only iterator");
    }
}
