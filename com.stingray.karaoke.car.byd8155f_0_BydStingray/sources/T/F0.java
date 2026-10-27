package T;

import e0.AbstractC1156C;

/* JADX INFO: loaded from: classes.dex */
public final class F0 extends AbstractC1156C {
    public Object c;

    public F0(Object obj) {
        this.c = obj;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord>");
        this.c = ((F0) abstractC1156C).c;
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new F0(this.c);
    }
}
