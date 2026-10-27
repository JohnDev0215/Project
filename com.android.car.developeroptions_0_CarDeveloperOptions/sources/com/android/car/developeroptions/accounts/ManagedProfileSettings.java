package com.android.car.developeroptions.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ManagedProfileSettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accounts.ManagedProfileSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.managed_profile_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ManagedProfileBroadcastReceiver mManagedProfileBroadcastReceiver;
    private UserHandle mManagedUser;
    private UserManager mUserManager;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ManagedProfileSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 401;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.managed_profile_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mUserManager = (UserManager) getSystemService("user");
        UserHandle managedUserFromArgument = getManagedUserFromArgument();
        this.mManagedUser = managedUserFromArgument;
        if (managedUserFromArgument == null) {
            getActivity().finish();
        }
        ((WorkModePreferenceController) use(WorkModePreferenceController.class)).setManagedUser(this.mManagedUser);
        ((ContactSearchPreferenceController) use(ContactSearchPreferenceController.class)).setManagedUser(this.mManagedUser);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ManagedProfileBroadcastReceiver managedProfileBroadcastReceiver = new ManagedProfileBroadcastReceiver();
        this.mManagedProfileBroadcastReceiver = managedProfileBroadcastReceiver;
        managedProfileBroadcastReceiver.register(getActivity());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ManagedProfileBroadcastReceiver managedProfileBroadcastReceiver = this.mManagedProfileBroadcastReceiver;
        if (managedProfileBroadcastReceiver != null) {
            managedProfileBroadcastReceiver.unregister(getActivity());
        }
    }

    private UserHandle getManagedUserFromArgument() {
        UserHandle userHandle;
        Bundle arguments = getArguments();
        return (arguments == null || (userHandle = (UserHandle) arguments.getParcelable("android.intent.extra.USER")) == null || !this.mUserManager.isManagedProfile(userHandle.getIdentifier())) ? Utils.getManagedProfile(this.mUserManager) : userHandle;
    }

    private class ManagedProfileBroadcastReceiver extends BroadcastReceiver {
        private ManagedProfileBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            Log.v("ManagedProfileSettings", "Received broadcast: " + action);
            if ("android.intent.action.MANAGED_PROFILE_REMOVED".equals(action)) {
                if (intent.getIntExtra("android.intent.extra.user_handle", -10000) == ManagedProfileSettings.this.mManagedUser.getIdentifier()) {
                    ManagedProfileSettings.this.getActivity().finish();
                }
            } else {
                Log.w("ManagedProfileSettings", "Cannot handle received broadcast: " + intent.getAction());
            }
        }

        public void register(Context context) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
            context.registerReceiver(this, intentFilter);
        }

        public void unregister(Context context) {
            context.unregisterReceiver(this);
        }
    }
}
