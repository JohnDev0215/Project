package com.android.car.developeroptions.nfc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.provider.Settings;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class NfcPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause {
    public static final String KEY_TOGGLE_NFC = "toggle_nfc";
    NfcAirplaneModeObserver mAirplaneModeObserver;
    private final NfcAdapter mNfcAdapter;
    private NfcEnabler mNfcEnabler;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean hasAsyncUpdate() {
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NfcPreferenceController(Context context, String str) {
        super(context, str);
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (!isAvailable()) {
            this.mNfcEnabler = null;
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mNfcEnabler = new NfcEnabler(this.mContext, switchPreference);
        if (shouldTurnOffNFCInAirplaneMode(this.mContext) || isToggleableInAirplaneMode(this.mContext)) {
            this.mAirplaneModeObserver = new NfcAirplaneModeObserver(this.mContext, this.mNfcAdapter, switchPreference);
        }
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mNfcAdapter.isEnabled();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (z) {
            this.mNfcAdapter.enable();
            return true;
        }
        this.mNfcAdapter.disable();
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mNfcAdapter != null ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return NfcSliceWorker.class;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        NfcAirplaneModeObserver nfcAirplaneModeObserver = this.mAirplaneModeObserver;
        if (nfcAirplaneModeObserver != null) {
            nfcAirplaneModeObserver.register();
        }
        NfcEnabler nfcEnabler = this.mNfcEnabler;
        if (nfcEnabler != null) {
            nfcEnabler.resume();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        NfcAirplaneModeObserver nfcAirplaneModeObserver = this.mAirplaneModeObserver;
        if (nfcAirplaneModeObserver != null) {
            nfcAirplaneModeObserver.unregister();
        }
        NfcEnabler nfcEnabler = this.mNfcEnabler;
        if (nfcEnabler != null) {
            nfcEnabler.pause();
        }
    }

    public static boolean shouldTurnOffNFCInAirplaneMode(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "airplane_mode_radios");
        return string != null && string.contains("nfc");
    }

    public static boolean isToggleableInAirplaneMode(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "airplane_mode_toggleable_radios");
        return string != null && string.contains("nfc");
    }

    public static class NfcSliceWorker extends SliceBackgroundWorker<Void> {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        static {
            new IntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED");
        }

        public NfcSliceWorker(Context context, Uri uri) {
            super(context, uri);
            new NfcUpdateReceiver(this, this);
        }

        public void updateSlice() {
            notifySliceChange();
        }

        public class NfcUpdateReceiver extends BroadcastReceiver {
            private final NfcSliceWorker mSliceBackgroundWorker;

            public NfcUpdateReceiver(NfcSliceWorker nfcSliceWorker, NfcSliceWorker nfcSliceWorker2) {
                this.mSliceBackgroundWorker = nfcSliceWorker2;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.nfc.extra.ADAPTER_STATE", -1);
                if (intExtra == -1 || intExtra == 2 || intExtra == 4) {
                    Log.d("NfcSliceWorker", "Transitional update, dropping broadcast");
                } else {
                    Log.d("NfcSliceWorker", "Nfc broadcast received, updating Slice.");
                    this.mSliceBackgroundWorker.updateSlice();
                }
            }
        }
    }
}
