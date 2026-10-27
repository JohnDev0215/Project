package com.google.android.projection.protocol;

import android.os.SystemClock;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.google.android.projection.proto.Protos;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InputSource implements CarServiceProvider, InputSourceCallbacks {
    private static final boolean DBG_TRACE = false;
    private static final String TAG = "AHU.INPUT";
    private OnInputFeedbackListener mFeedbackListener;
    private final InputInjector mInputInjector;
    private long mNativeInputSource;
    private boolean mOverrideMotionEvent;
    public static final int FEEDBACK_EVENT_SELECT = Protos.FeedbackEvent.FEEDBACK_SELECT.getNumber();
    public static final int FEEDBACK_EVENT_FOCUS_CHANGE = Protos.FeedbackEvent.FEEDBACK_FOCUS_CHANGE.getNumber();
    public static final int FEEDBACK_EVENT_DRAG_SELECT = Protos.FeedbackEvent.FEEDBACK_DRAG_SELECT.getNumber();
    public static final int FEEDBACK_EVENT_DRAG_START = Protos.FeedbackEvent.FEEDBACK_DRAG_START.getNumber();
    public static final int FEEDBACK_EVENT_DRAG_END = Protos.FeedbackEvent.FEEDBACK_DRAG_END.getNumber();
    public static final int TOUCHSCREEN_TYPE_CAPACITIVE = Protos.TouchScreenType.CAPACITIVE.getNumber();
    public static final int TOUCHSCREEN_TYPE_RESISTIVE = Protos.TouchScreenType.RESISTIVE.getNumber();

    public interface InputInjector {
        void onKeyEvent(KeyEvent keyEvent);

        void onMotionEvent(MotionEvent motionEvent);

        void onRelativeEvent(int i, int i2);
    }

    public interface OnInputFeedbackListener {
        void onInputFeedback(int i);
    }

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native void nativeRegisterFeedbackEvents(int[] iArr);

    private native void nativeRegisterKeyCodes(int[] iArr);

    private native void nativeRegisterTouchPad(int i, int i2, boolean z, int i3, int i4, boolean z2, boolean z3);

    private native void nativeRegisterTouchScreen(int i, int i2, int i3);

    private native int nativeSendAbsoluteEvent(long j, int i, int i2);

    private native int nativeSendKeyEvent(long j, int i, boolean z, int i2);

    private native int nativeSendKeyLongEvent(long j, int i, int i2);

    private native int nativeSendRelativeEvent(long j, int i, int i2);

    private native int nativeSendTouchEvent(long j, int[] iArr, int[] iArr2, int[] iArr3, int i, int i2);

    private native int nativeSendTouchPadEvent(long j, int[] iArr, int[] iArr2, int[] iArr3, int i, int i2);

    private native void nativeSetDisplayId(int i);

    private native void nativeShutdown();

    public InputSource(InputInjector injector) {
        this.mInputInjector = injector;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver) == 0) {
            return true;
        }
        return DBG_TRACE;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeInputSource;
    }

    private int translateKeycode(int keycode) {
        return keycode;
    }

    public void registerKeyCodes(int[] keycodes) {
        for (int i = 0; i < keycodes.length; i++) {
            keycodes[i] = translateKeycode(keycodes[i]);
        }
        nativeRegisterKeyCodes(keycodes);
    }

    public void registerTouchScreen(int width, int height, int type) {
        nativeRegisterTouchScreen(width, height, type);
    }

    public void registerTouchPad(int width, int height, boolean uiNavigation, int physicalWidth, int physicalHeight, boolean uiAbsolute, boolean tapAsSelect) {
        nativeRegisterTouchPad(width, height, uiNavigation, physicalWidth, physicalHeight, uiAbsolute, tapAsSelect);
    }

    public void registerFeedbackEvents(int[] feedbackEvents, OnInputFeedbackListener feedbackListener) {
        this.mFeedbackListener = feedbackListener;
        nativeRegisterFeedbackEvents(feedbackEvents);
    }

    public void sendKeyEvent(KeyEvent event) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        int keycode = translateKeycode(event.getKeyCode());
        boolean down = event.getAction() == 0 ? true : DBG_TRACE;
        nativeSendKeyEvent(timestamp, keycode, down, event.getMetaState());
    }

    public void sendKeyEvent(int keycode, boolean down) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        nativeSendKeyEvent(timestamp, keycode, down, -1);
    }

    public void sendKeyLongEvent(int keycode) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        nativeSendKeyLongEvent(timestamp, keycode, -1);
    }

    public void sendKeyLongEvent(KeyEvent event) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        int keycode = translateKeycode(event.getKeyCode());
        nativeSendKeyLongEvent(timestamp, keycode, event.getMetaState());
    }

    public synchronized void sendTouchEvent(MotionEvent event) {
        long timestamp;
        long timestamp2 = SystemClock.elapsedRealtimeNanos();
        if (!this.mOverrideMotionEvent) {
            timestamp = timestamp2;
        } else {
            long timestamp3 = event.getEventTime();
            this.mOverrideMotionEvent = DBG_TRACE;
            timestamp = timestamp3;
        }
        int action = event.getActionMasked();
        if (action == 0 || action == 5 || action == 2 || action == 6 || action == 1) {
            int pointerCount = event.getPointerCount();
            int[] x = new int[pointerCount];
            int[] y = new int[pointerCount];
            int[] pointerId = new int[pointerCount];
            for (int i = 0; i < pointerCount; i++) {
                x[i] = (int) event.getX(i);
                y[i] = (int) event.getY(i);
                pointerId[i] = event.getPointerId(i);
            }
            int actionIndex = event.getActionIndex();
            nativeSendTouchEvent(timestamp, pointerId, x, y, action, actionIndex);
        }
    }

    public void sendTouchPadEvent(MotionEvent event) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        int action = event.getActionMasked();
        if (action != 0 && action != 5 && action != 2 && action != 6 && action != 1) {
            return;
        }
        int pointerCount = event.getPointerCount();
        int[] x = new int[pointerCount];
        int[] y = new int[pointerCount];
        int[] pointerId = new int[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            x[i] = (int) event.getX(i);
            y[i] = (int) event.getY(i);
            pointerId[i] = event.getPointerId(i);
        }
        int actionIndex = event.getActionIndex();
        nativeSendTouchPadEvent(timestamp, pointerId, x, y, action, actionIndex);
    }

    public void sendRelativeEvent(int keycode, int delta) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        nativeSendRelativeEvent(timestamp, keycode, delta);
    }

    public void sendAbsoluteEvent(int keycode, int value) {
        long timestamp = SystemClock.elapsedRealtimeNanos();
        nativeSendAbsoluteEvent(timestamp, keycode, value);
    }

    public void sendBackEvent() {
        int keycode = translateKeycode(4);
        nativeSendKeyEvent(SystemClock.elapsedRealtimeNanos(), keycode, true, 0);
        nativeSendKeyEvent(SystemClock.elapsedRealtimeNanos(), keycode, DBG_TRACE, 0);
    }

    public void injectForTest(Protos.InputReport input) {
        Log.d("AHU.INPUT", "Received an input report");
        if (input.getTouchEvent().getPointerDataCount() > 0) {
            int action = input.getTouchEvent().getAction().getNumber();
            int actionIndex = input.getTouchEvent().getActionIndex();
            int pointerCount = input.getTouchEvent().getPointerDataCount();
            MotionEvent.PointerCoords[] coords = new MotionEvent.PointerCoords[pointerCount];
            MotionEvent.PointerProperties[] pointerProperties = new MotionEvent.PointerProperties[pointerCount];
            for (int i = 0; i < pointerCount; i++) {
                MotionEvent.PointerCoords coord = new MotionEvent.PointerCoords();
                coord.x = input.getTouchEvent().getPointerData(i).getX();
                coord.y = input.getTouchEvent().getPointerData(i).getY();
                coords[i] = coord;
                MotionEvent.PointerProperties properties = new MotionEvent.PointerProperties();
                if (input.getTouchEvent().getPointerData(i).hasPointerId()) {
                    properties.id = input.getTouchEvent().getPointerData(i).getPointerId();
                }
                pointerProperties[i] = properties;
            }
            MotionEvent fakeEvent = MotionEvent.obtain(input.getTimestamp(), input.getTimestamp(), (actionIndex << 8) + action, pointerCount, pointerProperties, coords, 0, 0, 1.0f, 1.0f, 0, 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0);
            synchronized (this) {
                this.mOverrideMotionEvent = true;
                this.mInputInjector.onMotionEvent(fakeEvent);
            }
            fakeEvent.recycle();
        }
        if (input.getKeyEvent().getKeysCount() > 0) {
            if (input.getKeyEvent().getKeysCount() > 1) {
                this.mInputInjector.onKeyEvent(new KeyEvent(0, 4));
                return;
            }
            Protos.KeyEvent.Key k = input.getKeyEvent().getKeys(0);
            int action2 = k.getDown() ? 0 : 1;
            KeyEvent keyEvent = new KeyEvent(SystemClock.elapsedRealtimeNanos(), SystemClock.elapsedRealtimeNanos(), action2, k.getKeycode(), 0, k.getMetastate());
            this.mInputInjector.onKeyEvent(keyEvent);
        }
        if (input.getRelativeEvent().getDataCount() > 0) {
            List<Protos.RelativeEvent.Rel> allEventData = input.getRelativeEvent().getDataList();
            for (Protos.RelativeEvent.Rel eventData : allEventData) {
                int keyCode = eventData.getKeycode();
                int delta = eventData.getDelta();
                this.mInputInjector.onRelativeEvent(keyCode, delta);
            }
        }
        input.getAbsoluteEvent().getDataCount();
    }

    @Override // com.google.android.projection.protocol.InputSourceCallbacks
    public void onInputFeedback(int event) {
        OnInputFeedbackListener onInputFeedbackListener = this.mFeedbackListener;
        if (onInputFeedbackListener != null) {
            onInputFeedbackListener.onInputFeedback(event);
        }
    }

    public void setDisplayId(int displayId) {
        nativeSetDisplayId(displayId);
    }
}
