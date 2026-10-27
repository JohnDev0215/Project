package q0;

import q0.e;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends q0.a {

    /* JADX INFO: compiled from: BaseObservableField.java */
    public class a extends e.a {
        public a() {
        }

        @Override // q0.e.a
        public void a(e eVar, int i3) {
            b.this.notifyChange();
        }
    }

    public b() {
    }

    public b(e... eVarArr) {
        if (eVarArr == null || eVarArr.length == 0) {
            return;
        }
        a aVar = new a();
        for (e eVar : eVarArr) {
            eVar.addOnPropertyChangedCallback(aVar);
        }
    }
}
