package org.chromium.components.paintpreview.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.a22;
import defpackage.es3;
import defpackage.f22;
import defpackage.g22;
import defpackage.gc3;
import defpackage.j22;
import defpackage.ke0;
import defpackage.l51;
import defpackage.nv1;
import defpackage.ve3;
import defpackage.w12;
import defpackage.y12;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.components.paintpreview.player.frame.PlayerFrameView;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PlayerManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
    public final Context a;
    public PlayerCompositorDelegateImpl b;
    public nv1 c;
    public w12 d;
    public final FrameLayout e;
    public final j22 f;
    public final f22 g;
    public final boolean h;
    public final InterfaceC0137b i;
    public long j;
    public WebContentsAccessibilityImpl k;

    /* JADX INFO: compiled from: PlayerManager.java */
    public static class a {
    }

    /* JADX INFO: renamed from: org.chromium.components.paintpreview.player.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PlayerManager.java */
    public interface InterfaceC0137b {
        boolean a();

        void b();

        void c();

        void d();

        void e();

        void f(int i);

        void g(GURL gurl);

        void h();

        void i();
    }

    static {
        new a();
    }

    public b(GURL gurl, Context context, PaintPreviewTabService paintPreviewTabService, String str, InterfaceC0137b interfaceC0137b, int i) {
        TraceEvent.q("PlayerManager", null);
        TraceEvent.I0(hashCode(), "paint_preview PlayerManager init");
        this.a = context;
        this.i = interfaceC0137b;
        this.h = false;
        this.b = new PlayerCompositorDelegateImpl(paintPreviewTabService, 0L, gurl, str, false, new ke0(1, this), new l51(17, interfaceC0137b));
        this.f = new j22(context, new g22(interfaceC0137b, 0));
        this.g = new f22(new gc3(interfaceC0137b), new es3(16, interfaceC0137b), new ve3(21, interfaceC0137b));
        FrameLayout frameLayout = new FrameLayout(context);
        this.e = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(i);
        frameLayout.setWillNotDraw(false);
        frameLayout.postInvalidate();
        TraceEvent.C("PlayerManager");
    }

    public final void a(w12 w12Var, nv1 nv1Var) {
        nv1[] nv1VarArr = nv1Var.d;
        if (nv1VarArr == null || nv1VarArr.length == 0) {
            return;
        }
        int i = 0;
        while (true) {
            nv1[] nv1VarArr2 = nv1Var.d;
            if (i >= nv1VarArr2.length) {
                return;
            }
            nv1 nv1Var2 = nv1VarArr2[i];
            w12 w12Var2 = new w12(this.a, this.b, nv1Var2.a, nv1Var2.b, nv1Var2.c, nv1Var2.f, nv1Var2.g, 0.0f, false, null, this.g, null, null, null);
            a(w12Var2, nv1Var2);
            Rect rect = nv1Var.e[i];
            w12Var.e.add(w12Var2);
            y12 y12Var = w12Var.a;
            ArrayList arrayList = y12Var.c;
            PlayerFrameView playerFrameView = w12Var2.d;
            arrayList.add(playerFrameView);
            y12Var.d.add(rect);
            ArrayList arrayList2 = y12Var.e;
            y12 y12Var2 = w12Var2.a;
            arrayList2.add(y12Var2);
            ArrayList arrayList3 = y12Var.f;
            arrayList3.add(new Rect());
            y12Var2.j = true;
            y12Var2.e(y12Var.l);
            PropertyModel.r<List<View>> rVar = a22.e;
            PropertyModel propertyModel = y12Var.g;
            propertyModel.p(rVar, arrayList);
            propertyModel.p(a22.f, arrayList3);
            playerFrameView.getGestureDetector().e = w12Var.d.getGestureDetector();
            i++;
        }
    }
}
