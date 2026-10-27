package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import defpackage.tp3;
import java.util.HashSet;
import java.util.concurrent.Callable;
import org.chromium.chrome.browser.customtabs.CustomTabBottomBarView;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z40 implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ z40(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        int i = this.a;
        boolean z = false;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                PendingIntent pendingIntent = (PendingIntent) obj;
                HashSet hashSet = CustomTabsConnection.o;
                d30 d30Var = ((d40) obj2).g.get();
                if (pendingIntent == null) {
                    CustomTabBottomBarView customTabBottomBarView = d30Var.m;
                    if (customTabBottomBarView != null) {
                        if (customTabBottomBarView != null) {
                            customTabBottomBarView.setSwipeHandler(null);
                            d30Var.p = null;
                        }
                    }
                    return Boolean.valueOf(z);
                }
                CustomTabBottomBarView customTabBottomBarView2 = d30Var.m;
                if (customTabBottomBarView2 != null) {
                    d30Var.p = pendingIntent;
                    customTabBottomBarView2.setSwipeHandler(d30Var);
                }
                z = true;
                return Boolean.valueOf(z);
            default:
                Context context = (Context) obj;
                for (tp3.a aVar : (tp3.a[]) obj2) {
                    context.unbindService(aVar);
                }
                return Boolean.TRUE;
        }
    }
}
