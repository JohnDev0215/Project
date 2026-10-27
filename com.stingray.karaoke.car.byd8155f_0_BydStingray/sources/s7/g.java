package s7;

import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends AbstractC2408a {
    public g(InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        if (interfaceC2189c != null && interfaceC2189c.getContext() != C2195i.l) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return C2195i.l;
    }
}
