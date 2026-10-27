package com.android.car.settings.security;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.SystemClock;
import com.android.car.settings.R;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmLockLockoutHelper {
    private static ConfirmLockLockoutHelper sInstance;
    private final Context mContext;
    private CountDownTimer mCountDownTimer;
    private final LockPatternUtils mLockPatternUtils;
    private ConfirmLockUIController mUiController;
    private final int mUserId;

    public interface ConfirmLockUIController {
        void refreshUI(boolean z);

        void setErrorText(String str);
    }

    public static ConfirmLockLockoutHelper getInstance(Context context, int userId) {
        if (sInstance == null) {
            sInstance = new ConfirmLockLockoutHelper(context, userId, new LockPatternUtils(context));
        }
        return sInstance;
    }

    ConfirmLockLockoutHelper(Context context, int userId, LockPatternUtils lockPatternUtils) {
        this.mContext = context;
        this.mUserId = userId;
        this.mLockPatternUtils = lockPatternUtils;
    }

    public void setConfirmLockUIController(ConfirmLockUIController uiController) {
        this.mUiController = uiController;
    }

    public LockPatternUtils getLockPatternUtils() {
        return this.mLockPatternUtils;
    }

    public void onCheckCompletedWithTimeout(int timeoutMs) {
        if (timeoutMs <= 0) {
            return;
        }
        long deadline = this.mLockPatternUtils.setLockoutAttemptDeadline(this.mUserId, timeoutMs);
        handleAttemptLockout(deadline);
    }

    public void onResumeUI() {
        if (isLockedOut()) {
            handleAttemptLockout(this.mLockPatternUtils.getLockoutAttemptDeadline(this.mUserId));
        } else {
            this.mUiController.refreshUI(isLockedOut());
        }
    }

    public void onPauseUI() {
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    CountDownTimer getCountDownTimer() {
        return this.mCountDownTimer;
    }

    private void handleAttemptLockout(long deadline) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mUiController.refreshUI(isLockedOut());
        this.mCountDownTimer = newCountDownTimer(deadline - elapsedRealtime).start();
    }

    private boolean isLockedOut() {
        return this.mLockPatternUtils.getLockoutAttemptDeadline(this.mUserId) != 0;
    }

    private CountDownTimer newCountDownTimer(long countDownMillis) {
        return new CountDownTimer(countDownMillis, 1000L) { // from class: com.android.car.settings.security.ConfirmLockLockoutHelper.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                int secondsCountdown = (int) (millisUntilFinished / 1000);
                ConfirmLockLockoutHelper.this.mUiController.setErrorText(ConfirmLockLockoutHelper.this.mContext.getString(R.string.lockpattern_too_many_failed_confirmation_attempts, Integer.valueOf(secondsCountdown)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ConfirmLockLockoutHelper.this.mUiController.refreshUI(false);
                ConfirmLockLockoutHelper.this.mUiController.setErrorText("");
            }
        };
    }
}
