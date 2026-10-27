package com.android.car.developeroptions.datausage;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.text.BidiFormatter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.RelativeSizeSpan;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.datausage.BillingCycleSettings;
import com.android.car.developeroptions.datausage.DataUsageSummary;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageSummary extends DataUsageBaseFragment implements DataUsageEditController {
    private NetworkTemplate mDefaultTemplate;
    private DataUsageSummaryPreferenceController mSummaryController;
    private DataUsageSummaryPreference mSummaryPreference;
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.datausage.-$$Lambda$LJ34ahqXyyJ51_a4dZqvpKC6dt4
        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
        public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new DataUsageSummary.SummaryProvider(activity, summaryLoader);
        }
    };
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.datausage.DataUsageSummary.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.data_usage;
            arrayList.add(searchIndexableResource);
            SearchIndexableResource searchIndexableResource2 = new SearchIndexableResource(context);
            searchIndexableResource2.xmlResId = R.xml.data_usage_cellular;
            arrayList.add(searchIndexableResource2);
            SearchIndexableResource searchIndexableResource3 = new SearchIndexableResource(context);
            searchIndexableResource3.xmlResId = R.xml.data_usage_wifi;
            arrayList.add(searchIndexableResource3);
            return arrayList;
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_data_usage;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DataUsageSummary";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 37;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.data_usage;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        boolean zHasMobileData = DataUsageUtils.hasMobileData(context);
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (defaultDataSubscriptionId == -1) {
            zHasMobileData = false;
        }
        this.mDefaultTemplate = DataUsageUtils.getDefaultTemplate(context, defaultDataSubscriptionId);
        this.mSummaryPreference = (DataUsageSummaryPreference) findPreference("status_header");
        if (!zHasMobileData || !isAdmin()) {
            removePreference("restrict_background");
        }
        boolean zHasWifiRadio = DataUsageUtils.hasWifiRadio(context);
        if (zHasMobileData) {
            addMobileSection(defaultDataSubscriptionId);
            if (DataUsageUtils.hasSim(context) && zHasWifiRadio) {
                addWifiSection();
            }
        } else if (zHasWifiRadio) {
            addWifiSection();
        }
        if (DataUsageUtils.hasEthernet(context)) {
            addEthernetSection();
        }
        setHasOptionsMenu(true);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == findPreference("status_header")) {
            BillingCycleSettings.BytesEditorFragment.show((DataUsageEditController) this, false);
            return false;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        FragmentActivity activity = getActivity();
        ArrayList arrayList = new ArrayList();
        DataUsageSummaryPreferenceController dataUsageSummaryPreferenceController = new DataUsageSummaryPreferenceController(activity, getSettingsLifecycle(), this, DataUsageUtils.getDefaultSubscriptionId(activity));
        this.mSummaryController = dataUsageSummaryPreferenceController;
        arrayList.add(dataUsageSummaryPreferenceController);
        getSettingsLifecycle().addObserver(this.mSummaryController);
        return arrayList;
    }

    void addMobileSection(int i) {
        addMobileSection(i, null);
    }

    private void addMobileSection(int i, SubscriptionInfo subscriptionInfo) {
        TemplatePreferenceCategory templatePreferenceCategory = (TemplatePreferenceCategory) inflatePreferences(R.xml.data_usage_cellular);
        templatePreferenceCategory.setTemplate(getNetworkTemplate(i), i, this.services);
        templatePreferenceCategory.pushTemplates(this.services);
        if (subscriptionInfo == null || TextUtils.isEmpty(subscriptionInfo.getDisplayName())) {
            return;
        }
        templatePreferenceCategory.findPreference("mobile_category").setTitle(subscriptionInfo.getDisplayName());
    }

    void addWifiSection() {
        ((TemplatePreferenceCategory) inflatePreferences(R.xml.data_usage_wifi)).setTemplate(NetworkTemplate.buildTemplateWifiWildcard(), 0, this.services);
    }

    private void addEthernetSection() {
        ((TemplatePreferenceCategory) inflatePreferences(R.xml.data_usage_ethernet)).setTemplate(NetworkTemplate.buildTemplateEthernet(), 0, this.services);
    }

    private Preference inflatePreferences(int i) {
        PreferenceScreen preferenceScreenInflateFromResource = getPreferenceManager().inflateFromResource(getPrefContext(), i, null);
        Preference preference = preferenceScreenInflateFromResource.getPreference(0);
        preferenceScreenInflateFromResource.removeAll();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preference.setOrder(preferenceScreen.getPreferenceCount());
        preferenceScreen.addPreference(preference);
        return preference;
    }

    private NetworkTemplate getNetworkTemplate(int i) {
        return NetworkTemplate.normalize(NetworkTemplate.buildTemplateMobileAll(this.services.mTelephonyManager.getSubscriberId(i)), this.services.mTelephonyManager.getMergedSubscriberIds());
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateState();
    }

    static CharSequence formatUsage(Context context, String str, long j) {
        return formatUsage(context, str, j, 1.5625f, 0.64f);
    }

    static CharSequence formatUsage(Context context, String str, long j, float f, float f2) {
        Formatter.BytesResult bytes = Formatter.formatBytes(context.getResources(), j, 10);
        SpannableString spannableString = new SpannableString(bytes.value);
        spannableString.setSpan(new RelativeSizeSpan(f), 0, spannableString.length(), 18);
        CharSequence charSequenceExpandTemplate = TextUtils.expandTemplate(new SpannableString(context.getString(android.R.string.default_audio_route_name_external_device).replace("%1$s", "^1").replace("%2$s", "^2")), spannableString, bytes.units);
        SpannableString spannableString2 = new SpannableString(str);
        spannableString2.setSpan(new RelativeSizeSpan(f2), 0, spannableString2.length(), 18);
        return TextUtils.expandTemplate(spannableString2, BidiFormatter.getInstance().unicodeWrap(charSequenceExpandTemplate.toString()));
    }

    private void updateState() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 1; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof TemplatePreferenceCategory) {
                ((TemplatePreferenceCategory) preference).pushTemplates(this.services);
            }
        }
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public NetworkPolicyEditor getNetworkPolicyEditor() {
        return this.services.mPolicyEditor;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public NetworkTemplate getNetworkTemplate() {
        return this.mDefaultTemplate;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public void updateDataUsage() {
        updateState();
        this.mSummaryController.updateState(this.mSummaryPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Activity mActivity;
        private final DataUsageController mDataController;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            this.mActivity = activity;
            this.mSummaryLoader = summaryLoader;
            this.mDataController = new DataUsageController(activity);
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            if (z) {
                if (DataUsageUtils.hasSim(this.mActivity)) {
                    this.mSummaryLoader.setSummary(this, this.mActivity.getString(R.string.data_usage_summary_format, new Object[]{formatUsedData()}));
                    return;
                }
                DataUsageController.DataUsageInfo wifiDataUsageInfo = this.mDataController.getWifiDataUsageInfo();
                if (wifiDataUsageInfo == null) {
                    this.mSummaryLoader.setSummary(this, null);
                    return;
                }
                this.mSummaryLoader.setSummary(this, TextUtils.expandTemplate(this.mActivity.getText(R.string.data_usage_wifi_format), DataUsageUtils.formatDataUsage(this.mActivity, wifiDataUsageInfo.usageLevel)));
            }
        }

        private CharSequence formatUsedData() {
            SubscriptionManager subscriptionManager = (SubscriptionManager) this.mActivity.getSystemService("telephony_subscription_service");
            int defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
            if (defaultSubscriptionId == -1) {
                return formatFallbackData();
            }
            SubscriptionPlan primaryPlan = DataUsageSummaryPreferenceController.getPrimaryPlan(subscriptionManager, defaultSubscriptionId);
            if (primaryPlan == null) {
                return formatFallbackData();
            }
            if (DataUsageSummaryPreferenceController.unlimited(primaryPlan.getDataLimitBytes())) {
                return DataUsageUtils.formatDataUsage(this.mActivity, primaryPlan.getDataUsageBytes());
            }
            return Utils.formatPercentage(primaryPlan.getDataUsageBytes(), primaryPlan.getDataLimitBytes());
        }

        private CharSequence formatFallbackData() {
            DataUsageController.DataUsageInfo dataUsageInfo = this.mDataController.getDataUsageInfo();
            if (dataUsageInfo == null) {
                return DataUsageUtils.formatDataUsage(this.mActivity, 0L);
            }
            long j = dataUsageInfo.limitLevel;
            if (j <= 0) {
                return DataUsageUtils.formatDataUsage(this.mActivity, dataUsageInfo.usageLevel);
            }
            return Utils.formatPercentage(dataUsageInfo.usageLevel, j);
        }
    }
}
