package q0;

import q0.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient g f4552b;

    @Override // q0.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f4552b == null) {
                this.f4552b = new g();
            }
        }
        this.f4552b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f4552b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i3) {
        synchronized (this) {
            g gVar = this.f4552b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, i3, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f4552b;
            if (gVar == null) {
                return;
            }
            gVar.i(aVar);
        }
    }
}
