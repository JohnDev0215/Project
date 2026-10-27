package androidx.lifecycle;

import androidx.lifecycle.d;
import defpackage.b71;
import java.util.HashMap;

/* JADX INFO: compiled from: CompositeGeneratedAdaptersObserver.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CompositeGeneratedAdaptersObserver implements f {
    public final b[] e;

    public CompositeGeneratedAdaptersObserver(b[] bVarArr) {
        this.e = bVarArr;
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        new HashMap();
        b[] bVarArr = this.e;
        for (b bVar : bVarArr) {
            bVar.a();
        }
        for (b bVar2 : bVarArr) {
            bVar2.a();
        }
    }
}
