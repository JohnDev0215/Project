package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkPolicyManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$DataUsageSummaryActivity;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundDataConditionController implements ConditionalCardController {
    static final int ID = Objects.hash("BackgroundDataConditionController");
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final NetworkPolicyManager mNetworkPolicyManager;

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
    }

    public BackgroundDataConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mNetworkPolicyManager = (NetworkPolicyManager) context.getSystemService("netpolicy");
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mNetworkPolicyManager.getRestrictBackground();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent(context, (Class<?>) Settings$DataUsageSummaryActivity.class));
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        this.mNetworkPolicyManager.setRestrictBackground(false);
        this.mConditionManager.onConditionChanged();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(378);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_bg_data_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_bg_data_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_bg_data_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_data_saver));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }
}
