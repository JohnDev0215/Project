package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.net.NetworkTemplate;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.car.developeroptions.datausage.TemplatePreference;

/* JADX INFO: loaded from: classes.dex */
public class NetworkRestrictionsPreference extends Preference implements TemplatePreference {
    @Override // com.android.car.developeroptions.datausage.TemplatePreference
    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
    }

    public NetworkRestrictionsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
