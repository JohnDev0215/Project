package androidx.databinding;

import androidx.databinding.j;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient q f2064e;

    @Override // androidx.databinding.j
    public void b(j.a aVar) {
        synchronized (this) {
            if (this.f2064e == null) {
                this.f2064e = new q();
            }
        }
        this.f2064e.a(aVar);
    }

    @Override // androidx.databinding.j
    public void k(j.a aVar) {
        synchronized (this) {
            q qVar = this.f2064e;
            if (qVar == null) {
                return;
            }
            qVar.i(aVar);
        }
    }

    public void m() {
        synchronized (this) {
            q qVar = this.f2064e;
            if (qVar == null) {
                return;
            }
            qVar.d(this, 0, null);
        }
    }

    public void p(int i7) {
        synchronized (this) {
            q qVar = this.f2064e;
            if (qVar == null) {
                return;
            }
            qVar.d(this, i7, null);
        }
    }
}
