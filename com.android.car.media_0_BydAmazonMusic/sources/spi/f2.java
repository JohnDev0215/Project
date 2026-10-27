package spi;

import androidx.constraintlayout.helper.widget.Carousel;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f2 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ f2(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((Carousel) this.b).lambda$updateItems$0();
                break;
            case 1:
                l9 l9Var = (l9) this.b;
                Set<Integer> set = l9.e;
                l9Var.a();
                break;
            case 2:
                v7 v7Var = (v7) this.b;
                Iterator it = v7Var.a.a.iterator();
                while (it.hasNext()) {
                    NetStateChangeReceiver.a(v7Var.a, (u7) it.next(), NetStateChangeReceiver.c.get());
                }
                break;
            case 3:
                w7 w7Var = (w7) this.b;
                Iterator it2 = w7Var.a.a.iterator();
                while (it2.hasNext()) {
                    NetStateChangeReceiver.b(w7Var.a, (u7) it2.next(), NetStateChangeReceiver.d.get());
                }
                break;
            case 4:
                m mVar = (m) this.b;
                mVar.j.b(mVar.m);
                break;
            default:
                ((com.android.car.ui.toolbar.b) this.b).b();
                break;
        }
    }
}
