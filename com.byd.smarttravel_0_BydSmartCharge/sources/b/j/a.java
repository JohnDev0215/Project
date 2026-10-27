package b.j;

import b.j.g;

/* JADX INFO: compiled from: BaseObservable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient i f1209b;

    @Override // b.j.g
    public void addOnPropertyChangedCallback(g.a aVar) {
        synchronized (this) {
            if (this.f1209b == null) {
                this.f1209b = new i();
            }
        }
        this.f1209b.a(aVar);
    }

    public void notifyChange() {
        synchronized (this) {
            i iVar = this.f1209b;
            if (iVar == null) {
                return;
            }
            iVar.d(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int i) {
        synchronized (this) {
            i iVar = this.f1209b;
            if (iVar == null) {
                return;
            }
            iVar.d(this, i, null);
        }
    }

    public void removeOnPropertyChangedCallback(g.a aVar) {
        synchronized (this) {
            i iVar = this.f1209b;
            if (iVar == null) {
                return;
            }
            iVar.i(aVar);
        }
    }
}
