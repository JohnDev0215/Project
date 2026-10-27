package com.android.car.developeroptions.deviceinfo.imei;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.slices.Sliceable;
import com.android.settingslib.Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImeiInfoPreferenceController extends BasePreferenceController {
    private Fragment mFragment;
    private final boolean mIsMultiSim;
    private final List<Preference> mPreferenceList;
    private final TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean isCopyableSlice() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public ImeiInfoPreferenceController(Context context, String str) {
        super(context, str);
        this.mPreferenceList = new ArrayList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        this.mIsMultiSim = telephonyManager.getPhoneCount() > 1;
    }

    public void setHost(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceList.add(preferenceFindPreference);
        updatePreference(preferenceFindPreference, 0);
        int order = preferenceFindPreference.getOrder();
        for (int i = 1; i < this.mTelephonyManager.getPhoneCount(); i++) {
            Preference preferenceCreateNewPreference = createNewPreference(preferenceScreen.getContext());
            preferenceCreateNewPreference.setOrder(order + i);
            preferenceCreateNewPreference.setKey(getPreferenceKey() + i);
            preferenceScreen.addPreference(preferenceCreateNewPreference);
            this.mPreferenceList.add(preferenceCreateNewPreference);
            updatePreference(preferenceCreateNewPreference, i);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mTelephonyManager.getPhoneType() == 2) {
            return this.mTelephonyManager.getMeid();
        }
        return this.mTelephonyManager.getImei();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        int iIndexOf = this.mPreferenceList.indexOf(preference);
        if (iIndexOf == -1) {
            return false;
        }
        ImeiInfoDialogFragment.show(this.mFragment, iIndexOf, preference.getTitle().toString());
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!((UserManager) this.mContext.getSystemService(UserManager.class)).isAdminUser() || Utils.isWifiOnly(this.mContext)) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public void copy() {
        Sliceable.setCopyContent(this.mContext, getSummary(), this.mContext.getText(R.string.status_imei));
    }

    private void updatePreference(Preference preference, int i) {
        if (this.mTelephonyManager.getPhoneType() == 2) {
            preference.setTitle(getTitleForCdmaPhone(i));
            preference.setSummary(getMeid(i));
        } else {
            preference.setTitle(getTitleForGsmPhone(i));
            preference.setSummary(this.mTelephonyManager.getImei(i));
        }
    }

    private CharSequence getTitleForGsmPhone(int i) {
        return this.mIsMultiSim ? this.mContext.getString(R.string.imei_multi_sim, Integer.valueOf(i + 1)) : this.mContext.getString(R.string.status_imei);
    }

    private CharSequence getTitleForCdmaPhone(int i) {
        return this.mIsMultiSim ? this.mContext.getString(R.string.meid_multi_sim, Integer.valueOf(i + 1)) : this.mContext.getString(R.string.status_meid_number);
    }

    String getMeid(int i) {
        return this.mTelephonyManager.getMeid(i);
    }

    Preference createNewPreference(Context context) {
        return new Preference(context);
    }
}
