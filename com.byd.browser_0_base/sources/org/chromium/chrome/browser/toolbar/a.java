package org.chromium.chrome.browser.toolbar;

import defpackage.dz2;
import defpackage.ez2;
import defpackage.y13;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: TabCountProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final org.chromium.base.b<InterfaceC0115a> a = new org.chromium.base.b<>();
    public y13 b;
    public dz2 c;
    public ez2 d;
    public int e;
    public boolean f;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.toolbar.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TabCountProvider.java */
    public interface InterfaceC0115a {
        void c(int i, boolean z);
    }

    public final void a(InterfaceC0115a interfaceC0115a) {
        this.a.a(interfaceC0115a);
        y13 y13Var = this.b;
        if (y13Var == null || !y13Var.t()) {
            return;
        }
        interfaceC0115a.c(this.b.u().c().e.getCount(), this.b.r());
    }

    public final void b() {
        if (!this.b.t()) {
            return;
        }
        int count = this.b.u().c().e.getCount();
        boolean zR = this.b.r();
        if (this.e == count && this.f == zR) {
            return;
        }
        this.e = count;
        this.f = zR;
        Iterator<InterfaceC0115a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((InterfaceC0115a) aVar.next()).c(count, zR);
            }
        }
    }
}
