package e0;

/* JADX INFO: renamed from: e0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1180t extends AbstractC1156C {
    public W.d c;
    public int d;

    public C1180t(W.d dVar) {
        this.c = dVar;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord, V of androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord>");
        C1180t c1180t = (C1180t) abstractC1156C;
        synchronized (AbstractC1179s.f13732b) {
            this.c = c1180t.c;
            this.d = c1180t.d;
        }
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new C1180t(this.c);
    }
}
