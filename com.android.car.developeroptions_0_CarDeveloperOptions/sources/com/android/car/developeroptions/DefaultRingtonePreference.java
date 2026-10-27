package com.android.car.developeroptions;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class DefaultRingtonePreference extends RingtonePreference {
    public DefaultRingtonePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.car.developeroptions.RingtonePreference
    public void onPrepareRingtonePickerIntent(Intent intent) {
        super.onPrepareRingtonePickerIntent(intent);
        intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
    }

    @Override // com.android.car.developeroptions.RingtonePreference
    protected void onSaveRingtone(Uri uri) {
        RingtoneManager.setActualDefaultRingtoneUri(this.mUserContext, getRingtoneType(), uri);
    }

    @Override // com.android.car.developeroptions.RingtonePreference
    protected Uri onRestoreRingtone() {
        return RingtoneManager.getActualDefaultRingtoneUri(this.mUserContext, getRingtoneType());
    }
}
