package io.sentry.android.core;

import A.C0050y0;
import android.content.Context;
import android.content.Intent;
import io.sentry.EnumC1495p1;
import io.sentry.F1;

/* JADX INFO: loaded from: classes.dex */
public final class F {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile F f16050h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f16051a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SentryAndroidOptions f16052b;
    public final B c;
    public final Boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0050y0 f16053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final io.sentry.protocol.m f16054f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Long f16055g;

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007f, code lost:
    
        r0 = new java.lang.String[]{"/system/xbin/which", "su"};
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0087, code lost:
    
        r0 = r2.f16223f.exec((java.lang.String[]) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008d, code lost:
    
        r5 = new java.io.BufferedReader(new java.io.InputStreamReader(r0.getInputStream(), io.sentry.android.core.internal.util.g.f16219g));
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a1, code lost:
    
        if (r5.readLine() == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a3, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a5, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a6, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a9, code lost:
    
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ad, code lost:
    
        r5 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00af, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b0, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b4, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b5, code lost:
    
        r7.addSuppressed(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:
    
        throw r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b9, code lost:
    
        r5 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ba, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bc, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00be, code lost:
    
        r8.s(io.sentry.EnumC1495p1.DEBUG, "Error when trying to check if SU exists.", r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cb, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cd, code lost:
    
        r8.k(io.sentry.EnumC1495p1.DEBUG, "SU isn't found on this Device.", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d6, code lost:
    
        if (r0 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d9, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
    
        if (r7 == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dc, code lost:
    
        h6.e.q(r8, "The ILogger object is required.");
        r0 = r2.f16220a.getPackageManager();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e7, code lost:
    
        if (r0 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e9, code lost:
    
        r2 = r2.f16222e;
        r5 = r2.length;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ef, code lost:
    
        r8 = r2[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f5, code lost:
    
        if (android.os.Build.VERSION.SDK_INT >= 33) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00f7, code lost:
    
        r0.getPackageInfo(r8, android.content.pm.PackageManager.PackageInfoFlags.of(0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ff, code lost:
    
        r0.getPackageInfo(r8, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0102, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0104, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0107, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0108, code lost:
    
        if (r0 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x010b, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0113, code lost:
    
        if (r0 != 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0115, code lost:
    
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0118, code lost:
    
        throw r13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c7 A[DONT_GENERATE, PHI: r0
  0x00c7: PHI (r0v13 java.lang.Process) = (r0v12 java.lang.Process), (r0v14 java.lang.Process) binds: [B:43:0x00c5, B:48:0x00d6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0142  */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.String[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public F(android.content.Context r14, io.sentry.android.core.SentryAndroidOptions r15) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.F.<init>(android.content.Context, io.sentry.android.core.SentryAndroidOptions):void");
    }

    public static Float b(Intent intent, F1 f12) {
        try {
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            if (intExtra != -1 && intExtra2 != -1) {
                return Float.valueOf((intExtra / intExtra2) * 100.0f);
            }
            return null;
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error getting device battery level.", th);
            return null;
        }
    }

    public static Boolean c(Intent intent, F1 f12) {
        try {
            int intExtra = intent.getIntExtra("plugged", -1);
            boolean z6 = true;
            if (intExtra != 1 && intExtra != 2) {
                z6 = false;
            }
            return Boolean.valueOf(z6);
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error getting device charging state.", th);
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(30:0|2|(1:4)|5|(3:165|6|149)|(1:(23:9|(3:155|15|16)|24|(1:26)|27|146|28|(1:33)|153|34|38|(1:40)(1:41)|42|(3:161|44|48)|49|(1:51)|52|(1:54)|55|(1:57)|58|(15:62|(1:64)(1:65)|(5:67|159|68|(1:70)(1:74)|75)|76|(1:(1:79)(1:80))(1:81)|82|(1:85)|86|(7:88|144|89|93|163|94|98)|99|(1:105)(4:107|(4:(1:110)(1:111)|112|(2:114|(1:170)(2:171|123))|167)(1:124)|125|(4:127|(6:157|130|134|151|135|139)|140|(1:142))(1:128))|106|(0)|140|(0))|143)(1:10))(1:13)|148|(0)|24|(0)|27|146|28|(0)|153|34|38|(0)(0)|42|(0)|49|(0)|52|(0)|55|(0)|58|(12:60|62|(0)(0)|(0)|76|(0)(0)|82|(1:85)|86|(0)|99|(7:101|103|105|106|(0)|140|(0))(6:103|105|106|(0)|140|(0)))|143|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0091, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0092, code lost:
    
        r7.s(io.sentry.EnumC1495p1.ERROR, "Error getting DisplayMetrics.", r8);
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ca, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00cb, code lost:
    
        r1.getLogger().p(io.sentry.EnumC1495p1.ERROR, r7, "Error getting the device's boot time.", new java.lang.Object[0]);
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0108 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.sentry.protocol.C1501f a(boolean r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 757
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.F.a(boolean, boolean):io.sentry.protocol.f");
    }
}
