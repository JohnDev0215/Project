package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.fragment.ATCWebFragment;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.web.ATCUrls;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.BasicWebTarget;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.StationItem;

/* JADX INFO: loaded from: classes3.dex */
public class ATCWebActivity extends BaseWebActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "ATCWebActivity";
    private boolean customerCompletedATC = false;
    private CustomerFlow customerFlow = CustomerFlow.firstTimeCustomer;
    private ATCWebFragment mFragment;
    private String refMarker;

    public enum CustomerFlow {
        firstTimeCustomer,
        tastesDeeplink,
        ingressFromLibrary
    }

    public static Intent start(final Context context) {
        return BaseWebActivity.getTargetStartEvent(context, ATCWebActivity.class, new BasicWebTarget(ATCUrls.getProdUrl()), "com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPublisherForJSBridge();
        init();
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
        }
        onReceivedIntent(getIntent());
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        onReceivedIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ATCWebFragment aTCWebFragment = this.mFragment;
        if (aTCWebFragment != null) {
            aTCWebFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(final WebEvent webEvent) {
        if ("CUSTOMER_COMPLETED_ATC".equals(webEvent.id())) {
            onATCCompletion();
        } else if ("CUSTOMER_COMPLETED_ATC_START_PROACTIVE_PLAYBACK".equals(webEvent.id())) {
            onATCCompletionShouldStartProactivePlayback();
        } else if ("ATC_LOAD_TIMED_OUT".equals(webEvent.id())) {
            onATCCompletion();
        }
    }

    private void onReceivedIntent(final Intent intent) {
        WebTarget webTarget = (WebTarget) intent.getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (intent.getSerializableExtra("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW") != null) {
            this.customerFlow = (CustomerFlow) intent.getSerializableExtra("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW");
        }
        if (intent.getStringExtra("com.amazon.mp3.activity.EXTRA_REF_MARKER") != null) {
            this.refMarker = intent.getStringExtra("com.amazon.mp3.activity.EXTRA_REF_MARKER");
        }
        setupNewFragment(webTarget);
    }

    private void setupNewFragment(final WebTarget webTarget) {
        ATCWebFragment aTCWebFragmentNewInstance = ATCWebFragment.newInstance(webTarget, this.customerFlow, this.refMarker);
        this.mFragment = aTCWebFragmentNewInstance;
        aTCWebFragmentNewInstance.setPublisher(this.mWebEventPublisher);
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, this.mFragment, "ATCWebActivity.TAG_WEB_FRAGMENT").commit();
    }

    private void onATCCompletion() {
        this.customerCompletedATC = true;
        FTUSettingsUtil.setCustomerCompletedATC(this, true);
        shouldLaunchBrowseHome();
        setResult(-1);
        finish();
    }

    private void shouldLaunchBrowseHome() {
        if (isTaskRoot()) {
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        startActivity(PrimeActivityFactory.getHomeIntent(this));
    }

    private void onATCCompletionShouldStartProactivePlayback() {
        startProactivePlayback();
        onATCCompletion();
    }

    public void startProactivePlayback() {
        StationItem stationItem = new StationItem(new CatalogStatusTiers(true, true, true, false, true).toSet(), StationItem.buildExplicitMap(false), "", "", "", "", ContextMappingConstants.MY_SOUNDTRACK_STATION_KEY, "STATION_KEY");
        Boolean bool = Boolean.TRUE;
        PlaybackPageType playbackPageType = PlaybackPageType.MY_SOUNDTRACK;
        String str = this.refMarker;
        String str2 = TAG;
        StationUtils.playStationForATC(this, stationItem, bool, bool, playbackPageType, str, str2);
        Log.info(str2, "Starting playback with My Soundtrack");
    }

    public CustomerFlow getCustomerFlow() {
        return this.customerFlow;
    }

    public String getRefMarker() {
        return this.refMarker;
    }

    public boolean getCustomerCompletedATC() {
        return this.customerCompletedATC;
    }
}
