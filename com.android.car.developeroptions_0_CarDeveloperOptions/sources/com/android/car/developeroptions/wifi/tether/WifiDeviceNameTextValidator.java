package com.android.car.developeroptions.wifi.tether;

import com.android.car.developeroptions.widget.ValidatedEditTextPreference;
import com.android.car.developeroptions.wifi.WifiUtils;

/* JADX INFO: loaded from: classes.dex */
public class WifiDeviceNameTextValidator implements ValidatedEditTextPreference.Validator {
    @Override // com.android.car.developeroptions.widget.ValidatedEditTextPreference.Validator
    public boolean isTextValid(String str) {
        return (WifiUtils.isSSIDTooLong(str) || WifiUtils.isSSIDTooShort(str)) ? false : true;
    }
}
