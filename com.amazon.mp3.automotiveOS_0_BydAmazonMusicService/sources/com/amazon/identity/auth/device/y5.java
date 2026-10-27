package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.amazon.identity.auth.device.me;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class y5 {
    public static final AtomicInteger f = new AtomicInteger(0);
    public static final String g = y5.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f667a;
    public final int b;
    public final boolean c;
    public final int d;
    public ce e;

    public y5(int i, String str, int i2) {
        System.nanoTime();
        this.b = i;
        this.f667a = str;
        this.d = i2;
        this.e = yd.a(str);
        this.c = false;
        ga.a(g, "Tracer created. Tracer Id : %s API Name : %s", Integer.valueOf(i), str);
    }

    public static int a() {
        return (f.incrementAndGet() % 1000) + (Process.myPid() * 1000);
    }

    public de b() {
        de deVarB = this.e.b("Time");
        deVarB.b();
        return deVarB;
    }

    public de c(String str) {
        de deVarB = this.e.b(str);
        deVarB.b();
        return deVarB;
    }

    public static y5 a(String str) {
        return new y5(a(), str, Binder.getCallingUid(), true);
    }

    public void b(String str) {
        this.e.a(str);
        if (this.c) {
            me.a aVar = (me.a) yd.a();
            aVar.f438a = this.f667a;
            aVar.b = str;
            aVar.a().a();
        }
    }

    public static y5 a(Bundle bundle, String str) {
        if (bundle == null) {
            return new y5(str);
        }
        int i = bundle.getInt("traceId", a());
        String string = bundle.getString("apiName");
        boolean z = bundle.getBoolean("supportMinerva", false);
        int i2 = bundle.getInt("callingUid", -1);
        if (TextUtils.isEmpty(string)) {
            return new y5(i, str, i2, z);
        }
        return new y5(i, string, i2, z);
    }

    public y5(int i, String str, int i2, boolean z) {
        System.nanoTime();
        this.b = i;
        this.f667a = str;
        this.d = i2;
        this.e = yd.a(str);
        this.c = z;
        ga.a(g, "Tracer created. Tracer Id : %s API Name : %s", Integer.valueOf(i), str);
    }

    public static y5 a(Intent intent, String str) {
        if (intent == null) {
            return new y5(str);
        }
        int intExtra = intent.getIntExtra("traceId", a());
        String stringExtra = intent.getStringExtra("apiName");
        boolean booleanExtra = intent.getBooleanExtra("supportMinerva", false);
        int intExtra2 = intent.getIntExtra("callingUid", -1);
        if (TextUtils.isEmpty(stringExtra)) {
            ga.a(g);
            return new y5(intExtra, str, intExtra2, booleanExtra);
        }
        ga.a(g);
        return new y5(intExtra, stringExtra, intExtra2, booleanExtra);
    }

    public y5(String str) {
        this(a(), str, Binder.getCallingUid());
    }

    public void a(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.putExtra("traceId", this.b);
        intent.putExtra("apiName", this.f667a);
        intent.putExtra("supportMinerva", this.c);
    }

    public void a(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putInt("traceId", this.b);
        bundle.putString("apiName", this.f667a);
        bundle.putBoolean("supportMinerva", this.c);
    }

    public String a(Context context) {
        String[] packagesForUid;
        try {
            if (this.d != -1 && (packagesForUid = context.getPackageManager().getPackagesForUid(this.d)) != null) {
                return Arrays.toString(packagesForUid);
            }
        } catch (Exception e) {
            Log.w(ga.a(g), String.format("Couldn't get packages for the calling uid.Error Message : %s", e.getMessage()));
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public de a(Context context, String str) {
        de deVarA = ae.a(context).a(this.f667a + ":" + str);
        deVarA.b();
        return deVarA;
    }

    public void a(String str, String... strArr) {
        this.e.a(str, strArr);
        if (this.c) {
            me.a aVar = (me.a) yd.a();
            aVar.f438a = this.f667a;
            aVar.b = str;
            aVar.a().a();
        }
    }

    public void a(String str, double d) {
        this.e.a(str, d);
        if (this.c) {
            me.a aVar = (me.a) yd.a();
            aVar.f438a = this.f667a;
            aVar.b = str;
            aVar.e = Long.valueOf(Double.valueOf(d).longValue());
            aVar.a().a();
        }
    }
}
