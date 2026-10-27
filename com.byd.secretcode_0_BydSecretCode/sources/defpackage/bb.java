package defpackage;

import defpackage.eb;

/* JADX INFO: compiled from: BaseObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class bb extends ab {

    /* JADX INFO: compiled from: BaseObservableField.java */
    public class a extends eb.a {
        public a() {
        }

        @Override // eb.a
        public void a(eb ebVar, int i) {
            bb.this.notifyChange();
        }
    }

    public bb() {
    }

    public bb(eb... ebVarArr) {
        if (ebVarArr == null || ebVarArr.length == 0) {
            return;
        }
        a aVar = new a();
        for (eb ebVar : ebVarArr) {
            ebVar.addOnPropertyChangedCallback(aVar);
        }
    }
}
