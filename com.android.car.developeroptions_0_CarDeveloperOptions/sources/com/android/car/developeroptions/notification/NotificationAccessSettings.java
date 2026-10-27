package com.android.car.developeroptions.notification;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.notification.NotificationAccessSettings;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationAccessSettings extends ManagedServiceSettings {
    private static final ManagedServiceSettings.Config CONFIG;
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    private NotificationManager mNm;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 179;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.notification_access_settings;
    }

    static {
        ManagedServiceSettings.Config.Builder builder = new ManagedServiceSettings.Config.Builder();
        builder.setTag("NotificationAccessSettings");
        builder.setSetting("enabled_notification_listeners");
        builder.setIntentAction("android.service.notification.NotificationListenerService");
        builder.setPermission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE");
        builder.setNoun("notification listener");
        builder.setWarningDialogTitle(R.string.notification_listener_security_warning_title);
        builder.setWarningDialogSummary(R.string.notification_listener_security_warning_summary);
        builder.setEmptyText(R.string.no_notification_listeners);
        CONFIG = builder.build();
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.NotificationAccessSettings.1
            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
                ArrayList arrayList = new ArrayList();
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.notification_access_settings;
                arrayList.add(searchIndexableResource);
                return arrayList;
            }
        };
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (UserManager.get(context).isManagedProfile()) {
            Toast.makeText(context, R.string.notification_settings_work_profile, 0).show();
            finish();
        }
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mNm = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    protected ManagedServiceSettings.Config getConfig() {
        return CONFIG;
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    protected boolean setEnabled(ComponentName componentName, String str, boolean z) {
        logSpecialPermissionChange(z, componentName.getPackageName());
        if (!z) {
            if (!isServiceEnabled(componentName)) {
                return true;
            }
            FriendlyWarningDialogFragment friendlyWarningDialogFragment = new FriendlyWarningDialogFragment();
            friendlyWarningDialogFragment.setServiceInfo(componentName, str, this);
            friendlyWarningDialogFragment.show(getFragmentManager(), "friendlydialog");
            return false;
        }
        if (isServiceEnabled(componentName)) {
            return true;
        }
        ManagedServiceSettings.ScaryWarningDialogFragment scaryWarningDialogFragment = new ManagedServiceSettings.ScaryWarningDialogFragment();
        scaryWarningDialogFragment.setServiceInfo(componentName, str, this);
        scaryWarningDialogFragment.show(getFragmentManager(), "dialog");
        return false;
    }

    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    protected boolean isServiceEnabled(ComponentName componentName) {
        return this.mNm.isNotificationListenerAccessGranted(componentName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.developeroptions.utils.ManagedServiceSettings
    public void enable(ComponentName componentName) {
        this.mNm.setNotificationListenerAccessGranted(componentName, true);
    }

    void logSpecialPermissionChange(boolean z, String str) {
        FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider().action(getContext(), z ? 776 : 777, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void disable(final NotificationAccessSettings notificationAccessSettings, final ComponentName componentName) {
        notificationAccessSettings.mNm.setNotificationListenerAccessGranted(componentName, false);
        AsyncTask.execute(new Runnable() { // from class: com.android.car.developeroptions.notification.-$$Lambda$NotificationAccessSettings$nkpuU7gBVDjOlc_KYCMI4eT4OqY
            @Override // java.lang.Runnable
            public final void run() {
                NotificationAccessSettings.lambda$disable$0(this.f$0, componentName);
            }
        });
    }

    static /* synthetic */ void lambda$disable$0(NotificationAccessSettings notificationAccessSettings, ComponentName componentName) {
        if (notificationAccessSettings.mNm.isNotificationPolicyAccessGrantedForPackage(componentName.getPackageName())) {
            return;
        }
        notificationAccessSettings.mNm.removeAutomaticZenRules(componentName.getPackageName());
    }

    public static class FriendlyWarningDialogFragment extends InstrumentedDialogFragment {
        static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialogInterface, int i) {
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 552;
        }

        public FriendlyWarningDialogFragment setServiceInfo(ComponentName componentName, String str, Fragment fragment) {
            Bundle bundle = new Bundle();
            bundle.putString("c", componentName.flattenToString());
            bundle.putString("l", str);
            setArguments(bundle);
            setTargetFragment(fragment, 0);
            return this;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Bundle arguments = getArguments();
            String string = arguments.getString("l");
            final ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(arguments.getString("c"));
            final NotificationAccessSettings notificationAccessSettings = (NotificationAccessSettings) getTargetFragment();
            String string2 = getResources().getString(R.string.notification_listener_disable_warning_summary, string);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(string2);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.notification_listener_disable_warning_confirm, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$NotificationAccessSettings$FriendlyWarningDialogFragment$bsnJfqEsJfmtAQN0_XOoIptMa64
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NotificationAccessSettings.disable(notificationAccessSettings, componentNameUnflattenFromString);
                }
            });
            builder.setNegativeButton(R.string.notification_listener_disable_warning_cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$NotificationAccessSettings$FriendlyWarningDialogFragment$IA_yN6g1x34hC8wSC5hi2lEhBj8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NotificationAccessSettings.FriendlyWarningDialogFragment.lambda$onCreateDialog$1(dialogInterface, i);
                }
            });
            return builder.create();
        }
    }
}
