package defpackage;

import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabbedModeTabPersistencePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v53 implements q23 {
    public static final Object h = new Object();
    public static final Object i = new Object();
    public static final AtomicBoolean j = new AtomicBoolean();
    public static u53 k;
    public static i9<Void> l;
    public final int a;
    public final int b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public TabContentManager f;
    public boolean g;

    /* JADX INFO: compiled from: TabbedModeTabPersistencePolicy.java */
    public class a extends i9<Void> {
        public final Callback<List<String>> h;
        public String[] i;
        public String[] j;
        public final nw2<SparseBooleanArray> k;
        public SparseBooleanArray l;

        public a(Callback<List<String>> callback, nw2<SparseBooleanArray> nw2Var) {
            this.h = callback;
            this.k = nw2Var;
        }

        @Override // defpackage.i9
        public final Void b() {
            if (v53.this.g) {
                return null;
            }
            this.i = s33.a().list();
            this.j = new File(PathUtils.getThumbnailCacheDirectory()).list();
            this.l = this.k.get();
            return null;
        }

        @Override // defpackage.i9
        public final void k(Void r1) {
            synchronized (v53.i) {
                v53.l = null;
            }
        }

        @Override // defpackage.i9
        public final void l(Void r10) {
            String[] strArr;
            if (v53.this.g) {
                return;
            }
            n53 n53VarR = oj2.r();
            ArrayList arrayList = new ArrayList();
            String[] strArr2 = this.i;
            if (strArr2 != null) {
                for (String str : strArr2) {
                    Pair<Integer, Boolean> pairB = t33.b(str);
                    if (pairB != null) {
                        int iIntValue = ((Integer) pairB.first).intValue();
                        if (((o53) n53VarR).e(iIntValue) == null && !this.l.get(iIntValue)) {
                            arrayList.add(str);
                        }
                    }
                }
            }
            this.h.onResult(arrayList);
            if (v53.this.f != null && (strArr = this.j) != null) {
                for (String str2 : strArr) {
                    try {
                        int i = Integer.parseInt(str2);
                        if (((o53) n53VarR).e(i) == null && !this.l.get(i)) {
                            v53.this.f.f(i);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            synchronized (v53.i) {
                v53.l = null;
            }
        }
    }

    public v53(int i2, int i3, boolean z, boolean z2) {
        this.a = i2;
        this.b = i2 == 0 ? 1 : 0;
        this.c = z;
        this.d = z2;
        this.e = i3;
    }

    public static String q(int i2) {
        String string = Integer.toString(i2);
        Object obj = z23.A;
        return C0175b.j("tab_state", string);
    }

    @Override // defpackage.q23
    public final void a(a33 a33Var) {
        synchronized (i) {
            i9<Void> i9Var = l;
            if (i9Var != null) {
                i9Var.a(true);
            }
            a aVar = new a(a33Var, new hs(10, this));
            l = aVar;
            aVar.c(i9.e);
        }
    }

    @Override // defpackage.q23
    public final void b(TabContentManager tabContentManager) {
        this.f = tabContentManager;
    }

    @Override // defpackage.q23
    public final void c(boolean z) {
        j.set(z);
    }

    @Override // defpackage.q23
    public final String d() {
        return q(this.a);
    }

    @Override // defpackage.q23
    public final void destroy() {
        this.f = null;
        this.g = true;
    }

    @Override // defpackage.q23
    public final void e() {
        u53 u53Var = k;
        if (u53Var == null) {
            return;
        }
        try {
            u53Var.g();
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // defpackage.q23
    public final boolean f() {
        return j.get();
    }

    @Override // defpackage.q23
    public final boolean g(oi2 oi2Var) {
        Object obj = ThreadUtils.a;
        boolean z = SharedPreferencesManager.getInstance().readBoolean("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_FILE_MIGRATION", false);
        boolean z2 = SharedPreferencesManager.getInstance().readBoolean("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_MULTI_INSTANCE_FILE_MIGRATION", false);
        if (z && z2) {
            return false;
        }
        synchronized (h) {
            if (k != null) {
                return true;
            }
            u53 u53Var = new u53(this, z, z2);
            u53Var.d(oi2Var);
            k = u53Var;
            return true;
        }
    }

    @Override // defpackage.q23
    public final void h(int i2) {
        oj2.R(i2, "Tabs.CountAtStartup");
    }

    @Override // defpackage.q23
    public final void i(b33 b33Var, final int i2) {
        y13 y13VarF = ((o53) oj2.r()).f(i2);
        if (y13VarF != null) {
            for (int i3 = 0; i3 < y13VarF.y().size(); i3++) {
                TabModel tabModel = (TabModel) y13VarF.y().get(i3);
                while (tabModel.getCount() > 0) {
                    tabModel.o(tabModel.getTabAt(0));
                }
            }
        }
        synchronized (i) {
            i9<Void> i9Var = l;
            if (i9Var != null) {
                i9Var.a(true);
            }
            a aVar = new a(b33Var, new nw2() { // from class: t53
                @Override // defpackage.nw2
                public final Object get() {
                    return this.e.p(i2);
                }
            });
            l = aVar;
            aVar.c(i9.e);
        }
    }

    @Override // defpackage.q23
    public final List<String> j() {
        ArrayList arrayList = new ArrayList();
        if (this.c || this.d) {
            arrayList.add(q(this.b));
        }
        return arrayList;
    }

    @Override // defpackage.q23
    public final boolean l() {
        return this.c;
    }

    @Override // defpackage.q23
    public final boolean m() {
        return true;
    }

    @Override // defpackage.q23
    public final File n() {
        return s33.a();
    }

    @Override // defpackage.q23
    public final void o() {
        synchronized (i) {
            i9<Void> i9Var = l;
            if (i9Var != null) {
                i9Var.a(true);
            }
        }
    }

    public final SparseBooleanArray p(int i2) throws Throwable {
        DataInputStream dataInputStream;
        Exception e;
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (int i3 = 0; i3 < this.e; i3++) {
            if (i3 != i2) {
                File file = new File(s33.a(), q(i3));
                if (file.exists()) {
                    DataInputStream dataInputStream2 = null;
                    try {
                        dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                    } catch (Exception e2) {
                        dataInputStream = null;
                        e = e2;
                    } catch (Throwable th) {
                        th = th;
                        dataInputStream = dataInputStream2;
                        a8.s(dataInputStream);
                        throw th;
                    }
                    try {
                        z23.m(dataInputStream, null, sparseBooleanArray);
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            Log.e("cr_tabmodel", "Unable to read state for " + file.getName() + ": " + e);
                        } catch (Throwable th2) {
                            th = th2;
                            dataInputStream2 = dataInputStream;
                            dataInputStream = dataInputStream2;
                            a8.s(dataInputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        a8.s(dataInputStream);
                        throw th;
                    }
                    a8.s(dataInputStream);
                } else {
                    continue;
                }
            }
        }
        return sparseBooleanArray;
    }
}
