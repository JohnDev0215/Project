package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.inputmethod.InputMethodAndSubtypeEnablerManagerCompat;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodAndSubtypePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private PreferenceFragmentCompat mFragment;
    private InputMethodAndSubtypeEnablerManagerCompat mManager;
    private String mTargetImi;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public InputMethodAndSubtypePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void initialize(PreferenceFragmentCompat preferenceFragmentCompat, String str) {
        this.mFragment = preferenceFragmentCompat;
        this.mTargetImi = str;
        this.mManager = new InputMethodAndSubtypeEnablerManagerCompat(preferenceFragmentCompat);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mManager.init(this.mFragment, this.mTargetImi, preferenceScreen);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mManager.refresh(this.mContext, this.mFragment);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mManager.save(this.mContext, this.mFragment);
    }
}
