package T;

import e0.AbstractC1156C;

/* JADX INFO: loaded from: classes.dex */
public final class E0 extends AbstractC1156C {
    public long c;

    public E0(long j3) {
        this.c = j3;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
        this.c = ((E0) abstractC1156C).c;
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new E0(this.c);
    }
}
