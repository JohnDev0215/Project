package m4;

import android.content.Context;
import android.content.pm.PackageManager;
import com.byd.mvp.R$style;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: ThemeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static z3.b f6378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f6379b;

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getResources().getResourceEntryName(context.getPackageManager().getPackageInfo(context.getPackageName(), 128).applicationInfo.theme);
        } catch (PackageManager.NameNotFoundException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public static int b() {
        z3.b bVar = f6378a;
        if (bVar == null) {
            return R$style.dialog_appCompat;
        }
        int dialogAppCompatThemeId = bVar.getDialogAppCompatThemeId(AppConfig.a());
        return dialogAppCompatThemeId < 0 ? R$style.dialog_appCompat : dialogAppCompatThemeId;
    }

    public static int c(CarModel$Brand carModel$Brand) {
        z3.b bVar = f6378a;
        if (bVar == null) {
            return -1;
        }
        return bVar.getThemeId(carModel$Brand);
    }

    public static final boolean d() {
        return f6378a != null;
    }

    public static final void e(z3.b bVar) {
        f6378a = bVar;
    }

    public static void f(Context context) {
        if (context == null || (context instanceof DialogActivity)) {
            return;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        int iC = c(carModel$BrandA);
        x3.a.a("ThemeUtil", "--setTheme--brand: " + carModel$BrandA + ", themeId: " + iC);
        if (iC <= 0) {
            return;
        }
        f6379b = a(context);
        x3.a.e("ThemeUtil", "--setTheme--Have no activity, do not set theme. themeName: " + f6379b + ", " + context.getClass().getName());
    }
}
