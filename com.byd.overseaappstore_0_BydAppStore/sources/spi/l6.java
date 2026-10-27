package spi;

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
import java.util.Objects;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class l6 implements Closeable, Flushable {

    @JvmField
    public static final long A;

    @JvmField
    @NotNull
    public static final Regex B;

    @JvmField
    @NotNull
    public static final String C;

    @JvmField
    @NotNull
    public static final String D;

    @JvmField
    @NotNull
    public static final String E;

    @JvmField
    @NotNull
    public static final String F;

    @JvmField
    @NotNull
    public static final String v;

    @JvmField
    @NotNull
    public static final String w;

    @JvmField
    @NotNull
    public static final String x;

    @JvmField
    @NotNull
    public static final String y;

    @JvmField
    @NotNull
    public static final String z;
    public long a;
    public final File b;
    public final File c;
    public final File d;
    public long e;
    public k2 f;

    @NotNull
    public final LinkedHashMap<String, e> g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public long o;
    public final vs p;
    public final b q;

    @NotNull
    public final yb r;

    @NotNull
    public final File s;
    public final int t;
    public final int u;

    public final class a {

        @Nullable
        public final boolean[] a;
        public boolean b;

        @NotNull
        public final e c;
        public final /* synthetic */ l6 d;

        /* JADX INFO: renamed from: spi.l6$a$a, reason: collision with other inner class name */
        public static final class C0131a extends Lambda implements Function1<IOException, Unit> {
            public C0131a(int i) {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(IOException iOException) {
                Unit unit;
                IOException it = iOException;
                Intrinsics.checkNotNullParameter(it, "it");
                synchronized (a.this.d) {
                    a.this.c();
                    unit = Unit.INSTANCE;
                }
                return unit;
            }
        }

        public a(@NotNull l6 l6Var, e entry) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            this.d = l6Var;
            this.c = entry;
            this.a = entry.d ? null : new boolean[l6Var.u];
        }

        public final void a() throws IOException {
            synchronized (this.d) {
                if (!(!this.b)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (Intrinsics.areEqual(this.c.f, this)) {
                    this.d.B(this, false);
                }
                this.b = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void b() throws IOException {
            synchronized (this.d) {
                if (!(!this.b)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (Intrinsics.areEqual(this.c.f, this)) {
                    this.d.B(this, true);
                }
                this.b = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void c() throws IOException {
            if (Intrinsics.areEqual(this.c.f, this)) {
                l6 l6Var = this.d;
                if (l6Var.j) {
                    l6Var.B(this, false);
                } else {
                    this.c.e = true;
                }
            }
        }

        /* JADX WARN: Type inference failed for: r1v10, types: [java.util.ArrayList, java.util.List<java.io.File>] */
        @NotNull
        public final rp d(int i) {
            synchronized (this.d) {
                if (!(!this.b)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (!Intrinsics.areEqual(this.c.f, this)) {
                    return new b2();
                }
                if (!this.c.d) {
                    boolean[] zArr = this.a;
                    Intrinsics.checkNotNull(zArr);
                    zArr[i] = true;
                }
                try {
                    return new wa(this.d.r.b((File) this.c.c.get(i)), new C0131a(i));
                } catch (FileNotFoundException unused) {
                    return new b2();
                }
            }
        }
    }

    public static final class b extends ts {
        public b(String str) {
            super(str, false, 2, null);
        }

        @Override // spi.ts
        public final long a() {
            synchronized (l6.this) {
                l6 l6Var = l6.this;
                if (!l6Var.k || l6Var.l) {
                    return -1L;
                }
                try {
                    l6Var.M();
                } catch (IOException unused) {
                    l6.this.m = true;
                }
                try {
                    if (l6.this.F()) {
                        l6.this.K();
                        l6.this.h = 0;
                    }
                } catch (IOException unused2) {
                    l6 l6Var2 = l6.this;
                    l6Var2.n = true;
                    l6Var2.f = spi.b.b(new b2());
                }
                return -1L;
            }
        }
    }

    public static final class c extends Lambda implements Function1<IOException, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(IOException iOException) {
            IOException it = iOException;
            Intrinsics.checkNotNullParameter(it, "it");
            l6 l6Var = l6.this;
            byte[] bArr = uu.a;
            l6Var.i = true;
            return Unit.INSTANCE;
        }
    }

    public static final class d {
        private d() {
        }

        public /* synthetic */ d(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final class e {

        @NotNull
        public final long[] a;

        @NotNull
        public final List<File> b;

        @NotNull
        public final List<File> c;
        public boolean d;
        public boolean e;

        @Nullable
        public a f;
        public int g;
        public long h;

        @NotNull
        public final String i;
        public final /* synthetic */ l6 j;

        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.ArrayList, java.util.List<java.io.File>] */
        /* JADX WARN: Type inference failed for: r3v2, types: [java.util.ArrayList, java.util.List<java.io.File>] */
        public e(@NotNull l6 l6Var, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.j = l6Var;
            this.i = key;
            this.a = new long[l6Var.u];
            this.b = new ArrayList();
            this.c = new ArrayList();
            StringBuilder sb = new StringBuilder(key);
            sb.append('.');
            int length = sb.length();
            int i = l6Var.u;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(i2);
                this.b.add(new File(l6Var.s, sb.toString()));
                sb.append(".tmp");
                this.c.add(new File(l6Var.s, sb.toString()));
                sb.setLength(length);
            }
        }

        public final Void a(List<String> list) throws IOException {
            throw new IOException("unexpected journal line: " + list);
        }

        /* JADX WARN: Type inference failed for: r5v1, types: [java.util.ArrayList, java.util.List<java.io.File>] */
        @Nullable
        public final f b() {
            l6 l6Var = this.j;
            byte[] bArr = uu.a;
            if (!this.d) {
                return null;
            }
            if (!l6Var.j && (this.f != null || this.e)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            long[] jArr = (long[]) this.a.clone();
            try {
                int i = this.j.u;
                for (int i2 = 0; i2 < i; i2++) {
                    vr vrVarA = this.j.r.a((File) this.b.get(i2));
                    if (!this.j.j) {
                        this.g++;
                        vrVarA = new m6(this, vrVarA, vrVarA);
                    }
                    arrayList.add(vrVarA);
                }
                return new f(this.j, this.i, this.h, arrayList, jArr);
            } catch (FileNotFoundException unused) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    uu.c((vr) it.next());
                }
                try {
                    this.j.L(this);
                } catch (IOException unused2) {
                }
                return null;
            }
        }

        public final void c(@NotNull k2 writer) throws IOException {
            Intrinsics.checkNotNullParameter(writer, "writer");
            for (long j : this.a) {
                writer.i(32).y(j);
            }
        }
    }

    public final class f implements Closeable {
        public final String a;
        public final long b;
        public final List<vr> c;
        public final /* synthetic */ l6 d;

        /* JADX WARN: Multi-variable type inference failed */
        public f(@NotNull l6 l6Var, String key, @NotNull long j, @NotNull List<? extends vr> sources, long[] lengths) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(lengths, "lengths");
            this.d = l6Var;
            this.a = key;
            this.b = j;
            this.c = sources;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            Iterator<vr> it = this.c.iterator();
            while (it.hasNext()) {
                uu.c(it.next());
            }
        }
    }

    static {
        new d(null);
        v = "journal";
        w = "journal.tmp";
        x = "journal.bkp";
        y = "libcore.io.DiskLruCache";
        z = "1";
        A = -1L;
        B = new Regex("[a-z0-9_-]{1,120}");
        C = "CLEAN";
        D = "DIRTY";
        E = "REMOVE";
        F = "READ";
    }

    public l6(@NotNull yb fileSystem, @NotNull File directory, int i, int i2, long j, @NotNull ws taskRunner) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.r = fileSystem;
        this.s = directory;
        this.t = i;
        this.u = i2;
        this.a = j;
        this.g = new LinkedHashMap<>(0, 0.75f, true);
        this.p = taskRunner.f();
        this.q = new b(hi.f(new StringBuilder(), uu.g, " Cache"));
        if (!(j > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (!(i2 > 0)) {
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        this.b = new File(directory, v);
        this.c = new File(directory, w);
        this.d = new File(directory, x);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    public final synchronized void B(@NotNull a editor, boolean z2) throws IOException {
        Intrinsics.checkNotNullParameter(editor, "editor");
        e eVar = editor.c;
        if (!Intrinsics.areEqual(eVar.f, editor)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (z2 && !eVar.d) {
            int i = this.u;
            for (int i2 = 0; i2 < i; i2++) {
                boolean[] zArr = editor.a;
                Intrinsics.checkNotNull(zArr);
                if (!zArr[i2]) {
                    editor.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                }
                if (!this.r.d((File) eVar.c.get(i2))) {
                    editor.a();
                    return;
                }
            }
        }
        int i3 = this.u;
        for (int i4 = 0; i4 < i3; i4++) {
            File file = (File) eVar.c.get(i4);
            if (!z2 || eVar.e) {
                this.r.f(file);
            } else if (this.r.d(file)) {
                File file2 = (File) eVar.b.get(i4);
                this.r.e(file, file2);
                long j = eVar.a[i4];
                long jH = this.r.h(file2);
                eVar.a[i4] = jH;
                this.e = (this.e - j) + jH;
            }
        }
        eVar.f = null;
        if (eVar.e) {
            L(eVar);
            return;
        }
        this.h++;
        k2 k2Var = this.f;
        Intrinsics.checkNotNull(k2Var);
        if (eVar.d || z2) {
            eVar.d = true;
            k2Var.l(C).i(32);
            k2Var.l(eVar.i);
            eVar.c(k2Var);
            k2Var.i(10);
            if (z2) {
                long j2 = this.o;
                this.o = 1 + j2;
                eVar.h = j2;
            }
        } else {
            this.g.remove(eVar.i);
            k2Var.l(E).i(32);
            k2Var.l(eVar.i);
            k2Var.i(10);
        }
        k2Var.flush();
        if (this.e > this.a || F()) {
            this.p.c(this.q, 0L);
        }
    }

    @JvmOverloads
    @Nullable
    public final synchronized a C(@NotNull String key, long j) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        E();
        e();
        N(key);
        e eVar = this.g.get(key);
        if (j != A && (eVar == null || eVar.h != j)) {
            return null;
        }
        if ((eVar != null ? eVar.f : null) != null) {
            return null;
        }
        if (eVar != null && eVar.g != 0) {
            return null;
        }
        if (!this.m && !this.n) {
            k2 k2Var = this.f;
            Intrinsics.checkNotNull(k2Var);
            k2Var.l(D).i(32).l(key).i(10);
            k2Var.flush();
            if (this.i) {
                return null;
            }
            if (eVar == null) {
                eVar = new e(this, key);
                this.g.put(key, eVar);
            }
            a aVar = new a(this, eVar);
            eVar.f = aVar;
            return aVar;
        }
        this.p.c(this.q, 0L);
        return null;
    }

    @Nullable
    public final synchronized f D(@NotNull String key) throws IOException {
        Intrinsics.checkNotNullParameter(key, "key");
        E();
        e();
        N(key);
        e eVar = this.g.get(key);
        if (eVar == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(eVar, "lruEntries[key] ?: return null");
        f fVarB = eVar.b();
        if (fVarB == null) {
            return null;
        }
        this.h++;
        k2 k2Var = this.f;
        Intrinsics.checkNotNull(k2Var);
        k2Var.l(F).i(32).l(key).i(10);
        if (F()) {
            this.p.c(this.q, 0L);
        }
        return fVarB;
    }

    public final synchronized void E() throws IOException {
        boolean z2;
        byte[] bArr = uu.a;
        if (this.k) {
            return;
        }
        if (this.r.d(this.d)) {
            if (this.r.d(this.b)) {
                this.r.f(this.d);
            } else {
                this.r.e(this.d, this.b);
            }
        }
        yb isCivilized = this.r;
        File file = this.d;
        Intrinsics.checkNotNullParameter(isCivilized, "$this$isCivilized");
        Intrinsics.checkNotNullParameter(file, "file");
        rp rpVarB = isCivilized.b(file);
        try {
            try {
                isCivilized.f(file);
                CloseableKt.closeFinally(rpVarB, null);
                z2 = true;
            } catch (IOException unused) {
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(rpVarB, null);
                isCivilized.f(file);
                z2 = false;
            }
            this.j = z2;
            if (this.r.d(this.b)) {
                try {
                    I();
                    H();
                    this.k = true;
                    return;
                } catch (IOException e2) {
                    Objects.requireNonNull(zk.c);
                    zk.a.i("DiskLruCache " + this.s + " is corrupt: " + e2.getMessage() + ", removing", 5, e2);
                    try {
                        close();
                        this.r.c(this.s);
                        this.l = false;
                        K();
                        this.k = true;
                    } catch (Throwable th) {
                        this.l = false;
                        throw th;
                    }
                }
            }
            K();
            this.k = true;
        } finally {
        }
    }

    public final boolean F() {
        int i = this.h;
        return i >= 2000 && i >= this.g.size();
    }

    public final k2 G() throws FileNotFoundException {
        return spi.b.b(new wa(this.r.g(this.b), new c()));
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    public final void H() throws IOException {
        this.r.f(this.c);
        Iterator<e> it = this.g.values().iterator();
        while (it.hasNext()) {
            e next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "i.next()");
            e eVar = next;
            int i = 0;
            if (eVar.f == null) {
                int i2 = this.u;
                while (i < i2) {
                    this.e += eVar.a[i];
                    i++;
                }
            } else {
                eVar.f = null;
                int i3 = this.u;
                while (i < i3) {
                    this.r.f((File) eVar.b.get(i));
                    this.r.f((File) eVar.c.get(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    public final void I() throws IOException {
        l2 l2VarC = spi.b.c(this.r.a(this.b));
        try {
            String strR = l2VarC.r();
            String strR2 = l2VarC.r();
            String strR3 = l2VarC.r();
            String strR4 = l2VarC.r();
            String strR5 = l2VarC.r();
            if (!(!Intrinsics.areEqual(y, strR)) && !(!Intrinsics.areEqual(z, strR2)) && !(!Intrinsics.areEqual(String.valueOf(this.t), strR3)) && !(!Intrinsics.areEqual(String.valueOf(this.u), strR4))) {
                int i = 0;
                if (!(strR5.length() > 0)) {
                    while (true) {
                        try {
                            J(l2VarC.r());
                            i++;
                        } catch (EOFException unused) {
                            this.h = i - this.g.size();
                            if (l2VarC.h()) {
                                this.f = G();
                            } else {
                                K();
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(l2VarC, null);
                            return;
                        }
                    }
                }
            }
            throw new IOException("unexpected journal header: [" + strR + ", " + strR2 + ", " + strR4 + ", " + strR5 + ']');
        } finally {
        }
    }

    public final void J(String str) throws IOException {
        String strSubstring;
        int iJ = StringsKt.j(str, ' ', 0, 6);
        if (iJ == -1) {
            throw new IOException(spi.d.f("unexpected journal line: ", str));
        }
        int i = iJ + 1;
        int iJ2 = StringsKt.j(str, ' ', i, 4);
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        if (iJ2 == -1) {
            strSubstring = str.substring(i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            String str2 = E;
            if (iJ == str2.length() && StringsKt.x(str, str2)) {
                this.g.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iJ2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        e eVar = this.g.get(strSubstring);
        if (eVar == null) {
            eVar = new e(this, strSubstring);
            this.g.put(strSubstring, eVar);
        }
        if (iJ2 != -1) {
            String str3 = C;
            if (iJ == str3.length() && StringsKt.x(str, str3)) {
                String strSubstring2 = str.substring(iJ2 + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                List<String> strings = StringsKt__StringsKt.split$default(strSubstring2, new char[]{' '}, false, 0, 6, (Object) null);
                eVar.d = true;
                eVar.f = null;
                Intrinsics.checkNotNullParameter(strings, "strings");
                if (strings.size() != eVar.j.u) {
                    eVar.a(strings);
                    throw null;
                }
                try {
                    int size = strings.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        eVar.a[i2] = Long.parseLong(strings.get(i2));
                    }
                    return;
                } catch (NumberFormatException unused) {
                    eVar.a(strings);
                    throw null;
                }
            }
        }
        if (iJ2 == -1) {
            String str4 = D;
            if (iJ == str4.length() && StringsKt.x(str, str4)) {
                eVar.f = new a(this, eVar);
                return;
            }
        }
        if (iJ2 == -1) {
            String str5 = F;
            if (iJ == str5.length() && StringsKt.x(str, str5)) {
                return;
            }
        }
        throw new IOException(spi.d.f("unexpected journal line: ", str));
    }

    public final synchronized void K() throws IOException {
        k2 k2Var = this.f;
        if (k2Var != null) {
            k2Var.close();
        }
        k2 k2VarB = spi.b.b(this.r.b(this.c));
        try {
            k2VarB.l(y).i(10);
            k2VarB.l(z).i(10);
            k2VarB.y(this.t);
            k2VarB.i(10);
            k2VarB.y(this.u);
            k2VarB.i(10);
            k2VarB.i(10);
            for (e eVar : this.g.values()) {
                if (eVar.f != null) {
                    k2VarB.l(D).i(32);
                    k2VarB.l(eVar.i);
                } else {
                    k2VarB.l(C).i(32);
                    k2VarB.l(eVar.i);
                    eVar.c(k2VarB);
                }
                k2VarB.i(10);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(k2VarB, null);
            if (this.r.d(this.b)) {
                this.r.e(this.b, this.d);
            }
            this.r.e(this.c, this.b);
            this.r.f(this.d);
            this.f = G();
            this.i = false;
            this.n = false;
        } finally {
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.ArrayList, java.util.List<java.io.File>] */
    public final void L(@NotNull e entry) throws IOException {
        k2 k2Var;
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.j) {
            if (entry.g > 0 && (k2Var = this.f) != null) {
                k2Var.l(D);
                k2Var.i(32);
                k2Var.l(entry.i);
                k2Var.i(10);
                k2Var.flush();
            }
            if (entry.g > 0 || entry.f != null) {
                entry.e = true;
                return;
            }
        }
        a aVar = entry.f;
        if (aVar != null) {
            aVar.c();
        }
        int i = this.u;
        for (int i2 = 0; i2 < i; i2++) {
            this.r.f((File) entry.b.get(i2));
            long j = this.e;
            long[] jArr = entry.a;
            this.e = j - jArr[i2];
            jArr[i2] = 0;
        }
        this.h++;
        k2 k2Var2 = this.f;
        if (k2Var2 != null) {
            k2Var2.l(E);
            k2Var2.i(32);
            k2Var2.l(entry.i);
            k2Var2.i(10);
        }
        this.g.remove(entry.i);
        if (F()) {
            this.p.c(this.q, 0L);
        }
    }

    public final void M() throws IOException {
        boolean z2;
        do {
            z2 = false;
            if (this.e <= this.a) {
                this.m = false;
                return;
            }
            Iterator<e> it = this.g.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                e toEvict = it.next();
                if (!toEvict.e) {
                    Intrinsics.checkNotNullExpressionValue(toEvict, "toEvict");
                    L(toEvict);
                    z2 = true;
                    break;
                }
            }
        } while (z2);
    }

    public final void N(String str) {
        if (B.matches(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + Typography.quote).toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.k && !this.l) {
            Collection<e> collectionValues = this.g.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "lruEntries.values");
            Object[] array = collectionValues.toArray(new e[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            for (e eVar : (e[]) array) {
                a aVar = eVar.f;
                if (aVar != null && aVar != null) {
                    aVar.c();
                }
            }
            M();
            k2 k2Var = this.f;
            Intrinsics.checkNotNull(k2Var);
            k2Var.close();
            this.f = null;
            this.l = true;
            return;
        }
        this.l = true;
    }

    public final synchronized void e() {
        if (!(!this.l)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() throws IOException {
        if (this.k) {
            e();
            M();
            k2 k2Var = this.f;
            Intrinsics.checkNotNull(k2Var);
            k2Var.flush();
        }
    }
}
