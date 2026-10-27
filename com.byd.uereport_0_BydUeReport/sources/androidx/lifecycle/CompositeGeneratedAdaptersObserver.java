package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import x0.c;
import x0.d;
import x0.f;
import x0.j;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c[] f1433d;

    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f1433d = cVarArr;
    }

    @Override // x0.d
    public void onStateChanged(f fVar, Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f1433d) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f1433d) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}
