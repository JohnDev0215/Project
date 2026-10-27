package com.bumptech.glide.j;

import android.annotation.TargetApi;
import android.os.Build;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f2868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final File f2869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final File f2870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f2871e;
    private final int f;
    private long g;
    private final int h;
    private Writer j;
    private int l;
    private long i = 0;
    private final LinkedHashMap<String, d> k = new LinkedHashMap<>(0, 0.75f, true);
    private long m = 0;
    final ThreadPoolExecutor n = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));
    private final Callable<Void> o = new CallableC0059a();

    /* JADX INFO: renamed from: com.bumptech.glide.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DiskLruCache.java */
    class CallableC0059a implements Callable<Void> {
        CallableC0059a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (a.this) {
                if (a.this.j == null) {
                    return null;
                }
                a.this.I();
                if (a.this.A()) {
                    a.this.F();
                    a.this.l = 0;
                }
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private static final class b implements ThreadFactory {
        private b() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }

        /* synthetic */ b(CallableC0059a callableC0059a) {
            this();
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f2873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean[] f2874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2875c;

        /* synthetic */ c(a aVar, d dVar, CallableC0059a callableC0059a) {
            this(dVar);
        }

        public void a() throws IOException {
            a.this.t(this, false);
        }

        public void b() {
            if (this.f2875c) {
                return;
            }
            try {
                a();
            } catch (IOException unused) {
            }
        }

        public void e() throws IOException {
            a.this.t(this, true);
            this.f2875c = true;
        }

        public File f(int i) throws IOException {
            File fileK;
            synchronized (a.this) {
                if (this.f2873a.f != this) {
                    throw new IllegalStateException();
                }
                if (!this.f2873a.f2881e) {
                    this.f2874b[i] = true;
                }
                fileK = this.f2873a.k(i);
                if (!a.this.f2868b.exists()) {
                    a.this.f2868b.mkdirs();
                }
            }
            return fileK;
        }

        private c(d dVar) {
            this.f2873a = dVar;
            this.f2874b = dVar.f2881e ? null : new boolean[a.this.h];
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    private final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f2877a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long[] f2878b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        File[] f2879c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        File[] f2880d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2881e;
        private c f;
        private long g;

        /* synthetic */ d(a aVar, String str, CallableC0059a callableC0059a) {
            this(str);
        }

        private IOException m(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(String[] strArr) throws IOException {
            if (strArr.length != a.this.h) {
                throw m(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.f2878b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw m(strArr);
                }
            }
        }

        public File j(int i) {
            return this.f2879c[i];
        }

        public File k(int i) {
            return this.f2880d[i];
        }

        public String l() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.f2878b) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        private d(String str) {
            this.f2877a = str;
            this.f2878b = new long[a.this.h];
            this.f2879c = new File[a.this.h];
            this.f2880d = new File[a.this.h];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < a.this.h; i++) {
                sb.append(i);
                this.f2879c[i] = new File(a.this.f2868b, sb.toString());
                sb.append(".tmp");
                this.f2880d[i] = new File(a.this.f2868b, sb.toString());
                sb.setLength(length);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f2882a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f2883b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long[] f2884c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final File[] f2885d;

        /* synthetic */ e(a aVar, String str, long j, File[] fileArr, long[] jArr, CallableC0059a callableC0059a) {
            this(str, j, fileArr, jArr);
        }

        public File a(int i) {
            return this.f2885d[i];
        }

        private e(String str, long j, File[] fileArr, long[] jArr) {
            this.f2882a = str;
            this.f2883b = j;
            this.f2885d = fileArr;
            this.f2884c = jArr;
        }
    }

    private a(File file, int i, int i2, long j) {
        this.f2868b = file;
        this.f = i;
        this.f2869c = new File(file, "journal");
        this.f2870d = new File(file, "journal.tmp");
        this.f2871e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A() {
        int i = this.l;
        return i >= 2000 && i >= this.k.size();
    }

    public static a B(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                H(file2, file3, false);
            }
        }
        a aVar = new a(file, i, i2, j);
        if (aVar.f2869c.exists()) {
            try {
                aVar.D();
                aVar.C();
                return aVar;
            } catch (IOException e2) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                aVar.u();
            }
        }
        file.mkdirs();
        a aVar2 = new a(file, i, i2, j);
        aVar2.F();
        return aVar2;
    }

    private void C() throws IOException {
        v(this.f2870d);
        Iterator<d> it = this.k.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i = 0;
            if (next.f == null) {
                while (i < this.h) {
                    this.i += next.f2878b[i];
                    i++;
                }
            } else {
                next.f = null;
                while (i < this.h) {
                    v(next.j(i));
                    v(next.k(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    private void D() throws IOException {
        com.bumptech.glide.j.b bVar = new com.bumptech.glide.j.b(new FileInputStream(this.f2869c), com.bumptech.glide.j.c.f2892a);
        try {
            String strM = bVar.m();
            String strM2 = bVar.m();
            String strM3 = bVar.m();
            String strM4 = bVar.m();
            String strM5 = bVar.m();
            if (!"libcore.io.DiskLruCache".equals(strM) || !"1".equals(strM2) || !Integer.toString(this.f).equals(strM3) || !Integer.toString(this.h).equals(strM4) || !"".equals(strM5)) {
                throw new IOException("unexpected journal header: [" + strM + ", " + strM2 + ", " + strM4 + ", " + strM5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    E(bVar.m());
                    i++;
                } catch (EOFException unused) {
                    this.l = i - this.k.size();
                    if (bVar.l()) {
                        F();
                    } else {
                        this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2869c, true), com.bumptech.glide.j.c.f2892a));
                    }
                    com.bumptech.glide.j.c.a(bVar);
                    return;
                }
            }
        } catch (Throwable th) {
            com.bumptech.glide.j.c.a(bVar);
            throw th;
        }
    }

    private void E(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.k.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        d dVar = this.k.get(strSubstring);
        CallableC0059a callableC0059a = null;
        if (dVar == null) {
            dVar = new d(this, strSubstring, callableC0059a);
            this.k.put(strSubstring, dVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            dVar.f2881e = true;
            dVar.f = null;
            dVar.n(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
            dVar.f = new c(this, dVar, callableC0059a);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void F() throws IOException {
        Writer writer = this.j;
        if (writer != null) {
            s(writer);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2870d), com.bumptech.glide.j.c.f2892a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (d dVar : this.k.values()) {
                if (dVar.f != null) {
                    bufferedWriter.write("DIRTY " + dVar.f2877a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + dVar.f2877a + dVar.l() + '\n');
                }
            }
            s(bufferedWriter);
            if (this.f2869c.exists()) {
                H(this.f2869c, this.f2871e, true);
            }
            H(this.f2870d, this.f2869c, false);
            this.f2871e.delete();
            this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f2869c, true), com.bumptech.glide.j.c.f2892a));
        } catch (Throwable th) {
            s(bufferedWriter);
            throw th;
        }
    }

    private static void H(File file, File file2, boolean z) throws IOException {
        if (z) {
            v(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() throws IOException {
        while (this.i > this.g) {
            G(this.k.entrySet().iterator().next().getKey());
        }
    }

    private void r() {
        if (this.j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @TargetApi(26)
    private static void s(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.close();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void t(c cVar, boolean z) throws IOException {
        d dVar = cVar.f2873a;
        if (dVar.f != cVar) {
            throw new IllegalStateException();
        }
        if (z && !dVar.f2881e) {
            for (int i = 0; i < this.h; i++) {
                if (!cVar.f2874b[i]) {
                    cVar.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!dVar.k(i).exists()) {
                    cVar.a();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.h; i2++) {
            File fileK = dVar.k(i2);
            if (!z) {
                v(fileK);
            } else if (fileK.exists()) {
                File fileJ = dVar.j(i2);
                fileK.renameTo(fileJ);
                long j = dVar.f2878b[i2];
                long length = fileJ.length();
                dVar.f2878b[i2] = length;
                this.i = (this.i - j) + length;
            }
        }
        this.l++;
        dVar.f = null;
        if (dVar.f2881e || z) {
            dVar.f2881e = true;
            this.j.append((CharSequence) "CLEAN");
            this.j.append(' ');
            this.j.append((CharSequence) dVar.f2877a);
            this.j.append((CharSequence) dVar.l());
            this.j.append('\n');
            if (z) {
                long j2 = this.m;
                this.m = 1 + j2;
                dVar.g = j2;
            }
        } else {
            this.k.remove(dVar.f2877a);
            this.j.append((CharSequence) "REMOVE");
            this.j.append(' ');
            this.j.append((CharSequence) dVar.f2877a);
            this.j.append('\n');
        }
        y(this.j);
        if (this.i > this.g || A()) {
            this.n.submit(this.o);
        }
    }

    private static void v(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private synchronized c x(String str, long j) throws IOException {
        r();
        d dVar = this.k.get(str);
        CallableC0059a callableC0059a = null;
        if (j != -1 && (dVar == null || dVar.g != j)) {
            return null;
        }
        if (dVar == null) {
            dVar = new d(this, str, callableC0059a);
            this.k.put(str, dVar);
        } else if (dVar.f != null) {
            return null;
        }
        c cVar = new c(this, dVar, callableC0059a);
        dVar.f = cVar;
        this.j.append((CharSequence) "DIRTY");
        this.j.append(' ');
        this.j.append((CharSequence) str);
        this.j.append('\n');
        y(this.j);
        return cVar;
    }

    @TargetApi(26)
    private static void y(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.flush();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public synchronized boolean G(String str) throws IOException {
        r();
        d dVar = this.k.get(str);
        if (dVar != null && dVar.f == null) {
            for (int i = 0; i < this.h; i++) {
                File fileJ = dVar.j(i);
                if (fileJ.exists() && !fileJ.delete()) {
                    throw new IOException("failed to delete " + fileJ);
                }
                this.i -= dVar.f2878b[i];
                dVar.f2878b[i] = 0;
            }
            this.l++;
            this.j.append((CharSequence) "REMOVE");
            this.j.append(' ');
            this.j.append((CharSequence) str);
            this.j.append('\n');
            this.k.remove(str);
            if (A()) {
                this.n.submit(this.o);
            }
            return true;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.j == null) {
            return;
        }
        for (d dVar : new ArrayList(this.k.values())) {
            if (dVar.f != null) {
                dVar.f.a();
            }
        }
        I();
        s(this.j);
        this.j = null;
    }

    public void u() throws IOException {
        close();
        com.bumptech.glide.j.c.b(this.f2868b);
    }

    public c w(String str) throws IOException {
        return x(str, -1L);
    }

    public synchronized e z(String str) throws IOException {
        r();
        d dVar = this.k.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.f2881e) {
            return null;
        }
        for (File file : dVar.f2879c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.l++;
        this.j.append((CharSequence) "READ");
        this.j.append(' ');
        this.j.append((CharSequence) str);
        this.j.append('\n');
        if (A()) {
            this.n.submit(this.o);
        }
        return new e(this, str, dVar.g, dVar.f2879c, dVar.f2878b, null);
    }
}
