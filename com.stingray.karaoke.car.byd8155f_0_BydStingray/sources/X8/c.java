package X8;

import S8.InterfaceC0725w;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class c implements InterfaceC0725w {
    public final InterfaceC2194h l;

    public c(InterfaceC2194h interfaceC2194h) {
        this.l = interfaceC2194h;
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.l;
    }

    public final String toString() {
        return "CoroutineScope(coroutineContext=" + this.l + ')';
    }
}
