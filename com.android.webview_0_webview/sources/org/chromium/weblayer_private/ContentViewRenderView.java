package org.chromium.weblayer_private;

import J.N;
import android.content.Context;
import android.view.SurfaceView;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import defpackage.TP;
import defpackage.VP;
import defpackage.XP;
import defpackage.ZP;
import java.util.ArrayList;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentViewRenderView extends FrameLayout {
    public int A;
    public int B;
    public final ArrayList C;
    public final ZP s;
    public VP t;
    public VP u;
    public long v;
    public WindowAndroid w;
    public WebContents x;
    public int y;
    public int z;

    public ContentViewRenderView(Context context) {
        super(context);
        this.C = new ArrayList();
        ZP zp = new ZP(this, context);
        this.s = zp;
        addView(zp, new FrameLayout.LayoutParams(-1, -2));
        setBackgroundColor(-1);
    }

    public static final /* synthetic */ void a() {
    }

    private boolean didSwapFrame() {
        VP vp;
        VP vp2 = this.u;
        SurfaceView surfaceView = vp2.l;
        if (surfaceView != null && surfaceView.getBackground() != null) {
            vp2.l.post(new TP(vp2));
        }
        if (vp2.a != 0) {
            return false;
        }
        int i = vp2.m;
        if (i > 0) {
            vp2.m = i - 1;
        }
        if (vp2.m == 0 && (vp = vp2.i) != null) {
            vp.d();
            vp2.i = null;
        }
        return vp2.m > 0;
    }

    public void b(int i, ValueCallback valueCallback) {
        VP vp = this.t;
        if (vp != null && vp.a != i) {
            if (vp != this.u) {
                vp.e(false);
                this.t.d();
            }
            this.t = null;
        }
        if (this.t == null) {
            XP xp = new XP(this, null);
            VP vp2 = new VP(this, i, this.s, xp, this.y, new Runnable(this) { // from class: MP
                public final ContentViewRenderView s;

                {
                    this.s = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    long j = this.s.v;
                    if (j == 0) {
                        return;
                    }
                    N.M9bR5p1w(j);
                }
            });
            this.t = vp2;
            xp.a = vp2;
        }
        VP vp3 = this.t;
        vp3.p.add(valueCallback);
        if (vp3.e) {
            vp3.f();
        }
    }

    public final void c() {
        WebContents webContents = this.x;
        if (webContents == null) {
            return;
        }
        webContents.L(getWidth(), getHeight() - this.B);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        c();
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        WindowAndroid windowAndroid = this.w;
        if (windowAndroid == null) {
            return;
        }
        if (i == 8) {
            long j = windowAndroid.s;
            if (j == 0) {
                return;
            }
            N.MrnNdVRa(j, windowAndroid, false);
            return;
        }
        if (i == 0) {
            long j2 = windowAndroid.s;
            if (j2 == 0) {
                return;
            }
            N.MrnNdVRa(j2, windowAndroid, true);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        this.y = i;
        VP vp = this.t;
        if (vp != null && vp.a == 0) {
            vp.l.setBackgroundColor(i);
        }
        VP vp2 = this.u;
        if (vp2 == null || vp2.a != 0) {
            return;
        }
        vp2.l.setBackgroundColor(i);
    }
}
