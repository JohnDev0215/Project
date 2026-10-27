package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.NetworkTemplate;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.NetworkPolicyEditor;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DataWarningAndLimitBasePreferenceController<V extends Preference> extends PreferenceController<V> {
    private NetworkPolicyEditor mNetworkPolicyEditor;
    private NetworkTemplate mNetworkTemplate;

    public DataWarningAndLimitBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public NetworkPolicyEditor getNetworkPolicyEditor() {
        return this.mNetworkPolicyEditor;
    }

    public void setNetworkPolicyEditor(NetworkPolicyEditor networkPolicyEditor) {
        this.mNetworkPolicyEditor = networkPolicyEditor;
    }

    public NetworkTemplate getNetworkTemplate() {
        return this.mNetworkTemplate;
    }

    public void setNetworkTemplate(NetworkTemplate networkTemplate) {
        this.mNetworkTemplate = networkTemplate;
    }
}
