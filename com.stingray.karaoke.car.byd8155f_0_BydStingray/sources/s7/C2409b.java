package s7;

import q7.InterfaceC2189c;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: s7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2409b implements InterfaceC2189c {
    public static final C2409b l = new C2409b();

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
