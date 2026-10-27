package p;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class aa0 {
    public boolean a;
    public final Object b;
    public final Serializable c;
    public final /* synthetic */ Object d;

    public aa0(gu0 gu0Var, boolean z) {
        this.d = gu0Var;
        this.c = new AtomicReference(null);
        this.a = z;
        this.b = new AtomicMarkableReference(new o81(z ? 8192 : 1024), false);
    }

    public final void a(boolean z) {
        Object obj = this.d;
        coil.disk.a aVar = (coil.disk.a) obj;
        coil.disk.a aVar2 = (coil.disk.a) obj;
        synchronized (aVar) {
            try {
                if (!(!this.a)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                if (v03.b(((ba0) this.b).g, this)) {
                    coil.disk.a.b(aVar2, this, z);
                }
                this.a = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final os1 b(int i) {
        os1 os1Var;
        coil.disk.a aVar = (coil.disk.a) this.d;
        synchronized (aVar) {
            if (!(!this.a)) {
                throw new IllegalStateException("editor is closed".toString());
            }
            ((boolean[]) this.c)[i] = true;
            Object obj = ((ba0) this.b).d.get(i);
            da0 da0Var = aVar.x;
            os1 os1Var2 = (os1) obj;
            if (!da0Var.f(os1Var2)) {
                i.a(da0Var.k(os1Var2));
            }
            os1Var = (os1) obj;
        }
        return os1Var;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [boolean[], java.io.Serializable] */
    public aa0(coil.disk.a aVar, ba0 ba0Var) {
        this.d = aVar;
        this.b = ba0Var;
        aVar.getClass();
        this.c = new boolean[2];
    }
}
