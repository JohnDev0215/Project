package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.TetherSettings;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class HotspotConditionController implements ConditionalCardController {
    static final int ID = Objects.hash("HotspotConditionController");
    private static final IntentFilter WIFI_AP_STATE_FILTER = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final Receiver mReceiver = new Receiver();
    private final WifiManager mWifiManager;

    public HotspotConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mWifiManager.isWifiApEnabled();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(TetherSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(35);
        subSettingLauncher.setTitleRes(R.string.tether_settings_title_all);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mAppContext, "no_config_tethering", UserHandle.myUserId());
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mAppContext, enforcedAdminCheckIfRestrictionEnforced);
        } else {
            ((ConnectivityManager) this.mAppContext.getSystemService("connectivity")).stopTethering(0);
        }
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(382);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_hotspot_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_hotspot_title).toString());
        builder.setSummaryText(getSsid().toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_hotspot));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, WIFI_AP_STATE_FILTER);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    private CharSequence getSsid() {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        if (wifiApConfiguration == null) {
            Log.e("HotspotConditionController", "Ap config null unexpectedly");
            return "";
        }
        return wifiApConfiguration.SSID;
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(intent.getAction())) {
                HotspotConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
