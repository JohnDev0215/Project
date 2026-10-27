package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.oj2;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: MessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public final org.chromium.base.b<b> e = new org.chromium.base.b<>();
    public final int f;

    /* JADX INFO: compiled from: MessageService.java */
    public interface a {
    }

    /* JADX INFO: compiled from: MessageService.java */
    public interface b {
        void a(int i);

        void b(int i, a aVar);
    }

    public g(int i) {
        this.f = i;
    }

    public static void d(int i, String str) {
        oj2.U(i, 4, String.format("GridTabSwitcher.%s.DisableReason", str));
    }

    public void c(d dVar) {
        this.e.a(dVar);
    }

    public final void e(a aVar) {
        Iterator<b> it = this.e.iterator();
        while (true) {
            b.a aVar2 = (b.a) it;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((b) aVar2.next()).b(this.f, aVar);
            }
        }
    }

    public final void g() {
        Iterator<b> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).a(this.f);
            }
        }
    }
}
