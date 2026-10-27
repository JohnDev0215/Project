package com.android.car.developeroptions.gestures;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.AmbientDisplayConfiguration;
import android.provider.Settings;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GesturesSettingPreferenceController extends BasePreferenceController {
    private static final String FAKE_PREF_KEY = "fake_key_only_for_get_available";
    private static final String KEY_GESTURES_SETTINGS = "gesture_settings";
    private final AssistGestureFeatureProvider mFeatureProvider;
    private List<AbstractPreferenceController> mGestureControllers;

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

    public GesturesSettingPreferenceController(Context context) {
        super(context, KEY_GESTURES_SETTINGS);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getAssistGestureFeatureProvider();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        boolean z;
        if (this.mGestureControllers == null) {
            this.mGestureControllers = buildAllPreferenceControllers(this.mContext);
        }
        loop0: while (true) {
            for (AbstractPreferenceController abstractPreferenceController : this.mGestureControllers) {
                z = z || abstractPreferenceController.isAvailable();
            }
        }
        return z ? 0 : 3;
    }

    private static List<AbstractPreferenceController> buildAllPreferenceControllers(Context context) {
        AmbientDisplayConfiguration ambientDisplayConfiguration = new AmbientDisplayConfiguration(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AssistGestureSettingsPreferenceController(context, FAKE_PREF_KEY).setAssistOnly(false));
        arrayList.add(new SwipeToNotificationPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new DoubleTwistPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new DoubleTapPowerPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new PickupGesturePreferenceController(context, FAKE_PREF_KEY).setConfig(ambientDisplayConfiguration));
        arrayList.add(new DoubleTapScreenPreferenceController(context, FAKE_PREF_KEY).setConfig(ambientDisplayConfiguration));
        arrayList.add(new PreventRingingParentPreferenceController(context, FAKE_PREF_KEY));
        return arrayList;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (!this.mFeatureProvider.isSensorAvailable(this.mContext)) {
            return "";
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = Settings.Secure.getInt(contentResolver, "assist_gesture_enabled", 1) != 0;
        boolean z2 = Settings.Secure.getInt(contentResolver, "assist_gesture_silence_alerts_enabled", 1) != 0;
        if (this.mFeatureProvider.isSupported(this.mContext) && z) {
            return this.mContext.getText(R.string.language_input_gesture_summary_on_with_assist);
        }
        if (z2) {
            return this.mContext.getText(R.string.language_input_gesture_summary_on_non_assist);
        }
        return this.mContext.getText(R.string.language_input_gesture_summary_off);
    }
}
