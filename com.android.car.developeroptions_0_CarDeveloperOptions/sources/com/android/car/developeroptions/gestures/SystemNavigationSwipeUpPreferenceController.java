package com.android.car.developeroptions.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.os.ServiceManager;
import android.text.TextUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.RadioButtonPreference;

/* JADX INFO: loaded from: classes.dex */
public class SystemNavigationSwipeUpPreferenceController extends SystemNavigationPreferenceController {
    static final String PREF_KEY_SWIPE_UP = "gesture_swipe_up";

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

    public SystemNavigationSwipeUpPreferenceController(Context context, String str) {
        this(context, IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")), str);
    }

    public SystemNavigationSwipeUpPreferenceController(Context context, IOverlayManager iOverlayManager, String str) {
        super(context, iOverlayManager, str);
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(PREF_KEY_SWIPE_UP, getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.gestures.SystemNavigationPreferenceController, com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        SystemNavigationPreferenceController.setNavBarInteractionMode(this.mOverlayManager, "com.android.internal.systemui.navbar.twobutton");
        SystemNavigationPreferenceController.selectRadioButtonInGroup(PREF_KEY_SWIPE_UP, this.mPreferenceScreen);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return SystemNavigationPreferenceController.isSwipeUpEnabled(this.mContext);
    }
}
