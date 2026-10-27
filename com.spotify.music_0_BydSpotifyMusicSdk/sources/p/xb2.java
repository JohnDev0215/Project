package p;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

/* JADX INFO: loaded from: classes.dex */
public final class xb2 {
    public final h40 a;
    public final j40 b;
    public final z50 c;
    public final mc1 d;
    public final gu0 e;

    public xb2(h40 h40Var, j40 j40Var, z50 z50Var, mc1 mc1Var, gu0 gu0Var) {
        this.a = h40Var;
        this.b = j40Var;
        this.c = z50Var;
        this.d = mc1Var;
        this.e = gu0Var;
    }

    public static nh a(nh nhVar, mc1 mc1Var, gu0 gu0Var) {
        Map mapUnmodifiableMap;
        Map mapUnmodifiableMap2;
        pi piVar = new pi();
        piVar.a = Long.valueOf(nhVar.a);
        piVar.b = nhVar.b;
        piVar.c = nhVar.c;
        piVar.d = nhVar.d;
        piVar.e = nhVar.e;
        String strC = mc1Var.b.c();
        if (strC != null) {
            piVar.e = new wh(strC);
        } else {
            Log.isLoggable("FirebaseCrashlytics", 2);
        }
        o81 o81Var = (o81) ((AtomicMarkableReference) ((aa0) gu0Var.d).b).getReference();
        synchronized (o81Var) {
            mapUnmodifiableMap = Collections.unmodifiableMap(new HashMap(o81Var.a));
        }
        ArrayList arrayListC = c(mapUnmodifiableMap);
        o81 o81Var2 = (o81) ((AtomicMarkableReference) ((aa0) gu0Var.e).b).getReference();
        synchronized (o81Var2) {
            mapUnmodifiableMap2 = Collections.unmodifiableMap(new HashMap(o81Var2.a));
        }
        ArrayList arrayListC2 = c(mapUnmodifiableMap2);
        if (!arrayListC.isEmpty() || !arrayListC2.isEmpty()) {
            oh ohVar = (oh) nhVar.c;
            y30 y30Var = ohVar.a;
            Boolean bool = ohVar.d;
            Integer numValueOf = Integer.valueOf(ohVar.e);
            z31 z31Var = new z31(arrayListC);
            z31 z31Var2 = new z31(arrayListC2);
            String strConcat = y30Var == null ? " execution" : "";
            if (numValueOf == null) {
                strConcat = strConcat.concat(" uiOrientation");
            }
            if (!strConcat.isEmpty()) {
                throw new IllegalStateException("Missing required properties:".concat(strConcat));
            }
            piVar.c = new oh(y30Var, z31Var, z31Var2, bool, numValueOf.intValue());
        }
        return piVar.b();
    }

    public static xb2 b(Context context, e31 e31Var, gu0 gu0Var, jh1 jh1Var, mc1 mc1Var, gu0 gu0Var2, jv jvVar, com.google.firebase.crashlytics.internal.settings.a aVar, ww1 ww1Var) {
        byte[] bytes;
        h40 h40Var = new h40(context, e31Var, jh1Var, jvVar, aVar);
        j40 j40Var = new j40(gu0Var, aVar);
        i40 i40Var = z50.b;
        ho2.b(context);
        ho2 ho2VarA = ho2.a();
        ho2VarA.getClass();
        Set setUnmodifiableSet = Collections.unmodifiableSet(bp.d);
        v4 v4VarA = wi.a();
        v4VarA.M("cct");
        String str = z50.c;
        String str2 = z50.d;
        if (str2 == null && str == null) {
            bytes = null;
        } else {
            Object[] objArr = new Object[4];
            objArr[0] = "1$";
            objArr[1] = str;
            objArr[2] = "\\";
            if (str2 == null) {
                str2 = "";
            }
            objArr[3] = str2;
            bytes = String.format("%s%s%s%s", objArr).getBytes(Charset.forName("UTF-8"));
        }
        v4VarA.k = bytes;
        kk0 kk0Var = new kk0(setUnmodifiableSet, v4VarA.e(), ho2VarA, 13, 0);
        ae0 ae0Var = new ae0("json");
        iy2 iy2Var = z50.e;
        if (((Set) kk0Var.e).contains(ae0Var)) {
            return new xb2(h40Var, j40Var, new z50(new l32(new fo2((wi) kk0Var.k, ae0Var, iy2Var, (go2) kk0Var.l), aVar.b(), ww1Var)), mc1Var, gu0Var2);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", ae0Var, (Set) kk0Var.e));
    }

    public static ArrayList c(Map map) {
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(map.size());
        for (Map.Entry entry : map.entrySet()) {
            ay2 ay2Var = new ay2(10);
            String str = (String) entry.getKey();
            if (str == null) {
                throw new NullPointerException("Null key");
            }
            ay2Var.c = str;
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                throw new NullPointerException("Null value");
            }
            ay2Var.e = str2;
            arrayList.add(ay2Var.b());
        }
        Collections.sort(arrayList, new nv0(1));
        return arrayList;
    }

