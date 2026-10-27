package p;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class h40 {
    public static final HashMap f;
    public static final String g;
    public final Context a;
    public final e31 b;
    public final jh1 c;
    public final nf2 d;
    public final com.google.firebase.crashlytics.internal.settings.a e;

    static {
        HashMap map = new HashMap();
        f = map;
        map.put("armeabi", 5);
        map.put("armeabi-v7a", 6);
        map.put("arm64-v8a", 9);
        map.put("x86", 0);
        map.put("x86_64", 1);
        g = String.format(Locale.US, "Crashlytics Android SDK/%s", "18.3.6");
    }

    public h40(Context context, e31 e31Var, jh1 jh1Var, jv jvVar, com.google.firebase.crashlytics.internal.settings.a aVar) {
        this.a = context;
        this.b = e31Var;
        this.c = jh1Var;
        this.d = jvVar;
        this.e = aVar;
    }

    public static rh c(gh2 gh2Var, int i) {
        String str = (String) gh2Var.k;
        String str2 = (String) gh2Var.e;
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) gh2Var.l;
        int i2 = 0;
        if (stackTraceElementArr == null) {
            stackTraceElementArr = new StackTraceElement[0];
        }
        gh2 gh2Var2 = (gh2) gh2Var.m;
        if (i >= 8) {
            gh2 gh2Var3 = gh2Var2;
            while (gh2Var3 != null) {
                gh2Var3 = (gh2) gh2Var3.m;
                i2++;
            }
        }
        pi piVar = new pi();
        if (str == null) {
            throw new NullPointerException("Null type");
        }
        piVar.b = str;
        piVar.a = str2;
        piVar.c = new z31(d(stackTraceElementArr, 4));
        piVar.e = Integer.valueOf(i2);
        if (gh2Var2 != null && i2 == 0) {
            piVar.d = c(gh2Var2, i + 1);
        }
        return piVar.c();
    }

    public static z31 d(StackTraceElement[] stackTraceElementArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            pi piVar = new pi();
            piVar.e = Integer.valueOf(i);
            long lineNumber = 0;
            long jMax = stackTraceElement.isNativeMethod() ? Math.max(stackTraceElement.getLineNumber(), 0L) : 0L;
            String str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
            String fileName = stackTraceElement.getFileName();
            if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
                lineNumber = stackTraceElement.getLineNumber();
            }
            piVar.a = Long.valueOf(jMax);
            if (str == null) {
                throw new NullPointerException("Null symbol");
            }
            piVar.b = str;
            piVar.c = fileName;
            piVar.d = Long.valueOf(lineNumber);
            arrayList.add(piVar.d());
        }
        return new z31(arrayList);
    }

    public static th e(Thread thread, StackTraceElement[] stackTraceElementArr, int i) {
        kk0 kk0Var = new kk0(16);
        String name = thread.getName();
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        kk0Var.e = name;
        kk0Var.k = Integer.valueOf(i);
        kk0Var.l = new z31(d(stackTraceElementArr, i));
        return kk0Var.h();
    }

    public final z31 a() {
        t30[] t30VarArr = new t30[1];
        gh2 gh2Var = new gh2(11);
        gh2Var.e = 0L;
        gh2Var.k = 0L;
        jh1 jh1Var = this.c;
        String str = (String) jh1Var.e;
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        gh2Var.l = str;
        gh2Var.m = (String) jh1Var.b;
        t30VarArr[0] = gh2Var.g();
        return new z31(Arrays.asList(t30VarArr));
    }

    public final vh b(int i) {
        boolean z;
        Float fValueOf;
        Intent intentRegisterReceiver;
        int intExtra;
        int intExtra2;
        Context context = this.a;
        int i2 = 2;
        boolean z2 = false;
        try {
            intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (IllegalStateException e) {
            e = e;
            z = false;
        }
        if (intentRegisterReceiver != null) {
            int intExtra3 = intentRegisterReceiver.getIntExtra("status", -1);
            z = intExtra3 != -1 && (intExtra3 == 2 || intExtra3 == 5);
            try {
                intExtra = intentRegisterReceiver.getIntExtra("level", -1);
                intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
            } catch (IllegalStateException e2) {
                e = e2;
                Log.e("FirebaseCrashlytics", "An error occurred getting battery state.", e);
            }
            fValueOf = (intExtra == -1 || intExtra2 == -1) ? null : Float.valueOf(intExtra / intExtra2);
        } else {
            fValueOf = null;
            z = false;
        }
        Double dValueOf = fValueOf != null ? Double.valueOf(fValueOf.doubleValue()) : null;
        if (!z || fValueOf == null) {
            i2 = 1;
        } else if (fValueOf.floatValue() >= 0.99d) {
            i2 = 3;
        }
        if (!zu.n0() && ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            z2 = true;
        }
        long jJ0 = zu.j0();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        long j = jJ0 - memoryInfo.availMem;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        long blockCount = (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
        gu0 gu0Var = new gu0(6);
        gu0Var.a = dValueOf;
        gu0Var.b = Integer.valueOf(i2);
        gu0Var.c = Boolean.valueOf(z2);
        gu0Var.d = Integer.valueOf(i);
        gu0Var.e = Long.valueOf(j);
        gu0Var.f = Long.valueOf(blockCount);
        return gu0Var.c();
    }
}
