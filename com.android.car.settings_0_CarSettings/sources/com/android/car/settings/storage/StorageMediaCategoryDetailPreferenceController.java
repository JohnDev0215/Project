package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class StorageMediaCategoryDetailPreferenceController extends StorageApplicationListPreferenceController {
    private long mExternalAudioBytes;

    public StorageMediaCategoryDetailPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageApplicationListPreferenceController, com.android.car.settings.applications.ApplicationListItemManager.AppListItemListener
    public void onDataLoaded(ArrayList<ApplicationsState.AppEntry> apps) {
        super.onDataLoaded(apps);
        Preference preference = createPreference(getContext().getString(R.string.storage_audio_files_title), Long.toString(this.mExternalAudioBytes), getContext().getDrawable(R.drawable.ic_headset), getContext().getString(R.string.pk_storage_music_audio_files));
        preference.setOnPreferenceClickListener(null);
        getPreference().addPreference(preference);
    }

    public void setExternalAudioBytes(long externalAudioBytes) {
        this.mExternalAudioBytes = externalAudioBytes;
    }
}
