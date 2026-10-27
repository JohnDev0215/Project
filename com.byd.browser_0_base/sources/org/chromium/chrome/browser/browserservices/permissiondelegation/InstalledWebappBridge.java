package org.chromium.chrome.browser.browserservices.permissiondelegation;

import J.N;
import android.net.Uri;
import defpackage.cp1;
import defpackage.dp1;
import defpackage.ft1;
import defpackage.nf3;
import defpackage.pf3;
import defpackage.pp;
import defpackage.s50;
import defpackage.sf3;
import defpackage.xz;
import defpackage.y91;
import defpackage.z91;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes.dex */
public class InstalledWebappBridge {
    public static long a;

    public static class Permission {
        public final ft1 a;
        public final int b;

        public Permission(int i, ft1 ft1Var) {
            this.a = ft1Var;
            this.b = i;
        }
    }

    public static void a(int i) {
        long j = a;
        if (j == 0) {
            return;
        }
        N.MPWzS9sk(j, i);
    }

    public static void b(int i, long j) {
        if (j == 0) {
            return;
        }
        N.MO5rIsWk(j, i);
    }

    @CalledByNative
    public static void decidePermission(int i, String str, String str2, long j) {
        ft1 ft1VarA = ft1.a(Uri.parse(str));
        if (ft1VarA == null) {
            b(2, j);
            return;
        }
        if (i == 4) {
            z91 z91Var = ((s50) pp.d()).h().c;
            z91Var.getClass();
            y91 y91Var = new y91(z91Var, ft1VarA, j);
            sf3 sf3Var = z91Var.b;
            sf3Var.getClass();
            sf3Var.a(Uri.parse(str2), new pf3(y91Var));
            return;
        }
        if (i != 5) {
            throw new IllegalStateException("Unsupported permission type.");
        }
        dp1 dp1Var = ((s50) pp.d()).h().b;
        dp1Var.getClass();
        cp1 cp1Var = new cp1(dp1Var, ft1VarA, j, str2);
        sf3 sf3Var2 = dp1Var.b;
        sf3Var2.getClass();
        sf3Var2.a(Uri.parse(str2), new nf3(sf3Var2, xz.a.getResources().getString(R$string.notification_category_group_general), cp1Var));
    }

    @CalledByNative
    public static String getOriginFromPermission(Permission permission) {
        return permission.a.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v20 */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge.Permission[] getPermissions(int r13) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge.getPermissions(int):org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge$Permission[]");
    }

    @CalledByNative
    public static int getSettingFromPermission(Permission permission) {
        return permission.b;
    }

    @CalledByNative
    public static void setInstalledWebappProvider(long j) {
        a = j;
    }
}
