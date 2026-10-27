package defpackage;

import J.N;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import defpackage.ll;
import defpackage.s33;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z23 {
    public static final Object A = new Object();
    public static final sh B = new sh("CriticalPersistedTabData", "critical_persisted_tab_data_save_only", false);
    public w23 a;
    public k23 b;
    public final q23 c;
    public final y13 d;
    public final gz2 e;
    public g k;
    public f l;
    public e m;
    public boolean n;
    public boolean o;
    public boolean p;
    public SparseIntArray q;
    public SparseIntArray r;
    public final oi2 s;
    public final r23 t;
    public final ArrayList u;
    public final HashSet v;
    public byte[] w;
    public boolean x;
    public t23 y;
    public s23 z;
    public final ArrayDeque g = new ArrayDeque();
    public final ArrayDeque h = new ArrayDeque();
    public final ArrayDeque i = new ArrayDeque();
    public final HashSet j = new HashSet();
    public final org.chromium.base.b<i> f = new org.chromium.base.b<>();

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            z23.this.s();
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class b extends ac<Void> {
        public final /* synthetic */ String h;

        public b(String str) {
            this.h = str;
        }

        @Override // defpackage.i9
        public final Object b() {
            Object obj = z23.A;
            z23.this.e(this.h);
            return null;
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class c extends i9<m33> {
        public final j h;
        public m33 i;
        public final long j;

        public c(j jVar) {
            this.h = jVar;
            TraceEvent.I0(jVar.a, "LoadTabTask");
            TraceEvent.I0(jVar.a, "LoadTabState");
            this.j = SystemClock.elapsedRealtime();
        }

        @Override // defpackage.i9
        public final m33 b() {
            z23 z23Var = z23.this;
            if (!z23Var.n && !i()) {
                try {
                    return t33.d(z23Var.g(), this.h.a);
                } catch (Exception e) {
                    Log.w("cr_tabmodel", "Unable to read state: " + e);
                }
            }
            return null;
        }

        @Override // defpackage.i9
        public final void l(m33 m33Var) {
            m33 m33Var2 = m33Var;
            j jVar = this.h;
            TraceEvent.F(jVar.a, "LoadTabState");
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            objArr[0] = m33Var2 == null ? "Null" : "Exists";
            oj2.b0(SystemClock.elapsedRealtime() - this.j, String.format(locale, "Tabs.SavedTabLoadTime.TabState.%s", objArr));
            this.i = m33Var2;
            TraceEvent.F(jVar.a, "LoadTabTask");
            z23 z23Var = z23.this;
            if (z23Var.n || i()) {
                return;
            }
            z23.a(z23Var, jVar, this.i, null);
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public interface d {
        void e(int i, int i2, Boolean bool, String str, boolean z, boolean z2);
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class e extends i9<Void> {
        public h h;

        public e() {
        }

        @Override // defpackage.i9
        public final Void b() {
            if (this.h == null || i()) {
                return null;
            }
            byte[] bArr = this.h.a;
            Object obj = z23.A;
            z23.this.q(bArr);
            return null;
        }

        @Override // defpackage.i9
        public final void l(Void r4) {
            z23 z23Var = z23.this;
            if (z23Var.n || i()) {
                this.h = null;
                return;
            }
            if (z23Var.m != this) {
                return;
            }
            z23Var.m = null;
            Iterator<i> it = z23Var.f.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    this.h = null;
                    return;
                }
                ((i) aVar.next()).getClass();
            }
        }

        @Override // defpackage.i9
        public final void m() {
            z23 z23Var = z23.this;
            if (z23Var.n || i()) {
                return;
            }
            try {
                this.h = z23Var.t();
            } catch (IOException unused) {
                this.h = null;
            }
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class f extends i9<Void> {
        public final Tab h;
        public final int i;
        public m33 j;
        public final boolean k;
        public boolean l;

        public f(Tab tab) {
            this.h = tab;
            this.i = tab.getId();
            this.k = tab.isIncognito();
        }

        @Override // defpackage.i9
        public final Void b() throws Throwable {
            boolean z;
            int i = this.i;
            boolean z2 = this.k;
            m33 m33Var = this.j;
            Object obj = z23.A;
            z23 z23Var = z23.this;
            z23Var.getClass();
            if (m33Var == null) {
                z = false;
            } else {
                try {
                    t33.e(new File(z23Var.g(), t33.a(i, z2)), m33Var, z2);
                    z = true;
                } catch (OutOfMemoryError unused) {
                    Log.e("tabmodel", "Out of memory error while attempting to save tab state.  Erasing.");
                    z23Var.f(i, z2);
                    z = false;
                }
            }
            this.l = z;
            return null;
        }

        @Override // defpackage.i9
        public final void l(Void r5) {
            z23 z23Var = z23.this;
            if (z23Var.n || i()) {
                return;
            }
            if (this.l) {
                Tab tab = this.h;
                int i = 0;
                if (!tab.isDestroyed()) {
                    p33.d(tab).f(0);
                }
                tab.A(z23.i() || z23.h());
                if (z23.i() || z23.h()) {
                    while (true) {
                        int iM37SqSAy = 5;
                        if (a8.P()) {
                            sk skVar = bq.a;
                            iM37SqSAy = N.M37SqSAy("CriticalPersistedTabData", "migrate_to_critical_persisted_tab_data_batch_size", 5);
                        }
                        if (i >= iM37SqSAy) {
                            break;
                        }
                        ArrayDeque arrayDeque = z23Var.h;
                        if (arrayDeque.isEmpty()) {
                            break;
                        }
                        Tab tab2 = (Tab) arrayDeque.pollFirst();
                        if (tab2 != null && !tab2.isDestroyed()) {
                            CriticalPersistedTabData.m(tab2).z = true;
                            tab2.A(true);
                        }
                        i++;
                    }
                }
            }
            z23Var.l = null;
            z23Var.r();
        }

        @Override // defpackage.i9
        public final void m() {
            if (z23.this.n || i()) {
                return;
            }
            this.j = z7.k(this.h);
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public class g {
        public final j a;
        public c b;
        public final ll c = new ll();

        public g(j jVar) {
            this.a = jVar;
        }

        public final void a() {
            z23 z23Var = z23.this;
            c cVar = z23Var.new c(this.a);
            this.b = cVar;
            cVar.d(z23Var.s);
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public static class h {
        public final byte[] a;

        public h(byte[] bArr) {
            this.a = bArr;
        }
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public static final class j {
        public final int a;
        public final int b;
        public final String c;
        public final Boolean d;
        public final Boolean e;

        public j(int i, int i2, Boolean bool, String str, Boolean bool2) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = bool;
            this.e = bool2;
        }
    }

    public z23(q23 q23Var, a23 a23Var, gz2 gz2Var) {
        this.c = q23Var;
        this.d = a23Var;
        this.e = gz2Var;
        d73 d73Var = d73.k;
        oi2 oi2VarA = PostTask.a(d73Var);
        this.s = oi2VarA;
        this.u = new ArrayList();
        this.v = new HashSet();
        boolean zG = q23Var.g(oi2VarA);
        q23Var.k(oi2VarA);
        b73 b73VarA = oi2VarA;
        if (q23Var.f()) {
            return;
        }
        b73VarA = zG ? b73VarA : PostTask.e.get(d73Var.d).a(d73Var);
        r23 r23Var = new r23(this, q23Var.d());
        r23Var.d(b73VarA);
        this.t = r23Var;
        int iD = SharedPreferencesManager.getInstance().d(-1, "org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID");
        if (iD != -1) {
            if (h()) {
                s23 s23Var = new s23(this, mm0.j(iD), iD, b73VarA);
                s23Var.d(b73VarA);
                this.z = s23Var;
            } else {
                t23 t23Var = new t23(this, iD);
                t23Var.d(b73VarA);
                this.y = t23Var;
            }
        }
        if (q23Var.l()) {
            for (String str : q23Var.j()) {
                r23 r23Var2 = new r23(this, str);
                r23Var2.d(b73VarA);
                this.u.add(Pair.create(r23Var2, str));
            }
        }
        if (i() || h()) {
            return;
        }
        boolean[] zArr = {false, true};
        for (int i2 = 0; i2 < 2; i2++) {
            boolean z = zArr[i2];
            y13 y13Var = this.d;
            int count = y13Var.g(z).getCount();
            for (int i3 = 0; i3 < count; i3++) {
                CriticalPersistedTabData.m(y13Var.g(z).getTabAt(i3)).a();
            }
        }
    }

    public static void a(z23 z23Var, j jVar, m33 m33Var, ui2 ui2Var) {
        z23Var.getClass();
        boolean zJ = j(jVar, m33Var, ui2Var);
        if (!((zJ && z23Var.p) || (!zJ && z23Var.o))) {
            z23Var.n(jVar, m33Var, ui2Var, false);
        }
        z23Var.k();
    }

    public static void c() {
        File[] fileArrListFiles;
        int i2 = 0;
        if (SharedPreferencesManager.getInstance().readBoolean("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_COMPUTED_MAX_ID", false)) {
            return;
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Object obj = s33.a;
            File[] fileArrListFiles2 = s33.a.a.listFiles();
            if (fileArrListFiles2 != null) {
                int iMax = 0;
                for (File file : fileArrListFiles2) {
                    if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                        for (File file2 : fileArrListFiles) {
                            Pair<Integer, Boolean> pairB = t33.b(file2.getName());
                            if (pairB != null) {
                                iMax = Math.max(iMax, ((Integer) pairB.first).intValue());
                            } else {
                                String name = file2.getName();
                                if (name.startsWith("tab_state") && !name.endsWith(".new")) {
                                    DataInputStream dataInputStream = null;
                                    try {
                                        DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new FileInputStream(file2)));
                                        try {
                                            iMax = Math.max(iMax, m(dataInputStream2, null, null));
                                            a8.s(dataInputStream2);
                                        } catch (Throwable th) {
                                            th = th;
                                            dataInputStream = dataInputStream2;
                                            a8.s(dataInputStream);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
                i2 = iMax;
            }
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            t03 t03VarA = t03.a();
            AtomicInteger atomicInteger = t03VarA.a;
            int i3 = i2 - atomicInteger.get();
            if (i3 >= 0) {
                atomicInteger.addAndGet(i3);
                t03VarA.b.m(atomicInteger.get(), "org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID");
            }
            SharedPreferencesManager.getInstance().l("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_COMPUTED_MAX_ID", true);
        } catch (Throwable th3) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th3;
        }
    }

    public static boolean h() {
        return bq.v.a() && !B.c();
    }

    public static boolean i() {
        return bq.v.a() && B.c();
    }

    public static boolean j(j jVar, m33 m33Var, ui2 ui2Var) {
        if (m33Var != null) {
            return m33Var.h;
        }
        Boolean bool = jVar.d;
        if (bool != null) {
            return bool.booleanValue();
        }
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        if (ui2Var == null) {
            return false;
        }
        return mm0.j(jVar.a).booleanValue();
    }

    public static int m(DataInputStream dataInputStream, d dVar, SparseBooleanArray sparseBooleanArray) throws IOException {
        boolean z;
        boolean z2;
        Boolean boolValueOf;
        int i2 = dataInputStream.readInt();
        if (i2 == 5) {
            z = false;
            z2 = false;
        } else {
            if (i2 < 3) {
                return 0;
            }
            z2 = i2 < 5;
            z = i2 < 4;
        }
        int i3 = dataInputStream.readInt();
        int i4 = z2 ? -1 : dataInputStream.readInt();
        int i5 = dataInputStream.readInt();
        int i6 = dataInputStream.readInt();
        if (i3 < 0 || i5 >= i3 || i6 >= i3) {
            throw new IOException();
        }
        int i7 = 0;
        int i8 = 0;
        while (i8 < i3) {
            int i9 = dataInputStream.readInt();
            String utf = z ? "" : dataInputStream.readUTF();
            if (i9 >= i7) {
                i7 = i9 + 1;
            }
            int i10 = i7;
            if (sparseBooleanArray != null) {
                sparseBooleanArray.append(i9, true);
            }
            if (i4 < 0) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(i8 < i4);
            }
            Boolean bool = boolValueOf;
            if (dVar != null) {
                dVar.e(i8, i9, bool, utf, i8 == i6, i8 == i5);
            }
            i8++;
            i7 = i10;
        }
        return i7;
    }

    public final void b(Tab tab) {
        if (tab == null || tab.isDestroyed()) {
            return;
        }
        ArrayDeque arrayDeque = this.g;
        if (arrayDeque.contains(tab) || p33.d(tab).j == 0) {
            return;
        }
        GURL url = tab.getUrl();
        if (url != null && url.i().equals("content")) {
            return;
        }
        if (!bk3.l(tab.getUrl()) || tab.g() || tab.i()) {
            arrayDeque.addLast(tab);
        }
    }

    public final void d(String str, boolean z) {
        if (z) {
            new b(str).d(this.s);
        } else {
            PostTask.d(d73.g, new ul2(this, 1, str));
        }
    }

    public final void e(String str) {
        Object obj = ThreadUtils.a;
        File file = new File(g(), str);
        if (file.exists()) {
            if (!file.delete()) {
                Log.e("cr_tabmodel", "Failed to delete file: " + file);
            }
            HashSet hashSet = this.v;
            if (hashSet.remove(str) && hashSet.isEmpty()) {
                this.c.c(false);
            }
        }
    }

    public final void f(int i2, boolean z) {
        File file = new File(g(), t33.a(i2, z));
        if (!file.exists() || file.delete()) {
            return;
        }
        Log.e("cr_TabState", "Failed to delete TabState: " + file);
    }

    public final File g() {
        return this.c.n();
    }

    public final void k() {
        if (this.n) {
            return;
        }
        ArrayDeque arrayDeque = this.i;
        int i2 = 1;
        if (!arrayDeque.isEmpty()) {
            g gVar = new g((j) arrayDeque.removeFirst());
            this.k = gVar;
            if (!h()) {
                gVar.a();
                return;
            }
            j jVar = gVar.a;
            Boolean boolJ = jVar.d;
            if (boolJ == null) {
                boolJ = mm0.j(jVar.a);
            }
            if (boolJ == null) {
                gVar.a();
                return;
            }
            int i3 = jVar.a;
            TraceEvent.I0(i3, "LoadCriticalPersistedTabData");
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            boolean zBooleanValue = boolJ.booleanValue();
            ll.b bVarB = gVar.c.b(new xc0(jElapsedRealtime, i2, gVar));
            CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
            m02 m02VarA = m02.a(CriticalPersistedTabData.class, zBooleanValue);
            m02VarA.f().d(i3, m02VarA.e, bVarB);
            return;
        }
        this.q = null;
        this.r = null;
        this.x = false;
        q23 q23Var = this.c;
        if (q23Var.f()) {
            PostTask.c(rh3.a, new a());
            Iterator it = new HashSet(this.v).iterator();
            while (it.hasNext()) {
                d((String) it.next(), true);
            }
            Iterator<i> it2 = this.f.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((i) aVar2.next()).getClass();
                }
            }
        }
        q23Var.a(new a33(this));
        ArrayList arrayList = new ArrayList();
        y13 y13Var = this.d;
        int count = y13Var.g(false).getCount();
        for (int i4 = 0; i4 < count; i4++) {
            arrayList.add(Integer.valueOf(y13Var.g(false).getTabAt(i4).getId()));
        }
        HashMap map = l02.l;
        Object obj = ThreadUtils.a;
        Iterator it3 = l02.m.iterator();
        while (it3.hasNext()) {
            m02 m02VarA2 = m02.a((Class) it3.next(), false);
            m02VarA2.f().e(m02VarA2.e, arrayList);
        }
        l();
        this.k = null;
        oj2.R(y13Var.g(false).getCount(), "Tabs.Startup.TabCount.Regular");
        oj2.R(y13Var.g(true).getCount(), "Tabs.Startup.TabCount.Incognito");
        y13Var.g(false).getCount();
        y13Var.g(true).getCount();
    }

    public final void l() {
        Iterator<i> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            }
            PostTask.d(rh3.a, new zx1(17, (i) aVar.next()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x018c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n(z23.j r19, defpackage.m33 r20, defpackage.ui2 r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z23.n(z23$j, m33, ui2, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:4:0x000a, B:20:0x0055, B:22:0x005b, B:34:0x007f, B:28:0x0065, B:30:0x0069, B:32:0x006d, B:33:0x0075, B:7:0x001d, B:9:0x0021, B:11:0x0025, B:12:0x002c, B:14:0x0030, B:19:0x003b), top: B:49:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:4:0x000a, B:20:0x0055, B:22:0x005b, B:34:0x007f, B:28:0x0065, B:30:0x0069, B:32:0x006d, B:33:0x0075, B:7:0x001d, B:9:0x0021, B:11:0x0025, B:12:0x002c, B:14:0x0030, B:19:0x003b), top: B:49:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(z23.j r9, boolean r10) {
        /*
            r8 = this;
            java.lang.String r0 = "loadTabs exception: "
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads()
            ku2 r2 = defpackage.ku2.F()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r3 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = "org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID"
            r5 = -1
            int r3 = r3.d(r5, r4)     // Catch: java.lang.Throwable -> L35
            boolean r4 = h()     // Catch: java.lang.Throwable -> L35
            r5 = 0
            if (r4 != 0) goto L1d
            goto L39
        L1d:
            int r4 = r9.a     // Catch: java.lang.Throwable -> L35
            if (r3 != r4) goto L2c
            s23 r6 = r8.z     // Catch: java.lang.Throwable -> L35
            if (r6 == 0) goto L2c
            java.lang.Object r4 = r6.g()     // Catch: java.lang.Throwable -> L35
            ui2 r4 = (defpackage.ui2) r4     // Catch: java.lang.Throwable -> L35
            goto L55
        L2c:
            java.lang.Boolean r6 = r9.d     // Catch: java.lang.Throwable -> L35
            if (r6 != 0) goto L37
            java.lang.Boolean r6 = defpackage.mm0.j(r4)     // Catch: java.lang.Throwable -> L35
            goto L37
        L35:
            r8 = move-exception
            goto L86
        L37:
            if (r6 != 0) goto L3b
        L39:
            r4 = r5
            goto L55
        L3b:
            int r4 = r9.a     // Catch: java.lang.Throwable -> L35
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L35
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData$a r7 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.A     // Catch: java.lang.Throwable -> L35
            java.lang.Class<org.chromium.chrome.browser.tab.state.CriticalPersistedTabData> r7 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.class
            m02 r6 = defpackage.m02.a(r7, r6)     // Catch: java.lang.Throwable -> L35
            q02 r7 = r6.f()     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = r6.e     // Catch: java.lang.Throwable -> L35
            p02 r4 = r7.c(r4, r6)     // Catch: java.lang.Throwable -> L35
            ui2 r4 = (defpackage.ui2) r4     // Catch: java.lang.Throwable -> L35
        L55:
            boolean r6 = h()     // Catch: java.lang.Throwable -> L35
            if (r6 == 0) goto L65
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData$a r6 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.A     // Catch: java.lang.Throwable -> L35
            if (r4 != 0) goto L61
            r6 = 1
            goto L62
        L61:
            r6 = 0
        L62:
            if (r6 != 0) goto L65
            goto L7f
        L65:
            int r5 = r9.a     // Catch: java.lang.Throwable -> L35
            if (r3 != r5) goto L75
            t23 r3 = r8.y     // Catch: java.lang.Throwable -> L35
            if (r3 == 0) goto L75
            java.lang.Object r3 = r3.g()     // Catch: java.lang.Throwable -> L35
            r5 = r3
            m33 r5 = (defpackage.m33) r5     // Catch: java.lang.Throwable -> L35
            goto L7f
        L75:
            java.io.File r3 = r8.g()     // Catch: java.lang.Throwable -> L35
            int r5 = r9.a     // Catch: java.lang.Throwable -> L35
            m33 r5 = defpackage.t33.d(r3, r5)     // Catch: java.lang.Throwable -> L35
        L7f:
            r8.n(r9, r5, r4, r10)     // Catch: java.lang.Throwable -> L35
            r2.close()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            goto La2
        L86:
            r2.close()     // Catch: java.lang.Throwable -> L89
        L89:
            throw r8     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
        L8a:
            r8 = move-exception
            goto La6
        L8c:
            r8 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a
            r9.<init>(r0)     // Catch: java.lang.Throwable -> L8a
            java.lang.String r10 = r8.toString()     // Catch: java.lang.Throwable -> L8a
            r9.append(r10)     // Catch: java.lang.Throwable -> L8a
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L8a
            java.lang.String r10 = "cr_tabmodel"
            android.util.Log.i(r10, r9, r8)     // Catch: java.lang.Throwable -> L8a
        La2:
            android.os.StrictMode.setThreadPolicy(r1)
            return
        La6:
            android.os.StrictMode.setThreadPolicy(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z23.o(z23$j, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(int r6, java.lang.String r7) {
        /*
            r5 = this;
            z23$g r0 = r5.k
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L2e
            z23$j r0 = r0.a
            if (r7 != 0) goto Le
            int r3 = r0.a
            if (r3 == r6) goto L18
        Le:
            if (r7 == 0) goto L2e
            java.lang.String r0 = r0.c
            boolean r0 = android.text.TextUtils.equals(r0, r7)
            if (r0 == 0) goto L2e
        L18:
            z23$g r0 = r5.k
            z23$c r3 = r0.b
            if (r3 == 0) goto L21
            r3.a(r1)
        L21:
            ll r0 = r0.c
            r0.a()
            z23$g r0 = r5.k
            z23$j r0 = r0.a
            r5.k()
            goto L2f
        L2e:
            r0 = r2
        L2f:
            java.util.ArrayDeque r3 = r5.i
            if (r0 != 0) goto L65
            if (r7 != 0) goto L4c
            java.util.Iterator r7 = r3.iterator()
        L39:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L4a
            java.lang.Object r0 = r7.next()
            z23$j r0 = (z23.j) r0
            int r4 = r0.a
            if (r4 != r6) goto L39
        L49:
            r2 = r0
        L4a:
            r0 = r2
            goto L65
        L4c:
            java.util.Iterator r6 = r3.iterator()
        L50:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L4a
            java.lang.Object r0 = r6.next()
            z23$j r0 = (z23.j) r0
            java.lang.String r4 = r0.c
            boolean r4 = android.text.TextUtils.equals(r4, r7)
            if (r4 == 0) goto L50
            goto L49
        L65:
            if (r0 == 0) goto L6d
            r3.remove(r0)
            r5.o(r0, r1)
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z23.p(int, java.lang.String):void");
    }

    public final void q(byte[] bArr) {
        FileOutputStream fileOutputStreamF;
        if (Arrays.equals(this.w, bArr)) {
            return;
        }
        File fileG = g();
        String strD = this.c.d();
        synchronized (A) {
            File file = new File(fileG, strD);
            j9 j9Var = new j9(file);
            try {
                fileOutputStreamF = j9Var.f();
            } catch (IOException unused) {
                fileOutputStreamF = null;
            }
            try {
                fileOutputStreamF.write(bArr, 0, bArr.length);
                j9Var.b(fileOutputStreamF);
            } catch (IOException unused2) {
                if (fileOutputStreamF != null) {
                    j9Var.a(fileOutputStreamF);
                }
                Log.e("cr_tabmodel", "Failed to write file: " + file.getAbsolutePath());
            }
        }
        this.w = bArr;
    }

    public final void r() {
        if (this.l != null) {
            return;
        }
        ArrayDeque arrayDeque = this.g;
        if (arrayDeque.isEmpty()) {
            s();
            return;
        }
        f fVar = new f((Tab) arrayDeque.removeFirst());
        this.l = fVar;
        fVar.d(this.s);
    }

    public final void s() {
        e eVar = this.m;
        if (eVar != null) {
            eVar.a(true);
        }
        e eVar2 = new e();
        this.m = eVar2;
        eVar2.d(this.s);
    }

    public final h t() throws IOException {
        ArrayList<j> arrayList = new ArrayList();
        g gVar = this.k;
        if (gVar != null) {
            arrayList.add(gVar.a);
        }
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            arrayList.add((j) it.next());
        }
        Object obj = ThreadUtils.a;
        y13 y13Var = this.d;
        int i2 = 1;
        TabModel tabModelG = y13Var.g(true);
        int iIndex = tabModelG.index();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < tabModelG.getCount(); i3++) {
            arrayList2.add(Integer.valueOf(tabModelG.getTabAt(i3).getId()));
            arrayList3.add(tabModelG.getTabAt(i3).getUrl().j());
        }
        TabModel tabModelG2 = y13Var.g(false);
        int iIndex2 = tabModelG2.index();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (int i4 = 0; i4 < tabModelG2.getCount(); i4++) {
            arrayList4.add(Integer.valueOf(tabModelG2.getTabAt(i4).getId()));
            arrayList5.add(tabModelG2.getTabAt(i4).getUrl().j());
        }
        int iIndex3 = tabModelG2.index();
        int id = -1;
        if (iIndex3 != -1) {
            Tab tabAt = tabModelG2.getTabAt(iIndex3);
            id = tabAt.getId();
            if (!bk3.l(tabAt.getUrl())) {
                i2 = 2;
            }
        } else {
            i2 = 0;
        }
        SharedPreferencesManager.getInstance().m(id, "org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID");
        SharedPreferencesManager.getInstance().m(i2, "Chrome.AppLaunch.LastKnownActiveTabState");
        for (j jVar : arrayList) {
            Boolean bool = jVar.d;
            String str = jVar.c;
            int i5 = jVar.a;
            if (bool == null || bool.booleanValue()) {
                arrayList2.add(Integer.valueOf(i5));
                arrayList3.add(str);
            } else {
                arrayList4.add(Integer.valueOf(i5));
                arrayList5.add(str);
            }
        }
        int size = arrayList4.size();
        int size2 = arrayList2.size();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(5);
        dataOutputStream.writeInt(size2 + size);
        dataOutputStream.writeInt(size2);
        dataOutputStream.writeInt(iIndex);
        dataOutputStream.writeInt(iIndex2 + size2);
        SharedPreferencesManager.getInstance().m(size, "Chrome.StartSurface.RegularTabCount");
        SharedPreferencesManager.getInstance().m(size2, "Chrome.StartSurface.IncognitoTabCount");
        for (int i6 = 0; i6 < size2; i6++) {
            dataOutputStream.writeInt(((Integer) arrayList2.get(i6)).intValue());
            dataOutputStream.writeUTF((String) arrayList3.get(i6));
        }
        for (int i7 = 0; i7 < size; i7++) {
            dataOutputStream.writeInt(((Integer) arrayList4.get(i7)).intValue());
            dataOutputStream.writeUTF((String) arrayList5.get(i7));
        }
        dataOutputStream.close();
        return new h(byteArrayOutputStream.toByteArray());
    }

    /* JADX INFO: compiled from: TabPersistentStore.java */
    public static abstract class i {
        public void a() {
        }
    }
}
