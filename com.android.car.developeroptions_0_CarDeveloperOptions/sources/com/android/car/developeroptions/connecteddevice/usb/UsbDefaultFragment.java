package com.android.car.developeroptions.connecteddevice.usb;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.widget.CandidateInfo;
import com.android.settingslib.widget.FooterPreference;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UsbDefaultFragment extends RadioButtonPickerFragment {
    ConnectivityManager mConnectivityManager;
    OnStartTetheringCallback mOnStartTetheringCallback = new OnStartTetheringCallback();
    long mPreviousFunctions;
    UsbBackend mUsbBackend;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1312;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.usb_default_fragment;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mUsbBackend = new UsbBackend(context);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        FooterPreference.Builder builder = new FooterPreference.Builder(getActivity());
        builder.setTitle(R.string.usb_default_info);
        preferenceScreen.addPreference(builder.build());
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        Iterator<Long> it = UsbDetailsFunctionsController.FUNCTIONS_MAP.keySet().iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            final String string = getContext().getString(UsbDetailsFunctionsController.FUNCTIONS_MAP.get(Long.valueOf(jLongValue)).intValue());
            final String strUsbFunctionsToString = UsbBackend.usbFunctionsToString(jLongValue);
            if (this.mUsbBackend.areFunctionsSupported(jLongValue)) {
                arrayListNewArrayList.add(new CandidateInfo(this, true) { // from class: com.android.car.developeroptions.connecteddevice.usb.UsbDefaultFragment.1
                    @Override // com.android.settingslib.widget.CandidateInfo
                    public Drawable loadIcon() {
                        return null;
                    }

                    @Override // com.android.settingslib.widget.CandidateInfo
                    public CharSequence loadLabel() {
                        return string;
                    }

                    @Override // com.android.settingslib.widget.CandidateInfo
                    public String getKey() {
                        return strUsbFunctionsToString;
                    }
                });
            }
        }
        return arrayListNewArrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return UsbBackend.usbFunctionsToString(this.mUsbBackend.getDefaultUsbFunctions());
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        long jUsbFunctionsFromString = UsbBackend.usbFunctionsFromString(str);
        this.mPreviousFunctions = this.mUsbBackend.getCurrentFunctions();
        if (!Utils.isMonkeyRunning()) {
            if (jUsbFunctionsFromString == 32) {
                this.mConnectivityManager.startTethering(1, true, this.mOnStartTetheringCallback);
            } else {
                this.mUsbBackend.setDefaultUsbFunctions(jUsbFunctionsFromString);
            }
        }
        return true;
    }

    final class OnStartTetheringCallback extends ConnectivityManager.OnStartTetheringCallback {
        OnStartTetheringCallback() {
        }

        public void onTetheringStarted() {
            super.onTetheringStarted();
            UsbDefaultFragment.this.mUsbBackend.setDefaultUsbFunctions(32L);
        }

        public void onTetheringFailed() {
            super.onTetheringFailed();
            UsbDefaultFragment usbDefaultFragment = UsbDefaultFragment.this;
            usbDefaultFragment.mUsbBackend.setDefaultUsbFunctions(usbDefaultFragment.mPreviousFunctions);
            UsbDefaultFragment.this.updateCandidates();
        }
    }
}
