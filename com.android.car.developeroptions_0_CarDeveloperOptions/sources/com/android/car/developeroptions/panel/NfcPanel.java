package com.android.car.developeroptions.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SubSettings;
import com.android.car.developeroptions.connecteddevice.AdvancedConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.car.developeroptions.slices.SliceBuilderUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NfcPanel implements PanelContent {
    private final Context mContext;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1656;
    }

    public static NfcPanel create(Context context) {
        return new NfcPanel(context);
    }

    private NfcPanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public CharSequence getTitle() {
        return this.mContext.getText(R.string.nfc_quick_toggle_title);
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.NFC_SLICE_URI);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public Intent getSeeMoreIntent() {
        Intent intentBuildSearchResultPageIntent = SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, AdvancedConnectedDeviceDashboardFragment.class.getName(), null, this.mContext.getText(R.string.connected_device_connections_title).toString(), 747);
        intentBuildSearchResultPageIntent.setClassName(this.mContext.getPackageName(), SubSettings.class.getName());
        return intentBuildSearchResultPageIntent;
    }
}
