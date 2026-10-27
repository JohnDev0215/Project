package org.chromium.components.signin.identitymanager;

import J.N;
import defpackage.tg;
import defpackage.xf1;
import defpackage.z1;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes3.dex */
public class AccountTrackerService implements z1 {
    public final long e;
    public boolean i;
    public final org.chromium.base.b<a> h = new org.chromium.base.b<>();
    public int g = 0;
    public final ConcurrentLinkedDeque f = new ConcurrentLinkedDeque();
    public boolean j = false;

    public interface a {
        void c(List list);
    }

    @CalledByNative
    public AccountTrackerService(long j) {
        this.e = j;
    }

    @Override // defpackage.z1
    public final void F() {
        if (N.MF7huoxQ()) {
            return;
        }
        if (this.g == 1) {
            this.j = true;
        } else {
            b(true);
        }
    }

    public final void a(List<CoreAccountInfo> list, boolean z) {
        N.MyqrKXjt(this.e, (CoreAccountInfo[]) list.toArray(new CoreAccountInfo[0]));
        this.g = 2;
        if (this.j) {
            b(true);
            this.j = false;
            return;
        }
        ConcurrentLinkedDeque concurrentLinkedDeque = this.f;
        for (Runnable runnable = (Runnable) concurrentLinkedDeque.poll(); runnable != null; runnable = (Runnable) concurrentLinkedDeque.poll()) {
            runnable.run();
        }
        Iterator<a> it = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).c(list);
            }
        }
    }

    public final void b(boolean z) {
        Object obj = ThreadUtils.a;
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        int i = 1;
        this.g = 1;
        if (!this.i) {
            this.i = true;
            accountManagerFacadeProvider.a(this);
        }
        if (N.MF7huoxQ()) {
            accountManagerFacadeProvider.i().g(new tg(this, i, z));
        } else {
            accountManagerFacadeProvider.b().g(new xf1(this, i, accountManagerFacadeProvider, z));
        }
    }

    public final void c(Runnable runnable) {
        Object obj = ThreadUtils.a;
        int i = this.g;
        ConcurrentLinkedDeque concurrentLinkedDeque = this.f;
        if (i == 0) {
            concurrentLinkedDeque.add(runnable);
            b(false);
        } else if (i == 1) {
            concurrentLinkedDeque.add(runnable);
        } else {
            if (i != 2) {
                return;
            }
            runnable.run();
        }
    }

    @Override // defpackage.z1
    public final void q() {
        if (N.MF7huoxQ()) {
            if (this.g == 1) {
                this.j = true;
            } else {
                b(true);
            }
        }
    }
}
