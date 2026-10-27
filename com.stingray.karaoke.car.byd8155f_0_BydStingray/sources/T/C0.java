package T;

import e0.AbstractC1156C;

/* JADX INFO: loaded from: classes.dex */
public final class C0 extends AbstractC1156C {
    public float c;

    public C0(float f10) {
        this.c = f10;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
        this.c = ((C0) abstractC1156C).c;
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new C0(this.c);
    }
}
