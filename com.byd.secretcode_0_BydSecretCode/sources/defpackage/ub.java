package defpackage;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class ub implements ec {
    public fc b = null;

    public void a(Lifecycle.Event event) {
        this.b.i(event);
    }

    public void b() {
        if (this.b == null) {
            this.b = new fc(this);
        }
    }

    public boolean c() {
        return this.b != null;
    }

    @Override // defpackage.ec
    public Lifecycle getLifecycle() {
        b();
        return this.b;
    }
}
