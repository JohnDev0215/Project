package b.i;

import b.i.e;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends b.i.a {

    /* JADX INFO: compiled from: BaseObservableField.java */
    public class a extends e.a {
        public a() {
        }

        @Override // b.i.e.a
        public void a(e eVar, int i2) {
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
