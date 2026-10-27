package com.qtxa.qtxadefend;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f114a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final u f115b;
    String c = "";
    long d = 0;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ o f116a;

        a(o oVar) {
            this.f116a = oVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strE = this.f116a.e();
                if (!strE.equals(c.this.c) || System.currentTimeMillis() - c.this.d >= 60000) {
                    c.this.c = strE;
                    c.this.d = System.currentTimeMillis();
                    boolean zH = c.this.f115b.f().h(strE);
                    ApplicationInfo applicationInfo = null;
                    int iK = 0;
                    try {
                        applicationInfo = c.this.f115b.c().getPackageManager().getApplicationInfo(strE, 0);
                        if ((applicationInfo.flags & 1) != 0) {
                            return;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        w.d("isSystemApp NameNotFoundException: " + strE);
                    }
                    if (zH && applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                        if (!c.this.f115b.f().e(strE)) {
                            iK = c.this.e(b0.e(strE), this.f116a);
                            w.d("onRunPackage " + strE + " apk(hash) had been changed." + iK);
                        }
                        w.d("onRunPackage " + strE + " apk(hash) is ok." + iK);
                    } else {
                        iK = c.this.k(this.f116a, true);
                    }
                    if (iK == 1) {
                        w.d("onRunPackage BLOCK " + strE + " install package? " + zH);
                        c.this.f115b.h().d(strE);
                        return;
                    }
                    if (iK == 2) {
                        w.d("onRunPackage DELETE " + strE + " install package? " + zH);
                        c.this.f115b.h().d(strE);
                        c.this.f115b.h().c(strE);
                    }
                }
            } catch (Throwable unused2) {
            }
        }
    }

    c(u uVar) {
        this.f115b = uVar;
        this.f114a = uVar.e();
    }

    private void d(b0 b0Var, o oVar, int i) {
        if (i == 0 && b0Var.f112a.equals("ids_ducarsecurity_permission")) {
            try {
                this.f115b.f().l(oVar.e());
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(b0 b0Var, o oVar) {
        int i = this.f115b.g().i(b0Var);
        d(b0Var, oVar, i);
        w.d("[System_Action2] " + i);
        if (i == -1) {
            i = b0Var.c;
        }
        w.d("[System_Action3] " + i);
        return i;
    }

    private p g(b0 b0Var, o oVar) {
        return new p(e(b0Var, oVar));
    }

    public static boolean h(String str) {
        w.d("installerPkgIsOk:" + str);
        if (str != null && str.equals("com.ford.hmi.appstore")) {
            return true;
        }
        w.d("installerPkg_false:" + str);
        return false;
    }

    private p i(o oVar) {
        File file;
        String strA;
        boolean zC;
        try {
            file = new File(new URI(oVar.f()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            file = null;
        }
        if (file != null && file.isDirectory() && file.list().length > 0) {
            file = new File(file, "base.apk");
        }
        try {
            strA = oVar.a("installerPackageName");
            try {
                w.d("installerPackageName:" + strA);
            } catch (JSONException e2) {
                e = e2;
                w.d("installerPackageName:" + e.toString());
            }
        } catch (JSONException e3) {
            e = e3;
            strA = null;
        }
        if (h(strA)) {
            com.qtxa.qtxadefend.a.j().n(oVar.e(), file);
            zC = false;
        } else {
            zC = this.f114a.c(oVar.e(), file);
            w.d("onInstallPackage:block:" + zC);
        }
        if (!zC) {
            return p.a();
        }
        if (file != null) {
            try {
                file.getName();
                PackageInfo packageArchiveInfo = this.f115b.c().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
                str = packageArchiveInfo != null ? packageArchiveInfo.packageName : null;
                oVar.g("packageName", packageArchiveInfo != null ? packageArchiveInfo.packageName : "null");
            } catch (Exception e4) {
                w.a("URISyntaxException:" + e4.toString() + " " + file);
            }
        }
        b0 b0VarC = b0.c(str, oVar.f(), oVar.b(this.f115b.c()));
        p pVarG = g(b0VarC, oVar);
        w.a("onInstallPackage:" + b0VarC + " ");
        w.a("onInstallPackage:" + oVar + " ");
        w.a("onInstallPackage:" + pVarG + " ");
        return pVarG;
    }

    private p j(o oVar) {
        this.f115b.d().a(new a(oVar));
        return p.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int k(o oVar, boolean z) {
        String strE = oVar.e();
        if (z && this.f114a.d(strE)) {
            return e(b0.f(strE), oVar);
        }
        if (this.f115b.f().i(strE) || !this.f115b.g().g()) {
            return 0;
        }
        List<String> listD = i.d(this.f115b.c(), strE);
        if (listD.isEmpty()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = listD.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append('+');
        }
        sb.deleteCharAt(sb.length() - 1);
        return e(b0.d(strE, sb.toString()), oVar);
    }

    private p l(o oVar) {
        String strE = oVar.e();
        return !(!h(this.f115b.c().getPackageManager().getInstallerPackageName(strE)) ? this.f114a.e(strE) : false) ? p.a() : g(b0.h(strE, oVar.b(this.f115b.c())), oVar);
    }

    private p m(String str) {
        w.a("onUnknow : " + str);
        return new p(0);
    }

    private p n(o oVar) {
        this.f115b.b().g(new t(oVar.a("config")));
        return p.a();
    }

    String f(String str) {
        try {
            o oVar = new o(new JSONObject(str));
            String strC = oVar.c();
            return (strC == null || !strC.equals("installPackage")) ? (strC == null || !strC.equals("uninstallPackage")) ? (strC == null || !strC.equals("runPackage")) ? (strC == null || !strC.equals("updateConfig")) ? m(str).toString() : n(oVar).toString() : j(oVar).toString() : l(oVar).toString() : i(oVar).toString();
        } catch (Exception e) {
            w.f(e.toString());
            return m(str).toString();
        }
    }

    public void o(String str) {
        try {
            o oVar = new o(new JSONObject());
            oVar.g("cmd", "runPackage");
            oVar.g("packageName", str);
            j(oVar);
        } catch (JSONException unused) {
        }
    }
}
