package com.android.car.developeroptions.applications.specialaccess.vrlistener;

import android.content.ComponentName;
import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VrListenerSettings extends ManagedServiceSettings {
    private static final ManagedServiceSettings.Config CONFIG;
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    private static final String TAG = VrListenerSettings.class.getSimpleName();

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 334;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.vr_listeners_settings;
    }

    static {
        ManagedServiceSettings.Config.Builder builder = new ManagedServiceSettings.Config.Builder();
        builder.setTag(TAG);
        builder.setSetting("enabled_vr_listeners");
        builder.setIntentAction("android.service.vr.VrListenerService");
        builder.setPermission("android.permission.BIND_VR_LISTENER_SERVICE");
        builder.setNoun("vr listener");
        builder.setWarningDialogTitle(R.string.vr_listener_security_warning_title);
        builder.setWarningDialogSummary(R.string.vr_listener_security_warning_summary);
        builder.setEmptyText(R.string.no_vr_listeners);
        CONFIG = builder.build();
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.applications.specialaccess.vrlistener.VrListenerSettings.1
            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
                ArrayList arrayList = new ArrayList();
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.vr_listeners_settings;
                arrayList.add(searchIndexableResource);
                return arrayList;
            }
        };
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    protected ManagedServiceSettings.Config getConfig() {
        return CONFIG;
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    protected boolean setEnabled(ComponentName componentName, String str, boolean z) {
        logSpecialPermissionChange(z, componentName.getPackageName());
        return super.setEnabled(componentName, str, z);
    }

    void logSpecialPermissionChange(boolean z, String str) {
        int i = z ? 772 : 773;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), i, getMetricsCategory(), str, 0);
    }
}
