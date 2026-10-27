package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.chrome.browser.dragdrop.ChromeDragAndDropBrowserDelegate;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabViewAndroidDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h53 extends ViewAndroidDelegate {
    public final TabImpl i;
    public ChromeDragAndDropBrowserDelegate j;
    public int k;
    public zp1<Integer> l;

    /* JADX INFO: compiled from: TabViewAndroidDelegate.java */
    public class a extends nh0 {
        public final /* synthetic */ Callback e;

        public a(dr drVar) {
            this.e = drVar;
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            Callback<Integer> callback = this.e;
            h53 h53Var = h53.this;
            if (windowAndroid != null) {
                h53Var.l = tab.D().m;
                h53Var.l.p(callback);
            } else {
                h53Var.l.d(callback);
                h53Var.l = null;
                h53Var.b();
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            h53.this.b();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void l0(int i, Tab tab) {
            h53.this.b();
        }
    }

    public h53(Tab tab, ContentView contentView) {
        super(contentView);
        TabImpl tabImpl = (TabImpl) tab;
        this.i = tabImpl;
        contentView.addOnDragListener(this.a);
        if (N.Mudil8Bg("TouchDragAndContextMenu")) {
            ChromeDragAndDropBrowserDelegate chromeDragAndDropBrowserDelegate = new ChromeDragAndDropBrowserDelegate(tab.getContext());
            this.j = chromeDragAndDropBrowserDelegate;
            this.a.m = chromeDragAndDropBrowserDelegate;
        }
        dr drVar = new dr(4, this);
        x7 x7Var = tab.D().m;
        this.l = x7Var;
        x7Var.p(drVar);
        tabImpl.x(new a(drVar));
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public final qd0 a() {
        return this.a;
    }

    public final void b() {
        zp1<Integer> zp1Var;
        TabImpl tabImpl = this.i;
        int iIntValue = (tabImpl.isHidden() || (zp1Var = this.l) == null) ? 0 : zp1Var.get().intValue();
        if (iIntValue == this.k) {
            return;
        }
        this.k = iIntValue;
        WebContents webContents = tabImpl.g;
        if (webContents == null || webContents.b0() == null) {
            return;
        }
        RenderWidgetHostViewImpl renderWidgetHostViewImplB0 = tabImpl.g.b0();
        long j = renderWidgetHostViewImplB0.a;
        if (j == 0) {
            throw new IllegalStateException("Native RenderWidgetHostViewAndroid already destroyed", renderWidgetHostViewImplB0.b);
        }
        N.Myd8R_Wn(j, renderWidgetHostViewImplB0);
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public final int getViewportInsetBottom() {
        return this.k;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public final void onBackgroundColorChanged(int i) {
        TabImpl tabImpl = this.i;
        Iterator<n23> it = tabImpl.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).A0(tabImpl);
            }
        }
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public final void onBottomControlsChanged(int i, int i2) {
        qy2 qy2VarY1 = qy2.y1(this.i);
        if (qy2VarY1.k && qy2VarY1.g == i && qy2VarY1.j == i2) {
            return;
        }
        qy2VarY1.g = i;
        qy2VarY1.j = i2;
        qy2VarY1.z1();
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public final void onTopControlsChanged(int i, int i2, int i3) {
        qy2 qy2VarY1 = qy2.y1(this.i);
        if (qy2VarY1.k && i == qy2VarY1.f && qy2VarY1.h == i2 && qy2VarY1.i == i3) {
            return;
        }
        qy2VarY1.f = i;
        qy2VarY1.h = i2;
        qy2VarY1.i = i3;
        qy2VarY1.z1();
    }
}