    public final void d(Throwable th, Thread thread, String str, String str2, long j, boolean z) {
        ActivityManager.RunningAppProcessInfo next;
        boolean zEquals = str2.equals("crash");
        h40 h40Var = this.a;
        Context context = h40Var.a;
        int i = context.getResources().getConfiguration().orientation;
        nf2 nf2Var = h40Var.d;
        gh2 gh2Var = new gh2(th, nf2Var);
        pi piVar = new pi();
        piVar.b = str2;
        piVar.a = Long.valueOf(j);
        String str3 = (String) h40Var.c.e;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.processName.equals(str3)) {
                    break;
                }
            }
            next = null;
        } else {
            next = null;
        }
        Boolean boolValueOf = next != null ? Boolean.valueOf(next.importance != 100) : null;
        Integer numValueOf = Integer.valueOf(i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(h40.e(thread, (StackTraceElement[]) gh2Var.l, 4));
        if (z) {
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if (!key.equals(thread)) {
                    arrayList.add(h40.e(key, nf2Var.a(entry.getValue()), 0));
                }
            }
        }
        z31 z31Var = new z31(arrayList);
        rh rhVarC = h40.c(gh2Var, 0);
        v4 v4Var = new v4(15);
        v4Var.e = "0";
        v4Var.k = "0";
        v4Var.l = 0L;
        ph phVar = new ph(z31Var, rhVarC, null, v4Var.c(), h40Var.a());
        String strConcat = numValueOf == null ? "".concat(" uiOrientation") : "";
        if (!strConcat.isEmpty()) {
            throw new IllegalStateException("Missing required properties:".concat(strConcat));
        }
        piVar.c = new oh(phVar, null, null, boolValueOf, numValueOf.intValue());
        piVar.d = h40Var.b(i);
        this.b.d(a(piVar.b(), this.d, this.e), str, zEquals);
    }

    public final b33 e(Executor executor, String str) {
        bj2 bj2Var;
        ArrayList<File> arrayListB = this.b.b();
        ArrayList<ch> arrayList = new ArrayList();
        for (File file : arrayListB) {
            try {
                i40 i40Var = j40.f;
                String strE = j40.e(file);
                i40Var.getClass();
                arrayList.add(new ch(i40.h(strE), file.getName(), file));
            } catch (IOException unused) {
                Objects.toString(file);
                file.delete();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (ch chVar : arrayList) {
            if (str == null || str.equals(chVar.b)) {
                z50 z50Var = this.c;
                boolean z = str != null;
                l32 l32Var = z50Var.a;
                synchronized (l32Var.f) {
                    try {
                        bj2Var = new bj2();
                        if (z) {
                            ((AtomicInteger) l32Var.i.e).getAndIncrement();
                            if (l32Var.f.size() < l32Var.e) {
                                String str2 = chVar.b;
                                Log.isLoggable("FirebaseCrashlytics", 3);
                                l32Var.f.size();
                                Log.isLoggable("FirebaseCrashlytics", 3);
                                l32Var.g.execute(new xf1(l32Var, chVar, bj2Var));
                                Log.isLoggable("FirebaseCrashlytics", 3);
                                bj2Var.b(chVar);
                            } else {
                                l32Var.a();
                                String str3 = chVar.b;
                                Log.isLoggable("FirebaseCrashlytics", 3);
                                ((AtomicInteger) l32Var.i.k).getAndIncrement();
                                bj2Var.b(chVar);
                            }
                        } else {
                            l32Var.b(chVar, bj2Var);
                        }
                    } finally {
                    }
                }
                arrayList2.add(bj2Var.a.a(executor, new af2(11, this)));
            }
        }
        return ci1.J(arrayList2);
    }
}
