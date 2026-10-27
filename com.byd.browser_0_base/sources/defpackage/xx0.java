package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.IncognitoCustomTabColorProvider;

/* JADX INFO: compiled from: IncognitoCustomTabIntentDataProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class xx0 extends oj {
    public final Intent a;
    public final CustomTabsSessionToken b;
    public final boolean c;
    public final Bundle d;
    public final IncognitoCustomTabColorProvider e;
    public final int f;
    public final za3 g;
    public final boolean h;
    public final ArrayList i = new ArrayList();
    public final String j;
    public final String k;
    public final boolean l;
    public final int m;

    public xx0(Context context, Intent intent) {
        ArrayList arrayListC;
        this.a = intent;
        this.j = b.o(intent);
        this.k = CustomTabsConnection.e().d(CustomTabsSessionToken.b(intent));
        CustomTabsSessionToken customTabsSessionTokenB = CustomTabsSessionToken.b(intent);
        this.b = customTabsSessionTokenB;
        this.c = t30.h0(intent, customTabsSessionTokenB);
        this.d = q11.j(intent, "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE");
        this.l = q11.g(intent);
        this.e = new IncognitoCustomTabColorProvider(context);
        this.g = za3.a(context, R$drawable.btn_close);
        this.h = q11.i(intent, "android.support.customtabs.extra.SHARE_MENU_ITEM", false);
        this.f = q11.l(intent, "android.support.customtabs.extra.TITLE_VISIBILITY", 0);
        this.m = e0(intent) ? 3 : 0;
        if (e0(intent) && (arrayListC = q11.c(intent, "android.support.customtabs.extra.MENU_ITEMS")) != null) {
            for (int i = 0; i < Math.min(5, arrayListC.size()); i++) {
                Bundle bundle = (Bundle) arrayListC.get(i);
                String strP = q11.p(bundle, "android.support.customtabs.customaction.MENU_ITEM_TITLE");
                PendingIntent pendingIntent = (PendingIntent) q11.m(bundle, "android.support.customtabs.customaction.PENDING_INTENT");
                if (!TextUtils.isEmpty(strP) && pendingIntent != null) {
                    this.i.add(new Pair(strP, pendingIntent));
                }
            }
        }
        if (bq.h.a()) {
            f50 f50Var = new f50();
            if (this.g != null) {
                f50Var.a(5);
            }
            if (this.d != null) {
                f50Var.a(13);
            }
            f50Var.a(15);
            if (this.i != null) {
                f50Var.a(20);
            }
            if (j() != null) {
                f50Var.a(22);
            }
            if (q11.s(intent, "org.chromium.chrome.browser.calling_activity_package")) {
                f50Var.a(21);
            }
            if (e0(intent)) {
                f50Var.a(26);
            }
            if (this.l) {
                f50Var.a(31);
            }
            if (this.h) {
                f50Var.a(33);
            }
            if (this.f != 0) {
                f50Var.a(35);
            }
        }
    }

    public static boolean e0(Intent intent) {
        return q11.g(intent) && q11.l(intent, "org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 0) == 3;
    }

    public static void f0(Intent intent) {
        if (TextUtils.isEmpty(CustomTabsConnection.e().d(CustomTabsSessionToken.b(intent)))) {
            return;
        }
        ((s50) pp.d()).b.getClass();
        zi0 zi0Var = zi0.b;
        a8.n(zi0Var);
        zi0Var.b();
    }

    @Override // defpackage.oj
    public final int E() {
        return this.f;
    }

    @Override // defpackage.oj
    public final int J() {
        return this.m;
    }

    @Override // defpackage.oj
    public final String K() {
        return this.j;
    }

    @Override // defpackage.oj
    public final boolean N() {
        return this.l;
    }

    @Override // defpackage.oj
    @Deprecated
    public final boolean R() {
        return this.c;
    }

    @Override // defpackage.oj
    public final boolean V() {
        return (this.d == null || j() == null) ? false : true;
    }

    @Override // defpackage.oj
    public final boolean a0() {
        return this.h;
    }

    @Override // defpackage.oj
    public final int d() {
        return 1;
    }

    @Override // defpackage.oj
    public final int f() {
        if (V()) {
            return this.d.getInt("android:activity.animEnterRes");
        }
        return 0;
    }

    @Override // defpackage.oj
    public final int g() {
        if (V()) {
            return this.d.getInt("android:activity.animExitRes");
        }
        return 0;
    }

    @Override // defpackage.oj
    public final String j() {
        Bundle bundle = this.d;
        if (bundle == null) {
            return null;
        }
        return bundle.getString("android:activity.packageName");
    }

    @Override // defpackage.oj
    public final Drawable k() {
        return this.g;
    }

    @Override // defpackage.oj
    public final cv m() {
        return this.e;
    }

    @Override // defpackage.oj
    public final Intent t() {
        return this.a;
    }

    @Override // defpackage.oj
    public final List<String> v() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) it.next()).first);
        }
        return arrayList;
    }

    @Override // defpackage.oj
    public final CustomTabsSessionToken z() {
        return this.b;
    }
}
