package com.android.car.developeroptions.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.provider.SearchIndexableResource;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.applications.ServiceListing;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.CandidateInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationAssistantPicker extends DefaultAppPickerFragment implements ServiceListing.Callback {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.NotificationAssistantPicker.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.notification_assistant_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private List<CandidateInfo> mCandidateInfos = new ArrayList();

    @VisibleForTesting
    protected Context mContext;

    @VisibleForTesting
    protected NotificationBackend mNotificationBackend;
    private ServiceListing mServiceListing;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 790;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.notification_assistant_settings;
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment, com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mNotificationBackend = new NotificationBackend();
        ServiceListing.Builder builder = new ServiceListing.Builder(context);
        builder.setTag("NotiAssistantPicker");
        builder.setSetting("enabled_notification_assistant");
        builder.setIntentAction("android.service.notification.NotificationAssistantService");
        builder.setPermission("android.permission.BIND_NOTIFICATION_ASSISTANT_SERVICE");
        builder.setNoun("notification assistant");
        ServiceListing serviceListingBuild = builder.build();
        this.mServiceListing = serviceListingBuild;
        serviceListingBuild.addCallback(this);
        this.mServiceListing.reload();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mServiceListing.removeCallback(this);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        return this.mCandidateInfos;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        ComponentName allowedNotificationAssistant = this.mNotificationBackend.getAllowedNotificationAssistant();
        return allowedNotificationAssistant != null ? allowedNotificationAssistant.flattenToString() : "";
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        return this.mNotificationBackend.setNotificationAssistantGranted(ComponentName.unflattenFromString(str));
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment
    protected CharSequence getConfirmationMessage(CandidateInfo candidateInfo) {
        if (TextUtils.isEmpty(candidateInfo.getKey())) {
            return null;
        }
        return this.mContext.getString(R.string.notification_assistant_security_warning_summary, candidateInfo.loadLabel());
    }

    @Override // com.android.settingslib.applications.ServiceListing.Callback
    public void onServicesReloaded(List<ServiceInfo> list) {
        ArrayList arrayList = new ArrayList();
        list.sort(new PackageItemInfo.DisplayNameComparator(this.mPm));
        for (ServiceInfo serviceInfo : list) {
            if (this.mContext.getPackageManager().checkPermission("android.permission.REQUEST_NOTIFICATION_ASSISTANT_SERVICE", serviceInfo.packageName) == 0) {
                arrayList.add(new DefaultAppInfo(this.mContext, this.mPm, this.mUserId, new ComponentName(serviceInfo.packageName, serviceInfo.name)));
            }
        }
        arrayList.add(new CandidateNone(this.mContext));
        this.mCandidateInfos = arrayList;
    }

    public static class CandidateNone extends CandidateInfo {
        public Context mContext;

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return "";
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        public CandidateNone(Context context) {
            super(true);
            this.mContext = context;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.mContext.getString(R.string.no_notification_assistant);
        }
    }
}
