package com.android.car.developeroptions.slices;

import android.content.Context;
import com.android.car.developeroptions.network.telephony.Enhanced4gLteSliceHelper;
import com.android.car.developeroptions.wifi.calling.WifiCallingSliceHelper;

/* JADX INFO: loaded from: classes.dex */
public interface SlicesFeatureProvider {
    Enhanced4gLteSliceHelper getNewEnhanced4gLteSliceHelper(Context context);

    WifiCallingSliceHelper getNewWifiCallingSliceHelper(Context context);

    SliceDataConverter getSliceDataConverter(Context context);

    void indexSliceData(Context context);

    void newUiSession();
}
