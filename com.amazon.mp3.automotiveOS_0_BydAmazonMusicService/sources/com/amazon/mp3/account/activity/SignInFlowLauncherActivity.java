package com.amazon.mp3.account.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.preauth.PreAuthSplashEligibilityProvider;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.navigation.DeeplinkHandlerActivity;
import com.amazon.mp3.platform.providers.SessionIdProviderImpl;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.AnonymousRealTimeMTSEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.signin.RxSignInFlow;
import com.amazon.music.signin.SignInTaskCache;
import com.amazon.music.signin.SignInTaskId;
import com.google.android.material.button.MaterialButton;
import org.apache.commons.lang3.StringUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class SignInFlowLauncherActivity extends SignInFlowActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "SignInFlowLauncherActivity";
    private Context mContext;
    private String mDeviceId;
    private boolean mIsUserSSO;
    private PreAuthSplashEligibilityProvider mPreAuthSplashEligibilityProvider;
    private RxSignInFlow mRxSignInFlow;
    private Subscription mSignInTaskStreamSubscription;
    private Action1<SignInTaskId> onNext = new Action1<SignInTaskId>() { // from class: com.amazon.mp3.account.activity.SignInFlowLauncherActivity.1
        @Override // rx.functions.Action1
        public void call(final SignInTaskId taskId) {
            Log.info(SignInFlowLauncherActivity.TAG, "onNext recieved task name: %s with task id: %d", taskId.getTaskName(), Integer.valueOf(taskId.getTaskValue()));
            SignInTaskCache signInTaskCache = new SignInTaskCache(SignInFlowLauncherActivity.this.mContext);
            if (taskId.equals(SignInTaskId.SSO_SIGN_IN_START)) {
                if (signInTaskCache.isSignedIn()) {
                    SignInFlowLauncherActivity.this.mIsUserSSO = false;
                    SignInFlowLauncherActivity.this.mSignInTaskStreamSubscription.unsubscribe();
                    SignInFlowLauncherActivity.this.startProgressActivityWithNoTransitionAnimation();
                    return;
                } else {
                    SignInFlowLauncherActivity.this.mIsUserSSO = true;
                    signInTaskCache.saveIsUserSSO(SignInFlowLauncherActivity.this.mIsUserSSO);
                    return;
                }
            }
            if (taskId.equals(SignInTaskId.ACCOUNT_DATA_SAVED)) {
                SignInFlowLauncherActivity.this.mSignInTaskStreamSubscription.unsubscribe();
                if (SignInFlowLauncherActivity.this.mIsUserSSO) {
                    SignInFlowLauncherActivity.this.startActivityForResult(new Intent(SignInFlowLauncherActivity.this.mContext, (Class<?>) SsoWelcomeActivity.class), SsoWelcomeActivity.REQUEST_CODE);
                    signInTaskCache.saveSignInEvent();
                    return;
                }
                return;
            }
            if (taskId.equals(SignInTaskId.SIGN_IN_WITH_RECOVER_ACCOUNT_START)) {
                SignInFlowLauncherActivity.this.sendRecoverAccountFlexEvent("RECOVER_ACCOUNT_START_3P");
                return;
            }
            if (taskId.equals(SignInTaskId.SIGN_IN_WITH_DIALOG_COMPLETE) || taskId.equals(SignInTaskId.SIGN_IN_WITH_RECOVER_ACCOUNT_COMPLETE)) {
                signInTaskCache.saveSignInEvent();
                Log.debug(SignInFlowLauncherActivity.TAG, "User has seen MusicTOU. Setting hasSeenTOU to true");
                signInTaskCache.saveHasSeenMusicTOUEvent();
                Log.debug(SignInFlowLauncherActivity.TAG, "User has reached sign in dialog, setting hasReachedAuthPortal to true");
                SignInFlowLauncherActivity.this.mPreAuthSplashEligibilityProvider.setHasReachedAuthPortal(true);
                if (taskId.equals(SignInTaskId.SIGN_IN_WITH_RECOVER_ACCOUNT_COMPLETE)) {
                    SignInFlowLauncherActivity.this.sendRecoverAccountFlexEvent("RECOVER_ACCOUNT_SUCCESS_3P");
                }
                SignInFlowLauncherActivity.this.mSignInTaskStreamSubscription.unsubscribe();
                SignInFlowLauncherActivity.this.startProgressActivityWithNoTransitionAnimation();
            }
        }
    };
    Action0 onCompleted = new Action0() { // from class: com.amazon.mp3.account.activity.SignInFlowLauncherActivity.2
        @Override // rx.functions.Action0
        public void call() {
            SignInFlowLauncherActivity.this.mSignInTaskStreamSubscription.unsubscribe();
            SignInTaskStream.get().clearRxSubject();
            SignInFlowLauncherActivity.this.setResult(-1);
            SignInFlowLauncherActivity.this.finish();
        }
    };

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity
    protected void handleException() {
    }

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.logo_activity);
        super.onCreate(savedInstanceState);
        Context applicationContext = getApplicationContext();
        this.mContext = applicationContext;
        this.mDeviceId = AccountCredentialStorage.get(applicationContext).getDeviceId();
        this.mPreAuthSplashEligibilityProvider = new PreAuthSplashEligibilityProvider(this.mContext);
        sendLoadingPageViewed();
        boolean zIsUserRecognized = isUserRecognized();
        boolean zIsEligibleForSplash = this.mPreAuthSplashEligibilityProvider.isEligibleForSplash(zIsUserRecognized);
        String str = TAG;
        Log.info(str, "Checking pre auth splash, isEligibleForSplash: %s, isUserRecognized: %s", Boolean.valueOf(zIsEligibleForSplash), Boolean.valueOf(zIsUserRecognized));
        if (!zIsEligibleForSplash) {
            startRxSignInFlow();
            return;
        }
        if (Feature.pre_auth_splash_experiment_t2.isEnabled()) {
            Log.debug(str, "Pre-Auth Splash T2 is enabled");
            showPreAuthSplashExperimentT2();
        } else if (Feature.pre_auth_splash_experiment_t1.isEnabled()) {
            Log.debug(str, "Pre-Auth Splash T1 is enabled");
            showPreAuthSplashExperimentT1();
        } else {
            Log.debug(str, "Pre-Auth Splash showing Control");
            startRxSignInFlow();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.mSignInTaskStreamSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mSignInTaskStreamSubscription.unsubscribe();
    }

    private boolean isUserRecognized() {
        return new RxSignInFlow(this, AccountCredentialStorage.get(this).getCredentialPrefs(), new SignInFlowTasks(this).getSignInTasks(), Boolean.valueOf(PlatformUtil.isFireOS())).isRecognized();
    }

    public void signInOnInteraction(View view) {
        if (view != null && view.getTag() != null) {
            sendPreAuthSplashAcceptClicked(view.getTag().toString());
        }
        startRxSignInFlow();
    }

    private void startRxSignInFlow() {
        SignInFlowTasks signInFlowTasks = new SignInFlowTasks(this);
        resetTasksIfSignedOut(signInFlowTasks);
        Context context = this.mContext;
        RxSignInFlow rxSignInFlow = new RxSignInFlow(context, AccountCredentialStorage.get(context).getCredentialPrefs(), signInFlowTasks.getSignInTasks(), Boolean.valueOf(PlatformUtil.isFireOS()));
        this.mRxSignInFlow = rxSignInFlow;
        SignInTaskStream.get().setRxSubject(rxSignInFlow.signIn(this));
        this.mSignInTaskStreamSubscription = SignInTaskStream.get().getRxSubject().observeOn(AndroidSchedulers.mainThread()).subscribe(this.onNext, this.onError, this.onCompleted);
        if (this.mPreAuthSplashEligibilityProvider.hasReachedAuthPortal()) {
            return;
        }
        sendAuthPortalPageViewWithRefMarker();
    }

    private void resetTasksIfSignedOut(final SignInFlowTasks signInFlowTasks) {
        if (AccountCredentialUtil.get(this).isSignedOut()) {
            signInFlowTasks.clearExecutedTasks();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRecoverAccountFlexEvent(final String eventName) {
        MetricsLogger.sendEvent(FlexEvent.builder(eventName).build());
    }

    @Override // com.amazon.mp3.account.activity.SignInFlowActivity
    protected void handleMapEmptyValueException() {
        if (StringUtils.isBlank(new MAPAccountManager(this.mContext).getAccount())) {
            Log.error(TAG, "MapAccountManager returned null/empty account ID, user likely signed out and is no longer in recoverAccount state, starting regular sign in flow instead");
            startRxSignInFlow();
        } else {
            startSignInWithRecoverAccountFlow();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {
            Log.error(TAG, "Authentication error: starting recoverAccount flow");
            handleMapEmptyValueException();
            return;
        }
        if (requestCode == SsoWelcomeActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                startProgressActivityWithNoTransitionAnimation();
                return;
            } else {
                this.mSignInTaskStreamSubscription = SignInTaskStream.get().getRxSubject().observeOn(AndroidSchedulers.mainThread()).subscribe(this.onNext, this.onError, this.onCompleted);
                return;
            }
        }
        if (requestCode == ProgressActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                setResult(-1);
                if (DeeplinkHandlerActivity.isAppOpenedFromDeeplink()) {
                    Uri deeplinkUri = DeeplinkHandlerActivity.getDeeplinkUri();
                    MetricsLogger.sendDeeplinkInteractionEvent(deeplinkUri, deeplinkUri, DeeplinkHandlerActivity.getDeeplinkRefererUri(), "userSignedIn", DeeplinkHandlerActivity.getBranchLink());
                }
                if (this.mPreAuthSplashEligibilityProvider.hasReachedAuthPortal()) {
                    emitPreAuthFlexEvent();
                }
            } else if (resultCode == 3) {
                setResult(3);
            } else {
                if (resultCode == 4) {
                    startRxSignInFlow();
                    return;
                }
                int i = MusicLauncherActivity.RESULT_FINISH;
                if (resultCode == i) {
                    setResult(i);
                } else {
                    setResult(0);
                }
            }
            finish();
            return;
        }
        if (requestCode == AccountWebViewActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                setResult(3);
            } else if (resultCode == AccountWebViewActivity.RESULT_GO_TO_STAND_ALONE_EXPERIENCE) {
                setResult(MusicLauncherActivity.RESULT_FINISH);
            }
            finish();
        }
    }

    private void startSignInWithRecoverAccountFlow() {
        AccountCredentialUtil.get(this).removeCredentialsAndSettings();
        SignInTaskStream.get().setRxSubject(new RxSignInFlow(this, AccountCredentialStorage.get(this).getCredentialPrefs(), new SignInFlowTasks(this).getSignInTasks(), Boolean.valueOf(PlatformUtil.isFireOS())).signInWithRecoverAccountFlow(this));
        this.mSignInTaskStreamSubscription = SignInTaskStream.get().getRxSubject().observeOn(AndroidSchedulers.mainThread()).subscribe(this.onNext, this.onError, this.onCompleted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startProgressActivityWithNoTransitionAnimation() {
        if (SignInTaskStream.get().signInFlowIsInProgress()) {
            Intent intent = new Intent(this.mContext, (Class<?>) ProgressActivity.class);
            intent.addFlags(65536);
            RxSignInFlow rxSignInFlow = this.mRxSignInFlow;
            if (rxSignInFlow != null) {
                intent.putExtra("task_count", rxSignInFlow.getCountOfRemainingTasksToExecute());
            }
            startActivityForResult(intent, ProgressActivity.REQUEST_CODE);
            return;
        }
        setResult(0);
        finish();
    }

    private void showPreAuthSplashExperimentT2() {
        Log.debug(TAG, "Presenting pre auth splash t2 ...");
        setContentView(R.layout.pre_auth_splash_t2);
        sendPreAuthSplashPageViewed("_T2");
    }

    private void showPreAuthSplashExperimentT1() {
        Log.debug(TAG, "Presenting pre auth splash t1 ...");
        setContentView(R.layout.pre_auth_splash);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.signInSplashPrimaryButton);
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            materialButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.cyan_accent)));
        } else {
            materialButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_accent)));
        }
        sendPreAuthSplashPageViewed("_T1");
    }

    private void sendLoadingPageViewed() {
        MetricsHolder.getManager().handleEvent(new AnonymousRealTimeMTSEvent(UiPageViewEvent.builder(PageType.SIGNIN_APP_START_LOADING.toString()).build()));
    }

    private void sendPreAuthSplashPageViewed(String experimentTreatment) {
        MetricsHolder.getManager().handleEvent(new AnonymousRealTimeMTSEvent(UiPageViewEvent.builder(PageType.SIGNIN_APP_START_SPLASH.toString()).withExternalReference("dm_and_pre_auth_splash" + experimentTreatment).withCsSessionId(SessionIdProviderImpl.INSTANCE.getGeneratedSessionId(this.mContext)).build()));
    }

    private void sendPreAuthSplashAcceptClicked(String tag) {
        MetricsHolder.getManager().handleEvent(new AnonymousRealTimeMTSEvent(UiClickEvent.builder(ActionType.SIGNIN_APP_START_SPLASH_ACCEPT).withRefMarker("dm_and_pre_auth_splash").withCsSessionId(SessionIdProviderImpl.INSTANCE.getGeneratedSessionId(this.mContext)).withPageSubType(tag).build()));
    }

    private void sendAuthPortalPageViewWithRefMarker() {
        MetricsHolder.getManager().handleEvent(new AnonymousRealTimeMTSEvent(UiPageViewEvent.builder(PageType.SIGNIN_APP_START_AUTHPORTAL.toString()).withExternalReference("dm_and_pre_auth_splash").withCsSessionId(SessionIdProviderImpl.INSTANCE.getGeneratedSessionId(this.mContext)).build()));
    }

    private void emitPreAuthFlexEvent() {
        SessionIdProviderImpl.Companion companion = SessionIdProviderImpl.INSTANCE;
        String loginSessionId = companion.getLoginSessionId();
        if (StringUtils.isEmpty(loginSessionId)) {
            Log.info(TAG, "Could not retrieve login sessionId and not emitting flexevent");
            return;
        }
        String generatedSessionId = companion.getGeneratedSessionId(this.mContext);
        Log.debug(TAG, "Emitting Pre-Auth flexEvent with unauthSessionId: %s, loginSessionId: %s", generatedSessionId, loginSessionId);
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("PREAUTH_LOGIN_COMPLETE").withHcFlexStr1(generatedSessionId).withHcFlexStr2(loginSessionId).build());
    }
}
