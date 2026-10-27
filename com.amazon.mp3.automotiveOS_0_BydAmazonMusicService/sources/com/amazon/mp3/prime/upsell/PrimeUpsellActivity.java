package com.amazon.mp3.prime.upsell;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.activity.SsoWelcomeActivity;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.BaseWebActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.prime.upsell.PrimeDialogFragment;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.upsellweb.PrimeOfferPageWebTargetBuilderFactory;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.SplashScreen;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.events.NavigationAppEvent;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeUpsellActivity extends BaseWebActivity implements PrimeDialogFragment.PrimeDialogListener {
    public static final int PRIME_UPSELL_EXTERNAL_BROWSER_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "PrimeUpsellActivity";
    private Context mContext;
    private PrimeUpsellDialogFragment mDialogFragment;
    private PrimeUpsellFormFragment mFormFragment;
    private WebTarget mWebTarget;
    private long mFinishUpsellJobId = -1;
    private boolean mShowDialogBeforeWebTarget = false;

    public enum PrimeUpsellResult {
        EXPLORE_PRIME_MUSIC,
        UPSELL_SUCCESS,
        UPSELL_DEFER,
        UPSELL_BACK_BUTTON
    }

    public enum UpsellWebEventEnum {
        EXPLORE_PRIME_MUSIC,
        CUSTOMER_UPGRADED,
        CUSTOMER_DEFERRED,
        SIGNUP_ERROR,
        CONTINUE_TO_SIGN_UP
    }

    public static Intent getWebIntent(Context context, WebTarget webTarget) {
        return BaseWebActivity.getTargetStartEvent(context, PrimeUpsellActivity.class, webTarget, "com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
    }

    public static Intent getDialogBeforeWebIntent(Context context, WebTarget webTarget) {
        Intent webIntent = getWebIntent(context, webTarget);
        Bundle extras = webIntent.getExtras();
        extras.putBoolean("com.amazon.mp3.extra.PrimeUpsellActivity.SHOW_DIALOG_BEFORE_WEB_TARGET", true);
        webIntent.putExtras(extras);
        return webIntent;
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getApplicationContext();
        this.mShowDialogBeforeWebTarget = getIntent().getBooleanExtra("com.amazon.mp3.extra.PrimeUpsellActivity.SHOW_DIALOG_BEFORE_WEB_TARGET", false);
        WebTarget webTarget = (WebTarget) getIntent().getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        this.mWebTarget = webTarget;
        if (webTarget == null) {
            throw new IllegalArgumentException("Cannot start Prime Upsell Activity without a web target.");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View viewOnCreateView = super.onCreateView(name, context, attrs);
        setupPublisherForJSBridge();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        init();
        SplashScreen.PRIME_UPSELL.setShown(null);
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
            return;
        }
        if (AccountCredentialUtil.get().isSignedIn()) {
            if (this.mShowDialogBeforeWebTarget) {
                showUpsellDialog();
                return;
            } else {
                showUpsellWebView(this.mWebTarget);
                return;
            }
        }
        AccountCredentialUtil.get(this).synchronizeWithDeviceRegistration(this, shouldEnsureRegistration());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.upsell.PrimeUpsellActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum;

        static {
            int[] iArr = new int[UpsellWebEventEnum.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum = iArr;
            try {
                iArr[UpsellWebEventEnum.EXPLORE_PRIME_MUSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.CUSTOMER_UPGRADED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.CUSTOMER_DEFERRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.SIGNUP_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.CONTINUE_TO_SIGN_UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(final WebEvent webEvent) {
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.valueOf(webEvent.id()).ordinal()];
        if (i == 1) {
            onCustomerUpgradeSuccess(PrimeUpsellResult.EXPLORE_PRIME_MUSIC);
            return;
        }
        if (i == 2) {
            AccountDetailStorage.get().setPrimeMusicAccessible(true);
            onCustomerUpgradeSuccess(PrimeUpsellResult.UPSELL_SUCCESS);
        } else if (i == 3) {
            onCustomerDeferred();
        } else if (i == 4) {
            onSignupError();
        } else {
            if (i != 5) {
                return;
            }
            onContinueToSignUp();
        }
    }

    private boolean isOfferPage(final WebTarget webTarget) {
        return webTarget != null && webTarget.getUrl().contains("signup");
    }

    private void showUpsellWebView(final WebTarget webTarget) {
        PrimeUpsellFormFragment primeUpsellFormFragment = (PrimeUpsellFormFragment) getSupportFragmentManager().findFragmentByTag("TAG_FORM_FRAGMENT");
        this.mFormFragment = primeUpsellFormFragment;
        if (primeUpsellFormFragment == null) {
            if (shouldSkipWebView(webTarget)) {
                launchUrlOutsideApp(webTarget.getUrl());
                return;
            } else {
                this.mFormFragment = PrimeUpsellFormFragment.newInstance(webTarget);
                getSupportFragmentManager().beginTransaction().add(R.id.FragmentContainer, this.mFormFragment, "TAG_FORM_FRAGMENT").commit();
            }
        }
        this.mFormFragment.setPublisher(this.mWebEventPublisher);
    }

    private boolean shouldSkipWebView(WebTarget webTarget) {
        return isOfferPage(webTarget) && AccountDetailUtil.getMusicTerritoryOfResidence().equals(MusicTerritory.INDIA.value);
    }

    private void launchUrlOutsideApp(final String url) {
        startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(url)), PRIME_UPSELL_EXTERNAL_BROWSER_REQUEST_CODE);
    }

    private void showUpsellDialog() {
        NavigationAppEvent.builder("PrimeBrowseUpsellFTU").publish();
        this.mDialogFragment = getDialogFragment();
        this.mDialogFragment.show(getSupportFragmentManager().beginTransaction(), "TAG_DIALOG_FRAGMENT");
    }

    private PrimeUpsellDialogFragment getDialogFragment() {
        PrimeUpsellDialogFragment primeUpsellDialogFragment = (PrimeUpsellDialogFragment) getSupportFragmentManager().findFragmentByTag("TAG_DIALOG_FRAGMENT");
        if (primeUpsellDialogFragment != null) {
            removeFragmentFromFragmentManager(primeUpsellDialogFragment);
        }
        return PrimeUpsellDialogFragment.newInstance(new Bundle());
    }

    private void removeFragmentFromFragmentManager(PrimeUpsellDialogFragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void hideUpsellDialog() {
        PrimeUpsellDialogFragment primeUpsellDialogFragment = (PrimeUpsellDialogFragment) getSupportFragmentManager().findFragmentByTag("TAG_DIALOG_FRAGMENT");
        this.mDialogFragment = primeUpsellDialogFragment;
        if (primeUpsellDialogFragment != null) {
            removeFragmentFromFragmentManager(primeUpsellDialogFragment);
        }
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment.PrimeDialogListener
    public void onAccept() {
        hideUpsellDialog();
        showUpsellWebView(this.mWebTarget);
    }

    @Override // com.amazon.mp3.prime.upsell.PrimeDialogFragment.PrimeDialogListener
    public void onDismiss() {
        onCustomerDeferred();
    }

    private void onCustomerUpgradeSuccess(final PrimeUpsellResult primeUpsellResult) {
        if (AmazonApplication.getCapabilities().shouldForceRefreshBrowseTab()) {
            AccountManagerSingleton.get().registerListener(new AccountStateChangeListener() { // from class: com.amazon.mp3.prime.upsell.PrimeUpsellActivity.1
                @Override // com.amazon.music.account.AccountStateChangeListener
                public void onAccountStateChange(final Set<AccountStateField> set) {
                    MusicHomeActivity.startPrimeSectionWithRefresh(PrimeUpsellActivity.this);
                }
            });
            finishWithResult(primeUpsellResult);
        } else {
            shouldLaunchBrowseHome();
            finishWithResult(primeUpsellResult);
        }
    }

    private void shouldLaunchBrowseHome() {
        if (isTaskRoot()) {
            BrushFragmentNavigation.navigateToHome(this);
        }
    }

    private void finishWithResult(PrimeUpsellResult result) {
        if (this.mFinishUpsellJobId == -1) {
            this.mFinishUpsellJobId = addJob(new FinishPrimeUpsellJob(result));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        PrimeUpsellFormFragment primeUpsellFormFragment = this.mFormFragment;
        if (primeUpsellFormFragment == null || !primeUpsellFormFragment.handleBackPress()) {
            super.onBackPressed();
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (jobId == this.mFinishUpsellJobId) {
            Intent intent = new Intent();
            intent.putExtra("com.amazon.mp3.extra.PrimeUpsellActivity.PrimeUpsellResult", ((FinishPrimeUpsellJob) job).getUpsellResult());
            setResult(-1, intent);
            finish();
            this.mFinishUpsellJobId = -1L;
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SsoWelcomeActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                init();
                return;
            } else {
                AccountCredentialUtil.get(this).synchronizeWithDeviceRegistration(this, shouldEnsureRegistration());
                return;
            }
        }
        if (requestCode == PrimeUpsellFormFragment.PRIME_UPSELL_EXTERNAL_BROWSER_REQUEST_CODE) {
            finish();
            BrushFragmentNavigation.navigateToHome(this);
        }
    }

    private void onCustomerDeferred() {
        shouldLaunchBrowseHome();
        finishWithResult(PrimeUpsellResult.UPSELL_DEFER);
    }

    private void onSignupError() {
        Log.warning(TAG, "Received an error during the prime signup process");
        setResult(2);
        getSupportFragmentManager().beginTransaction().remove(this.mFormFragment).commit();
        PrimeSignupErrorDialogFragment.newInstance(false).show(getSupportFragmentManager(), (String) null);
    }

    private void onContinueToSignUp() {
        WebTarget webTargetBuild = new PrimeOfferPageWebTargetBuilderFactory(this.mContext).newBuilder().withReportingParameters("first_login", "join prime button").build();
        this.mWebTarget = webTargetBuild;
        this.mFormFragment.resetWebTarget(webTargetBuild);
        init();
    }
}
