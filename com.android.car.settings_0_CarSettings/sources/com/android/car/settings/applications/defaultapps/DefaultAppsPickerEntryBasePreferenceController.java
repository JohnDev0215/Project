package com.android.car.settings.applications.defaultapps;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import com.android.car.settings.common.FragmentController;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;
import com.android.settingslib.applications.DefaultAppInfo;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DefaultAppsPickerEntryBasePreferenceController extends DefaultAppEntryBasePreferenceController<CarUiTwoActionIconPreference> {
    public DefaultAppsPickerEntryBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<CarUiTwoActionIconPreference> getPreferenceType() {
        return CarUiTwoActionIconPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(CarUiTwoActionIconPreference preference) {
        super.updateState(preference);
        final Intent intent = getSettingIntent(getCurrentDefaultAppInfo());
        if (intent == null || intent.resolveActivityInfo(getContext().getPackageManager(), intent.getFlags()) == null) {
            preference.setSecondaryActionVisible(false);
        } else {
            preference.setOnSecondaryActionClickListener(new Runnable() { // from class: com.android.car.settings.applications.defaultapps.-$$Lambda$DefaultAppsPickerEntryBasePreferenceController$3LbM7zY25KYa9FAyHi8zJJJtOMg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateState$0$DefaultAppsPickerEntryBasePreferenceController(intent);
                }
            });
            preference.setSecondaryActionVisible(true);
        }
    }

    public /* synthetic */ void lambda$updateState$0$DefaultAppsPickerEntryBasePreferenceController(Intent intent) {
        getContext().startActivityForResult(getContext().getBasePackageName(), intent, 0, null);
    }

    protected Intent getSettingIntent(DefaultAppInfo info) {
        return null;
    }
}
