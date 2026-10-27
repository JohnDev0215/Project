package com.amazon.identity.auth.device;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class t9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f582a = "com.amazon.identity.auth.device.t9";
    public static final i6 b = new j6();
    public static AtomicReference<c6<a>> c = new AtomicReference<>(null);

    /* JADX INFO: compiled from: DCP */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f583a;

        public a(String str, Integer num) {
            this.f583a = str;
        }
    }

    public static c6<a> a(Context context) {
        Uri uri = z4.i;
        ProviderInfo providerInfoA = z5.a(uri, 0, context.getPackageManager());
        if (providerInfoA != null) {
            String str = providerInfoA.packageName;
            Bundle bundle = new Bundle();
            bundle.putString("central_package_name", str);
            bundle.putBoolean("ignore_isolation_mode", true);
            if (((j6) b).a(context, bundle)) {
                Integer numA = za.a(context, providerInfoA.packageName);
                String str2 = f582a;
                String.format(Locale.ENGLISH, "Retrieved central APK info from package manager using content provider %s. The package name is : %s and version is: %d.", uri, providerInfoA.packageName, numA);
                ga.a(str2);
                return new c6<>(new a(providerInfoA.packageName, numA));
            }
        }
        AuthenticatorDescription authenticatorDescriptionA = a(context, true);
        if (authenticatorDescriptionA == null) {
            Log.i(ga.a(f582a), "No central apk detected. This should be a 3P device.");
            return new c6<>(null);
        }
        String str3 = authenticatorDescriptionA.packageName;
        Integer numA2 = za.a(context, str3);
        String str4 = f582a;
        String.format(Locale.ENGLISH, "Retrieved central APK info from account manager using account authenticator. The package name is: %s and version is: %d.", str3, numA2);
        ga.a(str4);
        return new c6<>(new a(str3, numA2));
    }

    public static a b(Context context) {
        if (qe.a(t5.a(context))) {
            return null;
        }
        if (c.get() == null) {
            CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(c, null, a(context));
        }
        return c.get().f261a;
    }

    public static boolean c(Context context) {
        return b(context) != null;
    }

    public static boolean d(Context context) {
        return a(context).f261a != null;
    }

    public static boolean e(Context context) {
        AuthenticatorDescription authenticatorDescription;
        AuthenticatorDescription[] authenticatorTypes = AccountManager.get(context).getAuthenticatorTypes();
        int length = authenticatorTypes.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                Log.i(ga.a(f582a), "Cannot find amazon authenticator. returning null");
                authenticatorDescription = null;
                break;
            }
            authenticatorDescription = authenticatorTypes[i];
            if (a(context, authenticatorDescription, true)) {
                Log.i(ga.a(f582a), String.format("SSO was found in package %s", authenticatorDescription.packageName));
                break;
            }
            i++;
        }
        return authenticatorDescription != null;
    }

    public static boolean f(Context context) {
        a aVarB = b(context);
        if (aVarB == null) {
            return false;
        }
        return a(context, aVarB);
    }

    public static AuthenticatorDescription a(Context context, boolean z) {
        for (AuthenticatorDescription authenticatorDescription : AccountManager.get(context).getAuthenticatorTypes()) {
            if (a(context, authenticatorDescription, z)) {
                String str = f582a;
                String.format("SSO was found in package %s", authenticatorDescription.packageName);
                ga.a(str);
                return authenticatorDescription;
            }
        }
        Log.i(ga.a(f582a), "Cannot find amazon authenticator. returning null");
        return null;
    }

    @Deprecated
    public static boolean a(Context context, AuthenticatorDescription authenticatorDescription, boolean z) {
        if (!AccountConstants.AMAZON_ACCOUNT_TYPE.equals(authenticatorDescription.type)) {
            return false;
        }
        String str = authenticatorDescription.packageName;
        Bundle bundle = new Bundle();
        bundle.putString("central_package_name", str);
        bundle.putBoolean("ignore_isolation_mode", z);
        return ((j6) b).a(context, bundle);
    }

    public static boolean a(Context context, a aVar) {
        String packageName = context.getPackageName();
        String str = aVar.f583a;
        boolean zEquals = TextUtils.equals(packageName, str);
        if (!zEquals) {
            String str2 = f582a;
            String.format("Current package: %s and Authenticator owner's package: %s are different", packageName, str);
            ga.a(str2);
        }
        return zEquals;
    }
}
