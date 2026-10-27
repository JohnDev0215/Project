package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: IntentUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class q11 {
    public static ComponentName a;
    public static final Object b = new Object();

    public static void a(Intent intent) {
        if (e(xz.a, intent)) {
            intent.putExtra("trusted_application_code_extra", b());
        }
    }

    public static PendingIntent b() {
        Intent intent = new Intent();
        Context context = xz.a;
        String packageName = context.getPackageName();
        synchronized (b) {
            if (a == null) {
                a = new ComponentName(packageName, "FakeClass");
            }
        }
        intent.setComponent(a);
        return PendingIntent.getActivity(context, 0, intent, d(false));
    }

    public static <T extends Parcelable> ArrayList<T> c(Intent intent, String str) {
        try {
            return intent.getParcelableArrayListExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getParcelableArrayListExtra failed on intent " + intent);
            return null;
        }
    }

    public static int d(boolean z) {
        if (z) {
            return (!z || Build.VERSION.SDK_INT < 31) ? 0 : 33554432;
        }
        return 67108864;
    }

    public static boolean e(Context context, Intent intent) {
        boolean z = !TextUtils.isEmpty(intent.getPackage());
        boolean z2 = z && context.getPackageName().equals(intent.getPackage());
        boolean z3 = intent.getComponent() != null;
        if (z3 && context.getPackageName().equals(intent.getComponent().getPackageName())) {
            if (z) {
                return z2;
            }
            return true;
        }
        if (z2) {
            return !z3;
        }
        return false;
    }

    public static boolean f(Intent intent) {
        return intent != null && TextUtils.equals(intent.getAction(), "android.intent.action.MAIN") && intent.hasCategory("android.intent.category.LAUNCHER") && (intent.getFlags() & 1048576) == 0;
    }

    public static boolean g(Intent intent) {
        PendingIntent pendingIntent;
        if (intent == null || (pendingIntent = (PendingIntent) n(intent, "trusted_application_code_extra")) == null) {
            return false;
        }
        return b().equals(pendingIntent);
    }

    public static boolean h(Bundle bundle, String str, boolean z) {
        try {
            return bundle.getBoolean(str, z);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getBoolean failed on bundle " + bundle);
            return z;
        }
    }

    public static boolean i(Intent intent, String str, boolean z) {
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getBooleanExtra failed on intent " + intent);
            return z;
        }
    }

    public static Bundle j(Intent intent, String str) {
        try {
            return intent.getBundleExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getBundleExtra failed on intent " + intent);
            return null;
        }
    }

    public static int k(String str, int i, Bundle bundle) {
        try {
            return bundle.getInt(str, i);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getInt failed on bundle " + bundle);
            return i;
        }
    }

    public static int l(Intent intent, String str, int i) {
        try {
            return intent.getIntExtra(str, i);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getIntExtra failed on intent " + intent);
            return i;
        }
    }

    public static <T extends Parcelable> T m(Bundle bundle, String str) {
        try {
            return (T) bundle.getParcelable(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getParcelable failed on bundle " + bundle);
            return null;
        }
    }

    public static <T extends Parcelable> T n(Intent intent, String str) {
        try {
            return (T) intent.getParcelableExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getParcelableExtra failed on intent " + intent);
            return null;
        }
    }

    public static <T extends Serializable> T o(Intent intent, String str) {
        try {
            return (T) intent.getSerializableExtra(str);
        } catch (ClassCastException e) {
            Log.e("cr_IntentUtils", "Invalide class for Serializable: ".concat(str), e);
            return null;
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getSerializableExtra failed on intent " + intent);
            return null;
        }
    }

    public static String p(Bundle bundle, String str) {
        try {
            return bundle.getString(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getString failed on bundle " + bundle);
            return null;
        }
    }

    public static ArrayList<String> q(Intent intent, String str) {
        try {
            return intent.getStringArrayListExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getStringArrayListExtra failed on intent " + intent);
            return null;
        }
    }

    public static String r(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "getStringExtra failed on intent " + intent);
            return null;
        }
    }

    public static boolean s(Intent intent, String str) {
        try {
            return intent.hasExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "hasExtra failed on intent " + intent);
            return false;
        }
    }

    public static void t(Intent intent, String str) {
        try {
            intent.removeExtra(str);
        } catch (Throwable unused) {
            Log.e("cr_IntentUtils", "removeExtra failed on intent " + intent);
        }
    }

    public static Intent u(Intent intent) {
        if (Build.VERSION.SDK_INT >= 33) {
            return intent;
        }
        if (intent == null) {
            return null;
        }
        try {
            intent.getBooleanExtra("TriggerUnparcel", false);
            return intent;
        } catch (BadParcelableException e) {
            Log.e("cr_IntentUtils", "Invalid incoming intent.", e);
            return intent.replaceExtras((Bundle) null);
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof ClassNotFoundException)) {
                throw e2;
            }
            Log.e("cr_IntentUtils", "Invalid incoming intent.", e2);
            return intent.replaceExtras((Bundle) null);
        }
    }
}
