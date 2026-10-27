package org.chromium.content.browser;

import android.view.MotionEvent;
import android.view.View;
import defpackage.cj1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SyntheticGestureTarget {
    public final cj1 a;

    public SyntheticGestureTarget(View view) {
        this.a = new cj1(view);
    }

    @CalledByNative
    public static SyntheticGestureTarget create(View view) {
        return new SyntheticGestureTarget(view);
    }

    @CalledByNative
    public final void inject(int i, int i2, long j) {
        cj1 cj1Var;
        View view;
        cj1 cj1Var2 = this.a;
        View view2 = cj1Var2.c;
        switch (i) {
            case 0:
                cj1Var2.d = j;
                MotionEvent motionEventObtain = MotionEvent.obtain(j, j, 0, 1, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                view2.dispatchTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
                if (i2 > 1) {
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(cj1Var2.d, j, 261, i2, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    view2.dispatchTouchEvent(motionEventObtain2);
                    motionEventObtain2.recycle();
                }
                break;
            case 1:
                MotionEvent motionEventObtain3 = MotionEvent.obtain(cj1Var2.d, j, 2, i2, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                view2.dispatchTouchEvent(motionEventObtain3);
                motionEventObtain3.recycle();
                break;
            case 2:
                MotionEvent motionEventObtain4 = MotionEvent.obtain(cj1Var2.d, j, 3, 1, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                view2.dispatchTouchEvent(motionEventObtain4);
                motionEventObtain4.recycle();
                break;
            case 3:
                if (i2 > 1) {
                    cj1Var = cj1Var2;
                    MotionEvent motionEventObtain5 = MotionEvent.obtain(cj1Var2.d, j, 262, i2, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    view = view2;
                    view.dispatchTouchEvent(motionEventObtain5);
                    motionEventObtain5.recycle();
                } else {
                    cj1Var = cj1Var2;
                    view = view2;
                }
                cj1 cj1Var3 = cj1Var;
                MotionEvent motionEventObtain6 = MotionEvent.obtain(cj1Var3.d, j, 1, 1, cj1Var3.a, cj1Var3.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                view.dispatchTouchEvent(motionEventObtain6);
                motionEventObtain6.recycle();
                break;
            case 4:
                MotionEvent motionEventObtain7 = MotionEvent.obtain(cj1Var2.d, j, 8, i2, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                view2.dispatchGenericMotionEvent(motionEventObtain7);
                motionEventObtain7.recycle();
                break;
            case 5:
            case 6:
            case 7:
                MotionEvent motionEventObtain8 = MotionEvent.obtain(cj1Var2.d, j, 7 == i ? 7 : 6 == i ? 10 : 9, i2, cj1Var2.a, cj1Var2.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                view2.dispatchGenericMotionEvent(motionEventObtain8);
                motionEventObtain8.recycle();
                break;
        }
    }

    @CalledByNative
    public final void setPointer(int i, float f, float f2, int i2) {
        this.a.a(i, f, f2, i2);
    }

    @CalledByNative
    public final void setScrollDeltas(float f, float f2, float f3, float f4) {
        cj1 cj1Var = this.a;
        cj1Var.a(0, f, f2, 0);
        MotionEvent.PointerCoords[] pointerCoordsArr = cj1Var.b;
        pointerCoordsArr[0].setAxisValue(10, f3);
        pointerCoordsArr[0].setAxisValue(9, f4);
    }
}
