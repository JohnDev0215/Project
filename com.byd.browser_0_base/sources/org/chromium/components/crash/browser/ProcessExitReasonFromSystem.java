package org.chromium.components.crash.browser;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.os.Build;
import defpackage.oj2;
import defpackage.xz;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessExitReasonFromSystem {
    public static int a(int i) {
        if (Build.VERSION.SDK_INT < 30) {
            return -1;
        }
        List historicalProcessExitReasons = ((ActivityManager) xz.a.getSystemService("activity")).getHistoricalProcessExitReasons(null, i, 1);
        if (historicalProcessExitReasons.isEmpty() || historicalProcessExitReasons.get(0) == null || ((ApplicationExitInfo) historicalProcessExitReasons.get(0)).getPid() != i) {
            return -1;
        }
        return ((ApplicationExitInfo) historicalProcessExitReasons.get(0)).getReason();
    }

    public static void b(int i, String str) {
        int i2;
        switch (i) {
            case 0:
                i2 = 11;
                break;
            case 1:
                i2 = 5;
                break;
            case 2:
                i2 = 10;
                break;
            case 3:
                i2 = 7;
                break;
            case 4:
                i2 = 1;
                break;
            case 5:
                i2 = 2;
                break;
            case 6:
                i2 = 0;
                break;
            case 7:
                i2 = 6;
                break;
            case 8:
                i2 = 9;
                break;
            case 9:
                i2 = 4;
                break;
            case 10:
                i2 = 12;
                break;
            case 11:
                i2 = 13;
                break;
            case 12:
                i2 = 3;
                break;
            case 13:
                i2 = 8;
                break;
            default:
                return;
        }
        oj2.U(i2, 14, str);
    }

    @CalledByNative
    public static void recordExitReasonToUma(int i, String str) {
        b(a(i), str);
    }
}
