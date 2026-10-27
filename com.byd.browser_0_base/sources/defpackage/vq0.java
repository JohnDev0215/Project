package defpackage;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.graphics.Rect;
import android.util.Log;
import android.util.Rational;
import defpackage.rq0;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.notifications.NotificationIntentInterceptor;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vq0 {
    public static final a i = new a();
    public final LinkedList a = new LinkedList();
    public c b;
    public b c;
    public final Activity d;
    public final z2 e;
    public final rq0 f;
    public boolean g;
    public final boolean h;

    /* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
    public class a extends HashSet<String> {
        public a() {
            add(NotificationIntentInterceptor.TrampolineActivity.class.getName());
        }
    }

    /* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
    public class b implements rq0.a {
        public b() {
        }

        @Override // rq0.a
        public final void b(Tab tab) {
            vq0 vq0Var = vq0.this;
            vq0.a(vq0Var, vq0Var.d, 6);
        }
    }

    /* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
    public class c implements Callback<Tab> {
        public final Activity e;
        public Tab f;
        public d g;

        public c(Activity activity) {
            this.e = activity;
            z2 z2Var = vq0.this.e;
            this.f = (Tab) z2Var.f;
            z2Var.p(this);
            Tab tab = this.f;
            if (tab == null) {
                return;
            }
            this.g = vq0.this.new d(activity, tab);
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Tab tab) {
            Tab tab2 = tab;
            if (this.f == tab2) {
                return;
            }
            d dVar = this.g;
            if (dVar != null) {
                dVar.y1();
                Tab tab3 = dVar.f;
                if (tab3 != null) {
                    tab3.t(dVar);
                }
                this.g = null;
            }
            this.f = tab2;
            vq0 vq0Var = vq0.this;
            Activity activity = this.e;
            vq0.a(vq0Var, activity, 4);
            Tab tab4 = this.f;
            if (tab4 != null && tab4 != null) {
                this.g = vq0Var.new d(activity, tab4);
            }
            vq0Var.d();
        }
    }

    /* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
    public class d extends nh0 {
        public final Activity e;
        public final Tab f;
        public WebContents g;
        public e h;

        public d(Activity activity, Tab tab) {
            this.e = activity;
            this.f = tab;
            tab.x(this);
            if (tab == null) {
                return;
            }
            WebContents webContentsC = tab.c();
            this.g = webContentsC;
            if (webContentsC == null) {
                return;
            }
            this.h = vq0.this.new e(activity, webContentsC);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D0(Tab tab, boolean z) {
            vq0.a(vq0.this, this.e, 2);
            y1();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            if (tab != this.f) {
                return;
            }
            y1();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            vq0.a(vq0.this, this.e, 3);
            y1();
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            if (windowAndroid != null) {
                vq0.a(vq0.this, this.e, 5);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void e1(Tab tab) {
            vq0.a(vq0.this, this.e, 7);
            y1();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            Tab tab2 = this.f;
            if (tab != tab2) {
                return;
            }
            y1();
            if (tab2 == null) {
                return;
            }
            WebContents webContentsC = tab2.c();
            this.g = webContentsC;
            if (webContentsC == null) {
                return;
            }
            this.h = vq0.this.new e(this.e, webContentsC);
        }

        public final void y1() {
            e eVar = this.h;
            if (eVar == null) {
                return;
            }
            eVar.g.z(eVar);
            this.h = null;
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: FullscreenVideoPictureInPictureController.java */
    public class e extends rq3 {
        public final Activity f;
        public final WebContents g;

        public e(Activity activity, WebContents webContents) {
            this.f = activity;
            this.g = webContents;
            webContents.C(this);
        }

        @Override // defpackage.rq3
        public final void hasEffectivelyFullscreenVideoChange(boolean z) {
            vq0 vq0Var = vq0.this;
            if (!z) {
                vq0.a(vq0Var, this.f, 7);
            } else {
                a aVar = vq0.i;
                vq0Var.d();
            }
        }

        @Override // defpackage.rq3
        public final void mediaStartedPlaying() {
            a aVar = vq0.i;
            vq0.this.d();
        }

        @Override // defpackage.rq3
        public final void mediaStoppedPlaying() {
            a aVar = vq0.i;
            vq0.this.d();
        }
    }

    public vq0(Activity activity, z2 z2Var, qq0 qq0Var) {
        this.d = activity;
        this.e = z2Var;
        this.f = qq0Var;
        boolean zA = yj.a();
        this.h = zA;
        if (zA) {
            b();
        }
    }

    public static void a(vq0 vq0Var, Activity activity, int i2) {
        vq0Var.d();
        if (!vq0Var.a.isEmpty()) {
            activity.moveTaskToBack(true);
            vq0Var.c(i2);
        }
    }

    public final void b() {
        if (this.b == null) {
            this.b = new c(this.d);
        }
        if (this.c == null) {
            b bVar = new b();
            this.c = bVar;
            this.f.h(bVar);
        }
    }

    public final void c(int i2) {
        Log.i("cr_VideoPersist", "Exited picture in picture with reason: " + i2);
        LinkedList linkedList = this.a;
        if (!linkedList.isEmpty()) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            linkedList.clear();
            if (!this.h) {
                b bVar = this.c;
                if (bVar != null) {
                    this.f.c(bVar);
                    this.c = null;
                }
                c cVar = this.b;
                if (cVar != null) {
                    d dVar = cVar.g;
                    if (dVar != null) {
                        dVar.y1();
                        Tab tab = dVar.f;
                        if (tab != null) {
                            tab.t(dVar);
                        }
                        cVar.g = null;
                    }
                    cVar.f = null;
                    vq0.this.e.d(cVar);
                    this.b = null;
                }
            }
            oj2.U(i2, 8, "Media.VideoPersistence.EndReason");
        }
    }

    public final void d() {
        int i2;
        int i3;
        if (this.h) {
            z2 z2Var = this.e;
            Tab tab = (Tab) z2Var.f;
            Rect rect = null;
            WebContents webContentsC = tab == null ? null : tab.c();
            Activity activity = this.d;
            boolean z = (webContentsC == null ? (char) 7 : (!webContentsC.S() || !webContentsC.d0()) ? '\b' : !activity.getPackageManager().hasSystemFeature("android.software.picture_in_picture") ? (char) 2 : activity.isChangingConfigurations() ? (char) 5 : activity.isFinishing() ? (char) 6 : !k5.a(activity, i).isEmpty() ? '\t' : (char) 0) == 0;
            if (z == this.g) {
                return;
            }
            PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
            if (z) {
                Tab tab2 = (Tab) z2Var.f;
                Rect rectP = (tab2 == null ? null : tab2.c()).p();
                if (rectP != null && rectP.width() != 0 && rectP.height() != 0) {
                    float fO = a8.o(rectP.width() / rectP.height(), 0.41841003f, 2.39f);
                    int width = activity.getWindow().getDecorView().getWidth();
                    int height = activity.getWindow().getDecorView().getHeight();
                    float f = width;
                    float f2 = height;
                    if (fO > f / f2) {
                        i3 = (int) (f / fO);
                        i2 = width;
                    } else {
                        i2 = (int) (f2 * fO);
                        i3 = height;
                    }
                    int i4 = (width - i2) / 2;
                    int i5 = (height - i3) / 2;
                    rect = new Rect(i4, i5, i2 + i4, i3 + i5);
                }
                if (rect != null) {
                    builder.setAspectRatio(new Rational(rect.width(), rect.height()));
                    builder.setSourceRectHint(rect);
                }
                builder.setAutoEnterEnabled(true);
            } else {
                builder.setAutoEnterEnabled(false);
            }
            this.g = z;
            try {
                activity.setPictureInPictureParams(builder.build());
            } catch (RuntimeException e2) {
                Log.e("cr_VideoPersist", "Error setting PiP params", e2);
            }
        }
    }
}
