package y0;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: InvalidationTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String[] f9897m = {"UPDATE", "DELETE", "INSERT"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String[] f9899b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long[] f9900c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final f f9903f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    volatile b1.f f9906i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private b f9907j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Object[] f9901d = new Object[1];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f9902e = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    AtomicBoolean f9904g = new AtomicBoolean(false);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private volatile boolean f9905h = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final l.b<Object, c> f9908k = new l.b<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    Runnable f9909l = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    n.a<String, Integer> f9898a = new n.a<>();

    /* JADX INFO: compiled from: InvalidationTracker.java */
    class a implements Runnable {
        a() {
        }

        private boolean a() {
            d dVar = d.this;
            Cursor cursorP = dVar.f9903f.p("SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;", dVar.f9901d);
            boolean z6 = false;
            while (cursorP.moveToNext()) {
                try {
                    long j7 = cursorP.getLong(0);
                    int i7 = cursorP.getInt(1);
                    d dVar2 = d.this;
                    dVar2.f9900c[i7] = j7;
                    dVar2.f9902e = j7;
                    z6 = true;
                } finally {
                    cursorP.close();
                }
            }
            return z6;
        }

        @Override // java.lang.Runnable
        public void run() {
            Lock lockG = d.this.f9903f.g();
            boolean zA = false;
            try {
                try {
                    lockG.lock();
                } catch (SQLiteException | IllegalStateException e7) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e7);
                }
                if (d.this.b()) {
                    if (d.this.f9904g.compareAndSet(true, false)) {
                        if (d.this.f9903f.j()) {
                            return;
                        }
                        d.this.f9906i.v();
                        d dVar = d.this;
                        dVar.f9901d[0] = Long.valueOf(dVar.f9902e);
                        f fVar = d.this.f9903f;
                        if (fVar.f9925f) {
                            b1.b bVarB = fVar.h().b();
                            try {
                                bVarB.g();
                                zA = a();
                                bVarB.u();
                                bVarB.f();
                            } catch (Throwable th) {
                                bVarB.f();
                                throw th;
                            }
                        } else {
                            zA = a();
                        }
                        if (zA) {
                            synchronized (d.this.f9908k) {
                                Iterator<Map.Entry<Object, c>> it = d.this.f9908k.iterator();
                                while (it.hasNext()) {
                                    it.next().getValue().a(d.this.f9900c);
                                }
                            }
                        }
                    }
                }
            } finally {
                lockG.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final long[] f9911a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean[] f9912b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int[] f9913c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f9914d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f9915e;

        b(int i7) {
            long[] jArr = new long[i7];
            this.f9911a = jArr;
            boolean[] zArr = new boolean[i7];
            this.f9912b = zArr;
            this.f9913c = new int[i7];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        int[] a() {
            synchronized (this) {
                if (this.f9914d && !this.f9915e) {
                    int length = this.f9911a.length;
                    int i7 = 0;
                    while (true) {
                        int i8 = 1;
                        if (i7 >= length) {
                            this.f9915e = true;
                            this.f9914d = false;
                            return this.f9913c;
                        }
                        boolean z6 = this.f9911a[i7] > 0;
                        boolean[] zArr = this.f9912b;
                        if (z6 != zArr[i7]) {
                            int[] iArr = this.f9913c;
                            if (!z6) {
                                i8 = 2;
                            }
                            iArr[i7] = i8;
                        } else {
                            this.f9913c[i7] = 0;
                        }
                        zArr[i7] = z6;
                        i7++;
                    }
                }
                return null;
            }
        }

        void b() {
            synchronized (this) {
                this.f9915e = false;
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f9916a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String[] f9917b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long[] f9918c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Set<String> f9919d;

        void a(long[] jArr) {
            int length = this.f9916a.length;
            Set bVar = null;
            for (int i7 = 0; i7 < length; i7++) {
                long j7 = jArr[this.f9916a[i7]];
                long[] jArr2 = this.f9918c;
                if (jArr2[i7] < j7) {
                    jArr2[i7] = j7;
                    if (length == 1) {
                        bVar = this.f9919d;
                    } else {
                        if (bVar == null) {
                            bVar = new n.b(length);
                        }
                        bVar.add(this.f9917b[i7]);
                    }
                }
            }
            if (bVar != null) {
                throw null;
            }
        }
    }

    public d(f fVar, String... strArr) {
        this.f9903f = fVar;
        this.f9907j = new b(strArr.length);
        int length = strArr.length;
        this.f9899b = new String[length];
        for (int i7 = 0; i7 < length; i7++) {
            String lowerCase = strArr[i7].toLowerCase(Locale.US);
            this.f9898a.put(lowerCase, Integer.valueOf(i7));
            this.f9899b[i7] = lowerCase;
        }
        long[] jArr = new long[strArr.length];
        this.f9900c = jArr;
        Arrays.fill(jArr, 0L);
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    private void e(b1.b bVar, int i7) {
        String str = this.f9899b[i7];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f9897m) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            a(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN INSERT OR REPLACE INTO ");
            sb.append("room_table_modification_log");
            sb.append(" VALUES(null, ");
            sb.append(i7);
            sb.append("); END");
            bVar.o(sb.toString());
        }
    }

    private void f(b1.b bVar, int i7) {
        String str = this.f9899b[i7];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f9897m) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            a(sb, str, str2);
            bVar.o(sb.toString());
        }
    }

    boolean b() {
        if (!this.f9903f.n()) {
            return false;
        }
        if (!this.f9905h) {
            this.f9903f.h().b();
        }
        if (this.f9905h) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    void c(b1.b bVar) {
        synchronized (this) {
            if (this.f9905h) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.g();
            try {
                bVar.o("PRAGMA temp_store = MEMORY;");
                bVar.o("PRAGMA recursive_triggers='ON';");
                bVar.o("CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)");
                bVar.u();
                bVar.f();
                g(bVar);
                this.f9906i = bVar.w("DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)");
                this.f9905h = true;
            } catch (Throwable th) {
                bVar.f();
                throw th;
            }
        }
    }

    public void d() {
        if (this.f9904g.compareAndSet(false, true)) {
            this.f9903f.i().execute(this.f9909l);
        }
    }

    void g(b1.b bVar) {
        if (bVar.W()) {
            return;
        }
        while (true) {
            try {
                Lock lockG = this.f9903f.g();
                lockG.lock();
                try {
                    int[] iArrA = this.f9907j.a();
                    if (iArrA == null) {
                        return;
                    }
                    int length = iArrA.length;
                    try {
                        bVar.g();
                        for (int i7 = 0; i7 < length; i7++) {
                            int i8 = iArrA[i7];
                            if (i8 == 1) {
                                e(bVar, i7);
                            } else if (i8 == 2) {
                                f(bVar, i7);
                            }
                        }
                        bVar.u();
                        bVar.f();
                        this.f9907j.b();
                    } finally {
                    }
                } finally {
                    lockG.unlock();
                }
            } catch (SQLiteException | IllegalStateException e7) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e7);
                return;
            }
        }
    }
}
