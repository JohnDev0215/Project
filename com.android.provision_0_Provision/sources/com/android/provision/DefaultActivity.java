package com.android.provision;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.provision.base.BaseFullScreenActivity;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class DefaultActivity extends BaseFullScreenActivity {
    private static String TAG = "DefaultActivity";
    private static final int WHAT_NOTIFY = 1;
    private boolean flagMessage;
    private H handler;
    private boolean mPrivacyFinished;
    private boolean mReCreate;
    private TextView mTextView;
    private Toast mToast;
    private UserAgreementFragment mUserAgreementFragment;
    private boolean mWarnFinished;
    private boolean userAgreementShow;

    public boolean isReCreate() {
        return this.mReCreate;
    }

    public void setReCreate(boolean mReCreate) {
        this.mReCreate = mReCreate;
    }

    @Override // com.android.provision.base.BaseFullScreenActivity, com.android.provision.base.BaseActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BydLogHelper.logW(TAG, "onCreate");
        setContentView(R.layout.activity_default);
        BootGuideManager.getInstance();
        this.mPrivacyFinished = BootGuideManager.isAgreePrivacy();
        BootGuideManager.getInstance();
        this.mWarnFinished = BootGuideManager.isSafetyWarnFinished();
        BydLogHelper.logW(TAG, "mPrivacyFinished:" + this.mPrivacyFinished + " , mWarnFinished:" + this.mWarnFinished);
        BydLogHelper.logW(TAG, "boot_business:" + BootGuideManager.getInstance().getGuideStatus());
        if (savedInstanceState != null) {
            BydLogHelper.logW(TAG, "savedInstanceState not null");
            this.flagMessage = true;
            setReCreate(true);
            if (this.mPrivacyFinished && this.mWarnFinished) {
                BootGuideManager.getInstance().finishSetUpWizard(this);
                finish();
                return;
            }
            return;
        }
        if (!this.mPrivacyFinished) {
            setReCreate(false);
            initBydSettings();
            BootGuideManager.getInstance().setGuideStatus(BootGuideManager.GUIDE);
            showUserAgreementView();
            return;
        }
        if (!this.mWarnFinished) {
            setReCreate(false);
            initBydSettings();
            BootGuideManager.getInstance().setGuideStatus(BootGuideManager.GUIDE);
            showWarnView();
            return;
        }
        BootGuideManager.getInstance().finishSetUpWizard(this);
        finish();
    }

    public void showUserAgreementView() {
        this.userAgreementShow = true;
        FragmentManager fragmentManager = getFragmentManager();
        UserAgreementFragment userAgreementFragment = (UserAgreementFragment) fragmentManager.findFragmentByTag(UserAgreementFragment.TAG);
        this.mUserAgreementFragment = userAgreementFragment;
        if (userAgreementFragment == null) {
            this.mUserAgreementFragment = new UserAgreementFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_content, this.mUserAgreementFragment).addToBackStack(UserAgreementFragment.TAG).commit();
    }

    public void showPrivacyView() {
        this.userAgreementShow = false;
        FragmentManager fragmentManager = getFragmentManager();
        PrivacyPolicyFragment privacyPolicyFragment = (PrivacyPolicyFragment) fragmentManager.findFragmentByTag(PrivacyPolicyFragment.TAG);
        if (privacyPolicyFragment == null) {
            privacyPolicyFragment = new PrivacyPolicyFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_content, privacyPolicyFragment).addToBackStack(PrivacyPolicyFragment.TAG).commit();
    }

    public void showWarnView() {
        this.userAgreementShow = false;
        FragmentManager fragmentManager = getFragmentManager();
        SafetyWarnFragment safetyWarnFragment = (SafetyWarnFragment) fragmentManager.findFragmentByTag(SafetyWarnFragment.TAG);
        if (safetyWarnFragment == null) {
            safetyWarnFragment = new SafetyWarnFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_content, safetyWarnFragment).addToBackStack(SafetyWarnFragment.TAG).commit();
    }

    public void resetPrivacyView() {
        this.mPrivacyFinished = true;
    }

    @Override // com.android.provision.base.BaseFullScreenActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        BydLogHelper.logW(TAG, "onBackPressed");
        showTips(getString(R.string.completed_guide_before_use_multimedia), R.dimen.dp_120);
    }

    @Override // com.android.provision.base.BaseFullScreenActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BydLogHelper.logW(TAG, "onResume sendMessage");
        sendMessage();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        BydLogHelper.logW(TAG, "onPause");
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        BydLogHelper.logW(TAG, "onStop");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        BootGuideManager.getInstance().notifyDiLink();
        unRegisterSettings();
        this.flagMessage = false;
        cancelTips();
        super.onDestroy();
        BydLogHelper.logW(TAG, "onDestroy");
    }

    public void showTips(String tips, int redId) {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        BydLogHelper.logW(TAG, "showTips");
        this.mToast = new Toast(this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(redId);
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.view_custom_toast, (ViewGroup) null);
        this.mTextView = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.mToast.setView(viewInflate);
        this.mToast.setGravity(48, 0, dimensionPixelSize);
        this.mToast.setDuration(0);
        this.mTextView.setText(tips);
        this.mToast.show();
        this.mTextView.measure(0, 0);
        int measuredWidth = this.mTextView.getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = this.mTextView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        layoutParams.width = measuredWidth;
        this.mTextView.setLayoutParams(layoutParams);
    }

    public void cancelTips() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
            this.mToast = null;
        }
    }

    private void initBydSettings() {
        this.handler = new H();
    }

    private void unRegisterSettings() {
        try {
            H h = this.handler;
            if (h != null) {
                h.removeMessages(1);
                this.handler = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startAgreementTimer() {
        UserAgreementFragment userAgreementFragment = this.mUserAgreementFragment;
        if (userAgreementFragment != null) {
            userAgreementFragment.startTimer();
        }
    }

    public void sendMessage() {
        if (this.handler != null) {
            BydLogHelper.logW(TAG, "sendMessage");
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, 1200L);
        }
    }

    private class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BydLogHelper.logW(DefaultActivity.TAG, "handleMessage msg = " + msg.toString() + ",userAgreementShow:" + DefaultActivity.this.userAgreementShow);
            if (msg.what == 1) {
                BootGuideManager.getInstance().notifyDiLink();
                if (DefaultActivity.this.userAgreementShow) {
                    DefaultActivity.this.startAgreementTimer();
                }
            }
        }
    }
}
