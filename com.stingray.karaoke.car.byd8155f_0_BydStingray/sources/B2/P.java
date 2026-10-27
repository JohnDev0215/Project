package B2;

import Q7.InterfaceC0650c;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class P implements O8.b {
    public boolean l;

    public synchronized void a() {
        while (!this.l) {
            wait();
        }
    }

    @Override // O8.b
    public Iterable b(Object obj) {
        InterfaceC0650c interfaceC0650cA = (InterfaceC0650c) obj;
        if (this.l) {
            interfaceC0650cA = interfaceC0650cA != null ? interfaceC0650cA.a() : null;
        }
        Collection collectionQ = interfaceC0650cA != null ? interfaceC0650cA.q() : null;
        return collectionQ == null ? n7.u.l : collectionQ;
    }

    public synchronized boolean c() {
        if (this.l) {
            return false;
        }
        this.l = true;
        notifyAll();
        return true;
    }

    public P() {
        this.l = false;
    }
}
