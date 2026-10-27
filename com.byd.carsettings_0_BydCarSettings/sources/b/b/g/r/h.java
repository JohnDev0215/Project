package b.b.g.r;

import com.byd.carsettings.R;
import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: compiled from: BydThemeConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public class h implements b.b.m.e.b {

    /* JADX INFO: compiled from: BydThemeConfig.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1807a;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f1807a = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1807a[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // b.b.m.e.b
    public int a(CarModel$Brand carModel$Brand) {
        if (carModel$Brand == null) {
            return -1;
        }
        int i = a.f1807a[carModel$Brand.ordinal()];
        if (i == 1) {
            return R.style.Theme_CarSettings_final;
        }
        if (i != 2) {
            return -1;
        }
        return R.style.Theme_CarSettings_final_denza;
    }

    @Override // b.b.m.e.b
    public int b(CarModel$Brand carModel$Brand) {
        if (carModel$Brand == null) {
            return -1;
        }
        int i = a.f1807a[carModel$Brand.ordinal()];
        if (i == 1) {
            return R.style.dialog_activity_statusbar;
        }
        if (i != 2) {
            return -1;
        }
        return R.style.dialog_activity_statusbar_denza;
    }

    @Override // b.b.m.e.b
    public int c(CarModel$Brand carModel$Brand) {
        return (carModel$Brand != null && a.f1807a[carModel$Brand.ordinal()] == 2) ? R.style.dialog_def_denza : R.style.dialog_def_dynasty;
    }

    @Override // b.b.m.e.b
    public int d(CarModel$Brand carModel$Brand) {
        return (carModel$Brand != null && a.f1807a[carModel$Brand.ordinal()] == 2) ? R.style.dialog_appCompat_denza : R.style.dialog_appCompat_dynasty;
    }
}
