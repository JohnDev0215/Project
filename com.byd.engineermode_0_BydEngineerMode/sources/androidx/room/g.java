package androidx.room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: InvalidationTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f1325a = {"UPDATE", "DELETE", "INSERT"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String[] f1327c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map<String, Set<String>> f1328d;
    final RoomDatabase e;
    volatile b.m.a.f h;
    private b i;
    private final f j;
    private h l;
    AtomicBoolean f = new AtomicBoolean(false);
    private volatile boolean g = false;

    @SuppressLint({"RestrictedApi"})
    final b.b.a.b.b<c, d> k = new b.b.a.b.b<>();
    Runnable m = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final HashMap<String, Integer> f1326b = new HashMap<>();

    /* JADX INFO: compiled from: InvalidationTracker.java */
    class a implements Runnable {
        a() {
        }

        private Set<Integer> a() {
            HashSet hashSet = new HashSet();
            Cursor cursorP = g.this.e.p(new b.m.a.a("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
            while (cursorP.moveToNext()) {
                try {
                    hashSet.add(Integer.valueOf(cursorP.getInt(0)));
                } catch (Throwable th) {
                    cursorP.close();
                    throw th;
                }
            }
            cursorP.close();
            if (!hashSet.isEmpty()) {
                g.this.h.i();
            }
            return hashSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            Lock lockH = g.this.e.h();
            Set<Integer> setA = null;
            try {
                try {
                    lockH.lock();
                } finally {
                    lockH.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            }
            if (g.this.c()) {
                if (g.this.f.compareAndSet(true, false)) {
                    if (g.this.e.k()) {
                        return;
                    }
                    RoomDatabase roomDatabase = g.this.e;
                    if (roomDatabase.g) {
                        b.m.a.b bVarB = roomDatabase.i().b();
                        bVarB.b();
                        try {
                            setA = a();
                            bVarB.h();
                            bVarB.a();
                        } catch (Throwable th) {
                            bVarB.a();
                            throw th;
                        }
                    } else {
                        setA = a();
                    }
                    if (setA == null || setA.isEmpty()) {
                        return;
                    }
                    synchronized (g.this.k) {
                        Iterator<Map.Entry<c, d>> it = g.this.k.iterator();
                        while (it.hasNext()) {
                            it.next().getValue().a(setA);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final long[] f1330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean[] f1331b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int[] f1332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1333d;
        boolean e;

        b(int i) {
            long[] jArr = new long[i];
            this.f1330a = jArr;
            boolean[] zArr = new boolean[i];
            this.f1331b = zArr;
            this.f1332c = new int[i];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        int[] a() {
            synchronized (this) {
                if (this.f1333d && !this.e) {
                    int length = this.f1330a.length;
                    int i = 0;
                    while (true) {
                        int i2 = 1;
                        if (i >= length) {
                            this.e = true;
                            this.f1333d = false;
                            return this.f1332c;
                        }
                        boolean z = this.f1330a[i] > 0;
                        boolean[] zArr = this.f1331b;
                        if (z != zArr[i]) {
                            int[] iArr = this.f1332c;
                            if (!z) {
                                i2 = 2;
                            }
                            iArr[i] = i2;
                        } else {
                            this.f1332c[i] = 0;
                        }
                        zArr[i] = z;
                        i++;
                    }
                }
                return null;
            }
        }

        boolean b(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.f1330a;
                    long j = jArr[i];
                    jArr[i] = 1 + j;
                    if (j == 0) {
                        this.f1333d = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        boolean c(int... iArr) {
            boolean z;
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.f1330a;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        this.f1333d = true;
                        z = true;
                    }
                }
            }
            return z;
        }

        void d() {
            synchronized (this) {
                this.e = false;
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String[] f1334a;

        public c(String[] strArr) {
            this.f1334a = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        boolean a() {
            return false;
        }

        public abstract void b(Set<String> set);
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f1335a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String[] f1336b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c f1337c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Set<String> f1338d;

        d(c cVar, int[] iArr, String[] strArr) {
            this.f1337c = cVar;
            this.f1335a = iArr;
            this.f1336b = strArr;
            if (iArr.length != 1) {
                this.f1338d = null;
                return;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(strArr[0]);
            this.f1338d = Collections.unmodifiableSet(hashSet);
        }

        void a(Set<Integer> set) {
            int length = this.f1335a.length;
            Set<String> hashSet = null;
            for (int i = 0; i < length; i++) {
                if (set.contains(Integer.valueOf(this.f1335a[i]))) {
                    if (length == 1) {
                        hashSet = this.f1338d;
                    } else {
                        if (hashSet == null) {
                            hashSet = new HashSet<>(length);
                        }
                        hashSet.add(this.f1336b[i]);
                    }
                }
            }
            if (hashSet != null) {
                this.f1337c.b(hashSet);
            }
        }

        void b(String[] strArr) {
            Set<String> set = null;
            if (this.f1336b.length == 1) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (strArr[i].equalsIgnoreCase(this.f1336b[0])) {
                        set = this.f1338d;
                        break;
                    }
                    i++;
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.f1336b;
                    int length2 = strArr2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 < length2) {
                            String str2 = strArr2[i2];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i2++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.f1337c.b(set);
            }
        }
    }

    public g(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.e = roomDatabase;
        this.i = new b(strArr.length);
        this.f1328d = map2;
        this.j = new f(roomDatabase);
        int length = strArr.length;
        this.f1327c = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.f1326b.put(lowerCase, Integer.valueOf(i));
            String str2 = map.get(strArr[i]);
            if (str2 != null) {
                this.f1327c[i] = str2.toLowerCase(locale);
            } else {
                this.f1327c[i] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            Locale locale2 = Locale.US;
            String lowerCase2 = value.toLowerCase(locale2);
            if (this.f1326b.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap<String, Integer> map3 = this.f1326b;
                map3.put(lowerCase3, map3.get(lowerCase2));
            }
        }
    }

    private static void b(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("`");
    }

    private String[] h(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.f1328d.containsKey(lowerCase)) {
                hashSet.addAll(this.f1328d.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private void j(b.m.a.b bVar, int i) {
        bVar.e("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.f1327c[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f1325a) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            b(sb, str, str2);
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append("room_table_modification_log");
            sb.append(" SET ");
            sb.append("invalidated");
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append("table_id");
            sb.append(" = ");
            sb.append(i);
            sb.append(" AND ");
            sb.append("invalidated");
            sb.append(" = 0");
            sb.append("; END");
            bVar.e(sb.toString());
        }
    }

    private void k(b.m.a.b bVar, int i) {
        String str = this.f1327c[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f1325a) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            b(sb, str, str2);
            bVar.e(sb.toString());
        }
    }

    @SuppressLint({"RestrictedApi"})
    public void a(c cVar) {
        d dVarG;
        String[] strArrH = h(cVar.f1334a);
        int[] iArr = new int[strArrH.length];
        int length = strArrH.length;
        for (int i = 0; i < length; i++) {
            Integer num = this.f1326b.get(strArrH[i].toLowerCase(Locale.US));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + strArrH[i]);
            }
            iArr[i] = num.intValue();
        }
        d dVar = new d(cVar, iArr, strArrH);
        synchronized (this.k) {
            dVarG = this.k.g(cVar, dVar);
        }
        if (dVarG == null && this.i.b(iArr)) {
            l();
        }
    }

    boolean c() {
        if (!this.e.o()) {
            return false;
        }
        if (!this.g) {
            this.e.i().b();
        }
        if (this.g) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    void d(b.m.a.b bVar) {
        synchronized (this) {
            if (this.g) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.e("PRAGMA temp_store = MEMORY;");
            bVar.e("PRAGMA recursive_triggers='ON';");
            bVar.e("CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            m(bVar);
            this.h = bVar.j("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
            this.g = true;
        }
    }

    public void e(String... strArr) {
        synchronized (this.k) {
            for (Map.Entry<c, d> entry : this.k) {
                if (!entry.getKey().a()) {
                    entry.getValue().b(strArr);
                }
            }
        }
    }

    public void f() {
        if (this.f.compareAndSet(false, true)) {
            this.e.j().execute(this.m);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public void g(c cVar) {
        d dVarH;
        synchronized (this.k) {
            dVarH = this.k.h(cVar);
        }
        if (dVarH == null || !this.i.c(dVarH.f1335a)) {
            return;
        }
        l();
    }

    void i(Context context, String str) {
        this.l = new h(context, str, this, this.e.j());
    }

    void l() {
        if (this.e.o()) {
            m(this.e.i().b());
        }
    }

    void m(b.m.a.b bVar) {
        if (bVar.t()) {
            return;
        }
        while (true) {
            try {
                Lock lockH = this.e.h();
                lockH.lock();
                try {
                    int[] iArrA = this.i.a();
                    if (iArrA == null) {
                        return;
                    }
                    int length = iArrA.length;
                    bVar.b();
                    for (int i = 0; i < length; i++) {
                        try {
                            int i2 = iArrA[i];
                            if (i2 == 1) {
                                j(bVar, i);
                            } else if (i2 == 2) {
                                k(bVar, i);
                            }
                        } finally {
                        }
                    }
                    bVar.h();
                    bVar.a();
                    this.i.d();
                } finally {
                    lockH.unlock();
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                return;
            }
        }
    }
}
