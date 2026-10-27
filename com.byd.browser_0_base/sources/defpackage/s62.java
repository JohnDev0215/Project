package defpackage;

import android.os.Handler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: Promise.java */
/* JADX INFO: loaded from: classes.dex */
public final class s62<T> {
    public T b;
    public Exception d;
    public final Handler f;
    public boolean g;
    public int a = 0;
    public final LinkedList c = new LinkedList();
    public final LinkedList e = new LinkedList();

    /* JADX INFO: compiled from: Promise.java */
    public interface a<A, RT> extends Function<A, s62<RT>> {
    }

    /* JADX INFO: compiled from: Promise.java */
    public static class b extends RuntimeException {
        public b(Throwable th) {
            super("Promise was rejected without a rejection handler.", th);
        }
    }

    public s62() {
        Thread.currentThread();
        this.f = new Handler();
    }

    public static <T> s62<T> c(T t) {
        s62<T> s62Var = new s62<>();
        s62Var.b(t);
        return s62Var;
    }

    public final void a(Callback<Exception> callback) {
        int i = this.a;
        if (i == 2) {
            this.f.post(callback.p1(this.d));
        } else if (i == 0) {
            this.e.add(callback);
        }
    }

    public final void b(T t) {
        this.a = 1;
        this.b = t;
        LinkedList linkedList = this.c;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.f.post(((Callback) it.next()).p1(t));
        }
        linkedList.clear();
    }

    public final boolean d() {
        return this.a == 1;
    }

    public final void e(Exception exc) {
        this.a = 2;
        this.d = exc;
        LinkedList linkedList = this.e;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.f.post(((Callback) it.next()).p1(exc));
        }
        linkedList.clear();
    }

    public final <RT> s62<RT> f(Function<T, RT> function) {
        s62<RT> s62Var = new s62<>();
        int i = 0;
        i(new n62(s62Var, i, function));
        a(new o62(s62Var, i));
        return s62Var;
    }

    public final void g(Callback<T> callback) {
        if (this.g) {
            i(callback);
        } else {
            h(callback, new m62(0));
            this.g = true;
        }
    }

    public final void h(Callback<T> callback, Callback<Exception> callback2) {
        i(callback);
        a(callback2);
    }

    public final void i(Callback<T> callback) {
        int i = this.a;
        if (i == 1) {
            this.f.post(callback.p1(this.b));
        } else if (i == 0) {
            this.c.add(callback);
        }
    }
}
