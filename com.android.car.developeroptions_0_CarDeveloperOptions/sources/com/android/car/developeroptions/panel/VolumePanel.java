package com.android.car.developeroptions.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VolumePanel implements PanelContent {
    private final Context mContext;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1655;
    }

    public static VolumePanel create(Context context) {
        return new VolumePanel(context);
    }

    private VolumePanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public CharSequence getTitle() {
        return this.mContext.getText(R.string.volume_connectivity_panel_title);
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.VOLUME_REMOTE_MEDIA_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_MEDIA_URI);
        arrayList.add(CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_CALL_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_RINGER_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_ALARM_URI);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public Intent getSeeMoreIntent() {
        return new Intent("android.settings.SOUND_SETTINGS");
    }
}
