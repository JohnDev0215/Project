package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import t0.c;
import t0.d;
import t0.f;
import t0.j;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c[] f1346a;

    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f1346a = cVarArr;
    }

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f1346a) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f1346a) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}
