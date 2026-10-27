package p;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class vg1 {
    public final Object a;
    public boolean b;
    public boolean c;
    public boolean d;

    public vg1(Object obj) {
        this.a = obj;
    }

    public void a() {
        boolean z = this.b;
        Object obj = this.a;
        if (z) {
            throw new IllegalStateException("detach() called when detach() had already been called for: " + obj);
        }
        if (this.c) {
            throw new IllegalStateException("detach() called when sendResult() had already been called for: " + obj);
        }
        if (!this.d) {
            this.b = true;
        } else {
            throw new IllegalStateException("detach() called when sendError() had already been called for: " + obj);
        }
    }

    public final boolean b() {
        return this.b || this.c || this.d;
    }

    public void c() {
        throw new UnsupportedOperationException("It is not supported to send an error for " + this.a);
    }

    public void d(Bundle bundle) {
        throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.a);
    }

    public abstract void e(Object obj);

    public final void f() {
        if (this.c || this.d) {
            throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.a);
        }
        this.d = true;
        c();
    }

    public final void g(Object obj) {
        if (this.c || this.d) {
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.a);
        }
        this.c = true;
        e(obj);
    }
}
