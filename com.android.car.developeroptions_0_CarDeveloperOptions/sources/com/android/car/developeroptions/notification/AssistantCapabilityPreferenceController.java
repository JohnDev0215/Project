package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AssistantCapabilityPreferenceController extends TogglePreferenceController {
    static final String PRIORITIZER_KEY = "asst_capability_prioritizer";
    static final String SMART_KEY = "asst_capabilities_actions_replies";
    private NotificationBackend mBackend;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AssistantCapabilityPreferenceController(Context context, String str) {
        super(context, str);
        this.mBackend = new NotificationBackend();
    }

    void setBackend(NotificationBackend notificationBackend) {
        this.mBackend = notificationBackend;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        List<String> assistantAdjustments = this.mBackend.getAssistantAdjustments(this.mContext.getPackageName());
        if (PRIORITIZER_KEY.equals(getPreferenceKey())) {
            return assistantAdjustments.contains("key_importance");
        }
        return SMART_KEY.equals(getPreferenceKey()) && assistantAdjustments.contains("key_contextual_actions") && assistantAdjustments.contains("key_text_replies");
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (PRIORITIZER_KEY.equals(getPreferenceKey())) {
            this.mBackend.allowAssistantAdjustment("key_importance", z);
            return true;
        }
        if (!SMART_KEY.equals(getPreferenceKey())) {
            return true;
        }
        this.mBackend.allowAssistantAdjustment("key_contextual_actions", z);
        this.mBackend.allowAssistantAdjustment("key_text_replies", z);
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mBackend.getAllowedNotificationAssistant() != null ? 0 : 5;
    }
}
