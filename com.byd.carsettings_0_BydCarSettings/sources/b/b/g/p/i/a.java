package b.b.g.p.i;

import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: AccessibilityUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TextUtils.SimpleStringSplitter f1407a = new TextUtils.SimpleStringSplitter(':');

    public static Set<ComponentName> a(Context context) {
        return b(context, UserHandle.myUserId());
    }

    public static Set<ComponentName> b(Context context, int i) {
        String stringForUser = Settings.Secure.getStringForUser(context.getContentResolver(), "enabled_accessibility_services", i);
        if (stringForUser == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        TextUtils.SimpleStringSplitter simpleStringSplitter = f1407a;
        simpleStringSplitter.setString(stringForUser);
        while (simpleStringSplitter.hasNext()) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(simpleStringSplitter.next());
            if (componentNameUnflattenFromString != null) {
                hashSet.add(componentNameUnflattenFromString);
            }
        }
        return hashSet;
    }

    public static void c(Context context, ComponentName componentName, boolean z) {
        d(context, componentName, z, UserHandle.myUserId());
    }

    public static void d(Context context, ComponentName componentName, boolean z, int i) {
        Set setB = b(context, i);
        if (setB.isEmpty()) {
            setB = new ArraySet(1);
        }
        if (z) {
            setB.add(componentName);
        } else {
            setB.remove(componentName);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = setB.iterator();
        while (it.hasNext()) {
            sb.append(((ComponentName) it.next()).flattenToString());
            sb.append(':');
        }
        int length = sb.length();
        if (length > 0) {
            sb.deleteCharAt(length - 1);
        }
        Settings.Secure.putStringForUser(context.getContentResolver(), "enabled_accessibility_services", sb.toString(), i);
    }
}
