package b.b.m.f.d;

import android.content.Context;
import b.b.m.f.d.a;

/* JADX INFO: compiled from: DialogHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: compiled from: DialogHolder.java */
    public interface a {
        void onOKClicked();
    }

    public static void a(Class<? extends b.b.m.e.a> cls) {
        a.b.a().c(cls);
    }

    public static void b() {
        a.b.a().d();
    }

    public static void c(String str) {
        a.b.a().e(str);
    }

    public static b d(b.b.m.e.a aVar) {
        return e(aVar, true);
    }

    public static b e(b.b.m.e.a aVar, boolean z) {
        b bVarO = a.b.a().o(aVar, z);
        if (bVarO != null) {
            bVarO.y(false);
        }
        return bVarO;
    }

    public static b f(Class<? extends b.b.m.e.a> cls) {
        return a.b.a().g(cls);
    }

    public static boolean g() {
        a.b.a();
        return b.b.m.f.d.a.i();
    }

    public static boolean h() {
        return b.b.m.f.d.a.j();
    }

    public static void i(Context context, b.b.m.e.a aVar, int i, int i2) {
        if (aVar == null || aVar.getRootView() == null) {
            return;
        }
        d(aVar).A(context, aVar.getRootView(), i, i2);
    }

    public static void j(Context context, b.b.m.e.a aVar, int i, int i2) {
        if (aVar == null || aVar.getRootView() == null) {
            return;
        }
        e(aVar, false).F(context, aVar.getRootView(), i, i2);
    }
}
