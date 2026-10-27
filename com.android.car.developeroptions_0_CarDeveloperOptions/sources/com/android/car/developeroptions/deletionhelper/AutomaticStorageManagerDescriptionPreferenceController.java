package com.android.car.developeroptions.deletionhelper;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class AutomaticStorageManagerDescriptionPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "freed_bytes";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public AutomaticStorageManagerDescriptionPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        Context context = preferenceFindPreference.getContext();
        ContentResolver contentResolver = context.getContentResolver();
        long j = Settings.Secure.getLong(contentResolver, "automatic_storage_manager_bytes_cleared", 0L);
        long j2 = Settings.Secure.getLong(contentResolver, "automatic_storage_manager_last_run", 0L);
        if (j == 0 || j2 == 0 || !Utils.isStorageManagerEnabled(context)) {
            preferenceFindPreference.setSummary(R.string.automatic_storage_manager_text);
        } else {
            preferenceFindPreference.setSummary(context.getString(R.string.automatic_storage_manager_freed_bytes, Formatter.formatFileSize(context, j), DateUtils.formatDateTime(context, j2, 16)));
        }
    }
}
