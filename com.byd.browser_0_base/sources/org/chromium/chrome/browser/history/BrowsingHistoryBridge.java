package org.chromium.chrome.browser.history;

import J.N;
import defpackage.b60;
import defpackage.dv0;
import defpackage.ev0;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.history.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class BrowsingHistoryBridge implements b {
    public b.a a;
    public long b;
    public boolean c;
    public boolean d;

    public BrowsingHistoryBridge(Profile profile) {
        this.b = N.Mj1_ZHGA(this, profile);
    }

    @CalledByNative
    public static void createHistoryItemAndAddToList(List<ev0> list, GURL gurl, String str, String str2, long j, long[] jArr, boolean z) {
        list.add(new ev0(gurl, str2, j, jArr, z));
    }

    public final void a() {
        if (this.c) {
            this.d = true;
            return;
        }
        this.c = true;
        this.d = false;
        N.MVl9wW5M(this.b, this);
    }

    @CalledByNative
    public void hasOtherFormsOfBrowsingData(boolean z) {
        b.a aVar = this.a;
        if (aVar != null) {
            a aVar2 = (a) aVar;
            aVar2.p = z;
            aVar2.T();
            aVar2.j.i.u();
        }
    }

    @CalledByNative
    public void onHistoryDeleted() {
        b.a aVar = this.a;
        if (aVar != null) {
            a aVar2 = (a) aVar;
            if (aVar2.q) {
                return;
            }
            dv0 dv0Var = aVar2.j;
            dv0Var.r.b();
            dv0Var.i.e();
            aVar2.U();
        }
    }

    @CalledByNative
    public void onQueryHistoryComplete(List<ev0> list, boolean z) {
        boolean z2;
        b.a aVar = this.a;
        if (aVar != null) {
            a aVar2 = (a) aVar;
            if (aVar2.q) {
                return;
            }
            boolean z3 = aVar2.v;
            TreeSet treeSet = aVar2.i;
            if (z3) {
                aVar2.h = 0;
                Iterator it = treeSet.iterator();
                while (it.hasNext()) {
                    Iterator it2 = ((b60.i) it.next()).b.iterator();
                    while (it2.hasNext()) {
                        ((b60.k) it2.next()).a = -1;
                    }
                }
                treeSet.clear();
                aVar2.o();
                aVar2.v = false;
            }
            if (!aVar2.r && list.size() > 0 && !aVar2.t) {
                aVar2.S();
                aVar2.r = true;
            }
            if (aVar2.L()) {
                treeSet.remove(treeSet.last());
                aVar2.O();
                aVar2.o();
            }
            for (ev0 ev0Var : list) {
                Date date = new Date(ev0Var.b());
                Iterator it3 = treeSet.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    }
                    b60.i iVar = (b60.i) it3.next();
                    if (b60.E(iVar.a, date) == 0) {
                        iVar.a(ev0Var);
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    b60.c cVar = new b60.c(ev0Var.b());
                    cVar.b = true;
                    b60.i iVar2 = new b60.i(ev0Var.b());
                    iVar2.a(cVar);
                    iVar2.a(ev0Var);
                    treeSet.add(iVar2);
                }
            }
            aVar2.O();
            aVar2.o();
            aVar2.s = false;
            aVar2.u = z;
            if (z) {
                aVar2.V();
            }
        }
    }

    @CalledByNative
    public void onRemoveComplete() {
        this.c = false;
        if (this.d) {
            a();
        }
    }

    @CalledByNative
    public void onRemoveFailed() {
        this.c = false;
        if (this.d) {
            a();
        }
    }
}
