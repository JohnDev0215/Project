package com.android.car.developeroptions.connecteddevice.usb;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UsbDetailsFunctionsController extends UsbDetailsController implements RadioButtonPreference.OnClickListener {
    static final Map<Long, Integer> FUNCTIONS_MAP;
    private ConnectivityManager mConnectivityManager;
    OnStartTetheringCallback mOnStartTetheringCallback;
    long mPreviousFunction;
    private PreferenceCategory mProfilesContainer;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "usb_details_functions";
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FUNCTIONS_MAP = linkedHashMap;
        linkedHashMap.put(4L, Integer.valueOf(R.string.usb_use_file_transfers));
        FUNCTIONS_MAP.put(32L, Integer.valueOf(R.string.usb_use_tethering));
        FUNCTIONS_MAP.put(8L, Integer.valueOf(R.string.usb_use_MIDI));
        FUNCTIONS_MAP.put(16L, Integer.valueOf(R.string.usb_use_photo_transfers));
        FUNCTIONS_MAP.put(0L, Integer.valueOf(R.string.usb_use_charging_only));
    }

    public UsbDetailsFunctionsController(Context context, UsbDetailsFragment usbDetailsFragment, UsbBackend usbBackend) {
        super(context, usbDetailsFragment, usbBackend);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mOnStartTetheringCallback = new OnStartTetheringCallback();
        this.mPreviousFunction = this.mUsbBackend.getCurrentFunctions();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mProfilesContainer = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
    }

    private RadioButtonPreference getProfilePreference(String str, int i) {
        RadioButtonPreference radioButtonPreference = (RadioButtonPreference) this.mProfilesContainer.findPreference(str);
        if (radioButtonPreference != null) {
            return radioButtonPreference;
        }
        RadioButtonPreference radioButtonPreference2 = new RadioButtonPreference(this.mProfilesContainer.getContext());
        radioButtonPreference2.setKey(str);
        radioButtonPreference2.setTitle(i);
        radioButtonPreference2.setOnClickListener(this);
        this.mProfilesContainer.addPreference(radioButtonPreference2);
        return radioButtonPreference2;
    }

    @Override // com.android.car.developeroptions.connecteddevice.usb.UsbDetailsController
    protected void refresh(boolean z, long j, int i, int i2) {
        if (!z || i2 != 2) {
            this.mProfilesContainer.setEnabled(false);
        } else {
            this.mProfilesContainer.setEnabled(true);
        }
        Iterator<Long> it = FUNCTIONS_MAP.keySet().iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            RadioButtonPreference profilePreference = getProfilePreference(UsbBackend.usbFunctionsToString(jLongValue), FUNCTIONS_MAP.get(Long.valueOf(jLongValue)).intValue());
            if (this.mUsbBackend.areFunctionsSupported(jLongValue)) {
                profilePreference.setChecked(j == jLongValue);
            } else {
                this.mProfilesContainer.removePreference(profilePreference);
            }
        }
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        long jUsbFunctionsFromString = UsbBackend.usbFunctionsFromString(radioButtonPreference.getKey());
        long currentFunctions = this.mUsbBackend.getCurrentFunctions();
        if (jUsbFunctionsFromString == currentFunctions || Utils.isMonkeyRunning()) {
            return;
        }
        this.mPreviousFunction = currentFunctions;
        if (jUsbFunctionsFromString == 32) {
            RadioButtonPreference radioButtonPreference2 = (RadioButtonPreference) this.mProfilesContainer.findPreference(UsbBackend.usbFunctionsToString(currentFunctions));
            if (radioButtonPreference2 != null) {
                radioButtonPreference2.setChecked(false);
                radioButtonPreference.setChecked(true);
            }
            this.mConnectivityManager.startTethering(1, true, this.mOnStartTetheringCallback);
            return;
        }
        this.mUsbBackend.setCurrentFunctions(jUsbFunctionsFromString);
    }

    @Override // com.android.car.developeroptions.connecteddevice.usb.UsbDetailsController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !Utils.isMonkeyRunning();
    }

    final class OnStartTetheringCallback extends ConnectivityManager.OnStartTetheringCallback {
        OnStartTetheringCallback() {
        }

        public void onTetheringFailed() {
            super.onTetheringFailed();
            UsbDetailsFunctionsController usbDetailsFunctionsController = UsbDetailsFunctionsController.this;
            usbDetailsFunctionsController.mUsbBackend.setCurrentFunctions(usbDetailsFunctionsController.mPreviousFunction);
        }
    }
}
