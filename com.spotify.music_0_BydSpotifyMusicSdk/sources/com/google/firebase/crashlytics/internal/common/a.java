package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import p.b30;
import p.b33;
import p.bh;
import p.bj2;
import p.ci1;
import p.dj2;
import p.e31;
import p.es2;
import p.f30;
import p.f40;
import p.g40;
import p.g90;
import p.gh2;
import p.gu0;
import p.h40;
import p.hk0;
import p.i23;
import p.i40;
import p.ih;
import p.j30;
import p.j40;
import p.jh;
import p.jh1;
import p.k40;
import p.kh;
import p.ks2;
import p.mc1;
import p.oz1;
import p.q6;
import p.ri;
import p.si;
import p.ti;
import p.ui;
import p.uy2;
import p.v4;
import p.x42;
import p.x50;
import p.xb2;
import p.y23;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final b30 r = new b30(0);
    public final Context a;
    public final x50 b;
    public final g90 c;
    public final gu0 d;
    public final gh2 e;
    public final e31 f;
    public final gu0 g;
    public final jh1 h;
    public final mc1 i;
    public final j30 j;
    public final q6 k;
    public final xb2 l;
    public k40 m;
    public final bj2 n = new bj2();
    public final bj2 o = new bj2();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final bj2 f38p = new bj2();
    public final AtomicBoolean q = new AtomicBoolean(false);

    public a(Context context, gh2 gh2Var, e31 e31Var, x50 x50Var, gu0 gu0Var, g90 g90Var, jh1 jh1Var, gu0 gu0Var2, mc1 mc1Var, xb2 xb2Var, j30 j30Var, q6 q6Var) {
        this.a = context;
        this.e = gh2Var;
        this.f = e31Var;
        this.b = x50Var;
        this.g = gu0Var;
        this.c = g90Var;
        this.h = jh1Var;
        this.d = gu0Var2;
        this.i = mc1Var;
        this.j = j30Var;
        this.k = q6Var;
        this.l = xb2Var;
    }

    public static void a(a aVar, String str) {
        Locale locale;
        Integer num;
        aVar.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        Log.isLoggable("FirebaseCrashlytics", 3);
        Locale locale2 = Locale.US;
        String str2 = String.format(locale2, "Crashlytics Android SDK/%s", "18.3.6");
        e31 e31Var = aVar.f;
        String str3 = e31Var.c;
        jh1 jh1Var = aVar.h;
        si siVar = new si(str3, (String) jh1Var.f, (String) jh1Var.g, e31Var.d(), (((String) jh1Var.d) != null ? DeliveryMechanism.APP_STORE : DeliveryMechanism.DEVELOPER).a(), (g90) jh1Var.h);
        String str4 = Build.VERSION.RELEASE;
        String str5 = Build.VERSION.CODENAME;
        ui uiVar = new ui(str4, str5, zu.p0());
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        CommonUtils$Architecture commonUtils$Architecture = CommonUtils$Architecture.c;
        String str6 = Build.CPU_ABI;
        boolean zIsEmpty = TextUtils.isEmpty(str6);
        CommonUtils$Architecture commonUtils$Architecture2 = CommonUtils$Architecture.c;
        if (zIsEmpty) {
            Log.isLoggable("FirebaseCrashlytics", 2);
            locale = locale2;
        } else {
            locale = locale2;
            CommonUtils$Architecture commonUtils$Architecture3 = (CommonUtils$Architecture) CommonUtils$Architecture.e.get(str6.toLowerCase(locale));
            if (commonUtils$Architecture3 != null) {
                commonUtils$Architecture2 = commonUtils$Architecture3;
            }
        }
        int iOrdinal = commonUtils$Architecture2.ordinal();
        String str7 = Build.MODEL;
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        long jJ0 = zu.j0();
        boolean zN0 = zu.n0();
        int iE0 = zu.e0();
        String str8 = Build.MANUFACTURER;
        String str9 = Build.PRODUCT;
        aVar.j.c(str, str2, jCurrentTimeMillis, new ri(siVar, uiVar, new ti(iOrdinal, str7, iAvailableProcessors, jJ0, blockCount, zN0, iE0, str8, str9)));
        mc1 mc1Var = aVar.i;
        mc1Var.b.b();
        mc1Var.b = mc1.c;
        if (str != null) {
            mc1Var.b = new oz1(mc1Var.a.l(str, "userlog"));
        }
        xb2 xb2Var = aVar.l;
        h40 h40Var = xb2Var.a;
        h40Var.getClass();
        Charset charset = g40.a;
        uy2 uy2Var = new uy2();
        uy2Var.a = "18.3.6";
        jh1 jh1Var2 = h40Var.c;
        String str10 = (String) jh1Var2.a;
        if (str10 == null) {
            throw new NullPointerException("Null gmpAppId");
        }
        uy2Var.b = str10;
        e31 e31Var2 = h40Var.b;
        String strD = e31Var2.d();
        if (strD == null) {
            throw new NullPointerException("Null installationUuid");
        }
        uy2Var.d = strD;
        String str11 = (String) jh1Var2.f;
        if (str11 == null) {
            throw new NullPointerException("Null buildVersion");
        }
        uy2Var.e = str11;
        String str12 = (String) jh1Var2.g;
        if (str12 == null) {
            throw new NullPointerException("Null displayVersion");
        }
        uy2Var.f = str12;
        uy2Var.c = 4;
        ih ihVar = new ih();
        ihVar.e = Boolean.FALSE;
        ihVar.c = Long.valueOf(jCurrentTimeMillis);
        if (str == null) {
            throw new NullPointerException("Null identifier");
        }
        ihVar.b = str;
        String str13 = h40.g;
        if (str13 == null) {
            throw new NullPointerException("Null generator");
        }
        ihVar.a = str13;
        String str14 = e31Var2.c;
        if (str14 == null) {
            throw new NullPointerException("Null identifier");
        }
        String str15 = (String) jh1Var2.f;
        if (str15 == null) {
            throw new NullPointerException("Null version");
        }
        String str16 = (String) jh1Var2.g;
        String strD2 = e31Var2.d();
        g90 g90Var = (g90) jh1Var2.h;
        int i = 0;
        if (((v4) g90Var.k) == null) {
            g90Var.k = new v4(g90Var, i);
        }
        String str17 = (String) ((v4) g90Var.k).e;
        g90 g90Var2 = (g90) jh1Var2.h;
        if (((v4) g90Var2.k) == null) {
            g90Var2.k = new v4(g90Var2, i);
        }
        ihVar.f = new kh(str14, str15, str16, strD2, str17, (String) ((v4) g90Var2.k).k);
        gh2 gh2Var = new gh2(12);
        gh2Var.e = 3;
        gh2Var.k = str4;
        gh2Var.l = str5;
        gh2Var.m = Boolean.valueOf(zu.p0());
        ihVar.h = gh2Var.h();
        StatFs statFs2 = new StatFs(Environment.getDataDirectory().getPath());
        int iIntValue = 7;
        if (!TextUtils.isEmpty(str6) && (num = (Integer) h40.f.get(str6.toLowerCase(locale))) != null) {
            iIntValue = num.intValue();
        }
        int iAvailableProcessors2 = Runtime.getRuntime().availableProcessors();
        long jJ02 = zu.j0();
        long blockCount2 = ((long) statFs2.getBlockCount()) * ((long) statFs2.getBlockSize());
        boolean zN02 = zu.n0();
        int iE02 = zu.e0();
        uy2 uy2Var2 = new uy2();
        uy2Var2.a = Integer.valueOf(iIntValue);
        uy2Var2.b = str7;
        uy2Var2.c = Integer.valueOf(iAvailableProcessors2);
        uy2Var2.d = Long.valueOf(jJ02);
        uy2Var2.e = Long.valueOf(blockCount2);
        uy2Var2.f = Boolean.valueOf(zN02);
        uy2Var2.g = Integer.valueOf(iE02);
        uy2Var2.h = str8;
        uy2Var2.i = str9;
        ihVar.i = uy2Var2.c();
        ihVar.k = 3;
        uy2Var.g = ihVar.a();
        bh bhVarA = uy2Var.a();
        gu0 gu0Var = xb2Var.b.b;
        f40 f40Var = bhVarA.h;
        if (f40Var == null) {
            Log.isLoggable("FirebaseCrashlytics", 3);
            return;
        }
        String str18 = ((jh) f40Var).b;
        try {
            j40.f.getClass();
            j40.f(gu0Var.l(str18, "report"), i40.a.t(bhVarA));
            File fileL = gu0Var.l(str18, "start-time");
            long j = ((jh) f40Var).c;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileL), j40.d);
            try {
                outputStreamWriter.write("");
                fileL.setLastModified(j * 1000);
                outputStreamWriter.close();
            } finally {
            }
        } catch (IOException unused) {
            Log.isLoggable("FirebaseCrashlytics", 3);
        }
    }

    public static b33 b(a aVar) {
        b33 b33VarI;
        aVar.getClass();
        ArrayList arrayList = new ArrayList();
        for (File file : gu0.p(((File) aVar.g.b).listFiles(r))) {
            try {
                long j = Long.parseLong(file.getName().substring(3));
                try {
                    Class.forName("com.google.firebase.crash.FirebaseCrash");
                    b33VarI = ci1.t(null);
                } catch (ClassNotFoundException unused) {
                    Log.isLoggable("FirebaseCrashlytics", 3);
                    b33VarI = ci1.i(new ScheduledThreadPoolExecutor(1), new f30(aVar, j));
                }
                arrayList.add(b33VarI);
            } catch (NumberFormatException unused2) {
                file.getName();
            }
            file.delete();
        }
        return ci1.J(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x04a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:363:0x04ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:371:0x051f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01f4  */
    /* JADX WARN: Type inference failed for: r10v7, types: [byte[], java.io.Serializable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(boolean r28, com.google.firebase.crashlytics.internal.settings.a r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1938
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.a.c(boolean, com.google.firebase.crashlytics.internal.settings.a):void");
    }

    public final void d(long j) {
        try {
            gu0 gu0Var = this.g;
            String str = ".ae" + j;
            gu0Var.getClass();
            if (new File((File) gu0Var.b, str).createNewFile()) {
            } else {
                throw new IOException("Create new file failed.");
            }
        } catch (IOException unused) {
        }
    }

    public final void e(com.google.firebase.crashlytics.internal.settings.a aVar) throws Throwable {
        if (!Boolean.TRUE.equals(((ThreadLocal) this.e.m).get())) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
        k40 k40Var = this.m;
        if (k40Var != null && k40Var.e.get()) {
            return;
        }
        Log.isLoggable("FirebaseCrashlytics", 2);
        try {
            c(true, aVar);
            Log.isLoggable("FirebaseCrashlytics", 2);
        } catch (Exception e) {
            Log.e("FirebaseCrashlytics", "Unable to finalize previously open sessions.", e);
        }
    }

    public final String f() {
        NavigableSet navigableSetC = this.l.b.c();
        if (navigableSetC.isEmpty()) {
            return null;
        }
        return (String) navigableSetC.first();
    }

    public final b33 g(b33 b33Var) {
        b33 b33Var2;
        b33 b33VarT;
        gu0 gu0Var = this.l.b.b;
        boolean zIsEmpty = gu0.p(((File) gu0Var.d).listFiles()).isEmpty();
        bj2 bj2Var = this.n;
        int i = 2;
        if (zIsEmpty && gu0.p(((File) gu0Var.e).listFiles()).isEmpty() && gu0.p(((File) gu0Var.f).listFiles()).isEmpty()) {
            Log.isLoggable("FirebaseCrashlytics", 2);
            bj2Var.b(Boolean.FALSE);
            return ci1.t(null);
        }
        Log.isLoggable("FirebaseCrashlytics", 2);
        x50 x50Var = this.b;
        if (x50Var.b()) {
            Log.isLoggable("FirebaseCrashlytics", 3);
            bj2Var.b(Boolean.FALSE);
            b33VarT = ci1.t(Boolean.TRUE);
        } else {
            Log.isLoggable("FirebaseCrashlytics", 3);
            Log.isLoggable("FirebaseCrashlytics", 2);
            bj2Var.b(Boolean.TRUE);
            synchronized (x50Var.f) {
                b33Var2 = ((bj2) x50Var.g).a;
            }
            hk0 hk0Var = new hk0(21, this);
            b33Var2.getClass();
            y23 y23Var = dj2.a;
            b33 b33Var3 = new b33();
            b33Var2.b.b(new i23(y23Var, hk0Var, b33Var3));
            b33Var2.j();
            Log.isLoggable("FirebaseCrashlytics", 3);
            b33 b33Var4 = this.o.a;
            ExecutorService executorService = ks2.a;
            bj2 bj2Var2 = new bj2();
            es2 es2Var = new es2(bj2Var2, i);
            b33Var3.a(y23Var, es2Var);
            b33Var4.getClass();
            b33Var4.a(y23Var, es2Var);
            b33VarT = bj2Var2.a;
        }
        x42 x42Var = new x42(this, b33Var);
        b33VarT.getClass();
        y23 y23Var2 = dj2.a;
        b33 b33Var5 = new b33();
        b33VarT.b.b(new i23(y23Var2, x42Var, b33Var5));
        b33VarT.j();
        return b33Var5;
    }
}
