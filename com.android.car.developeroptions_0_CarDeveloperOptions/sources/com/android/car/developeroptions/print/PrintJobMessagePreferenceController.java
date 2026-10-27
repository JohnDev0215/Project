package com.android.car.developeroptions.print;

import android.content.Context;
import android.content.IntentFilter;
import android.print.PrintJob;
import android.text.TextUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class PrintJobMessagePreferenceController extends PrintJobPreferenceControllerBase {
    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public PrintJobMessagePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.print.PrintJobPreferenceControllerBase
    protected void updateUi() {
        PrintJob printJob = getPrintJob();
        if (printJob == null) {
            this.mFragment.finish();
            return;
        }
        if (printJob.isCancelled() || printJob.isCompleted()) {
            this.mFragment.finish();
            return;
        }
        CharSequence status = printJob.getInfo().getStatus(this.mContext.getPackageManager());
        this.mPreference.setVisible(!TextUtils.isEmpty(status));
        this.mPreference.setSummary(status);
    }
}
