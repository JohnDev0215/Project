package e.a.a;

import a.k.n;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ReferenceQueue<Object> f2515a = new ReferenceQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static c f2516b;

    public static class b<T, A extends e.a.a.b<T>> extends WeakReference<A> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n<T> f2517a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final n.a f2518b;

        public b(A a2, n<T> nVar, n.a aVar) {
            super(a2, a.f2515a);
            this.f2517a = nVar;
            this.f2518b = aVar;
        }
    }

    public static class c extends Thread {
        public c(C0058a c0058a) {
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Reference<? extends Object> referenceRemove = a.f2515a.remove();
                    if (referenceRemove instanceof b) {
                        b bVar = (b) referenceRemove;
                        bVar.f2517a.a((n.a<? extends n<T>>) bVar.f2518b);
                    }
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }
}
