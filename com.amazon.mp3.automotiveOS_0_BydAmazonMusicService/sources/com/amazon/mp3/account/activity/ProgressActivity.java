package com.amazon.mp3.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.LaunchingAnimationController;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.signin.SignInTaskId;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class ProgressActivity extends SignInFlowActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final String TAG = "ProgressActivity";
    private LaunchingAnimationController launchingAnimationController;
    private ProgressBar mProgressBar;
    private Subscription mSignInTaskStreamSubscription;
    private Subscription mSubscription;
    private int mNumTasksCompleted = 0;
    private int mTotalSignInTaskCount = 0;
    private boolean mNeedToGetLoadingMessage = true;
    private boolean mIsRestartSigninFlowOptimized = false;
    private Action1<SignInTaskId> onNext = new Action1<SignInTaskId>() { // from class: com.amazon.mp3.account.activity.ProgressActivity.1
        @Override // rx.functions.Action1
        public void call(final SignInTaskId taskId) {
            if (taskId.equals(SignInTaskId.ACCOUNT_DATA_SAVED)) {
                ProgressActivity.this.showWelcomeMessage();
            }
            ProgressActivity.this.updateProgressBar(ProgressActivity.access$108(r4));
            if (ProgressActivity.this.mNumTasksCompleted == 1) {
                ProgressActivity progressActivity = ProgressActivity.this;
                progressActivity.mSubscription = BootstrapSingletonTask.get(progressActivity.getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.account.activity.ProgressActivity.1.1
                    @Override // rx.functions.Action1
                    public void call(final Object o) {
                        if (ProgressActivity.this.mNeedToGetLoadingMessage) {
                            ProgressActivity.this.showSubscriptionBadge();
                        }
                    }
                }, new Action1<Throwable>() { // from class: com.amazon.mp3.account.activity.ProgressActivity.1.2
                    @Override // rx.functions.Action1
                    public void call(final Throwable throwable) {
                        Log.warning(ProgressActivity.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
                    }
                });
            }
        }
    };
    private Action1<Throwable> onProgressError = new Action1<Throwable>() { // from class: com.amazon.mp3.account.activity.ProgressActivity.2
        @Override // rx.functions.Action1
        public void call(Throwable throwable) {
            if ((throwable instanceof MusicAccountNotCreatedException) && !ChildUserUtil.INSTANCE.isChildUser(ProgressActivity.this.getApplicationContext())) {
                ProgressActivity.this.mIsRestartSigninFlowOptimized = AmazonApplication.getCapabilities().isRestartSigninFlowOptimizationEnabled();
                if (ProgressActivity.this.mIsRestartSigninFlowOptimized) {
                    ProgressActivity.this.setContentView(R.layout.logo_activity);
                }
            }
            ProgressActivity.this.onError.call(throwable);
        }
    };
    Action0 onCompleted = new Action0() { // from class: com.amazon.mp3.account.activity.ProgressActivity.3
        @Override // rx.functions.Action0
        public void call() {
            BootstrapSingletonTask.get(ProgressActivity.this.getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.account.activity.ProgressActivity.3.1
                @Override // rx.functions.Action1
                public void call(final Object o) {
                    ProgressActivity.this.mSignInTaskStreamSubscription.unsubscribe();
                    SignInTaskStream.get().clearRxSubject();
                    ProgressActivity.this.mProgressBar.setProgress(100);
                    ProgressActivity.this.setResult(-1);
                    ProgressActivity.this.finish();
                }
            });
        }
    };

    static /* synthetic */ int access$108(ProgressActivity progressActivity) {
        int i = progressActivity.mNumTasksCompleted;
        progressActivity.mNumTasksCompleted = i + 1;
        return i;
    }

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mTotalSignInTaskCount = getCountOfRemainingSignInTasksToExecute() + 2;
        if (SignInTaskStream.get().getRxSubject() != null) {
            setUpView();
            this.mSignInTaskStreamSubscription = SignInTaskStream.get().getRxSubject().observeOn(AndroidSchedulers.mainThread()).subscribe(this.onNext, this.onProgressError, this.onCompleted);
        } else {
            setResult(0);
            finish();
        }
    }

    private int getCountOfRemainingSignInTasksToExecute() {
        Intent intent = getIntent();
        int intExtra = intent != null ? intent.getIntExtra("task_count", 0) : 0;
        if (intExtra > 0) {
            return intExtra;
        }
        return 12;
    }

    private void setUpView() {
        setContentView(R.layout.progress_activity);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_thin);
        LaunchingAnimationController launchingAnimationController = new LaunchingAnimationController(this, true);
        this.launchingAnimationController = launchingAnimationController;
        launchingAnimationController.initLoadingScreen();
    }

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity
    protected void handleException() {
        Log.debug(ProgressActivity.class.getSimpleName(), "ProgressActivity received an error. Finishing");
        setResult(0);
        finish();
    }

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity
    protected void handleMapEmptyValueException() {
        setResult(2);
        finish();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AccountWebViewActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                if (this.mIsRestartSigninFlowOptimized) {
                    setResult(4);
                } else {
                    setResult(3);
                }
            } else if (resultCode == AccountWebViewActivity.RESULT_GO_TO_STAND_ALONE_EXPERIENCE) {
                setResult(MusicLauncherActivity.RESULT_FINISH);
            }
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWelcomeMessage() {
        String firstName = AccountCredentialUtil.get(getApplicationContext()).getFirstName();
        TextView textView = (TextView) findViewById(R.id.progress_welcome_text);
        textView.setText(getString(R.string.dmusic_welcome, firstName));
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000L);
        alphaAnimation.setFillAfter(true);
        textView.startAnimation(alphaAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSubscriptionBadge() {
        try {
            this.launchingAnimationController.showSubscriptionBadge();
            this.mNeedToGetLoadingMessage = false;
        } catch (DataNotReadyException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgressBar(final float numTasksCompleted) {
        this.mProgressBar.setProgress((int) ((numTasksCompleted * 100.0f) / this.mTotalSignInTaskCount));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Subscription subscription = this.mSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.mSignInTaskStreamSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.mSignInTaskStreamSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
