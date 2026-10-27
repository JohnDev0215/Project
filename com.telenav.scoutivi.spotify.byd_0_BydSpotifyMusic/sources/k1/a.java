package k1;

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

/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f7194e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final File f7195g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final File f7196h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final File f7197i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f7198j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f7199k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f7200l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Writer f7202n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f7204p;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private long f7201m = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final LinkedHashMap<String, d> f7203o = new LinkedHashMap<>(0, 0.75f, true);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private long f7205q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    final ThreadPoolExecutor f7206r = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Callable<Void> f7207s = new CallableC0097a();

    /* JADX INFO: renamed from: k1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DiskLruCache.java */
    class CallableC0097a implements Callable<Void> {
        CallableC0097a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() {
            synchronized (a.this) {
                if (a.this.f7202n == null) {
                    return null;
                }
                a.this.o0();
                if (a.this.g0()) {
                    a.this.l0();
                    a.this.f7204p = 0;
                }
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private static final class b implements ThreadFactory {
        private b() {
        }

        /* synthetic */ b(CallableC0097a callableC0097a) {
            this();
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f7209a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean[] f7210b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f7211c;

        /* synthetic */ c(a aVar, d dVar, CallableC0097a callableC0097a) {
            this(dVar);
        }

        public void a() {
            a.this.Z(this, false);
        }

        public void b() {
            if (this.f7211c) {
                return;
            }
            try {
                a();
            } catch (IOException unused) {
            }
        }

        public void e() {
            a.this.Z(this, true);
            this.f7211c = true;
        }

        public File f(int i7) {
            File fileK;
            synchronized (a.this) {
                if (this.f7209a.f7218f != this) {
                    throw new IllegalStateException();
                }
                if (!this.f7209a.f7217e) {
                    this.f7210b[i7] = true;
                }
                fileK = this.f7209a.k(i7);
                if (!a.this.f7194e.exists()) {
                    a.this.f7194e.mkdirs();
                }
            }
            return fileK;
        }

        private c(d dVar) {
            this.f7209a = dVar;
            this.f7210b = dVar.f7217e ? null : new boolean[a.this.f7200l];
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f7213a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long[] f7214b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        File[] f7215c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        File[] f7216d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f7217e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private c f7218f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private long f7219g;

        /* synthetic */ d(a aVar, String str, CallableC0097a callableC0097a) {
            this(str);
        }

        private IOException m(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(String[] strArr) throws IOException {
            if (strArr.length != a.this.f7200l) {
                throw m(strArr);
            }
            for (int i7 = 0; i7 < strArr.length; i7++) {
                try {
                    this.f7214b[i7] = Long.parseLong(strArr[i7]);
                } catch (NumberFormatException unused) {
                    throw m(strArr);
                }
            }
        }

        public File j(int i7) {
            return this.f7215c[i7];
        }

        public File k(int i7) {
            return this.f7216d[i7];
        }

        public String l() {
            StringBuilder sb = new StringBuilder();
            for (long j7 : this.f7214b) {
                sb.append(' ');
                sb.append(j7);
            }
            return sb.toString();
        }

        private d(String str) {
            this.f7213a = str;
            this.f7214b = new long[a.this.f7200l];
            this.f7215c = new File[a.this.f7200l];
            this.f7216d = new File[a.this.f7200l];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i7 = 0; i7 < a.this.f7200l; i7++) {
                sb.append(i7);
                this.f7215c[i7] = new File(a.this.f7194e, sb.toString());
                sb.append(".tmp");
                this.f7216d[i7] = new File(a.this.f7194e, sb.toString());
                sb.setLength(length);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f7221a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f7222b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long[] f7223c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final File[] f7224d;

        /* synthetic */ e(a aVar, String str, long j7, File[] fileArr, long[] jArr, CallableC0097a callableC0097a) {
            this(str, j7, fileArr, jArr);
        }

        public File a(int i7) {
            return this.f7224d[i7];
        }

        private e(String str, long j7, File[] fileArr, long[] jArr) {
            this.f7221a = str;
            this.f7222b = j7;
            this.f7224d = fileArr;
            this.f7223c = jArr;
        }
    }

    private a(File file, int i7, int i8, long j7) {
        this.f7194e = file;
        this.f7198j = i7;
        this.f7195g = new File(file, "journal");
        this.f7196h = new File(file, "journal.tmp");
        this.f7197i = new File(file, "journal.bkp");
        this.f7200l = i8;
        this.f7199k = j7;
    }

    private void U() {
        if (this.f7202n == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @TargetApi(26)
    private static void V(Writer writer) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void Z(c cVar, boolean z6) {
        d dVar = cVar.f7209a;
        if (dVar.f7218f != cVar) {
            throw new IllegalStateException();
        }
        if (z6 && !dVar.f7217e) {
            for (int i7 = 0; i7 < this.f7200l; i7++) {
                if (!cVar.f7210b[i7]) {
                    cVar.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i7);
                }
                if (!dVar.k(i7).exists()) {
                    cVar.a();
                    return;
                }
            }
        }
        for (int i8 = 0; i8 < this.f7200l; i8++) {
            File fileK = dVar.k(i8);
            if (!z6) {
                b0(fileK);
            } else if (fileK.exists()) {
                File fileJ = dVar.j(i8);
                fileK.renameTo(fileJ);
                long j7 = dVar.f7214b[i8];
                long length = fileJ.length();
                dVar.f7214b[i8] = length;
                this.f7201m = (this.f7201m - j7) + length;
            }
        }
        this.f7204p++;
        dVar.f7218f = null;
        if (dVar.f7217e || z6) {
            dVar.f7217e = true;
            this.f7202n.append((CharSequence) "CLEAN");
            this.f7202n.append(' ');
            this.f7202n.append((CharSequence) dVar.f7213a);
            this.f7202n.append((CharSequence) dVar.l());
            this.f7202n.append('\n');
            if (z6) {
                long j8 = this.f7205q;
                this.f7205q = 1 + j8;
                dVar.f7219g = j8;
            }
        } else {
            this.f7203o.remove(dVar.f7213a);
            this.f7202n.append((CharSequence) "REMOVE");
            this.f7202n.append(' ');
            this.f7202n.append((CharSequence) dVar.f7213a);
            this.f7202n.append('\n');
        }
        e0(this.f7202n);
        if (this.f7201m > this.f7199k || g0()) {
            this.f7206r.submit(this.f7207s);
        }
    }

    private static void b0(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private synchronized c d0(String str, long j7) {
        U();
        d dVar = this.f7203o.get(str);
        CallableC0097a callableC0097a = null;
        if (j7 != -1 && (dVar == null || dVar.f7219g != j7)) {
            return null;
        }
        if (dVar == null) {
            dVar = new d(this, str, callableC0097a);
            this.f7203o.put(str, dVar);
        } else if (dVar.f7218f != null) {
            return null;
        }
        c cVar = new c(this, dVar, callableC0097a);
        dVar.f7218f = cVar;
        this.f7202n.append((CharSequence) "DIRTY");
        this.f7202n.append(' ');
        this.f7202n.append((CharSequence) str);
        this.f7202n.append('\n');
        e0(this.f7202n);
        return cVar;
    }

    @TargetApi(26)
    private static void e0(Writer writer) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g0() {
        int i7 = this.f7204p;
        return i7 >= 2000 && i7 >= this.f7203o.size();
    }

    public static a h0(File file, int i7, int i8, long j7) throws IOException {
        if (j7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i8 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                n0(file2, file3, false);
            }
        }
        a aVar = new a(file, i7, i8, j7);
        if (aVar.f7195g.exists()) {
            try {
                aVar.j0();
                aVar.i0();
                return aVar;
            } catch (IOException e7) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e7.getMessage() + ", removing");
                aVar.a0();
            }
        }
        file.mkdirs();
        a aVar2 = new a(file, i7, i8, j7);
        aVar2.l0();
        return aVar2;
    }

    private void i0() throws IOException {
        b0(this.f7196h);
        Iterator<d> it = this.f7203o.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i7 = 0;
            if (next.f7218f == null) {
                while (i7 < this.f7200l) {
                    this.f7201m += next.f7214b[i7];
                    i7++;
                }
            } else {
                next.f7218f = null;
                while (i7 < this.f7200l) {
                    b0(next.j(i7));
                    b0(next.k(i7));
                    i7++;
                }
                it.remove();
            }
        }
    }

    private void j0() {
        k1.b bVar = new k1.b(new FileInputStream(this.f7195g), k1.c.f7232a);
        try {
            String strG = bVar.G();
            String strG2 = bVar.G();
            String strG3 = bVar.G();
            String strG4 = bVar.G();
            String strG5 = bVar.G();
            if (!"libcore.io.DiskLruCache".equals(strG) || !"1".equals(strG2) || !Integer.toString(this.f7198j).equals(strG3) || !Integer.toString(this.f7200l).equals(strG4) || !"".equals(strG5)) {
                throw new IOException("unexpected journal header: [" + strG + ", " + strG2 + ", " + strG4 + ", " + strG5 + "]");
            }
            int i7 = 0;
            while (true) {
                try {
                    k0(bVar.G());
                    i7++;
                } catch (EOFException unused) {
                    this.f7204p = i7 - this.f7203o.size();
                    if (bVar.A()) {
                        l0();
                    } else {
                        this.f7202n = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f7195g, true), k1.c.f7232a));
                    }
                    k1.c.a(bVar);
                    return;
                }
            }
        } catch (Throwable th) {
            k1.c.a(bVar);
            throw th;
        }
    }

    private void k0(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i7 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i7);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i7);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.f7203o.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iIndexOf2);
        }
        d dVar = this.f7203o.get(strSubstring);
        CallableC0097a callableC0097a = null;
        if (dVar == null) {
            dVar = new d(this, strSubstring, callableC0097a);
            this.f7203o.put(strSubstring, dVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            dVar.f7217e = true;
            dVar.f7218f = null;
            dVar.n(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
            dVar.f7218f = new c(this, dVar, callableC0097a);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void l0() {
        Writer writer = this.f7202n;
        if (writer != null) {
            V(writer);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f7196h), k1.c.f7232a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f7198j));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f7200l));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (d dVar : this.f7203o.values()) {
                if (dVar.f7218f != null) {
                    bufferedWriter.write("DIRTY " + dVar.f7213a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + dVar.f7213a + dVar.l() + '\n');
                }
            }
            V(bufferedWriter);
            if (this.f7195g.exists()) {
                n0(this.f7195g, this.f7197i, true);
            }
            n0(this.f7196h, this.f7195g, false);
            this.f7197i.delete();
            this.f7202n = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f7195g, true), k1.c.f7232a));
        } catch (Throwable th) {
            V(bufferedWriter);
            throw th;
        }
    }

    private static void n0(File file, File file2, boolean z6) throws IOException {
        if (z6) {
            b0(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o0() {
        while (this.f7201m > this.f7199k) {
            m0(this.f7203o.entrySet().iterator().next().getKey());
        }
    }

    public void a0() throws IOException {
        close();
        k1.c.b(this.f7194e);
    }

    public c c0(String str) {
        return d0(str, -1L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f7202n == null) {
            return;
        }
        for (d dVar : new ArrayList(this.f7203o.values())) {
            if (dVar.f7218f != null) {
                dVar.f7218f.a();
            }
        }
        o0();
        V(this.f7202n);
        this.f7202n = null;
    }

    public synchronized e f0(String str) {
        U();
        d dVar = this.f7203o.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.f7217e) {
            return null;
        }
        for (File file : dVar.f7215c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.f7204p++;
        this.f7202n.append((CharSequence) "READ");
        this.f7202n.append(' ');
        this.f7202n.append((CharSequence) str);
        this.f7202n.append('\n');
        if (g0()) {
            this.f7206r.submit(this.f7207s);
        }
        return new e(this, str, dVar.f7219g, dVar.f7215c, dVar.f7214b, null);
    }

    public synchronized boolean m0(String str) {
        U();
        d dVar = this.f7203o.get(str);
        if (dVar != null && dVar.f7218f == null) {
            for (int i7 = 0; i7 < this.f7200l; i7++) {
                File fileJ = dVar.j(i7);
                if (fileJ.exists() && !fileJ.delete()) {
                    throw new IOException("failed to delete " + fileJ);
                }
                this.f7201m -= dVar.f7214b[i7];
                dVar.f7214b[i7] = 0;
            }
            this.f7204p++;
            this.f7202n.append((CharSequence) "REMOVE");
            this.f7202n.append(' ');
            this.f7202n.append((CharSequence) str);
            this.f7202n.append('\n');
            this.f7203o.remove(str);
            if (g0()) {
                this.f7206r.submit(this.f7207s);
            }
            return true;
        }
        return false;
    }
}
