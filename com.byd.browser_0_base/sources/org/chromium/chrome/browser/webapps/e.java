package org.chromium.chrome.browser.webapps;

import android.app.Activity;
import defpackage.ds3;
import defpackage.js3;
import defpackage.oj;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: WebappDeferredStartupWithStorageHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public final Activity a;
    public final String b;
    public final boolean c;
    public final ArrayList d = new ArrayList();

    /* JADX INFO: compiled from: WebappDeferredStartupWithStorageHandler.java */
    public interface a {
        void a(ds3 ds3Var, boolean z);
    }

    public e(Activity activity, oj ojVar) {
        this.a = activity;
        js3 js3VarM = ojVar.M();
        this.b = js3VarM != null ? js3VarM.a : null;
        this.c = ojVar.T();
    }

    public final void a(ds3 ds3Var, boolean z) {
        ArrayList arrayList = this.d;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(ds3Var, z);
        }
        arrayList.clear();
    }
}
