package b.k;

import b.k.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient g f2742b;

    @Override // b.k.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f2742b == null) {
                this.f2742b = new g();
            }
        }
        this.f2742b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f2742b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i2) {
        synchronized (this) {
            g gVar = this.f2742b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, i2, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f2742b;
            if (gVar == null) {
                return;
            }
            gVar.i(aVar);
        }
    }
}
