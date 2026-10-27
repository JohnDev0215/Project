package com.android.car.settings.security;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.security.CheckLockWorker;
import com.android.car.settings.security.ConfirmLockLockoutHelper;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmLockPatternFragment extends BaseFragment {
    private static final long CLEAR_WRONG_ATTEMPT_TIMEOUT_MS = 2500;
    private static final String FRAGMENT_TAG_CHECK_LOCK_WORKER = "check_lock_worker";
    private CheckLockListener mCheckLockListener;
    private CheckLockWorker mCheckLockWorker;
    private ConfirmLockLockoutHelper mConfirmLockLockoutHelper;
    private LockPatternView mLockPatternView;
    private TextView mMsgView;
    private List<LockPatternView.Cell> mPattern;
    private int mUserId;
    private Runnable mClearErrorRunnable = new Runnable() { // from class: com.android.car.settings.security.-$$Lambda$ConfirmLockPatternFragment$DkptQ2SKkLQRW8Fk2jbewAOF9Eo
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$ConfirmLockPatternFragment();
        }
    };
    private LockPatternView.OnPatternListener mLockPatternListener = new AnonymousClass2();

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return R.layout.confirm_lock_pattern;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return R.string.security_settings_title;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof CheckLockListener) {
            this.mCheckLockListener = (CheckLockListener) getActivity();
            this.mUserId = UserHandle.myUserId();
            this.mConfirmLockLockoutHelper = ConfirmLockLockoutHelper.getInstance(requireContext(), this.mUserId);
            return;
        }
        throw new RuntimeException("The activity must implement CheckLockListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mMsgView = (TextView) view.findViewById(R.id.message);
        LockPatternView lockPatternViewFindViewById = view.findViewById(R.id.lockPattern);
        this.mLockPatternView = lockPatternViewFindViewById;
        lockPatternViewFindViewById.setFadePattern(false);
        this.mLockPatternView.setInStealthMode(!this.mConfirmLockLockoutHelper.getLockPatternUtils().isVisiblePatternEnabled(this.mUserId));
        this.mLockPatternView.setOnPatternListener(this.mLockPatternListener);
        this.mConfirmLockLockoutHelper.setConfirmLockUIController(new ConfirmLockLockoutHelper.ConfirmLockUIController() { // from class: com.android.car.settings.security.ConfirmLockPatternFragment.1
            @Override // com.android.car.settings.security.ConfirmLockLockoutHelper.ConfirmLockUIController
            public void setErrorText(String text) {
                ConfirmLockPatternFragment.this.mMsgView.setText(text);
            }

            @Override // com.android.car.settings.security.ConfirmLockLockoutHelper.ConfirmLockUIController
            public void refreshUI(boolean isLockedOut) {
                ConfirmLockPatternFragment.this.mLockPatternView.setEnabled(!isLockedOut);
                ConfirmLockPatternFragment.this.mLockPatternView.clearPattern();
            }
        });
        if (savedInstanceState != null) {
            this.mCheckLockWorker = (CheckLockWorker) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_CHECK_LOCK_WORKER);
        }
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CheckLockWorker checkLockWorker = this.mCheckLockWorker;
        if (checkLockWorker != null) {
            checkLockWorker.setListener(new CheckLockWorker.Listener() { // from class: com.android.car.settings.security.-$$Lambda$ConfirmLockPatternFragment$00C59Ir5cjYye7kU5h4nFb2BBUA
                @Override // com.android.car.settings.security.CheckLockWorker.Listener
                public final void onCheckCompleted(boolean z, int i) {
                    this.f$0.onCheckCompleted(z, i);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mConfirmLockLockoutHelper.onResumeUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mConfirmLockLockoutHelper.onPauseUI();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        CheckLockWorker checkLockWorker = this.mCheckLockWorker;
        if (checkLockWorker != null) {
            checkLockWorker.setListener(null);
        }
    }

    public /* synthetic */ void lambda$new$0$ConfirmLockPatternFragment() {
        this.mLockPatternView.setEnabled(true);
        this.mLockPatternView.clearPattern();
        this.mMsgView.setText("");
    }

    /* JADX INFO: renamed from: com.android.car.settings.security.ConfirmLockPatternFragment$2, reason: invalid class name */
    class AnonymousClass2 implements LockPatternView.OnPatternListener {
        AnonymousClass2() {
        }

        public void onPatternStart() {
            ConfirmLockPatternFragment.this.mLockPatternView.removeCallbacks(ConfirmLockPatternFragment.this.mClearErrorRunnable);
            ConfirmLockPatternFragment.this.mMsgView.setText("");
        }

        public void onPatternCleared() {
            ConfirmLockPatternFragment.this.mLockPatternView.removeCallbacks(ConfirmLockPatternFragment.this.mClearErrorRunnable);
        }

        public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {
        }

        public void onPatternDetected(List<LockPatternView.Cell> pattern) {
            ConfirmLockPatternFragment.this.mLockPatternView.setEnabled(false);
            if (ConfirmLockPatternFragment.this.mCheckLockWorker == null) {
                ConfirmLockPatternFragment.this.mCheckLockWorker = new CheckLockWorker();
                CheckLockWorker checkLockWorker = ConfirmLockPatternFragment.this.mCheckLockWorker;
                final ConfirmLockPatternFragment confirmLockPatternFragment = ConfirmLockPatternFragment.this;
                checkLockWorker.setListener(new CheckLockWorker.Listener() { // from class: com.android.car.settings.security.-$$Lambda$ConfirmLockPatternFragment$2$-tpyqsE-dzIbOLtKdisZ8m_0Geg
                    @Override // com.android.car.settings.security.CheckLockWorker.Listener
                    public final void onCheckCompleted(boolean z, int i) {
                        confirmLockPatternFragment.onCheckCompleted(z, i);
                    }
                });
                ConfirmLockPatternFragment.this.getFragmentManager().beginTransaction().add(ConfirmLockPatternFragment.this.mCheckLockWorker, ConfirmLockPatternFragment.FRAGMENT_TAG_CHECK_LOCK_WORKER).commitNow();
            }
            ConfirmLockPatternFragment.this.mPattern = pattern;
            ConfirmLockPatternFragment.this.mCheckLockWorker.checkPattern(ConfirmLockPatternFragment.this.mUserId, pattern);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCheckCompleted(boolean lockMatched, int timeoutMs) {
        if (lockMatched) {
            this.mCheckLockListener.onLockVerified(LockscreenCredential.createPattern(this.mPattern));
            return;
        }
        if (timeoutMs > 0) {
            this.mConfirmLockLockoutHelper.onCheckCompletedWithTimeout(timeoutMs);
            return;
        }
        this.mLockPatternView.setEnabled(true);
        this.mMsgView.setText(R.string.lockpattern_pattern_wrong);
        this.mLockPatternView.removeCallbacks(this.mClearErrorRunnable);
        this.mLockPatternView.postDelayed(this.mClearErrorRunnable, CLEAR_WRONG_ATTEMPT_TIMEOUT_MS);
    }
}
