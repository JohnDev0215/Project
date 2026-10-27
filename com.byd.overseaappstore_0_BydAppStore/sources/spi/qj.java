package spi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.RemoteException;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.qg;

/* JADX INFO: loaded from: classes.dex */
public final class qj {

    @NotNull
    public static final qj a = new qj();

    private qj() {
    }

    @NotNull
    public final String a() {
        String strA = av.a.a();
        r3 r3Var = r3.a;
        if (r3Var.c()) {
            ct.a("generateBaseUrl apkType:flavor_8155f_pro countryCode:" + strA + " mockCountryCode:47 url:https://storeadmin-test-eu.byd.auto/appmarketapiv5/", new Object[0]);
            return "https://storeadmin-test-eu.byd.auto/appmarketapiv5/";
        }
        if (r3Var.e() || r3Var.h()) {
            ct.a("generateBaseUrl apkType:flavor_8155f_pro countryCode:" + strA + " mockCountryCode:47 url:https://storeadmin-test-eu.byd.auto/appmarketapiv5/", new Object[0]);
            return "https://storeadmin-test-eu.byd.auto/appmarketapiv5/";
        }
        if (r3Var.b()) {
            ct.a("generateBaseUrl apkType:flavor_8155f_pro countryCode:" + strA + " mockCountryCode:47 url:https://storeadmin-test-eu.byd.auto/appmarketapiv5/", new Object[0]);
            return "https://storeadmin-test-eu.byd.auto/appmarketapiv5/";
        }
        if (Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125f_pro")) {
            String strF = f(strA);
            if (d(strF)) {
                strF = g(strA);
            }
            if (d(strF)) {
                strF = h(strA);
            }
            if (d(strF)) {
                strF = e(strA);
            }
            String str = strF;
            StringBuilder sbL = d.l("generateBaseTargetUrl apkType:", "flavor_8155f_pro", " product countryCode:", strA, " url:");
            sbL.append(str);
            ct.a(sbL.toString(), new Object[0]);
            return str;
        }
        if (Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125f_master") || r3Var.d() || r3Var.g()) {
            return b(strA);
        }
        if (r3Var.f() || r3Var.i()) {
            String strF2 = f(strA);
            if (d(strF2)) {
                strF2 = g(strA);
            }
            if (d(strF2)) {
                strF2 = h(strA);
            }
            if (d(strF2)) {
                strF2 = e(strA);
            }
            String str2 = strF2;
            StringBuilder sbL2 = d.l("generateBaseTargetUrl apkType:", "flavor_8155f_pro", " product countryCode:", strA, " url:");
            sbL2.append(str2);
            ct.a(sbL2.toString(), new Object[0]);
            return str2;
        }
        if (!r3Var.a()) {
            ct.b("generateBaseTargetUrl error apkType:flavor_8155f_pro product countryCode:" + strA + " url:https://storeadmin-test-eu.byd.auto/appmarketapiv5/", new Object[0]);
            return "https://storeadmin-test-eu.byd.auto/appmarketapiv5/";
        }
        String strF3 = f(strA);
        if (d(strF3)) {
            strF3 = g(strA);
        }
        if (d(strF3)) {
            strF3 = h(strA);
        }
        if (d(strF3)) {
            strF3 = e(strA);
        }
        String str3 = strF3;
        StringBuilder sbL3 = d.l("generateBaseTargetUrl apkType:", "flavor_8155f_pro", " product countryCode:", strA, " url:");
        sbL3.append(str3);
        ct.a(sbL3.toString(), new Object[0]);
        return str3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r6.equals("975") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r6.equals("960") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        if (r6.equals("880") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
    
        if (r6.equals("856") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        if (r6.equals("855") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:
    
        if (r6.equals("853") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
    
        if (r6.equals("850") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0070, code lost:
    
        if (r6.equals("692") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007a, code lost:
    
        if (r6.equals("689") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0084, code lost:
    
        if (r6.equals("687") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
    
        if (r6.equals("673") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0098, code lost:
    
        if (r6.equals("95") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a2, code lost:
    
        if (r6.equals("94") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ac, code lost:
    
        if (r6.equals("92") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x011a, code lost:
    
        if (r6.equals("84") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0156, code lost:
    
        if (r6.equals("66") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        if (r6.equals("977") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0160, code lost:
    
        if (r6.equals("65") != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0169, code lost:
    
        if (r6.equals("63") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a2, code lost:
    
        if (r6.equals("60") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
    
        if (r6.equals("976") == false) goto L85;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(java.lang.String r6) {
        /*
            Method dump skipped, instruction units count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qj.b(java.lang.String):java.lang.String");
    }

    public final boolean c(@NotNull Context context) {
        byte bC;
        Intrinsics.checkNotNullParameter(context, "context");
        Objects.requireNonNull(qg.a.a);
        try {
            bC = kf.b().c();
        } catch (RemoteException e) {
            ct.b("libVehicle isCellularConnected error:%s", e.getMessage());
            bC = 1;
        }
        boolean z = bC != 1;
        ct.a("libVehicle isCellularConnected realApn2State:%d, isConnected:%s", Byte.valueOf(bC), Boolean.valueOf(z));
        return z;
    }

    public final boolean d(String str) {
        return Intrinsics.areEqual(str, "https://xxx.xxx");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03c0 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03cc A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03e0 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x03e3 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String e(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 1300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qj.e(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0171 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f2 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011b A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String f(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qj.f(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002c A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String g(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case 1692: goto L56;
                case 52471: goto L4d;
                case 52475: goto L44;
                case 52753: goto L3b;
                case 56532: goto L2f;
                case 56596: goto L23;
                case 56599: goto L1a;
                case 56600: goto L11;
                case 1515390: goto L8;
                default: goto L7;
            }
        L7:
            goto L61
        L8:
            java.lang.String r0 = "1890"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L5e
            goto L61
        L11:
            java.lang.String r0 = "998"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L2c
            goto L61
        L1a:
            java.lang.String r0 = "997"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L2c
            goto L61
        L23:
            java.lang.String r0 = "994"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L2c
            goto L61
        L2c:
            java.lang.String r2 = "https://storeadmin-uz.byd.auto/appmarketapiv5/"
            goto L63
        L2f:
            java.lang.String r0 = "972"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L38
            goto L61
        L38:
            java.lang.String r2 = "https://storeadmin-eu.byd.auto/appmarketapiv5/"
            goto L63
        L3b:
            java.lang.String r0 = "595"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L5e
            goto L61
        L44:
            java.lang.String r0 = "506"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L5e
            goto L61
        L4d:
            java.lang.String r0 = "502"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L5e
            goto L61
        L56:
            java.lang.String r0 = "51"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L61
        L5e:
            java.lang.String r2 = "https://storeadmin-mx.byd.auto/appmarketapiv5/"
            goto L63
        L61:
            java.lang.String r2 = "https://xxx.xxx"
        L63:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qj.g(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00cb A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d7 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00da A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String h(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qj.h(java.lang.String):java.lang.String");
    }

    public final boolean i(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo networkInfo = ((ConnectivityManager) systemService).getNetworkInfo(1);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }
}
