package com.android.car.developeroptions.notification;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import com.android.car.developeroptions.RingtonePreference;

/* JADX INFO: loaded from: classes.dex */
public class NotificationSoundPreference extends RingtonePreference {
    private Uri mRingtone;

    public NotificationSoundPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.car.developeroptions.RingtonePreference
    protected Uri onRestoreRingtone() {
        return this.mRingtone;
    }

    public void setRingtone(Uri uri) {
        this.mRingtone = uri;
        setSummary(" ");
        updateRingtoneName(this.mRingtone);
    }

    @Override // com.android.car.developeroptions.RingtonePreference
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            return true;
        }
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
        setRingtone(uri);
        callChangeListener(uri);
        return true;
    }

    private void updateRingtoneName(final Uri uri) {
        new AsyncTask<Object, Void, CharSequence>() { // from class: com.android.car.developeroptions.notification.NotificationSoundPreference.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.AsyncTask
            public CharSequence doInBackground(Object... objArr) {
                Uri uri2 = uri;
                if (uri2 == null) {
                    return NotificationSoundPreference.this.getContext().getString(R.string.permdesc_accessWimaxState);
                }
                if (RingtoneManager.isDefault(uri2)) {
                    return NotificationSoundPreference.this.getContext().getString(com.android.car.developeroptions.R.string.notification_sound_default);
                }
                if ("android.resource".equals(uri.getScheme())) {
                    return NotificationSoundPreference.this.getContext().getString(com.android.car.developeroptions.R.string.notification_unknown_sound_title);
                }
                return Ringtone.getTitle(NotificationSoundPreference.this.getContext(), uri, false, true);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(CharSequence charSequence) {
                NotificationSoundPreference.this.setSummary(charSequence);
            }
        }.execute(new Object[0]);
    }
}
