package defpackage;

import android.app.Activity;
import android.util.SparseArray;
import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: SessionDataHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class bj2 {
    public final p61<CustomTabsConnection> a;
    public final SparseArray<a> b = new SparseArray<>();
    public d40 c;
    public xr d;

    /* JADX INFO: compiled from: SessionDataHolder.java */
    public static class a {
        public final CustomTabsSessionToken a;
        public final Class<? extends Activity> b;

        public a() {
            throw null;
        }

        public a(CustomTabsSessionToken customTabsSessionToken, Class cls) {
            this.a = customTabsSessionToken;
            this.b = cls;
        }
    }

    public bj2(p61<CustomTabsConnection> p61Var) {
        this.a = p61Var;
    }

    public final d40 a(CustomTabsSessionToken customTabsSessionToken) {
        CustomTabsSessionToken customTabsSessionTokenZ;
        d40 d40Var = this.c;
        if (d40Var == null || (customTabsSessionTokenZ = d40Var.e.z()) == null || !customTabsSessionTokenZ.equals(customTabsSessionToken)) {
            return null;
        }
        return this.c;
    }

    public final void b(d40 d40Var) {
        this.c = d40Var;
        CustomTabsSessionToken customTabsSessionTokenZ = d40Var.e.z();
        if (customTabsSessionTokenZ == null) {
            return;
        }
        SparseArray<a> sparseArray = this.b;
        Activity activity = d40Var.k;
        sparseArray.append(activity.getTaskId(), new a(customTabsSessionTokenZ, activity.getClass()));
        if (this.d != null) {
            return;
        }
        this.d = new xr(7, this);
        this.a.get().g = this.d;
    }
}
