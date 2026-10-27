package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.datausage.CellDataPreference;
import com.android.car.developeroptions.datausage.TemplatePreference;

/* JADX INFO: loaded from: classes.dex */
public class BillingCyclePreference extends Preference implements TemplatePreference {
    private final CellDataPreference.DataStateListener mListener;
    private TemplatePreference.NetworkServices mServices;
    private int mSubId;
    private NetworkTemplate mTemplate;

    public BillingCyclePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListener = new CellDataPreference.DataStateListener() { // from class: com.android.car.developeroptions.datausage.BillingCyclePreference.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                BillingCyclePreference.this.updateEnabled();
            }
        };
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mListener.setListener(true, this.mSubId, getContext());
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        this.mListener.setListener(false, this.mSubId, getContext());
        super.onDetached();
    }

    @Override // com.android.car.developeroptions.datausage.TemplatePreference
    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
        this.mTemplate = networkTemplate;
        this.mSubId = i;
        this.mServices = networkServices;
        setSummary((CharSequence) null);
        setIntent(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEnabled() {
        try {
            setEnabled(this.mServices.mNetworkService.isBandwidthControlEnabled() && this.mServices.mTelephonyManager.getDataEnabled(this.mSubId) && this.mServices.mUserManager.isAdminUser());
        } catch (RemoteException unused) {
            setEnabled(false);
        }
    }

    @Override // androidx.preference.Preference
    public Intent getIntent() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("network_template", this.mTemplate);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(BillingCycleSettings.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.billing_cycle);
        subSettingLauncher.setSourceMetricsCategory(0);
        return subSettingLauncher.toIntent();
    }
}
