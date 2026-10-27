package a.b.a.a.i;

import a.b.a.a.n.b;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile b f70a;
    public static final a d = new a();
    public static final List<a.b.a.a.n.b> b = Collections.synchronizedList(new ArrayList());
    public static final Map<String, b.a> c = Collections.synchronizedMap(new HashMap());

    public final int a(b.a aVar, String str, String str2) {
        List<a.b.a.a.n.b> logListeners = b;
        Intrinsics.checkExpressionValueIsNotNull(logListeners, "logListeners");
        synchronized (logListeners) {
            Iterator<a.b.a.a.n.b> it = logListeners.iterator();
            while (it.hasNext()) {
                it.next().a(3, aVar, str, str2);
            }
        }
        return Log.d(str, str2);
    }

    public final int a(String str, String str2) {
        b.a aVar = b.a.AllLog;
        Map<String, b.a> map = c;
        if (map.containsKey(str)) {
            b.a aVar2 = map.get(str);
            if (aVar2 == null) {
                Intrinsics.throwNpe();
            }
            aVar = aVar2;
        }
        return a(aVar, str, str2);
    }

    public final int b(b.a aVar, String str, String str2) {
        List<a.b.a.a.n.b> logListeners = b;
        Intrinsics.checkExpressionValueIsNotNull(logListeners, "logListeners");
        synchronized (logListeners) {
            Iterator<a.b.a.a.n.b> it = logListeners.iterator();
            while (it.hasNext()) {
                it.next().a(4, aVar, str, str2);
            }
        }
        return Log.i(str, str2);
    }

    public final int b(String str, String str2) {
        b.a aVar = b.a.AllLog;
        Map<String, b.a> map = c;
        if (map.containsKey(str)) {
            b.a aVar2 = map.get(str);
            if (aVar2 == null) {
                Intrinsics.throwNpe();
            }
            aVar = aVar2;
        }
        List<a.b.a.a.n.b> logListeners = b;
        Intrinsics.checkExpressionValueIsNotNull(logListeners, "logListeners");
        synchronized (logListeners) {
            Iterator<a.b.a.a.n.b> it = logListeners.iterator();
            while (it.hasNext()) {
                it.next().a(6, aVar, str, str2);
            }
        }
        return Log.e(str, str2);
    }

    public final int c(b.a aVar, String str, String str2) {
        List<a.b.a.a.n.b> logListeners = b;
        Intrinsics.checkExpressionValueIsNotNull(logListeners, "logListeners");
        synchronized (logListeners) {
            Iterator<a.b.a.a.n.b> it = logListeners.iterator();
            while (it.hasNext()) {
                it.next().a(2, aVar, str, str2);
            }
        }
        return Log.v(str, str2);
    }

    public final int c(String str, String str2) {
        b.a aVar = b.a.AllLog;
        Map<String, b.a> map = c;
        if (map.containsKey(str)) {
            b.a aVar2 = map.get(str);
            if (aVar2 == null) {
                Intrinsics.throwNpe();
            }
            aVar = aVar2;
        }
        return b(aVar, str, str2);
    }

    public final int d(String str, String str2) {
        b.a aVar = b.a.AllLog;
        Map<String, b.a> map = c;
        if (map.containsKey(str)) {
            b.a aVar2 = map.get(str);
            if (aVar2 == null) {
                Intrinsics.throwNpe();
            }
            aVar = aVar2;
        }
        return c(aVar, str, str2);
    }

    public final int e(String str, String str2) {
        b.a aVar = b.a.AllLog;
        Map<String, b.a> map = c;
        if (map.containsKey(str)) {
            b.a aVar2 = map.get(str);
            if (aVar2 == null) {
                Intrinsics.throwNpe();
            }
            aVar = aVar2;
        }
        List<a.b.a.a.n.b> logListeners = b;
        Intrinsics.checkExpressionValueIsNotNull(logListeners, "logListeners");
        synchronized (logListeners) {
            Iterator<a.b.a.a.n.b> it = logListeners.iterator();
            while (it.hasNext()) {
                it.next().a(5, aVar, str, str2);
            }
        }
        return Log.w(str, str2);
    }

    public static final class a {
        @JvmStatic
        public final b a() {
            if (b.f70a == null) {
                synchronized (b.class) {
                    if (b.f70a == null) {
                        b.f70a = new b();
                    }
                }
            }
            b bVar = b.f70a;
            if (bVar == null) {
                Intrinsics.throwNpe();
            }
            return bVar;
        }

        @JvmStatic
        public final b a(String str, b.a aVar) {
            Map<String, b.a> tagMap = b.c;
            Intrinsics.checkExpressionValueIsNotNull(tagMap, "tagMap");
            tagMap.put(str, aVar);
            return a();
        }
    }
}
