package p;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class h52 {
    public final Context a;
    public final Class b;
    public final String c;
    public final ArrayList d;
    public final ArrayList e;
    public final ArrayList f;
    public Executor g;
    public Executor h;
    public ph2 i;
    public boolean j;
    public final RoomDatabase$JournalMode k;
    public boolean l;
    public boolean m;
    public final long n;
    public final gk1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinkedHashSet f128p;
    public HashSet q;

    public h52(Context context, Class cls, String str) {
        v03.j(context, "context");
        this.a = context;
        this.b = cls;
        this.c = str;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.k = RoomDatabase$JournalMode.c;
        this.l = true;
        this.n = -1L;
        this.o = new gk1();
        this.f128p = new LinkedHashSet();
    }

    public final void a(ik1... ik1VarArr) {
        if (this.q == null) {
            this.q = new HashSet();
        }
        for (ik1 ik1Var : ik1VarArr) {
            HashSet hashSet = this.q;
            v03.h(hashSet);
            hashSet.add(Integer.valueOf(ik1Var.a));
            HashSet hashSet2 = this.q;
            v03.h(hashSet2);
            hashSet2.add(Integer.valueOf(ik1Var.b));
        }
        this.o.a((ik1[]) Arrays.copyOf(ik1VarArr, ik1VarArr.length));
    }

    public final i52 b() {
        RoomDatabase$JournalMode roomDatabase$JournalMode;
        Executor executor = this.g;
        if (executor == null && this.h == null) {
            db dbVar = eb.h;
            this.h = dbVar;
            this.g = dbVar;
        } else if (executor != null && this.h == null) {
            this.h = executor;
        } else if (executor == null) {
            this.g = this.h;
        }
        HashSet hashSet = this.q;
        LinkedHashSet linkedHashSet = this.f128p;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                if (!(!linkedHashSet.contains(Integer.valueOf(iIntValue)))) {
                    throw new IllegalArgumentException(u4.e("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ", iIntValue).toString());
                }
            }
        }
        ph2 zb0Var = this.i;
        if (zb0Var == null) {
            zb0Var = new zb0();
        }
        ph2 ph2Var = zb0Var;
        if (this.n > 0) {
            if (this.c != null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.".toString());
        }
        String str = this.c;
        gk1 gk1Var = this.o;
        ArrayList arrayList = this.d;
        boolean z = this.j;
        RoomDatabase$JournalMode roomDatabase$JournalMode2 = this.k;
        roomDatabase$JournalMode2.getClass();
        Context context = this.a;
        v03.j(context, "context");
        RoomDatabase$JournalMode roomDatabase$JournalMode3 = RoomDatabase$JournalMode.c;
        RoomDatabase$JournalMode roomDatabase$JournalMode4 = RoomDatabase$JournalMode.k;
        if (roomDatabase$JournalMode2 != roomDatabase$JournalMode3) {
            roomDatabase$JournalMode = roomDatabase$JournalMode2;
        } else {
            Object systemService = context.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            if (activityManager == null || activityManager.isLowRamDevice()) {
                roomDatabase$JournalMode2 = RoomDatabase$JournalMode.e;
                roomDatabase$JournalMode = roomDatabase$JournalMode2;
            } else {
                roomDatabase$JournalMode = roomDatabase$JournalMode4;
            }
        }
        Executor executor2 = this.g;
        if (executor2 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Executor executor3 = this.h;
        if (executor3 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        a60 a60Var = new a60(context, str, ph2Var, gk1Var, arrayList, z, roomDatabase$JournalMode, executor2, executor3, this.l, this.m, linkedHashSet, this.e, this.f);
        Class cls = this.b;
        v03.j(cls, "klass");
        Package r4 = cls.getPackage();
        v03.h(r4);
        String name = r4.getName();
        String canonicalName = cls.getCanonicalName();
        v03.h(canonicalName);
        v03.i(name, "fullPackage");
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
            v03.i(canonicalName, "this as java.lang.String).substring(startIndex)");
        }
        String strReplace = canonicalName.replace('.', '_');
        v03.i(strReplace, "this as java.lang.String…replace(oldChar, newChar)");
        String strConcat = strReplace.concat("_Impl");
        try {
            Class<?> cls2 = Class.forName(name.length() == 0 ? strConcat : name + '.' + strConcat, true, cls.getClassLoader());
            v03.g(cls2, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
            i52 i52Var = (i52) cls2.newInstance();
            i52Var.getClass();
            i52Var.c = i52Var.f(a60Var);
            Set setI = i52Var.i();
            BitSet bitSet = new BitSet();
            Iterator it2 = setI.iterator();
            while (true) {
                boolean zHasNext = it2.hasNext();
                LinkedHashMap linkedHashMap = i52Var.g;
                int i = -1;
                List list = a60Var.o;
                if (zHasNext) {
                    Class cls3 = (Class) it2.next();
                    int size = list.size() - 1;
                    if (size >= 0) {
                        while (true) {
                            int i2 = size - 1;
                            if (cls3.isAssignableFrom(list.get(size).getClass())) {
                                bitSet.set(size);
                                i = size;
                                break;
                            }
                            if (i2 < 0) {
                                break;
                            }
                            size = i2;
                        }
                    }
                    if (i < 0) {
                        throw new IllegalArgumentException(("A required auto migration spec (" + cls3.getCanonicalName() + ") is missing in the database configuration.").toString());
                    }
                    linkedHashMap.put(cls3, list.get(i));
                } else {
                    int size2 = list.size() - 1;
                    if (size2 >= 0) {
                        while (true) {
                            int i3 = size2 - 1;
                            if (!bitSet.get(size2)) {
                                throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.".toString());
                            }
                            if (i3 < 0) {
                                break;
                            }
                            size2 = i3;
                        }
                    }
                    Iterator it3 = i52Var.g(linkedHashMap).iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        ik1 ik1Var = (ik1) it3.next();
                        int i4 = ik1Var.a;
                        gk1 gk1Var2 = a60Var.d;
                        HashMap map = gk1Var2.a;
                        if (map.containsKey(Integer.valueOf(i4))) {
                            Map mapH0 = (Map) map.get(Integer.valueOf(i4));
                            if (mapH0 == null) {
                                mapH0 = kotlin.collections.c.h0();
                            }
                            if (!mapH0.containsKey(Integer.valueOf(ik1Var.b))) {
                            }
                        }
                        gk1Var2.a(ik1Var);
                    }
                    c61 c61Var = i52Var.d;
                    i52Var.h().setWriteAheadLoggingEnabled(a60Var.g == roomDatabase$JournalMode4);
                    i52Var.f = a60Var.e;
                    i52Var.b = a60Var.h;
                    v03.j(a60Var.i, "executor");
                    new ArrayDeque();
                    i52Var.e = a60Var.f;
                    Intent intent = a60Var.j;
                    if (intent != null) {
                        String str2 = a60Var.b;
                        if (str2 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        c61Var.getClass();
                        Context context2 = a60Var.a;
                        v03.j(context2, "context");
                        Executor executor4 = c61Var.a.b;
                        if (executor4 == null) {
                            v03.Q("internalQueryExecutor");
                            throw null;
                        }
                        new ql1(context2, str2, intent, c61Var, executor4);
                    }
                    Map mapJ = i52Var.j();
                    BitSet bitSet2 = new BitSet();
                    Iterator it4 = mapJ.entrySet().iterator();
                    while (true) {
                        boolean zHasNext2 = it4.hasNext();
                        List list2 = a60Var.n;
                        if (!zHasNext2) {
                            int size3 = list2.size() - 1;
                            if (size3 >= 0) {
                                while (true) {
                                    int i5 = size3 - 1;
                                    if (!bitSet2.get(size3)) {
                                        throw new IllegalArgumentException("Unexpected type converter " + list2.get(size3) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                                    }
                                    if (i5 < 0) {
                                        break;
                                    }
                                    size3 = i5;
                                }
                            }
                            return i52Var;
                        }
                        Map.Entry entry = (Map.Entry) it4.next();
                        Class cls4 = (Class) entry.getKey();
                        for (Class cls5 : (List) entry.getValue()) {
                            int size4 = list2.size() - 1;
                            if (size4 >= 0) {
                                while (true) {
                                    int i6 = size4 - 1;
                                    if (cls5.isAssignableFrom(list2.get(size4).getClass())) {
                                        bitSet2.set(size4);
                                        break;
                                    }
                                    if (i6 < 0) {
                                        break;
                                    }
                                    size4 = i6;
                                }
                                size4 = -1;
                            } else {
                                size4 = -1;
                            }
                            if (size4 < 0) {
                                throw new IllegalArgumentException(("A required type converter (" + cls5 + ") for " + cls4.getCanonicalName() + " is missing in the database configuration.").toString());
                            }
                            i52Var.j.put(cls5, list2.get(size4));
                        }
                    }
                }
            }
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + strConcat + " does not exist");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("Cannot access the constructor " + cls + ".canonicalName");
        } catch (InstantiationException unused3) {
            throw new RuntimeException("Failed to create an instance of " + cls + ".canonicalName");
        }
    }
}
