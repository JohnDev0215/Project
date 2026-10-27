package b.i;

import b.i.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient g f1861c;

    @Override // b.i.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f1861c == null) {
                this.f1861c = new g();
            }
        }
        this.f1861c.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f1861c;
            if (gVar == null) {
                return;
            }
            gVar.f(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i2) {
        synchronized (this) {
            g gVar = this.f1861c;
            if (gVar == null) {
                return;
            }
            gVar.f(this, i2, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f1861c;
            if (gVar == null) {
                return;
            }
            gVar.k(aVar);
        }
    }
}
