package io.sentry.cache;

import io.sentry.C1474i1;
import io.sentry.C1489n1;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.R1;
import io.sentry.T;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class c implements d {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Charset f16489r = Charset.forName("UTF-8");
    public final F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.d f16490m = new io.sentry.util.d(new G2.a(this, 17));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final File f16491n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CountDownLatch f16492p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final WeakHashMap f16493q;

    public c(F1 f12, String str, int i7) {
        h6.e.q(f12, "SentryOptions is required.");
        this.l = f12;
        this.f16491n = new File(str);
        this.o = i7;
        this.f16493q = new WeakHashMap();
        this.f16492p = new CountDownLatch(1);
    }

    public final File[] a() {
        File file = this.f16491n;
        if (file.isDirectory() && file.canWrite() && file.canRead()) {
            File[] fileArrListFiles = file.listFiles(new b());
            if (fileArrListFiles != null) {
                return fileArrListFiles;
            }
        } else {
            this.l.getLogger().k(EnumC1495p1.ERROR, "The directory for caching files is inaccessible.: %s", file.getAbsolutePath());
        }
        return new File[0];
    }

    public final synchronized File d(C1489n1 c1489n1) {
        String str;
        try {
            if (this.f16493q.containsKey(c1489n1)) {
                str = (String) this.f16493q.get(c1489n1);
            } else {
                String str2 = UUID.randomUUID() + ".envelope";
                this.f16493q.put(c1489n1, str2);
                str = str2;
            }
        } catch (Throwable th) {
            throw th;
        }
        return new File(this.f16491n.getAbsolutePath(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0211 A[PHI: r3 r4 r9
  0x0211: PHI (r3v29 java.io.File[]) = 
  (r3v28 java.io.File[])
  (r3v28 java.io.File[])
  (r3v28 java.io.File[])
  (r3v28 java.io.File[])
  (r3v28 java.io.File[])
  (r3v28 java.io.File[])
  (r3v32 java.io.File[])
 binds: [B:10:0x0045, B:12:0x0053, B:25:0x008c, B:32:0x009f, B:35:0x00a5, B:37:0x00ab, B:311:0x0211] A[DONT_GENERATE, DONT_INLINE]
  0x0211: PHI (r4v30 int) = (r4v29 int), (r4v29 int), (r4v29 int), (r4v29 int), (r4v29 int), (r4v29 int), (r4v34 int) binds: [B:10:0x0045, B:12:0x0053, B:25:0x008c, B:32:0x009f, B:35:0x00a5, B:37:0x00ab, B:311:0x0211] A[DONT_GENERATE, DONT_INLINE]
  0x0211: PHI (r9v11 java.io.File[]) = 
  (r9v10 java.io.File[])
  (r9v10 java.io.File[])
  (r9v10 java.io.File[])
  (r9v10 java.io.File[])
  (r9v10 java.io.File[])
  (r9v10 java.io.File[])
  (r9v13 java.io.File[])
 binds: [B:10:0x0045, B:12:0x0053, B:25:0x008c, B:32:0x009f, B:35:0x00a5, B:37:0x00ab, B:311:0x0211] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0107  */
    @Override // io.sentry.cache.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(io.sentry.C1489n1 r23, io.sentry.C1523x r24) {
        /*
            Method dump skipped, instruction units count: 1445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.cache.c.g(io.sentry.n1, io.sentry.x):void");
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        F1 f12 = this.l;
        File[] fileArrA = a();
        ArrayList arrayList = new ArrayList(fileArrA.length);
        for (File file : fileArrA) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    arrayList.add(((T) this.f16490m.a()).c(bufferedInputStream));
                    bufferedInputStream.close();
                } catch (Throwable th) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "Envelope file '%s' disappeared while converting all cached files to envelopes.", file.getAbsolutePath());
            } catch (IOException e10) {
                f12.getLogger().s(EnumC1495p1.ERROR, "Error while reading cached envelope from file " + file.getAbsolutePath(), e10);
            }
        }
        return arrayList.iterator();
    }

    public final C1489n1 l(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                C1489n1 c1489n1C = ((T) this.f16490m.a()).c(bufferedInputStream);
                bufferedInputStream.close();
                return c1489n1C;
            } finally {
            }
        } catch (IOException e10) {
            this.l.getLogger().s(EnumC1495p1.ERROR, "Failed to deserialize the envelope.", e10);
            return null;
        }
    }

    public final R1 m(C1474i1 c1474i1) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(c1474i1.d()), f16489r));
            try {
                R1 r12 = (R1) ((T) this.f16490m.a()).a(bufferedReader, R1.class);
                bufferedReader.close();
                return r12;
            } finally {
            }
        } catch (Throwable th) {
            this.l.getLogger().s(EnumC1495p1.ERROR, "Failed to deserialize the session.", th);
            return null;
        }
    }

    @Override // io.sentry.cache.d
    public final void p(C1489n1 c1489n1) {
        h6.e.q(c1489n1, "Envelope is required.");
        File fileD = d(c1489n1);
        boolean zExists = fileD.exists();
        F1 f12 = this.l;
        if (!zExists) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Envelope was not cached: %s", fileD.getAbsolutePath());
            return;
        }
        f12.getLogger().k(EnumC1495p1.DEBUG, "Discarding envelope from cache: %s", fileD.getAbsolutePath());
        if (fileD.delete()) {
            return;
        }
        f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete envelope: %s", fileD.getAbsolutePath());
    }

    public final boolean q() {
        F1 f12 = this.l;
        try {
            return this.f16492p.await(f12.getSessionFlushTimeoutMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            f12.getLogger().k(EnumC1495p1.DEBUG, "Timed out waiting for previous session to flush.", new Object[0]);
            return false;
        }
    }

    public final void r(File file, R1 r12) {
        boolean zExists = file.exists();
        F1 f12 = this.l;
        UUID uuid = r12.f15931p;
        if (zExists) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Overwriting session to offline storage: %s", uuid);
            if (!file.delete()) {
                f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete: %s", file.getAbsolutePath());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, f16489r));
                try {
                    ((T) this.f16490m.a()).f(r12, bufferedWriter);
                    bufferedWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Error writing Session to offline storage: %s", uuid);
        }
    }
}
