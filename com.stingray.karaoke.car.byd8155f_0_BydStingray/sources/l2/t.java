package l2;

import android.database.SQLException;
import android.os.ConditionVariable;
import d5.M;
import d5.s0;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import j2.C1538a;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class t implements InterfaceC1735b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final HashSet f18495j = new HashSet();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f18496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f18497b;
    public final H0.b c;
    public final g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f18498e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Random f18499f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f18500g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f18501h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1734a f18502i;

    public t(File file, q qVar, C1538a c1538a) {
        boolean zAdd;
        H0.b bVar = new H0.b(c1538a, file);
        g gVar = new g(c1538a);
        synchronized (t.class) {
            zAdd = f18495j.add(file.getAbsoluteFile());
        }
        if (!zAdd) {
            throw new IllegalStateException("Another SimpleCache instance uses the folder: " + file);
        }
        this.f18496a = file;
        this.f18497b = qVar;
        this.c = bVar;
        this.d = gVar;
        this.f18498e = new HashMap();
        this.f18499f = new Random();
        this.f18500g = true;
        this.f18501h = -1L;
        ConditionVariable conditionVariable = new ConditionVariable();
        new s(this, conditionVariable).start();
        conditionVariable.block();
    }

    public static void a(t tVar) {
        long j3;
        H0.b bVar = tVar.c;
        File file = tVar.f18496a;
        if (!file.exists()) {
            try {
                e(file);
            } catch (C1734a e10) {
                tVar.f18502i = e10;
                return;
            }
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            String str = "Failed to list cache directory files: " + file;
            AbstractC1346c.p("SimpleCache", str);
            tVar.f18502i = new C1734a(str);
            return;
        }
        int length = fileArrListFiles.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                j3 = -1;
                break;
            }
            File file2 = fileArrListFiles[i7];
            String name = file2.getName();
            if (name.endsWith(".uid")) {
                try {
                    j3 = Long.parseLong(name.substring(0, name.indexOf(46)), 16);
                    break;
                } catch (NumberFormatException unused) {
                    AbstractC1346c.p("SimpleCache", "Malformed UID file: " + file2);
                    file2.delete();
                }
            }
            i7++;
        }
        tVar.f18501h = j3;
        if (j3 == -1) {
            try {
                tVar.f18501h = f(file);
            } catch (IOException e11) {
                String str2 = "Failed to create cache UID: " + file;
                AbstractC1346c.q("SimpleCache", str2, e11);
                tVar.f18502i = new C1734a(str2, e11);
                return;
            }
        }
        try {
            bVar.D(tVar.f18501h);
            g gVar = tVar.d;
            if (gVar != null) {
                gVar.c(tVar.f18501h);
                HashMap mapB = gVar.b();
                tVar.i(file, true, fileArrListFiles, mapB);
                gVar.d(mapB.keySet());
            } else {
                tVar.i(file, true, fileArrListFiles, null);
            }
            s0 it = M.v(((HashMap) bVar.f2765m).keySet()).iterator();
            while (it.hasNext()) {
                bVar.K((String) it.next());
            }
            try {
                bVar.N();
            } catch (IOException e12) {
                AbstractC1346c.q("SimpleCache", "Storing index file failed", e12);
            }
        } catch (IOException e13) {
            String str3 = "Failed to initialize cache indices: " + file;
            AbstractC1346c.q("SimpleCache", str3, e13);
            tVar.f18502i = new C1734a(str3, e13);
        }
    }

    public static void e(File file) throws C1734a {
        if (file.mkdirs() || file.isDirectory()) {
            return;
        }
        String str = "Failed to create cache directory: " + file;
        AbstractC1346c.p("SimpleCache", str);
        throw new C1734a(str);
    }

    public static long f(File file) throws IOException {
        long jNextLong = new SecureRandom().nextLong();
        long jAbs = jNextLong == Long.MIN_VALUE ? 0L : Math.abs(jNextLong);
        File file2 = new File(file, android.hardware.bydauto.ac.tsimpl.a.h(Long.toString(jAbs, 16), ".uid"));
        if (file2.createNewFile()) {
            return jAbs;
        }
        throw new IOException("Failed to create UID file: " + file2);
    }

    public final void b(u uVar) {
        H0.b bVar = this.c;
        String str = uVar.l;
        bVar.z(str).c.add(uVar);
        ArrayList arrayList = (ArrayList) this.f18498e.get(str);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((q) arrayList.get(size)).b(this, uVar);
            }
        }
        this.f18497b.b(this, uVar);
    }

    public final synchronized void c(String str, g gVar) {
        d();
        H0.b bVar = this.c;
        k kVarZ = bVar.z(str);
        o oVar = kVarZ.f18486e;
        o oVarB = oVar.b(gVar);
        kVarZ.f18486e = oVarB;
        if (!oVarB.equals(oVar)) {
            ((m) bVar.f2768q).e(kVarZ);
        }
        try {
            this.c.N();
        } catch (IOException e10) {
            throw new C1734a(e10);
        }
    }

    public final synchronized void d() {
        C1734a c1734a = this.f18502i;
        if (c1734a != null) {
            throw c1734a;
        }
    }

    public final synchronized o g(String str) {
        k kVarX;
        kVarX = this.c.x(str);
        return kVarX != null ? kVarX.f18486e : o.c;
    }

    public final u h(long j3, long j10, String str) {
        u uVar;
        long j11;
        k kVarX = this.c.x(str);
        if (kVarX == null) {
            return new u(str, j3, j10, -9223372036854775807L, null);
        }
        while (true) {
            u uVar2 = new u(kVarX.f18485b, j3, -1L, -9223372036854775807L, null);
            TreeSet treeSet = kVarX.c;
            uVar = (u) treeSet.floor(uVar2);
            if (uVar == null || uVar.f18478m + uVar.f18479n <= j3) {
                u uVar3 = (u) treeSet.ceiling(uVar2);
                if (uVar3 != null) {
                    long jMin = uVar3.f18478m - j3;
                    if (j10 != -1) {
                        jMin = Math.min(jMin, j10);
                    }
                    j11 = jMin;
                } else {
                    j11 = j10;
                }
                uVar = new u(kVarX.f18485b, j3, j11, -9223372036854775807L, null);
            }
            if (!uVar.o) {
                break;
            }
            File file = uVar.f18480p;
            file.getClass();
            if (file.length() == uVar.f18479n) {
                break;
            }
            l();
        }
        return uVar;
    }

    public final void i(File file, boolean z6, File[] fileArr, HashMap map) {
        long j3;
        long j10;
        if (fileArr == null || fileArr.length == 0) {
            if (z6) {
                return;
            }
            file.delete();
            return;
        }
        for (File file2 : fileArr) {
            String name = file2.getName();
            if (z6 && name.indexOf(46) == -1) {
                i(file2, false, file2.listFiles(), map);
            } else if (!z6 || (!name.startsWith("cached_content_index.exi") && !name.endsWith(".uid"))) {
                f fVar = map != null ? (f) map.remove(name) : null;
                if (fVar != null) {
                    j10 = fVar.f18473a;
                    j3 = fVar.f18474b;
                } else {
                    j3 = -9223372036854775807L;
                    j10 = -1;
                }
                u uVarB = u.b(file2, j10, j3, this.c);
                if (uVarB != null) {
                    b(uVarB);
                } else {
                    file2.delete();
                }
            }
        }
    }

    public final synchronized void j(u uVar) {
        k kVarX = this.c.x(uVar.l);
        kVarX.getClass();
        long j3 = uVar.f18478m;
        int i7 = 0;
        while (true) {
            ArrayList arrayList = kVarX.d;
            if (i7 >= arrayList.size()) {
                throw new IllegalStateException();
            }
            if (((j) arrayList.get(i7)).f18482a == j3) {
                arrayList.remove(i7);
                this.c.K(kVarX.f18485b);
                notifyAll();
            } else {
                i7++;
            }
        }
    }

    public final void k(i iVar) {
        String str = iVar.l;
        H0.b bVar = this.c;
        k kVarX = bVar.x(str);
        if (kVarX == null || !kVarX.c.remove(iVar)) {
            return;
        }
        File file = iVar.f18480p;
        if (file != null) {
            file.delete();
        }
        g gVar = this.d;
        if (gVar != null) {
            file.getClass();
            String name = file.getName();
            try {
                ((String) gVar.f18476b).getClass();
                try {
                    ((C1538a) gVar.f18475a).getWritableDatabase().delete((String) gVar.f18476b, "name = ?", new String[]{name});
                } catch (SQLException e10) {
                    throw new E5.s(e10);
                }
            } catch (IOException unused) {
                AbstractC1347d.q("Failed to remove file index entry for: ", name, "SimpleCache");
            }
        }
        bVar.K(kVarX.f18485b);
        ArrayList arrayList = (ArrayList) this.f18498e.get(iVar.l);
        long j3 = iVar.f18479n;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                q qVar = (q) arrayList.get(size);
                qVar.f18492a.remove(iVar);
                qVar.f18493b -= j3;
            }
        }
        q qVar2 = this.f18497b;
        qVar2.f18492a.remove(iVar);
        qVar2.f18493b -= j3;
    }

    public final void l() {
        ArrayList arrayList = new ArrayList();
        Iterator it = Collections.unmodifiableCollection(((HashMap) this.c.f2765m).values()).iterator();
        while (it.hasNext()) {
            for (i iVar : ((k) it.next()).c) {
                File file = iVar.f18480p;
                file.getClass();
                if (file.length() != iVar.f18479n) {
                    arrayList.add(iVar);
                }
            }
        }
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            k((i) arrayList.get(i7));
        }
    }

    public final synchronized u m(long j3, long j10, String str) {
        d();
        u uVarH = h(j3, j10, str);
        if (uVarH.o) {
            return n(str, uVarH);
        }
        k kVarZ = this.c.z(str);
        long j11 = uVarH.f18479n;
        int i7 = 0;
        while (true) {
            ArrayList arrayList = kVarZ.d;
            if (i7 >= arrayList.size()) {
                arrayList.add(new j(j3, j11));
                return uVarH;
            }
            j jVar = (j) arrayList.get(i7);
            long j12 = jVar.f18482a;
            if (j12 <= j3) {
                long j13 = jVar.f18483b;
                if (j13 == -1 || j12 + j13 > j3) {
                    break;
                }
                i7++;
            } else {
                if (j11 == -1 || j3 + j11 > j12) {
                    break;
                }
                i7++;
            }
        }
        return null;
    }

    public final u n(String str, u uVar) {
        boolean z6;
        File file;
        if (!this.f18500g) {
            return uVar;
        }
        File file2 = uVar.f18480p;
        file2.getClass();
        String name = file2.getName();
        long j3 = uVar.f18479n;
        long jCurrentTimeMillis = System.currentTimeMillis();
        g gVar = this.d;
        if (gVar != null) {
            try {
                gVar.e(j3, jCurrentTimeMillis, name);
            } catch (IOException unused) {
                AbstractC1346c.z("SimpleCache", "Failed to update index with new touch timestamp.");
            }
            z6 = false;
        } else {
            z6 = true;
        }
        k kVarX = this.c.x(str);
        kVarX.getClass();
        TreeSet treeSet = kVarX.c;
        AbstractC1346c.j(treeSet.remove(uVar));
        file2.getClass();
        if (z6) {
            File parentFile = file2.getParentFile();
            parentFile.getClass();
            File fileC = u.c(parentFile, kVarX.f18484a, uVar.f18478m, jCurrentTimeMillis);
            if (file2.renameTo(fileC)) {
                file = fileC;
            } else {
                AbstractC1346c.z("CachedContent", "Failed to rename " + file2 + " to " + fileC);
                file = file2;
            }
        } else {
            file = file2;
        }
        AbstractC1346c.j(uVar.o);
        u uVar2 = new u(uVar.l, uVar.f18478m, uVar.f18479n, jCurrentTimeMillis, file);
        treeSet.add(uVar2);
        ArrayList arrayList = (ArrayList) this.f18498e.get(uVar.l);
        long j10 = uVar.f18479n;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                q qVar = (q) arrayList.get(size);
                qVar.f18492a.remove(uVar);
                qVar.f18493b -= j10;
                qVar.b(this, uVar2);
            }
        }
        q qVar2 = this.f18497b;
        qVar2.f18492a.remove(uVar);
        qVar2.f18493b -= j10;
        qVar2.b(this, uVar2);
        return uVar2;
    }
}
