package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public interface RoleBehavior {
    default void onRoleAdded(Role role, Context context) {
    }

    default boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        return true;
    }

    default List<String> getDefaultHolders(Role role, Context context) {
        return Collections.emptyList();
    }

    default String getFallbackHolder(Role role, Context context) {
        return null;
    }

    default boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return true;
    }

    default Intent getManageIntentAsUser(Role role, UserHandle user, Context context) {
        return null;
    }

    default void preparePreferenceAsUser(Role role, TwoTargetPreference preference, UserHandle user, Context context) {
    }

    default boolean isApplicationVisibleAsUser(Role role, ApplicationInfo applicationInfo, UserHandle user, Context context) {
        return true;
    }

    default void prepareApplicationPreferenceAsUser(Role role, Preference preference, ApplicationInfo applicationInfo, UserHandle user, Context context) {
    }

    default CharSequence getConfirmationMessage(Role role, String packageName, Context context) {
        return null;
    }

    default List<String> getQualifyingPackagesAsUser(Role role, UserHandle user, Context context) {
        return null;
    }

    default Boolean isPackageQualified(Role role, String packageName, Context context) {
        return null;
    }

    default void grant(Role role, String packageName, Context context) {
    }

    default void revoke(Role role, String packageName, Context context) {
    }

    default void onHolderSelectedAsUser(Role role, String packageName, UserHandle user, Context context) {
    }

    default void onHolderChangedAsUser(Role role, UserHandle user, Context context) {
    }
}
