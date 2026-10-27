package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.NightDisplayListener;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.display.NightDisplaySettings;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplayConditionController implements ConditionalCardController, NightDisplayListener.Callback {
    static final int ID = Objects.hash("NightDisplayConditionController");
    private final Context mAppContext;
    private final ColorDisplayManager mColorDisplayManager;
    private final ConditionManager mConditionManager;
    private final NightDisplayListener mNightDisplayListener;

    public NightDisplayConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mNightDisplayListener = new NightDisplayListener(context);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mColorDisplayManager.isNightDisplayActivated();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(NightDisplaySettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(1502);
        subSettingLauncher.setTitleRes(R.string.night_display_title);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        this.mColorDisplayManager.setNightDisplayActivated(false);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(492);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_night_display_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_night_display_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_night_display_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_settings_night_display));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mNightDisplayListener.setCallback(this);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mNightDisplayListener.setCallback((NightDisplayListener.Callback) null);
    }

    public void onActivated(boolean z) {
        this.mConditionManager.onConditionChanged();
    }
}
