package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.service.notification.ZenModeConfig;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import com.android.car.developeroptions.notification.ZenModeSettings;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class DndConditionCardController implements ConditionalCardController {
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final NotificationManager mNotificationManager;
    private final Receiver mReceiver = new Receiver();
    static final int ID = Objects.hash("DndConditionCardController");
    static final IntentFilter DND_FILTER = new IntentFilter("android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL");

    public DndConditionCardController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mConditionManager = conditionManager;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        return this.mNotificationManager.getZenMode() != 0;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, DND_FILTER);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(ZenModeSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(1502);
        subSettingLauncher.setTitleRes(R.string.zen_mode_settings_title);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        this.mNotificationManager.setZenMode(0, null, "DndCondition");
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(381);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_off));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_zen_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_zen_title).toString());
        builder.setSummaryText(getSummary());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_do_not_disturb_on_24dp));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL".equals(intent.getAction())) {
                DndConditionCardController.this.mConditionManager.onConditionChanged();
            }
        }
    }

    private String getSummary() {
        if (ZenModeConfig.areAllZenBehaviorSoundsMuted(this.mNotificationManager.getZenModeConfig())) {
            return this.mAppContext.getText(R.string.condition_zen_summary_phone_muted).toString();
        }
        return this.mAppContext.getText(R.string.condition_zen_summary_with_exceptions).toString();
    }
}
