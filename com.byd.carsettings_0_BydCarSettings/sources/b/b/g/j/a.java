package b.b.g.j;

import android.content.Context;
import b.b.g.j.b;
import b.b.g.r.r;
import com.byd.base.hilt.Router;
import com.byd.carsettings.common.CountryVersion;
import com.byd.carsettings.common.DriveType;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: HideStateChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CountryVersion f805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CarModel$Brand f806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DriveType f807c;

    /* JADX INFO: compiled from: HideStateChecker.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f808a = new a();

        public static a a() {
            return f808a;
        }
    }

    public static int b(int i, int i2, int i3) {
        if (i3 <= 0 || !l(i, i2, i3)) {
            return 0;
        }
        return i3 - h(i, i2, i3);
    }

    public static int c(int i) {
        if (i <= 0 || !n(i)) {
            return 0;
        }
        return i - i(i);
    }

    public static int d(int i, int i2) {
        int iG;
        if (i2 <= 0 || !o(i, i2)) {
            return 0;
        }
        return (i != 2 || (iG = Router.b().f().g()) >= 3) ? i2 - j(i, i2) : i2 - (3 - iG);
    }

    public static void e(Context context, int... iArr) {
        b.C0048b.a().e(context);
        r.a(iArr);
    }

    public static void f(Context context) {
        b.C0048b.a().b(context);
    }

    public static int g(int i, int i2, int i3, int i4) {
        if (i4 <= 0 || !m(i, i2, i3, i4)) {
            return 0;
        }
        return i4 - k(i, i2, i3, i4);
    }

    public static int h(int i, int i2, int i3) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return 0;
        }
        return b.C0048b.a().d(new int[]{i, i2, i3});
    }

    public static int i(int i) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return 0;
        }
        return b.C0048b.a().d(new int[]{i});
    }

    public static int j(int i, int i2) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return 0;
        }
        return b.C0048b.a().d(new int[]{i, i2});
    }

    public static int k(int i, int i2, int i3, int i4) {
        return b.C0048b.a().d(new int[]{i, i2, i3, i4});
    }

    public static boolean l(int i, int i2, int i3) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return true;
        }
        return !b.C0048b.a().h(i, i2, i3, -1);
    }

    public static boolean m(int i, int i2, int i3, int i4) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return true;
        }
        return !b.C0048b.a().h(i, i2, i3, i4);
    }

    public static boolean n(int i) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return true;
        }
        return !b.C0048b.a().h(i, -1, -1, -1);
    }

    public static boolean o(int i, int i2) {
        if (b.a().f805a != CountryVersion.OVERSEAS) {
            return true;
        }
        return !b.C0048b.a().g(i, i2);
    }

    public void a(Context context) {
        b.C0048b.a().e(context);
        r.l();
    }

    public void p(Context context) {
        this.f806b = AppConfig.a();
        b.b.m.c.a.d("HideStateChecker", "--initRights--brand: " + this.f806b);
        q(CountryVersion.OVERSEAS, DriveType.DM);
        a(context);
    }

    public void q(CountryVersion countryVersion, DriveType driveType) {
        this.f805a = countryVersion;
        this.f807c = driveType;
    }

    public a() {
        this.f805a = CountryVersion.OVERSEAS;
    }
}
