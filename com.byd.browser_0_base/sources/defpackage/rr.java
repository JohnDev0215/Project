package defpackage;

import J.N;
import android.R;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rr implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ rr(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Tab tab;
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                g63 g63Var = (g63) chromeTabbedActivity.N0;
                if (g63Var.g == null || (tab = (Tab) g63Var.k.f) == null || tab.c() == null || !tab.isUserInteractable()) {
                    return;
                }
                gl1 gl1Var = new gl1(g63Var.g.getWindow().getDecorView().findViewById(R.id.content), g63Var.g, new c63(g63Var, 0), Profile.b(tab.c()));
                g63Var.V0 = gl1Var;
                gl1Var.n = new j63(tab, new d63(0, g63Var), g63Var.g.getResources().getString(R$string.show_full_history));
                if (g63Var.V0.b()) {
                    g63Var.B.g(new f63(g63Var));
                    return;
                } else {
                    g63Var.V0 = null;
                    return;
                }
            case 1:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.q3();
                return;
            default:
                n3 n3Var3 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.getClass();
                TraceEvent traceEventH0 = TraceEvent.H0("ChromeTabbedActivity.maybeGetFeedAppLifecycleAndMaybeCreatePageViewObserver", null);
                try {
                    ul0 ul0VarB = ul0.b();
                    if (!ul0VarB.a) {
                        ul0VarB.a = true;
                        N.MEQNDUUr();
                        HashSet<sw2> hashSet = ul0VarB.c;
                        if (hashSet != null) {
                            Iterator<sw2> it = hashSet.iterator();
                            while (it.hasNext()) {
                                it.next().b();
                            }
                        }
                    }
                    fk3.a(chromeTabbedActivity, chromeTabbedActivity.G0, chromeTabbedActivity.o0);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                            break;
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
        }
    }
}
