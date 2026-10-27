package c4;

import B5.CallableC0077a;
import N1.C0420a;
import android.os.StrictMode;
import h6.j;
import h6.m;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Closeable {
    public final File l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final File f12383m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final File f12384n;
    public final File o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f12386q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public BufferedWriter f12389t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f12391v;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f12388s = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final LinkedHashMap f12390u = new LinkedHashMap(0, 0.75f, true);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f12392w = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ThreadPoolExecutor f12393x = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC1023a());

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final CallableC0077a f12394y = new CallableC0077a(this, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f12385p = 1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f12387r = 1;

    public c(File file, long j3) {
        this.l = file;
        this.f12383m = new File(file, "journal");
        this.f12384n = new File(file, "journal.tmp");
        this.o = new File(file, "journal.bkp");
        this.f12386q = j3;
    }

    public static void F(File file, File file2, boolean z6) throws IOException {
        if (z6) {
            i(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public static void b(c cVar, A2.c cVar2, boolean z6) {
        synchronized (cVar) {
            b bVar = (b) cVar2.c;
            if (bVar.f12381f != cVar2) {
                throw new IllegalStateException();
            }
            if (z6 && !bVar.f12380e) {
                for (int i7 = 0; i7 < cVar.f12387r; i7++) {
                    if (!((boolean[]) cVar2.d)[i7]) {
                        cVar2.a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i7);
                    }
                    if (!bVar.d[i7].exists()) {
                        cVar2.a();
                        return;
                    }
                }
            }
            for (int i10 = 0; i10 < cVar.f12387r; i10++) {
                File file = bVar.d[i10];
                if (!z6) {
                    i(file);
                } else if (file.exists()) {
                    File file2 = bVar.c[i10];
                    file.renameTo(file2);
                    long j3 = bVar.f12379b[i10];
                    long length = file2.length();
                    bVar.f12379b[i10] = length;
                    cVar.f12388s = (cVar.f12388s - j3) + length;
                }
            }
            cVar.f12391v++;
            bVar.f12381f = null;
            if (bVar.f12380e || z6) {
                bVar.f12380e = true;
                cVar.f12389t.append((CharSequence) "CLEAN");
                cVar.f12389t.append(' ');
                cVar.f12389t.append((CharSequence) bVar.f12378a);
                cVar.f12389t.append((CharSequence) bVar.a());
                cVar.f12389t.append('\n');
                if (z6) {
                    cVar.f12392w++;
                }
            } else {
                cVar.f12390u.remove(bVar.f12378a);
                cVar.f12389t.append((CharSequence) "REMOVE");
                cVar.f12389t.append(' ');
                cVar.f12389t.append((CharSequence) bVar.f12378a);
                cVar.f12389t.append('\n');
            }
            k(cVar.f12389t);
            if (cVar.f12388s > cVar.f12386q || cVar.o()) {
                cVar.f12393x.submit(cVar.f12394y);
            }
        }
    }

    public static void g(BufferedWriter bufferedWriter) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            bufferedWriter.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static void i(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public static void k(BufferedWriter bufferedWriter) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            bufferedWriter.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static c q(File file, long j3) throws IOException {
        if (j3 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                F(file2, file3, false);
            }
        }
        c cVar = new c(file, j3);
        if (cVar.f12383m.exists()) {
            try {
                cVar.y();
                cVar.v();
                return cVar;
            } catch (IOException e10) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e10.getMessage() + ", removing");
                cVar.close();
                f.a(cVar.l);
            }
        }
        file.mkdirs();
        c cVar2 = new c(file, j3);
        cVar2.C();
        return cVar2;
    }

    public final synchronized void C() {
        try {
            BufferedWriter bufferedWriter = this.f12389t;
            if (bufferedWriter != null) {
                g(bufferedWriter);
            }
            File file = this.f12384n;
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(m.m(new FileOutputStream(file), file), f.f12398a));
            try {
                bufferedWriter2.write("libcore.io.DiskLruCache");
                bufferedWriter2.write("\n");
                bufferedWriter2.write("1");
                bufferedWriter2.write("\n");
                bufferedWriter2.write(Integer.toString(this.f12385p));
                bufferedWriter2.write("\n");
                bufferedWriter2.write(Integer.toString(this.f12387r));
                bufferedWriter2.write("\n");
                bufferedWriter2.write("\n");
                for (b bVar : this.f12390u.values()) {
                    if (bVar.f12381f != null) {
                        bufferedWriter2.write("DIRTY " + bVar.f12378a + '\n');
                    } else {
                        bufferedWriter2.write("CLEAN " + bVar.f12378a + bVar.a() + '\n');
                    }
                }
                g(bufferedWriter2);
                if (this.f12383m.exists()) {
                    F(this.f12383m, this.o, true);
                }
                F(this.f12384n, this.f12383m, false);
                this.o.delete();
                File file2 = this.f12383m;
                this.f12389t = new BufferedWriter(new OutputStreamWriter(m.n(new FileOutputStream(file2, true), file2, true), f.f12398a));
            } catch (Throwable th) {
                g(bufferedWriter2);
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final void G() {
        while (this.f12388s > this.f12386q) {
            String str = (String) ((Map.Entry) this.f12390u.entrySet().iterator().next()).getKey();
            synchronized (this) {
                try {
                    if (this.f12389t == null) {
                        throw new IllegalStateException("cache is closed");
                    }
                    b bVar = (b) this.f12390u.get(str);
                    if (bVar != null && bVar.f12381f == null) {
                        for (int i7 = 0; i7 < this.f12387r; i7++) {
                            File file = bVar.c[i7];
                            if (file.exists() && !file.delete()) {
                                throw new IOException("failed to delete " + file);
                            }
                            long j3 = this.f12388s;
                            long[] jArr = bVar.f12379b;
                            this.f12388s = j3 - jArr[i7];
                            jArr[i7] = 0;
                        }
                        this.f12391v++;
                        this.f12389t.append((CharSequence) "REMOVE");
                        this.f12389t.append(' ');
                        this.f12389t.append((CharSequence) str);
                        this.f12389t.append('\n');
                        this.f12390u.remove(str);
                        if (o()) {
                            this.f12393x.submit(this.f12394y);
                        }
                    }
                } finally {
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.f12389t == null) {
                return;
            }
            Iterator it = new ArrayList(this.f12390u.values()).iterator();
            while (it.hasNext()) {
                A2.c cVar = ((b) it.next()).f12381f;
                if (cVar != null) {
                    cVar.a();
                }
            }
            G();
            g(this.f12389t);
            this.f12389t = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final A2.c j(String str) {
        synchronized (this) {
            try {
                if (this.f12389t == null) {
                    throw new IllegalStateException("cache is closed");
                }
                b bVar = (b) this.f12390u.get(str);
                if (bVar == null) {
                    bVar = new b(this, str);
                    this.f12390u.put(str, bVar);
                } else if (bVar.f12381f != null) {
                    return null;
                }
                A2.c cVar = new A2.c(this, bVar);
                bVar.f12381f = cVar;
                this.f12389t.append((CharSequence) "DIRTY");
                this.f12389t.append(' ');
                this.f12389t.append((CharSequence) str);
                this.f12389t.append('\n');
                k(this.f12389t);
                return cVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized C0420a m(String str) {
        if (this.f12389t == null) {
            throw new IllegalStateException("cache is closed");
        }
        b bVar = (b) this.f12390u.get(str);
        if (bVar == null) {
            return null;
        }
        if (!bVar.f12380e) {
            return null;
        }
        for (File file : bVar.c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.f12391v++;
        this.f12389t.append((CharSequence) "READ");
        this.f12389t.append(' ');
        this.f12389t.append((CharSequence) str);
        this.f12389t.append('\n');
        if (o()) {
            this.f12393x.submit(this.f12394y);
        }
        return new C0420a(bVar.c);
    }

    public final boolean o() {
        int i7 = this.f12391v;
        return i7 >= 2000 && i7 >= this.f12390u.size();
    }

    public final void v() throws IOException {
        i(this.f12384n);
        Iterator it = this.f12390u.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            A2.c cVar = bVar.f12381f;
            int i7 = this.f12387r;
            int i10 = 0;
            if (cVar == null) {
                while (i10 < i7) {
                    this.f12388s += bVar.f12379b[i10];
                    i10++;
                }
            } else {
                bVar.f12381f = null;
                while (i10 < i7) {
                    i(bVar.c[i10]);
                    i(bVar.d[i10]);
                    i10++;
                }
                it.remove();
            }
        }
    }

    public final void y() {
        File file = this.f12383m;
        e eVar = new e(j.h(new FileInputStream(file), file), f.f12398a);
        try {
            String strB = eVar.b();
            String strB2 = eVar.b();
            String strB3 = eVar.b();
            String strB4 = eVar.b();
            String strB5 = eVar.b();
            if (!"libcore.io.DiskLruCache".equals(strB) || !"1".equals(strB2) || !Integer.toString(this.f12385p).equals(strB3) || !Integer.toString(this.f12387r).equals(strB4) || !"".equals(strB5)) {
                throw new IOException("unexpected journal header: [" + strB + ", " + strB2 + ", " + strB4 + ", " + strB5 + "]");
            }
            int i7 = 0;
            while (true) {
                try {
                    z(eVar.b());
                    i7++;
                } catch (EOFException unused) {
                    this.f12391v = i7 - this.f12390u.size();
                    if (eVar.f12397p == -1) {
                        C();
                    } else {
                        this.f12389t = new BufferedWriter(new OutputStreamWriter(m.n(new FileOutputStream(file, true), file, true), f.f12398a));
                    }
                    try {
                        eVar.close();
                        return;
                    } catch (RuntimeException e10) {
                        throw e10;
                    } catch (Exception unused2) {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            try {
                eVar.close();
            } catch (RuntimeException e11) {
                throw e11;
            } catch (Exception unused3) {
            }
            throw th;
        }
    }

    public final void z(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: ".concat(str));
        }
        int i7 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i7);
        LinkedHashMap linkedHashMap = this.f12390u;
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i7);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iIndexOf2);
        }
        b bVar = (b) linkedHashMap.get(strSubstring);
        if (bVar == null) {
            bVar = new b(this, strSubstring);
            linkedHashMap.put(strSubstring, bVar);
        }
        if (iIndexOf2 == -1 || iIndexOf != 5 || !str.startsWith("CLEAN")) {
            if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
                bVar.f12381f = new A2.c(this, bVar);
                return;
            } else {
                if (iIndexOf2 != -1 || iIndexOf != 4 || !str.startsWith("READ")) {
                    throw new IOException("unexpected journal line: ".concat(str));
                }
                return;
            }
        }
        String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
        bVar.f12380e = true;
        bVar.f12381f = null;
        if (strArrSplit.length != bVar.f12382g.f12387r) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArrSplit));
        }
        for (int i10 = 0; i10 < strArrSplit.length; i10++) {
            try {
                bVar.f12379b[i10] = Long.parseLong(strArrSplit[i10]);
            } catch (NumberFormatException unused) {
                throw new IOException("unexpected journal line: " + Arrays.toString(strArrSplit));
            }
        }
    }
}
