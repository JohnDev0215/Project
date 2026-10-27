package com.android.car.developeroptions.slices;

import android.content.Context;
import android.os.SystemClock;
import com.android.car.developeroptions.network.telephony.Enhanced4gLteSliceHelper;
import com.android.car.developeroptions.wifi.calling.WifiCallingSliceHelper;

/* JADX INFO: loaded from: classes.dex */
public class SlicesFeatureProviderImpl implements SlicesFeatureProvider {
    private SliceDataConverter mSliceDataConverter;
    private SlicesIndexer mSlicesIndexer;

    @Override // com.android.car.developeroptions.slices.SlicesFeatureProvider
    public SliceDataConverter getSliceDataConverter(Context context) {
        if (this.mSliceDataConverter == null) {
            this.mSliceDataConverter = new SliceDataConverter(context.getApplicationContext());
        }
        return this.mSliceDataConverter;
    }

    @Override // com.android.car.developeroptions.slices.SlicesFeatureProvider
    public void newUiSession() {
        SystemClock.elapsedRealtime();
    }

    @Override // com.android.car.developeroptions.slices.SlicesFeatureProvider
    public void indexSliceData(Context context) {
        getSliceIndexer(context).indexSliceData();
    }

    @Override // com.android.car.developeroptions.slices.SlicesFeatureProvider
    public WifiCallingSliceHelper getNewWifiCallingSliceHelper(Context context) {
        return new WifiCallingSliceHelper(context);
    }

    @Override // com.android.car.developeroptions.slices.SlicesFeatureProvider
    public Enhanced4gLteSliceHelper getNewEnhanced4gLteSliceHelper(Context context) {
        return new Enhanced4gLteSliceHelper(context);
    }

    private SlicesIndexer getSliceIndexer(Context context) {
        if (this.mSlicesIndexer == null) {
            this.mSlicesIndexer = new SlicesIndexer(context.getApplicationContext());
        }
        return this.mSlicesIndexer;
    }
}
