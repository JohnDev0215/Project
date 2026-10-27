package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RingerMutedConditionController extends AbnormalRingerConditionController {
    static final int ID = Objects.hash("RingerMutedConditionController");
    private final Context mAppContext;

    public RingerMutedConditionController(Context context, ConditionManager conditionManager) {
        super(context, conditionManager);
        this.mAppContext = context;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mAudioManager.getRingerModeInternal() == 0;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(1368);
        builder.setActionText(this.mAppContext.getText(R.string.condition_device_muted_action_turn_on_sound));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_device_muted_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_device_muted_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_device_muted_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_notifications_off_24dp));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }
}
