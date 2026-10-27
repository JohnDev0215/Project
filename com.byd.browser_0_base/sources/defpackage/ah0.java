package defpackage;

import android.app.Activity;
import android.view.ViewStub;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;

/* JADX INFO: compiled from: EmptyBackgroundViewWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ah0 {
    public final Activity a;
    public final y13 b;
    public final fz2 c;
    public final xg0 d;
    public final yg0 e;
    public final wp2 f;
    public ll g;
    public z51 h;
    public EmptyBackgroundViewTablet i;
    public final h7 j;

    public ah0(y13 y13Var, fz2 fz2Var, AppCompatActivity appCompatActivity, h7 h7Var, wp2 wp2Var, aq1 aq1Var) {
        ll llVar = new ll();
        this.g = llVar;
        this.a = appCompatActivity;
        this.j = h7Var;
        this.b = y13Var;
        this.c = fz2Var;
        this.f = wp2Var;
        aq1Var.p(llVar.b(new jb1(26, this)));
        this.d = new xg0(this);
        this.e = new yg0(this);
    }

    public static void a(ah0 ah0Var) {
        z51 z51Var;
        y13 y13Var = ah0Var.b;
        boolean z = false;
        TabModel tabModelG = y13Var.g(false);
        Activity activity = ah0Var.a;
        if (tabModelG != null && !oj2.I(activity)) {
            boolean z2 = y13Var.g(true).getCount() == 0;
            boolean zR = y13Var.r();
            if (tabModelG.getCount() == 0 && (((z51Var = ah0Var.h) == null || !z51Var.q(2)) && (!zR || z2))) {
                z = true;
            }
        }
        if (z && ah0Var.i == null) {
            EmptyBackgroundViewTablet emptyBackgroundViewTablet = (EmptyBackgroundViewTablet) ((ViewStub) activity.findViewById(R$id.empty_container_stub)).inflate();
            ah0Var.i = emptyBackgroundViewTablet;
            emptyBackgroundViewTablet.setTabModelSelector(y13Var);
            ah0Var.i.setTabCreator(ah0Var.c);
            h7 h7Var = ah0Var.j;
            if (h7Var != null) {
                ah0Var.i.setMenuOnTouchListener(h7Var);
            }
            ah0Var.i.addOnAttachStateChangeListener(new zg0(ah0Var));
        }
        EmptyBackgroundViewTablet emptyBackgroundViewTablet2 = ah0Var.i;
        if (emptyBackgroundViewTablet2 != null) {
            emptyBackgroundViewTablet2.setEmptyContainerState(z);
            EmptyBackgroundViewTablet emptyBackgroundViewTablet3 = z ? ah0Var.i : null;
            yp2 yp2Var = ah0Var.f.f;
            if (yp2Var != null) {
                yp2Var.b(emptyBackgroundViewTablet3);
            }
        }
    }
}
