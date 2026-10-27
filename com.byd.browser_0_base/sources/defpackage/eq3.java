package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import defpackage.fc;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.webapps.WebappActivity;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: compiled from: WebApkUpdateTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eq3 extends fk1 {
    public ds3 f;
    public boolean g;

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        ds3 ds3VarC;
        WeakReference weakReference;
        ku2 ku2VarT = ku2.t();
        try {
            WebappRegistry webappRegistry = WebappRegistry.b.a;
            webappRegistry.d(null);
            ku2VarT.close();
            webappRegistry.getClass();
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : webappRegistry.b.entrySet()) {
                ds3 ds3Var = (ds3) entry.getValue();
                if (!TextUtils.isEmpty(ds3Var.b.getString("pending_update_file_path", null)) && zt1.e(ds3Var.b.getString("webapk_package_name", null))) {
                    arrayList.add((String) entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            do {
                if (!it.hasNext()) {
                    return arrayList.isEmpty() ? 2 : 1;
                }
                ds3VarC = WebappRegistry.b.a.c((String) it.next());
                String str = ds3VarC.a;
                Iterator it2 = ApplicationStatus.b().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        weakReference = null;
                        break;
                    }
                    Activity activity = (Activity) it2.next();
                    if (activity instanceof WebappActivity) {
                        BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) activity;
                        js3 js3VarM = baseCustomTabActivity.b1.M();
                        if (js3VarM != null && TextUtils.equals(str, js3VarM.a)) {
                            weakReference = new WeakReference(baseCustomTabActivity);
                            break;
                        }
                    }
                }
                if (weakReference == null) {
                    break;
                }
            } while (weakReference.get() != null);
            this.f = ds3VarC;
            this.g = arrayList.size() > 1;
            return 0;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        ds3 ds3Var = this.f;
        z8 z8Var = new z8(this, 29, aVar);
        Log.i("cr_WebApkUpdateManager", "Update now");
        du duVar = new du(ds3Var, z8Var);
        oj2.U(3, 4, "WebApk.Update.RequestSent");
        N.MEqkTChv(ds3Var.b.getString("pending_update_file_path", null), duVar);
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        return true;
    }
}
