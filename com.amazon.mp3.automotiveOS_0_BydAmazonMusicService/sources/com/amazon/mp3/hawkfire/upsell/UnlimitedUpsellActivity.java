package com.amazon.mp3.hawkfire.upsell;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseWebActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.dialogtarget.DialogTarget;
import com.amazon.mp3.dialog.fragment.DialogFragmentListener;
import com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragment;
import com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragmentV2;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Optional;
import java.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public class UnlimitedUpsellActivity extends BaseWebActivity implements DialogFragmentListener, DynamicMessageDialogFragmentListener {
    public static final String REF_MARKER_KEY = UnlimitedUpsellActivity.class.getSimpleName() + ".ref_";
    private static final String TAG = UnlimitedUpsellActivity.class.getSimpleName();
    private HawkfireUpsellFormFragment mFormFragment;
    private PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);

    public enum UpsellWebEventEnum {
        CUSTOMER_UPGRADED,
        CUSTOMER_DEFERRED,
        LISTEN_NOW
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onShow(final DialogInterface dialog) {
    }

    public static void start(Activity activity, final WebTarget webTarget) {
        activity.startActivity(getStartIntent(activity, webTarget));
    }

    public static Intent getStartIntent(final Context context, final DialogTarget dialogTarget) {
        return BaseWebActivity.getTargetStartEvent(context, UnlimitedUpsellActivity.class, dialogTarget, DialogTarget.BUNDLE_KEY);
    }

    public static Intent getStartIntent(final Context context, final WebTarget webTarget) {
        return BaseWebActivity.getTargetStartEvent(context, UnlimitedUpsellActivity.class, webTarget, "com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View viewOnCreateView = super.onCreateView(name, context, attrs);
        setupPublisherForJSBridge();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
        }
        onReceivedIntent(getIntent(), false);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        onReceivedIntent(getIntent(), true);
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(final WebEvent webEvent) {
        UpsellWebEventEnum upsellWebEventEnumValueOf = UpsellWebEventEnum.valueOf(webEvent.id());
        boolean andClearHawkfireUpsellAutoplayFlag = getAndClearHawkfireUpsellAutoplayFlag(getApplicationContext());
        int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$hawkfire$upsell$UnlimitedUpsellActivity$UpsellWebEventEnum[upsellWebEventEnumValueOf.ordinal()];
        if (i == 1 || i == 2) {
            onCustomerUpgradeSuccess(andClearHawkfireUpsellAutoplayFlag);
        } else {
            if (i != 3) {
                return;
            }
            onCustomerDeferred();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$hawkfire$upsell$UnlimitedUpsellActivity$UpsellWebEventEnum;

        static {
            int[] iArr = new int[UpsellWebEventEnum.values().length];
            $SwitchMap$com$amazon$mp3$hawkfire$upsell$UnlimitedUpsellActivity$UpsellWebEventEnum = iArr;
            try {
                iArr[UpsellWebEventEnum.CUSTOMER_UPGRADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$hawkfire$upsell$UnlimitedUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.LISTEN_NOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$hawkfire$upsell$UnlimitedUpsellActivity$UpsellWebEventEnum[UpsellWebEventEnum.CUSTOMER_DEFERRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void onReceivedIntent(final Intent intent, boolean shouldForceFragmentRefresh) {
        WebTarget webTarget = (WebTarget) intent.getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        String str = DialogTarget.BUNDLE_KEY;
        DialogTarget dialogTarget = (DialogTarget) intent.getSerializableExtra(str);
        String str2 = (String) intent.getSerializableExtra(REF_MARKER_KEY);
        validate(webTarget, dialogTarget);
        intent.removeExtra(str);
        if (dialogTarget != null) {
            showDialog(dialogTarget);
            return;
        }
        showUpsellWebview(webTarget, shouldForceFragmentRefresh);
        if ("sdp_to_individual_upgrade".equals(str2)) {
            UpsellUtil.emitPersistentUpgradePageViewedMetric(false, false);
        }
    }

    private void validate(final WebTarget webTarget, final DialogTarget dialogTarget) {
        if (webTarget == null && dialogTarget == null) {
            Log.error(TAG, "UnlimitedUpsellActivity needs a web target or dialog target to render. Finishing.");
            finish();
        } else {
            if (webTarget == null || dialogTarget == null) {
                return;
            }
            Log.error(TAG, "UnlimitedUpsellActivity needs either one web target to render or one dialog target to render, but you provided both. Finishing.");
            finish();
        }
    }

    private void showDialog(final DialogTarget dialogTarget) {
        DialogFragment dialogFragmentFromDialogTarget = getDialogFragmentFromDialogTarget(dialogTarget);
        if (dialogFragmentFromDialogTarget != null) {
            dialogFragmentFromDialogTarget.show(getSupportFragmentManager(), (String) null);
        } else {
            Log.info(TAG, "The dialogFragment returns null when trying to showDialog. It's expected if dialog is supposed to show by dynamic message.");
        }
    }

    private DialogFragment getDialogFragmentFromDialogTarget(final DialogTarget dialogTarget) {
        try {
            UpsellInformation upsellInformation = UpsellUtil.getUpsellInformation("DEFAULT_ASIN", UpsellReason.DEEPLINK_SIGNUP, UpsellSourceEntity.UNKNOWN_ENTITY);
            DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes = new DynamicMessageMetricsAttributes(PageType.NOW_PLAYING.getMetricsValue(), upsellInformation.getUpsellReason().getValue());
            if (dialogTarget.getDialogFragment() instanceof HawkfireUpsellDialogFragmentV2) {
                return UpsellManager.getInstance().getBlockingUpsellFragment(this, upsellInformation, dynamicMessageMetricsAttributes);
            }
            return dialogTarget.getDialogFragment();
        } catch (DialogTarget.GetDialogFragmentException e) {
            Log.error(TAG, "The dialog target provided was unrecognized. Showing default dialog.Exception message: %s. Cause: %s", e.getMessage(), e.getCause());
            return HawkfireUpsellDialogFragment.newInstance(new Bundle());
        }
    }

    private void showUpsellWebview(final WebTarget webTarget, final boolean shouldForceRefresh) {
        HawkfireUpsellFormFragment hawkfireUpsellFormFragment = (HawkfireUpsellFormFragment) getSupportFragmentManager().findFragmentByTag("UnlimitedUpsellActivity.TAG_FORM_FRAGMENT");
        this.mFormFragment = hawkfireUpsellFormFragment;
        if (hawkfireUpsellFormFragment == null) {
            setUpNewFragment(webTarget);
        } else if (shouldForceRefresh) {
            setUpRefreshFragment(webTarget);
        }
        this.mFormFragment.setPublisher(this.mWebEventPublisher);
    }

    private void setUpNewFragment(final WebTarget webTarget) {
        this.mFormFragment = HawkfireUpsellFormFragment.newInstance(webTarget);
        getSupportFragmentManager().beginTransaction().add(R.id.FragmentContainer, this.mFormFragment, "UnlimitedUpsellActivity.TAG_FORM_FRAGMENT").commit();
    }

    private void setUpRefreshFragment(final WebTarget webTarget) {
        if (getSupportFragmentManager().findFragmentById(R.id.container) != null) {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.FragmentContainer)).commit();
        }
        this.mFormFragment = HawkfireUpsellFormFragment.newInstance(webTarget);
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, this.mFormFragment, "UnlimitedUpsellActivity.TAG_FORM_FRAGMENT").commit();
    }

    @Override // com.amazon.mp3.dialog.fragment.DialogFragmentListener
    public void onNegativeButtonClicked(final DialogInterface dialog) {
        if (isTaskRoot()) {
            BrushFragmentNavigation.navigateToHome(getApplicationContext());
        }
        finish();
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener
    public void onDismissClickEvent(final DialogInterface dialog) {
        if (isTaskRoot()) {
            BrushFragmentNavigation.navigateToHome(getApplicationContext());
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        HawkfireUpsellFormFragment hawkfireUpsellFormFragment = this.mFormFragment;
        if (hawkfireUpsellFormFragment == null || !hawkfireUpsellFormFragment.handleBackPress()) {
            super.onBackPressed();
        }
    }

    private void shouldLaunchBrowseHome() {
        if (isTaskRoot()) {
            BrushFragmentNavigation.navigateToHome(this);
        }
    }

    private String getRefMarker() {
        return (String) Optional.ofNullable(getIntent()).map(new Function() { // from class: com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UnlimitedUpsellActivity.lambda$getRefMarker$0((Intent) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getRefMarker$0(Intent intent) {
        return (String) intent.getSerializableExtra(REF_MARKER_KEY);
    }

    private void onCustomerUpgradeSuccess(final boolean hawkfireUpsellAutoplayFlag) {
        if ("sdp_to_individual_upgrade".equals(getRefMarker())) {
            UpsellUtil.emitPersistentUpgradeSuccessPageViewMetric();
        }
        AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity.1
            @Override // java.lang.Runnable
            public void run() {
                AccountManagerSingleton.get().updateUser();
                if (AmazonApplication.getCapabilities().shouldForceRefreshButNotOverATC(UnlimitedUpsellActivity.this.getApplicationContext())) {
                    MusicHomeActivity.startPrimeSectionWithRefresh(UnlimitedUpsellActivity.this);
                    UnlimitedUpsellActivity.this.finish();
                }
            }
        });
        if (AmazonApplication.getCapabilities().shouldForceRefreshButNotOverATC(getApplicationContext())) {
            return;
        }
        shouldLaunchBrowseHome();
        if (hawkfireUpsellAutoplayFlag) {
            this.mPlaybackController.play();
        }
        finish();
    }

    private void onCustomerDeferred() {
        Log.debug(TAG, "Customer deferred");
        AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity.2
            @Override // java.lang.Runnable
            public void run() {
                Log.debug(UnlimitedUpsellActivity.TAG, "Initiate account refresh");
                AccountManagerSingleton.get().updateUser();
                if (SubscriptionManagerSingleton.isInitialized()) {
                    SubscriptionManagerSingleton.getInstance().update();
                }
            }
        });
        shouldLaunchBrowseHome();
        finish();
    }

    public static boolean getAndClearHawkfireUpsellAutoplayFlag(final Context context) {
        boolean z = context.getSharedPreferences("concurrentStreamingSharedPrefs", 0).getBoolean("hawkfireUpsellSuccessAutoplayFlag", false);
        setHawkfireUpsellAutoplayFlag(context, false);
        return z;
    }

    public static void setHawkfireUpsellAutoplayFlag(final Context context, final boolean hawkfireUpsellAutoplayFlag) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("concurrentStreamingSharedPrefs", 0).edit();
        editorEdit.putBoolean("hawkfireUpsellSuccessAutoplayFlag", hawkfireUpsellAutoplayFlag);
        editorEdit.apply();
    }
}
