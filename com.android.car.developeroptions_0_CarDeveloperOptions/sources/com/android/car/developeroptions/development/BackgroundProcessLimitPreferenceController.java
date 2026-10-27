package com.android.car.developeroptions.development;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.os.RemoteException;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundProcessLimitPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private final String[] mListSummaries;
    private final String[] mListValues;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "app_process_limit";
    }

    public BackgroundProcessLimitPreferenceController(Context context) {
        super(context);
        this.mListValues = context.getResources().getStringArray(R.array.app_process_limit_values);
        this.mListSummaries = context.getResources().getStringArray(R.array.app_process_limit_entries);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        writeAppProcessLimitOptions(obj);
        updateAppProcessLimitOptions();
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateAppProcessLimitOptions();
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeAppProcessLimitOptions(null);
    }

    private void updateAppProcessLimitOptions() {
        try {
            int processLimit = getActivityManagerService().getProcessLimit();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.mListValues.length) {
                    break;
                }
                if (Integer.parseInt(this.mListValues[i2]) >= processLimit) {
                    i = i2;
                    break;
                }
                i2++;
            }
            ListPreference listPreference = (ListPreference) this.mPreference;
            listPreference.setValue(this.mListValues[i]);
            listPreference.setSummary(this.mListSummaries[i]);
        } catch (RemoteException unused) {
        }
    }

    private void writeAppProcessLimitOptions(Object obj) {
        int i;
        if (obj != null) {
            try {
                i = Integer.parseInt(obj.toString());
            } catch (RemoteException unused) {
                return;
            }
        } else {
            i = -1;
        }
        getActivityManagerService().setProcessLimit(i);
        updateAppProcessLimitOptions();
    }

    IActivityManager getActivityManagerService() {
        return ActivityManager.getService();
    }
}
