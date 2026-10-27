package com.android.car.developeroptions.gestures;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.RadioButtonPreference;

/* JADX INFO: loaded from: classes.dex */
public abstract class SystemNavigationPreferenceController extends GesturePreferenceController implements RadioButtonPreference.OnClickListener {
    private static final String ACTION_QUICKSTEP = "android.intent.action.QUICKSTEP_SERVICE";
    private static final String PREF_KEY_VIDEO = "gesture_swipe_up_video";
    private static final String[] RADIO_BUTTONS_IN_GROUP = {"gesture_legacy", "gesture_swipe_up", "gesture_edge_to_edge"};
    protected final IOverlayManager mOverlayManager;
    protected PreferenceScreen mPreferenceScreen;

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController
    protected String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public abstract /* synthetic */ void onRadioButtonClicked(RadioButtonPreference radioButtonPreference);

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SystemNavigationPreferenceController(Context context, IOverlayManager iOverlayManager, String str) {
        super(context, str);
        this.mOverlayManager = iOverlayManager;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return isGestureAvailable(this.mContext) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof RadioButtonPreference)) {
            return;
        }
        RadioButtonPreference radioButtonPreference = (RadioButtonPreference) preferenceFindPreference;
        radioButtonPreference.setOnClickListener(this);
        radioButtonPreference.setAppendixVisibility(8);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        PreferenceScreen preferenceScreen;
        if (!z || (preferenceScreen = this.mPreferenceScreen) == null) {
            return false;
        }
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference == null || !(preferenceFindPreference instanceof RadioButtonPreference)) {
            return true;
        }
        onRadioButtonClicked((RadioButtonPreference) preferenceFindPreference);
        return true;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (isEdgeToEdgeEnabled(this.mContext)) {
            return this.mContext.getText(R.string.edge_to_edge_navigation_title);
        }
        if (isSwipeUpEnabled(this.mContext)) {
            return this.mContext.getText(R.string.swipe_up_to_switch_apps_title);
        }
        return this.mContext.getText(R.string.legacy_navigation_title);
    }

    static boolean isGestureAvailable(Context context) {
        ComponentName componentNameUnflattenFromString;
        if (context.getResources().getBoolean(android.R.bool.config_cecRcProfileSourceSetupMenuNotHandled_default) && (componentNameUnflattenFromString = ComponentName.unflattenFromString(context.getString(android.R.string.config_defaultNearbySharingComponent))) != null) {
            return context.getPackageManager().resolveService(new Intent(ACTION_QUICKSTEP).setPackage(componentNameUnflattenFromString.getPackageName()), 1048576) != null;
        }
        return false;
    }

    static void selectRadioButtonInGroup(String str, PreferenceScreen preferenceScreen) {
        if (preferenceScreen == null) {
            return;
        }
        for (String str2 : RADIO_BUTTONS_IN_GROUP) {
            ((RadioButtonPreference) preferenceScreen.findPreference(str2)).setChecked(TextUtils.equals(str2, str));
        }
    }

    static void setNavBarInteractionMode(IOverlayManager iOverlayManager, String str) {
        setOverlayEnabled(iOverlayManager, "com.android.internal.systemui.navbar.threebutton", str == "com.android.internal.systemui.navbar.threebutton");
        setOverlayEnabled(iOverlayManager, "com.android.internal.systemui.navbar.twobutton", str == "com.android.internal.systemui.navbar.twobutton");
        setOverlayEnabled(iOverlayManager, "com.android.internal.systemui.navbar.gestural", str == "com.android.internal.systemui.navbar.gestural");
    }

    static boolean isSwipeUpEnabled(Context context) {
        return !isEdgeToEdgeEnabled(context) && 1 == context.getResources().getInteger(android.R.integer.config_dockedStackDividerSnapMode);
    }

    static boolean isEdgeToEdgeEnabled(Context context) {
        return 2 == context.getResources().getInteger(android.R.integer.config_dockedStackDividerSnapMode);
    }

    static void setOverlayEnabled(IOverlayManager iOverlayManager, String str, boolean z) {
        try {
            iOverlayManager.setEnabled(str, z, -2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
