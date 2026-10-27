package u0;

import u0.e;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends u0.a {

    /* JADX INFO: compiled from: BaseObservableField.java */
    public class a extends e.a {
        public a() {
        }

        @Override // u0.e.a
        public void a(e eVar, int i8) {
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
