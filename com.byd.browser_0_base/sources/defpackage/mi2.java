package defpackage;

import android.view.View;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: SequenceBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class mi2<T> extends ni2<T> implements Iterator<T>, b00<vi3> {
    public int e;
    public T f;
    public Iterator<? extends T> g;
    public b00<? super vi3> h;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.ni2
    public final void b(View view, b00 b00Var) {
        this.f = view;
        this.e = 3;
        this.h = b00Var;
        i21.e(b00Var, "frame");
    }

    @Override // defpackage.b00
    public final void c(Object obj) throws Throwable {
        a8.i0(obj);
        this.e = 4;
    }

    @Override // defpackage.ni2
    public final Object d(Iterator<? extends T> it, b00<? super vi3> b00Var) {
        if (!it.hasNext()) {
            return vi3.a;
        }
        this.g = it;
        this.e = 2;
        this.h = b00Var;
        t00 t00Var = t00.COROUTINE_SUSPENDED;
        i21.e(b00Var, "frame");
        return t00Var;
    }

    public final RuntimeException e() {
        int i = this.e;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.e);
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        return dh0.e;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        while (true) {
            int i = this.e;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        return true;
                    }
                    if (i == 4) {
                        return false;
                    }
                    throw e();
                }
                Iterator<? extends T> it = this.g;
                i21.b(it);
                if (it.hasNext()) {
                    this.e = 2;
                    return true;
                }
                this.g = null;
            }
            this.e = 5;
            b00<? super vi3> b00Var = this.h;
            i21.b(b00Var);
            this.h = null;
            b00Var.c(vi3.a);
        }
    }

    @Override // java.util.Iterator
    public final T next() {
        int i = this.e;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.e = 1;
            Iterator<? extends T> it = this.g;
            i21.b(it);
            return it.next();
        }
        if (i != 3) {
            throw e();
        }
        this.e = 0;
        T t = this.f;
        this.f = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
