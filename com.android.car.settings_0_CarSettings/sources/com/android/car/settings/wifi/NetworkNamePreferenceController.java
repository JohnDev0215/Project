package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.EditTextPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkNamePreferenceController extends PreferenceController<EditTextPreference> {
    public static final String ACTION_NAME_CHANGE = "com.android.car.settings.wifi.NameChangeAction";
    public static final String KEY_NETWORK_NAME = "network_name";

    public NetworkNamePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<EditTextPreference> getPreferenceType() {
        return EditTextPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(EditTextPreference preference) {
        preference.setSummary(TextUtils.isEmpty(preference.getText()) ? getContext().getString(R.string.default_network_name_summary) : preference.getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(EditTextPreference preference, Object newValue) {
        String name = newValue.toString();
        preference.setText(name);
        notifyNameChange(name);
        refreshUi();
        return true;
    }

    private void notifyNameChange(String newName) {
        Intent intent = new Intent(ACTION_NAME_CHANGE);
        intent.putExtra(KEY_NETWORK_NAME, newName);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcastSync(intent);
    }
}
