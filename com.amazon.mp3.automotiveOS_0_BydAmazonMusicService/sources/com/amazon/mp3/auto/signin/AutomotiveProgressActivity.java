package com.amazon.mp3.auto.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.mp3.account.activity.AccountWebViewActivity;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.signintasks.GetHomeMarketplace;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.signin.SignInTaskId;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.Subject;

/* JADX INFO: compiled from: AutomotiveProgressActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0006H\u0002J\"\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0017H\u0014J\b\u0010\"\u001a\u00020\u0017H\u0002J\b\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0017H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveProgressActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "launchingAnimationController", "Lcom/amazon/mp3/auto/signin/AutomotiveLaunchingAnimationController;", "numSignInTasksCompleted", "", "onCompleted", "Lrx/functions/Action0;", "onError", "Lrx/functions/Action1;", "", "onNext", "Lcom/amazon/music/signin/SignInTaskId;", "progressBar", "Landroid/widget/ProgressBar;", "showLoadingMessage", "", "signInTaskStreamSubscription", "Lrx/Subscription;", "totalSignInTaskCount", "welcomeMessageSubscription", "cancelSignInFlow", "", "getCountOfRemainingSignInTasksToExecute", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setUpView", "showSubscriptionBadge", "showWelcomeMessage", "startAccountWebView", "updateProgressBar", "numTasksCompleted", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveProgressActivity extends AppCompatActivity {
    private AutomotiveLaunchingAnimationController launchingAnimationController;
    private int numSignInTasksCompleted;
    private ProgressBar progressBar;
    private Subscription signInTaskStreamSubscription;
    private int totalSignInTaskCount;
    private Subscription welcomeMessageSubscription;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AutomotiveProgressActivity.class.getSimpleName();
    private static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private boolean showLoadingMessage = true;
    private final Action1<SignInTaskId> onNext = new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda0
        @Override // rx.functions.Action1
        public final void call(Object obj) {
            AutomotiveProgressActivity.m371onNext$lambda2(this.f$0, (SignInTaskId) obj);
        }
    };
    private final Action1<Throwable> onError = new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda1
        @Override // rx.functions.Action1
        public final void call(Object obj) {
            AutomotiveProgressActivity.m370onError$lambda3(this.f$0, (Throwable) obj);
        }
    };
    private final Action0 onCompleted = new Action0() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda2
        @Override // rx.functions.Action0
        public final void call() {
            AutomotiveProgressActivity.m368onCompleted$lambda5(this.f$0);
        }
    };

    /* JADX INFO: compiled from: AutomotiveProgressActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveProgressActivity$Companion;", "", "()V", "DEFAULT_SIGN_IN_EVENT_COUNT", "", "EXTRA_REMAINING_TASK_COUNT", "", "REQUEST_CODE", "getREQUEST_CODE", "()I", "TAG", "kotlin.jvm.PlatformType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getREQUEST_CODE() {
            return AutomotiveProgressActivity.REQUEST_CODE;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.totalSignInTaskCount = getCountOfRemainingSignInTasksToExecute() + 2;
        Subject<SignInTaskId, SignInTaskId> rxSubject = SignInTaskStream.get().getRxSubject();
        if (rxSubject != null) {
            setUpView();
            this.signInTaskStreamSubscription = rxSubject.observeOn(AndroidSchedulers.mainThread()).subscribe(this.onNext, this.onError, this.onCompleted);
        } else {
            setResult(0);
            finish();
        }
    }

    private final void setUpView() {
        setContentView(R.layout.progress_activity);
        View viewFindViewById = findViewById(R.id.progress_bar_thin);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.progress_bar_thin)");
        this.progressBar = (ProgressBar) viewFindViewById;
        AutomotiveLaunchingAnimationController automotiveLaunchingAnimationController = new AutomotiveLaunchingAnimationController(this);
        this.launchingAnimationController = automotiveLaunchingAnimationController;
        automotiveLaunchingAnimationController.initLoadingScreen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onNext$lambda-2, reason: not valid java name */
    public static final void m371onNext$lambda2(final AutomotiveProgressActivity this$0, SignInTaskId signInTaskId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(signInTaskId, SignInTaskId.ACCOUNT_DATA_SAVED)) {
            this$0.showWelcomeMessage();
        }
        int i = this$0.numSignInTasksCompleted + 1;
        this$0.numSignInTasksCompleted = i;
        this$0.updateProgressBar(i);
        if (this$0.numSignInTasksCompleted == 1) {
            this$0.welcomeMessageSubscription = BootstrapSingletonTask.get(this$0.getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AutomotiveProgressActivity.m372onNext$lambda2$lambda0(this.f$0, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AutomotiveProgressActivity.m373onNext$lambda2$lambda1((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onNext$lambda-2$lambda-0, reason: not valid java name */
    public static final void m372onNext$lambda2$lambda0(AutomotiveProgressActivity this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.showLoadingMessage) {
            this$0.showSubscriptionBadge();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onNext$lambda-2$lambda-1, reason: not valid java name */
    public static final void m373onNext$lambda2$lambda1(Throwable th) {
        Log.error(TAG, "BootstrapSingletonTask error", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onError$lambda-3, reason: not valid java name */
    public static final void m370onError$lambda3(AutomotiveProgressActivity this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if ((th instanceof MusicAccountNotCreatedException) || (th instanceof GetHomeMarketplace.AccountStatusException) || (th instanceof DataNotReadyException)) {
            Log.error(TAG, "User account in invalid status", th);
            this$0.startAccountWebView();
        } else {
            Log.error(TAG, "Error found during sign in", th);
            this$0.cancelSignInFlow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCompleted$lambda-5, reason: not valid java name */
    public static final void m368onCompleted$lambda5(final AutomotiveProgressActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BootstrapSingletonTask.get(this$0.getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveProgressActivity$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveProgressActivity.m369onCompleted$lambda5$lambda4(this.f$0, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCompleted$lambda-5$lambda-4, reason: not valid java name */
    public static final void m369onCompleted$lambda5$lambda4(AutomotiveProgressActivity this$0, Void r2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Subscription subscription = this$0.signInTaskStreamSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        SignInTaskStream.get().clearRxSubject();
        ProgressBar progressBar = this$0.progressBar;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            progressBar = null;
        }
        progressBar.setProgress(100);
        this$0.setResult(-1);
        this$0.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AccountWebViewActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                setResult(3);
            } else if (resultCode == AccountWebViewActivity.RESULT_GO_TO_STAND_ALONE_EXPERIENCE) {
                setResult(MusicLauncherActivity.RESULT_FINISH);
            }
            finish();
        }
    }

    private final void updateProgressBar(float numTasksCompleted) {
        float f = (numTasksCompleted * 100) / this.totalSignInTaskCount;
        ProgressBar progressBar = this.progressBar;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            progressBar = null;
        }
        progressBar.setProgress((int) f);
    }

    private final void showWelcomeMessage() {
        String firstName = AccountCredentialUtil.get(getApplicationContext()).getFirstName();
        View viewFindViewById = findViewById(R.id.progress_welcome_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.progress_welcome_text)");
        TextView textView = (TextView) viewFindViewById;
        textView.setText(getString(R.string.dmusic_welcome, firstName));
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000L);
        alphaAnimation.setFillAfter(true);
        textView.startAnimation(alphaAnimation);
    }

    private final void showSubscriptionBadge() {
        try {
            AutomotiveLaunchingAnimationController automotiveLaunchingAnimationController = this.launchingAnimationController;
            if (automotiveLaunchingAnimationController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("launchingAnimationController");
                automotiveLaunchingAnimationController = null;
            }
            automotiveLaunchingAnimationController.showSubscriptionBadge();
            this.showLoadingMessage = false;
        } catch (DataNotReadyException unused) {
        }
    }

    private final int getCountOfRemainingSignInTasksToExecute() {
        Intent intent = getIntent();
        int intExtra = intent != null ? intent.getIntExtra("Task_Count", 0) : 0;
        if (intExtra > 0) {
            return intExtra;
        }
        return 12;
    }

    private final void cancelSignInFlow() {
        setResult(0);
        finish();
    }

    private final void startAccountWebView() {
        startActivityForResult(AccountWebViewActivity.getIntent(this), AccountWebViewActivity.REQUEST_CODE);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        Subscription subscription = this.welcomeMessageSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.signInTaskStreamSubscription;
        if (subscription2 != null && subscription2.isUnsubscribed()) {
            subscription2.unsubscribe();
        }
        super.onDestroy();
    }
}
