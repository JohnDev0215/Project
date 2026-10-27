package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class DefaultBrowserShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_browser";

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.applications.appinfo.DefaultAppShortcutPreferenceControllerBase, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public DefaultBrowserShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.BROWSER", str);
    }
}
