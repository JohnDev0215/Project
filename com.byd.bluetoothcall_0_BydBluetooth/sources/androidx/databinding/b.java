package androidx.databinding;

import androidx.databinding.e;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b extends androidx.databinding.a {

    /* JADX INFO: compiled from: BaseObservableField.java */
    class a extends e.a {
        a() {
        }

        @Override // androidx.databinding.e.a
        public void a(e eVar, int i) {
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
