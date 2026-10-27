package org.chromium.components.thinwebview.internal;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.g3;
import defpackage.s83;
import defpackage.t83;
import defpackage.xz;
import org.chromium.build.annotations.DoNotInline;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.c;

/* JADX INFO: loaded from: classes3.dex */
public class ThinWebViewImpl extends FrameLayout implements s83 {
    public final CompositorViewImpl e;
    public final WindowAndroid f;
    public long g;
    public WebContents h;
    public View i;

    @DoNotInline
    public WebContentsDelegateAndroid j;

    public ThinWebViewImpl(Context context, t83 t83Var, c cVar) {
        super(context);
        if (xz.a(context) != null) {
            this.f = new g3(context, true, cVar);
        } else {
            this.f = new WindowAndroid(context);
        }
        CompositorViewImpl compositorViewImpl = new CompositorViewImpl(context, this.f, t83Var);
        this.e = compositorViewImpl;
        addView(compositorViewImpl.a, new FrameLayout.LayoutParams(-1, -1));
        this.g = N.M$XqDO$W(this, compositorViewImpl, this.f);
    }

    private void setContentView(View view) {
        View view2 = this.i;
        if (view2 == view) {
            return;
        }
        if (view2 != null) {
            removeViewAt(1);
        }
        this.i = view;
        if (view != null) {
            addView(view, 1);
        }
    }

    public final void a(WebContents webContents, ContentView contentView, WebContentsDelegateAndroid webContentsDelegateAndroid) {
        if (this.g == 0) {
            return;
        }
        this.h = webContents;
        this.j = webContentsDelegateAndroid;
        setContentView(contentView);
        N.M9Q7LfVV(this.g, this, webContents, webContentsDelegateAndroid);
        webContents.Y();
    }

    public final void b() {
        if (this.g == 0) {
            return;
        }
        View view = this.i;
        if (view != null) {
            removeView(view);
            this.i = null;
        }
        CompositorViewImpl compositorViewImpl = this.e;
        long j = compositorViewImpl.b;
        if (j != 0) {
            N.M_L66GG1(j, compositorViewImpl);
            compositorViewImpl.b = 0L;
        }
        N.Mi0zHYZ4(this.g, this);
        this.g = 0L;
        this.f.destroy();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        long j = this.g;
        if (j == 0) {
            return;
        }
        if (i == i3 && i2 == i4) {
            return;
        }
        N.MgG98$5a(j, this, i, i2);
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        CompositorViewImpl compositorViewImpl = this.e;
        if (compositorViewImpl.b == 0) {
            return;
        }
        compositorViewImpl.a.setAlpha(f);
    }

    @Override // defpackage.s83
    public View getView() {
        return this;
    }
}
