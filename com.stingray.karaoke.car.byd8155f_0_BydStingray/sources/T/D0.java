package T;

import e0.AbstractC1156C;

/* JADX INFO: loaded from: classes.dex */
public final class D0 extends AbstractC1156C {
    public int c;

    public D0(int i7) {
        this.c = i7;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord");
        this.c = ((D0) abstractC1156C).c;
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new D0(this.c);
    }
}
