package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.l.c;
import b.l.d;
import b.l.f;
import b.l.j;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c[] f520a;

    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f520a = cVarArr;
    }

    @Override // b.l.d
    public void d(f fVar, Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f520a) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f520a) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}
