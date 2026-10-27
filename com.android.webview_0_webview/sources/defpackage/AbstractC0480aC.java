package defpackage;

import android.os.Build;
import android.widget.DatePicker;

/* JADX INFO: renamed from: aC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0480aC {
    public static void a(DatePicker datePicker, DatePicker.OnDateChangedListener onDateChangedListener, int i, int i2, int i3, long j, long j2) {
        ZB zbA = ZB.a(i, i2, i3);
        ZB zbB = ZB.b(j);
        ZB zbB2 = ZB.b(j2);
        if (zbB2.a < zbB.a) {
            zbB2 = zbB;
        }
        long j3 = zbA.a;
        if (j3 < zbB.a) {
            zbA = zbB;
        } else if (j3 > zbB2.a) {
            zbA = zbB2;
        }
        long j4 = zbA.a;
        long jMax = zbB.a;
        long jMin = zbB2.a;
        int i4 = Build.VERSION.SDK_INT;
        if (i4 == 21 || i4 == 22) {
            jMax = Math.max(jMax, j4 - 157680000000000L);
            jMin = Math.min(jMin, j4 + 157680000000000L);
        }
        if (jMax > datePicker.getMaxDate()) {
            datePicker.setMaxDate(jMin);
            datePicker.setMinDate(jMax);
        } else {
            datePicker.setMinDate(jMax);
            datePicker.setMaxDate(jMin);
        }
        datePicker.init(zbA.b, zbA.c, zbA.d, onDateChangedListener);
    }
}
