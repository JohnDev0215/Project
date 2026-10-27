package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import defpackage.bk3;
import defpackage.bq;
import defpackage.cq3;
import defpackage.ds3;
import defpackage.eg1;
import defpackage.gq2;
import defpackage.gs3;
import defpackage.i9;
import defpackage.l51;
import defpackage.ls3;
import defpackage.oj2;
import defpackage.q2;
import defpackage.sk;
import defpackage.v80;
import defpackage.z2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.PathUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.webapps.WebApkUpdateDataFetcher;

/* JADX INFO: loaded from: classes2.dex */
public final class WebApkUpdateManager implements WebApkUpdateDataFetcher.a, v80 {
    public static int n;
    public final z2 e;
    public ls3 f;
    public eg1 g;
    public String h;
    public String i;
    public ArrayList j;
    public ds3 k;
    public WebApkUpdateDataFetcher l;
    public Handler m;

    public interface WebApkUpdateCallback {
        @CalledByNative
        void onResultFromNative(int i, boolean z);
    }

    public WebApkUpdateManager(z2 z2Var, q2 q2Var) {
        this.e = z2Var;
        q2Var.f(this);
    }

    public static String b(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (bk3.q(entry.getKey(), str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static String c(eg1 eg1Var, String str) {
        if (eg1Var == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(eg1Var.p());
        sb.append("|");
        sb.append(eg1Var.r());
        sb.append("|");
        sb.append(eg1Var.h().get(str));
        sb.append(eg1Var.k() ? "|Adaptive" : "|NotAdaptive");
        return sb.toString();
    }

    public static void g(ds3 ds3Var, int i, boolean z) {
        ds3Var.b.edit().putLong("last_update_request_complete_time", System.currentTimeMillis()).apply();
        boolean z2 = i == 0;
        SharedPreferences sharedPreferences = ds3Var.b;
        sharedPreferences.edit().putBoolean("did_last_update_request_succeed", z2).apply();
        sharedPreferences.edit().putBoolean("relax_updates", z).apply();
        if (n == 0) {
            n = N.MXmzBYjM();
        }
        sharedPreferences.edit().putInt("last_requested_shell_apk_version", n).apply();
    }

    public final void a(ls3 ls3Var, String str, String str2, boolean z, boolean z2, List<Integer> list) {
        ls3 ls3Var2 = this.f;
        eg1 eg1Var = this.g;
        sk skVar = bq.a;
        if (N.M09VlOh_("WebApkUniqueId") && eg1Var != null) {
            String strConcat = "WebApk.Update.UniqueId".concat(TextUtils.isEmpty(ls3Var2.m()) ? "Empty" : "Same");
            oj2.O(gq2.f(strConcat, ".ManifestUrl"), TextUtils.equals(ls3Var2.o(), eg1Var.o()));
            oj2.O(strConcat + ".StartUrl", TextUtils.equals(ls3Var2.n(), eg1Var.n()));
        }
        l51 l51Var = new l51(3, this);
        ds3 ds3Var = this.k;
        ds3Var.getClass();
        AtomicBoolean atomicBoolean = gs3.a;
        String path = new File(new File(PathUtils.getCacheDirectory(), "webapk/update"), ds3Var.a).getPath();
        ds3Var.b.edit().putString("pending_update_file_path", path).apply();
        new cq3(this, ls3Var, path, str, str2, z, z2, list, l51Var).c(i9.e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03d5  */
    /* JADX WARN: Type inference failed for: r0v4, types: [ds3] */
    /* JADX WARN: Type inference failed for: r1v28, types: [android.content.SharedPreferences$Editor] */
    /* JADX WARN: Type inference failed for: r1v31, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [int] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v48 */
    /* JADX WARN: Type inference failed for: r9v49 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(defpackage.ms3 r19, java.lang.String r20, java.lang.String r21) {
        /*
            Method dump skipped, instruction units count: 1359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.webapps.WebApkUpdateManager.d(ms3, java.lang.String, java.lang.String):void");
    }

    public final void e(int i) {
        g(this.k, 1, false);
        if (i == 1 || i == 5) {
            String strC = c(this.g, this.h);
            if (!strC.isEmpty()) {
                this.k.b.edit().putString("last_update_hash_accepted", strC).apply();
            }
            eg1 eg1Var = this.g;
            if (eg1Var == null) {
                ls3 ls3Var = this.f;
                sk skVar = bq.a;
                a(ls3Var, "", "", true, N.M09VlOh_("PwaUpdateDialogForName") || N.M09VlOh_("PwaUpdateDialogForIcon"), this.j);
            } else {
                String str = this.h;
                String str2 = this.i;
                sk skVar2 = bq.a;
                a(eg1Var, str, str2, false, N.M09VlOh_("PwaUpdateDialogForName") || N.M09VlOh_("PwaUpdateDialogForIcon"), this.j);
            }
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        WebApkUpdateDataFetcher webApkUpdateDataFetcher = this.l;
        if (webApkUpdateDataFetcher != null) {
            Tab tab = webApkUpdateDataFetcher.f;
            if (tab != null) {
                tab.t(webApkUpdateDataFetcher);
                N.MbkiXMEf(webApkUpdateDataFetcher.e, webApkUpdateDataFetcher);
                webApkUpdateDataFetcher.e = 0L;
            }
            this.l = null;
        }
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
