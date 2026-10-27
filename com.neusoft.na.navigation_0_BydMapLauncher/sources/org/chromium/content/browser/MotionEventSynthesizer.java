package org.chromium.content.browser;

import android.view.MotionEvent;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class MotionEventSynthesizer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ACTION_CANCEL = 2;
    private static final int ACTION_END = 3;
    private static final int ACTION_MOVE = 1;
    private static final int ACTION_SCROLL = 4;
    private static final int ACTION_START = 0;
    private static final int MAX_NUM_POINTERS = 16;
    private final ContentViewCore mContentViewCore;
    private long mDownTimeInMs;
    private final MotionEvent.PointerProperties[] mPointerProperties = new MotionEvent.PointerProperties[16];
    private final MotionEvent.PointerCoords[] mPointerCoords = new MotionEvent.PointerCoords[16];

    MotionEventSynthesizer(ContentViewCore contentViewCore) {
        this.mContentViewCore = contentViewCore;
    }

    void setPointer(int i, int i2, int i3, int i4) {
        float deviceScaleFactor = this.mContentViewCore.getRenderCoordinates().getDeviceScaleFactor();
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = i2 * deviceScaleFactor;
        pointerCoords.y = deviceScaleFactor * i3;
        pointerCoords.pressure = 1.0f;
        this.mPointerCoords[i] = pointerCoords;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = i4;
        this.mPointerProperties[i] = pointerProperties;
    }

    void setScrollDeltas(int i, int i2, int i3, int i4) {
        setPointer(0, i, i2, 0);
        float deviceScaleFactor = this.mContentViewCore.getRenderCoordinates().getDeviceScaleFactor();
        this.mPointerCoords[0].setAxisValue(10, i3 * deviceScaleFactor);
        this.mPointerCoords[0].setAxisValue(9, deviceScaleFactor * i4);
    }

    void inject(int i, int i2, long j) {
        if (i == 0) {
            this.mDownTimeInMs = j;
            MotionEvent motionEventObtain = MotionEvent.obtain(j, j, 0, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
            this.mContentViewCore.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            if (i2 > 1) {
                MotionEvent motionEventObtain2 = MotionEvent.obtain(this.mDownTimeInMs, j, 5, i2, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(motionEventObtain2);
                motionEventObtain2.recycle();
                return;
            }
            return;
        }
        if (i == 1) {
            MotionEvent motionEventObtain3 = MotionEvent.obtain(this.mDownTimeInMs, j, 2, i2, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
            this.mContentViewCore.onTouchEvent(motionEventObtain3);
            motionEventObtain3.recycle();
            return;
        }
        if (i == 2) {
            MotionEvent motionEventObtain4 = MotionEvent.obtain(this.mDownTimeInMs, j, 3, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
            this.mContentViewCore.onTouchEvent(motionEventObtain4);
            motionEventObtain4.recycle();
        } else {
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                MotionEvent motionEventObtain5 = MotionEvent.obtain(this.mDownTimeInMs, j, 8, i2, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                this.mContentViewCore.onGenericMotionEvent(motionEventObtain5);
                motionEventObtain5.recycle();
                return;
            }
            if (i2 > 1) {
                MotionEvent motionEventObtain6 = MotionEvent.obtain(this.mDownTimeInMs, j, 6, i2, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(motionEventObtain6);
                motionEventObtain6.recycle();
            }
            MotionEvent motionEventObtain7 = MotionEvent.obtain(this.mDownTimeInMs, j, 1, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
            this.mContentViewCore.onTouchEvent(motionEventObtain7);
            motionEventObtain7.recycle();
        }
    }
}
