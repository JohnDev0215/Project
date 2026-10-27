package com.android.phone;

import android.content.Context;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.AttributeSet;
import com.android.internal.telephony.Phone;

/* JADX INFO: loaded from: classes.dex */
public class CdmaVoicePrivacySwitchPreference extends SwitchPreference {
    private MyHandler mHandler;
    Phone phone;

    public CdmaVoicePrivacySwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new MyHandler();
        Phone phone = PhoneGlobals.getPhone();
        this.phone = phone;
        phone.getEnhancedVoicePrivacy(this.mHandler.obtainMessage(0));
    }

    public CdmaVoicePrivacySwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.switchPreferenceStyle);
    }

    public CdmaVoicePrivacySwitchPreference(Context context) {
        this(context, null);
    }

    @Override // android.preference.TwoStatePreference, android.preference.Preference
    protected void onClick() {
        super.onClick();
        this.phone.enableEnhancedVoicePrivacy(isChecked(), this.mHandler.obtainMessage(1));
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleGetVPResponse(message);
            } else {
                if (i != 1) {
                    return;
                }
                handleSetVPResponse(message);
            }
        }

        private void handleGetVPResponse(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            if (asyncResult.exception != null) {
                CdmaVoicePrivacySwitchPreference.this.setEnabled(false);
                return;
            }
            int i = ((int[]) asyncResult.result)[0];
            CdmaVoicePrivacySwitchPreference.this.setChecked(i != 0);
            Settings.Secure.putInt(CdmaVoicePrivacySwitchPreference.this.getContext().getContentResolver(), "enhanced_voice_privacy_enabled", i);
        }

        private void handleSetVPResponse(Message message) {
            Throwable th = ((AsyncResult) message.obj).exception;
            CdmaVoicePrivacySwitchPreference.this.phone.getEnhancedVoicePrivacy(obtainMessage(0));
        }
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
