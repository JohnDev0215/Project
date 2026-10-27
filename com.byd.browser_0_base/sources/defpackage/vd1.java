package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureSession;
import androidx.transition.Transition;
import java.util.ArrayList;
import org.chromium.components.browser_ui.photo_picker.h;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattDescriptorWrapper;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: MediaRouterClient.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class vd1 {

    @SuppressLint({"StaticFieldLeak"})
    public static vd1 e;
    public static ux1 f;
    public static yx1 g;
    public static ay1 h;
    public static m12 i;
    public static vd1 j;

    public static int H(int i2) {
        if (i2 == 1) {
            return 2;
        }
        if (i2 == 2) {
            return 15;
        }
        if (i2 == 21) {
            return 3;
        }
        if (i2 == 22) {
            return 14;
        }
        if (i2 == 42) {
            return 4;
        }
        if (i2 == 43) {
            return 5;
        }
        if (i2 == 83) {
            return 10;
        }
        if (i2 == 91) {
            return 12;
        }
        if (i2 == 110) {
            return 27;
        }
        if (i2 == 33656) {
            return 0;
        }
        if (i2 == 71300) {
            return 1;
        }
        switch (i2) {
            case 53:
                return 8;
            case 54:
                return 9;
            case 55:
                return 13;
            case 56:
                return 18;
            case 57:
                return 25;
            default:
                switch (i2) {
                    case 77:
                        return 6;
                    case 78:
                        return 7;
                    case 79:
                        return 11;
                    default:
                        switch (i2) {
                            case 102:
                                return 19;
                            case 103:
                                return 20;
                            case 104:
                                return 21;
                            case 105:
                                return 22;
                            case 106:
                                return 23;
                            case 107:
                                return 24;
                            default:
                                return -1;
                        }
                }
        }
    }

    public static final int b(int i2) {
        if (Integer.toHexString(i2).startsWith("1")) {
            return 0;
        }
        return i2;
    }

    public static vd1 f() {
        if (i == null) {
            i = new m12();
        }
        return i;
    }

    public abstract void A(ph1 ph1Var);

    public abstract void B(int i2);

    public abstract void C(int i2, fv2[] fv2VarArr, float f2);

    public abstract void E();

    public abstract void F(int i2, fv2[] fv2VarArr, float f2, float f3, float f4, float f5, boolean z, boolean z2, float f6);

    public abstract h G(WindowAndroid windowAndroid, y02 y02Var, boolean z, ArrayList arrayList);

    public abstract void I(byte[] bArr, int i2, int i3);

    public float c(fv2[] fv2VarArr, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z) {
        float fI;
        float f9;
        float f10;
        int i2 = 0;
        if (LocalizationUtils.isLayoutRtl()) {
            boolean zA = bq.e0.a();
            float fMin = f5 - f4;
            int length = fv2VarArr.length;
            while (i2 < length) {
                fv2 fv2Var = fv2VarArr[i2];
                fMin = Math.min((zA && z) ? fv2Var.o : fv2Var.s, fMin);
                i2++;
            }
            return (Math.max(fMin, f3) - f7) - f6;
        }
        boolean zA2 = bq.e0.a();
        int length2 = fv2VarArr.length;
        while (i2 < length2) {
            fv2 fv2Var2 = fv2VarArr[i2];
            if (zA2 && z) {
                f10 = fv2Var2.o;
                fI = 1.0f;
                f9 = f8;
            } else {
                fI = fv2Var2.i();
                f9 = fv2Var2.u;
                f10 = fv2Var2.s;
            }
            f3 = Math.max(((f9 - f2) * fI) + f10, f3);
            i2++;
        }
        return Math.min(f3 + f2, f5 - f4) + f7;
    }

    public abstract ContentCaptureSession d(ContentCaptureSession contentCaptureSession, String str, String str2);

    public abstract boolean e();

    public abstract long g(ViewGroup viewGroup, Transition transition, df3 df3Var, df3 df3Var2);

    public abstract int h(WebContents webContents);

    public abstract void i(ContentCaptureSession contentCaptureSession, String str);

    public abstract void j();

    public abstract void k(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper);

    public abstract void l(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i2);

    public abstract void m(Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i2);

    public abstract void n(int i2, int i3);

    public abstract void o(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i2);

    public abstract void p(Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i2);

    public abstract void q(String str, Bundle bundle);

    public abstract View r(int i2);

    public abstract void s(int i2);

    public abstract void w(Typeface typeface, boolean z);

    public abstract boolean x();

    public abstract void y(int i2, int i3);

    public abstract void z(int i2);
}
