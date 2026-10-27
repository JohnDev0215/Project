package b.b.g.r;

import android.content.Context;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: CarIdentifier.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile int f1815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile int f1816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static CarModel$Brand f1817c = CarModel$Brand.DYNASTY;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static b.b.a.d.b f1818d = null;

    public static void a() {
        b.b.m.c.a.d("CarIdentifier", "---identify---carModelCode: " + f1815a);
    }

    public static void b() {
        int i = f1816b;
        if (i == 0) {
            f1817c = CarModel$Brand.DYNASTY;
        } else if (i == 1) {
            f1817c = CarModel$Brand.DENZA;
        } else if (i != 2) {
            f1817c = CarModel$Brand.DYNASTY;
        } else {
            f1817c = CarModel$Brand.OCEAN;
        }
        b.b.m.c.a.d("CarIdentifier", "---initUIStyle---UIStyle: " + f1816b);
    }

    public static void c(Context context) {
        b.b.a.d.b bVarG;
        if (context == null || (bVarG = b.b.a.i.a.g(context.getApplicationContext())) == null || bVarG.equals(f1818d)) {
            return;
        }
        b.b.m.c.a.d("CarIdentifier", "---readVehicleConfig---config: " + bVarG);
        f1818d = bVarG;
        f1815a = bVarG.a();
        f1816b = bVarG.b();
        boolean zC = bVarG.c();
        a();
        b();
        AppConfig.e(f1817c);
        AppConfig.g(zC);
        AppConfig.f(f1815a);
        b.b.m.c.a.d("CarIdentifier", "---readVehicleConfig---Brand: " + f1817c + ", RightRudder: " + zC);
    }
}
