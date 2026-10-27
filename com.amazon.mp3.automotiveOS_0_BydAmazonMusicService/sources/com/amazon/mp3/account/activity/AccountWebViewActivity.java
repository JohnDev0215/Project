package com.amazon.mp3.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.activity.BaseWebActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.navigation.webtargetbuilder.AccountWebTargetBuilder;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;

/* JADX INFO: loaded from: classes3.dex */
public class AccountWebViewActivity extends BaseWebActivity implements AccountSetUpDialogListener, AccountWebViewListener {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    public static final int RESULT_GO_TO_STAND_ALONE_EXPERIENCE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "AccountWebViewActivity";
    private WebTarget mWebTarget;
    private AccountWebViewFragment mWebViewFragment;

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(final WebEvent webEvent) {
    }

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, (Class<?>) AccountWebViewActivity.class);
        intent.setFlags(603979776);
        return intent;
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        this.mWebTarget = new AccountWebTargetBuilder(this).build();
        showWebView();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        showWebView(false);
    }

    private void showWebView() {
        showWebView(false);
    }

    private void showWebView(final boolean shouldForceRefresh) {
        AccountWebViewFragment accountWebViewFragment = (AccountWebViewFragment) getSupportFragmentManager().findFragmentByTag("AccountWebViewActivity.TAG_FORM_FRAGMENT");
        this.mWebViewFragment = accountWebViewFragment;
        if (accountWebViewFragment == null) {
            setUpNewFragment();
        } else if (shouldForceRefresh) {
            setUpRefreshFragment();
        }
    }

    private void setUpNewFragment() {
        if (this.mWebTarget == null) {
            this.mWebTarget = new AccountWebTargetBuilder(this).build();
        }
        this.mWebViewFragment = AccountWebViewFragment.newInstance(this.mWebTarget);
        getSupportFragmentManager().beginTransaction().add(R.id.FragmentContainer, this.mWebViewFragment, "AccountWebViewActivity.TAG_FORM_FRAGMENT").commit();
    }

    private void setUpRefreshFragment() {
        if (this.mWebTarget == null) {
            this.mWebTarget = new AccountWebTargetBuilder(this).build();
        }
        if (getSupportFragmentManager().findFragmentById(R.id.container) != null) {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.FragmentContainer)).commit();
        }
        this.mWebViewFragment = AccountWebViewFragment.newInstance(this.mWebTarget);
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, this.mWebViewFragment, "AccountWebViewActivity.TAG_FORM_FRAGMENT").commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AccountWebViewFragment accountWebViewFragment = this.mWebViewFragment;
        if (accountWebViewFragment == null || !accountWebViewFragment.handleBackPress()) {
            onWebViewFlowFailure();
        }
    }

    @Override // com.amazon.mp3.account.activity.AccountWebViewListener
    public void onWebViewFlowFailure() {
        Log.warning(TAG, "Failed to complete Account Set Up Webview flow");
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            runOnUiThread(new Runnable() { // from class: com.amazon.mp3.account.activity.AccountWebViewActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    AccountWebViewActivity.this.handleNetworkError();
                }
            });
        } else {
            showAccountSetUpDialog();
        }
    }

    @Override // com.amazon.mp3.account.activity.AccountWebViewListener
    public void onWebViewFlowSuccess() {
        Log.info(TAG, "Successfully completed Account Set Up Webview flow");
        AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.account.activity.AccountWebViewActivity.2
            @Override // java.lang.Runnable
            public void run() {
                AccountRequestUtil.updateUser();
            }
        });
        if (AccountRequestUtil.isMigrationCandidate()) {
            AccountDetailStorage.get(this).setMigrationCandidateCompletedAccountFlow();
        }
        emitOnWebViewFlowSuccessFlexEvent();
        setResult(-1);
        finish();
    }

    @Override // com.amazon.mp3.account.activity.AccountSetUpDialogListener
    public void onAcknowledgeDialog() {
        Log.info(TAG, "User acknowledged Account Set Up dialog");
        UserInteractionAppEvent.builder("AccountInformationMissingOk").publish();
        showWebView(true);
    }

    @Override // com.amazon.mp3.account.activity.AccountSetUpDialogListener
    public void onSignOut() {
        Log.info(TAG, "User chooses to sign out from Account Set Up dialog");
        UserInteractionAppEvent.builder("AccountInformationMissingCancel").publish();
        AccountDeregistrationDialogActivity.start(this);
    }

    @Override // com.amazon.mp3.account.activity.AccountSetUpDialogListener
    public void onGoToStandAloneExperience() {
        Log.info(TAG, "User chooses to go to Offline Library from Account Set Up dialog");
        AccountCredentialStorage.get(this).setShowSsoWelcome(true);
        AccountCredentialStorage.get(this).setShouldPerformSignInTasks(false);
        startActivity(MusicHomeActivity.getStartIntent(this).putExtra("com.amazon.mp3.fragment.extra", MyLibraryHomeFragment.class.getSimpleName()).putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local"));
        setResult(RESULT_GO_TO_STAND_ALONE_EXPERIENCE);
        finish();
    }

    private void showAccountSetUpDialog() {
        NavigationAppEvent.builder("AccountInformationMissing").publish();
        AccountSetUpDialogFragment accountSetUpDialogFragment = new AccountSetUpDialogFragment();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(accountSetUpDialogFragment, (String) null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == AccountDeregistrationDialogActivity.REQUEST_CODE && resultCode == -1) {
            Log.info(TAG, "Successfully signed out. Relaunching app.");
            startActivity(new Intent(this, (Class<?>) MusicLauncherActivity.class));
            finish();
        }
    }

    private void emitOnWebViewFlowSuccessFlexEvent() {
        Log.debug(TAG, "Emitting onWebViewFlowSuccess flex event");
        MetricsLogger.sendEvent(FlexEvent.builder("WEB_VIEW_FLOW_SUCCESS").build());
    }
}
