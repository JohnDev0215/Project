package b.b.i;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import b.b.a.f.h;
import b.b.m.f.d.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ServiceDialogHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: compiled from: ServiceDialogHolder.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LinkedList f1920a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f1921b;

        public a(LinkedList linkedList, Context context) {
            this.f1920a = linkedList;
            this.f1921b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (this.f1920a.size() > 0) {
                b.b.m.f.d.c cVar = (b.b.m.f.d.c) this.f1920a.pop();
                b.b.m.c.a.a("ServiceDialogHolder", "onConfigurationChanged", ", do show dialog: " + cVar);
                if (cVar.e()) {
                    c.h(cVar.b(), cVar.d(), this.f1921b);
                } else {
                    b.b.m.c.a.a("ServiceDialogHolder", "onConfigurationChanged", ", do show dialog.isStaticMethod: " + cVar.e());
                }
            }
        }
    }

    /* JADX INFO: compiled from: ServiceDialogHolder.java */
    public static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.m.f.d.b f1922a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f1923b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f1924c;

        public b(b.b.m.f.d.b bVar, Context context, Class cls) {
            this.f1922a = bVar;
            this.f1923b = context;
            this.f1924c = cls;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.h(this.f1922a.d(), this.f1922a.f(), this.f1923b);
            b.b.m.c.a.d("ServiceDialogHolder", "checkDialogLevel", ", Page is invoked. page: " + this.f1924c);
        }
    }

    public static boolean b(Context context, Class cls) {
        if (context == null) {
            return false;
        }
        b.b.m.f.d.b bVarG = g(cls);
        if (bVarG == null) {
            return true;
        }
        b.b.m.c.a.d("ServiceDialogHolder", "checkDialogLevel", ", cd: " + bVarG);
        if (i(cls)) {
            if (!bVarG.j()) {
                b.b.m.c.a.d("ServiceDialogHolder", "checkDialogLevel", ", Page is showing on top. page: " + cls);
                return false;
            }
            a.b.a().r(cls);
        }
        bVarG.c();
        b.b.m.c.a.d("ServiceDialogHolder", "checkDialogLevel", ", Page is detached. page: " + cls);
        h.a().g(new b(bVarG, context, cls), 80L);
        return false;
    }

    public static void c(Class<? extends b.b.m.e.a> cls) {
        a.b.a().c(cls);
    }

    public static void d() {
        b.b.m.c.a.d("ServiceDialogHolder", "--dismissAll---");
        a.b.a().d();
    }

    public static void e(String str) {
        b.b.m.c.a.d("ServiceDialogHolder", "--dismissAll---clazzName: " + str);
        a.b.a().e(str);
    }

    public static b.b.m.f.d.b f(b.b.m.e.a aVar, boolean z) {
        b.b.m.f.d.b bVarN = a.b.a().n(aVar);
        if (bVarN != null) {
            bVarN.y(z);
        }
        return bVarN;
    }

    public static b.b.m.f.d.b g(Class<? extends b.b.m.e.a> cls) {
        return a.b.a().g(cls);
    }

    public static void h(String str, String str2, Context context) {
        b.b.m.c.a.a("ServiceDialogHolder", "invokeDialog", ", cls: " + str + ", methodName: " + str2 + ", ctx: " + context);
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, Context.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context);
        } catch (Exception e2) {
            b.b.m.c.a.c("ServiceDialogHolder", "invokeDialog", ", e: " + e2.toString());
        }
    }

    public static boolean i(Class<? extends b.b.m.e.a> cls) {
        return a.b.a().m(cls);
    }

    public static void j(Context context, Configuration configuration) {
        b.b.m.c.a.a("ServiceDialogHolder", "onConfigurationChanged", ", newConfig: " + configuration);
        if (b.b.m.f.d.a.l()) {
            List<b.b.m.f.d.b> listH = a.b.a().h();
            b.b.m.c.a.a("ServiceDialogHolder", "onConfigurationChanged", ", list: " + listH);
            ArrayList arrayList = new ArrayList();
            for (b.b.m.f.d.b bVar : listH) {
                if (bVar.l()) {
                    b.b.m.f.d.c cVar = new b.b.m.f.d.c(bVar.e(), bVar.d(), bVar.f());
                    cVar.f(bVar.m());
                    arrayList.add(cVar);
                    b.b.m.c.a.a("ServiceDialogHolder", "onConfigurationChanged", ", dismiss dialog: " + bVar);
                    a.b.a().e(bVar.d());
                }
            }
            Collections.sort(arrayList);
            LinkedList linkedList = new LinkedList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                linkedList.offer((b.b.m.f.d.c) it.next());
            }
            new Handler(Looper.myLooper()).postDelayed(new a(linkedList, context), 180L);
        }
    }

    public static void k() {
        a.b.a().q();
    }

    public static void l(Context context, b.b.m.e.a aVar) {
        f(aVar, true).E(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true);
        aVar.resume();
    }

    public static void m(Context context, b.b.m.e.a aVar, boolean z) {
        f(aVar, z).E(context, aVar.getRootView(), aVar.getWidth(), aVar.getHeight(), true);
        aVar.resume();
    }
}
