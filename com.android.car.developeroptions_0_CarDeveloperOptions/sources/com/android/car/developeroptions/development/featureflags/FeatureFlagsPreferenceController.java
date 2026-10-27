package com.android.car.developeroptions.development.featureflags;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.util.FeatureFlagUtils;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class FeatureFlagsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart {
    private PreferenceGroup mGroup;

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

    public FeatureFlagsPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return Build.IS_DEBUGGABLE ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        Map allFeatureFlags;
        if (this.mGroup == null || (allFeatureFlags = FeatureFlagUtils.getAllFeatureFlags()) == null) {
            return;
        }
        this.mGroup.removeAll();
        final Context context = this.mGroup.getContext();
        allFeatureFlags.keySet().stream().sorted().forEach(new Consumer() { // from class: com.android.car.developeroptions.development.featureflags.-$$Lambda$FeatureFlagsPreferenceController$LK4gj394n6JZfny1NiHb7tEMTow
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onStart$0$FeatureFlagsPreferenceController(context, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onStart$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onStart$0$FeatureFlagsPreferenceController(Context context, String str) {
        this.mGroup.addPreference(new FeatureFlagPreference(context, str));
    }
}
