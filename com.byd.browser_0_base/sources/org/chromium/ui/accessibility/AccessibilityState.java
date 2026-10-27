package org.chromium.ui.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import defpackage.gg3;
import defpackage.o90;
import defpackage.wz;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityState {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static c e;
    public static boolean f;
    public static String[] g;
    public static final Set<a> h = Collections.newSetFromMap(new WeakHashMap());
    public static int i = 250;

    public interface a {
        void d();
    }

    public static class b extends ContentObserver {
        public final Runnable a;

        public b(Handler handler, Runnable runnable) {
            super(handler);
            this.a = runnable;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            onChange(z, null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            ThreadUtils.c().post(this.a);
        }
    }

    public static class c {
        public final boolean a;
        public final boolean b;
        public final boolean c;
        public final boolean d;
        public final boolean e;

        public c(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.a = z;
            this.b = z2;
            this.c = z3;
            this.d = z4;
            this.e = z5;
        }
    }

    public static void a() {
        if (!f) {
            e = new c(false, false, false, false, false);
        }
        f = true;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        Context context = xz.a;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = ((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1);
        g = new String[enabledAccessibilityServiceList.size()];
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        for (AccessibilityServiceInfo accessibilityServiceInfo : enabledAccessibilityServiceList) {
            if (accessibilityServiceInfo != null) {
                a |= accessibilityServiceInfo.eventTypes;
                b |= accessibilityServiceInfo.feedbackType;
                c |= accessibilityServiceInfo.flags;
                d |= accessibilityServiceInfo.getCapabilities();
                z2 |= Build.VERSION.SDK_INT < 31 || accessibilityServiceInfo.isAccessibilityTool();
                String id = accessibilityServiceInfo.getId();
                int i3 = i2 + 1;
                g[i2] = id;
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(id);
                if (componentNameUnflattenFromString != null) {
                    arrayList.add(componentNameUnflattenFromString.flattenToShortString());
                } else {
                    arrayList.add(id);
                }
                z = true;
                i2 = i3;
            }
        }
        boolean z3 = Settings.System.getInt(context.getContentResolver(), "show_password", 1) == 1;
        Settings.Secure.getInt(context.getContentResolver(), "speak_password", 0);
        ArrayList arrayList2 = new ArrayList();
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (string != null && !string.isEmpty()) {
            for (String str : string.split(":")) {
                if (str != null && !str.isEmpty()) {
                    ComponentName componentNameUnflattenFromString2 = ComponentName.unflattenFromString(str);
                    if (componentNameUnflattenFromString2 != null) {
                        arrayList2.add(componentNameUnflattenFromString2.flattenToShortString());
                    } else {
                        arrayList2.add(str);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        Collections.sort(arrayList2);
        if (arrayList.equals(arrayList2)) {
            i = 250;
        } else {
            arrayList2.toString();
            arrayList.toString();
            if (i < 5000) {
                ThreadUtils.c().postDelayed(new wz(27), i);
                i *= 2;
                return;
            }
        }
        e = new c((a & 20484) != 0, (c & 4) != 0, z, z2, z3);
        Iterator<a> it = h.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
    }

    @CalledByNative
    public static int getAccessibilityServiceCapabilitiesMask() {
        if (!f) {
            a();
        }
        return d;
    }

    @CalledByNative
    public static int getAccessibilityServiceEventTypeMask() {
        if (!f) {
            a();
        }
        return a;
    }

    @CalledByNative
    public static int getAccessibilityServiceFeedbackTypeMask() {
        if (!f) {
            a();
        }
        return b;
    }

    @CalledByNative
    public static int getAccessibilityServiceFlagsMask() {
        if (!f) {
            a();
        }
        return c;
    }

    @CalledByNative
    public static String[] getAccessibilityServiceIds() {
        if (!f) {
            a();
        }
        return g;
    }

    @CalledByNative
    public static void registerObservers() {
        ContentResolver contentResolver = xz.a.getContentResolver();
        b bVar = new b(ThreadUtils.c(), new gg3(16));
        b bVar2 = new b(ThreadUtils.c(), new o90(15));
        contentResolver.registerContentObserver(Settings.Global.getUriFor("animator_duration_scale"), false, bVar);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("enabled_accessibility_services"), false, bVar2);
        contentResolver.registerContentObserver(Settings.System.getUriFor("touch_exploration_enabled"), false, bVar2);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("speak_password"), false, bVar2);
        contentResolver.registerContentObserver(Settings.System.getUriFor("show_password"), false, bVar2);
        if (f) {
            return;
        }
        a();
    }
}
