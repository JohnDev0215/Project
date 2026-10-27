package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.TetheringManager;
import android.os.HandlerExecutor;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.internal.os.BackgroundThread;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherPreferenceController extends PreferenceController<Preference> {
    private volatile boolean mIsTetheringSupported;
    private volatile boolean mReceivedTetheringEventCallback;
    private TetheringManager.TetheringEventCallback mTetheringCallback;
    private final TetheringManager mTetheringManager;

    public WifiTetherPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mTetheringManager = (TetheringManager) getContext().getSystemService(TetheringManager.class);
        this.mReceivedTetheringEventCallback = false;
        this.mTetheringCallback = new TetheringManager.TetheringEventCallback() { // from class: com.android.car.settings.wifi.WifiTetherPreferenceController.1
            public void onTetheringSupported(boolean supported) {
                WifiTetherPreferenceController.this.mReceivedTetheringEventCallback = true;
                if (WifiTetherPreferenceController.this.mIsTetheringSupported != supported) {
                    WifiTetherPreferenceController.this.mIsTetheringSupported = supported;
                }
                WifiTetherPreferenceController.this.refreshUi();
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mTetheringManager.registerTetheringEventCallback(new HandlerExecutor(BackgroundThread.getHandler()), this.mTetheringCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mTetheringManager.unregisterTetheringEventCallback(this.mTetheringCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (this.mReceivedTetheringEventCallback) {
            return this.mIsTetheringSupported ? 0 : 2;
        }
        return 4;
    }
}
