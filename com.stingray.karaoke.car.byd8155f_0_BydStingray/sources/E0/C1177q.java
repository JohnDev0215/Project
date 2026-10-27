package e0;

/* JADX INFO: renamed from: e0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1177q extends AbstractC1156C {
    public X.c c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f13730e;

    public C1177q(X.c cVar) {
        this.c = cVar;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        synchronized (AbstractC1179s.f13731a) {
            A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord.assign$lambda$0>");
            this.c = ((C1177q) abstractC1156C).c;
            this.d = ((C1177q) abstractC1156C).d;
            this.f13730e = ((C1177q) abstractC1156C).f13730e;
        }
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new C1177q(this.c);
    }
}
