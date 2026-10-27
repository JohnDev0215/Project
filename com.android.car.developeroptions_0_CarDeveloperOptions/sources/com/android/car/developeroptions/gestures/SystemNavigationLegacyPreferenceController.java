package com.android.car.developeroptions.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.os.ServiceManager;
import android.text.TextUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.RadioButtonPreference;

/* JADX INFO: loaded from: classes.dex */
public class SystemNavigationLegacyPreferenceController extends SystemNavigationPreferenceController {
    static final String PREF_KEY_LEGACY = "gesture_legacy";

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SystemNavigationLegacyPreferenceController(Context context, String str) {
        this(context, IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")), str);
    }

    public SystemNavigationLegacyPreferenceController(Context context, IOverlayManager iOverlayManager, String str) {
        super(context, iOverlayManager, str);
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(PREF_KEY_LEGACY, getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        SystemNavigationPreferenceController.setNavBarInteractionMode(this.mOverlayManager, "com.android.internal.systemui.navbar.threebutton");
        SystemNavigationPreferenceController.selectRadioButtonInGroup(PREF_KEY_LEGACY, this.mPreferenceScreen);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return (SystemNavigationPreferenceController.isEdgeToEdgeEnabled(this.mContext) || SystemNavigationPreferenceController.isSwipeUpEnabled(this.mContext)) ? false : true;
    }
}
