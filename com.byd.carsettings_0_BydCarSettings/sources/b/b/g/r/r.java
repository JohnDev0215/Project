package b.b.g.r;

import android.text.TextUtils;
import com.byd.carsettings.adapter.CarHealthyAdapter;
import com.byd.carsettings.adapter.DiLinkPagerAdapter;
import com.byd.carsettings.adapter.MainFragmentPageAdapter;
import com.byd.carsettings.adapter.NewEnergyAdapter;
import com.byd.carsettings.adapter.carsettings.BydCsFragmentAdapter;
import com.byd.carsettings.ui.fragment.dipilot.ActiveSafeFragment;
import com.byd.carsettings.ui.fragment.dipilot.DriverAssistFragment;
import com.byd.carsettings.ui.fragment.dipilot.ParkAssistFragment;
import com.byd.dipilot.BaseFragment;
import com.byd.mvp.mvp.XFragment;

/* JADX INFO: compiled from: PageKeysUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile String f1829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile String f1830b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile String f1831c;

    public static void a(int... iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        int i = iArr[0];
        String strE = b.b.m.e.i.a.e(i);
        b.b.m.c.a.d("PageKeysUtil", "---checkPageKeyRegistered---moduleIndex: " + i + ", moduleKey: " + strE);
        if (strE == null) {
            j();
        }
        if (iArr.length < 2) {
            return;
        }
        String strE2 = b.b.m.e.i.a.e(i, iArr[1]);
        b.b.m.c.a.d("PageKeysUtil", "---checkPageKeyRegistered---moduleIndex: " + i + ", pageKey: " + strE2);
        if (strE2 != null) {
            return;
        }
        if (i == 1) {
            h();
            return;
        }
        if (i == 2) {
            i();
            return;
        }
        if (i == 3) {
            k();
        } else if (i == 4) {
            g();
        } else {
            if (i != 5) {
                return;
            }
            f();
        }
    }

    public static void b() {
        b.b.m.c.a.a("PageKeysUtil", "----doRegisterPageKeys------");
        j();
        h();
        i();
        k();
        g();
        f();
    }

    public static Class<? extends BaseFragment> c(int i) {
        return i == 1 ? ActiveSafeFragment.class : i == 2 ? ParkAssistFragment.class : DriverAssistFragment.class;
    }

    public static String d(int i) {
        Class<? extends XFragment> clsF = MainFragmentPageAdapter.f(i);
        if (clsF != null) {
            return clsF.getName();
        }
        return null;
    }

    public static String e(int i, int i2) {
        Class clsF = i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : CarHealthyAdapter.f(i2) : BydCsFragmentAdapter.f(i2) : NewEnergyAdapter.f(i2) : c(i2) : DiLinkPagerAdapter.f(i2);
        if (clsF != null) {
            return clsF.getName();
        }
        return null;
    }

    public static void f() {
        b.b.m.c.a.a("PageKeysUtil", "----registerCarHealthyPageKeys------");
        for (int i = 0; i < 3; i++) {
            b.b.m.e.i.a.l(CarHealthyAdapter.f(i), 5, i);
        }
    }

    public static void g() {
        b.b.m.c.a.a("PageKeysUtil", "----registerCarSettingPageKeys------");
        for (int i = 0; i < 14; i++) {
            if (b.b.g.j.a.o(4, i)) {
                b.b.g.j.a.d(4, i);
                b.b.m.e.i.a.l(BydCsFragmentAdapter.f(i), 4, i);
            }
        }
    }

    public static void h() {
        b.b.m.c.a.a("PageKeysUtil", "----registerDiLinkPageKeys------");
        for (int i = 0; i < DiLinkPagerAdapter.g(); i++) {
            b.b.m.e.i.a.l(DiLinkPagerAdapter.f(i), 1, i);
        }
    }

    public static void i() {
        b.b.m.c.a.a("PageKeysUtil", "----registerDiPilotPageKeys------");
        for (int i = 0; i < 3; i++) {
            b.b.m.e.i.a.l(c(i), 2, i);
        }
    }

    public static void j() {
        b.b.m.c.a.a("PageKeysUtil", "----registerModuleKeys------");
        for (int i = 0; i < 6; i++) {
            b.b.m.e.i.a.l(MainFragmentPageAdapter.f(i), i);
        }
    }

    public static void k() {
        b.b.m.c.a.a("PageKeysUtil", "----registerNewEnergyPageKeys------");
        for (int i = 0; i < NewEnergyAdapter.f2607b; i++) {
            b.b.m.e.i.a.l(NewEnergyAdapter.f(i), 3, i);
        }
    }

    public static void l() {
        if (b.b.m.e.i.a.j()) {
            return;
        }
        b();
    }

    public static void m() {
        b.b.m.c.a.d("PageKeysUtil", "---resetPageValue---KEY1: " + f1829a);
        b.b.m.c.a.d("PageKeysUtil", "---resetPageValue---KEY2: " + f1830b);
        b.b.m.c.a.d("PageKeysUtil", "---resetPageValue---KEY3: " + f1831c);
        if (!TextUtils.isEmpty(f1829a)) {
            b.b.m.e.i.a.k(f1829a, 0);
        }
        if (!TextUtils.isEmpty(f1830b)) {
            b.b.m.e.i.a.k(f1830b, 0);
        }
        if (!TextUtils.isEmpty(f1831c)) {
            b.b.m.e.i.a.k(f1831c, 0);
        }
        for (int i = 0; i < 6; i++) {
            String strE = b.b.m.e.i.a.e(i);
            b.b.m.c.a.d("PageKeysUtil", "---resetPageValue---i: " + i + ", key: " + strE);
            if (!TextUtils.isEmpty(strE)) {
                b.b.m.e.i.a.k(strE, 0);
            }
        }
    }
}
