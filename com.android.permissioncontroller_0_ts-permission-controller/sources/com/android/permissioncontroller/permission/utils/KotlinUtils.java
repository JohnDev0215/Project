package com.android.permissioncontroller.permission.utils;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.LightPackageInfoLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import com.android.permissioncontroller.permission.model.livedatatypes.PermState;
import com.android.permissioncontroller.permission.service.LocationAccessCheck;
import com.android.permissioncontroller.permission.ui.handheld.SettingsWithLargeHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\"\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0007JD\u0010\u0015\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00170\u0016\"\u0004\b\u0000\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0010\u0010\u001b\u001a\f\u0012\u0004\u0012\u0002H\u0018\u0012\u0002\b\u00030\u001cJ\u001e\u0010\u001d\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J%\u0010\u001e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004J\u0018\u0010%\u001a\u0004\u0018\u00010\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004J\u0016\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004J\u0016\u0010'\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0004J\u0018\u0010)\u001a\u0004\u0018\u00010\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0004J\u0016\u0010*\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0004J(\u0010+\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0007J(\u0010.\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0007J,\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J0\u00100\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\b2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0002J\u001e\u00102\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u00103\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u0004J<\u00104\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u00020\b2\b\b\u0002\u00106\u001a\u00020\b2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0007J<\u00107\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u00020\b2\b\b\u0002\u00106\u001a\u00020\b2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0007J<\u00108\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J>\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0002J0\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020@H\u0002JN\u0010A\u001a\u00020B2\u0006\u0010\r\u001a\u00020C26\u0010D\u001a2\u0012\u0013\u0012\u00110F¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110F¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020\u00060E2\u0006\u0010K\u001a\u00020\bJ\u0014\u0010L\u001a\u00020\u0006*\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0002J\u0014\u0010N\u001a\u00020\u0006*\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/android/permissioncontroller/permission/utils/KotlinUtils;", "", "()V", "KILL_REASON_APP_OP_CHANGE", "", "PERMISSION_CONTROLLER_CHANGED_FLAG_MASK", "", "allowAppOp", "", "app", "Landroid/app/Application;", "perm", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "group", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "disallowAppOp", "getBadgedPackageIcon", "Landroid/graphics/drawable/Drawable;", "packageName", "user", "Landroid/os/UserHandle;", "getMapAndListDifferences", "Lkotlin/Pair;", "", "K", "newValues", "", "oldValues", "", "getPackageLabel", "getPackageUid", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;)Ljava/lang/Integer;", "getPermGroupDescription", "", "context", "Landroid/content/Context;", "groupName", "getPermGroupIcon", "getPermGroupLabel", "getPermInfoDescription", "permName", "getPermInfoIcon", "getPermInfoLabel", "grantBackgroundRuntimePermissions", "filterPermissions", "", "grantForegroundRuntimePermissions", "grantRuntimePermission", "grantRuntimePermissions", "grantBackground", "isROrAutoRevokeEnabled", "packageHasLaunchIntent", "revokeBackgroundRuntimePermissions", "userFixed", "oneTime", "revokeForegroundRuntimePermissions", "revokeRuntimePermission", "revokeRuntimePermissions", "revokeBackground", "setOpMode", "op", "uid", "mode", "manager", "Landroid/app/AppOpsManager;", "sortPreferenceGroup", "", "Landroidx/preference/PreferenceGroup;", "compare", "Lkotlin/Function2;", "Landroidx/preference/Preference;", "Lkotlin/ParameterName;", "name", "lhs", "rhs", "hasHeader", "clearFlag", "flagToSet", "setFlag", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class KotlinUtils {
    public static final KotlinUtils INSTANCE = new KotlinUtils();
    private static final String KILL_REASON_APP_OP_CHANGE = "Permission related app op changed";
    private static final int PERMISSION_CONTROLLER_CHANGED_FLAG_MASK = 196683;

    public final LightAppPermGroup grantBackgroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup) {
        return grantBackgroundRuntimePermissions$default(this, application, lightAppPermGroup, null, 4, null);
    }

    public final LightAppPermGroup grantForegroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup) {
        return grantForegroundRuntimePermissions$default(this, application, lightAppPermGroup, null, 4, null);
    }

    public final LightAppPermGroup revokeBackgroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup) {
        return revokeBackgroundRuntimePermissions$default(this, application, lightAppPermGroup, false, false, null, 28, null);
    }

    public final LightAppPermGroup revokeBackgroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup, boolean z) {
        return revokeBackgroundRuntimePermissions$default(this, application, lightAppPermGroup, z, false, null, 24, null);
    }

    public final LightAppPermGroup revokeBackgroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup, boolean z, boolean z2) {
        return revokeBackgroundRuntimePermissions$default(this, application, lightAppPermGroup, z, z2, null, 16, null);
    }

    public final LightAppPermGroup revokeForegroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup) {
        return revokeForegroundRuntimePermissions$default(this, application, lightAppPermGroup, false, false, null, 28, null);
    }

    public final LightAppPermGroup revokeForegroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup, boolean z) {
        return revokeForegroundRuntimePermissions$default(this, application, lightAppPermGroup, z, false, null, 24, null);
    }

    public final LightAppPermGroup revokeForegroundRuntimePermissions(Application application, LightAppPermGroup lightAppPermGroup, boolean z, boolean z2) {
        return revokeForegroundRuntimePermissions$default(this, application, lightAppPermGroup, z, z2, null, 16, null);
    }

    private KotlinUtils() {
    }

    public final <K> Pair<Set<K>, Set<K>> getMapAndListDifferences(Collection<? extends K> newValues, Map<K, ?> oldValues) {
        Intrinsics.checkParameterIsNotNull(newValues, "newValues");
        Intrinsics.checkParameterIsNotNull(oldValues, "oldValues");
        Set mapHas = CollectionsKt.toMutableSet(oldValues.keySet());
        Set listHas = CollectionsKt.toMutableSet(newValues);
        for (Object newVal : newValues) {
            if (oldValues.containsKey(newVal)) {
                mapHas.remove(newVal);
                listHas.remove(newVal);
            }
        }
        return TuplesKt.to(listHas, mapHas);
    }

    public final void sortPreferenceGroup(PreferenceGroup group, final Function2<? super Preference, ? super Preference, Integer> compare, boolean hasHeader) {
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(compare, "compare");
        List preferences = new ArrayList();
        int preferenceCount = group.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = group.getPreference(i);
            Intrinsics.checkExpressionValueIsNotNull(preference, "group.getPreference(i)");
            preferences.add(preference);
        }
        if (hasHeader) {
            CollectionsKt.sortWith(preferences, new Comparator<Preference>() { // from class: com.android.permissioncontroller.permission.utils.KotlinUtils.sortPreferenceGroup.1
                @Override // java.util.Comparator
                public final int compare(Preference lhs, Preference rhs) {
                    if (lhs instanceof SettingsWithLargeHeader.LargeHeaderPreference) {
                        return -1;
                    }
                    if (rhs instanceof SettingsWithLargeHeader.LargeHeaderPreference) {
                        return 1;
                    }
                    Function2 function2 = compare;
                    Intrinsics.checkExpressionValueIsNotNull(lhs, "lhs");
                    Intrinsics.checkExpressionValueIsNotNull(rhs, "rhs");
                    return ((Number) function2.invoke(lhs, rhs)).intValue();
                }
            });
        } else {
            CollectionsKt.sortWith(preferences, new Comparator() { // from class: com.android.permissioncontroller.permission.utils.KotlinUtilsKt$sam$java_util_Comparator$0
                @Override // java.util.Comparator
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    Object objInvoke = compare.invoke(obj, obj2);
                    Intrinsics.checkExpressionValueIsNotNull(objInvoke, "invoke(...)");
                    return ((Number) objInvoke).intValue();
                }
            });
        }
        int size = preferences.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Preference) preferences.get(i2)).setOrder(i2);
        }
    }

    public final Drawable getPermGroupIcon(Context context, String groupName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        PackageItemInfo groupInfo = Utils.getGroupInfo(groupName, context);
        Drawable icon = (Drawable) null;
        if (groupInfo != null && groupInfo.icon != 0) {
            icon = Utils.loadDrawable(context.getPackageManager(), groupInfo.packageName, groupInfo.icon);
        }
        if (icon == null) {
            icon = context.getDrawable(R.drawable.ic_perm_device_info);
        }
        return Utils.applyTint(context, icon, android.R.attr.colorControlNormal);
    }

    public final CharSequence getPermGroupLabel(Context context, String groupName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        PackageItemInfo groupInfo = Utils.getGroupInfo(groupName, context);
        if (groupInfo == null) {
            return groupName;
        }
        Intrinsics.checkExpressionValueIsNotNull(groupInfo, "Utils.getGroupInfo(group…text) ?: return groupName");
        CharSequence charSequenceLoadSafeLabel = groupInfo.loadSafeLabel(context.getPackageManager(), 0.0f, 5);
        Intrinsics.checkExpressionValueIsNotNull(charSequenceLoadSafeLabel, "groupInfo.loadSafeLabel(…ls.SAFE_STRING_FLAG_TRIM)");
        return charSequenceLoadSafeLabel;
    }

    public final CharSequence getPermGroupDescription(Context context, String groupName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        PackageItemInfo groupInfo = Utils.getGroupInfo(groupName, context);
        if (groupInfo instanceof PermissionGroupInfo) {
            String strLoadDescription = ((PermissionGroupInfo) groupInfo).loadDescription(context.getPackageManager());
            if (strLoadDescription == null) {
                strLoadDescription = groupName;
            }
            CharSequence description = strLoadDescription;
            return description;
        }
        if (groupInfo instanceof PermissionInfo) {
            String strLoadDescription2 = ((PermissionInfo) groupInfo).loadDescription(context.getPackageManager());
            if (strLoadDescription2 == null) {
                strLoadDescription2 = groupName;
            }
            CharSequence description2 = strLoadDescription2;
            return description2;
        }
        return description;
    }

    public final CharSequence getPermInfoLabel(Context context, String permName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permName, "permName");
        try {
            CharSequence charSequenceLoadSafeLabel = context.getPackageManager().getPermissionInfo(permName, 0).loadSafeLabel(context.getPackageManager(), 20000, 1);
            Intrinsics.checkExpressionValueIsNotNull(charSequenceLoadSafeLabel, "context.packageManager.g…ls.SAFE_STRING_FLAG_TRIM)");
            return charSequenceLoadSafeLabel;
        } catch (PackageManager.NameNotFoundException e) {
            return permName;
        }
    }

    public final Drawable getPermInfoIcon(Context context, String permName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permName, "permName");
        try {
            PermissionInfo permInfo = context.getPackageManager().getPermissionInfo(permName, 0);
            Drawable icon = (Drawable) null;
            if (permInfo.icon != 0) {
                icon = Utils.applyTint(context, permInfo.loadUnbadgedIcon(context.getPackageManager()), android.R.attr.colorControlNormal);
            }
            if (icon != null) {
                return icon;
            }
            String groupName = Utils.getGroupOfPermission(permInfo);
            if (groupName == null) {
                groupName = permInfo.name;
            }
            Intrinsics.checkExpressionValueIsNotNull(groupName, "groupName");
            return getPermGroupIcon(context, groupName);
        } catch (PackageManager.NameNotFoundException e) {
            return Utils.applyTint(context, context.getDrawable(R.drawable.ic_perm_device_info), android.R.attr.colorControlNormal);
        }
    }

    public final CharSequence getPermInfoDescription(Context context, String permName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permName, "permName");
        try {
            PermissionInfo permInfo = context.getPackageManager().getPermissionInfo(permName, 0);
            CharSequence charSequenceLoadDescription = permInfo.loadDescription(context.getPackageManager());
            if (charSequenceLoadDescription == null) {
            }
            return charSequenceLoadDescription;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public final Drawable getBadgedPackageIcon(Application app, String packageName, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        try {
            Context userContext = Utils.getUserContext(app, user);
            Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
            ApplicationInfo appInfo = userContext.getPackageManager().getApplicationInfo(packageName, 0);
            return Utils.getBadgedIcon(app, appInfo);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public final String getPackageLabel(Application app, String packageName, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        try {
            Context userContext = Utils.getUserContext(app, user);
            Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
            ApplicationInfo appInfo = userContext.getPackageManager().getApplicationInfo(packageName, 0);
            String fullAppLabel = Utils.getFullAppLabel(appInfo, app);
            Intrinsics.checkExpressionValueIsNotNull(fullAppLabel, "Utils.getFullAppLabel(appInfo, app)");
            return fullAppLabel;
        } catch (PackageManager.NameNotFoundException e) {
            return packageName;
        }
    }

    public final Integer getPackageUid(Application app, String packageName, UserHandle user) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        LightPackageInfoLiveData liveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, packageName, user);
        LightPackageInfo value = liveData.getValue();
        Integer liveDataUid = value != null ? Integer.valueOf(value.getUid()) : null;
        if (liveDataUid != null && liveData.hasActiveObservers()) {
            return liveDataUid;
        }
        Context userContext = Utils.getUserContext(app, user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        try {
            ApplicationInfo appInfo = userContext.getPackageManager().getApplicationInfo(packageName, 0);
            return Integer.valueOf(appInfo.uid);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public final boolean isROrAutoRevokeEnabled(Application app, String packageName, UserHandle user) throws PackageManager.NameNotFoundException {
        Pair pair;
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Context userContext = Utils.getUserContext(app, user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        LightPackageInfo liveDataValue = ((LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, packageName, user)).getValue();
        if (liveDataValue == null) {
            ApplicationInfo appInfo = userContext.getPackageManager().getApplicationInfo(packageName, 0);
            pair = TuplesKt.to(Integer.valueOf(appInfo.targetSdkVersion), Integer.valueOf(appInfo.uid));
        } else {
            pair = TuplesKt.to(Integer.valueOf(liveDataValue.getTargetSdkVersion()), Integer.valueOf(liveDataValue.getUid()));
        }
        int targetSdk = ((Number) pair.component1()).intValue();
        int uid = ((Number) pair.component2()).intValue();
        if (targetSdk > 29) {
            return true;
        }
        Object systemService = app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        AppOpsManager opsManager = (AppOpsManager) systemService;
        return opsManager.unsafeCheckOpNoThrow("android:auto_revoke_permissions_if_unused", uid, packageName) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightAppPermGroup grantForegroundRuntimePermissions$default(KotlinUtils kotlinUtils, Application application, LightAppPermGroup lightAppPermGroup, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet());
        }
        return kotlinUtils.grantForegroundRuntimePermissions(application, lightAppPermGroup, list);
    }

    public final LightAppPermGroup grantForegroundRuntimePermissions(Application app, LightAppPermGroup group, List<String> filterPermissions) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(filterPermissions, "filterPermissions");
        return grantRuntimePermissions(app, group, false, filterPermissions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightAppPermGroup grantBackgroundRuntimePermissions$default(KotlinUtils kotlinUtils, Application application, LightAppPermGroup lightAppPermGroup, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet());
        }
        return kotlinUtils.grantBackgroundRuntimePermissions(application, lightAppPermGroup, list);
    }

    public final LightAppPermGroup grantBackgroundRuntimePermissions(Application app, LightAppPermGroup group, List<String> filterPermissions) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(filterPermissions, "filterPermissions");
        return grantRuntimePermissions(app, group, true, filterPermissions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ LightAppPermGroup grantRuntimePermissions$default(KotlinUtils kotlinUtils, Application application, LightAppPermGroup lightAppPermGroup, boolean z, List list, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet());
        }
        return kotlinUtils.grantRuntimePermissions(application, lightAppPermGroup, z, list);
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0012 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup grantRuntimePermissions(android.app.Application r10, com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup r11, boolean r12, java.util.List<java.lang.String> r13) {
        /*
            r9 = this;
            java.util.Map r0 = r11.getPermissions()
            java.util.Map r0 = kotlin.collections.MapsKt.toMutableMap(r0)
            r1 = 0
            java.util.Iterator r2 = r13.iterator()
            r7 = r1
        Le:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L55
            java.lang.Object r1 = r2.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Map r3 = r11.getPermissions()
            java.lang.Object r3 = r3.get(r1)
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermission r3 = (com.android.permissioncontroller.permission.model.livedatatypes.LightPermission) r3
            if (r3 == 0) goto L54
            java.util.List r4 = r11.getBackgroundPermNames()
            boolean r4 = r4.contains(r1)
            if (r4 != r12) goto L54
            kotlin.Pair r5 = r9.grantRuntimePermission(r10, r3, r11)
            java.lang.Object r6 = r5.component1()
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermission r6 = (com.android.permissioncontroller.permission.model.livedatatypes.LightPermission) r6
            java.lang.Object r5 = r5.component2()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            java.lang.String r8 = r6.getName()
            r0.put(r8, r6)
            if (r7 != 0) goto L52
            if (r5 == 0) goto L50
            goto L52
        L50:
            r8 = 0
            goto L53
        L52:
            r8 = 1
        L53:
            r7 = r8
        L54:
            goto Le
        L55:
            if (r7 == 0) goto L77
            java.lang.Class<android.app.ActivityManager> r1 = android.app.ActivityManager.class
            java.lang.Object r1 = r10.getSystemService(r1)
            if (r1 == 0) goto L6f
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r2 = r11.getPackageInfo()
            int r2 = r2.getUid()
            java.lang.String r3 = "Permission related app op changed"
            r1.killUid(r2, r3)
            goto L77
        L6f:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type android.app.ActivityManager"
            r1.<init>(r2)
            throw r1
        L77:
            com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup r8 = new com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup
            com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r2 = r11.getPackageInfo()
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermGroupInfo r3 = r11.getPermGroupInfo()
            boolean r5 = r11.getHasInstallToRuntimeSplit()
            java.lang.Boolean r6 = r11.getSpecialLocationGrant()
            r1 = r8
            r4 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.KotlinUtils.grantRuntimePermissions(android.app.Application, com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup, boolean, java.util.List):com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup");
    }

    private final Pair<LightPermission, Boolean> grantRuntimePermission(Application app, LightPermission perm, LightAppPermGroup group) {
        boolean isGranted;
        boolean shouldKill;
        UserHandle user = UserHandle.getUserHandleForUid(group.getPackageInfo().getUid());
        boolean z = false;
        boolean supportsRuntime = group.getPackageInfo().getTargetSdkVersion() >= 23;
        boolean isGrantingAllowed = (!group.getPackageInfo().isInstantApp() || perm.getIsInstantPerm()) && (supportsRuntime || !perm.getIsRuntimeOnly());
        if (!isGrantingAllowed || perm.getIsSystemFixed()) {
            return TuplesKt.to(perm, false);
        }
        int newFlags = perm.getFlags();
        boolean isGranted2 = perm.isGrantedIncludingAppOp();
        boolean shouldKill2 = false;
        if (perm.isGrantedIncludingAppOp()) {
            isGranted = isGranted2;
            shouldKill = false;
        } else {
            boolean affectsAppOp = AppOpsManager.permissionToOp(perm.getName()) != null || perm.getIsBackgroundPermission();
            if (supportsRuntime) {
                app.getPackageManager().grantRuntimePermission(group.getPackageInfo().getPackageName(), perm.getName(), user);
                isGranted2 = true;
            } else if (affectsAppOp) {
                shouldKill2 = true;
                isGranted2 = true;
            }
            newFlags = clearFlag(newFlags, 8);
            if (affectsAppOp) {
                allowAppOp(app, perm, group);
            }
            isGranted = isGranted2;
            shouldKill = shouldKill2;
        }
        int newFlags2 = clearFlag(clearFlag(setFlag(clearFlag(clearFlag(newFlags, 64), 2), 1), 65536), 131072);
        if (!perm.isGrantedIncludingAppOp() && isGranted) {
            boolean triggerLocationAccessCheck = false;
            if (Intrinsics.areEqual(perm.getName(), "android.permission.ACCESS_FINE_LOCATION")) {
                LightPermission bgPerm = group.getPermissions().get(perm.getBackgroundPermission());
                if (bgPerm != null && bgPerm.isGrantedIncludingAppOp()) {
                    z = true;
                }
                triggerLocationAccessCheck = z;
            } else if (Intrinsics.areEqual(perm.getName(), "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                LightPermission fgPerm = group.getPermissions().get("android.permission.ACCESS_FINE_LOCATION");
                if (fgPerm != null && fgPerm.isGrantedIncludingAppOp()) {
                    z = true;
                }
                triggerLocationAccessCheck = z;
            }
            if (triggerLocationAccessCheck) {
                new LocationAccessCheck(app, null).checkLocationAccessSoon();
            }
        }
        if (perm.getFlags() != newFlags2) {
            app.getPackageManager().updatePermissionFlags(perm.getName(), group.getPackageInfo().getPackageName(), PERMISSION_CONTROLLER_CHANGED_FLAG_MASK, newFlags2, user);
        }
        PermState newState = new PermState(newFlags2, isGranted);
        return TuplesKt.to(new LightPermission(perm.getPkgInfo(), perm.getPermInfo(), newState, perm.getForegroundPerms()), Boolean.valueOf(shouldKill));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightAppPermGroup revokeForegroundRuntimePermissions$default(KotlinUtils kotlinUtils, Application application, LightAppPermGroup lightAppPermGroup, boolean z, boolean z2, List list, int i, Object obj) {
        return kotlinUtils.revokeForegroundRuntimePermissions(application, lightAppPermGroup, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet()) : list);
    }

    public final LightAppPermGroup revokeForegroundRuntimePermissions(Application app, LightAppPermGroup group, boolean userFixed, boolean oneTime, List<String> filterPermissions) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(filterPermissions, "filterPermissions");
        return revokeRuntimePermissions(app, group, false, userFixed, oneTime, filterPermissions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightAppPermGroup revokeBackgroundRuntimePermissions$default(KotlinUtils kotlinUtils, Application application, LightAppPermGroup lightAppPermGroup, boolean z, boolean z2, List list, int i, Object obj) {
        return kotlinUtils.revokeBackgroundRuntimePermissions(application, lightAppPermGroup, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? CollectionsKt.toList(lightAppPermGroup.getPermissions().keySet()) : list);
    }

    public final LightAppPermGroup revokeBackgroundRuntimePermissions(Application app, LightAppPermGroup group, boolean userFixed, boolean oneTime, List<String> filterPermissions) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(filterPermissions, "filterPermissions");
        return revokeRuntimePermissions(app, group, true, userFixed, oneTime, filterPermissions);
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0012 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup revokeRuntimePermissions(android.app.Application r16, com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup r17, boolean r18, boolean r19, boolean r20, java.util.List<java.lang.String> r21) {
        /*
            r15 = this;
            java.util.Map r0 = r17.getPermissions()
            java.util.Map r0 = kotlin.collections.MapsKt.toMutableMap(r0)
            r1 = 0
            java.util.Iterator r2 = r21.iterator()
            r7 = r1
        Le:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L65
            java.lang.Object r1 = r2.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Map r3 = r17.getPermissions()
            java.lang.Object r3 = r3.get(r1)
            r10 = r3
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermission r10 = (com.android.permissioncontroller.permission.model.livedatatypes.LightPermission) r10
            if (r10 == 0) goto L62
            java.util.List r3 = r17.getBackgroundPermNames()
            boolean r3 = r3.contains(r1)
            r14 = r18
            if (r3 != r14) goto L64
        L34:
            r8 = r15
            r9 = r16
            r11 = r19
            r12 = r20
            r13 = r17
            kotlin.Pair r4 = r8.revokeRuntimePermission(r9, r10, r11, r12, r13)
            java.lang.Object r5 = r4.component1()
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermission r5 = (com.android.permissioncontroller.permission.model.livedatatypes.LightPermission) r5
            java.lang.Object r4 = r4.component2()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            java.lang.String r6 = r5.getName()
            r0.put(r6, r5)
            if (r7 != 0) goto L5f
            if (r4 == 0) goto L5d
            goto L5f
        L5d:
            r6 = 0
            goto L60
        L5f:
            r6 = 1
        L60:
            r7 = r6
            goto L64
        L62:
            r14 = r18
        L64:
            goto Le
        L65:
            r14 = r18
            if (r7 == 0) goto L8b
            java.lang.Class<android.app.ActivityManager> r1 = android.app.ActivityManager.class
            r8 = r16
            java.lang.Object r1 = r8.getSystemService(r1)
            if (r1 == 0) goto L83
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r2 = r17.getPackageInfo()
            int r2 = r2.getUid()
            java.lang.String r3 = "Permission related app op changed"
            r1.killUid(r2, r3)
            goto L8d
        L83:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type android.app.ActivityManager"
            r1.<init>(r2)
            throw r1
        L8b:
            r8 = r16
        L8d:
            com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup r9 = new com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup
            com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r2 = r17.getPackageInfo()
            com.android.permissioncontroller.permission.model.livedatatypes.LightPermGroupInfo r3 = r17.getPermGroupInfo()
            boolean r5 = r17.getHasInstallToRuntimeSplit()
            java.lang.Boolean r6 = r17.getSpecialLocationGrant()
            r1 = r9
            r4 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.KotlinUtils.revokeRuntimePermissions(android.app.Application, com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup, boolean, boolean, boolean, java.util.List):com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup");
    }

    private final Pair<LightPermission, Boolean> revokeRuntimePermission(Application app, LightPermission perm, boolean userFixed, boolean oneTime, LightAppPermGroup group) {
        boolean isGranted;
        boolean shouldKill;
        int iClearFlag;
        int newFlags;
        int newFlags2;
        if (perm.getIsSystemFixed()) {
            return TuplesKt.to(perm, false);
        }
        boolean supportsRuntime = group.getPackageInfo().getTargetSdkVersion() >= 23;
        UserHandle user = UserHandle.getUserHandleForUid(group.getPackageInfo().getUid());
        int newFlags3 = perm.getFlags();
        boolean isGranted2 = perm.isGrantedIncludingAppOp();
        boolean shouldKill2 = false;
        boolean affectsAppOp = AppOpsManager.permissionToOp(perm.getName()) != null || perm.getIsBackgroundPermission();
        if (!perm.isGrantedIncludingAppOp()) {
            isGranted = isGranted2;
            shouldKill = false;
        } else {
            if (supportsRuntime) {
                app.getPackageManager().revokeRuntimePermission(group.getPackageInfo().getPackageName(), perm.getName(), user);
                isGranted2 = false;
            } else if (affectsAppOp) {
                shouldKill2 = true;
                newFlags3 = setFlag(newFlags3, 8);
                isGranted2 = false;
            }
            if (affectsAppOp) {
                disallowAppOp(app, perm, group);
            }
            isGranted = isGranted2;
            shouldKill = shouldKill2;
        }
        if (userFixed) {
            iClearFlag = setFlag(newFlags3, 2);
        } else {
            iClearFlag = clearFlag(newFlags3, 2);
        }
        int newFlags4 = iClearFlag;
        if (oneTime) {
            newFlags = clearFlag(newFlags4, 1);
        } else {
            newFlags = setFlag(newFlags4, 1);
        }
        if (oneTime) {
            newFlags2 = setFlag(newFlags, 65536);
        } else {
            newFlags2 = clearFlag(newFlags, 65536);
        }
        int newFlags5 = clearFlag(newFlags2, 131072);
        if (perm.getFlags() != newFlags5) {
            app.getPackageManager().updatePermissionFlags(perm.getName(), group.getPackageInfo().getPackageName(), PERMISSION_CONTROLLER_CHANGED_FLAG_MASK, newFlags5, user);
        }
        PermState newState = new PermState(newFlags5, isGranted);
        return TuplesKt.to(new LightPermission(perm.getPkgInfo(), perm.getPermInfo(), newState, perm.getForegroundPerms()), Boolean.valueOf(shouldKill));
    }

    private final int setFlag(int $this$setFlag, int flagToSet) {
        return $this$setFlag | flagToSet;
    }

    private final int clearFlag(int $this$clearFlag, int flagToSet) {
        return (~flagToSet) & $this$clearFlag;
    }

    private final boolean allowAppOp(Application app, LightPermission perm, LightAppPermGroup group) {
        boolean opMode;
        String packageName = group.getPackageInfo().getPackageName();
        int uid = group.getPackageInfo().getUid();
        Object systemService = app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.AppOpsManager");
        }
        AppOpsManager appOpsManager = (AppOpsManager) systemService;
        boolean wasChanged = false;
        if (perm.getIsBackgroundPermission() && perm.getForegroundPerms() != null) {
            for (String foregroundPermName : perm.getForegroundPerms()) {
                LightPermission fgPerm = group.getPermissions().get(foregroundPermName);
                String appOpName = AppOpsManager.permissionToOp(foregroundPermName);
                if (appOpName != null && fgPerm != null && fgPerm.isGrantedIncludingAppOp()) {
                    wasChanged = wasChanged || setOpMode(appOpName, uid, packageName, 0, appOpsManager);
                }
            }
            return wasChanged;
        }
        String appOpName2 = AppOpsManager.permissionToOp(perm.getName());
        if (appOpName2 == null) {
            return false;
        }
        if (perm.getBackgroundPermission() != null) {
            if (group.getPermissions().containsKey(perm.getBackgroundPermission())) {
                LightPermission bgPerm = group.getPermissions().get(perm.getBackgroundPermission());
                int mode = (bgPerm == null || !bgPerm.isGrantedIncludingAppOp()) ? 4 : 0;
                opMode = setOpMode(appOpName2, uid, packageName, mode, appOpsManager);
            } else {
                opMode = setOpMode(appOpName2, uid, packageName, 4, appOpsManager);
            }
            boolean wasChanged2 = opMode;
            return wasChanged2;
        }
        boolean wasChanged3 = setOpMode(appOpName2, uid, packageName, 0, appOpsManager);
        return wasChanged3;
    }

    private final boolean disallowAppOp(Application app, LightPermission perm, LightAppPermGroup group) {
        String packageName = group.getPackageInfo().getPackageName();
        int uid = group.getPackageInfo().getUid();
        Object systemService = app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.AppOpsManager");
        }
        AppOpsManager appOpsManager = (AppOpsManager) systemService;
        boolean wasChanged = false;
        if (perm.getIsBackgroundPermission() && perm.getForegroundPerms() != null) {
            for (String foregroundPermName : perm.getForegroundPerms()) {
                LightPermission fgPerm = group.getPermissions().get(foregroundPermName);
                if (fgPerm != null && fgPerm.isGrantedIncludingAppOp()) {
                    String appOpName = AppOpsManager.permissionToOp(foregroundPermName);
                    if (appOpName == null) {
                        return false;
                    }
                    wasChanged = wasChanged || setOpMode(appOpName, uid, packageName, 4, appOpsManager);
                }
            }
            return wasChanged;
        }
        String appOpName2 = AppOpsManager.permissionToOp(perm.getName());
        if (appOpName2 == null) {
            return false;
        }
        boolean wasChanged2 = setOpMode(appOpName2, uid, packageName, 1, appOpsManager);
        return wasChanged2;
    }

    private final boolean setOpMode(String op, int uid, String packageName, int mode, AppOpsManager manager) {
        int currentMode = manager.unsafeCheckOpRaw(op, uid, packageName);
        if (currentMode == mode) {
            return false;
        }
        manager.setUidMode(op, uid, mode);
        return true;
    }

    public final boolean packageHasLaunchIntent(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intent intentToResolve = new Intent("android.intent.action.MAIN");
        intentToResolve.addCategory("android.intent.category.INFO");
        intentToResolve.setPackage(packageName);
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intentToResolve, 786432);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
            intentToResolve.removeCategory("android.intent.category.INFO");
            intentToResolve.addCategory("android.intent.category.LAUNCHER");
            intentToResolve.setPackage(packageName);
            listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intentToResolve, 786432);
        }
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }
}
