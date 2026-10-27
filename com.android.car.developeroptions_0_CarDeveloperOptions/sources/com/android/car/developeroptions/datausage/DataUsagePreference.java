package com.android.car.developeroptions.datausage;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.datausage.TemplatePreference;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes.dex */
public class DataUsagePreference extends Preference implements TemplatePreference {
    private int mSubId;
    private NetworkTemplate mTemplate;
    private int mTitleRes;

    public DataUsagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.title}, TypedArrayUtils.getAttr(context, com.android.car.developeroptions.R.attr.preferenceStyle, R.attr.preferenceStyle), 0);
        this.mTitleRes = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.android.car.developeroptions.datausage.TemplatePreference
    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
        this.mTemplate = networkTemplate;
        this.mSubId = i;
        DataUsageController dataUsageController = getDataUsageController();
        if (this.mTemplate.isMatchRuleMobile()) {
            setTitle(com.android.car.developeroptions.R.string.app_cellular_data_usage);
        } else {
            DataUsageController.DataUsageInfo dataUsageInfo = dataUsageController.getDataUsageInfo(this.mTemplate);
            setTitle(this.mTitleRes);
            setSummary(getContext().getString(com.android.car.developeroptions.R.string.data_usage_template, DataUsageUtils.formatDataUsage(getContext(), dataUsageInfo.usageLevel), dataUsageInfo.period));
        }
        if (dataUsageController.getHistoricalUsageLevel(networkTemplate) > 0) {
            setIntent(getIntent());
        } else {
            setIntent(null);
            setEnabled(false);
        }
    }

    @Override // androidx.preference.Preference
    public Intent getIntent() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("network_template", this.mTemplate);
        bundle.putInt("sub_id", this.mSubId);
        bundle.putInt("network_type", !this.mTemplate.isMatchRuleMobile() ? 1 : 0);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setDestination(DataUsageList.class.getName());
        subSettingLauncher.setSourceMetricsCategory(0);
        if (this.mTemplate.isMatchRuleMobile()) {
            subSettingLauncher.setTitleRes(com.android.car.developeroptions.R.string.app_cellular_data_usage);
        } else {
            subSettingLauncher.setTitleRes(this.mTitleRes);
        }
        return subSettingLauncher.toIntent();
    }

    DataUsageController getDataUsageController() {
        return new DataUsageController(getContext());
    }
}
