package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RingerVibrateConditionController extends AbnormalRingerConditionController {
    static final int ID = Objects.hash("RingerVibrateConditionController");
    private final Context mAppContext;

    public RingerVibrateConditionController(Context context, ConditionManager conditionManager) {
        super(context, conditionManager);
        this.mAppContext = context;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mAudioManager.getRingerModeInternal() == 1;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(1369);
        builder.setActionText(this.mAppContext.getText(R.string.condition_device_muted_action_turn_on_sound));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_device_vibrate_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_device_vibrate_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_device_vibrate_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_volume_ringer_vibrate));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }
}
