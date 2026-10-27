package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.os.PowerManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.fuelgauge.BatterySaverReceiver;
import com.android.car.developeroptions.fuelgauge.batterysaver.BatterySaverSettings;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverConditionController implements ConditionalCardController, BatterySaverReceiver.BatterySaverListener {
    static final int ID = Objects.hash("BatterySaverConditionController");
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final PowerManager mPowerManager;
    private final BatterySaverReceiver mReceiver;

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onBatteryChanged(boolean z) {
    }

    public BatterySaverConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        BatterySaverReceiver batterySaverReceiver = new BatterySaverReceiver(context);
        this.mReceiver = batterySaverReceiver;
        batterySaverReceiver.setBatterySaverListener(this);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mPowerManager.isPowerSaveMode();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(BatterySaverSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(35);
        subSettingLauncher.setTitleRes(R.string.battery_saver);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        BatterySaverUtils.setPowerSaveMode(this.mAppContext, false, false);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(379);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_battery_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_battery_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_battery_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_battery_saver_accent_24dp));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mReceiver.setListening(true);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mReceiver.setListening(false);
    }

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onPowerSaveModeChanged() {
        this.mConditionManager.onConditionChanged();
    }
}
