package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class TypingTimeRecorder {
    private long mLastBatchInputTime;
    private long mLastLetterTypingTime;
    private long mLastTypingTime;
    private final int mStaticTimeThresholdAfterFastTyping;
    private final int mSuppressKeyPreviewAfterBatchInputDuration;

    public TypingTimeRecorder(final int staticTimeThresholdAfterFastTyping, final int suppressKeyPreviewAfterBatchInputDuration) {
        this.mStaticTimeThresholdAfterFastTyping = staticTimeThresholdAfterFastTyping;
        this.mSuppressKeyPreviewAfterBatchInputDuration = suppressKeyPreviewAfterBatchInputDuration;
    }

    public boolean isInFastTyping(final long eventTime) {
        return eventTime - this.mLastLetterTypingTime < ((long) this.mStaticTimeThresholdAfterFastTyping);
    }

    private boolean wasLastInputTyping() {
        return this.mLastTypingTime >= this.mLastBatchInputTime;
    }

    public void onCodeInput(final int code, final long eventTime) {
        if (Character.isLetter(code)) {
            if (wasLastInputTyping() || eventTime - this.mLastTypingTime < this.mStaticTimeThresholdAfterFastTyping) {
                this.mLastLetterTypingTime = eventTime;
            }
        } else if (eventTime - this.mLastLetterTypingTime < this.mStaticTimeThresholdAfterFastTyping) {
            this.mLastLetterTypingTime = eventTime;
        }
        this.mLastTypingTime = eventTime;
    }

    public void onEndBatchInput(final long eventTime) {
        this.mLastBatchInputTime = eventTime;
    }

    public long getLastLetterTypingTime() {
        return this.mLastLetterTypingTime;
    }

    public boolean needsToSuppressKeyPreviewPopup(final long eventTime) {
        return !wasLastInputTyping() && eventTime - this.mLastBatchInputTime < ((long) this.mSuppressKeyPreviewAfterBatchInputDuration);
    }
}
