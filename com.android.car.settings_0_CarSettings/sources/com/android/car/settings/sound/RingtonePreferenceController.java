package com.android.car.settings.sound;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class RingtonePreferenceController extends PreferenceController<RingtonePreference> {
    private RingtoneManager mRingtoneManager;

    public RingtonePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mRingtoneManager = new RingtoneManager(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mRingtoneManager.setType(getPreference().getRingtoneType());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<RingtonePreference> getPreferenceType() {
        return RingtonePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(RingtonePreference preference) {
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getContext(), getPreference().getRingtoneType());
        if (this.mRingtoneManager.getRingtonePosition(ringtoneUri) < 0) {
            ringtoneUri = null;
        }
        preference.setSummary(Ringtone.getTitle(getContext(), ringtoneUri, false, true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(RingtonePreference preference) {
        onPrepareRingtonePickerIntent(preference, preference.getIntent());
        getContext().startActivity(preference.getIntent());
        return true;
    }

    private void onPrepareRingtonePickerIntent(RingtonePreference ringtonePreference, Intent ringtonePickerIntent) {
        ringtonePickerIntent.setClass(getContext(), CarSettingActivities.RingtonePickerActivity.class);
        ringtonePickerIntent.putExtra("android.intent.extra.ringtone.TITLE", ringtonePreference.getTitle());
        ringtonePickerIntent.putExtra("android.intent.extra.ringtone.TYPE", ringtonePreference.getRingtoneType());
        ringtonePickerIntent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", ringtonePreference.getShowSilent());
        ringtonePickerIntent.putExtra("android.intent.extra.ringtone.AUDIO_ATTRIBUTES_FLAGS", 64);
    }
}
