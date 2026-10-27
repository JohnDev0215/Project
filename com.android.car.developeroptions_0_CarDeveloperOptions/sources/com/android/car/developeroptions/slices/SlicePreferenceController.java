package com.android.car.developeroptions.slices;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceScreen;
import androidx.slice.Slice;
import androidx.slice.widget.SliceLiveData;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class SlicePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, Observer<Slice> {
    LiveData<Slice> mLiveData;
    private SlicePreference mSlicePreference;
    private Uri mUri;

    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SlicePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSlicePreference = (SlicePreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mUri != null ? 0 : 3;
    }

    public void setSliceUri(Uri uri) {
        this.mUri = uri;
        LiveData<Slice> liveDataFromUri = SliceLiveData.fromUri(this.mContext, uri);
        this.mLiveData = liveDataFromUri;
        liveDataFromUri.removeObserver(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        LiveData<Slice> liveData = this.mLiveData;
        if (liveData != null) {
            liveData.observeForever(this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        LiveData<Slice> liveData = this.mLiveData;
        if (liveData != null) {
            liveData.removeObserver(this);
        }
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(Slice slice) {
        if (slice != null) {
            this.mSlicePreference.onSliceUpdated(slice);
        }
    }
}
