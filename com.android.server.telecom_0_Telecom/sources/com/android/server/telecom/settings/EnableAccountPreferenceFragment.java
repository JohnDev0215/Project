package com.android.server.telecom.settings;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.server.telecom.R;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EnableAccountPreferenceFragment extends PreferenceFragment {
    private TelecomManager mTelecomManager;

    /* JADX INFO: Access modifiers changed from: private */
    final class AccountSwitchPreference extends SwitchPreference {
        private final PhoneAccount mAccount;

        public AccountSwitchPreference(Context context, PhoneAccount phoneAccount) {
            super(context);
            this.mAccount = phoneAccount;
            setTitle(phoneAccount.getLabel());
            setSummary(phoneAccount.getShortDescription());
            Icon icon = phoneAccount.getIcon();
            if (icon != null) {
                setIcon(icon.loadDrawable(context));
            }
            setChecked(phoneAccount.isEnabled());
            setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.server.telecom.settings.-$$Lambda$EnableAccountPreferenceFragment$AccountSwitchPreference$j6qrBrKygaM0_AM4_KZb4Y2pLyw
                @Override // android.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return this.f$0.onPreferenceChange(preference, obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onPreferenceChange(Preference preference, Object obj) {
            Log.d(this, "onPreferenceChange: key = %s", new Object[]{preference.getKey()});
            Log.d(this, "  preference = '%s'", new Object[]{preference});
            Log.d(this, "  newValue = '%b'", new Object[]{obj});
            EnableAccountPreferenceFragment.this.mTelecomManager.enablePhoneAccount(this.mAccount.getAccountHandle(), ((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTelecomManager = TelecomManager.from(getActivity());
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        addPreferencesFromResource(R.xml.enable_account_preference);
        PreferenceScreen preferenceScreen2 = getPreferenceScreen();
        List callCapablePhoneAccounts = this.mTelecomManager.getCallCapablePhoneAccounts(true);
        Activity activity = getActivity();
        Iterator it = callCapablePhoneAccounts.iterator();
        while (it.hasNext()) {
            PhoneAccount phoneAccount = this.mTelecomManager.getPhoneAccount((PhoneAccountHandle) it.next());
            if (phoneAccount != null) {
                if (!((phoneAccount.getCapabilities() & 4) != 0)) {
                    preferenceScreen2.addPreference(new AccountSwitchPreference(activity, phoneAccount));
                }
            }
        }
    }
}
