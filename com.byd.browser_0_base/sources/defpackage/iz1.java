package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PaymentHandlerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iz1 implements ii {
    public final View e;
    public final FrameLayout f;
    public final View g;
    public final WebContents h;
    public final aq1<Boolean> i;
    public Runnable j;

    public iz1(Activity activity, WebContents webContents, View view, View view2) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.i = aq1Var;
        this.h = webContents;
        this.e = view;
        this.g = view2;
        int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R$dimen.sheet_tab_toolbar_height);
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(activity).inflate(R$layout.payment_handler_content, (ViewGroup) null);
        this.f = frameLayout;
        frameLayout.setPadding(0, dimensionPixelSize, 0, 0);
        frameLayout.addView(view2, 0);
        aq1Var.r(Boolean.TRUE);
    }

    @Override // defpackage.ii
    public final void a() {
        this.j.run();
    }

    @Override // defpackage.ii
    public final View d() {
        return this.f;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final boolean f() {
        return true;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.e;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.payment_handler_sheet_opened_half;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.payment_handler_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final aq1<Boolean> l() {
        return this.i;
    }

    @Override // defpackage.ii
    public final int m() {
        WebContents webContents = this.h;
        if (webContents == null) {
            return 0;
        }
        return ((WebContentsImpl) webContents).l.b();
    }

    @Override // defpackage.ii
    public final float n() {
        return 0.5f;
    }

    @Override // defpackage.ii
    public final boolean o() {
        this.j.run();
        return true;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.payment_handler_sheet_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.payment_handler_sheet_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        return 0.9f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
