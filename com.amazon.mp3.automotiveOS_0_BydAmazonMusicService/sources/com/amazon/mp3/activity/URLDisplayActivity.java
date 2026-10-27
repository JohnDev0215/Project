package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.URLDisplayFragment;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.BasicWebTarget;
import com.amazon.mp3.web.webtarget.WebTarget;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLDisplayActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/activity/URLDisplayActivity;", "Lcom/amazon/mp3/activity/BaseWebActivity;", "()V", "TAG_WEB_FRAGMENT", "", "mFragment", "Lcom/amazon/mp3/fragment/URLDisplayFragment;", "mToolbar", "Landroidx/appcompat/widget/Toolbar;", "handleWebEvent", "", "webEvent", "Lcom/amazon/mp3/web/WebEvent;", "init", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onReceivedIntent", "setupActionBar", "setupNewFragment", "webTarget", "Lcom/amazon/mp3/web/webtarget/WebTarget;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class URLDisplayActivity extends BaseWebActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG_WEB_FRAGMENT = "URLDisplayActivity.TAG_WEB_FRAGMENT";
    private URLDisplayFragment mFragment;
    private Toolbar mToolbar;

    @JvmStatic
    public static final Intent getStartIntent(Context context, String str) {
        return INSTANCE.getStartIntent(context, str);
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(WebEvent webEvent) {
    }

    /* JADX INFO: compiled from: URLDisplayActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/activity/URLDisplayActivity$Companion;", "", "()V", "getStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "urlString", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Intent getStartIntent(Context context, String urlString) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(urlString, "urlString");
            Intent targetStartEvent = BaseWebActivity.getTargetStartEvent(context, URLDisplayActivity.class, new BasicWebTarget(WebViewUtil.addLocaleToUrlString(urlString)), "com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
            Intrinsics.checkNotNullExpressionValue(targetStartEvent, "getTargetStartEvent(\n   ….BUNDLE_KEY\n            )");
            return targetStartEvent;
        }
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_display);
        setupActionBar();
        init();
    }

    private final void setupActionBar() {
        View viewFindViewById = findViewById(R.id.toolbar);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.appcompat.widget.Toolbar");
        }
        Toolbar toolbar = (Toolbar) viewFindViewById;
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        Toolbar toolbar2 = this.mToolbar;
        Toolbar toolbar3 = null;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbar");
            toolbar2 = null;
        }
        toolbar2.setNavigationIcon(R.drawable.btn_close);
        Toolbar toolbar4 = this.mToolbar;
        if (toolbar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbar");
            toolbar4 = null;
        }
        toolbar4.setNavigationContentDescription(R.string.close_button_content_description);
        Toolbar toolbar5 = this.mToolbar;
        if (toolbar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbar");
        } else {
            toolbar3 = toolbar5;
        }
        toolbar3.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.URLDisplayActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                URLDisplayActivity.m154setupActionBar$lambda0(this.f$0, view);
            }
        });
        setTitle("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupActionBar$lambda-0, reason: not valid java name */
    public static final void m154setupActionBar$lambda0(URLDisplayActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
        }
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        onReceivedIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        }
        URLDisplayFragment uRLDisplayFragment = this.mFragment;
        URLDisplayFragment uRLDisplayFragment2 = null;
        if (uRLDisplayFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            uRLDisplayFragment = null;
        }
        if (uRLDisplayFragment.canGoBack()) {
            URLDisplayFragment uRLDisplayFragment3 = this.mFragment;
            if (uRLDisplayFragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            } else {
                uRLDisplayFragment2 = uRLDisplayFragment3;
            }
            uRLDisplayFragment2.onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        onReceivedIntent(intent);
    }

    private final void onReceivedIntent(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (serializableExtra == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.web.webtarget.WebTarget");
        }
        setupNewFragment((WebTarget) serializableExtra);
    }

    private final void setupNewFragment(WebTarget webTarget) {
        URLDisplayFragment uRLDisplayFragmentNewInstance = URLDisplayFragment.INSTANCE.newInstance(webTarget);
        this.mFragment = uRLDisplayFragmentNewInstance;
        URLDisplayFragment uRLDisplayFragment = null;
        if (uRLDisplayFragmentNewInstance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            uRLDisplayFragmentNewInstance = null;
        }
        uRLDisplayFragmentNewInstance.setPublisher(this.mWebEventPublisher);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        URLDisplayFragment uRLDisplayFragment2 = this.mFragment;
        if (uRLDisplayFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        } else {
            uRLDisplayFragment = uRLDisplayFragment2;
        }
        fragmentTransactionBeginTransaction.replace(R.id.FragmentContainer, uRLDisplayFragment, this.TAG_WEB_FRAGMENT).commit();
    }
}
