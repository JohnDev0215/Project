package c.a.a;

import android.content.Context;
import c.a.d.d;
import c.a.d.k;
import com.byd.bydbase.R$style;

/* JADX INFO: compiled from: CarLayoutMangerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(a aVar, int i, Context context) {
        return i != 1 ? aVar.d() : aVar.a();
    }

    public static int b(a aVar, Context context) {
        return a(aVar, c(), context);
    }

    public static int c() {
        return !e() ? 1 : 0;
    }

    public static int d(Context context) {
        return a.a.a.a(context).b();
    }

    public static boolean e() {
        return !d.f1595a;
    }

    public static void f(Context context) throws Throwable {
        int iD = d(context);
        k.b("setTheme----style: " + iD);
        if (iD == 1) {
            context.setTheme(R$style.byd_denza);
        } else {
            context.setTheme(R$style.byd_dynasty);
        }
    }
}
