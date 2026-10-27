package com.android.internal.accessibility.util;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.accessibility.common.ShortcutConstants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class AccessibilityUtils {
    private AccessibilityUtils() {
    }

    public static Set<ComponentName> getEnabledServicesFromSettings(Context context, int i) {
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, i);
        if (TextUtils.isEmpty(stringForUser)) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(ShortcutConstants.SERVICES_SEPARATOR);
        simpleStringSplitter.setString(stringForUser);
        Iterator<String> it = simpleStringSplitter.iterator();
        while (it.hasNext()) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(it.next());
            if (componentNameUnflattenFromString != null) {
                hashSet.add(componentNameUnflattenFromString);
            }
        }
        return hashSet;
    }

    public static void setAccessibilityServiceState(Context context, ComponentName componentName, boolean z) {
        setAccessibilityServiceState(context, componentName, z, UserHandle.myUserId());
    }

    public static void setAccessibilityServiceState(Context context, ComponentName componentName, boolean z, int i) {
        Set enabledServicesFromSettings = getEnabledServicesFromSettings(context, i);
        if (enabledServicesFromSettings.isEmpty()) {
            enabledServicesFromSettings = new ArraySet(1);
        }
        if (z) {
            enabledServicesFromSettings.add(componentName);
        } else {
            enabledServicesFromSettings.remove(componentName);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = enabledServicesFromSettings.iterator();
        while (it.hasNext()) {
            sb.append(((ComponentName) it.next()).flattenToString());
            sb.append(ShortcutConstants.SERVICES_SEPARATOR);
        }
        int length = sb.length();
        if (length > 0) {
            sb.deleteCharAt(length - 1);
        }
        Settings.Secure.putStringForUser(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, sb.toString(), i);
    }

    public static int getAccessibilityServiceFragmentType(AccessibilityServiceInfo accessibilityServiceInfo) {
        int i = accessibilityServiceInfo.getResolveInfo().serviceInfo.applicationInfo.targetSdkVersion;
        boolean z = (accessibilityServiceInfo.flags & 256) != 0;
        if (i <= 29) {
            return 0;
        }
        return z ? 1 : 2;
    }

    public static boolean isAccessibilityServiceEnabled(Context context, String str) {
        Iterator<AccessibilityServiceInfo> it = ((AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE)).getEnabledAccessibilityServiceList(-1).iterator();
        while (it.hasNext()) {
            if (it.next().getComponentName().flattenToString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserSetupCompleted(Context context) {
        return Settings.Secure.getIntForUser(context.getContentResolver(), Settings.Secure.USER_SETUP_COMPLETE, 0, -2) != 0;
    }
}
