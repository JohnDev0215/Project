package com.byd.systemsettings.system.utils;

import com.byd.mvp.common.CarModel$Brand;
import com.byd.systemsettings.R;
import x3.a;
import z3.b;

/* JADX INFO: loaded from: classes2.dex */
public class BydThemeConfig implements b {
    private static final String TAG = "BydThemeConfig";

    /* JADX INFO: renamed from: com.byd.systemsettings.system.utils.BydThemeConfig$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$mvp$common$CarModel$Brand;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            $SwitchMap$com$byd$mvp$common$CarModel$Brand = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$mvp$common$CarModel$Brand[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int getDialogActivityThemeId(CarModel$Brand carModel$Brand) {
        if (carModel$Brand == null) {
            return -1;
        }
        int i8 = AnonymousClass1.$SwitchMap$com$byd$mvp$common$CarModel$Brand[carModel$Brand.ordinal()];
        if (i8 == 1) {
            return R.style.dialog_activity_statusbar;
        }
        if (i8 != 2) {
            return -1;
        }
        return R.style.dialog_activity_statusbar_denza;
    }

    @Override // z3.b
    public int getDialogAppCompatThemeId(CarModel$Brand carModel$Brand) {
        a.c(TAG, "---getDialogAppCompatThemeId---brand: " + carModel$Brand);
        if (carModel$Brand == null) {
            return R.style.dialog_appCompat_dynasty;
        }
        int i8 = AnonymousClass1.$SwitchMap$com$byd$mvp$common$CarModel$Brand[carModel$Brand.ordinal()];
        return i8 != 1 ? i8 != 2 ? R.style.dialog_appCompat_dynasty : R.style.dialog_appCompat_denza : R.style.dialog_appCompat_dynasty;
    }

    public int getDialogThemeId(CarModel$Brand carModel$Brand) {
        a.c(TAG, "---getDialogThemeId---brand: " + carModel$Brand);
        if (carModel$Brand == null) {
            return R.style.dialog_def_dynasty;
        }
        int i8 = AnonymousClass1.$SwitchMap$com$byd$mvp$common$CarModel$Brand[carModel$Brand.ordinal()];
        return i8 != 1 ? i8 != 2 ? R.style.dialog_def_dynasty : R.style.dialog_def_denza : R.style.dialog_def_dynasty;
    }

    @Override // z3.b
    public int getThemeId(CarModel$Brand carModel$Brand) {
        if (carModel$Brand == null) {
            return -1;
        }
        int i8 = AnonymousClass1.$SwitchMap$com$byd$mvp$common$CarModel$Brand[carModel$Brand.ordinal()];
        if (i8 == 1) {
            return R.style.Theme_systemsettings_final;
        }
        if (i8 != 2) {
            return -1;
        }
        return R.style.Theme_systemsettings_final_denza;
    }
}
