package android.content.pm;

import android.annotation.SystemApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import com.android.internal.R;
import com.android.internal.util.UserIcons;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class CrossProfileApps {
    public static final String ACTION_CAN_INTERACT_ACROSS_PROFILES_CHANGED = "android.content.pm.action.CAN_INTERACT_ACROSS_PROFILES_CHANGED";
    private final Context mContext;
    private final Resources mResources;
    private final ICrossProfileApps mService;
    private final UserManager mUserManager;

    public CrossProfileApps(Context context, ICrossProfileApps iCrossProfileApps) {
        this.mContext = context;
        this.mService = iCrossProfileApps;
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mResources = context.getResources();
    }

    public void startMainActivity(ComponentName componentName, UserHandle userHandle) {
        try {
            this.mService.startActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), componentName, userHandle.getIdentifier(), true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startActivity(Intent intent, UserHandle userHandle, Activity activity) {
        startActivity(intent, userHandle, activity, null);
    }

    public void startActivity(Intent intent, UserHandle userHandle, Activity activity, Bundle bundle) {
        try {
            this.mService.startActivityAsUserByIntent(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), intent, userHandle.getIdentifier(), activity != null ? activity.getActivityToken() : null, bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void startActivity(ComponentName componentName, UserHandle userHandle) {
        try {
            this.mService.startActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), componentName, userHandle.getIdentifier(), false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<UserHandle> getTargetUserProfiles() {
        try {
            return this.mService.getTargetUserProfiles(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public CharSequence getProfileSwitchingLabel(UserHandle userHandle) {
        verifyCanAccessUser(userHandle);
        return this.mResources.getString(this.mUserManager.isManagedProfile(userHandle.getIdentifier()) ? R.string.managed_profile_label : R.string.user_owner_label);
    }

    public Drawable getProfileSwitchingIconDrawable(UserHandle userHandle) {
        verifyCanAccessUser(userHandle);
        if (this.mUserManager.isManagedProfile(userHandle.getIdentifier())) {
            return this.mResources.getDrawable(R.drawable.ic_corp_badge, null);
        }
        return UserIcons.getDefaultUserIcon(this.mResources, 0, true);
    }

    public boolean canRequestInteractAcrossProfiles() {
        try {
            return this.mService.canRequestInteractAcrossProfiles(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canInteractAcrossProfiles() {
        try {
            return this.mService.canInteractAcrossProfiles(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Intent createRequestInteractAcrossProfilesIntent() {
        if (!canRequestInteractAcrossProfiles()) {
            throw new SecurityException("The calling package can not request to interact across profiles.");
        }
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_MANAGE_CROSS_PROFILE_ACCESS);
        intent.setData(Uri.parse("package:" + this.mContext.getPackageName()));
        return intent;
    }

    public void setInteractAcrossProfilesAppOp(String str, int i) {
        try {
            this.mService.setInteractAcrossProfilesAppOp(str, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canConfigureInteractAcrossProfiles(String str) {
        try {
            return this.mService.canConfigureInteractAcrossProfiles(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean canUserAttemptToConfigureInteractAcrossProfiles(String str) {
        try {
            return this.mService.canUserAttemptToConfigureInteractAcrossProfiles(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void resetInteractAcrossProfilesAppOps(Collection<String> collection, final Set<String> set) {
        if (collection.isEmpty()) {
            return;
        }
        List<String> list = (List) collection.stream().filter(new Predicate() { // from class: android.content.pm.-$$Lambda$CrossProfileApps$q8A4D5J-ieuBm118fz0XxGYzHwQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CrossProfileApps.lambda$resetInteractAcrossProfilesAppOps$0(set, (String) obj);
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return;
        }
        try {
            this.mService.resetInteractAcrossProfilesAppOps(list);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    static /* synthetic */ boolean lambda$resetInteractAcrossProfilesAppOps$0(Set set, String str) {
        return !set.contains(str);
    }

    public void clearInteractAcrossProfilesAppOps() {
        try {
            this.mService.clearInteractAcrossProfilesAppOps();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void verifyCanAccessUser(UserHandle userHandle) {
        if (getTargetUserProfiles().contains(userHandle)) {
            return;
        }
        throw new SecurityException("Not allowed to access " + userHandle);
    }
}
