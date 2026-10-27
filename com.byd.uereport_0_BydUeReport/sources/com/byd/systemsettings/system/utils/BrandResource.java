package com.byd.systemsettings.system.utils;

import android.widget.TextView;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.R;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BrandResource {
    private static final String TAG = "BrandResource";

    /* JADX INFO: renamed from: com.byd.systemsettings.system.utils.BrandResource$1, reason: invalid class name */
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

    public static int getBtPairCodeTextColor() {
        if (AppConfig.a() == null) {
            return R.color.text_color_btn_blue;
        }
        CarModel$Brand carModel$BrandA = AppConfig.a();
        a.a(TAG, "---getBtPairCodeTextColor---brand: " + carModel$BrandA);
        int i8 = AnonymousClass1.$SwitchMap$com$byd$mvp$common$CarModel$Brand[carModel$BrandA.ordinal()];
        return i8 != 1 ? i8 != 2 ? R.color.text_color_btn_blue : R.color.text_color_main_dilink_pagers_denza : R.color.text_color_btn_blue;
    }

    public static void setTextColor(TextView... textViewArr) {
        if (textViewArr == null || textViewArr.length <= 0) {
            return;
        }
        a.a(TAG, "---setTextColor---arr.length: " + textViewArr.length);
        int color = textViewArr[0] != null ? textViewArr[0].getContext().getResources().getColor(getBtPairCodeTextColor()) : 0;
        for (TextView textView : textViewArr) {
            if (textView != null && color != 0) {
                textView.setTextColor(color);
            }
        }
    }
}
