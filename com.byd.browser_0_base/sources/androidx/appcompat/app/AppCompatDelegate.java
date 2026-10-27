package androidx.appcompat.app;

import android.app.LocaleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppLocalesStorageHelper;
import androidx.appcompat.widget.Toolbar;
import defpackage.o91;
import defpackage.q8;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppCompatDelegate {
    public static final AppLocalesStorageHelper.SerialExecutor e = new AppLocalesStorageHelper.SerialExecutor(new AppLocalesStorageHelper.ThreadPerTaskExecutor());
    public static int f = -100;
    public static o91 g = null;
    public static o91 h = null;
    public static Boolean i = null;
    public static boolean j = false;
    public static final q8<WeakReference<AppCompatDelegate>> k = new q8<>(0);
    public static final Object l = new Object();
    public static final Object m = new Object();

    public static class Api24Impl {
        private Api24Impl() {
        }
    }

    public static class Api33Impl {
        private Api33Impl() {
        }

        public static LocaleList a(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }

        public static void b(Object obj, LocaleList localeList) {
            ((LocaleManager) obj).setApplicationLocales(localeList);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NightMode {
    }

    public static void B(int i2) {
        if ((i2 == -1 || i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) && f != i2) {
            f = i2;
            synchronized (l) {
                q8<WeakReference<AppCompatDelegate>> q8Var = k;
                q8Var.getClass();
                q8.a aVar = new q8.a();
                while (aVar.hasNext()) {
                    AppCompatDelegate appCompatDelegate = (AppCompatDelegate) ((WeakReference) aVar.next()).get();
                    if (appCompatDelegate != null) {
                        appCompatDelegate.e();
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 33
            if (r0 < r2) goto L7e
            android.content.ComponentName r3 = new android.content.ComponentName
            java.lang.String r4 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            r3.<init>(r6, r4)
            android.content.pm.PackageManager r4 = r6.getPackageManager()
            int r4 = r4.getComponentEnabledSetting(r3)
            if (r4 == r1) goto L7e
            java.lang.String r4 = "locale"
            if (r0 < r2) goto L57
            q8<java.lang.ref.WeakReference<androidx.appcompat.app.AppCompatDelegate>> r0 = androidx.appcompat.app.AppCompatDelegate.k
            r0.getClass()
            q8$a r2 = new q8$a
            r2.<init>()
        L26:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L45
            java.lang.Object r0 = r2.next()
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            java.lang.Object r0 = r0.get()
            androidx.appcompat.app.AppCompatDelegate r0 = (androidx.appcompat.app.AppCompatDelegate) r0
            if (r0 == 0) goto L26
            android.content.Context r0 = r0.h()
            if (r0 == 0) goto L26
            java.lang.Object r0 = r0.getSystemService(r4)
            goto L46
        L45:
            r0 = 0
        L46:
            if (r0 == 0) goto L5c
            android.os.LocaleList r0 = androidx.appcompat.app.AppCompatDelegate.Api33Impl.a(r0)
            o91 r2 = new o91
            q91 r5 = new q91
            r5.<init>(r0)
            r2.<init>(r5)
            goto L5e
        L57:
            o91 r2 = androidx.appcompat.app.AppCompatDelegate.g
            if (r2 == 0) goto L5c
            goto L5e
        L5c:
            o91 r2 = defpackage.o91.b
        L5e:
            p91 r0 = r2.a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L77
            java.lang.String r0 = androidx.appcompat.app.AppLocalesStorageHelper.b(r6)
            java.lang.Object r2 = r6.getSystemService(r4)
            if (r2 == 0) goto L77
            android.os.LocaleList r0 = android.os.LocaleList.forLanguageTags(r0)
            androidx.appcompat.app.AppCompatDelegate.Api33Impl.b(r2, r0)
        L77:
            android.content.pm.PackageManager r6 = r6.getPackageManager()
            r6.setComponentEnabledSetting(r3, r1, r1)
        L7e:
            androidx.appcompat.app.AppCompatDelegate.j = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegate.c(android.content.Context):void");
    }

    public static boolean n(Context context) {
        if (i == null) {
            try {
                int i2 = AppLocalesMetadataHolderService.e;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), 640).metaData;
                if (bundle != null) {
                    i = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                i = Boolean.FALSE;
            }
        }
        return i.booleanValue();
    }

    public static void w(AppCompatDelegate appCompatDelegate) {
        synchronized (l) {
            q8<WeakReference<AppCompatDelegate>> q8Var = k;
            q8Var.getClass();
            q8.a aVar = new q8.a();
            while (aVar.hasNext()) {
                AppCompatDelegate appCompatDelegate2 = (AppCompatDelegate) ((WeakReference) aVar.next()).get();
                if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                    aVar.remove();
                }
            }
        }
    }

    public abstract void A(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void C();

    public abstract void D(int i2);

    public abstract void E(Toolbar toolbar);

    public abstract void G(CharSequence charSequence);

    public abstract void d(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean e();

    public abstract <T extends View> T g(int i2);

    public Context h() {
        return null;
    }

    public int i() {
        return -100;
    }

    public abstract MenuInflater j();

    public abstract ActionBar k();

    public abstract void l();

    public abstract void m();

    public abstract void o(Configuration configuration);

    public abstract void p();

    public abstract void q();

    public abstract void r();

    public abstract void s();

    public abstract void t();

    public abstract void u();

    public abstract void v();

    public abstract boolean x(int i2);

    public abstract void y(int i2);

    public abstract void z(View view);

    public void F(int i2) {
    }

    public Context f(Context context) {
        return context;
    }
}
