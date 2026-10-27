package com.android.car.developeroptions.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InternetConnectivityPanel implements PanelContent {
    private final Context mContext;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1654;
    }

    public static InternetConnectivityPanel create(Context context) {
        return new InternetConnectivityPanel(context);
    }

    private InternetConnectivityPanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public CharSequence getTitle() {
        return this.mContext.getText(R.string.internet_connectivity_panel_title);
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.WIFI_SLICE_URI);
        arrayList.add(CustomSliceRegistry.MOBILE_DATA_SLICE_URI);
        arrayList.add(CustomSliceRegistry.AIRPLANE_URI);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public Intent getSeeMoreIntent() {
        return new Intent("android.settings.WIRELESS_SETTINGS");
    }
}
