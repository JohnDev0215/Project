package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import defpackage.bc;
import defpackage.cc;
import defpackage.ec;
import defpackage.ic;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements cc {
    public final bc[] a;

    public CompositeGeneratedAdaptersObserver(bc[] bcVarArr) {
        this.a = bcVarArr;
    }

    @Override // defpackage.cc
    public void d(ec ecVar, Lifecycle.Event event) {
        ic icVar = new ic();
        for (bc bcVar : this.a) {
            bcVar.a(ecVar, event, false, icVar);
        }
        for (bc bcVar2 : this.a) {
            bcVar2.a(ecVar, event, true, icVar);
        }
    }
}
