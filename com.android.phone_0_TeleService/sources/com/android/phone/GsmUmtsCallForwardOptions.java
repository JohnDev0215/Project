package com.android.phone;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.MenuItem;
import com.android.internal.telephony.CallForwardInfo;
import com.android.internal.telephony.Phone;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GsmUmtsCallForwardOptions extends TimeConsumingPreferenceActivity {
    private static final String[] NUM_PROJECTION = {"data1"};
    private CallForwardEditPreference mButtonCFB;
    private CallForwardEditPreference mButtonCFNRc;
    private CallForwardEditPreference mButtonCFNRy;
    private CallForwardEditPreference mButtonCFU;
    private boolean mCallForwardByUssd;
    private boolean mFirstResume;
    private Bundle mIcicle;
    private Phone mPhone;
    private boolean mReplaceInvalidCFNumbers;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private final ArrayList<CallForwardEditPreference> mPreferences = new ArrayList<>();
    private int mInitIndex = 0;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        PersistableBundle carrierConfig;
        boolean z;
        boolean z2;
        boolean z3;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.callforward_options);
        SubscriptionInfoHelper subscriptionInfoHelper = new SubscriptionInfoHelper(this, getIntent());
        this.mSubscriptionInfoHelper = subscriptionInfoHelper;
        subscriptionInfoHelper.setActionBarTitle(getActionBar(), getResources(), R.string.call_forwarding_settings_with_label);
        this.mPhone = this.mSubscriptionInfoHelper.getPhone();
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        if (carrierConfig != null) {
            this.mReplaceInvalidCFNumbers = carrierConfig.getBoolean("call_forwarding_map_non_number_to_voicemail_bool");
            this.mCallForwardByUssd = carrierConfig.getBoolean("use_call_forwarding_ussd_bool");
            z2 = carrierConfig.getBoolean("call_forwarding_when_busy_supported_bool");
            z3 = carrierConfig.getBoolean("call_forwarding_when_unreachable_supported_bool");
            z = carrierConfig.getBoolean("call_forwarding_when_unanswered_supported_bool");
        } else {
            z = true;
            z2 = true;
            z3 = true;
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mButtonCFU = (CallForwardEditPreference) preferenceScreen.findPreference("button_cfu_key");
        this.mButtonCFB = (CallForwardEditPreference) preferenceScreen.findPreference("button_cfb_key");
        this.mButtonCFNRy = (CallForwardEditPreference) preferenceScreen.findPreference("button_cfnry_key");
        this.mButtonCFNRc = (CallForwardEditPreference) preferenceScreen.findPreference("button_cfnrc_key");
        CallForwardEditPreference callForwardEditPreference = this.mButtonCFU;
        callForwardEditPreference.setParentActivity(this, callForwardEditPreference.reason);
        CallForwardEditPreference callForwardEditPreference2 = this.mButtonCFB;
        callForwardEditPreference2.setParentActivity(this, callForwardEditPreference2.reason);
        CallForwardEditPreference callForwardEditPreference3 = this.mButtonCFNRy;
        callForwardEditPreference3.setParentActivity(this, callForwardEditPreference3.reason);
        CallForwardEditPreference callForwardEditPreference4 = this.mButtonCFNRc;
        callForwardEditPreference4.setParentActivity(this, callForwardEditPreference4.reason);
        this.mPreferences.add(this.mButtonCFU);
        layoutCallForwardItem(z2, this.mButtonCFB, preferenceScreen);
        layoutCallForwardItem(z, this.mButtonCFNRy, preferenceScreen);
        layoutCallForwardItem(z3, this.mButtonCFNRc, preferenceScreen);
        if (this.mCallForwardByUssd) {
            preferenceScreen.removePreference(this.mButtonCFU);
            preferenceScreen.removePreference(this.mButtonCFB);
            preferenceScreen.removePreference(this.mButtonCFNRc);
            this.mPreferences.remove(this.mButtonCFU);
            this.mPreferences.remove(this.mButtonCFB);
            this.mPreferences.remove(this.mButtonCFNRc);
            this.mButtonCFNRy.setDependency(null);
        }
        this.mFirstResume = true;
        this.mIcicle = bundle;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void layoutCallForwardItem(boolean z, CallForwardEditPreference callForwardEditPreference, PreferenceScreen preferenceScreen) {
        if (z) {
            this.mPreferences.add(callForwardEditPreference);
        } else {
            preferenceScreen.removePreference(callForwardEditPreference);
        }
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mFirstResume) {
            if (this.mIcicle == null) {
                Log.d("GsmUmtsCallForwardOptions", "start to init ");
                CallForwardEditPreference callForwardEditPreference = this.mPreferences.get(this.mInitIndex);
                callForwardEditPreference.init(this, this.mPhone, this.mReplaceInvalidCFNumbers, this.mCallForwardByUssd);
                callForwardEditPreference.startCallForwardOptionsQuery();
            } else {
                this.mInitIndex = this.mPreferences.size();
                for (CallForwardEditPreference callForwardEditPreference2 : this.mPreferences) {
                    Bundle bundle = (Bundle) this.mIcicle.getParcelable(callForwardEditPreference2.getKey());
                    callForwardEditPreference2.setToggled(bundle.getBoolean("toggle"));
                    callForwardEditPreference2.setEnabled(bundle.getBoolean("enable"));
                    CallForwardInfo callForwardInfo = new CallForwardInfo();
                    callForwardInfo.number = bundle.getString("number");
                    callForwardInfo.status = bundle.getInt("status");
                    callForwardEditPreference2.init(this, this.mPhone, this.mReplaceInvalidCFNumbers, this.mCallForwardByUssd);
                    callForwardEditPreference2.restoreCallForwardInfo(callForwardInfo);
                }
            }
            this.mFirstResume = false;
            this.mIcicle = null;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (CallForwardEditPreference callForwardEditPreference : this.mPreferences) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("toggle", callForwardEditPreference.isToggled());
            bundle2.putBoolean("enable", callForwardEditPreference.isEnabled());
            CallForwardInfo callForwardInfo = callForwardEditPreference.callForwardInfo;
            if (callForwardInfo != null) {
                bundle2.putString("number", callForwardInfo.number);
                bundle2.putInt("status", callForwardEditPreference.callForwardInfo.status);
            }
            bundle.putParcelable(callForwardEditPreference.getKey(), bundle2);
        }
    }

    @Override // com.android.phone.TimeConsumingPreferenceActivity, com.android.phone.TimeConsumingPreferenceListener
    public void onFinished(Preference preference, boolean z) {
        if (this.mInitIndex < this.mPreferences.size() - 1 && !isFinishing()) {
            int i = this.mInitIndex + 1;
            this.mInitIndex = i;
            CallForwardEditPreference callForwardEditPreference = this.mPreferences.get(i);
            callForwardEditPreference.init(this, this.mPhone, this.mReplaceInvalidCFNumbers, this.mCallForwardByUssd);
            callForwardEditPreference.startCallForwardOptionsQuery();
        }
        super.onFinished(preference, z);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.d("GsmUmtsCallForwardOptions", "onActivityResult: done");
        if (i2 != -1) {
            Log.d("GsmUmtsCallForwardOptions", "onActivityResult: contact picker result not OK.");
            return;
        }
        Cursor cursor = null;
        try {
            Cursor cursorQuery = getContentResolver().query(intent.getData(), NUM_PROJECTION, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                if (i == 0) {
                    this.mButtonCFU.onPickActivityResult(cursorQuery.getString(0));
                } else if (i == 1) {
                    this.mButtonCFB.onPickActivityResult(cursorQuery.getString(0));
                } else if (i == 2) {
                    this.mButtonCFNRy.onPickActivityResult(cursorQuery.getString(0));
                } else if (i == 3) {
                    this.mButtonCFNRc.onPickActivityResult(cursorQuery.getString(0));
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                    return;
                }
                return;
            }
            Log.d("GsmUmtsCallForwardOptions", "onActivityResult: bad contact data, no results found.");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            CallFeaturesSetting.goUpToTopLevelSetting(this, this.mSubscriptionInfoHelper);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
