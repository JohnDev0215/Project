package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.IntentFilter;
import android.icu.text.ListFormatter;
import android.text.BidiFormatter;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.wifi.WifiMasterSwitchPreferenceController;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelNetworkEntryPreferenceController extends BasePreferenceController {
    private final MobileNetworkPreferenceController mMobileNetworkPreferenceController;
    private final TetherPreferenceController mTetherPreferenceController;
    private final WifiMasterSwitchPreferenceController mWifiPreferenceController;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
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

    public TopLevelNetworkEntryPreferenceController(Context context, String str) {
        super(context, str);
        this.mMobileNetworkPreferenceController = new MobileNetworkPreferenceController(this.mContext);
        this.mTetherPreferenceController = new TetherPreferenceController(this.mContext, null);
        this.mWifiPreferenceController = new WifiMasterSwitchPreferenceController(this.mContext, null);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Utils.isDemoUser(this.mContext) ? 3 : 1;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        String strUnicodeWrap = BidiFormatter.getInstance().unicodeWrap(this.mContext.getString(R.string.wifi_settings_title));
        String string = this.mContext.getString(R.string.network_dashboard_summary_mobile);
        String string2 = this.mContext.getString(R.string.network_dashboard_summary_data_usage);
        String string3 = this.mContext.getString(R.string.network_dashboard_summary_hotspot);
        ArrayList arrayList = new ArrayList();
        if (this.mWifiPreferenceController.isAvailable() && !TextUtils.isEmpty(strUnicodeWrap)) {
            arrayList.add(strUnicodeWrap);
        }
        if (this.mMobileNetworkPreferenceController.isAvailable() && !TextUtils.isEmpty(string)) {
            arrayList.add(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            arrayList.add(string2);
        }
        if (this.mTetherPreferenceController.isAvailable() && !TextUtils.isEmpty(string3)) {
            arrayList.add(string3);
        }
        return ListFormatter.getInstance().format(arrayList);
    }
}
