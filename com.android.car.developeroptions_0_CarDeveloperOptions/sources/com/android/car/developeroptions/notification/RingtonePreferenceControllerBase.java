package com.android.car.developeroptions.notification;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class RingtonePreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    public abstract int getRingtoneType();

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        return false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public RingtonePreferenceControllerBase(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(final Preference preference) {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.notification.-$$Lambda$RingtonePreferenceControllerBase$ijoRnCAg3hdJrGJZBiUW1GJHYvY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateState$0$RingtonePreferenceControllerBase(preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSummary, reason: merged with bridge method [inline-methods] */
    public void lambda$updateState$0$RingtonePreferenceControllerBase(final Preference preference) {
        Uri actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.mContext, getRingtoneType());
        final String title = actualDefaultRingtoneUri == null ? null : Ringtone.getTitle(this.mContext, actualDefaultRingtoneUri, false, true);
        if (title != null) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.notification.-$$Lambda$RingtonePreferenceControllerBase$h93Ioa0jnxmo7Xf-cFJF2cKrK78
                @Override // java.lang.Runnable
                public final void run() {
                    preference.setSummary(title);
                }
            });
        }
    }
}
