package p;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class e31 implements x41 {
    public static final Pattern g = Pattern.compile("[^\\p{Alnum}]");
    public static final String h = Pattern.quote("/");
    public final oh1 a;
    public final Context b;
    public final String c;
    public final wu0 d;
    public final x50 e;
    public String f;

    public e31(Context context, String str, wu0 wu0Var, x50 x50Var) {
        if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        }
        this.b = context;
        this.c = str;
        this.d = wu0Var;
        this.e = x50Var;
        this.a = new oh1(20);
    }

    public static String b() {
        return "SYN_" + UUID.randomUUID().toString();
    }

    public final synchronized String a(String str, SharedPreferences sharedPreferences) {
        String lowerCase;
        String string = UUID.randomUUID().toString();
        lowerCase = string == null ? null : g.matcher(string).replaceAll("").toLowerCase(Locale.US);
        Log.isLoggable("FirebaseCrashlytics", 2);
        sharedPreferences.edit().putString("crashlytics.installation.id", lowerCase).putString("firebase.installation.id", str).apply();
        return lowerCase;
    }

    public final String c() {
        String str;
        b33 b33VarT;
        com.google.firebase.installations.a aVar = (com.google.firebase.installations.a) this.d;
        ru0 ru0Var = aVar.a;
        ru0Var.a();
        he1.f("Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.", ru0Var.c.b);
        ru0 ru0Var2 = aVar.a;
        ru0Var2.a();
        he1.f("Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.", ru0Var2.c.g);
        ru0 ru0Var3 = aVar.a;
        ru0Var3.a();
        he1.f("Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.", ru0Var3.c.a);
        ru0 ru0Var4 = aVar.a;
        ru0Var4.a();
        String str2 = ru0Var4.c.b;
        Pattern pattern = is2.c;
        if (!str2.contains(":")) {
            throw new IllegalArgumentException("Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        }
        ru0 ru0Var5 = aVar.a;
        ru0Var5.a();
        if (!is2.c.matcher(ru0Var5.c.a).matches()) {
            throw new IllegalArgumentException("Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
        }
        synchronized (aVar) {
            str = aVar.j;
        }
        if (str != null) {
            b33VarT = ci1.t(str);
        } else {
            bj2 bj2Var = new bj2();
            o01 o01Var = new o01(bj2Var);
            synchronized (aVar.g) {
                aVar.l.add(o01Var);
            }
            b33 b33Var = bj2Var.a;
            aVar.h.execute(new nv(13, aVar));
            b33VarT = b33Var;
        }
        try {
            return (String) ks2.a(b33VarT);
        } catch (Exception unused) {
            return null;
        }
    }

    public final synchronized String d() {
        try {
            String str = this.f;
            if (str != null) {
                return str;
            }
            Log.isLoggable("FirebaseCrashlytics", 2);
            SharedPreferences sharedPreferences = this.b.getSharedPreferences("com.google.firebase.crashlytics", 0);
            String string = sharedPreferences.getString("firebase.installation.id", null);
            Log.isLoggable("FirebaseCrashlytics", 2);
            if (this.e.b()) {
                String strC = c();
                Log.isLoggable("FirebaseCrashlytics", 2);
                if (strC == null) {
                    strC = string == null ? b() : string;
                }
                if (strC.equals(string)) {
                    this.f = sharedPreferences.getString("crashlytics.installation.id", null);
                } else {
                    this.f = a(strC, sharedPreferences);
                }
            } else if (string == null || !string.startsWith("SYN_")) {
                this.f = a(b(), sharedPreferences);
            } else {
                this.f = sharedPreferences.getString("crashlytics.installation.id", null);
            }
            if (this.f == null) {
                this.f = a(b(), sharedPreferences);
            }
            Log.isLoggable("FirebaseCrashlytics", 2);
            return this.f;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final String e() {
        String str;
        oh1 oh1Var = this.a;
        Context context = this.b;
        synchronized (oh1Var) {
            try {
                if (((String) oh1Var.e) == null) {
                    String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                    if (installerPackageName == null) {
                        installerPackageName = "";
                    }
                    oh1Var.e = installerPackageName;
                }
                str = "".equals((String) oh1Var.e) ? null : (String) oh1Var.e;
            } finally {
            }
        }
        return str;
    }
}
