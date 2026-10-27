package defpackage;

import android.os.Handler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: NavigationInfoCaptureTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public final class bl1 {
    public final Callback<Tab> a;
    public final Handler b = new Handler(ThreadUtils.d());
    public final LinkedList c = new LinkedList();
    public boolean d;
    public boolean e;
    public boolean f;

    /* JADX INFO: compiled from: NavigationInfoCaptureTrigger.java */
    public class a implements Runnable {
        public final Callable<Boolean> e;
        public final Tab f;

        public a(Tab tab, Callable<Boolean> callable) {
            this.e = callable;
            this.f = tab;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (this.e.call().booleanValue()) {
                    bl1 bl1Var = bl1.this;
                    bl1Var.a.onResult(this.f);
                    bl1Var.f = true;
                    bl1Var.b();
                }
            } catch (Exception e) {
                throw new uv(e);
            }
        }
    }

    public bl1(wb wbVar) {
        this.a = wbVar;
    }

    public final void a(Tab tab, Callable<Boolean> callable, long j) {
        if (this.f) {
            return;
        }
        a aVar = new a(tab, callable);
        this.c.add(aVar);
        this.b.postDelayed(aVar, j);
    }

    public final void b() {
        LinkedList linkedList = this.c;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.b.removeCallbacks((Runnable) it.next());
        }
        linkedList.clear();
    }
}
