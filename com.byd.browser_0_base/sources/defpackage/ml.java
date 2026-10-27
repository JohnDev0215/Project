package defpackage;

import defpackage.AbstractC0208s;
import defpackage.ol;
import java.io.Serializable;

/* JADX INFO: compiled from: CallbackToFutureAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ml<T> {
    public Serializable a;
    public ol<T> b;
    public nb2<Void> c = new nb2<>();
    public boolean d;

    public final void finalize() {
        nb2<Void> nb2Var;
        ol<T> olVar = this.b;
        if (olVar != null && !olVar.isDone()) {
            nl nlVar = new nl("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.a);
            ol.a aVar = olVar.f;
            aVar.getClass();
            if (AbstractC0208s.i.b(aVar, null, new AbstractC0208s.c(nlVar))) {
                AbstractC0208s.c(aVar);
            }
        }
        if (this.d || (nb2Var = this.c) == null) {
            return;
        }
        nb2Var.g(null);
    }
}
