package com.amazon.mp3.auto.signin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.autofill.HintConstants;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.activity.AccountDeregistrationDialogActivity;
import com.amazon.mp3.account.activity.AccountWebViewActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.auto.signin.fragment.AutomotiveCodeBasedSignInFragment;
import com.amazon.mp3.auto.signin.fragment.AutomotiveGeneralErrorFragment;
import com.amazon.mp3.auto.signin.fragment.AutomotiveLegalPageFragment;
import com.amazon.mp3.auto.signin.fragment.AutomotiveManuallySignInFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.signin.RxAutomotiveSignInFlow;
import com.amazon.music.signin.RxSignInTaskIdStream;
import com.amazon.music.signin.SignInTaskCache;
import com.amazon.music.signin.SignInTaskId;
import com.google.zxing.WriterException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: AutomotiveSignInFlowLauncherActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 c2\u00020\u0001:\u0002cdB\u0007¢\u0006\u0004\ba\u0010bJ\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0006H\u0002J\b\u0010\n\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0002J\b\u0010\f\u001a\u00020\u0006H\u0002J\b\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J\b\u0010\u000f\u001a\u00020\u0004H\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0002J\u0012\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0017\u001a\u00020\u0006H\u0014J\b\u0010\u0018\u001a\u00020\u0006H\u0014J\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020!J\u001a\u0010&\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010#J\u000e\u0010(\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u001fJ\u000e\u0010*\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u001fJ\u0006\u0010+\u001a\u00020\u0006J\u0006\u0010,\u001a\u00020\u0006J\"\u00102\u001a\u00020\u00062\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-2\b\u00101\u001a\u0004\u0018\u000100H\u0014J\u0006\u00103\u001a\u00020\u0006J\u0006\u00104\u001a\u00020\u0006J\u0006\u00105\u001a\u00020\u0006J\u0010\u00108\u001a\u00020\u001f2\b\u00107\u001a\u0004\u0018\u000106J\u0006\u00109\u001a\u00020\u0006R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010>\u001a\u00020=8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b>\u0010?R\u001c\u0010B\u001a\b\u0012\u0004\u0012\u00020A0@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u00148\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\u00198\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u001b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u001d8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010R\u001a\u00020Q8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010UR\u001d\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00040W8\u0006¢\u0006\f\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[R\u0016\u0010]\u001a\u00020\\8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010UR\u001c\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00140@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b`\u0010C¨\u0006e"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/amazon/mp3/auto/signin/AutomotiveError;", "error", "", "shouldSignOut", "", "showErrorPage", "initSubscriber", "initLinkCodeSubscriber", "startProgressActivity", "restartSignInFlow", "completeSignInFlow", "cancelSignInFlow", "isDeviceAuthorized", "isDeviceLimitsReached", "refreshAuthorizationStatus", "checkAuthorization", "showProgressBarCover", "hideProgressBarCover", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onResume", "onDestroy", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveManuallySignInFragment;", "getManuallySignInFragment", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveCodeBasedSignInFragment;", "getCodeBasedSignInFragment", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveLegalPageFragment;", "getLegalPageFragment", "", "getLinkCode", "Landroid/graphics/Bitmap;", "getQRCode", "Landroidx/fragment/app/Fragment;", "currentFragment", "targetFragment", "switchFragment", HintConstants.AUTOFILL_HINT_USERNAME, "setUsername", "password", "setPassword", "startRxSignInFlow", "generateCode", "", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "unsubscribeFromSignIn", "cancelLinkCodeGeneration", "authorizeDevice", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "getLegalPoliciesUri", "checkConnectivity", "Landroidx/fragment/app/FragmentManager;", "fragmentMgr", "Landroidx/fragment/app/FragmentManager;", "Lcom/amazon/music/signin/RxAutomotiveSignInFlow;", "signInFlow", "Lcom/amazon/music/signin/RxAutomotiveSignInFlow;", "Lrx/Subscriber;", "Lcom/amazon/music/signin/SignInTaskId;", "subscriber", "Lrx/Subscriber;", "Lrx/Subscription;", "signInTaskStreamSubscription", "Lrx/Subscription;", "credentialBundle", "Landroid/os/Bundle;", "manuallySignInFragment", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveManuallySignInFragment;", "codeBasedSignInFragment", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveCodeBasedSignInFragment;", "legalPageFragment", "Lcom/amazon/mp3/auto/signin/fragment/AutomotiveLegalPageFragment;", "lastFragment", "Landroidx/fragment/app/Fragment;", "Landroid/widget/RelativeLayout;", "progressBarContainer", "Landroid/widget/RelativeLayout;", "isPageHidden", "Z", "isWaitingDeAuthorization", "Landroidx/lifecycle/MutableLiveData;", "signInFlowLiveData", "Landroidx/lifecycle/MutableLiveData;", "getSignInFlowLiveData", "()Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/auto/signin/LinkCodeWrapper;", "linkCodeWrapper", "Lcom/amazon/mp3/auto/signin/LinkCodeWrapper;", "isSubscribedToLinkCode", "linkCodeSubscriber", "<init>", "()V", "Companion", "SignInFlowCancelAsyncTask", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotiveSignInFlowLauncherActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AutomotiveSignInFlowLauncherActivity.class.getSimpleName();
    private static boolean isSignInFlowInProgress;
    private AutomotiveCodeBasedSignInFragment codeBasedSignInFragment;
    private Bundle credentialBundle;
    private FragmentManager fragmentMgr;
    private boolean isPageHidden;
    private boolean isSubscribedToLinkCode;
    private boolean isWaitingDeAuthorization;
    private Fragment lastFragment;
    private AutomotiveLegalPageFragment legalPageFragment;
    private Subscriber<Bundle> linkCodeSubscriber;
    private LinkCodeWrapper linkCodeWrapper;
    private AutomotiveManuallySignInFragment manuallySignInFragment;
    private RelativeLayout progressBarContainer;
    private RxAutomotiveSignInFlow signInFlow;
    private final MutableLiveData<Boolean> signInFlowLiveData = new MutableLiveData<>();
    private Subscription signInTaskStreamSubscription;
    private Subscriber<SignInTaskId> subscriber;

    /* JADX INFO: compiled from: AutomotiveSignInFlowLauncherActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "arcusLegalPoliciesKey", "isSignInFlowInProgress", "", "legalPoliciesUri", "linkCodeTTL", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AutomotiveSignInFlowLauncherActivity.TAG;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automotive_sign_in);
        View viewFindViewById = findViewById(R.id.loading_progress_cover);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.loading_progress_cover)");
        this.progressBarContainer = (RelativeLayout) viewFindViewById;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.fragmentMgr = supportFragmentManager;
        this.credentialBundle = new Bundle();
        String registrationEndpoint = RxAutomotiveSignInFlow.getRegistrationEndpoint(getResources().getConfiguration().getLocales().get(0));
        Intrinsics.checkNotNullExpressionValue(registrationEndpoint, "getRegistrationEndpoint(…iguration.locales.get(0))");
        this.linkCodeWrapper = new LinkCodeWrapper(registrationEndpoint);
        this.codeBasedSignInFragment = new AutomotiveCodeBasedSignInFragment(this);
        this.manuallySignInFragment = new AutomotiveManuallySignInFragment(this);
        this.legalPageFragment = new AutomotiveLegalPageFragment(this);
        AutomotiveCodeBasedSignInFragment automotiveCodeBasedSignInFragment = this.codeBasedSignInFragment;
        if (automotiveCodeBasedSignInFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeBasedSignInFragment");
            automotiveCodeBasedSignInFragment = null;
        }
        this.lastFragment = automotiveCodeBasedSignInFragment;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.isPageHidden) {
            Fragment fragment = this.lastFragment;
            if (fragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lastFragment");
                fragment = null;
            }
            switchFragment(fragment, null);
            return;
        }
        Fragment fragment2 = this.lastFragment;
        if (fragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lastFragment");
            fragment2 = null;
        }
        switchFragment(null, fragment2);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        cancelLinkCodeGeneration();
        unsubscribeFromSignIn();
        super.onDestroy();
    }

    static /* synthetic */ void showErrorPage$default(AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity, AutomotiveError automotiveError, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        automotiveSignInFlowLauncherActivity.showErrorPage(automotiveError, z);
    }

    private final void showErrorPage(AutomotiveError error, boolean shouldSignOut) {
        if (isFinishing()) {
            return;
        }
        this.isPageHidden = false;
        FragmentManager fragmentManager = this.fragmentMgr;
        Fragment fragment = null;
        if (fragmentManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentMgr");
            fragmentManager = null;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "fragmentMgr.beginTransaction()");
        AutomotiveGeneralErrorFragment automotiveGeneralErrorFragment = new AutomotiveGeneralErrorFragment(this, error, shouldSignOut);
        Fragment fragment2 = this.lastFragment;
        if (fragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lastFragment");
        } else {
            fragment = fragment2;
        }
        fragmentTransactionBeginTransaction.hide(fragment).replace(R.id.sign_in_fragment_container, automotiveGeneralErrorFragment).commit();
        this.lastFragment = automotiveGeneralErrorFragment;
    }

    public final AutomotiveManuallySignInFragment getManuallySignInFragment() {
        AutomotiveManuallySignInFragment automotiveManuallySignInFragment = this.manuallySignInFragment;
        if (automotiveManuallySignInFragment != null) {
            return automotiveManuallySignInFragment;
        }
        Intrinsics.throwUninitializedPropertyAccessException("manuallySignInFragment");
        return null;
    }

    public final AutomotiveCodeBasedSignInFragment getCodeBasedSignInFragment() {
        AutomotiveCodeBasedSignInFragment automotiveCodeBasedSignInFragment = this.codeBasedSignInFragment;
        if (automotiveCodeBasedSignInFragment != null) {
            return automotiveCodeBasedSignInFragment;
        }
        Intrinsics.throwUninitializedPropertyAccessException("codeBasedSignInFragment");
        return null;
    }

    public final AutomotiveLegalPageFragment getLegalPageFragment() {
        AutomotiveLegalPageFragment automotiveLegalPageFragment = this.legalPageFragment;
        if (automotiveLegalPageFragment != null) {
            return automotiveLegalPageFragment;
        }
        Intrinsics.throwUninitializedPropertyAccessException("legalPageFragment");
        return null;
    }

    public final String getLinkCode() {
        LinkCodeWrapper linkCodeWrapper = this.linkCodeWrapper;
        if (linkCodeWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
            linkCodeWrapper = null;
        }
        return linkCodeWrapper.getLinkCode();
    }

    public final Bitmap getQRCode() {
        LinkCodeWrapper linkCodeWrapper = this.linkCodeWrapper;
        if (linkCodeWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
            linkCodeWrapper = null;
        }
        return linkCodeWrapper.getQrCode();
    }

    public final void switchFragment(Fragment currentFragment, Fragment targetFragment) {
        FragmentManager fragmentManager = this.fragmentMgr;
        if (fragmentManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentMgr");
            fragmentManager = null;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "fragmentMgr.beginTransaction()");
        if (currentFragment != null) {
            if (currentFragment instanceof AutomotiveGeneralErrorFragment) {
                fragmentTransactionBeginTransaction.remove(currentFragment);
            } else {
                fragmentTransactionBeginTransaction.hide(currentFragment);
            }
        }
        if (targetFragment != null) {
            if (targetFragment.isAdded()) {
                fragmentTransactionBeginTransaction.show(targetFragment);
            } else {
                fragmentTransactionBeginTransaction.replace(R.id.sign_in_fragment_container, targetFragment);
            }
            this.lastFragment = targetFragment;
        }
        fragmentTransactionBeginTransaction.commit();
    }

    public final void setUsername(String username) {
        Intrinsics.checkNotNullParameter(username, "username");
        Bundle bundle = this.credentialBundle;
        if (bundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
            bundle = null;
        }
        bundle.putString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME, username);
    }

    public final void setPassword(String password) {
        Intrinsics.checkNotNullParameter(password, "password");
        Bundle bundle = this.credentialBundle;
        if (bundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
            bundle = null;
        }
        bundle.putString("password", password);
    }

    public final synchronized void startRxSignInFlow() {
        if (!isSignInFlowInProgress) {
            isSignInFlowInProgress = true;
            this.signInFlowLiveData.setValue(Boolean.FALSE);
            SignInFlowTasks signInFlowTasks = new SignInFlowTasks(this);
            if (AccountCredentialUtil.get(this).isSignedOut()) {
                signInFlowTasks.clearExecutedTasks();
            }
            RxAutomotiveSignInFlow rxAutomotiveSignInFlow = new RxAutomotiveSignInFlow(getApplicationContext(), AccountCredentialStorage.get(getApplicationContext()).getCredentialPrefs(), signInFlowTasks.getSignInTasks());
            this.signInFlow = rxAutomotiveSignInFlow;
            Bundle bundle = this.credentialBundle;
            Subscriber<SignInTaskId> subscriber = null;
            if (bundle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
                bundle = null;
            }
            RxSignInTaskIdStream rxSignInTaskIdStreamSignIn = rxAutomotiveSignInFlow.signIn(this, bundle);
            Intrinsics.checkNotNullExpressionValue(rxSignInTaskIdStreamSignIn, "signInFlow.signIn(this, credentialBundle)");
            SignInTaskStream.get().setRxSubject(rxSignInTaskIdStreamSignIn);
            initSubscriber();
            Observable<SignInTaskId> observableObserveOn = SignInTaskStream.get().getRxSubject().doOnUnsubscribe(new Action0() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$$ExternalSyntheticLambda1
                @Override // rx.functions.Action0
                public final void call() {
                    AutomotiveSignInFlowLauncherActivity.m380startRxSignInFlow$lambda2(this.f$0);
                }
            }).observeOn(AndroidSchedulers.mainThread());
            Subscriber<SignInTaskId> subscriber2 = this.subscriber;
            if (subscriber2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subscriber");
            } else {
                subscriber = subscriber2;
            }
            Subscription subscriptionSubscribe = observableObserveOn.subscribe((Subscriber<? super SignInTaskId>) subscriber);
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe, "get().rxSubject\n        …   .subscribe(subscriber)");
            this.signInTaskStreamSubscription = subscriptionSubscribe;
        } else {
            Log.debug(TAG, "Waiting for response from MAP, should not start another sign in flow right now");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startRxSignInFlow$lambda-2, reason: not valid java name */
    public static final void m380startRxSignInFlow$lambda2(AutomotiveSignInFlowLauncherActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        isSignInFlowInProgress = false;
        this$0.signInFlowLiveData.postValue(Boolean.TRUE);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initSubscriber$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutomotiveSignInFlowLauncherActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\n"}, d2 = {"com/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity$initSubscriber$1", "Lrx/Subscriber;", "Lcom/amazon/music/signin/SignInTaskId;", "onCompleted", "", "onError", "throwable", "", "onNext", "taskId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class C03011 extends Subscriber<SignInTaskId> {
        C03011() {
        }

        @Override // rx.Observer
        public void onCompleted() {
            AutomotiveSignInFlowLauncherActivity.this.isPageHidden = false;
            Subscription subscription = AutomotiveSignInFlowLauncherActivity.this.signInTaskStreamSubscription;
            if (subscription == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInTaskStreamSubscription");
                subscription = null;
            }
            subscription.unsubscribe();
            SignInTaskStream.get().clearRxSubject();
        }

        @Override // rx.Observer
        public void onError(Throwable throwable) {
            Log.error(AutomotiveSignInFlowLauncherActivity.INSTANCE.getTAG(), "Failure to sign in through MAP", throwable);
            AutomotiveSignInFlowLauncherActivity.this.unsubscribeFromSignIn();
            AutomotiveSignInFlowLauncherActivity.showErrorPage$default(AutomotiveSignInFlowLauncherActivity.this, AutomotiveError.AUTHENTICATION_ERROR, false, 2, null);
        }

        @Override // rx.Observer
        public void onNext(SignInTaskId taskId) {
            SignInTaskCache signInTaskCache = new SignInTaskCache(AutomotiveSignInFlowLauncherActivity.this.getApplicationContext());
            Subscription subscription = null;
            if (Intrinsics.areEqual(taskId, SignInTaskId.SIGN_IN_WITH_NATIVE_SCREEN_COMPLETE) ? true : Intrinsics.areEqual(taskId, SignInTaskId.SIGN_IN_WITH_LINK_CODE_COMPLETE)) {
                signInTaskCache.saveSignInEvent();
                Subscription subscription2 = AutomotiveSignInFlowLauncherActivity.this.signInTaskStreamSubscription;
                if (subscription2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("signInTaskStreamSubscription");
                } else {
                    subscription = subscription2;
                }
                subscription.unsubscribe();
                AutomotiveSignInFlowLauncherActivity.this.startProgressActivity();
                return;
            }
            if (Intrinsics.areEqual(taskId, SignInTaskId.SSO_SIGN_IN_START)) {
                if (signInTaskCache.isSignedIn()) {
                    Subscription subscription3 = AutomotiveSignInFlowLauncherActivity.this.signInTaskStreamSubscription;
                    if (subscription3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("signInTaskStreamSubscription");
                    } else {
                        subscription = subscription3;
                    }
                    subscription.unsubscribe();
                    if (!AutomotiveSignInFlowLauncherActivity.this.isWaitingDeAuthorization) {
                        AutomotiveSignInFlowLauncherActivity.this.startProgressActivity();
                        return;
                    }
                    Observable observableObserveOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initSubscriber$1$$ExternalSyntheticLambda0
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            AutomotiveSignInFlowLauncherActivity.C03011.m385onNext$lambda0((Subscriber) obj);
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
                    final AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = AutomotiveSignInFlowLauncherActivity.this;
                    Action1 action1 = new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initSubscriber$1$$ExternalSyntheticLambda1
                        @Override // rx.functions.Action1
                        public final void call(Object obj) throws Exception {
                            AutomotiveSignInFlowLauncherActivity.C03011.m386onNext$lambda1(automotiveSignInFlowLauncherActivity, (Void) obj);
                        }
                    };
                    Action1<Throwable> action12 = new Action1() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initSubscriber$1$$ExternalSyntheticLambda2
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            AutomotiveSignInFlowLauncherActivity.C03011.m387onNext$lambda2((Throwable) obj);
                        }
                    };
                    final AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity2 = AutomotiveSignInFlowLauncherActivity.this;
                    observableObserveOn.subscribe(action1, action12, new Action0() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initSubscriber$1$$ExternalSyntheticLambda3
                        @Override // rx.functions.Action0
                        public final void call() {
                            AutomotiveSignInFlowLauncherActivity.C03011.m388onNext$lambda3(automotiveSignInFlowLauncherActivity2);
                        }
                    });
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(taskId, SignInTaskId.SIGN_IN_CODE_INVALID)) {
                AutomotiveSignInFlowLauncherActivity.this.cancelLinkCodeGeneration();
                AutomotiveSignInFlowLauncherActivity.this.unsubscribeFromSignIn();
                AutomotiveSignInFlowLauncherActivity.this.generateCode();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onNext$lambda-0, reason: not valid java name */
        public static final void m385onNext$lambda0(Subscriber subscriber) {
            subscriber.onNext(null);
            subscriber.onCompleted();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onNext$lambda-1, reason: not valid java name */
        public static final void m386onNext$lambda1(AutomotiveSignInFlowLauncherActivity this$0, Void r1) throws Exception {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.showProgressBarCover();
            this$0.refreshAuthorizationStatus();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onNext$lambda-2, reason: not valid java name */
        public static final void m387onNext$lambda2(Throwable th) {
            Log.error(AutomotiveSignInFlowLauncherActivity.INSTANCE.getTAG(), "Fail to refresh device authorization status", th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onNext$lambda-3, reason: not valid java name */
        public static final void m388onNext$lambda3(AutomotiveSignInFlowLauncherActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.checkAuthorization();
        }
    }

    private final void initSubscriber() {
        this.subscriber = new C03011();
    }

    public final synchronized void generateCode() {
        if (!this.isSubscribedToLinkCode) {
            this.isSubscribedToLinkCode = true;
            this.signInFlowLiveData.setValue(Boolean.FALSE);
            SignInFlowTasks signInFlowTasks = new SignInFlowTasks(this);
            if (AccountCredentialUtil.get(this).isSignedOut()) {
                signInFlowTasks.clearExecutedTasks();
            }
            this.signInFlow = new RxAutomotiveSignInFlow(getApplicationContext(), AccountCredentialStorage.get(getApplicationContext()).getCredentialPrefs(), signInFlowTasks.getSignInTasks());
            initLinkCodeSubscriber();
            Bundle bundle = new Bundle();
            LinkCodeWrapper linkCodeWrapper = this.linkCodeWrapper;
            Subscriber<Bundle> subscriber = null;
            if (linkCodeWrapper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
                linkCodeWrapper = null;
            }
            bundle.putString(MAPAccountManager.KEY_LINK_CODE_DOMAIN, linkCodeWrapper.getLoginDomain());
            bundle.putLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, 600000L);
            RxAutomotiveSignInFlow rxAutomotiveSignInFlow = this.signInFlow;
            if (rxAutomotiveSignInFlow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInFlow");
                rxAutomotiveSignInFlow = null;
            }
            Observable<Bundle> observableObserveOn = rxAutomotiveSignInFlow.executeRetrieveSignInLinkCode(bundle).observeOn(Schedulers.io());
            LinkCodeWrapper linkCodeWrapper2 = this.linkCodeWrapper;
            if (linkCodeWrapper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
                linkCodeWrapper2 = null;
            }
            Observable<Bundle> observableDelaySubscription = observableObserveOn.delaySubscription(linkCodeWrapper2.getTimeToLive(), TimeUnit.MILLISECONDS);
            Subscriber<Bundle> subscriber2 = this.linkCodeSubscriber;
            if (subscriber2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeSubscriber");
            } else {
                subscriber = subscriber2;
            }
            observableDelaySubscription.subscribe((Subscriber<? super Bundle>) subscriber);
        } else {
            Log.error(TAG, "Already subscribed to link code generator. Cannot attempt to resubscribe.");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initLinkCodeSubscriber$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutomotiveSignInFlowLauncherActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\n"}, d2 = {"com/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity$initLinkCodeSubscriber$1", "Lrx/Subscriber;", "Landroid/os/Bundle;", "onCompleted", "", "onError", "throwable", "", "onNext", "codeBundle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 extends Subscriber<Bundle> {
        AnonymousClass1() {
        }

        @Override // rx.Observer
        public void onCompleted() {
            Log.debug(AutomotiveSignInFlowLauncherActivity.INSTANCE.getTAG(), "Link code successfully retrieved");
            Subscriber subscriber = AutomotiveSignInFlowLauncherActivity.this.linkCodeSubscriber;
            if (subscriber == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeSubscriber");
                subscriber = null;
            }
            subscriber.unsubscribe();
            AutomotiveSignInFlowLauncherActivity.this.isSubscribedToLinkCode = false;
            final AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = AutomotiveSignInFlowLauncherActivity.this;
            automotiveSignInFlowLauncherActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initLinkCodeSubscriber$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AutomotiveSignInFlowLauncherActivity.AnonymousClass1.m382onCompleted$lambda0(automotiveSignInFlowLauncherActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onCompleted$lambda-0, reason: not valid java name */
        public static final void m382onCompleted$lambda0(AutomotiveSignInFlowLauncherActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.unsubscribeFromSignIn();
            this$0.generateCode();
            this$0.startRxSignInFlow();
        }

        @Override // rx.Observer
        public void onError(Throwable throwable) {
            Log.error(AutomotiveSignInFlowLauncherActivity.INSTANCE.getTAG(), "Link code retrieval unsuccessful");
            if (throwable != null) {
                throwable.printStackTrace();
            }
            AutomotiveSignInFlowLauncherActivity.this.cancelLinkCodeGeneration();
            AutomotiveSignInFlowLauncherActivity.showErrorPage$default(AutomotiveSignInFlowLauncherActivity.this, AutomotiveError.AUTHENTICATION_ERROR, false, 2, null);
        }

        @Override // rx.Observer
        public void onNext(final Bundle codeBundle) {
            if (codeBundle != null) {
                final AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = AutomotiveSignInFlowLauncherActivity.this;
                automotiveSignInFlowLauncherActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$initLinkCodeSubscriber$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() throws WriterException {
                        AutomotiveSignInFlowLauncherActivity.AnonymousClass1.m383onNext$lambda1(automotiveSignInFlowLauncherActivity, codeBundle);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onNext$lambda-1, reason: not valid java name */
        public static final void m383onNext$lambda1(AutomotiveSignInFlowLauncherActivity this$0, Bundle bundle) throws WriterException {
            LinkCodeWrapper linkCodeWrapper;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            LinkCodeWrapper linkCodeWrapper2 = this$0.linkCodeWrapper;
            LinkCodeWrapper linkCodeWrapper3 = null;
            if (linkCodeWrapper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
                linkCodeWrapper = null;
            } else {
                linkCodeWrapper = linkCodeWrapper2;
            }
            String string = bundle.getString(MAPAccountManager.KEY_LINK_CODE, "");
            Intrinsics.checkNotNullExpressionValue(string, "codeBundle.getString(MAP…anager.KEY_LINK_CODE, \"\")");
            linkCodeWrapper.setLinkCodeResponse(string, bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE), bundle.getLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL));
            AutomotiveCodeBasedSignInFragment automotiveCodeBasedSignInFragment = this$0.codeBasedSignInFragment;
            if (automotiveCodeBasedSignInFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("codeBasedSignInFragment");
                automotiveCodeBasedSignInFragment = null;
            }
            automotiveCodeBasedSignInFragment.setCode(this$0.getLinkCode());
            Bundle bundle2 = this$0.credentialBundle;
            if (bundle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
                bundle2 = null;
            }
            bundle2.putString(MAPAccountManager.KEY_LINK_CODE, this$0.getLinkCode());
            Bundle bundle3 = this$0.credentialBundle;
            if (bundle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
                bundle3 = null;
            }
            LinkCodeWrapper linkCodeWrapper4 = this$0.linkCodeWrapper;
            if (linkCodeWrapper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
            } else {
                linkCodeWrapper3 = linkCodeWrapper4;
            }
            bundle3.putLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL, linkCodeWrapper3.getPollingInterval());
        }
    }

    private final void initLinkCodeSubscriber() {
        this.linkCodeSubscriber = new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startProgressActivity() {
        if (SignInTaskStream.get().signInFlowIsInProgress()) {
            this.isPageHidden = true;
            Intent intent = new Intent(getApplicationContext(), (Class<?>) AutomotiveProgressActivity.class);
            intent.addFlags(65536);
            RxAutomotiveSignInFlow rxAutomotiveSignInFlow = this.signInFlow;
            if (rxAutomotiveSignInFlow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInFlow");
                rxAutomotiveSignInFlow = null;
            }
            intent.putExtra("Task_Count", rxAutomotiveSignInFlow.getCountOfRemainingTasksToExecute());
            startActivityForResult(intent, AutomotiveProgressActivity.INSTANCE.getREQUEST_CODE());
            return;
        }
        sendBroadcast(new Intent("com.amazon.mp3.MAP_SignIn_Cancel"));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AutomotiveProgressActivity.INSTANCE.getREQUEST_CODE()) {
            if (resultCode == -1) {
                if (isDeviceAuthorized()) {
                    completeSignInFlow();
                    return;
                } else {
                    checkAuthorization();
                    return;
                }
            }
            if (resultCode == 0) {
                cancelSignInFlow();
                return;
            } else {
                if (resultCode != 3) {
                    return;
                }
                restartSignInFlow();
                return;
            }
        }
        if (requestCode == AccountWebViewActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                restartSignInFlow();
            }
        } else {
            if (requestCode == LibraryDeviceAuthorizationWebViewActivity.REQUEST_CODE) {
                hideProgressBarCover();
                if (resultCode == -1) {
                    completeSignInFlow();
                    return;
                } else {
                    if (resultCode != 0) {
                        return;
                    }
                    showErrorPage$default(this, AutomotiveError.TRANSFER_AUTHORIZATION_ERROR, false, 2, null);
                    return;
                }
            }
            if (requestCode == AccountDeregistrationDialogActivity.REQUEST_CODE) {
                finish();
            }
        }
    }

    private final void restartSignInFlow() {
        Subscription subscription = this.signInTaskStreamSubscription;
        if (subscription != null) {
            if (subscription == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInTaskStreamSubscription");
                subscription = null;
            }
            subscription.unsubscribe();
        }
        SignInTaskStream.get().clearRxSubject();
        startRxSignInFlow();
    }

    private final void completeSignInFlow() {
        Subscriber<SignInTaskId> subscriber = this.subscriber;
        if (subscriber == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subscriber");
            subscriber = null;
        }
        subscriber.onCompleted();
        sendBroadcast(new Intent("com.amazon.mp3.MAP_SignIn_Complete"));
        finish();
    }

    private final void cancelSignInFlow() {
        unsubscribeFromSignIn();
        new SignInFlowCancelAsyncTask(this).execute(new Void[0]);
    }

    public final void unsubscribeFromSignIn() {
        if (this.signInTaskStreamSubscription != null) {
            RxAutomotiveSignInFlow rxAutomotiveSignInFlow = this.signInFlow;
            Subscription subscription = null;
            if (rxAutomotiveSignInFlow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInFlow");
                rxAutomotiveSignInFlow = null;
            }
            rxAutomotiveSignInFlow.cancelLinkCodePollingSubscriber();
            Subscription subscription2 = this.signInTaskStreamSubscription;
            if (subscription2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signInTaskStreamSubscription");
            } else {
                subscription = subscription2;
            }
            subscription.unsubscribe();
        }
        SignInTaskStream.get().clearRxSubject();
    }

    public final void cancelLinkCodeGeneration() {
        Subscriber<Bundle> subscriber = this.linkCodeSubscriber;
        Bundle bundle = null;
        if (subscriber == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linkCodeSubscriber");
            subscriber = null;
        }
        subscriber.unsubscribe();
        this.isSubscribedToLinkCode = false;
        LinkCodeWrapper linkCodeWrapper = this.linkCodeWrapper;
        if (linkCodeWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linkCodeWrapper");
            linkCodeWrapper = null;
        }
        linkCodeWrapper.resetTTLAndPolling();
        Bundle bundle2 = this.credentialBundle;
        if (bundle2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("credentialBundle");
        } else {
            bundle = bundle2;
        }
        bundle.clear();
        Log.debug(TAG, "Unsubscribed from link code generator.");
    }

    /* JADX INFO: compiled from: AutomotiveSignInFlowLauncherActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J'\u0010\b\u001a\u0004\u0018\u00010\u00022\u0016\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\n\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0014R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity$SignInFlowCancelAsyncTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "(Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;)V", "activityReference", "Ljava/lang/ref/WeakReference;", "doInBackground", "params", "", "([Ljava/lang/Void;)Ljava/lang/Void;", "onPostExecute", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SignInFlowCancelAsyncTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<AutomotiveSignInFlowLauncherActivity> activityReference;

        public SignInFlowCancelAsyncTask(AutomotiveSignInFlowLauncherActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activityReference = new WeakReference<>(activity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            Intrinsics.checkNotNullParameter(params, "params");
            AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.activityReference.get();
            Context applicationContext = automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getApplicationContext();
            if (applicationContext != null) {
                AccountDetailStorage.get(applicationContext).removeAll();
                DeviceAuthorizationManager.getInstance().removeAll();
                AccountCredentialStorage.get(applicationContext).removeAll();
                AccountManagerSingleton.get().onUserSignedOut();
                new SignInTaskCache(applicationContext).saveSignOutEvent();
                new SignInFlowTasks(applicationContext).clearExecutedTasks();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void params) {
            AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.activityReference.get();
            if (automotiveSignInFlowLauncherActivity == null) {
                return;
            }
            automotiveSignInFlowLauncherActivity.sendBroadcast(new Intent("com.amazon.mp3.MAP_SignIn_Cancel"));
            automotiveSignInFlowLauncherActivity.finish();
        }
    }

    public final void authorizeDevice() {
        Log.debug(TAG, "need to authorize this device");
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AutomotiveSignInFlowLauncherActivity.m377authorizeDevice$lambda4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: authorizeDevice$lambda-4, reason: not valid java name */
    public static final void m377authorizeDevice$lambda4(AutomotiveSignInFlowLauncherActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(this$0.getApplicationContext());
        if (deviceAuthorizationIntent == null) {
            return;
        }
        this$0.startActivityForResult(deviceAuthorizationIntent, LibraryDeviceAuthorizationWebViewActivity.REQUEST_CODE);
    }

    private final boolean isDeviceAuthorized() {
        return DeviceAuthorizationManager.getInstance().isAuthorized();
    }

    private final boolean isDeviceLimitsReached() {
        return DeviceAuthorizationManager.getInstance().getRemainingDeviceAuthorizations() <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshAuthorizationStatus() throws Exception {
        DeviceAuthorizationManager.getInstance().refresh();
        AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAuthorization() {
        boolean z = true;
        if (isDeviceLimitsReached()) {
            Log.debug(TAG, "checkAuthorization: Device limit reached");
            showErrorPage(AutomotiveError.AUTHORIZATION_ERROR, true);
            hideProgressBarCover();
        } else {
            authorizeDevice();
            z = false;
        }
        this.isWaitingDeAuthorization = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showProgressBarCover() {
        runOnUiThread(new Runnable() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AutomotiveSignInFlowLauncherActivity.m379showProgressBarCover$lambda5(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showProgressBarCover$lambda-5, reason: not valid java name */
    public static final void m379showProgressBarCover$lambda5(AutomotiveSignInFlowLauncherActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RelativeLayout relativeLayout = this$0.progressBarContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            relativeLayout = null;
        }
        relativeLayout.setVisibility(0);
    }

    private final void hideProgressBarCover() {
        runOnUiThread(new Runnable() { // from class: com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AutomotiveSignInFlowLauncherActivity.m378hideProgressBarCover$lambda6(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hideProgressBarCover$lambda-6, reason: not valid java name */
    public static final void m378hideProgressBarCover$lambda6(AutomotiveSignInFlowLauncherActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RelativeLayout relativeLayout = this$0.progressBarContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            relativeLayout = null;
        }
        relativeLayout.setVisibility(8);
    }

    public final String getLegalPoliciesUri(Configuration configuration) {
        String strString;
        return (configuration == null || (strString = configuration.string("legal_terms_and_policies_uri", "https://www.amazon.com/gp/help/customer/display.html?nodeId=200738950&amp;view-type=content-only")) == null) ? "https://www.amazon.com/gp/help/customer/display.html?nodeId=200738950&amp;view-type=content-only" : strString;
    }

    public final void checkConnectivity() {
        if (ConnectivityUtil.hasAnyInternetConnection(getApplicationContext())) {
            return;
        }
        showErrorPage$default(this, AutomotiveError.CONNECTIVITY_ERROR, false, 2, null);
    }
}
