package defpackage;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabStateBrowserControlsVisibilityDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q33 extends nh0 {

    @SuppressLint({"HandlerLeak"})
    public final a e = new a();
    public final /* synthetic */ r33 f;

    /* JADX INFO: compiled from: TabStateBrowserControlsVisibilityDelegate.java */
    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            r33 r33Var;
            boolean z;
            if (message != null && message.what == 1 && (z = (r33Var = q33.this.f).j) && z) {
                r33Var.j = false;
                r33Var.u();
            }
        }
    }

    public q33(r33 r33Var) {
        this.f = r33Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        this.e.removeCallbacksAndMessages(null);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I(Tab tab, boolean z) {
        this.f.u();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i, Tab tab) {
        this.e.removeMessages(1);
        r33 r33Var = this.f;
        if (r33Var.j) {
            r33Var.j = false;
            r33Var.u();
        }
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.f.u();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        if (z && this.f.j) {
            a aVar = this.e;
            if (aVar.hasMessages(1)) {
                return;
            }
            aVar.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void R0(TabImpl tabImpl) {
        this.f.u();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        this.e.removeMessages(1);
        r33 r33Var = this.f;
        if (r33Var.j) {
            r33Var.j = false;
            r33Var.u();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        if (this.f.j) {
            a aVar = this.e;
            if (aVar.hasMessages(1)) {
                return;
            }
            aVar.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        this.e.removeMessages(1);
        boolean z = !a8.M(gurl);
        r33 r33Var = this.f;
        if (r33Var.j == z) {
            return;
        }
        r33Var.j = z;
        r33Var.u();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        this.f.u();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.g) {
            a aVar = this.e;
            aVar.removeMessages(1);
            aVar.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        WebContents webContentsC = tab.c();
        r33 r33Var = this.f;
        if (r33Var.i == webContentsC) {
            return;
        }
        r33Var.i = webContentsC;
        if (webContentsC == null) {
            return;
        }
        ImeAdapterImpl.b(webContentsC).m.add(r33Var);
    }
}
