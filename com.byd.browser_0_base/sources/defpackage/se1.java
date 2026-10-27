package defpackage;

import android.app.ActivityManager;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.chrome.browser.photo_picker.DecoderService;
import org.chromium.components.payments.PaymentAppServiceBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class se1 implements nw2 {
    public final /* synthetic */ int e;

    public /* synthetic */ se1(int i) {
        this.e = i;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i;
        switch (this.e) {
            case 0:
                try {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(runningAppProcessInfo);
                    int i2 = runningAppProcessInfo.lastTrimLevel;
                    if (i2 >= 80 || i2 == 15) {
                        i = 2;
                    } else {
                        if (i2 < 40) {
                            return null;
                        }
                        i = 1;
                    }
                    return i;
                } catch (Exception unused) {
                    return null;
                }
            case 1:
            case 2:
            case 3:
                return null;
            case 4:
                return 0;
            case 5:
                Object obj = b62.d;
                return new Intent(xz.a, (Class<?>) DecoderService.class);
            case 6:
                return new PaymentAppServiceBridge();
            case 7:
                return oj2.r();
            case 8:
                return Boolean.FALSE;
            case 9:
                String strC = qv0.c();
                return Boolean.valueOf((TextUtils.isEmpty(strC) || bk3.k(strC)) ? false : true);
            case 10:
                String strC2 = qv0.c();
                return TextUtils.isEmpty(strC2) ? "chrome-native://newtab/" : strC2;
            case 11:
                return Float.valueOf(1.0f);
            default:
                return Float.valueOf(0.0f);
        }
    }
}
