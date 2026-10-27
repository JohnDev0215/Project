package com.android.car.developeroptions.notification;

import android.app.AlertDialog;
import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import com.android.car.developeroptions.utils.ZenServiceListing;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeAutomationSettings extends ZenModeSettingsBase {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.ZenModeAutomationSettings.3
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.zen_mode_automation_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    protected final ManagedServiceSettings.Config CONFIG = getConditionProviderConfig();
    private boolean[] mDeleteDialogChecked;
    private String[] mDeleteDialogRuleIds;
    private CharSequence[] mDeleteDialogRuleNames;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 142;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_automation_settings;
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("DELETE_RULE")) {
            return;
        }
        this.mBackend.removeZenRule(arguments.getString("DELETE_RULE"));
        arguments.remove("DELETE_RULE");
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ZenServiceListing zenServiceListing = new ZenServiceListing(getContext(), this.CONFIG);
        zenServiceListing.reloadApprovedServices();
        return buildPreferenceControllers(context, this, zenServiceListing, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Fragment fragment, ZenServiceListing zenServiceListing, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeAddAutomaticRulePreferenceController(context, fragment, zenServiceListing, lifecycle));
        arrayList.add(new ZenModeAutomaticRulesPreferenceController(context, fragment, lifecycle));
        return arrayList;
    }

    protected static ManagedServiceSettings.Config getConditionProviderConfig() {
        ManagedServiceSettings.Config.Builder builder = new ManagedServiceSettings.Config.Builder();
        builder.setTag("ZenModeSettings");
        builder.setIntentAction("android.service.notification.ConditionProviderService");
        builder.setConfigurationIntentAction("android.app.action.AUTOMATIC_ZEN_RULE");
        builder.setPermission("android.permission.BIND_CONDITION_PROVIDER_SERVICE");
        builder.setNoun("condition provider");
        return builder.build();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 1, 0, R.string.zen_mode_delete_automatic_rules);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            final Map.Entry<String, AutomaticZenRule>[] automaticZenRules = this.mBackend.getAutomaticZenRules();
            this.mDeleteDialogRuleNames = new CharSequence[automaticZenRules.length];
            this.mDeleteDialogRuleIds = new String[automaticZenRules.length];
            this.mDeleteDialogChecked = new boolean[automaticZenRules.length];
            for (int i = 0; i < automaticZenRules.length; i++) {
                this.mDeleteDialogRuleNames[i] = automaticZenRules[i].getValue().getName();
                this.mDeleteDialogRuleIds[i] = automaticZenRules[i].getKey();
            }
            new AlertDialog.Builder(this.mContext).setTitle(R.string.zen_mode_delete_automatic_rules).setMultiChoiceItems(this.mDeleteDialogRuleNames, (boolean[]) null, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.android.car.developeroptions.notification.ZenModeAutomationSettings.2
                @Override // android.content.DialogInterface.OnMultiChoiceClickListener
                public void onClick(DialogInterface dialogInterface, int i2, boolean z) {
                    ZenModeAutomationSettings.this.mDeleteDialogChecked[i2] = z;
                }
            }).setPositiveButton(R.string.zen_mode_schedule_delete, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.notification.ZenModeAutomationSettings.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    for (int i3 = 0; i3 < automaticZenRules.length; i3++) {
                        if (ZenModeAutomationSettings.this.mDeleteDialogChecked[i3]) {
                            ZenModeAutomationSettings zenModeAutomationSettings = ZenModeAutomationSettings.this;
                            zenModeAutomationSettings.mBackend.removeZenRule(zenModeAutomationSettings.mDeleteDialogRuleIds[i3]);
                        }
                    }
                }
            }).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
