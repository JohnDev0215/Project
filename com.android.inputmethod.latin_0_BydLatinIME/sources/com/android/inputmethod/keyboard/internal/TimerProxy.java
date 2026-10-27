package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.PointerTracker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface TimerProxy {

    public static class Adapter implements TimerProxy {
        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelAllUpdateBatchInputTimers() {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelDoubleTapShiftKeyTimer() {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelKeyTimersOf(@Nonnull PointerTracker tracker) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelLongPressShiftKeyTimer() {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelLongPressTimersOf(@Nonnull PointerTracker tracker) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void cancelUpdateBatchInputTimer(@Nonnull PointerTracker tracker) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public boolean isInDoubleTapShiftKeyTimeout() {
            return false;
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public boolean isTypingState() {
            return false;
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void startDoubleTapShiftKeyTimer() {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void startKeyRepeatTimerOf(@Nonnull PointerTracker tracker, int repeatCount, int delay) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void startLongPressTimerOf(@Nonnull PointerTracker tracker, int delay) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void startTypingStateTimer(@Nonnull Key typedKey) {
        }

        @Override // com.android.inputmethod.keyboard.internal.TimerProxy
        public void startUpdateBatchInputTimer(@Nonnull PointerTracker tracker) {
        }
    }

    void cancelAllUpdateBatchInputTimers();

    void cancelDoubleTapShiftKeyTimer();

    void cancelKeyTimersOf(@Nonnull PointerTracker tracker);

    void cancelLongPressShiftKeyTimer();

    void cancelLongPressTimersOf(@Nonnull PointerTracker tracker);

    void cancelUpdateBatchInputTimer(@Nonnull PointerTracker tracker);

    boolean isInDoubleTapShiftKeyTimeout();

    boolean isTypingState();

    void startDoubleTapShiftKeyTimer();

    void startKeyRepeatTimerOf(@Nonnull PointerTracker tracker, int repeatCount, int delay);

    void startLongPressTimerOf(@Nonnull PointerTracker tracker, int delay);

    void startTypingStateTimer(@Nonnull Key typedKey);

    void startUpdateBatchInputTimer(@Nonnull PointerTracker tracker);
}
