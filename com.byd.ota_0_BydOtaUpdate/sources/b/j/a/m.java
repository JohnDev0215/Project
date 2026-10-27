package b.j.a;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements b.l.f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.l.g f1957c = null;

    @Override // b.l.f
    public Lifecycle a() {
        e();
        return this.f1957c;
    }

    public void d(Lifecycle.Event event) {
        this.f1957c.i(event);
    }

    public void e() {
        if (this.f1957c == null) {
            this.f1957c = new b.l.g(this);
        }
    }

    public boolean f() {
        return this.f1957c != null;
    }
}
