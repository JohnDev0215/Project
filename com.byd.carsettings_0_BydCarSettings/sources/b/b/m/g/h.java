package b.b.m.g;

import android.content.Context;
import android.content.pm.PackageManager;
import com.byd.mvp.R$style;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: ThemeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b.b.m.e.b f2181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2182b;

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getResources().getResourceEntryName(context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.theme);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int b(CarModel$Brand carModel$Brand) {
        b.b.m.e.b bVar = f2181a;
        if (bVar == null) {
            return -1;
        }
        return bVar.b(carModel$Brand);
    }

    public static int c() {
        b.b.m.e.b bVar = f2181a;
        if (bVar == null) {
            return R$style.dialog_appCompat;
        }
        int iD = bVar.d(AppConfig.a());
        return iD < 0 ? R$style.dialog_appCompat : iD;
    }

    public static int d() {
        b.b.m.e.b bVar = f2181a;
        if (bVar == null) {
            return R$style.dialog;
        }
        int iC = bVar.c(AppConfig.a());
        return iC < 0 ? R$style.dialog : iC;
    }

    public static int e(CarModel$Brand carModel$Brand) {
        b.b.m.e.b bVar = f2181a;
        if (bVar == null) {
            return -1;
        }
        return bVar.a(carModel$Brand);
    }

    public static final boolean f() {
        return f2181a != null;
    }

    public static final void g(b.b.m.e.b bVar) {
        f2181a = bVar;
    }

    public static void h(Context context) {
        if (context == null || !(context instanceof DialogActivity)) {
            return;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        int iB = b(carModel$BrandA);
        b.b.m.c.a.a("ThemeUtil", "--setTheme--brand: " + carModel$BrandA + ", themeId: " + iB);
        if (iB > 0) {
            b.b.m.c.a.g("ThemeUtil", "--setTheme--do set theme. themeName: " + f2182b + ", " + context.getClass().getName());
            context.setTheme(iB);
        }
    }

    public static void i(Context context) {
        if (context == null) {
            return;
        }
        if (context instanceof DialogActivity) {
            h(context);
            return;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        int iE = e(carModel$BrandA);
        b.b.m.c.a.a("ThemeUtil", "--setTheme--brand: " + carModel$BrandA + ", themeId: " + iE);
        if (iE <= 0) {
            return;
        }
        f2182b = a(context);
        b.b.m.c.a.g("ThemeUtil", "--setTheme--do set theme. themeName: " + f2182b + ", " + context.getClass().getName());
        context.setTheme(iE);
    }
}
