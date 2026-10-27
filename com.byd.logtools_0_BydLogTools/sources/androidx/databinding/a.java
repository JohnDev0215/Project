package androidx.databinding;

import androidx.databinding.e;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient g f1424b;

    @Override // androidx.databinding.e
    public void addOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            if (this.f1424b == null) {
                this.f1424b = new g();
            }
        }
        this.f1424b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            g gVar = this.f1424b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            g gVar = this.f1424b;
            if (gVar == null) {
                return;
            }
            gVar.d(this, i, null);
        }
    }

    public void removeOnPropertyChangedCallback(e.a aVar) {
        synchronized (this) {
            g gVar = this.f1424b;
            if (gVar == null) {
                return;
            }
            gVar.j(aVar);
        }
    }
}
