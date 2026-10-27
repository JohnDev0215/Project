package com.android.systemui.util;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedLockUtils {
    public static EnforcedAdmin getProfileOrDeviceOwner(Context context, UserHandle user) {
        return getProfileOrDeviceOwner(context, null, user);
    }

    public static EnforcedAdmin getProfileOrDeviceOwner(Context context, String enforcedRestriction, UserHandle user) {
        DevicePolicyManager devicePolicyManager;
        ComponentName deviceOwnerComponentOnAnyUser;
        if (user == null || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        try {
            ComponentName profileOwner = ((DevicePolicyManager) context.createPackageContextAsUser(context.getPackageName(), 0, user).getSystemService(DevicePolicyManager.class)).getProfileOwner();
            if (profileOwner != null) {
                return new EnforcedAdmin(profileOwner, enforcedRestriction, user);
            }
            if (!Objects.equals(Integer.valueOf(devicePolicyManager.getDeviceOwnerUserId()), user) || (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == null) {
                return null;
            }
            return new EnforcedAdmin(deviceOwnerComponentOnAnyUser, enforcedRestriction, user);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void sendShowAdminSupportDetailsIntent(Context context, EnforcedAdmin admin) {
        Intent showAdminSupportDetailsIntent = getShowAdminSupportDetailsIntent(context, admin);
        int iMyUserId = UserHandle.myUserId();
        if (admin != null) {
            if (admin.user != null && isCurrentUserOrProfile(context, admin.user.getIdentifier())) {
                iMyUserId = admin.user.getIdentifier();
            }
            showAdminSupportDetailsIntent.putExtra("android.app.extra.RESTRICTION", admin.enforcedRestriction);
        }
        context.startActivityAsUser(showAdminSupportDetailsIntent, UserHandle.of(iMyUserId));
    }

    public static Intent getShowAdminSupportDetailsIntent(Context context, EnforcedAdmin admin) {
        Intent intent = new Intent("android.settings.SHOW_ADMIN_SUPPORT_DETAILS");
        if (admin != null) {
            if (admin.component != null) {
                intent.putExtra("android.app.extra.DEVICE_ADMIN", admin.component);
            }
            intent.putExtra("android.intent.extra.USER", admin.user);
        }
        return intent;
    }

    public static boolean isCurrentUserOrProfile(Context context, int userId) {
        return ((UserManager) context.getSystemService(UserManager.class)).getUserProfiles().contains(UserHandle.of(userId));
    }

    public static class EnforcedAdmin {
        public static final EnforcedAdmin MULTIPLE_ENFORCED_ADMIN = new EnforcedAdmin();
        public ComponentName component;
        public String enforcedRestriction;
        public UserHandle user;

        public static EnforcedAdmin createDefaultEnforcedAdminWithRestriction(String enforcedRestriction) {
            EnforcedAdmin enforcedAdmin = new EnforcedAdmin();
            enforcedAdmin.enforcedRestriction = enforcedRestriction;
            return enforcedAdmin;
        }

        public EnforcedAdmin(ComponentName component, UserHandle user) {
            this.component = null;
            this.enforcedRestriction = null;
            this.user = null;
            this.component = component;
            this.user = user;
        }

        public EnforcedAdmin(ComponentName component, String enforcedRestriction, UserHandle user) {
            this.component = null;
            this.enforcedRestriction = null;
            this.user = null;
            this.component = component;
            this.enforcedRestriction = enforcedRestriction;
            this.user = user;
        }

        public EnforcedAdmin(EnforcedAdmin other) {
            this.component = null;
            this.enforcedRestriction = null;
            this.user = null;
            if (other == null) {
                throw new IllegalArgumentException();
            }
            this.component = other.component;
            this.enforcedRestriction = other.enforcedRestriction;
            this.user = other.user;
        }

        public EnforcedAdmin() {
            this.component = null;
            this.enforcedRestriction = null;
            this.user = null;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EnforcedAdmin enforcedAdmin = (EnforcedAdmin) o;
            return Objects.equals(this.user, enforcedAdmin.user) && Objects.equals(this.component, enforcedAdmin.component) && Objects.equals(this.enforcedRestriction, enforcedAdmin.enforcedRestriction);
        }

        public int hashCode() {
            return Objects.hash(this.component, this.enforcedRestriction, this.user);
        }

        public String toString() {
            return "EnforcedAdmin{component=" + this.component + ", enforcedRestriction='" + this.enforcedRestriction + ", user=" + this.user + '}';
        }
    }
}
