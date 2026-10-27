package com.android.car.developeroptions.print;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.print.PrintJob;
import android.print.PrintJobInfo;
import android.text.format.DateUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class PrintJobPreferenceController extends PrintJobPreferenceControllerBase {
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

    public PrintJobPreferenceController(Context context, String str) {
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
        PrintJobInfo info = printJob.getInfo();
        int state = info.getState();
        if (state == 1) {
            this.mPreference.setTitle(this.mContext.getString(R.string.print_configuring_state_title_template, info.getLabel()));
        } else if (state == 2 || state == 3) {
            if (!printJob.getInfo().isCancelling()) {
                this.mPreference.setTitle(this.mContext.getString(R.string.print_printing_state_title_template, info.getLabel()));
            } else {
                this.mPreference.setTitle(this.mContext.getString(R.string.print_cancelling_state_title_template, info.getLabel()));
            }
        } else if (state != 4) {
            if (state == 6) {
                this.mPreference.setTitle(this.mContext.getString(R.string.print_failed_state_title_template, info.getLabel()));
            }
        } else if (!printJob.getInfo().isCancelling()) {
            this.mPreference.setTitle(this.mContext.getString(R.string.print_blocked_state_title_template, info.getLabel()));
        } else {
            this.mPreference.setTitle(this.mContext.getString(R.string.print_cancelling_state_title_template, info.getLabel()));
        }
        this.mPreference.setSummary(this.mContext.getString(R.string.print_job_summary, info.getPrinterName(), DateUtils.formatSameDayTime(info.getCreationTime(), info.getCreationTime(), 3, 3)));
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{android.R.attr.colorControlNormal});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        int state2 = info.getState();
        if (state2 == 2 || state2 == 3) {
            Drawable drawable = this.mContext.getDrawable(android.R.drawable.ic_media_route_connecting_light_14_mtrl);
            drawable.setTint(color);
            this.mPreference.setIcon(drawable);
        } else if (state2 == 4 || state2 == 6) {
            Drawable drawable2 = this.mContext.getDrawable(android.R.drawable.ic_media_route_connecting_light_15_mtrl);
            drawable2.setTint(color);
            this.mPreference.setIcon(drawable2);
        }
    }
}
