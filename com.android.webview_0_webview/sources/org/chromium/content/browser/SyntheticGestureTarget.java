package org.chromium.content.browser;

import android.view.MotionEvent;
import android.view.View;
import defpackage.C0571bs;
import defpackage.C1685vz;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SyntheticGestureTarget {
    public final C1685vz a;

    public SyntheticGestureTarget(View view) {
        this.a = new C1685vz(view);
    }

    public static SyntheticGestureTarget create(View view) {
        return new SyntheticGestureTarget(view);
    }

    private void inject(int i, int i2, long j) {
        C1685vz c1685vz = this.a;
        Objects.requireNonNull(c1685vz);
        switch (i) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                c1685vz.d = j;
                MotionEvent motionEventObtain = MotionEvent.obtain(j, j, 0, 1, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                c1685vz.c.dispatchTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
                if (i2 > 1) {
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(c1685vz.d, j, 261, i2, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    c1685vz.c.dispatchTouchEvent(motionEventObtain2);
                    motionEventObtain2.recycle();
                }
                break;
            case 1:
                MotionEvent motionEventObtain3 = MotionEvent.obtain(c1685vz.d, j, 2, i2, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                c1685vz.c.dispatchTouchEvent(motionEventObtain3);
                motionEventObtain3.recycle();
                break;
            case 2:
                MotionEvent motionEventObtain4 = MotionEvent.obtain(c1685vz.d, j, 3, 1, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                c1685vz.c.dispatchTouchEvent(motionEventObtain4);
                motionEventObtain4.recycle();
                break;
            case 3:
                C1685vz c1685vz2 = c1685vz;
                if (i2 > 1) {
                    MotionEvent motionEventObtain5 = MotionEvent.obtain(c1685vz2.d, j, 262, i2, c1685vz2.a, c1685vz2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    c1685vz2 = c1685vz2;
                    c1685vz2.c.dispatchTouchEvent(motionEventObtain5);
                    motionEventObtain5.recycle();
                }
                MotionEvent motionEventObtain6 = MotionEvent.obtain(c1685vz2.d, j, 1, 1, c1685vz2.a, c1685vz2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                c1685vz2.c.dispatchTouchEvent(motionEventObtain6);
                motionEventObtain6.recycle();
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                MotionEvent motionEventObtain7 = MotionEvent.obtain(c1685vz.d, j, 8, i2, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                c1685vz.c.dispatchGenericMotionEvent(motionEventObtain7);
                motionEventObtain7.recycle();
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
            case 7:
                MotionEvent motionEventObtain8 = MotionEvent.obtain(c1685vz.d, j, 7 == i ? 7 : 6 == i ? 10 : 9, i2, c1685vz.a, c1685vz.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                c1685vz.c.dispatchGenericMotionEvent(motionEventObtain8);
                motionEventObtain8.recycle();
                break;
        }
    }

    private void setPointer(int i, int i2, int i3, int i4) {
        this.a.a(i, i2, i3, i4);
    }

    private void setScrollDeltas(int i, int i2, int i3, int i4) {
        C1685vz c1685vz = this.a;
        c1685vz.a(0, i, i2, 0);
        c1685vz.b[0].setAxisValue(10, i3);
        c1685vz.b[0].setAxisValue(9, i4);
    }
}
