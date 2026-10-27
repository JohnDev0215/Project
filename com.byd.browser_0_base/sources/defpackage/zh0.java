package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.a;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.d;

/* JADX INFO: compiled from: EphemeralTabSheetContent.java */
/* JADX INFO: loaded from: classes.dex */
public final class zh0 implements ii {
    public final Runnable e;
    public final Runnable f;
    public final Runnable g;
    public final int h;
    public final gk2 i = new gk2();
    public final aq1<Boolean> j;
    public final Callback<ViewGroup> k;
    public ViewGroup l;
    public FrameLayout m;
    public WebContents n;
    public ContentView o;
    public ThinWebViewImpl p;
    public Drawable q;
    public ImageView r;

    public zh0(Context context, wq0 wq0Var, a aVar, wv wvVar, int i, d dVar, xr xrVar) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.j = aq1Var;
        this.e = wq0Var;
        this.f = aVar;
        this.g = wvVar;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.sheet_tab_toolbar_height);
        this.h = dimensionPixelSize;
        this.k = xrVar;
        this.p = new ThinWebViewImpl(context, new t83(), dVar);
        this.m = new FrameLayout(context);
        this.p.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, ((int) (i * 0.9f)) - dimensionPixelSize));
        this.m.addView(this.p.getView());
        this.m.setPadding(0, dimensionPixelSize, 0, 0);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.sheet_tab_toolbar, (ViewGroup) null);
        this.l = viewGroup;
        ((FadingShadowView) viewGroup.findViewById(R$id.shadow)).a(context.getColor(R$color.toolbar_shadow_color));
        int i2 = 6;
        ((ImageView) this.l.findViewById(R$id.open_in_new_tab)).setOnClickListener(new ie0(i2, this));
        this.l.findViewById(R$id.toolbar).setOnClickListener(new by1(7, this));
        this.l.findViewById(R$id.close).setOnClickListener(new vt(i2, this));
        ImageView imageView = (ImageView) this.l.findViewById(R$id.favicon);
        this.r = imageView;
        this.q = imageView.getDrawable();
        xrVar.onResult(this.l);
        aq1Var.r(Boolean.TRUE);
    }

    @Override // defpackage.ii
    public final void a() {
        this.g.run();
    }

    @Override // defpackage.ii
    public final View d() {
        return this.m;
    }

    @Override // defpackage.ii
    public final void destroy() {
        this.p.b();
        this.i.destroy();
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.l;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.ephemeral_tab_sheet_opened_half;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.ephemeral_tab_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final aq1<Boolean> l() {
        return this.j;
    }

    @Override // defpackage.ii
    public final int m() {
        WebContents webContents = this.n;
        if (webContents == null) {
            return 0;
        }
        return ((WebContentsImpl) webContents).l.b();
    }

    @Override // defpackage.ii
    public final float n() {
        return 0.0f;
    }

    @Override // defpackage.ii
    public final boolean o() {
        this.g.run();
        return true;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.ephemeral_tab_sheet_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.ephemeral_tab_sheet_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }
}
