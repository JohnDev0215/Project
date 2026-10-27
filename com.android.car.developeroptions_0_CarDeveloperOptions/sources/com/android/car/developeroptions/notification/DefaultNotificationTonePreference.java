package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import com.android.car.developeroptions.DefaultRingtonePreference;

/* JADX INFO: loaded from: classes.dex */
public class DefaultNotificationTonePreference extends DefaultRingtonePreference {
    private Uri mRingtone;

    public DefaultNotificationTonePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.car.developeroptions.DefaultRingtonePreference, com.android.car.developeroptions.RingtonePreference
    protected Uri onRestoreRingtone() {
        return this.mRingtone;
    }

    @Override // com.android.car.developeroptions.DefaultRingtonePreference, com.android.car.developeroptions.RingtonePreference
    public void onPrepareRingtonePickerIntent(Intent intent) {
        super.onPrepareRingtonePickerIntent(intent);
        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", this.mRingtone);
    }
}
