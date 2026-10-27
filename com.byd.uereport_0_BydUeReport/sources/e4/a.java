package e4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BydDialogManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<Integer, e4.b> f4588a = new ConcurrentHashMap<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, z3.a> f4589b = new ConcurrentHashMap<>(1);

    /* JADX INFO: renamed from: e4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydDialogManager.java */
    public class C0030a implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ z3.a f4590a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e4.b f4591b;

        public C0030a(z3.a aVar, e4.b bVar) {
            this.f4590a = aVar;
            this.f4591b = bVar;
        }

        @Override // e4.e
        public void a() {
            x3.a.c("BydDialogManager", "newDialog", "---beforeDialogDismiss--dialogMap.size:" + a.f4588a.size());
            this.f4590a.beforeDestroy();
        }

        @Override // e4.e
        public void onDialogDismiss() {
            x3.a.c("BydDialogManager", "newDialog", "---onDialogDismiss--0-dialogMap.size:" + a.f4588a.size());
            if (a.f4588a.size() <= 1) {
                a.f4588a.clear();
            } else {
                a.f4588a.remove(Integer.valueOf(this.f4591b.f()));
            }
            if (a.f4589b.size() <= 1) {
                a.f4589b.clear();
            } else {
                a.f4589b.remove(this.f4590a.getClass().getName());
            }
            x3.a.c("BydDialogManager", "newDialog", "---onDialogDismiss---thisDialog:" + this.f4591b + ",pager :" + this.f4590a + ", 1-dialogMap.size: " + a.f4588a.size() + ", dialogMap : " + a.f4588a);
            this.f4590a.onDestroy();
        }
    }

    /* JADX INFO: compiled from: BydDialogManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4593a = new a(null);

        public static a a() {
            return f4593a;
        }
    }

    public /* synthetic */ a(C0030a c0030a) {
        this();
    }

    public static boolean l() {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        return (concurrentHashMap == null || concurrentHashMap.isEmpty()) ? false : true;
    }

    public static boolean m() {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            Iterator<e4.b> it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                if (it.next().o()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void c(Class<? extends z3.a> cls) {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        if (concurrentHashMap == null || cls == null) {
            return;
        }
        x3.a.c("BydDialogManager", "dismiss", ", pageClass: " + cls.getName());
        for (e4.b bVar : concurrentHashMap.values()) {
            x3.a.c("BydDialogManager", "dismiss", "--SubClazzName: " + bVar.h() + ", " + bVar.g());
            if (cls.getName().equals(bVar.h())) {
                x3.a.c("BydDialogManager", "dismiss", "--do dismiss, SubClazzName: " + bVar.h());
                bVar.d();
            }
        }
    }

    public void d() {
        StringBuilder sb = new StringBuilder();
        sb.append("--dismissAll--Do dismiss all, dialogMap.size: ");
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        sb.append(concurrentHashMap.size());
        x3.a.c("BydDialogManager", sb.toString());
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        for (e4.b bVar : concurrentHashMap.values()) {
            if (bVar.j()) {
                bVar.d();
            }
        }
    }

    public void e(String str) {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        if (concurrentHashMap == null || str == null || concurrentHashMap.isEmpty()) {
            return;
        }
        x3.a.c("BydDialogManager", "dismissAll", "Do dismiss really, clazzName:" + str);
        for (e4.b bVar : concurrentHashMap.values()) {
            if (str.equals(bVar.e())) {
                x3.a.c("BydDialogManager", "dismissAll", "Do dismiss really and remove, clazzName = " + str);
                bVar.d();
            }
        }
    }

    public final boolean f(Class<? extends z3.a>[] clsArr, String str) {
        if (clsArr != null && clsArr.length > 0) {
            for (Class<? extends z3.a> cls : clsArr) {
                x3.a.c("BydDialogManager", "---equalsDialogPager---clazz:" + cls.getName());
                if (str.equals(cls.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int g() {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
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

    public e4.b h(Class<? extends z3.a> cls) {
        if (cls == null) {
            return null;
        }
        for (e4.b bVar : f4588a.values()) {
            if (cls.getName().equals(bVar.h())) {
                return bVar;
            }
        }
        return null;
    }

    public z3.a i(Class<? extends z3.a> cls) {
        x3.a.a("BydDialogManager", "---getOperateCarDialogPager---clazz:" + cls);
        if (cls == null) {
            return null;
        }
        for (z3.a aVar : f4589b.values()) {
            x3.a.a("BydDialogManager", "---getOperateCarDialogPager---pager:" + aVar);
            if (cls.getName().equals(aVar.getClass().getName())) {
                return aVar;
            }
        }
        return null;
    }

    public List<e4.b> j() {
        if (!l()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<e4.b> it = f4588a.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public boolean k(Class<? extends z3.a>[] clsArr) {
        if (clsArr != null && clsArr.length > 0) {
            ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
            if (!concurrentHashMap.isEmpty()) {
                Iterator<e4.b> it = concurrentHashMap.values().iterator();
                while (it.hasNext()) {
                    if (f(clsArr, it.next().h())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean n(Class<? extends z3.a> cls) {
        if (cls == null) {
            return false;
        }
        int iIntValue = 0;
        int iIntValue2 = 1;
        for (Integer num : f4588a.keySet()) {
            if (cls.getName().equals(f4588a.get(num).h())) {
                iIntValue = num.intValue();
            }
            if (num.intValue() >= iIntValue2) {
                iIntValue2 = num.intValue();
            }
        }
        return iIntValue >= iIntValue2;
    }

    public e4.b o(z3.a aVar) {
        if (aVar == null) {
            return null;
        }
        return p(aVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e4.b p(z3.a aVar, boolean z7) {
        e4.b bVar = null;
        if (aVar == null) {
            return null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        e4.b bVarH = h(aVar.getClass());
        x3.a.c("BydDialogManager", "newDialog", "---0--dialog:" + bVarH);
        if (bVarH != null) {
            x3.a.c("BydDialogManager", "newDialog", "-----dialog.isDetached:" + bVarH.k());
            if (bVarH.k()) {
                f4588a.remove(Integer.valueOf(bVarH.f()));
            } else {
                bVarH.d();
            }
        } else {
            bVar = bVarH;
        }
        int iG = g();
        if (bVar == null) {
            bVar = new e4.b();
            bVar.v(z7);
            bVar.A(iG);
            bVar.x(aVar.getDialogTag());
            bVar.H(aVar.getClass().getName());
            if (stackTrace != null && stackTrace.length > 6) {
                StackTraceElement stackTraceElement = stackTrace[6];
                bVar.B(stackTraceElement.getMethodName());
                bVar.w(stackTraceElement.getClassName());
            }
            x3.a.c("BydDialogManager", "newDialog", "---1--dialog:" + bVar);
            f4588a.put(Integer.valueOf(iG), bVar);
            f4589b.put(aVar.getClass().getName(), aVar);
            bVar.C(new C0030a(aVar, bVar));
        }
        return bVar;
    }

    public void q() {
        e4.b bVar;
        int iIntValue = Integer.MAX_VALUE;
        for (Integer num : f4588a.keySet()) {
            if (num.intValue() <= iIntValue) {
                iIntValue = num.intValue();
            }
        }
        if (iIntValue == Integer.MAX_VALUE || (bVar = f4588a.get(Integer.valueOf(iIntValue))) == null || bVar.l()) {
            return;
        }
        bVar.y(true);
    }

    public void r() {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, z3.a> concurrentHashMap2 = f4589b;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
    }

    public void s(Class<? extends z3.a> cls) {
        ConcurrentHashMap<Integer, e4.b> concurrentHashMap = f4588a;
        if (concurrentHashMap == null || cls == null || concurrentHashMap.isEmpty()) {
            return;
        }
        String name = cls.getName();
        x3.a.c("BydDialogManager", "remove", "Do remove really, clazzName:" + name);
        int iF = -1;
        for (e4.b bVar : concurrentHashMap.values()) {
            if (name.equals(bVar.e())) {
                iF = bVar.f();
            }
        }
        x3.a.c("BydDialogManager", "remove", "Do remove really and remove, clazzName = " + name);
        if (iF != -1) {
            f4588a.remove(Integer.valueOf(iF));
        }
    }

    public void t(String str) {
        x3.a.a("BydDialogManager", "restoreDialogStateAfterRestore", "pagerClass:" + str);
        for (z3.a aVar : f4589b.values()) {
            if (str.equals(aVar.getClass().getName())) {
                aVar.onRestoreInstanceState();
            }
        }
    }

    public void u(String str) {
        ConcurrentHashMap<String, z3.a> concurrentHashMap;
        if (str == null || (concurrentHashMap = f4589b) == null) {
            return;
        }
        x3.a.a("BydDialogManager", "saveDialogStateBeforeDismiss", "pagerClass:" + str);
        for (z3.a aVar : concurrentHashMap.values()) {
            if (str.equals(aVar.getClass().getName())) {
                aVar.onSaveInstanceState();
            }
        }
    }

    public a() {
    }
}
