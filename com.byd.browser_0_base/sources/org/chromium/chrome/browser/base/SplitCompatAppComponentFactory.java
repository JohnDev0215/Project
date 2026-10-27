package org.chromium.chrome.browser.base;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import defpackage.fa1;
import defpackage.xz;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatAppComponentFactory extends AppComponentFactory {
    public static ClassLoader a(ClassLoader classLoader, String str) {
        boolean z;
        Context context = xz.a;
        if (context == null) {
            fa1.b("SplitCompat", "Unexpected null Context when instantiating component: %s", str);
            return classLoader;
        }
        ClassLoader classLoader2 = SplitCompatAppComponentFactory.class.getClassLoader();
        ClassLoader classLoader3 = context.getClassLoader();
        if (!classLoader.equals(classLoader3)) {
            Object obj = BundleUtils.a;
            boolean z2 = true;
            try {
                Class.forName(str, false, classLoader2);
                z = true;
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            if (!z) {
                try {
                    Class.forName(str, false, classLoader3);
                } catch (ClassNotFoundException unused2) {
                    z2 = false;
                }
                if (z2) {
                    fa1.k("SplitCompat", "Mismatched ClassLoaders between Application and component: %s", str);
                    return classLoader3;
                }
            }
        }
        return classLoader;
    }

    @Override // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        SplitChromeApplication.f("chrome");
        return super.instantiateActivity(a(classLoader, str), str, intent);
    }

    @Override // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return super.instantiateProvider(a(classLoader, str), str);
    }

    @Override // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        SplitChromeApplication.f("chrome");
        return super.instantiateReceiver(a(classLoader, str), str, intent);
    }
}
