package com.android.car.settings.security;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.Logger;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CheckLockWorker extends Fragment implements LockPatternChecker.OnCheckCallback {
    private static final Logger LOG = new Logger((Class<?>) CheckLockWorker.class);
    private boolean mCheckInProgress;
    private boolean mHasPendingResult;
    private Listener mListener;
    private boolean mLockMatched;
    private LockPatternUtils mLockPatternUtils;
    private int mThrottleTimeoutMs;

    interface Listener {
        void onCheckCompleted(boolean z, int i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.mLockPatternUtils = new LockPatternUtils(getContext());
    }

    public void onChecked(boolean matched, int throttleTimeoutMs) {
        this.mCheckInProgress = false;
        Listener listener = this.mListener;
        if (listener == null) {
            this.mHasPendingResult = true;
            this.mLockMatched = matched;
            this.mThrottleTimeoutMs = throttleTimeoutMs;
            return;
        }
        listener.onCheckCompleted(matched, throttleTimeoutMs);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (listener != null && this.mHasPendingResult) {
            this.mHasPendingResult = false;
            listener.onCheckCompleted(this.mLockMatched, this.mThrottleTimeoutMs);
        }
    }

    public final boolean isCheckInProgress() {
        return this.mCheckInProgress;
    }

    public final void checkPattern(int userId, List<LockPatternView.Cell> pattern) {
        if (this.mCheckInProgress) {
            LOG.w("Check pattern request issued while one is still running");
        } else {
            this.mCheckInProgress = true;
            LockPatternChecker.checkCredential(this.mLockPatternUtils, LockscreenCredential.createPattern(pattern), userId, this);
        }
    }

    public final void checkPinPassword(int userId, LockscreenCredential password) {
        if (this.mCheckInProgress) {
            LOG.w("Check pin/password request issued while one is still running");
        } else if (password == null || password.isNone()) {
            LOG.w("Empty pin/password LockscreenCredential");
        } else {
            this.mCheckInProgress = true;
            LockPatternChecker.checkCredential(this.mLockPatternUtils, password, userId, this);
        }
    }
}
