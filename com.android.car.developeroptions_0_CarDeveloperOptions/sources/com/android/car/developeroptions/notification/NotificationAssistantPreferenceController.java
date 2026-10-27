package com.android.car.developeroptions.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.notification.NotificationAssistantPicker;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.widget.CandidateInfo;

/* JADX INFO: loaded from: classes.dex */
public class NotificationAssistantPreferenceController extends BasePreferenceController {
    protected NotificationBackend mNotificationBackend;
    private PackageManager mPackageManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NotificationAssistantPreferenceController(Context context, String str) {
        super(context, str);
        this.mNotificationBackend = new NotificationBackend();
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        CandidateInfo candidateNone = new NotificationAssistantPicker.CandidateNone(this.mContext);
        ComponentName allowedNotificationAssistant = this.mNotificationBackend.getAllowedNotificationAssistant();
        if (allowedNotificationAssistant != null) {
            candidateNone = createCandidateInfo(allowedNotificationAssistant);
        }
        return candidateNone.loadLabel();
    }

    protected CandidateInfo createCandidateInfo(ComponentName componentName) {
        return new DefaultAppInfo(this.mContext, this.mPackageManager, UserHandle.myUserId(), componentName);
    }
}
