package defpackage;

import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import defpackage.ll;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public class mm0 implements q02 {
    public static final a g = new a();
    public static final sh h = new sh("CriticalPersistedTabData", "delay_saves_until_deferred_startup", false);
    public boolean b;
    public boolean c;
    public f f;
    public final LinkedList<g> d = new LinkedList<>();
    public final LinkedList<f> e = new LinkedList<>();
    public final oi2 a = PostTask.a(d73.k);

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public class a implements Callback<Integer> {
        @Override // org.chromium.base.Callback
        public final /* bridge */ /* synthetic */ void onResult(Integer num) {
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public static class b {
        public static final File a;

        static {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            try {
                a = xz.a.getDir("persisted_tab_data_storage", 0);
            } finally {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            }
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public class c extends g<Void> {
        public final Callback<Integer> d;
        public final /* synthetic */ mm0 e;

        /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
        public class a extends i9<Void> {
            public a() {
            }

            @Override // defpackage.i9
            public final Void b() {
                c.this.d();
                return null;
            }

            @Override // defpackage.i9
            public final void l(Void r3) {
                PostTask.c(rh3.a, new eu1(21, this));
                c.this.e.l();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(mm0 mm0Var, int i, String str) {
            super(i, str);
            a aVar = mm0.g;
            this.e = mm0Var;
            this.d = aVar;
        }

        @Override // mm0.g
        public final /* bridge */ /* synthetic */ Void a() {
            d();
            return null;
        }

        @Override // mm0.g
        public final i9 b() {
            return new a();
        }

        @Override // mm0.g
        public final int c() {
            return 2;
        }

        public final void d() {
            File file = this.c;
            boolean zExists = file.exists();
            this.e.getClass();
            oj2.O("Tabs.PersistedTabData.Storage.Exists.File", zExists);
            if (zExists) {
                boolean zDelete = file.delete();
                oj2.O("Tabs.PersistedTabData.Storage.Delete.File", zDelete);
                if (zDelete) {
                    return;
                }
                Log.e("cr_FilePTDS", String.format(Locale.ENGLISH, "Error deleting file %s", file));
            }
        }

        @Override // mm0.g
        public final boolean equals(Object obj) {
            if (obj instanceof c) {
                return super.equals(obj);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public class d<U extends p02> extends g<U> {
        public final Callback<U> d;
        public final o02<U> e;
        public final /* synthetic */ mm0 f;

        /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
        public class a extends i9<U> {
            public a() {
            }

            @Override // defpackage.i9
            public final Object b() {
                return d.this.a();
            }

            @Override // defpackage.i9
            public final void l(Object obj) {
                PostTask.c(rh3.a, new z8(this, 23, (p02) obj));
                d.this.f.l();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(mm0 mm0Var, int i, String str, ll.b bVar) {
            super(i, str);
            CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
            this.f = mm0Var;
            this.d = bVar;
            this.e = aVar;
        }

        @Override // mm0.g
        public final i9 b() {
            return new a();
        }

        @Override // mm0.g
        public final int c() {
            return 1;
        }

        @Override // mm0.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public ui2 a() throws Throwable {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            ByteBuffer byteBufferA = this.f.new e(this.a, this.b, null).a();
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            ui2 ui2VarA = ((CriticalPersistedTabData.a) this.e).a(byteBufferA);
            long jElapsedRealtime3 = SystemClock.elapsedRealtime();
            if (byteBufferA != null && byteBufferA.limit() > 0) {
                oj2.b0(jElapsedRealtime3 - jElapsedRealtime, "Tabs.PersistedTabData.Storage.LoadAndMapTime.File");
                oj2.b0(jElapsedRealtime3 - jElapsedRealtime2, "Tabs.PersistedTabData.Storage.MapTime.File");
            }
            return ui2VarA;
        }

        @Override // mm0.g
        public final boolean equals(Object obj) {
            if (obj instanceof d) {
                return super.equals(obj);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public class e extends g<ByteBuffer> {
        public final Callback<ByteBuffer> d;

        /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
        public class a extends i9<ByteBuffer> {
            public a() {
            }

            @Override // defpackage.i9
            public final ByteBuffer b() {
                return e.this.a();
            }

            @Override // defpackage.i9
            public final void l(ByteBuffer byteBuffer) {
                PostTask.d(rh3.a, new a9(this, 21, byteBuffer));
                mm0.this.l();
            }
        }

        public e(int i, String str, j02 j02Var) {
            super(i, str);
            this.d = j02Var;
        }

        @Override // mm0.g
        public final i9 b() {
            return new a();
        }

        @Override // mm0.g
        public final int c() {
            return 1;
        }

        /* JADX WARN: Not initialized variable reg: 8, insn: 0x009a: MOVE (r5 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]) (LINE:155), block:B:29:0x009a */
        @Override // mm0.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public ByteBuffer a() throws Throwable {
            boolean z;
            FileInputStream fileInputStreamC;
            MappedByteBuffer map;
            Closeable closeable;
            mm0 mm0Var = mm0.this;
            File file = this.c;
            boolean z2 = true;
            Closeable closeable2 = null;
            try {
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    fileInputStreamC = new j9(file).c();
                    try {
                        FileChannel channel = fileInputStreamC.getChannel();
                        map = channel.map(FileChannel.MapMode.READ_ONLY, channel.position(), channel.size());
                    } catch (FileNotFoundException e) {
                        e = e;
                        z = false;
                        map = null;
                    } catch (IOException e2) {
                        e = e2;
                        z = false;
                        map = null;
                    }
                    try {
                        Locale locale = Locale.US;
                        mm0Var.getClass();
                        oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, String.format(locale, "Tabs.PersistedTabData.Storage.LoadTime.%s", "File"));
                        a8.s(fileInputStreamC);
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        z = true;
                        Log.e("cr_FilePTDS", String.format(Locale.ENGLISH, "FileNotFoundException while attempting to restore  %s. Details: %s", file, e.getMessage()));
                        a8.s(fileInputStreamC);
                        z2 = z;
                    } catch (IOException e4) {
                        e = e4;
                        z = true;
                        Log.e("cr_FilePTDS", String.format(Locale.ENGLISH, "IOException while attempting to restore %s. Details: %s", file, e.getMessage()));
                        a8.s(fileInputStreamC);
                        z2 = z;
                    }
                } catch (Throwable th) {
                    th = th;
                    closeable2 = closeable;
                    a8.s(closeable2);
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                z = false;
                fileInputStreamC = null;
                map = null;
            } catch (IOException e6) {
                e = e6;
                z = false;
                fileInputStreamC = null;
                map = null;
            } catch (Throwable th2) {
                th = th2;
                a8.s(closeable2);
                throw th;
            }
            mm0Var.getClass();
            oj2.O("Tabs.PersistedTabData.Storage.Restore.File", z2);
            if (z2) {
                return map;
            }
            return null;
        }

        @Override // mm0.g
        public final boolean equals(Object obj) {
            if (obj instanceof e) {
                return super.equals(obj);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public class f extends g<Void> {
        public vi2<ByteBuffer> d;
        public final Callback<Integer> e;
        public boolean f;

        /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
        public class a extends i9<Void> {
            public a() {
            }

            @Override // defpackage.i9
            public final Void b() throws Throwable {
                f.this.d();
                return null;
            }

            @Override // defpackage.i9
            public final void l(Void r4) {
                f fVar = f.this;
                mm0.this.f = null;
                PostTask.c(rh3.a, new zx1(16, this));
                mm0.this.l();
            }

            @Override // defpackage.i9
            public final void m() {
                f.this.d.m();
            }
        }

        public f(int i, String str, k02 k02Var, Callback callback) {
            super(i, str);
            this.d = k02Var;
            this.e = callback;
        }

        @Override // mm0.g
        public /* bridge */ /* synthetic */ Void a() throws Throwable {
            d();
            return null;
        }

        @Override // mm0.g
        public final i9 b() {
            return new a();
        }

        @Override // mm0.g
        public final int c() {
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00bf A[PHI: r3 r8 r10
  0x00bf: PHI (r3v9 java.io.FileOutputStream) = (r3v6 java.io.FileOutputStream), (r3v10 java.io.FileOutputStream) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]
  0x00bf: PHI (r8v8 boolean) = (r8v5 boolean), (r8v9 boolean) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]
  0x00bf: PHI (r10v9 j9) = (r10v6 j9), (r10v10 j9) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00c3 A[PHI: r3 r8 r10
  0x00c3: PHI (r3v8 java.io.FileOutputStream) = (r3v6 java.io.FileOutputStream), (r3v10 java.io.FileOutputStream) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]
  0x00c3: PHI (r8v7 boolean) = (r8v5 boolean), (r8v9 boolean) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]
  0x00c3: PHI (r10v8 j9) = (r10v6 j9), (r10v10 j9) binds: [B:35:0x009d, B:43:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void d() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 226
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: mm0.f.d():void");
        }

        @Override // mm0.g
        public final boolean equals(Object obj) {
            if (obj instanceof f) {
                return super.equals(obj);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FilePersistedTabDataStorage.java */
    public abstract class g<T> {
        public final int a;
        public final String b;
        public final File c;

        public g(int i, String str) {
            this.a = i;
            this.b = str;
            this.c = mm0.i(i, str);
        }

        public abstract T a();

        public abstract i9 b();

        public abstract int c();

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof g)) {
                return false;
            }
            g gVar = (g) obj;
            return this.a == gVar.a && this.b.equals(gVar.b) && this.c.equals(gVar.c);
        }

        public final int hashCode() {
            return this.c.hashCode() + ((this.b.hashCode() + ((527 + this.a) * 31)) * 31);
        }
    }

    public static File i(int i, String str) {
        return new File(b.a, String.format(Locale.ENGLISH, "%d%s", Integer.valueOf(i), str));
    }

    public static Boolean j(int i) {
        ku2 ku2VarT = ku2.t();
        try {
            if (i(i, m02.a(CriticalPersistedTabData.class, false).e).exists()) {
                Boolean bool = Boolean.FALSE;
                ku2VarT.close();
                return bool;
            }
            if (!i(i, m02.a(CriticalPersistedTabData.class, true).e).exists()) {
                ku2VarT.close();
                return null;
            }
            Boolean bool2 = Boolean.TRUE;
            ku2VarT.close();
            return bool2;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.q02
    public void a(int i, String str, j02 j02Var) {
        h(new e(i, str, j02Var));
    }

    @Override // defpackage.q02
    public final void b(int i, String str) {
        h(new c(this, i, str));
    }

    @Override // defpackage.q02
    public p02 c(int i, String str) throws Throwable {
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        i(i, str);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ByteBuffer byteBufferA = new e(i, str, null).a();
        long jElapsedRealtime2 = SystemClock.elapsedRealtime();
        ui2 ui2VarA = aVar.a(byteBufferA);
        long jElapsedRealtime3 = SystemClock.elapsedRealtime();
        if (byteBufferA != null && byteBufferA.limit() > 0) {
            oj2.b0(jElapsedRealtime3 - jElapsedRealtime, "Tabs.PersistedTabData.Storage.LoadAndMapTime.File");
            oj2.b0(jElapsedRealtime3 - jElapsedRealtime2, "Tabs.PersistedTabData.Storage.MapTime.File");
        }
        return ui2VarA;
    }

    @Override // defpackage.q02
    public void d(int i, String str, ll.b bVar) {
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        h(new d(this, i, str, bVar));
    }

    @Override // defpackage.q02
    public void f(int i, String str, k02 k02Var) {
        g(i, str, k02Var, g);
    }

    @Override // defpackage.q02
    public void g(int i, String str, k02 k02Var, Callback callback) {
        if ((bq.v.a() && h.c()) && !this.c) {
            f fVar = new f(i, str, k02Var, callback);
            LinkedList<f> linkedList = this.e;
            linkedList.remove(fVar);
            linkedList.add(fVar);
            return;
        }
        f fVar2 = new f(i, str, k02Var, callback);
        LinkedList<g> linkedList2 = this.d;
        linkedList2.remove(fVar2);
        linkedList2.add(fVar2);
        l();
    }

    public final void h(g gVar) {
        this.d.add(gVar);
        l();
    }

    public final void k() {
        ku2 ku2VarC = ku2.C();
        try {
            f fVar = this.f;
            LinkedList<g> linkedList = this.d;
            if (fVar != null) {
                fVar.getClass();
                fVar.new a().a(false);
                f fVar2 = this.f;
                if (!fVar2.f) {
                    linkedList.addFirst(fVar2);
                }
            }
            for (g gVar : linkedList) {
                if (gVar instanceof f) {
                    ((f) gVar).d.m();
                    gVar.a();
                }
            }
            linkedList.clear();
            ku2VarC.close();
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void l() {
        LinkedList<g> linkedList = this.d;
        if (linkedList.isEmpty()) {
            return;
        }
        g gVarPoll = linkedList.poll();
        if (!this.b) {
            oj2.U(gVarPoll.c(), 3, "Tabs.PersistedTabData.Storage.Save.File.FirstStorageRequestType");
            this.b = true;
        }
        if (gVarPoll instanceof f) {
            this.f = (f) gVarPoll;
        }
        gVarPoll.b().d(this.a);
    }

    @Override // defpackage.q02
    public final void e(String str, ArrayList arrayList) {
    }
}
