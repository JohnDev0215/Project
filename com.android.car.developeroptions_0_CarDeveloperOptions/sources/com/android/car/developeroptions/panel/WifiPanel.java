package com.android.car.developeroptions.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SubSettings;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.car.developeroptions.slices.SliceBuilderUtils;
import com.android.car.developeroptions.wifi.WifiSettings;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiPanel implements PanelContent {
    private final Context mContext;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1687;
    }

    public static WifiPanel create(Context context) {
        return new WifiPanel(context);
    }

    private WifiPanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public CharSequence getTitle() {
        return this.mContext.getText(R.string.wifi_settings);
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.WIFI_SLICE_URI);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public Intent getSeeMoreIntent() {
        Intent intentBuildSearchResultPageIntent = SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, WifiSettings.class.getName(), null, this.mContext.getText(R.string.wifi_settings).toString(), 103);
        intentBuildSearchResultPageIntent.setClassName(this.mContext.getPackageName(), SubSettings.class.getName());
        return intentBuildSearchResultPageIntent;
    }
}
