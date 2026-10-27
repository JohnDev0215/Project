package com.blankj.utilcode.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes2.dex */
public final class BusUtils {
    private static final Object NULL = "nULl";
    private static final String TAG = "BusUtils";
    private final Map<String, Set<Object>> mClassName_BusesMap;
    private final Map<String, Map<String, Object>> mClassName_Tag_Arg4StickyMap;
    private final Map<String, List<String>> mClassName_TagsMap;
    private final Map<String, List<b>> mTag_BusInfoListMap;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface Bus {
        int priority() default 0;

        boolean sticky() default false;

        String tag();

        ThreadMode threadMode() default ThreadMode.POSTING;
    }

    public enum ThreadMode {
        MAIN,
        IO,
        CPU,
        CACHED,
        SINGLE,
        POSTING
    }

    public class a implements Runnable {
        public final /* synthetic */ Object a;
        public final /* synthetic */ Object b;
        public final /* synthetic */ b c;
        public final /* synthetic */ boolean d;

        public a(Object obj, Object obj2, b bVar, boolean z) {
            this.a = obj;
            this.b = obj2;
            this.c = bVar;
            this.d = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            BusUtils.this.realInvokeMethod(this.a, this.b, this.c, this.d);
        }
    }

    public static final class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public boolean f;
        public String g;
        public int h;
        public Method i;
        public List<String> j = new CopyOnWriteArrayList();

        public b(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = z;
            this.g = str6;
            this.h = i;
        }

