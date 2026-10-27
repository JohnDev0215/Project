package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.text.Regex;
import kotlin.text.b;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import p.cm;
import p.de2;
import p.io;
import p.lq2;
import p.ng2;
import p.pd2;
import p.pw0;
import p.ry0;
import p.sc;
import p.u4;
import p.ux2;
import p.v02;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class DiskLruCache implements Closeable, Flushable {
    public static final String D;
    public static final String E;
    public static final String F;
    public static final String G;
    public static final String H;
    public static final long I;
    public static final Regex J;
    public static final String K;
    public static final String L;
    public static final String M;
    public static final String N;
    public long A;
    public final TaskQueue B;
    public final DiskLruCache$cleanupTask$1 C;
    public final FileSystem c;
    public final File e;
    public final int k;
    public final int l;
    public final long m;
    public final File n;
    public final File o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final File f97p;
    public long q;
    public io r;
    public final LinkedHashMap s;
    public int t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public final class Editor {
        public final Entry a;
        public final boolean[] b;
        public boolean c;

        public Editor(Entry entry) {
            this.a = entry;
            this.b = entry.e ? null : new boolean[DiskLruCache.this.l];
        }

        public final void a() {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                try {
                    if (!(!this.c)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    if (v03.b(this.a.g, this)) {
                        diskLruCache.c(this, false);
                    }
                    this.c = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void b() {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                try {
                    if (!(!this.c)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    if (v03.b(this.a.g, this)) {
                        diskLruCache.c(this, true);
                    }
                    this.c = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void c() {
            Entry entry = this.a;
            if (v03.b(entry.g, this)) {
                DiskLruCache diskLruCache = DiskLruCache.this;
                if (diskLruCache.v) {
                    diskLruCache.c(this, false);
                } else {
                    entry.f = true;
                }
            }
        }

        public final pd2 d(int i) {
            final DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                try {
                    if (!(!this.c)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    if (!v03.b(this.a.g, this)) {
                        return new cm();
                    }
                    if (!this.a.e) {
                        boolean[] zArr = this.b;
                        v03.h(zArr);
                        zArr[i] = true;
                    }
                    try {
                        return new FaultHidingSink(diskLruCache.c.c((File) this.a.d.get(i)), new ry0() { // from class: okhttp3.internal.cache.DiskLruCache$Editor$newSink$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // p.ry0
                            public final Object invoke(Object obj) {
                                v03.j((IOException) obj, "it");
                                DiskLruCache diskLruCache2 = diskLruCache;
                                DiskLruCache.Editor editor = this;
                                synchronized (diskLruCache2) {
                                    editor.c();
                                }
                                return lq2.a;
                            }
                        });
                    } catch (FileNotFoundException unused) {
                        return new cm();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final class Entry {
        public final String a;
        public final long[] b;
        public final ArrayList c;
        public final ArrayList d;
        public boolean e;
        public boolean f;
        public Editor g;
        public int h;
        public long i;
        public final /* synthetic */ DiskLruCache j;

        public Entry(DiskLruCache diskLruCache, String str) {
            v03.j(str, "key");
            this.j = diskLruCache;
            this.a = str;
            this.b = new long[diskLruCache.l];
            this.c = new ArrayList();
            this.d = new ArrayList();
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < diskLruCache.l; i++) {
                sb.append(i);
                this.c.add(new File(this.j.e, sb.toString()));
                sb.append(".tmp");
                this.d.add(new File(this.j.e, sb.toString()));
                sb.setLength(length);
            }
        }

        public final Snapshot a() {
            byte[] bArr = Util.a;
            if (!this.e) {
                return null;
            }
            final DiskLruCache diskLruCache = this.j;
            if (!diskLruCache.v && (this.g != null || this.f)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            long[] jArr = (long[]) this.b.clone();
            try {
                int i = diskLruCache.l;
                for (int i2 = 0; i2 < i; i2++) {
                    final de2 de2VarB = diskLruCache.c.b((File) this.c.get(i2));
                    if (!diskLruCache.v) {
                        this.h++;
                        de2VarB = new pw0(de2VarB) { // from class: okhttp3.internal.cache.DiskLruCache$Entry$newSource$1
                            public boolean e;

                            @Override // p.pw0, java.io.Closeable, java.lang.AutoCloseable
                            public final void close() {
                                super.close();
                                if (this.e) {
                                    return;
                                }
                                this.e = true;
                                DiskLruCache diskLruCache2 = diskLruCache;
                                DiskLruCache.Entry entry = this;
                                synchronized (diskLruCache2) {
                                    int i3 = entry.h - 1;
                                    entry.h = i3;
                                    if (i3 == 0 && entry.f) {
                                        diskLruCache2.H0(entry);
                                    }
                                }
                            }
                        };
                    }
                    arrayList.add(de2VarB);
                }
                return new Snapshot(this.j, this.a, this.i, arrayList, jArr);
            } catch (FileNotFoundException unused) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Util.c((de2) it.next());
                }
                try {
                    diskLruCache.H0(this);
                } catch (IOException unused2) {
                }
                return null;
            }
        }
    }

    public final class Snapshot implements Closeable {
        public final String c;
        public final long e;
        public final List k;
        public final /* synthetic */ DiskLruCache l;

        public Snapshot(DiskLruCache diskLruCache, String str, long j, ArrayList arrayList, long[] jArr) {
            v03.j(str, "key");
            v03.j(jArr, "lengths");
            this.l = diskLruCache;
            this.c = str;
            this.e = j;
            this.k = arrayList;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                Util.c((de2) it.next());
            }
        }
    }

    static {
        new Companion(0);
        D = "journal";
        E = "journal.tmp";
        F = "journal.bkp";
        G = "libcore.io.DiskLruCache";
        H = "1";
        I = -1L;
        J = new Regex("[a-z0-9_-]{1,120}");
        K = "CLEAN";
        L = "DIRTY";
        M = "REMOVE";
        N = "READ";
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [okhttp3.internal.cache.DiskLruCache$cleanupTask$1] */
    public DiskLruCache(FileSystem fileSystem, File file, TaskRunner taskRunner) {
        v03.j(taskRunner, "taskRunner");
        this.c = fileSystem;
        this.e = file;
        this.k = 201105;
        this.l = 2;
        this.m = 33554432L;
        this.s = new LinkedHashMap(0, 0.75f, true);
        this.B = taskRunner.f();
        final String strM = u4.m(new StringBuilder(), Util.g, " Cache");
        this.C = new Task(strM) { // from class: okhttp3.internal.cache.DiskLruCache$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public final long a() {
                DiskLruCache diskLruCache = this.e;
                synchronized (diskLruCache) {
                    if (!diskLruCache.w || diskLruCache.x) {
                        return -1L;
                    }
                    try {
                        diskLruCache.I0();
                    } catch (IOException unused) {
                        diskLruCache.y = true;
                    }
                    try {
                        if (diskLruCache.J()) {
                            diskLruCache.G0();
                            diskLruCache.t = 0;
                        }
                    } catch (IOException unused2) {
                        diskLruCache.z = true;
                        diskLruCache.r = v03.c(new cm());
                    }
                    return -1L;
                }
            }
        };
        this.n = new File(file, D);
        this.o = new File(file, E);
        this.f97p = new File(file, F);
    }

    public static void J0(String str) {
        if (J.a(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
    }

    public final synchronized void E() {
        boolean z;
        try {
            byte[] bArr = Util.a;
            if (this.w) {
                return;
            }
            if (this.c.f(this.f97p)) {
                if (this.c.f(this.n)) {
                    this.c.a(this.f97p);
                } else {
                    this.c.g(this.f97p, this.n);
                }
            }
            FileSystem fileSystem = this.c;
            File file = this.f97p;
            v03.j(fileSystem, "<this>");
            v03.j(file, "file");
            sc scVarC = fileSystem.c(file);
            try {
                fileSystem.a(file);
                ux2.e(scVarC, null);
                z = true;
            } catch (IOException unused) {
                ux2.e(scVarC, null);
                fileSystem.a(file);
                z = false;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    ux2.e(scVarC, th);
                    throw th2;
                }
            }
            this.v = z;
            if (this.c.f(this.n)) {
                try {
                    o0();
                    X();
                    this.w = true;
                    return;
                } catch (IOException e) {
                    Platform.a.getClass();
                    Platform platform = Platform.b;
                    String str = "DiskLruCache " + this.e + " is corrupt: " + e.getMessage() + ", removing";
                    platform.getClass();
                    Platform.i(5, str, e);
                    try {
                        close();
                        this.c.d(this.e);
                        this.x = false;
                        G0();
                        this.w = true;
                    } catch (Throwable th3) {
                        this.x = false;
                        throw th3;
                    }
                }
            }
            G0();
            this.w = true;
        } catch (Throwable th4) {
            throw th4;
        }
    }

    public final void E0(String str) throws IOException {
        String strSubstring;
        int iN0 = b.n0(str, ' ', 0, false, 6);
        if (iN0 == -1) {
            throw new IOException("unexpected journal line: ".concat(str));
        }
        int i = iN0 + 1;
        int iN02 = b.n0(str, ' ', i, false, 4);
        LinkedHashMap linkedHashMap = this.s;
        if (iN02 == -1) {
            strSubstring = str.substring(i);
            v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
            String str2 = M;
            if (iN0 == str2.length() && ng2.g0(str, str2, false)) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iN02);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        Entry entry = (Entry) linkedHashMap.get(strSubstring);
        if (entry == null) {
            entry = new Entry(this, strSubstring);
            linkedHashMap.put(strSubstring, entry);
        }
        if (iN02 != -1) {
            String str3 = K;
            if (iN0 == str3.length() && ng2.g0(str, str3, false)) {
                String strSubstring2 = str.substring(iN02 + 1);
                v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                List listY0 = b.y0(strSubstring2, new char[]{' '});
                entry.e = true;
                entry.g = null;
                if (listY0.size() != entry.j.l) {
                    throw new IOException("unexpected journal line: " + listY0);
                }
                try {
                    int size = listY0.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        entry.b[i2] = Long.parseLong((String) listY0.get(i2));
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw new IOException("unexpected journal line: " + listY0);
                }
            }
        }
        if (iN02 == -1) {
            String str4 = L;
            if (iN0 == str4.length() && ng2.g0(str, str4, false)) {
                entry.g = new Editor(entry);
                return;
            }
        }
        if (iN02 == -1) {
            String str5 = N;
            if (iN0 == str5.length() && ng2.g0(str, str5, false)) {
                return;
            }
        }
        throw new IOException("unexpected journal line: ".concat(str));
    }

    public final synchronized void G0() {
        try {
            io ioVar = this.r;
            if (ioVar != null) {
                ioVar.close();
            }
            v02 v02VarC = v03.c(this.c.c(this.o));
            try {
                v02VarC.s0(G);
                v02VarC.R(10);
                v02VarC.s0(H);
                v02VarC.R(10);
                v02VarC.t0(this.k);
                v02VarC.R(10);
                v02VarC.t0(this.l);
                v02VarC.R(10);
                v02VarC.R(10);
                Iterator it = this.s.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Entry entry = (Entry) it.next();
                    if (entry.g != null) {
                        v02VarC.s0(L);
                        v02VarC.R(32);
                        v02VarC.s0(entry.a);
                        v02VarC.R(10);
                    } else {
                        v02VarC.s0(K);
                        v02VarC.R(32);
                        v02VarC.s0(entry.a);
                        for (long j : entry.b) {
                            v02VarC.R(32);
                            v02VarC.t0(j);
                        }
                        v02VarC.R(10);
                    }
                }
                ux2.e(v02VarC, null);
                if (this.c.f(this.n)) {
                    this.c.g(this.n, this.f97p);
                }
                this.c.g(this.o, this.n);
                this.c.a(this.f97p);
                this.r = v03.c(new FaultHidingSink(this.c.e(this.n), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
                this.u = false;
                this.z = false;
            } finally {
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void H0(Entry entry) {
        io ioVar;
        v03.j(entry, "entry");
        boolean z = this.v;
        String str = entry.a;
        if (!z) {
            if (entry.h > 0 && (ioVar = this.r) != null) {
                ioVar.s0(L);
                ioVar.R(32);
                ioVar.s0(str);
                ioVar.R(10);
                ioVar.flush();
            }
            if (entry.h > 0 || entry.g != null) {
                entry.f = true;
                return;
            }
        }
        Editor editor = entry.g;
        if (editor != null) {
            editor.c();
        }
        for (int i = 0; i < this.l; i++) {
            this.c.a((File) entry.c.get(i));
            long j = this.q;
            long[] jArr = entry.b;
            this.q = j - jArr[i];
            jArr[i] = 0;
        }
        this.t++;
        io ioVar2 = this.r;
        if (ioVar2 != null) {
            ioVar2.s0(M);
            ioVar2.R(32);
            ioVar2.s0(str);
            ioVar2.R(10);
        }
        this.s.remove(str);
        if (J()) {
            this.B.c(this.C, 0L);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        H0(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I0() {
        /*
            r4 = this;
        L0:
            long r0 = r4.q
            long r2 = r4.m
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L27
            java.util.LinkedHashMap r0 = r4.s
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L26
            java.lang.Object r1 = r0.next()
            okhttp3.internal.cache.DiskLruCache$Entry r1 = (okhttp3.internal.cache.DiskLruCache.Entry) r1
            boolean r2 = r1.f
            if (r2 != 0) goto L12
            r4.H0(r1)
            goto L0
        L26:
            return
        L27:
            r0 = 0
            r4.y = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.I0():void");
    }

    public final boolean J() {
        int i = this.t;
        return i >= 2000 && i >= this.s.size();
    }

    public final void X() {
        File file = this.o;
        FileSystem fileSystem = this.c;
        fileSystem.a(file);
        Iterator it = this.s.values().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            v03.i(next, "i.next()");
            Entry entry = (Entry) next;
            Editor editor = entry.g;
            int i = this.l;
            int i2 = 0;
            if (editor == null) {
                while (i2 < i) {
                    this.q += entry.b[i2];
                    i2++;
                }
            } else {
                entry.g = null;
                while (i2 < i) {
                    fileSystem.a((File) entry.c.get(i2));
                    fileSystem.a((File) entry.d.get(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    public final synchronized void b() {
        if (!(!this.x)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    public final synchronized void c(Editor editor, boolean z) {
        v03.j(editor, "editor");
        Entry entry = editor.a;
        if (!v03.b(entry.g, editor)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (z && !entry.e) {
            int i = this.l;
            for (int i2 = 0; i2 < i; i2++) {
                boolean[] zArr = editor.b;
                v03.h(zArr);
                if (!zArr[i2]) {
                    editor.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                }
                if (!this.c.f((File) entry.d.get(i2))) {
                    editor.a();
                    return;
                }
            }
        }
        int i3 = this.l;
        for (int i4 = 0; i4 < i3; i4++) {
            File file = (File) entry.d.get(i4);
            if (!z || entry.f) {
                this.c.a(file);
            } else if (this.c.f(file)) {
                File file2 = (File) entry.c.get(i4);
                this.c.g(file, file2);
                long j = entry.b[i4];
                long jH = this.c.h(file2);
                entry.b[i4] = jH;
                this.q = (this.q - j) + jH;
            }
        }
        entry.g = null;
        if (entry.f) {
            H0(entry);
            return;
        }
        this.t++;
        io ioVar = this.r;
        v03.h(ioVar);
        if (entry.e || z) {
            entry.e = true;
            ioVar.s0(K).R(32);
            ioVar.s0(entry.a);
            for (long j2 : entry.b) {
                ioVar.R(32).t0(j2);
            }
            ioVar.R(10);
            if (z) {
                long j3 = this.A;
                this.A = 1 + j3;
                entry.i = j3;
            }
        } else {
            this.s.remove(entry.a);
            ioVar.s0(M).R(32);
            ioVar.s0(entry.a);
            ioVar.R(10);
        }
        ioVar.flush();
        if (this.q > this.m || J()) {
            this.B.c(this.C, 0L);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.w && !this.x) {
                Collection collectionValues = this.s.values();
                v03.i(collectionValues, "lruEntries.values");
                for (Entry entry : (Entry[]) collectionValues.toArray(new Entry[0])) {
                    Editor editor = entry.g;
                    if (editor != null) {
                        editor.c();
                    }
                }
                I0();
                io ioVar = this.r;
                v03.h(ioVar);
                ioVar.close();
                this.r = null;
                this.x = true;
                return;
            }
            this.x = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.w) {
            b();
            I0();
            io ioVar = this.r;
            v03.h(ioVar);
            ioVar.flush();
        }
    }

    public final synchronized Editor i(String str, long j) {
        try {
            v03.j(str, "key");
            E();
            b();
            J0(str);
            Entry entry = (Entry) this.s.get(str);
            if (j != I && (entry == null || entry.i != j)) {
                return null;
            }
            if ((entry != null ? entry.g : null) != null) {
                return null;
            }
            if (entry != null && entry.h != 0) {
                return null;
            }
            if (!this.y && !this.z) {
                io ioVar = this.r;
                v03.h(ioVar);
                ioVar.s0(L).R(32).s0(str).R(10);
                ioVar.flush();
                if (this.u) {
                    return null;
                }
                if (entry == null) {
                    entry = new Entry(this, str);
                    this.s.put(str, entry);
                }
                Editor editor = new Editor(entry);
                entry.g = editor;
                return editor;
            }
            this.B.c(this.C, 0L);
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void o0() {
        File file = this.n;
        FileSystem fileSystem = this.c;
        w02 w02VarD = v03.d(fileSystem.b(file));
        try {
            String strG0 = w02VarD.g0(Long.MAX_VALUE);
            String strG02 = w02VarD.g0(Long.MAX_VALUE);
            String strG03 = w02VarD.g0(Long.MAX_VALUE);
            String strG04 = w02VarD.g0(Long.MAX_VALUE);
            String strG05 = w02VarD.g0(Long.MAX_VALUE);
            if (!v03.b(G, strG0) || !v03.b(H, strG02) || !v03.b(String.valueOf(this.k), strG03) || !v03.b(String.valueOf(this.l), strG04) || strG05.length() > 0) {
                throw new IOException("unexpected journal header: [" + strG0 + ", " + strG02 + ", " + strG04 + ", " + strG05 + ']');
            }
            int i = 0;
            while (true) {
                try {
                    E0(w02VarD.g0(Long.MAX_VALUE));
                    i++;
                } catch (EOFException unused) {
                    this.t = i - this.s.size();
                    if (w02VarD.Q()) {
                        this.r = v03.c(new FaultHidingSink(fileSystem.e(file), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
                    } else {
                        G0();
                    }
                    ux2.e(w02VarD, null);
                    return;
                }
            }
        } finally {
        }
    }

    public final synchronized Snapshot q(String str) {
        v03.j(str, "key");
        E();
        b();
        J0(str);
        Entry entry = (Entry) this.s.get(str);
        if (entry == null) {
            return null;
        }
        Snapshot snapshotA = entry.a();
        if (snapshotA == null) {
            return null;
        }
        this.t++;
        io ioVar = this.r;
        v03.h(ioVar);
        ioVar.s0(N).R(32).s0(str).R(10);
        if (J()) {
            this.B.c(this.C, 0L);
        }
        return snapshotA;
    }
}
