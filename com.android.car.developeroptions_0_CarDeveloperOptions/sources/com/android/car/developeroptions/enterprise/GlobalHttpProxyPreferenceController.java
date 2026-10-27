package com.android.car.developeroptions.enterprise;

import android.content.Context;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class GlobalHttpProxyPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final EnterprisePrivacyFeatureProvider mFeatureProvider;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "global_http_proxy";
    }

    public GlobalHttpProxyPreferenceController(Context context) {
        super(context);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mFeatureProvider.isGlobalHttpProxySet();
    }
}
