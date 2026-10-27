package com.android.car.settings.wifi;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.android.car.settings.common.PasswordEditTextPreference;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkNameRestrictedPasswordEditTextPreference extends PasswordEditTextPreference {
    private String mNetworkName;

    public NetworkNameRestrictedPasswordEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NetworkNameRestrictedPasswordEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NetworkNameRestrictedPasswordEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkNameRestrictedPasswordEditTextPreference(Context context) {
        super(context);
    }

    public void setNetworkName(String name) {
        this.mNetworkName = name;
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void onClick() {
        if (TextUtils.isEmpty(this.mNetworkName)) {
            return;
        }
        super.onClick();
    }
}
