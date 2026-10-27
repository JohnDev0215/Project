package androidx.activity;

import a.a.b;
import a.l.d.r;
import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f1378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f1379b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements i, a.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g f1380a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f1381b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.a.a f1382c;

        public LifecycleOnBackPressedCancellable(g gVar, b bVar) {
            this.f1380a = gVar;
            this.f1381b = bVar;
            gVar.a(this);
        }

        @Override // a.a.a
        public void cancel() {
            ((l) this.f1380a).f1093a.g(this);
            this.f1381b.f1b.remove(this);
            a.a.a aVar = this.f1382c;
            if (aVar != null) {
                aVar.cancel();
                this.f1382c = null;
            }
        }

        @Override // a.n.i
        public void g(k kVar, g.a aVar) {
            if (aVar == g.a.ON_START) {
                OnBackPressedDispatcher onBackPressedDispatcher = OnBackPressedDispatcher.this;
                b bVar = this.f1381b;
                onBackPressedDispatcher.f1379b.add(bVar);
                a aVar2 = onBackPressedDispatcher.new a(bVar);
                bVar.f1b.add(aVar2);
                this.f1382c = aVar2;
                return;
            }
            if (aVar != g.a.ON_STOP) {
                if (aVar == g.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                a.a.a aVar3 = this.f1382c;
                if (aVar3 != null) {
                    aVar3.cancel();
                }
            }
        }
    }

    public class a implements a.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f1384a;

        public a(b bVar) {
            this.f1384a = bVar;
        }

        @Override // a.a.a
        public void cancel() {
            OnBackPressedDispatcher.this.f1379b.remove(this.f1384a);
            this.f1384a.f1b.remove(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f1378a = runnable;
    }

    public void a() {
        Iterator<b> itDescendingIterator = this.f1379b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.f0a) {
                r rVar = r.this;
                rVar.A(true);
                if (rVar.h.f0a) {
                    rVar.S();
                    return;
                } else {
                    rVar.g.a();
                    return;
                }
            }
        }
        Runnable runnable = this.f1378a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
