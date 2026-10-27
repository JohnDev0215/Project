package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.settingslib.WirelessUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AirplaneModeConditionController implements ConditionalCardController {
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final Receiver mReceiver = new Receiver();
    static final int ID = Objects.hash("AirplaneModeConditionController");
    private static final IntentFilter AIRPLANE_MODE_FILTER = new IntentFilter("android.intent.action.AIRPLANE_MODE");

    public AirplaneModeConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return WirelessUtils.isAirplaneModeOn(this.mAppContext);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        ConnectivityManager.from(this.mAppContext).setAirplaneMode(false);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(377);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_airplane_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_airplane_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_airplane_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_airplanemode_active));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, AIRPLANE_MODE_FILTER);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                AirplaneModeConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
