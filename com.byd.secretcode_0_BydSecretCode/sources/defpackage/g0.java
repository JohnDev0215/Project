package defpackage;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g0 {
    public boolean a;
    public CopyOnWriteArrayList<f0> b = new CopyOnWriteArrayList<>();

    public g0(boolean z) {
        this.a = z;
    }

    public void a(f0 f0Var) {
        this.b.add(f0Var);
    }

    public abstract void b();

    public final boolean c() {
        return this.a;
    }

    public final void d() {
        Iterator<f0> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void e(f0 f0Var) {
        this.b.remove(f0Var);
    }

    public final void f(boolean z) {
        this.a = z;
    }
}
