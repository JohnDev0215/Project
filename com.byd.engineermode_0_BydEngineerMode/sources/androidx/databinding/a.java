package androidx.databinding;

import androidx.databinding.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient g f926b;

    @Override // androidx.databinding.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f926b == null) {
                this.f926b = new g();
            }
        }
        this.f926b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f926b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            g gVar = this.f926b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, i, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f926b;
            if (gVar == null) {
                return;
            }
            gVar.i(aVar);
        }
    }
}
