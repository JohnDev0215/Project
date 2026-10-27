package defpackage;

import android.content.Context;
import android.os.SystemClock;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.fy2;
import defpackage.mo0;
import defpackage.na0;
import defpackage.yz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import mo0.a;
import org.chromium.base.BundleUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.background_task_scheduler.ChromeBackgroundTaskFactory;
import org.chromium.chrome.browser.base.SplitCompatApplication;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;

/* JADX INFO: compiled from: ChromeApplicationImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class pp extends SplitCompatApplication.b {
    public static final Object b = new Object();
    public static volatile s50 c;

    public static mp d() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new s50(new op(), new a8());
                }
            }
        }
        return c;
    }

    public static boolean e(int i) {
        return (i >= 10 && i < 20) || i >= 60;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication.b
    public final void a() {
        if (!SplitCompatApplication.d()) {
            return;
        }
        fy2 fy2VarA = fy2.a();
        boolean z = fy2VarA.b;
        boolean z2 = (xz.a.getResources().getConfiguration().uiMode & 48) == 32;
        fy2VarA.b = z2;
        if (z == z2) {
            return;
        }
        Iterator<fy2.a> it = fy2VarA.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((fy2.a) aVar.next()).a();
            }
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication.b
    public final void b() {
        if (SplitCompatApplication.d()) {
            mo0 mo0VarA = mo0.a();
            SplitCompatApplication splitCompatApplication = this.a;
            mo0VarA.a.getClass();
            if (!mo0VarA.c) {
                mo0VarA.c = true;
                Context applicationContext = splitCompatApplication.getApplicationContext();
                mo0.a aVar = mo0VarA.new a();
                for (Integer num : mo0VarA.b) {
                    rb2.b(applicationContext, num.intValue(), aVar);
                }
                mo0VarA.e = SystemClock.elapsedRealtime();
            }
            bq.x.a();
            Object obj = BundleUtils.a;
            ChromeBackgroundTaskFactory.setAsDefault();
            vd1.j = yz.a.a;
            qw1.a.a(new k62());
            AppHooks.get().getClass();
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication.b
    public final void c(int i) {
        na0 na0Var;
        if (e(i) && (na0Var = ys0.a) != null) {
            Set<na0.a<?>> set = na0Var.a;
            Iterator<na0.a<?>> it = set.iterator();
            while (it.hasNext()) {
                it.next().a = null;
            }
            set.clear();
        }
        if ((CustomTabsConnection.r != null) && e(i)) {
            org.chromium.chrome.browser.customtabs.a aVar = CustomTabsConnection.e().c;
            synchronized (aVar) {
                for (CustomTabsSessionToken customTabsSessionToken : new ArrayList(aVar.c.keySet())) {
                    if (((a.e) aVar.c.get(customTabsSessionToken)).b == null) {
                        du duVar = new du(aVar, customTabsSessionToken);
                        synchronized (aVar) {
                            a.e eVar = (a.e) aVar.c.get(customTabsSessionToken);
                            if (eVar != null) {
                                duVar.d(eVar);
                            }
                        }
                    }
                }
            }
        }
    }
}
