package defpackage;

import defpackage.eb;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class ab implements eb {
    public transient gb b;

    @Override // defpackage.eb
    public void addOnPropertyChangedCallback(eb.a aVar) {
        synchronized (this) {
            if (this.b == null) {
                this.b = new gb();
            }
        }
        this.b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            gb gbVar = this.b;
            if (gbVar == null) {
                return;
            }
            gbVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            gb gbVar = this.b;
            if (gbVar == null) {
                return;
            }
            gbVar.d(this, i, null);
        }
    }

    public void removeOnPropertyChangedCallback(eb.a aVar) {
        synchronized (this) {
            gb gbVar = this.b;
            if (gbVar == null) {
                return;
            }
            gbVar.i(aVar);
        }
    }
}
