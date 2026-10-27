package b.s;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Arrays;
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
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f3146a = {"UPDATE", "DELETE", "INSERT"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f3148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<String, Set<String>> f3149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f3150e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile b.u.a.e f3153h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f3154i;
    public final b.s.c j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AtomicBoolean f3151f = new AtomicBoolean(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f3152g = false;

    @SuppressLint({"RestrictedApi"})
    public final b.c.a.b.b<?, c> k = new b.c.a.b.b<>();
    public Runnable l = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap<String, Integer> f3147b = new HashMap<>();

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public class a implements Runnable {
        public a() {
        }

        public final Set<Integer> a() {
            HashSet hashSet = new HashSet();
            Cursor cursorI = d.this.f3150e.i(new b.u.a.a("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
            while (cursorI.moveToNext()) {
                try {
                    hashSet.add(Integer.valueOf(cursorI.getInt(0)));
                } catch (Throwable th) {
                    cursorI.close();
                    throw th;
                }
            }
            cursorI.close();
            if (!hashSet.isEmpty()) {
                d.this.f3153h.s();
            }
            return hashSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            Lock lockD = d.this.f3150e.d();
            Set<Integer> setA = null;
            try {
                try {
                    lockD.lock();
                } catch (SQLiteException | IllegalStateException e2) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                }
                if (d.this.a()) {
                    if (d.this.f3151f.compareAndSet(true, false)) {
                        if (d.this.f3150e.f()) {
                            return;
                        }
                        e eVar = d.this.f3150e;
                        if (eVar.f3166e) {
                            b.u.a.b bVarA = eVar.e().a();
                            bVarA.f();
                            try {
                                setA = a();
                                bVarA.r();
                                bVarA.e();
                            } catch (Throwable th) {
                                bVarA.e();
                                throw th;
                            }
                        } else {
                            setA = a();
                        }
                        if (setA == null || setA.isEmpty()) {
                            return;
                        }
                        synchronized (d.this.k) {
                            Iterator<Map.Entry<?, c>> it = d.this.k.iterator();
                            while (it.hasNext()) {
                                it.next().getValue().a(setA);
                            }
                        }
                    }
                }
            } finally {
                lockD.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long[] f3156a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean[] f3157b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f3158c;

        public b(int i2) {
            long[] jArr = new long[i2];
            this.f3156a = jArr;
            boolean[] zArr = new boolean[i2];
            this.f3157b = zArr;
            this.f3158c = new int[i2];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }
    }

    /* JADX INFO: compiled from: InvalidationTracker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f3159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String[] f3160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Set<String> f3161c;

        public void a(Set<Integer> set) {
            int length = this.f3159a.length;
            Set hashSet = null;
            for (int i2 = 0; i2 < length; i2++) {
                if (set.contains(Integer.valueOf(this.f3159a[i2]))) {
                    if (length == 1) {
                        hashSet = this.f3161c;
                    } else {
                        if (hashSet == null) {
                            hashSet = new HashSet(length);
                        }
                        hashSet.add(this.f3160b[i2]);
                    }
                }
            }
            if (hashSet != null) {
                throw null;
            }
        }
    }

    public d(e eVar, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.f3150e = eVar;
        this.f3154i = new b(strArr.length);
        this.f3149d = map2;
        this.j = new b.s.c(eVar);
        int length = strArr.length;
        this.f3148c = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String str = strArr[i2];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.f3147b.put(lowerCase, Integer.valueOf(i2));
            String str2 = map.get(strArr[i2]);
            if (str2 != null) {
                this.f3148c[i2] = str2.toLowerCase(locale);
            } else {
                this.f3148c[i2] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            Locale locale2 = Locale.US;
            String lowerCase2 = value.toLowerCase(locale2);
            if (this.f3147b.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap<String, Integer> map3 = this.f3147b;
                map3.put(lowerCase3, map3.get(lowerCase2));
            }
        }
    }

    public boolean a() {
        if (!this.f3150e.h()) {
            return false;
        }
        if (!this.f3152g) {
            this.f3150e.e().a();
        }
        if (this.f3152g) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }
}
