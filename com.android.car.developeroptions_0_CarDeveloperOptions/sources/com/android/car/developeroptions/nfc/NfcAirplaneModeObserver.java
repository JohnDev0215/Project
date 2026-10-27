package com.android.car.developeroptions.nfc;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class NfcAirplaneModeObserver extends ContentObserver {
    static final Uri AIRPLANE_MODE_URI = Settings.Global.getUriFor("airplane_mode_on");
    private int mAirplaneMode;
    private final Context mContext;
    private final NfcAdapter mNfcAdapter;
    private final Preference mPreference;

    public NfcAirplaneModeObserver(Context context, NfcAdapter nfcAdapter, Preference preference) {
        super(new Handler(Looper.getMainLooper()));
        this.mContext = context;
        this.mNfcAdapter = nfcAdapter;
        this.mPreference = preference;
        updateNfcPreference();
    }

    public void register() {
        this.mContext.getContentResolver().registerContentObserver(AIRPLANE_MODE_URI, false, this);
    }

    public void unregister() {
        this.mContext.getContentResolver().unregisterContentObserver(this);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        updateNfcPreference();
    }

    private void updateNfcPreference() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", this.mAirplaneMode);
        if (i == this.mAirplaneMode) {
            return;
        }
        this.mAirplaneMode = i;
        if (i == 1) {
            this.mNfcAdapter.disable();
            this.mPreference.setEnabled(NfcPreferenceController.isToggleableInAirplaneMode(this.mContext));
        } else {
            this.mPreference.setEnabled(true);
        }
    }
}
