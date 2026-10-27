package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$DataUsageSummaryActivity;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CellularDataConditionController implements ConditionalCardController {
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final ConnectivityManager mConnectivityManager;
    private final Receiver mReceiver = new Receiver();
    private final TelephonyManager mTelephonyManager;
    static final int ID = Objects.hash("CellularDataConditionController");
    private static final IntentFilter DATA_CONNECTION_FILTER = new IntentFilter("android.intent.action.ANY_DATA_STATE");

    public CellularDataConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        if (this.mConnectivityManager.isNetworkSupported(0) && this.mTelephonyManager.getSimState() == 5) {
            return !this.mTelephonyManager.isDataEnabled();
        }
        return false;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent(context, (Class<?>) Settings$DataUsageSummaryActivity.class));
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        this.mTelephonyManager.setDataEnabled(true);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(380);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_on));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_cellular_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_cellular_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_cellular_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_cellular_off));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, DATA_CONNECTION_FILTER);
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
            if ("android.intent.action.ANY_DATA_STATE".equals(intent.getAction())) {
                CellularDataConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
