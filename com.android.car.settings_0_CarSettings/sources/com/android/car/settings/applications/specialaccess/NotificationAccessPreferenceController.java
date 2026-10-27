package com.android.car.settings.applications.specialaccess;

import android.app.NotificationManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiSwitchPreference;
import com.android.settingslib.applications.ServiceListing;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class NotificationAccessPreferenceController extends PreferenceController<PreferenceGroup> {
    static final String GRANT_CONFIRM_DIALOG_TAG = "com.android.car.settings.applications.specialaccess.GrantNotificationAccessDialog";
    private static final String KEY_SERVICE = "service";
    private static final Logger LOG = new Logger((Class<?>) NotificationAccessPreferenceController.class);
    static final String REVOKE_CONFIRM_DIALOG_TAG = "com.android.car.settings.applications.specialaccess.RevokeNotificationAccessDialog";
    private final ServiceListing.Callback mCallback;
    private final ConfirmationDialogFragment.ConfirmListener mGrantConfirmListener;
    private final IconDrawableFactory mIconDrawableFactory;
    private final NotificationManager mNm;
    private final ConfirmationDialogFragment.ConfirmListener mRevokeConfirmListener;
    private final ServiceListing mServiceListing;

    public /* synthetic */ void lambda$new$0$NotificationAccessPreferenceController(Bundle arguments) {
        ComponentName service = (ComponentName) arguments.getParcelable("service");
        grantNotificationAccess(service);
    }

    public /* synthetic */ void lambda$new$1$NotificationAccessPreferenceController(Bundle arguments) {
        ComponentName service = (ComponentName) arguments.getParcelable("service");
        revokeNotificationAccess(service);
    }

    public NotificationAccessPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mCallback = new ServiceListing.Callback() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$NotificationAccessPreferenceController$vt048luuQBvdXpebH-qtFLjQglE
            @Override // com.android.settingslib.applications.ServiceListing.Callback
            public final void onServicesReloaded(List list) {
                this.f$0.onServicesReloaded(list);
            }
        };
        this.mGrantConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$NotificationAccessPreferenceController$CFGWAxEoN985isSZRdmBj9mtwL8
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$NotificationAccessPreferenceController(bundle);
            }
        };
        this.mRevokeConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$NotificationAccessPreferenceController$MtorihEeARaOGlb_nitMLcJ8sXI
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$1$NotificationAccessPreferenceController(bundle);
            }
        };
        this.mNm = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mServiceListing = new ServiceListing.Builder(context).setPermission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE").setIntentAction("android.service.notification.NotificationListenerService").setSetting("enabled_notification_listeners").setTag(NotificationAccessPreferenceController.class.getSimpleName()).setNoun("notification listener").build();
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment grantConfirmDialogFragment = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(GRANT_CONFIRM_DIALOG_TAG);
        ConfirmationDialogFragment.resetListeners(grantConfirmDialogFragment, this.mGrantConfirmListener, null, null);
        ConfirmationDialogFragment revokeConfirmDialogFragment = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(REVOKE_CONFIRM_DIALOG_TAG);
        ConfirmationDialogFragment.resetListeners(revokeConfirmDialogFragment, this.mRevokeConfirmListener, null, null);
        this.mServiceListing.addCallback(this.mCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mServiceListing.reload();
        this.mServiceListing.setListening(true);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mServiceListing.setListening(false);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mServiceListing.removeCallback(this.mCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServicesReloaded(List<ServiceInfo> services) {
        PackageManager packageManager = getContext().getPackageManager();
        services.sort(new PackageItemInfo.DisplayNameComparator(packageManager));
        getPreference().removeAll();
        for (ServiceInfo service : services) {
            final ComponentName cn = new ComponentName(service.packageName, service.name);
            CharSequence title = null;
            try {
                title = packageManager.getApplicationInfoAsUser(service.packageName, 0, UserHandle.myUserId()).loadLabel(packageManager);
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("can't find package name", e);
            }
            final String summary = service.loadLabel(packageManager).toString();
            SwitchPreference pref = new CarUiSwitchPreference(getContext());
            pref.setPersistent(false);
            pref.setIcon(this.mIconDrawableFactory.getBadgedIcon(service, service.applicationInfo, UserHandle.getUserId(service.applicationInfo.uid)));
            if (title != null && !title.equals(summary)) {
                pref.setTitle(title);
                pref.setSummary(summary);
            } else {
                pref.setTitle(summary);
            }
            pref.setKey(cn.flattenToString());
            pref.setChecked(isAccessGranted(cn));
            pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$NotificationAccessPreferenceController$r8edwYhsZqV3QINnnozgd_Gr9mQ
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return this.f$0.lambda$onServicesReloaded$2$NotificationAccessPreferenceController(cn, summary, preference, obj);
                }
            });
            getPreference().addPreference(pref);
        }
    }

    public /* synthetic */ boolean lambda$onServicesReloaded$2$NotificationAccessPreferenceController(ComponentName cn, String summary, Preference preference, Object newValue) {
        boolean enable = ((Boolean) newValue).booleanValue();
        return promptUserToConfirmChange(cn, summary, enable);
    }

    private boolean isAccessGranted(ComponentName service) {
        return this.mNm.isNotificationListenerAccessGranted(service);
    }

    private void grantNotificationAccess(ComponentName service) {
        this.mNm.setNotificationListenerAccessGranted(service, true);
    }

    private void revokeNotificationAccess(final ComponentName service) {
        this.mNm.setNotificationListenerAccessGranted(service, false);
        AsyncTask.execute(new Runnable() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$NotificationAccessPreferenceController$p3WHZeeKNhA5xmq2tbvqcHcFmkY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$revokeNotificationAccess$3$NotificationAccessPreferenceController(service);
            }
        });
    }

    public /* synthetic */ void lambda$revokeNotificationAccess$3$NotificationAccessPreferenceController(ComponentName service) {
        if (!this.mNm.isNotificationPolicyAccessGrantedForPackage(service.getPackageName())) {
            this.mNm.removeAutomaticZenRules(service.getPackageName());
        }
    }

    private boolean promptUserToConfirmChange(ComponentName service, String label, boolean grantAccess) {
        if (isAccessGranted(service) == grantAccess) {
            return true;
        }
        ConfirmationDialogFragment.Builder dialogFragment = grantAccess ? createConfirmGrantDialogFragment(label) : createConfirmRevokeDialogFragment(label);
        dialogFragment.addArgumentParcelable("service", service);
        getFragmentController().showDialog(dialogFragment.build(), grantAccess ? GRANT_CONFIRM_DIALOG_TAG : REVOKE_CONFIRM_DIALOG_TAG);
        return false;
    }

    private ConfirmationDialogFragment.Builder createConfirmGrantDialogFragment(String label) {
        String title = getContext().getResources().getString(R.string.notification_listener_security_warning_title, label);
        String summary = getContext().getResources().getString(R.string.notification_listener_security_warning_summary, label);
        return new ConfirmationDialogFragment.Builder(getContext()).setTitle(title).setMessage(summary).setPositiveButton(R.string.allow, this.mGrantConfirmListener).setNegativeButton(R.string.deny, (ConfirmationDialogFragment.RejectListener) null);
    }

    private ConfirmationDialogFragment.Builder createConfirmRevokeDialogFragment(String label) {
        String summary = getContext().getResources().getString(R.string.notification_listener_revoke_warning_summary, label);
        return new ConfirmationDialogFragment.Builder(getContext()).setMessage(summary).setPositiveButton(R.string.notification_listener_revoke_warning_confirm, this.mRevokeConfirmListener).setNegativeButton(R.string.notification_listener_revoke_warning_cancel, (ConfirmationDialogFragment.RejectListener) null);
    }
}
