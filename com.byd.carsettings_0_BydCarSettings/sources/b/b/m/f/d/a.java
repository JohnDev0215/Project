package b.b.m.f.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BydDialogManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<Integer, b.b.m.f.d.b> f2122a = new ConcurrentHashMap<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, b.b.m.e.a> f2123b = new ConcurrentHashMap<>(1);

    /* JADX INFO: renamed from: b.b.m.f.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydDialogManager.java */
    public class C0086a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.m.f.d.b f2124a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.b.m.e.a f2125b;

        public C0086a(b.b.m.f.d.b bVar, b.b.m.e.a aVar) {
            this.f2124a = bVar;
            this.f2125b = aVar;
        }

        @Override // b.b.m.f.d.f
        public void onDialogDismiss() {
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---onDialogDismiss--0-dialogMap.size:" + a.f2122a.size());
            if (a.f2122a.size() <= 1) {
                a.f2122a.clear();
            } else {
                a.f2122a.remove(Integer.valueOf(this.f2124a.e()));
            }
            if (a.f2123b.size() <= 1) {
                a.f2123b.clear();
            } else {
                a.f2123b.remove(this.f2125b.getClass().getName());
            }
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---onDialogDismiss---thisDialog:" + this.f2124a);
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---onDialogDismiss---pager:" + this.f2125b);
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---onDialogDismiss--1-dialogMap.size:" + a.f2122a.size());
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---onDialogDismiss--dialogMap:" + a.f2122a);
            this.f2125b.onDestroy();
        }
    }

    /* JADX INFO: compiled from: BydDialogManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2127a = new a(null);

        public static a a() {
            return f2127a;
        }
    }

    public /* synthetic */ a(C0086a c0086a) {
        this();
    }

    public static boolean i() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            Iterator<b.b.m.f.d.b> it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                if (!it.next().i()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean j() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            Iterator<b.b.m.f.d.b> it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                if (!it.next().m()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean k() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        return (concurrentHashMap == null || concurrentHashMap.isEmpty()) ? false : true;
    }

    public static boolean l() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            Iterator<b.b.m.f.d.b> it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                if (it.next().m()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void c(Class<? extends b.b.m.e.a> cls) {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap == null || cls == null) {
            return;
        }
        b.b.m.c.a.d("BydDialogManager", "dismiss", ", pageClass: " + cls.getName());
        for (b.b.m.f.d.b bVar : concurrentHashMap.values()) {
            b.b.m.c.a.d("BydDialogManager", "dismiss", "--SubClazzName: " + bVar.g() + ", " + bVar.f());
            if (cls.getName().equals(bVar.g())) {
                b.b.m.c.a.d("BydDialogManager", "dismiss", "--do dismiss, SubClazzName: " + bVar.g());
                bVar.c();
            }
        }
    }

    public void d() {
        StringBuilder sb = new StringBuilder();
        sb.append("--dismissAll--Do dismiss all, dialogMap.size: ");
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        sb.append(concurrentHashMap.size());
        b.b.m.c.a.d("BydDialogManager", sb.toString());
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        for (b.b.m.f.d.b bVar : concurrentHashMap.values()) {
            if (bVar.i()) {
                bVar.c();
            }
        }
    }

    public void e(String str) {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap == null || str == null || concurrentHashMap.isEmpty()) {
            return;
        }
        b.b.m.c.a.d("BydDialogManager", "dismissAll", "Do dismiss really, clazzName:" + str);
        for (b.b.m.f.d.b bVar : concurrentHashMap.values()) {
            if (str.equals(bVar.d())) {
                b.b.m.c.a.d("BydDialogManager", "dismissAll", "Do dismiss really and remove, clazzName = " + str);
                bVar.c();
            }
        }
    }

    public int f() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap.keySet().isEmpty()) {
            return 1;
        }
        int iIntValue = 1;
        for (Integer num : concurrentHashMap.keySet()) {
            if (num.intValue() >= iIntValue) {
                iIntValue = num.intValue();
            }
        }
        return iIntValue + 1;
    }

    public b.b.m.f.d.b g(Class<? extends b.b.m.e.a> cls) {
        if (cls == null) {
            return null;
        }
        for (b.b.m.f.d.b bVar : f2122a.values()) {
            if (cls.getName().equals(bVar.g())) {
                return bVar;
            }
        }
        return null;
    }

    public List<b.b.m.f.d.b> h() {
        if (!k()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<b.b.m.f.d.b> it = f2122a.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public boolean m(Class<? extends b.b.m.e.a> cls) {
        if (cls == null) {
            return false;
        }
        int iIntValue = 0;
        int iIntValue2 = 1;
        for (Integer num : f2122a.keySet()) {
            if (cls.getName().equals(f2122a.get(num).g())) {
                iIntValue = num.intValue();
            }
            if (num.intValue() >= iIntValue2) {
                iIntValue2 = num.intValue();
            }
        }
        return iIntValue >= iIntValue2;
    }

    public b.b.m.f.d.b n(b.b.m.e.a aVar) {
        if (aVar == null) {
            return null;
        }
        return o(aVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b.b.m.f.d.b o(b.b.m.e.a aVar, boolean z) {
        b.b.m.f.d.b bVar = null;
        if (aVar == null) {
            return null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        b.b.m.f.d.b bVarG = g(aVar.getClass());
        b.b.m.c.a.d("BydDialogManager", "newDialog", "---0--dialog:" + bVarG);
        if (bVarG != null) {
            b.b.m.c.a.d("BydDialogManager", "newDialog", "-----dialog.isDetached:" + bVarG.j());
            if (bVarG.j()) {
                f2122a.remove(Integer.valueOf(bVarG.e()));
            } else {
                bVarG.c();
            }
        } else {
            bVar = bVarG;
        }
        int iF = f();
        if (bVar == null) {
            bVar = new b.b.m.f.d.b();
            bVar.r(z);
            bVar.v(iF);
            bVar.t(aVar.getDialogTag());
            bVar.z(aVar.getClass().getName());
            if (stackTrace != null && stackTrace.length > 6) {
                StackTraceElement stackTraceElement = stackTrace[6];
                bVar.w(stackTraceElement.getMethodName());
                bVar.s(stackTraceElement.getClassName());
            }
            b.b.m.c.a.d("BydDialogManager", "newDialog", "---1--dialog:" + bVar);
            f2122a.put(Integer.valueOf(iF), bVar);
            f2123b.put(aVar.getClass().getName(), aVar);
            bVar.x(new C0086a(bVar, aVar));
        }
        return bVar;
    }

    public void p() {
        int iIntValue = Integer.MAX_VALUE;
        for (Integer num : f2122a.keySet()) {
            if (num.intValue() <= iIntValue) {
                iIntValue = num.intValue();
            }
        }
        if (iIntValue != Integer.MAX_VALUE) {
            b.b.m.f.d.b bVar = f2122a.get(Integer.valueOf(iIntValue));
            if (bVar.k()) {
                return;
            }
            bVar.u(true);
        }
    }

    public void q() {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, b.b.m.e.a> concurrentHashMap2 = f2123b;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
    }

    public void r(Class<? extends b.b.m.e.a> cls) {
        ConcurrentHashMap<Integer, b.b.m.f.d.b> concurrentHashMap = f2122a;
        if (concurrentHashMap == null || cls == null || concurrentHashMap.isEmpty()) {
            return;
        }
        String name = cls.getName();
        b.b.m.c.a.d("BydDialogManager", "remove", "Do remove really, clazzName:" + name);
        int iE = -1;
        for (b.b.m.f.d.b bVar : concurrentHashMap.values()) {
            if (name.equals(bVar.d())) {
                iE = bVar.e();
            }
        }
        b.b.m.c.a.d("BydDialogManager", "remove", "Do remove really and remove, clazzName = " + name);
        if (iE != -1) {
            f2122a.remove(Integer.valueOf(iE));
        }
    }

    public a() {
    }
}
