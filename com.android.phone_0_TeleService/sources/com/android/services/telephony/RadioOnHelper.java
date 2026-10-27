package com.android.services.telephony;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.services.telephony.RadioOnStateListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RadioOnHelper implements RadioOnStateListener.Callback {
    private RadioOnStateListener.Callback mCallback;
    private final Context mContext;
    private List<RadioOnStateListener> mInProgressListeners = new ArrayList(2);
    private boolean mIsRadioOnCallingEnabled;
    private List<RadioOnStateListener> mListeners;

    public RadioOnHelper(Context context) {
        this.mContext = context;
    }

    private void setupListeners() {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList(2);
        }
        int activeModemCount = TelephonyManager.from(this.mContext).getActiveModemCount();
        while (this.mListeners.size() < activeModemCount) {
            this.mListeners.add(new RadioOnStateListener());
        }
        while (this.mListeners.size() > activeModemCount) {
            this.mListeners.get(r1.size() - 1).cleanup();
            this.mListeners.remove(r1.size() - 1);
        }
    }

    public void triggerRadioOnAndListen(RadioOnStateListener.Callback callback, boolean z, Phone phone) {
        setupListeners();
        this.mCallback = callback;
        this.mInProgressListeners.clear();
        this.mIsRadioOnCallingEnabled = false;
        for (int i = 0; i < TelephonyManager.from(this.mContext).getActiveModemCount(); i++) {
            Phone phone2 = PhoneFactory.getPhone(i);
            if (phone2 != null) {
                this.mInProgressListeners.add(this.mListeners.get(i));
                this.mListeners.get(i).waitForRadioOn(phone2, this, z, z && phone2 == phone);
            }
        }
        powerOnRadio(z, phone);
    }

    private void powerOnRadio(boolean z, Phone phone) {
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) > 0) {
            Log.d(this, "==> Turning off airplane mode for emergency call.", new Object[0]);
            Settings.Global.putInt(this.mContext.getContentResolver(), "airplane_mode_on", 0);
            Phone[] phones = PhoneFactory.getPhones();
            int length = phones.length;
            for (int i = 0; i < length; i++) {
                Phone phone2 = phones[i];
                Log.d(this, "powerOnRadio, enabling Radio", new Object[0]);
                phone2.setRadioPower(true, z, phone2 == phone, false);
            }
            Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
            intent.putExtra("state", false);
            this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
        }
    }

    @Override // com.android.services.telephony.RadioOnStateListener.Callback
    public void onComplete(RadioOnStateListener radioOnStateListener, boolean z) {
        this.mIsRadioOnCallingEnabled = z | this.mIsRadioOnCallingEnabled;
        this.mInProgressListeners.remove(radioOnStateListener);
        if (this.mCallback == null || !this.mInProgressListeners.isEmpty()) {
            return;
        }
        this.mCallback.onComplete(null, this.mIsRadioOnCallingEnabled);
    }

    @Override // com.android.services.telephony.RadioOnStateListener.Callback
    public boolean isOkToCall(Phone phone, int i) {
        RadioOnStateListener.Callback callback = this.mCallback;
        if (callback == null) {
            return false;
        }
        return callback.isOkToCall(phone, i);
    }
}
