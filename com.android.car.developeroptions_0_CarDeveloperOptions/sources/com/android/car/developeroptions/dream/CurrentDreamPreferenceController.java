package com.android.car.developeroptions.dream;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.settingslib.dream.DreamBackend;
import java.util.Optional;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class CurrentDreamPreferenceController extends BasePreferenceController {
    private final DreamBackend mBackend;

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

    public CurrentDreamPreferenceController(Context context, String str) {
        super(context, str);
        this.mBackend = DreamBackend.getInstance(context);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mBackend.getDreamInfos().size() > 0 ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        setGearClickListenerForPreference(preference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mBackend.getActiveDreamName();
    }

    private void setGearClickListenerForPreference(Preference preference) {
        if (preference instanceof GearPreference) {
            GearPreference gearPreference = (GearPreference) preference;
            Optional<DreamBackend.DreamInfo> activeDreamInfo = getActiveDreamInfo();
            if (!activeDreamInfo.isPresent() || activeDreamInfo.get().settingsComponentName == null) {
                gearPreference.setOnGearClickListener(null);
            } else {
                gearPreference.setOnGearClickListener(new GearPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.dream.-$$Lambda$CurrentDreamPreferenceController$l5pjH-Re_1zs14jTbemqKqDR1eA
                    @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
                    public final void onGearClick(GearPreference gearPreference2) {
                        this.f$0.lambda$setGearClickListenerForPreference$0$CurrentDreamPreferenceController(gearPreference2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setGearClickListenerForPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setGearClickListenerForPreference$0$CurrentDreamPreferenceController(GearPreference gearPreference) {
        launchScreenSaverSettings();
    }

    private void launchScreenSaverSettings() {
        Optional<DreamBackend.DreamInfo> activeDreamInfo = getActiveDreamInfo();
        if (activeDreamInfo.isPresent()) {
            this.mBackend.launchSettings(this.mContext, activeDreamInfo.get());
        }
    }

    private Optional<DreamBackend.DreamInfo> getActiveDreamInfo() {
        return this.mBackend.getDreamInfos().stream().filter(new Predicate() { // from class: com.android.car.developeroptions.dream.-$$Lambda$CurrentDreamPreferenceController$28InJII0QcPn_hUa_jFoIdlQVds
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DreamBackend.DreamInfo) obj).isActive;
            }
        }).findFirst();
    }
}
