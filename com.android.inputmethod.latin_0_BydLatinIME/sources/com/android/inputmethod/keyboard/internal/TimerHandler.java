package com.android.inputmethod.keyboard.internal;

import android.os.Message;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.PointerTracker;
import com.android.inputmethod.latin.utils.LeakGuardHandlerWrapper;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class TimerHandler extends LeakGuardHandlerWrapper<DrawingProxy> implements TimerProxy {
    private static final int MSG_DISMISS_GESTURE_FLOATING_PREVIEW_TEXT = 7;
    private static final int MSG_DISMISS_KEY_PREVIEW = 6;
    private static final int MSG_DOUBLE_TAP_SHIFT_KEY = 4;
    private static final int MSG_LONGPRESS_KEY = 2;
    private static final int MSG_LONGPRESS_SHIFT_KEY = 3;
    private static final int MSG_REPEAT_KEY = 1;
    private static final int MSG_TYPING_STATE_EXPIRED = 0;
    private static final int MSG_UPDATE_BATCH_INPUT = 5;
    private final int mGestureRecognitionUpdateTime;
    private final int mIgnoreAltCodeKeyTimeout;

    public TimerHandler(@Nonnull final DrawingProxy ownerInstance, final int ignoreAltCodeKeyTimeout, final int gestureRecognitionUpdateTime) {
        super(ownerInstance);
        this.mIgnoreAltCodeKeyTimeout = ignoreAltCodeKeyTimeout;
        this.mGestureRecognitionUpdateTime = gestureRecognitionUpdateTime;
    }

    @Override // android.os.Handler
    public void handleMessage(final Message msg) {
        DrawingProxy ownerInstance = getOwnerInstance();
        if (ownerInstance == null) {
            return;
        }
        int i = msg.what;
        if (i == 0) {
            ownerInstance.startWhileTypingAnimation(0);
            return;
        }
        if (i == 1) {
            ((PointerTracker) msg.obj).onKeyRepeat(msg.arg1, msg.arg2);
            return;
        }
        if (i == 2 || i == 3) {
            cancelLongPressTimers();
            ((PointerTracker) msg.obj).onLongPressed();
            return;
        }
        if (i == 5) {
            PointerTracker pointerTracker = (PointerTracker) msg.obj;
            pointerTracker.updateBatchInputByTimer(SystemClock.uptimeMillis());
            startUpdateBatchInputTimer(pointerTracker);
        } else if (i == 6) {
            ownerInstance.onKeyReleased((Key) msg.obj, false);
        } else {
            if (i != 7) {
                return;
            }
            ownerInstance.dismissGestureFloatingPreviewTextWithoutDelay();
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void startKeyRepeatTimerOf(@Nonnull final PointerTracker tracker, final int repeatCount, final int delay) {
        Key key = tracker.getKey();
        if (key == null || delay == 0) {
            return;
        }
        sendMessageDelayed(obtainMessage(1, key.getCode(), repeatCount, tracker), delay);
    }

    private void cancelKeyRepeatTimerOf(final PointerTracker tracker) {
        removeMessages(1, tracker);
    }

    public void cancelKeyRepeatTimers() {
        removeMessages(1);
    }

    public boolean isInKeyRepeat() {
        return hasMessages(1);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void startLongPressTimerOf(@Nonnull final PointerTracker tracker, final int delay) {
        Key key = tracker.getKey();
        if (key == null) {
            return;
        }
        sendMessageDelayed(obtainMessage(key.getCode() == -1 ? 3 : 2, tracker), delay);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelLongPressTimersOf(@Nonnull final PointerTracker tracker) {
        removeMessages(2, tracker);
        removeMessages(3, tracker);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelLongPressShiftKeyTimer() {
        removeMessages(3);
    }

    public void cancelLongPressTimers() {
        removeMessages(2);
        removeMessages(3);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void startTypingStateTimer(@Nonnull final Key typedKey) {
        if (typedKey.isModifier() || typedKey.altCodeWhileTyping()) {
            return;
        }
        boolean zIsTypingState = isTypingState();
        removeMessages(0);
        DrawingProxy ownerInstance = getOwnerInstance();
        if (ownerInstance == null) {
            return;
        }
        int code = typedKey.getCode();
        if (code == 32 || code == 10) {
            if (zIsTypingState) {
                ownerInstance.startWhileTypingAnimation(0);
            }
        } else {
            sendMessageDelayed(obtainMessage(0), this.mIgnoreAltCodeKeyTimeout);
            if (zIsTypingState) {
                return;
            }
            ownerInstance.startWhileTypingAnimation(1);
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public boolean isTypingState() {
        return hasMessages(0);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void startDoubleTapShiftKeyTimer() {
        sendMessageDelayed(obtainMessage(4), ViewConfiguration.getDoubleTapTimeout());
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelDoubleTapShiftKeyTimer() {
        removeMessages(4);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public boolean isInDoubleTapShiftKeyTimeout() {
        return hasMessages(4);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelKeyTimersOf(@Nonnull final PointerTracker tracker) {
        cancelKeyRepeatTimerOf(tracker);
        cancelLongPressTimersOf(tracker);
    }

    public void cancelAllKeyTimers() {
        cancelKeyRepeatTimers();
        cancelLongPressTimers();
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void startUpdateBatchInputTimer(@Nonnull final PointerTracker tracker) {
        if (this.mGestureRecognitionUpdateTime <= 0) {
            return;
        }
        removeMessages(5, tracker);
        sendMessageDelayed(obtainMessage(5, tracker), this.mGestureRecognitionUpdateTime);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelUpdateBatchInputTimer(@Nonnull final PointerTracker tracker) {
        removeMessages(5, tracker);
    }

    @Override // com.android.inputmethod.keyboard.internal.TimerProxy
    public void cancelAllUpdateBatchInputTimers() {
        removeMessages(5);
    }

    public void postDismissKeyPreview(@Nonnull final Key key, final long delay) {
        sendMessageDelayed(obtainMessage(6, key), delay);
    }

    public void postDismissGestureFloatingPreviewText(final long delay) {
        sendMessageDelayed(obtainMessage(7), delay);
    }

    public void cancelAllMessages() {
        cancelAllKeyTimers();
        cancelAllUpdateBatchInputTimers();
        removeMessages(6);
        removeMessages(7);
    }
}
