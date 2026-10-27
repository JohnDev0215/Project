package com.android.launcher3.settings;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.Logging.Session;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.launcher3.R;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.config.FlagTogglerPrefUi;
import com.android.launcher3.uioverrides.plugins.PluginManagerWrapper;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class DeveloperOptionsFragment extends PreferenceFragment {
    private static final String ACTION_PLUGIN_SETTINGS = "com.android.systemui.action.PLUGIN_SETTINGS";
    private static final String PLUGIN_PERMISSION = "com.android.systemui.permission.PLUGIN";
    private FlagTogglerPrefUi mFlagTogglerPrefUi;
    private final BroadcastReceiver mPluginReceiver = new BroadcastReceiver() { // from class: com.android.launcher3.settings.DeveloperOptionsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeveloperOptionsFragment.this.loadPluginPrefs();
        }
    };
    private PreferenceCategory mPluginsCategory;
    private PreferenceScreen mPreferenceScreen;

    @Override // androidx.preference.PreferenceFragment
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        getContext().registerReceiver(this.mPluginReceiver, intentFilter);
        getContext().registerReceiver(this.mPluginReceiver, new IntentFilter(Intent.ACTION_USER_UNLOCKED));
        PreferenceScreen preferenceScreenCreatePreferenceScreen = getPreferenceManager().createPreferenceScreen(getContext());
        this.mPreferenceScreen = preferenceScreenCreatePreferenceScreen;
        setPreferenceScreen(preferenceScreenCreatePreferenceScreen);
        initFlags();
        loadPluginPrefs();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(this.mPluginReceiver);
    }

    private PreferenceCategory newCategory(String title) {
        PreferenceCategory preferenceCategory = new PreferenceCategory(getContext());
        preferenceCategory.setOrder(Integer.MAX_VALUE);
        preferenceCategory.setTitle(title);
        this.mPreferenceScreen.addPreference(preferenceCategory);
        return preferenceCategory;
    }

    private void initFlags() {
        if (FeatureFlags.showFlagTogglerUi(getContext())) {
            FlagTogglerPrefUi flagTogglerPrefUi = new FlagTogglerPrefUi(this);
            this.mFlagTogglerPrefUi = flagTogglerPrefUi;
            flagTogglerPrefUi.applyTo(newCategory("Feature flags"));
        }
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        FlagTogglerPrefUi flagTogglerPrefUi = this.mFlagTogglerPrefUi;
        if (flagTogglerPrefUi != null) {
            flagTogglerPrefUi.onCreateOptionsMenu(menu);
        }
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        FlagTogglerPrefUi flagTogglerPrefUi = this.mFlagTogglerPrefUi;
        if (flagTogglerPrefUi != null) {
            flagTogglerPrefUi.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.preference.PreferenceFragment, android.app.Fragment
    public void onStop() {
        FlagTogglerPrefUi flagTogglerPrefUi = this.mFlagTogglerPrefUi;
        if (flagTogglerPrefUi != null) {
            flagTogglerPrefUi.onStop();
        }
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadPluginPrefs() {
        PreferenceCategory preferenceCategory = this.mPluginsCategory;
        if (preferenceCategory != null) {
            this.mPreferenceScreen.removePreference(preferenceCategory);
        }
        if (!PluginManagerWrapper.hasPlugins(getActivity())) {
            this.mPluginsCategory = null;
            return;
        }
        this.mPluginsCategory = newCategory("Plugins");
        PluginManagerWrapper pluginManagerWrapperLambda$get$0$MainThreadInitializedObject = PluginManagerWrapper.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext());
        final Context context = getContext();
        PackageManager packageManager = getContext().getPackageManager();
        Set<String> pluginActions = pluginManagerWrapperLambda$get$0$MainThreadInitializedObject.getPluginActions();
        final ArrayMap arrayMap = new ArrayMap();
        for (String str : pluginActions) {
            String name = toName(str);
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent(str), 512).iterator();
            while (it.hasNext()) {
                String str2 = it.next().serviceInfo.packageName;
                if (!arrayMap.containsKey(str2)) {
                    arrayMap.put(str2, new ArraySet());
                }
                ((ArraySet) arrayMap.get(str2)).add(name);
            }
        }
        List<PackageInfo> packagesHoldingPermissions = packageManager.getPackagesHoldingPermissions(new String[]{PLUGIN_PERMISSION}, 516);
        final PreferenceDataStore pluginEnabler = pluginManagerWrapperLambda$get$0$MainThreadInitializedObject.getPluginEnabler();
        packagesHoldingPermissions.forEach(new Consumer() { // from class: com.android.launcher3.settings.-$$Lambda$DeveloperOptionsFragment$POsBtaxvU__adf1ZSoXUJeYd-5I
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$loadPluginPrefs$0$DeveloperOptionsFragment(arrayMap, context, pluginEnabler, (PackageInfo) obj);
            }
        });
    }

    public /* synthetic */ void lambda$loadPluginPrefs$0$DeveloperOptionsFragment(ArrayMap arrayMap, Context context, PreferenceDataStore preferenceDataStore, PackageInfo packageInfo) {
        if (arrayMap.containsKey(packageInfo.packageName)) {
            PluginPreference pluginPreference = new PluginPreference(context, packageInfo, preferenceDataStore);
            pluginPreference.setSummary("Plugins: " + toString((ArraySet) arrayMap.get(packageInfo.packageName)));
            this.mPluginsCategory.addPreference(pluginPreference);
        }
    }

    private String toString(ArraySet<String> plugins) {
        StringBuilder sb = new StringBuilder();
        for (String str : plugins) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private String toName(String action) {
        String strReplace = action.replace("com.android.systemui.action.PLUGIN_", "");
        StringBuilder sb = new StringBuilder();
        for (String str : strReplace.split(Session.SESSION_SEPARATION_CHAR_CHILD)) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(str.substring(0, 1));
            sb.append(str.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PluginPreference extends SwitchPreference {
        private final boolean mHasSettings;
        private final PackageInfo mInfo;
        private final PreferenceDataStore mPluginEnabler;

        public PluginPreference(Context prefContext, PackageInfo info, PreferenceDataStore pluginEnabler) {
            super(prefContext);
            PackageManager packageManager = prefContext.getPackageManager();
            this.mHasSettings = packageManager.resolveActivity(new Intent(DeveloperOptionsFragment.ACTION_PLUGIN_SETTINGS).setPackage(info.packageName), 0) != null;
            this.mInfo = info;
            this.mPluginEnabler = pluginEnabler;
            setTitle(info.applicationInfo.loadLabel(packageManager));
            setChecked(isPluginEnabled());
            setWidgetLayoutResource(R.layout.switch_preference_with_settings);
        }

        private boolean isEnabled(ComponentName cn) {
            return this.mPluginEnabler.getBoolean(PluginManagerWrapper.pluginEnabledKey(cn), true);
        }

        private boolean isPluginEnabled() {
            for (int i = 0; i < this.mInfo.services.length; i++) {
                if (!isEnabled(new ComponentName(this.mInfo.packageName, this.mInfo.services[i].name))) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.preference.Preference
        protected boolean persistBoolean(boolean isEnabled) {
            boolean z = false;
            for (int i = 0; i < this.mInfo.services.length; i++) {
                ComponentName componentName = new ComponentName(this.mInfo.packageName, this.mInfo.services[i].name);
                if (isEnabled(componentName) != isEnabled) {
                    this.mPluginEnabler.putBoolean(PluginManagerWrapper.pluginEnabledKey(componentName), isEnabled);
                    z = true;
                }
            }
            if (z) {
                String str = this.mInfo.packageName;
                getContext().sendBroadcast(new Intent(PluginManagerWrapper.PLUGIN_CHANGED, str != null ? Uri.fromParts("package", str, null) : null));
            }
            setChecked(isEnabled);
            return true;
        }

        @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder holder) {
            super.onBindViewHolder(holder);
            holder.findViewById(R.id.settings).setVisibility(this.mHasSettings ? 0 : 8);
            holder.findViewById(R.id.divider).setVisibility(this.mHasSettings ? 0 : 8);
            holder.findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.settings.-$$Lambda$DeveloperOptionsFragment$PluginPreference$IuqrwJa_f2odEx9tc7JawD7g5QA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0$DeveloperOptionsFragment$PluginPreference(view);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.launcher3.settings.-$$Lambda$DeveloperOptionsFragment$PluginPreference$rH1BM5-5IX2ZHR49mXASdoY5EjY
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return this.f$0.lambda$onBindViewHolder$1$DeveloperOptionsFragment$PluginPreference(view);
                }
            });
        }

        public /* synthetic */ void lambda$onBindViewHolder$0$DeveloperOptionsFragment$PluginPreference(View view) {
            ResolveInfo resolveInfoResolveActivity = view.getContext().getPackageManager().resolveActivity(new Intent(DeveloperOptionsFragment.ACTION_PLUGIN_SETTINGS).setPackage(this.mInfo.packageName), 0);
            if (resolveInfoResolveActivity != null) {
                view.getContext().startActivity(new Intent().setComponent(new ComponentName(resolveInfoResolveActivity.activityInfo.packageName, resolveInfoResolveActivity.activityInfo.name)));
            }
        }

        public /* synthetic */ boolean lambda$onBindViewHolder$1$DeveloperOptionsFragment$PluginPreference(View view) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", this.mInfo.packageName, null));
            getContext().startActivity(intent);
            return true;
        }
    }
}