        public final String toString() {
            String strH;
            StringBuilder sbK = spi.c.k("BusInfo { tag : ");
            sbK.append(this.a);
            sbK.append(", desc: ");
            StringBuilder sb = new StringBuilder();
            sb.append(this.b);
            sb.append("#");
            sb.append(this.c);
            if ("".equals(this.d)) {
                strH = "()";
            } else {
                StringBuilder sbK2 = spi.c.k("(");
                sbK2.append(this.d);
                sbK2.append(" ");
                strH = spi.c.h(sbK2, this.e, ")");
            }
            sb.append(strH);
            sbK.append(sb.toString());
            sbK.append(", sticky: ");
            sbK.append(this.f);
            sbK.append(", threadMode: ");
            sbK.append(this.g);
            sbK.append(", method: ");
            sbK.append(this.i);
            sbK.append(", priority: ");
            sbK.append(this.h);
            sbK.append(" }");
            return sbK.toString();
        }
    }

    public static class c {
        public static final BusUtils a = new BusUtils(null);

        private c() {
        }
    }

    public /* synthetic */ BusUtils(a aVar) {
        this();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    private void consumeSticky(Object obj, String str, Object obj2) {
        List<b> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (bVar.j.contains(obj.getClass().getName()) && bVar.f) {
                synchronized (this.mClassName_Tag_Arg4StickyMap) {
                    Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(bVar.b);
                    if (map != null && map.containsKey(str)) {
                        invokeBus(obj, obj2, bVar, true);
                    }
                }
            }
        }
    }

    private void consumeStickyIfExist(Object obj) {
        Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(obj.getClass().getName());
        if (map == null) {
            return;
        }
        synchronized (this.mClassName_Tag_Arg4StickyMap) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                consumeSticky(obj, entry.getKey(), entry.getValue());
            }
        }
    }

    private Class getClassName(String str) {
        Objects.requireNonNull(str);
        switch (str) {
            case "double":
                return Double.TYPE;
            case "int":
                return Integer.TYPE;
            case "byte":
                return Byte.TYPE;
            case "char":
                return Character.TYPE;
            case "long":
                return Long.TYPE;
            case "boolean":
                return Boolean.TYPE;
            case "float":
                return Float.TYPE;
            case "short":
                return Short.TYPE;
            default:
                return Class.forName(str);
        }
    }

    private static BusUtils getInstance() {
        return c.a;
    }

    private Method getMethodByBusInfo(b bVar) {
        try {
            return "".equals(bVar.d) ? Class.forName(bVar.b).getDeclaredMethod(bVar.c, new Class[0]) : Class.forName(bVar.b).getDeclaredMethod(bVar.c, getClassName(bVar.d));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void init() {
    }

    private void invokeBus(Object obj, b bVar, boolean z) {
        invokeBus(null, obj, bVar, z);
    }

    private void invokeBuses(Object obj, b bVar, Set<Object> set) {
        try {
            if (obj == NULL) {
                Iterator<Object> it = set.iterator();
                while (it.hasNext()) {
                    bVar.i.invoke(it.next(), new Object[0]);
                }
            } else {
                Iterator<Object> it2 = set.iterator();
                while (it2.hasNext()) {
                    bVar.i.invoke(it2.next(), obj);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    private void invokeMethod(Object obj, b bVar, boolean z) {
        invokeMethod(null, obj, bVar, z);
    }

    public static void post(@NonNull String str) {
        post(str, NULL);
    }

    private void postInner(String str, Object obj) {
        postInner(str, obj, false);
    }

    public static void postSticky(@NonNull String str) {
        postSticky(str, NULL);
    }

    private void postStickyInner(String str, Object obj) {
        List<b> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (bVar.f) {
                synchronized (this.mClassName_Tag_Arg4StickyMap) {
                    Map<String, Object> concurrentHashMap = this.mClassName_Tag_Arg4StickyMap.get(bVar.b);
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap<>();
                        this.mClassName_Tag_Arg4StickyMap.put(bVar.b, concurrentHashMap);
                    }
                    concurrentHashMap.put(str, obj);
                }
                invokeBus(obj, bVar, true);
            } else {
                invokeBus(obj, bVar, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    public void realInvokeMethod(Object obj, Object obj2, b bVar, boolean z) {
        HashSet hashSet = new HashSet();
        if (obj == null) {
            Iterator it = bVar.j.iterator();
            while (it.hasNext()) {
                Set<Object> set = this.mClassName_BusesMap.get((String) it.next());
                if (set != null && !set.isEmpty()) {
                    hashSet.addAll(set);
                }
            }
            if (hashSet.size() == 0) {
                if (z) {
                    return;
                }
                Log.e(TAG, "The " + bVar + " was not registered before.");
                return;
            }
        } else {
            hashSet.add(obj);
        }
        invokeBuses(obj2, bVar, hashSet);
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.List<java.lang.String>, java.util.concurrent.CopyOnWriteArrayList] */
    private void recordTags(Class<?> cls, String str) {
        if (this.mClassName_TagsMap.get(str) == null) {
            synchronized (this.mClassName_TagsMap) {
                if (this.mClassName_TagsMap.get(str) == null) {
                    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                    for (Map.Entry<String, List<b>> entry : this.mTag_BusInfoListMap.entrySet()) {
                        for (b bVar : entry.getValue()) {
                            try {
                                if (Class.forName(bVar.b).isAssignableFrom(cls)) {
                                    copyOnWriteArrayList.add(entry.getKey());
                                    bVar.j.add(str);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    this.mClassName_TagsMap.put(str, copyOnWriteArrayList);
                }
            }
        }
    }

    public static void register(@Nullable Object obj) {
        getInstance().registerInner(obj);
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        registerBus(str, str2, str3, str4, str5, z, str6, 0);
    }

    public static void registerBus4Test(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
        getInstance().registerBus(str, str2, str3, str4, str5, z, str6, i);
    }

    private void registerInner(@Nullable Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        boolean z = false;
        synchronized (this.mClassName_BusesMap) {
            Set<Object> copyOnWriteArraySet = this.mClassName_BusesMap.get(name);
            if (copyOnWriteArraySet == null) {
                copyOnWriteArraySet = new CopyOnWriteArraySet<>();
                this.mClassName_BusesMap.put(name, copyOnWriteArraySet);
                z = true;
            }
            if (!copyOnWriteArraySet.contains(obj)) {
                copyOnWriteArraySet.add(obj);
                if (z) {
                    recordTags(cls, name);
                }
                consumeStickyIfExist(obj);
                return;
            }
            Log.w(TAG, "The bus of <" + obj + "> already registered.");
        }
    }

    public static void removeSticky(String str) {
        getInstance().removeStickyInner(str);
    }

    private void removeStickyInner(String str) {
        List<b> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (b bVar : list) {
            if (bVar.f) {
                synchronized (this.mClassName_Tag_Arg4StickyMap) {
                    Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(bVar.b);
                    if (map != null && map.containsKey(str)) {
                        map.remove(str);
                    }
                    return;
                }
            }
        }
    }

    public static String toString_() {
        return getInstance().toString();
    }

    public static void unregister(@Nullable Object obj) {
        getInstance().unregisterInner(obj);
    }

    private void unregisterInner(Object obj) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        synchronized (this.mClassName_BusesMap) {
            Set<Object> set = this.mClassName_BusesMap.get(name);
            if (set != null && set.contains(obj)) {
                set.remove(obj);
                return;
            }
            Log.e(TAG, "The bus of <" + obj + "> was not registered before.");
        }
    }

    public String toString() {
        StringBuilder sbK = spi.c.k("BusUtils: ");
        sbK.append(this.mTag_BusInfoListMap);
        return sbK.toString();
    }

    private BusUtils() {
        this.mTag_BusInfoListMap = new ConcurrentHashMap();
        this.mClassName_BusesMap = new ConcurrentHashMap();
        this.mClassName_TagsMap = new ConcurrentHashMap();
        this.mClassName_Tag_Arg4StickyMap = new ConcurrentHashMap();
        init();
    }

    private void invokeBus(Object obj, Object obj2, b bVar, boolean z) {
        if (bVar.i == null) {
            Method methodByBusInfo = getMethodByBusInfo(bVar);
            if (methodByBusInfo == null) {
                return;
            } else {
                bVar.i = methodByBusInfo;
            }
        }
        invokeMethod(obj, obj2, bVar, z);
    }

    private void invokeMethod(Object obj, Object obj2, b bVar, boolean z) {
        a aVar;
        aVar = new a(obj, obj2, bVar, z);
        String str = bVar.g;
        Objects.requireNonNull(str);
        switch (str) {
            case "SINGLE":
                ThreadUtils.getSinglePool().execute(aVar);
                break;
            case "IO":
                ThreadUtils.getIoPool().execute(aVar);
                break;
            case "CPU":
                ThreadUtils.getCpuPool().execute(aVar);
                break;
            case "MAIN":
                ThreadUtils.runOnUiThread(aVar);
                break;
            case "CACHED":
                ThreadUtils.getCachedPool().execute(aVar);
                break;
            default:
                aVar.run();
                break;
        }
    }

    public static void post(@NonNull String str, @NonNull Object obj) {
        getInstance().postInner(str, obj);
    }

    private void postInner(String str, Object obj, boolean z) {
        List<b> list = this.mTag_BusInfoListMap.get(str);
        if (list != null) {
            Iterator<b> it = list.iterator();
            while (it.hasNext()) {
                invokeBus(obj, it.next(), z);
            }
            return;
        }
        Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
        if (this.mTag_BusInfoListMap.isEmpty()) {
            Log.e(TAG, "Please check whether the bus plugin is applied.");
        }
    }

    public static void postSticky(@NonNull String str, Object obj) {
        getInstance().postStickyInner(str, obj);
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
        List<b> copyOnWriteArrayList = this.mTag_BusInfoListMap.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.mTag_BusInfoListMap.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(new b(str, str2, str3, str4, str5, z, str6, i));
    }
}
