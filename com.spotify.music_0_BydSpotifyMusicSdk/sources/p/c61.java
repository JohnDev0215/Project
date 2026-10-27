package p;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.spotify.cosmos.cosmos.Request;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class c61 {
    public static final String[] n = {"UPDATE", Request.DELETE, "INSERT"};
    public final i52 a;
    public final Map b;
    public final Map c;
    public final LinkedHashMap d;
    public final String[] e;
    public final AtomicBoolean f;
    public volatile boolean g;
    public volatile th2 h;
    public final a61 i;
    public final q62 j;
    public final Object k;
    public final Object l;
    public final rv m;

    public c61(i52 i52Var, HashMap map, HashMap map2, String... strArr) {
        String lowerCase;
        v03.j(i52Var, "database");
        this.a = i52Var;
        this.b = map;
        this.c = map2;
        this.f = new AtomicBoolean(false);
        this.i = new a61(strArr.length);
        new ay2(i52Var, 2);
        this.j = new q62();
        this.k = new Object();
        this.l = new Object();
        this.d = new LinkedHashMap();
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            v03.i(locale, "US");
            String lowerCase2 = str.toLowerCase(locale);
            v03.i(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            this.d.put(lowerCase2, Integer.valueOf(i));
            String str2 = (String) this.b.get(strArr[i]);
            if (str2 != null) {
                lowerCase = str2.toLowerCase(locale);
                v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            if (lowerCase != null) {
                lowerCase2 = lowerCase;
            }
            strArr2[i] = lowerCase2;
        }
        this.e = strArr2;
        for (Map.Entry entry : this.b.entrySet()) {
            String str3 = (String) entry.getValue();
            Locale locale2 = Locale.US;
            v03.i(locale2, "US");
            String lowerCase3 = str3.toLowerCase(locale2);
            v03.i(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
            if (this.d.containsKey(lowerCase3)) {
                String lowerCase4 = ((String) entry.getKey()).toLowerCase(locale2);
                v03.i(lowerCase4, "this as java.lang.String).toLowerCase(locale)");
                LinkedHashMap linkedHashMap = this.d;
                v03.j(linkedHashMap, "<this>");
                Object obj = linkedHashMap.get(lowerCase3);
                if (obj == null && !linkedHashMap.containsKey(lowerCase3)) {
                    throw new NoSuchElementException("Key " + ((Object) lowerCase3) + " is missing in the map.");
                }
                linkedHashMap.put(lowerCase4, obj);
            }
        }
        this.m = new rv(9, this);
    }

    public final void a(ol1 ol1Var) {
        Object obj;
        b61 b61Var;
        boolean z;
        i52 i52Var;
        nh2 nh2Var;
        String[] strArr = ol1Var.a;
        SetBuilder setBuilder = new SetBuilder();
        for (String str : strArr) {
            Locale locale = Locale.US;
            v03.i(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Map map = this.c;
            if (map.containsKey(lowerCase)) {
                String lowerCase2 = str.toLowerCase(locale);
                v03.i(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                Object obj2 = map.get(lowerCase2);
                v03.h(obj2);
                setBuilder.addAll((Collection) obj2);
            } else {
                setBuilder.add(str);
            }
        }
        setBuilder.b();
        Object[] array = setBuilder.toArray(new String[0]);
        v03.g(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr2 = (String[]) array;
        ArrayList arrayList = new ArrayList(strArr2.length);
        for (String str2 : strArr2) {
            LinkedHashMap linkedHashMap = this.d;
            Locale locale2 = Locale.US;
            v03.i(locale2, "US");
            String lowerCase3 = str2.toLowerCase(locale2);
            v03.i(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
            Integer num = (Integer) linkedHashMap.get(lowerCase3);
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name ".concat(str2));
            }
            arrayList.add(Integer.valueOf(num.intValue()));
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Number) it.next()).intValue();
            i++;
        }
        b61 b61Var2 = new b61(ol1Var, iArr, strArr2);
        synchronized (this.j) {
            q62 q62Var = this.j;
            m62 m62VarA = q62Var.a(ol1Var);
            if (m62VarA != null) {
                obj = m62VarA.e;
            } else {
                m62 m62Var = new m62(ol1Var, b61Var2);
                q62Var.l++;
                m62 m62Var2 = q62Var.e;
                if (m62Var2 == null) {
                    q62Var.c = m62Var;
                    q62Var.e = m62Var;
                } else {
                    m62Var2.k = m62Var;
                    m62Var.l = m62Var2;
                    q62Var.e = m62Var;
                }
                obj = null;
            }
            b61Var = (b61) obj;
        }
        if (b61Var == null) {
            a61 a61Var = this.i;
            int[] iArrCopyOf = Arrays.copyOf(iArr, size);
            a61Var.getClass();
            v03.j(iArrCopyOf, "tableIds");
            synchronized (a61Var) {
                z = false;
                for (int i2 : iArrCopyOf) {
                    long[] jArr = a61Var.a;
                    long j = jArr[i2];
                    jArr[i2] = 1 + j;
                    if (j == 0) {
                        a61Var.d = true;
                        z = true;
                    }
                }
            }
            if (z && (nh2Var = (i52Var = this.a).a) != null && nh2Var.isOpen()) {
                d(i52Var.h().u0());
            }
        }
    }

    public final boolean b() {
        nh2 nh2Var = this.a.a;
        if (!(nh2Var != null && nh2Var.isOpen())) {
            return false;
        }
        if (!this.g) {
            this.a.h().u0();
        }
        if (this.g) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public final void c(nh2 nh2Var, int i) {
        nh2Var.w("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.e[i];
        String[] strArr = n;
        for (int i2 = 0; i2 < 3; i2++) {
            String str2 = strArr[i2];
            String str3 = "CREATE TEMP TRIGGER IF NOT EXISTS " + zb0.y(str, str2) + " AFTER " + str2 + " ON `" + str + "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = " + i + " AND invalidated = 0; END";
            v03.i(str3, "StringBuilder().apply(builderAction).toString()");
            nh2Var.w(str3);
        }
    }

    public final void d(nh2 nh2Var) {
        v03.j(nh2Var, "database");
        if (nh2Var.S()) {
            return;
        }
        try {
            ReentrantReadWriteLock.ReadLock lock = this.a.h.readLock();
            v03.i(lock, "readWriteLock.readLock()");
            lock.lock();
            try {
                synchronized (this.k) {
                    int[] iArrA = this.i.a();
                    if (iArrA == null) {
                        return;
                    }
                    if (nh2Var.e0()) {
                        nh2Var.k0();
                    } else {
                        nh2Var.n();
                    }
                    try {
                        int length = iArrA.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = iArrA[i];
                            int i4 = i2 + 1;
                            if (i3 == 1) {
                                c(nh2Var, i2);
                            } else if (i3 == 2) {
                                String str = this.e[i2];
                                String[] strArr = n;
                                for (int i5 = 0; i5 < 3; i5++) {
                                    String str2 = "DROP TRIGGER IF EXISTS " + zb0.y(str, strArr[i5]);
                                    v03.i(str2, "StringBuilder().apply(builderAction).toString()");
                                    nh2Var.w(str2);
                                }
                            }
                            i++;
                            i2 = i4;
                        }
                        nh2Var.j0();
                        nh2Var.m();
                    } catch (Throwable th) {
                        nh2Var.m();
                        throw th;
                    }
                }
            } finally {
                lock.unlock();
            }
        } catch (SQLiteException e) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
        } catch (IllegalStateException e2) {
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
        }
    }
}
