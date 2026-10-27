package spi;

import android.annotation.TargetApi;
import android.os.StrictMode;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k6 implements Closeable {
    public final File a;
    public final File b;
    public final File c;
    public final File d;
    public final int e;
    public long f;
    public final int g;
    public BufferedWriter i;
    public int k;
    public long h = 0;
    public final LinkedHashMap<String, d> j = new LinkedHashMap<>(0, 0.75f, true);
    public long l = 0;
    public final ThreadPoolExecutor m = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));
    public final Callable<Void> n = new a();

    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        public final Void call() throws Exception {
            synchronized (k6.this) {
                k6 k6Var = k6.this;
                if (k6Var.i != null) {
                    k6Var.O();
                    if (k6.this.H()) {
                        k6.this.M();
                        k6.this.k = 0;
                    }
                }
            }
            return null;
        }
    }

    public static final class b implements ThreadFactory {
        private b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        @Override // java.util.concurrent.ThreadFactory
        public final synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    public final class c {
        public final d a;
        public final boolean[] b;
        public boolean c;

        private c(d dVar) {
            this.a = dVar;
            this.b = dVar.e ? null : new boolean[k6.this.g];
        }

        public /* synthetic */ c(k6 k6Var, d dVar, a aVar) {
            this(dVar);
        }

        public final void a() throws IOException {
            k6.e(k6.this, this, false);
        }

        public final File b() throws IOException {
            File file;
            synchronized (k6.this) {
                d dVar = this.a;
                if (dVar.f != this) {
                    throw new IllegalStateException();
                }
                if (!dVar.e) {
                    this.b[0] = true;
                }
                file = dVar.d[0];
                k6.this.a.mkdirs();
            }
            return file;
        }
    }

    public final class d {
        public final String a;
        public final long[] b;
        public File[] c;
        public File[] d;
        public boolean e;
        public c f;
        public long g;

        private d(String str) {
            this.a = str;
            int i = k6.this.g;
            this.b = new long[i];
            this.c = new File[i];
            this.d = new File[i];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i2 = 0; i2 < k6.this.g; i2++) {
                sb.append(i2);
                this.c[i2] = new File(k6.this.a, sb.toString());
                sb.append(".tmp");
                this.d[i2] = new File(k6.this.a, sb.toString());
                sb.setLength(length);
            }
        }

        public /* synthetic */ d(k6 k6Var, String str, a aVar) {
            this(str);
        }

        public final String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.b) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public final IOException b(String[] strArr) throws IOException {
            StringBuilder sbK = spi.e.k("unexpected journal line: ");
            sbK.append(Arrays.toString(strArr));
            throw new IOException(sbK.toString());
        }
    }

    public final class e {
        public final File[] a;

        private e(k6 k6Var, String str, long j, File[] fileArr, long[] jArr) {
            this.a = fileArr;
        }

        public /* synthetic */ e(k6 k6Var, String str, long j, File[] fileArr, long[] jArr, a aVar) {
            this(k6Var, str, j, fileArr, jArr);
        }
    }

    private k6(File file, int i, int i2, long j) {
        this.a = file;
        this.e = i;
        this.b = new File(file, "journal");
        this.c = new File(file, "journal.tmp");
        this.d = new File(file, "journal.bkp");
        this.g = i2;
        this.f = j;
    }

    @TargetApi(26)
    public static void C(Writer writer) throws IOException {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static void D(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    @TargetApi(26)
    public static void F(Writer writer) throws IOException {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static k6 I(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                N(file2, file3, false);
            }
        }
        k6 k6Var = new k6(file, 1, 1, j);
        if (k6Var.b.exists()) {
            try {
                k6Var.K();
                k6Var.J();
                return k6Var;
            } catch (IOException e2) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                k6Var.close();
                vu.a(k6Var.a);
            }
        }
        file.mkdirs();
        k6 k6Var2 = new k6(file, 1, 1, j);
        k6Var2.M();
        return k6Var2;
    }

    public static void N(File file, File file2, boolean z) throws IOException {
        if (z) {
            D(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049 A[Catch: all -> 0x00f7, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:10:0x000f, B:12:0x0013, B:14:0x0019, B:16:0x0023, B:17:0x0028, B:18:0x002b, B:19:0x0044, B:20:0x0045, B:22:0x0049, B:24:0x004f, B:26:0x0055, B:28:0x0072, B:27:0x006f, B:29:0x0075, B:31:0x0087, B:33:0x00ac, B:35:0x00d5, B:37:0x00e2, B:39:0x00e8, B:34:0x00b6, B:42:0x00f1, B:43:0x00f6), top: B:47:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087 A[Catch: all -> 0x00f7, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:10:0x000f, B:12:0x0013, B:14:0x0019, B:16:0x0023, B:17:0x0028, B:18:0x002b, B:19:0x0044, B:20:0x0045, B:22:0x0049, B:24:0x004f, B:26:0x0055, B:28:0x0072, B:27:0x006f, B:29:0x0075, B:31:0x0087, B:33:0x00ac, B:35:0x00d5, B:37:0x00e2, B:39:0x00e8, B:34:0x00b6, B:42:0x00f1, B:43:0x00f6), top: B:47:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b6 A[Catch: all -> 0x00f7, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:10:0x000f, B:12:0x0013, B:14:0x0019, B:16:0x0023, B:17:0x0028, B:18:0x002b, B:19:0x0044, B:20:0x0045, B:22:0x0049, B:24:0x004f, B:26:0x0055, B:28:0x0072, B:27:0x006f, B:29:0x0075, B:31:0x0087, B:33:0x00ac, B:35:0x00d5, B:37:0x00e2, B:39:0x00e8, B:34:0x00b6, B:42:0x00f1, B:43:0x00f6), top: B:47:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e8 A[Catch: all -> 0x00f7, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:10:0x000f, B:12:0x0013, B:14:0x0019, B:16:0x0023, B:17:0x0028, B:18:0x002b, B:19:0x0044, B:20:0x0045, B:22:0x0049, B:24:0x004f, B:26:0x0055, B:28:0x0072, B:27:0x006f, B:29:0x0075, B:31:0x0087, B:33:0x00ac, B:35:0x00d5, B:37:0x00e2, B:39:0x00e8, B:34:0x00b6, B:42:0x00f1, B:43:0x00f6), top: B:47:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(spi.k6 r9, spi.k6.c r10, boolean r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.k6.e(spi.k6, spi.k6$c, boolean):void");
    }

    public final void B() {
        if (this.i == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final c E(String str) throws IOException {
        synchronized (this) {
            B();
            d dVar = this.j.get(str);
            a aVar = null;
            if (dVar == null) {
                dVar = new d(this, str, aVar);
                this.j.put(str, dVar);
            } else if (dVar.f != null) {
                return null;
            }
            c cVar = new c(this, dVar, aVar);
            dVar.f = cVar;
            this.i.append((CharSequence) "DIRTY");
            this.i.append(' ');
            this.i.append((CharSequence) str);
            this.i.append('\n');
            F(this.i);
            return cVar;
        }
    }

    public final synchronized e G(String str) throws IOException {
        B();
        d dVar = this.j.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.e) {
            return null;
        }
        for (File file : dVar.c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.k++;
        this.i.append((CharSequence) "READ");
        this.i.append(' ');
        this.i.append((CharSequence) str);
        this.i.append('\n');
        if (H()) {
            this.m.submit(this.n);
        }
        return new e(this, str, dVar.g, dVar.c, dVar.b, null);
    }

    public final boolean H() {
        int i = this.k;
        return i >= 2000 && i >= this.j.size();
    }

    public final void J() throws IOException {
        D(this.c);
        Iterator<d> it = this.j.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i = 0;
            if (next.f == null) {
                while (i < this.g) {
                    this.h += next.b[i];
                    i++;
                }
            } else {
                next.f = null;
                while (i < this.g) {
                    D(next.c[i]);
                    D(next.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    public final void K() throws IOException {
        ls lsVar = new ls(new FileInputStream(this.b), vu.a);
        try {
            String strB = lsVar.B();
            String strB2 = lsVar.B();
            String strB3 = lsVar.B();
            String strB4 = lsVar.B();
            String strB5 = lsVar.B();
            if (!"libcore.io.DiskLruCache".equals(strB) || !"1".equals(strB2) || !Integer.toString(this.e).equals(strB3) || !Integer.toString(this.g).equals(strB4) || !"".equals(strB5)) {
                throw new IOException("unexpected journal header: [" + strB + ", " + strB2 + ", " + strB4 + ", " + strB5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    L(lsVar.B());
                    i++;
                } catch (EOFException unused) {
                    this.k = i - this.j.size();
                    if (lsVar.e == -1) {
                        M();
                    } else {
                        this.i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.b, true), vu.a));
                    }
                    try {
                        lsVar.close();
                        return;
                    } catch (RuntimeException e2) {
                        throw e2;
                    } catch (Exception unused2) {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            try {
                lsVar.close();
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception unused3) {
            }
            throw th;
        }
    }

    public final void L(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException(spi.d.f("unexpected journal line: ", str));
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.j.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        d dVar = this.j.get(strSubstring);
        a aVar = null;
        if (dVar == null) {
            dVar = new d(this, strSubstring, aVar);
            this.j.put(strSubstring, dVar);
        }
        if (iIndexOf2 == -1 || iIndexOf != 5 || !str.startsWith("CLEAN")) {
            if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
                dVar.f = new c(this, dVar, aVar);
                return;
            } else {
                if (iIndexOf2 != -1 || iIndexOf != 4 || !str.startsWith("READ")) {
                    throw new IOException(spi.d.f("unexpected journal line: ", str));
                }
                return;
            }
        }
        String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
        dVar.e = true;
        dVar.f = null;
        if (strArrSplit.length != k6.this.g) {
            dVar.b(strArrSplit);
            throw null;
        }
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            try {
                dVar.b[i2] = Long.parseLong(strArrSplit[i2]);
            } catch (NumberFormatException unused) {
                dVar.b(strArrSplit);
                throw null;
            }
        }
    }

    public final synchronized void M() throws IOException {
        StringBuilder sb;
        BufferedWriter bufferedWriter = this.i;
        if (bufferedWriter != null) {
            C(bufferedWriter);
        }
        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c), vu.a));
        try {
            bufferedWriter2.write("libcore.io.DiskLruCache");
            bufferedWriter2.write("\n");
            bufferedWriter2.write("1");
            bufferedWriter2.write("\n");
            bufferedWriter2.write(Integer.toString(this.e));
            bufferedWriter2.write("\n");
            bufferedWriter2.write(Integer.toString(this.g));
            bufferedWriter2.write("\n");
            bufferedWriter2.write("\n");
            for (d dVar : this.j.values()) {
                if (dVar.f != null) {
                    sb = new StringBuilder();
                    sb.append("DIRTY ");
                    sb.append(dVar.a);
                    sb.append('\n');
                } else {
                    sb = new StringBuilder();
                    sb.append("CLEAN ");
                    sb.append(dVar.a);
                    sb.append(dVar.a());
                    sb.append('\n');
                }
                bufferedWriter2.write(sb.toString());
            }
            C(bufferedWriter2);
            if (this.b.exists()) {
                N(this.b, this.d, true);
            }
            N(this.c, this.b, false);
            this.d.delete();
            this.i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.b, true), vu.a));
        } catch (Throwable th) {
            C(bufferedWriter2);
            throw th;
        }
    }

    public final void O() throws IOException {
        while (this.h > this.f) {
            String key = this.j.entrySet().iterator().next().getKey();
            synchronized (this) {
                B();
                d dVar = this.j.get(key);
                if (dVar != null && dVar.f == null) {
                    for (int i = 0; i < this.g; i++) {
                        File file = dVar.c[i];
                        if (file.exists() && !file.delete()) {
                            throw new IOException("failed to delete " + file);
                        }
                        long j = this.h;
                        long[] jArr = dVar.b;
                        this.h = j - jArr[i];
                        jArr[i] = 0;
                    }
                    this.k++;
                    this.i.append((CharSequence) "REMOVE");
                    this.i.append(' ');
                    this.i.append((CharSequence) key);
                    this.i.append('\n');
                    this.j.remove(key);
                    if (H()) {
                        this.m.submit(this.n);
                    }
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.i == null) {
            return;
        }
        Iterator it = new ArrayList(this.j.values()).iterator();
        while (it.hasNext()) {
            c cVar = ((d) it.next()).f;
            if (cVar != null) {
                cVar.a();
            }
        }
        O();
        C(this.i);
        this.i = null;
    }
}
