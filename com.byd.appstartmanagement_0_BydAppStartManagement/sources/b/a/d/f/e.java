package b.a.d.f;

import android.content.Context;
import android.content.pm.PackageManager;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: ThemeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b.a.d.d.a f361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f362b;

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getResources().getResourceEntryName(context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.theme);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int b(CarModel$Brand carModel$Brand) {
        b.a.d.d.a aVar = f361a;
        if (aVar == null) {
            return -1;
        }
        return aVar.b(carModel$Brand);
    }

    public static int c(CarModel$Brand carModel$Brand) {
        b.a.d.d.a aVar = f361a;
        if (aVar == null) {
            return -1;
        }
        return aVar.a(carModel$Brand);
    }

    public static void d(Context context) {
        if (context == null || !(context instanceof DialogActivity)) {
            return;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        int iB = b(carModel$BrandA);
        b.a.d.b.a.a("ThemeUtil", "--setTheme--brand: " + carModel$BrandA + ", themeId: " + iB);
        if (iB > 0) {
            b.a.d.b.a.f("ThemeUtil", "--setTheme--do set theme. themeName: " + f362b + ", " + context.getClass().getName());
            context.setTheme(iB);
        }
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        if (context instanceof DialogActivity) {
            d(context);
            return;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        int iC = c(carModel$BrandA);
        b.a.d.b.a.a("ThemeUtil", "--setTheme--brand: " + carModel$BrandA + ", themeId: " + iC);
        if (iC <= 0) {
            return;
        }
        f362b = a(context);
        b.a.d.b.a.f("ThemeUtil", "--setTheme--do set theme. themeName: " + f362b + ", " + context.getClass().getName());
        context.setTheme(iC);
    }
}
