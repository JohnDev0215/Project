package b.j;

import b.j.g;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends b.j.a {

    /* JADX INFO: compiled from: BaseObservableField.java */
    public class a extends g.a {
        public a() {
        }

        @Override // b.j.g.a
        public void a(g gVar, int i) {
            b.this.notifyChange();
        }
    }

    public b() {
    }

    public b(g... gVarArr) {
        if (gVarArr == null || gVarArr.length == 0) {
            return;
        }
        a aVar = new a();
        for (g gVar : gVarArr) {
            gVar.addOnPropertyChangedCallback(aVar);
        }
    }
}
