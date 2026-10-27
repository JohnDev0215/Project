package com.android.car.developeroptions.notification;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModePriorityMessagesPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceChangeListener {
    private final ZenModeBackend mBackend;
    private final String[] mListValues;
    private ListPreference mPreference;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_messages";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModePriorityMessagesPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_messages", lifecycle);
        this.mBackend = ZenModeBackend.getInstance(context);
        this.mListValues = context.getResources().getStringArray(R.array.zen_mode_contacts_values);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference("zen_mode_messages");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        updateFromContactsValue(preference);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mBackend.saveSenders(4, ZenModeBackend.getSettingFromPrefKey(obj.toString()));
        updateFromContactsValue(preference);
        return true;
    }

    private void updateFromContactsValue(Preference preference) {
        this.mPreference = (ListPreference) preference;
        int zenMode = getZenMode();
        if (zenMode == 2 || zenMode == 3) {
            this.mPreference.setEnabled(false);
            this.mPreference.setValue("zen_mode_from_none");
            this.mPreference.setSummary(this.mBackend.getAlarmsTotalSilenceCallsMessagesSummary(4));
        } else {
            preference.setEnabled(true);
            preference.setSummary(this.mBackend.getContactsSummary(4));
            this.mPreference.setValue(this.mListValues[getIndexOfSendersValue(ZenModeBackend.getKeyFromSetting(this.mBackend.getPriorityMessageSenders()))]);
        }
    }

    protected int getIndexOfSendersValue(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.mListValues;
            if (i >= strArr.length) {
                return 3;
            }
            if (TextUtils.equals(str, strArr[i])) {
                return i;
            }
            i++;
        }
    }
}
