package u0;

import u0.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient g f8119d;

    @Override // u0.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f8119d == null) {
                this.f8119d = new g();
            }
        }
        this.f8119d.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f8119d;
            if (gVar == null) {
                return;
            }
            gVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i8) {
        synchronized (this) {
            g gVar = this.f8119d;
            if (gVar == null) {
                return;
            }
            gVar.d(this, i8, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f8119d;
            if (gVar == null) {
                return;
            }
            gVar.i(aVar);
        }
    }
}
