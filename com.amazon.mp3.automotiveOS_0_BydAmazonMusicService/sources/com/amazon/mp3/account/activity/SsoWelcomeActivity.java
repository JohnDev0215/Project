package com.amazon.mp3.account.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.credentials.MapAccount;
import com.amazon.mp3.account.details.TOUUtil;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.util.WelcomeScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.MapEmptyValueException;
import com.amazon.music.signin.RxSignInFlow;
import com.amazon.music.signin.SignInTaskId;
import rx.functions.Action0;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class SsoWelcomeActivity extends JobSessionActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private String TAG = SsoWelcomeActivity.class.getSimpleName();
    private Action1<SignInTaskId> onNext = new Action1<SignInTaskId>() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.5
        @Override // rx.functions.Action1
        public void call(final SignInTaskId taskId) {
        }
    };
    protected Action1<Throwable> onError = new Action1<Throwable>() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.6
        @Override // rx.functions.Action1
        public void call(final Throwable throwable) {
            if (throwable instanceof AuthenticationException) {
                Log.error(SsoWelcomeActivity.this.TAG, "Error is AuthenticationException");
                Throwable cause = throwable.getCause();
                String str = SsoWelcomeActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error cause is ");
                sb.append(cause == null ? "null " : cause.getClass().getSimpleName());
                Log.error(str, sb.toString());
                if (cause instanceof MapEmptyValueException) {
                    MapEmptyValueException mapEmptyValueException = (MapEmptyValueException) cause;
                    Bundle responseBundle = mapEmptyValueException.getResponseBundle();
                    String keyName = mapEmptyValueException.getKeyName();
                    if (responseBundle == null) {
                        Log.error(SsoWelcomeActivity.this.TAG, "Authentication Error: Received MapEmptyValueException for keyName: %s.MAP also returned a missing error bundle so we don't have an error code or message.", keyName);
                    } else {
                        Log.error(SsoWelcomeActivity.this.TAG, "Authentication Error: Received MapEmptyValueException for keyName: %s. MAP error code: %d. Error message: %s", keyName, Integer.valueOf(responseBundle.getInt("com.amazon.dcp.sso.ErrorCode")), responseBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                    }
                    SsoWelcomeActivity.this.setResult(2);
                }
            }
            SsoWelcomeActivity.this.finish();
        }
    };
    Action0 onCompleted = new Action0() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.7
        @Override // rx.functions.Action0
        public void call() {
        }
    };

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_sso_welcome_activity);
        if (SignInTaskStream.get().signInFlowIsInProgress()) {
            SignInTaskStream.get().getRxSubject().subscribe(this.onNext, this.onError, this.onCompleted);
        }
        initViews();
        initListeners();
        BootstrapSingletonTask.get(this).registerFeatureFlagObserver().subscribe(new Action1() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.1
            @Override // rx.functions.Action1
            public void call(final Object o) {
                SsoWelcomeActivity.this.updateUserAccountDescription();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(SsoWelcomeActivity.this.TAG, "BootstrapSingletonTask error: " + throwable.getMessage(), throwable);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        onContinueClicked();
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent();
        intent.setFlags(268451840);
        intent.setClass(context, SsoWelcomeActivity.class);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserAccountDescription() {
        TextView textView = (TextView) findViewById(R.id.WelcomeUserDescText);
        textView.setText(new WelcomeScreenUtil().getLoadingMessageIdForAccountStatus(this));
        textView.setVisibility(0);
    }

    private void initViews() {
        ((TextView) findViewById(R.id.WelcomeUserText)).setText(getString(R.string.dmusic_welcome, AccountCredentialUtil.get().getFirstName()));
        populateLegalText();
    }

    private void populateLegalText() {
        TextView textView = (TextView) findViewById(R.id.ssoLegalText);
        textView.setText(Html.fromHtml(getString(R.string.dmusic_sso_continue_legal_text, getString(R.string.dmusic_button_continue), TOUUtil.get(getApplicationContext()).getTOUUrl())));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initListeners() {
        final Context applicationContext = getApplicationContext();
        ((Button) findViewById(R.id.ContinueButton)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SsoWelcomeActivity.this.onContinueClicked();
            }
        });
        TextView textView = (TextView) findViewById(R.id.WrongUserText);
        String accountId = AccountCredentialStorage.get().getAccountId();
        String primaryAccount = MapAccount.getManager(this).getPrimaryAccount();
        if (!AmazonApplication.getCapabilities().supportsSecondaryLogin(this) || !accountId.equals(primaryAccount)) {
            textView.setVisibility(8);
        } else {
            textView.setClickable(true);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.account.activity.SsoWelcomeActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    AccountCredentialUtil.get(SsoWelcomeActivity.this).removeCredentialsAndSettings();
                    Context context = applicationContext;
                    SignInTaskStream.get().setRxSubject(new RxSignInFlow(context, AccountCredentialStorage.get(context).getCredentialPrefs(), new SignInFlowTasks(applicationContext).getSignInTasks(), Boolean.valueOf(PlatformUtil.isFireOS())).signInWithDifferentAccountThanSSOAccount(this));
                    SsoWelcomeActivity.this.setResult(0);
                    SsoWelcomeActivity.this.finish();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContinueClicked() {
        AccountCredentialStorage.get(getApplicationContext()).setShowSsoWelcome(true);
        setResult(-1);
        finish();
    }
}
