package defpackage;

import android.os.StrictMode;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import defpackage.z23;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabModelOrchestrator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class v13 {
    public z23 a;
    public a23 b;
    public q23 c;
    public boolean d;

    public final void b() {
        if (this.d) {
            z23 z23Var = this.a;
            if (z23Var != null) {
                z23Var.n = true;
                if (z23Var.a != null) {
                    y13 y13Var = z23Var.d;
                    y13Var.g(false).l(z23Var.a);
                    y13Var.g(true).l(z23Var.a);
                    z23Var.a = null;
                }
                k23 k23Var = z23Var.b;
                if (k23Var != null) {
                    k23Var.a.a();
                    for (Tab tab : (Tab[]) k23Var.d.toArray(new Tab[0])) {
                        k23Var.c(tab);
                    }
                }
                z23Var.c.destroy();
                z23.g gVar = z23Var.k;
                if (gVar != null) {
                    z23.c cVar = gVar.b;
                    if (cVar != null) {
                        cVar.a(true);
                    }
                    gVar.c.a();
                }
                z23Var.g.clear();
                z23Var.i.clear();
                z23.f fVar = z23Var.l;
                if (fVar != null) {
                    fVar.a(false);
                }
                z23.e eVar = z23Var.m;
                if (eVar != null) {
                    eVar.a(true);
                }
            }
            a23 a23Var = this.b;
            if (a23Var != null) {
                a23Var.B();
            }
        }
    }

    public final void c(boolean z) {
        HashSet hashSet;
        DataInputStream dataInputStreamG;
        z23 z23Var = this.a;
        ArrayDeque arrayDeque = z23Var.i;
        q23 q23Var = z23Var.c;
        q23Var.o();
        q23Var.e();
        z23Var.o = false;
        z23Var.p = z;
        z23Var.q = new SparseIntArray();
        z23Var.r = new SparseIntArray();
        try {
            z23.c();
            r23 r23Var = z23Var.t;
            y13 y13Var = z23Var.d;
            if (r23Var != null && (dataInputStreamG = r23Var.g()) != null) {
                z23Var.x = true;
                z23.m(dataInputStreamG, new y23(z23Var, false, y13Var.r()), null);
            }
            ArrayList arrayList = z23Var.u;
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = z23Var.v;
                    if (!zHasNext) {
                        break;
                    }
                    Pair pair = (Pair) it.next();
                    DataInputStream dataInputStream = (DataInputStream) ((i9) pair.first).g();
                    if (dataInputStream != null) {
                        hashSet.add((String) pair.second);
                        q23Var.c(true);
                        z23.m(dataInputStream, new y23(z23Var, arrayDeque.size() != 0, y13Var.r()), null);
                    }
                }
                if (!hashSet.isEmpty()) {
                    n3.X0("Android.MergeState.ColdStart");
                }
                arrayList.clear();
            }
        } catch (Exception e) {
            Log.i("cr_tabmodel", "loadState exception: " + e.toString(), e);
        }
        q23Var.h(arrayDeque.size());
        Iterator<z23.i> it2 = z23Var.f.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                return;
            }
            z23.i iVar = (z23.i) aVar.next();
            arrayDeque.size();
            iVar.getClass();
        }
    }

    public final void d(TabContentManager tabContentManager) {
        this.b.F(tabContentManager);
        this.c.b(tabContentManager);
        z23 z23Var = this.a;
        z23Var.getClass();
        u23 u23Var = new u23(z23Var);
        y13 y13Var = z23Var.d;
        k23 k23Var = new k23(y13Var);
        z23Var.b = k23Var;
        k23Var.b(new v23(z23Var, u23Var));
        z23Var.a = new w23(z23Var);
        y13Var.g(false).j(z23Var.a);
        y13Var.g(true).j(z23Var.a);
    }

    public final void e(boolean z) {
        z23 z23Var = this.a;
        if (z) {
            while (true) {
                ArrayDeque arrayDeque = z23Var.i;
                if (arrayDeque.isEmpty() || z23Var.q.size() != 0 || z23Var.r.size() != 0) {
                    break;
                }
                TraceEvent traceEventH0 = TraceEvent.H0("LoadFirstTabState", null);
                try {
                    z23Var.o((z23.j) arrayDeque.removeFirst(), true);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        }
        z23Var.k();
    }

    public final void f() {
        this.b.c();
        z23 z23Var = this.a;
        y13 y13Var = z23Var.d;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            z23.e eVar = z23Var.m;
            if (eVar != null) {
                eVar.a(true);
            }
            try {
                z23Var.q(z23Var.t().a);
            } catch (IOException e) {
                Log.w("cr_tabmodel", "Error while saving tabs state; will attempt to continue...", e);
            }
            Tab tabP = z7.p(y13Var.g(false));
            z23Var.b(tabP);
            Tab tabP2 = z7.p(y13Var.g(true));
            z23Var.b(tabP2);
            z23.f fVar = z23Var.l;
            if (fVar != null) {
                if (fVar.a(false)) {
                    z23.f fVar2 = z23Var.l;
                    if (!fVar2.l) {
                        z23Var.b(fVar2.h);
                    }
                }
                z23Var.l = null;
            }
            ArrayDeque<Tab> arrayDeque = z23Var.g;
            for (Tab tab : arrayDeque) {
                int id = tab.getId();
                boolean zIsIncognito = tab.isIncognito();
                try {
                    m33 m33VarK = z7.k(tab);
                    if (m33VarK != null) {
                        t33.e(new File(z23Var.g(), t33.a(id, zIsIncognito)), m33VarK, zIsIncognito);
                    }
                } catch (OutOfMemoryError unused) {
                    Log.e("cr_tabmodel", "Out of memory error while attempting to save tab state.  Erasing.");
                    z23Var.f(id, zIsIncognito);
                }
            }
            arrayDeque.clear();
            if (z23.i() || z23.h()) {
                if (tabP != null) {
                    CriticalPersistedTabData.m(tabP).l();
                }
                if (tabP2 != null) {
                    CriticalPersistedTabData.m(tabP2).l();
                }
                HashMap map = l02.l;
                m02.e().k();
                if (m02.j == null) {
                    m02.j = new rh0();
                }
                m02.j.k();
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    public void a(int i) {
    }
}
