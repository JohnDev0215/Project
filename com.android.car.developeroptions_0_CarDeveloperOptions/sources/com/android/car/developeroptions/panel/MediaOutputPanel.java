package com.android.car.developeroptions.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaOutputPanel implements PanelContent {
    private final Context mContext;
    private final String mPackageName;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1657;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public Intent getSeeMoreIntent() {
        return null;
    }

    public static MediaOutputPanel create(Context context, String str) {
        return new MediaOutputPanel(context, str);
    }

    private MediaOutputPanel(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mPackageName = str;
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public CharSequence getTitle() {
        return this.mContext.getText(R.string.media_output_panel_title);
    }

    @Override // com.android.car.developeroptions.panel.PanelContent
    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI.buildUpon().clearQuery().appendQueryParameter("media_package_name", this.mPackageName).build();
        CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI = uriBuild;
        arrayList.add(uriBuild);
        return arrayList;
    }
}
