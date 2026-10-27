package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class SmsDefaultSubscriptionController extends DefaultSubscriptionController {
    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SmsDefaultSubscriptionController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController
    protected SubscriptionInfo getDefaultSubscriptionInfo() {
        return this.mManager.getDefaultSmsSubscriptionInfo();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController
    protected int getDefaultSubscriptionId() {
        return SubscriptionManager.getDefaultSmsSubscriptionId();
    }

    @Override // com.android.car.developeroptions.network.telephony.DefaultSubscriptionController
    protected void setDefaultSubscription(int i) {
        this.mManager.setDefaultSmsSubId(i);
    }
}
