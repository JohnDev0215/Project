package d1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.room.RoomDatabase;
import com.byd.systemsettings.system.BydPowerManagerDevice;
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
    public static final String[] f4353a = {"UPDATE", "DELETE", "INSERT"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f4355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<String, Set<String>> f4356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RoomDatabase f4357e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile h1.f f4360h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f4361i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final f f4362j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public h f4364l;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AtomicBoolean f4358f = new AtomicBoolean(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f4359g = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    @SuppressLint({"RestrictedApi"})
    public final t.b<c, d> f4363k = new t.b<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Runnable f4365m = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap<String, Integer> f4354b = new HashMap<>();

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public class a implements Runnable {
        public a() {
        }

        public final Set<Integer> a() {
            HashSet hashSet = new HashSet();
            Cursor cursorP = g.this.f4357e.p(new h1.a("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
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
                g.this.f4360h.s();
            }
            return hashSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            Lock lockH = g.this.f4357e.h();
            Set<Integer> setA = null;
            try {
                try {
                    lockH.lock();
                } catch (SQLiteException | IllegalStateException e8) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e8);
                }
                if (g.this.c()) {
                    if (g.this.f4358f.compareAndSet(true, false)) {
                        if (g.this.f4357e.k()) {
                            return;
                        }
                        RoomDatabase roomDatabase = g.this.f4357e;
                        if (roomDatabase.f1741g) {
                            h1.b bVarB = roomDatabase.i().b();
                            bVarB.d();
                            try {
                                setA = a();
                                bVarB.W();
                                bVarB.c();
                            } catch (Throwable th) {
                                bVarB.c();
                                throw th;
                            }
                        } else {
                            setA = a();
                        }
                        if (setA == null || setA.isEmpty()) {
                            return;
                        }
                        synchronized (g.this.f4363k) {
                            Iterator<Map.Entry<c, d>> it = g.this.f4363k.iterator();
                            while (it.hasNext()) {
                                it.next().getValue().a(setA);
                            }
                        }
                    }
                }
            } finally {
                lockH.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long[] f4367a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean[] f4368b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f4369c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f4370d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4371e;

        public b(int i8) {
            long[] jArr = new long[i8];
            this.f4367a = jArr;
            boolean[] zArr = new boolean[i8];
            this.f4368b = zArr;
            this.f4369c = new int[i8];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        public int[] a() {
            synchronized (this) {
                if (this.f4370d && !this.f4371e) {
                    int length = this.f4367a.length;
                    int i8 = 0;
                    while (true) {
                        int i9 = 1;
                        if (i8 >= length) {
                            this.f4371e = true;
                            this.f4370d = false;
                            return this.f4369c;
                        }
                        boolean z7 = this.f4367a[i8] > 0;
                        boolean[] zArr = this.f4368b;
                        if (z7 != zArr[i8]) {
                            int[] iArr = this.f4369c;
                            if (!z7) {
                                i9 = 2;
                            }
                            iArr[i8] = i9;
                        } else {
                            this.f4369c[i8] = 0;
                        }
                        zArr[i8] = z7;
                        i8++;
                    }
                }
                return null;
            }
        }

        public boolean b(int... iArr) {
            boolean z7;
            synchronized (this) {
                z7 = false;
                for (int i8 : iArr) {
                    long[] jArr = this.f4367a;
                    long j8 = jArr[i8];
                    jArr[i8] = 1 + j8;
                    if (j8 == 0) {
                        this.f4370d = true;
                        z7 = true;
                    }
                }
            }
            return z7;
        }

        public boolean c(int... iArr) {
            boolean z7;
            synchronized (this) {
                z7 = false;
                for (int i8 : iArr) {
                    long[] jArr = this.f4367a;
                    long j8 = jArr[i8];
                    jArr[i8] = j8 - 1;
                    if (j8 == 1) {
                        this.f4370d = true;
                        z7 = true;
                    }
                }
            }
            return z7;
        }

        public void d() {
            synchronized (this) {
                this.f4371e = false;
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f4372a;

        public c(String[] strArr) {
            this.f4372a = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        public boolean a() {
            return false;
        }

        public abstract void b(Set<String> set);
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f4373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String[] f4374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f4375c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Set<String> f4376d;

        public d(c cVar, int[] iArr, String[] strArr) {
            this.f4375c = cVar;
            this.f4373a = iArr;
            this.f4374b = strArr;
            if (iArr.length != 1) {
                this.f4376d = null;
                return;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(strArr[0]);
            this.f4376d = Collections.unmodifiableSet(hashSet);
        }

        public void a(Set<Integer> set) {
            int length = this.f4373a.length;
            Set<String> hashSet = null;
            for (int i8 = 0; i8 < length; i8++) {
                if (set.contains(Integer.valueOf(this.f4373a[i8]))) {
                    if (length == 1) {
                        hashSet = this.f4376d;
                    } else {
                        if (hashSet == null) {
                            hashSet = new HashSet<>(length);
                        }
                        hashSet.add(this.f4374b[i8]);
                    }
                }
            }
            if (hashSet != null) {
                this.f4375c.b(hashSet);
            }
        }

        public void b(String[] strArr) {
            Set<String> set = null;
            if (this.f4374b.length == 1) {
                int length = strArr.length;
                int i8 = 0;
                while (true) {
                    if (i8 >= length) {
                        break;
                    }
                    if (strArr[i8].equalsIgnoreCase(this.f4374b[0])) {
                        set = this.f4376d;
                        break;
                    }
                    i8++;
                }
            } else {
                HashSet hashSet = new HashSet();
                for (String str : strArr) {
                    String[] strArr2 = this.f4374b;
                    int length2 = strArr2.length;
                    int i9 = 0;
                    while (true) {
                        if (i9 < length2) {
                            String str2 = strArr2[i9];
                            if (str2.equalsIgnoreCase(str)) {
                                hashSet.add(str2);
                                break;
                            }
                            i9++;
                        }
                    }
                }
                if (hashSet.size() > 0) {
                    set = hashSet;
                }
            }
            if (set != null) {
                this.f4375c.b(set);
            }
        }
    }

    public g(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.f4357e = roomDatabase;
        this.f4361i = new b(strArr.length);
        this.f4356d = map2;
        this.f4362j = new f(roomDatabase);
        int length = strArr.length;
        this.f4355c = new String[length];
        for (int i8 = 0; i8 < length; i8++) {
            String str = strArr[i8];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.f4354b.put(lowerCase, Integer.valueOf(i8));
            String str2 = map.get(strArr[i8]);
            if (str2 != null) {
                this.f4355c[i8] = str2.toLowerCase(locale);
            } else {
                this.f4355c[i8] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            Locale locale2 = Locale.US;
            String lowerCase2 = value.toLowerCase(locale2);
            if (this.f4354b.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap<String, Integer> map3 = this.f4354b;
                map3.put(lowerCase3, map3.get(lowerCase2));
            }
        }
    }

    public static void b(StringBuilder sb, String str, String str2) {
        sb.append("`");
        sb.append("room_table_modification_trigger_");
        sb.append(str);
        sb.append(BydPowerManagerDevice.SEPARATOR_K_V);
        sb.append(str2);
        sb.append("`");
    }

    @SuppressLint({"RestrictedApi"})
    public void a(c cVar) {
        d dVarG;
        String[] strArrH = h(cVar.f4372a);
        int[] iArr = new int[strArrH.length];
        int length = strArrH.length;
        for (int i8 = 0; i8 < length; i8++) {
            Integer num = this.f4354b.get(strArrH[i8].toLowerCase(Locale.US));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + strArrH[i8]);
            }
            iArr[i8] = num.intValue();
        }
        d dVar = new d(cVar, iArr, strArrH);
        synchronized (this.f4363k) {
            dVarG = this.f4363k.g(cVar, dVar);
        }
        if (dVarG == null && this.f4361i.b(iArr)) {
            l();
        }
    }

    public boolean c() {
        if (!this.f4357e.o()) {
            return false;
        }
        if (!this.f4359g) {
            this.f4357e.i().b();
        }
        if (this.f4359g) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void d(h1.b bVar) {
        synchronized (this) {
            if (this.f4359g) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.k("PRAGMA temp_store = MEMORY;");
            bVar.k("PRAGMA recursive_triggers='ON';");
            bVar.k("CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            m(bVar);
            this.f4360h = bVar.u("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
            this.f4359g = true;
        }
    }

    public void e(String... strArr) {
        synchronized (this.f4363k) {
            for (Map.Entry<c, d> entry : this.f4363k) {
                if (!entry.getKey().a()) {
                    entry.getValue().b(strArr);
                }
            }
        }
    }

    public void f() {
        if (this.f4358f.compareAndSet(false, true)) {
            this.f4357e.j().execute(this.f4365m);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public void g(c cVar) {
        d dVarH;
        synchronized (this.f4363k) {
            dVarH = this.f4363k.h(cVar);
        }
        if (dVarH == null || !this.f4361i.c(dVarH.f4373a)) {
            return;
        }
        l();
    }

    public final String[] h(String[] strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (this.f4356d.containsKey(lowerCase)) {
                hashSet.addAll(this.f4356d.get(lowerCase));
            } else {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public void i(Context context, String str) {
        this.f4364l = new h(context, str, this, this.f4357e.j());
    }

    public final void j(h1.b bVar, int i8) {
        bVar.k("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i8 + ", 0)");
        String str = this.f4355c[i8];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f4353a) {
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
            sb.append(i8);
            sb.append(" AND ");
            sb.append("invalidated");
            sb.append(" = 0");
            sb.append("; END");
            bVar.k(sb.toString());
        }
    }

    public final void k(h1.b bVar, int i8) {
        String str = this.f4355c[i8];
        StringBuilder sb = new StringBuilder();
        for (String str2 : f4353a) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            b(sb, str, str2);
            bVar.k(sb.toString());
        }
    }

    public void l() {
        if (this.f4357e.o()) {
            m(this.f4357e.i().b());
        }
    }

    public void m(h1.b bVar) {
        if (bVar.K()) {
            return;
        }
        while (true) {
            try {
                Lock lockH = this.f4357e.h();
                lockH.lock();
                try {
                    int[] iArrA = this.f4361i.a();
                    if (iArrA == null) {
                        return;
                    }
                    int length = iArrA.length;
                    bVar.d();
                    for (int i8 = 0; i8 < length; i8++) {
                        try {
                            int i9 = iArrA[i8];
                            if (i9 == 1) {
                                j(bVar, i8);
                            } else if (i9 == 2) {
                                k(bVar, i8);
                            }
                        } finally {
                        }
                    }
                    bVar.W();
                    bVar.c();
                    this.f4361i.d();
                } finally {
                    lockH.unlock();
                }
            } catch (SQLiteException | IllegalStateException e8) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e8);
                return;
            }
        }
    }
}
