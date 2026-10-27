package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$AccountDashboardActivity;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalContextualCard;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WorkModeConditionController implements ConditionalCardController {
    private static final IntentFilter FILTER;
    static final int ID = Objects.hash("WorkModeConditionController");
    private final Context mAppContext;
    private final ConditionManager mConditionManager;
    private final Receiver mReceiver = new Receiver();
    private final UserManager mUm;
    private UserHandle mUserHandle;

    static {
        IntentFilter intentFilter = new IntentFilter();
        FILTER = intentFilter;
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        FILTER.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
    }

    public WorkModeConditionController(Context context, ConditionManager conditionManager) {
        this.mAppContext = context;
        this.mUm = (UserManager) context.getSystemService(UserManager.class);
        this.mConditionManager = conditionManager;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public long getId() {
        return ID;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public boolean isDisplayable() {
        updateUserHandle();
        UserHandle userHandle = this.mUserHandle;
        return userHandle != null && this.mUm.isQuietModeEnabled(userHandle);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onPrimaryClick(Context context) {
        context.startActivity(new Intent(context, (Class<?>) Settings$AccountDashboardActivity.class));
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void onActionClick() {
        UserHandle userHandle = this.mUserHandle;
        if (userHandle != null) {
            this.mUm.requestQuietModeEnabled(false, userHandle);
        }
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public ContextualCard buildContextualCard() {
        ConditionalContextualCard.Builder builder = new ConditionalContextualCard.Builder();
        builder.setConditionId(ID);
        builder.setMetricsConstant(383);
        builder.setActionText(this.mAppContext.getText(R.string.condition_turn_on));
        builder.setName(this.mAppContext.getPackageName() + "/" + ((Object) this.mAppContext.getText(R.string.condition_work_title)));
        builder.setTitleText(this.mAppContext.getText(R.string.condition_work_title).toString());
        builder.setSummaryText(this.mAppContext.getText(R.string.condition_work_summary).toString());
        builder.setIconDrawable(this.mAppContext.getDrawable(R.drawable.ic_signal_workmode_enable));
        builder.setViewType(R.layout.conditional_card_half_tile);
        return builder.build();
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void startMonitoringStateChange() {
        this.mAppContext.registerReceiver(this.mReceiver, FILTER);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionalCardController
    public void stopMonitoringStateChange() {
        this.mAppContext.unregisterReceiver(this.mReceiver);
    }

    private void updateUserHandle() {
        List profiles = this.mUm.getProfiles(UserHandle.myUserId());
        int size = profiles.size();
        this.mUserHandle = null;
        for (int i = 0; i < size; i++) {
            UserInfo userInfo = (UserInfo) profiles.get(i);
            if (userInfo.isManagedProfile()) {
                this.mUserHandle = userInfo.getUserHandle();
                return;
            }
        }
    }

    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, "android.intent.action.MANAGED_PROFILE_AVAILABLE") || TextUtils.equals(action, "android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                WorkModeConditionController.this.mConditionManager.onConditionChanged();
            }
        }
    }
}
