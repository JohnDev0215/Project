package p;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class x50 {
    public final /* synthetic */ int a = 0;
    public boolean b;
    public boolean c;
    public final Object d;
    public final Object e;
    public final Object f;
    public Object g;
    public final Object h;
    public Object i;

    public x50(ru0 ru0Var) {
        Boolean boolValueOf;
        Object obj = new Object();
        this.f = obj;
        this.g = new bj2();
        this.b = false;
        this.c = false;
        this.h = new bj2();
        ru0Var.a();
        Context context = ru0Var.a;
        this.e = ru0Var;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.firebase.crashlytics", 0);
        this.d = sharedPreferences;
        if (sharedPreferences.contains("firebase_crashlytics_collection_enabled")) {
            this.c = false;
            boolValueOf = Boolean.valueOf(sharedPreferences.getBoolean("firebase_crashlytics_collection_enabled", true));
        } else {
            boolValueOf = null;
        }
        this.i = boolValueOf == null ? a(context) : boolValueOf;
        synchronized (obj) {
            try {
                if (b()) {
                    ((bj2) this.g).b(null);
                    this.b = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Boolean a(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            packageManager = context.getPackageManager();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("FirebaseCrashlytics", "Could not read data collection permission from manifest", e);
        }
        Boolean boolValueOf = (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("firebase_crashlytics_collection_enabled")) ? null : Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_crashlytics_collection_enabled"));
        if (boolValueOf == null) {
            this.c = false;
            return null;
        }
        this.c = true;
        return Boolean.valueOf(Boolean.TRUE.equals(boolValueOf));
    }

    public final synchronized boolean b() {
        boolean z;
        boolean zBooleanValue;
        try {
            Object obj = this.i;
            if (((Boolean) obj) != null) {
                zBooleanValue = ((Boolean) obj).booleanValue();
            } else {
                ru0 ru0Var = (ru0) this.e;
                ru0Var.a();
                y50 y50Var = (y50) ru0Var.g.get();
                synchronized (y50Var) {
                    z = y50Var.a;
                }
                zBooleanValue = z;
            }
            String.format("Crashlytics automatic data collection %s by %s.", zBooleanValue ? "ENABLED" : "DISABLED", ((Boolean) this.i) == null ? "global Firebase setting" : this.c ? "firebase_crashlytics_collection_enabled manifest flag" : "API");
            Log.isLoggable("FirebaseCrashlytics", 3);
        } catch (Throwable th) {
            throw th;
        }
        return zBooleanValue;
    }

    public final String toString() {
        switch (this.a) {
            case 1:
                ArrayList arrayList = new ArrayList();
                if (this.b) {
                    arrayList.add("isRegularFile");
                }
                if (this.c) {
                    arrayList.add("isDirectory");
                }
                Long l = (Long) this.e;
                if (l != null) {
                    arrayList.add("byteCount=" + l);
                }
                Long l2 = (Long) this.f;
                if (l2 != null) {
                    arrayList.add("createdAt=" + l2);
                }
                if (((Long) this.g) != null) {
                    arrayList.add("lastModifiedAt=" + ((Long) this.g));
                }
                Long l3 = (Long) this.h;
                if (l3 != null) {
                    arrayList.add("lastAccessedAt=" + l3);
                }
                if (!((Map) this.i).isEmpty()) {
                    arrayList.add("extras=" + ((Map) this.i));
                }
                return ou.h0(arrayList, ", ", "FileMetadata(", ")", null, 56);
            default:
                return super.toString();
        }
    }

    public x50(boolean z, boolean z2, os1 os1Var, Long l, Long l2, Long l3, Long l4, Map map) {
        v03.j(map, "extras");
        this.b = z;
        this.c = z2;
        this.d = os1Var;
        this.e = l;
        this.f = l2;
        this.g = l3;
        this.h = l4;
        this.i = kotlin.collections.c.l0(map);
    }

    public /* synthetic */ x50(boolean z, boolean z2, os1 os1Var, Long l, Long l2, Long l3, Long l4) {
        this(z, z2, os1Var, l, l2, l3, l4, kotlin.collections.c.h0());
    }
}
