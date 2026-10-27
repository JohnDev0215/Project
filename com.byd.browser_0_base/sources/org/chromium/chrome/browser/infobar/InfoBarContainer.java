package org.chromium.chrome.browser.infobar;

import J.N;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import defpackage.C0188h0;
import defpackage.bh0;
import defpackage.bq;
import defpackage.fj;
import defpackage.hj;
import defpackage.ji;
import defpackage.kj;
import defpackage.lk3;
import defpackage.nh0;
import defpackage.qo;
import defpackage.qq0;
import defpackage.rq0;
import defpackage.sk;
import defpackage.sq0;
import defpackage.t41;
import defpackage.tz0;
import defpackage.vi;
import defpackage.xz0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarContainerLayout;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class InfoBarContainer implements lk3, t41.b, InfoBar.a {
    public static final /* synthetic */ int u = 0;
    public final a e;
    public final b f;
    public final ArrayList<InfoBar> g;
    public final org.chromium.base.b<f> h;
    public final org.chromium.base.b<tz0> i;
    public final c j;
    public final d k;
    public final Tab l;
    public long m;
    public boolean n;
    public boolean o;
    public View p;
    public InfoBarContainerView q;
    public org.chromium.chrome.browser.infobar.b r;
    public vi s;
    public ji t;

    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
            InfoBarContainerView infoBarContainerView = InfoBarContainer.this.q;
            if (infoBarContainerView != null) {
                infoBarContainerView.setTranslationY(0.0f);
            }
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            InfoBarContainer infoBarContainer = InfoBarContainer.this;
            if (windowAndroid == null) {
                int i = InfoBarContainer.u;
                infoBarContainer.c();
            } else {
                int i2 = InfoBarContainer.u;
                infoBarContainer.e((Activity) tab.D().k().get());
                InfoBarContainer.b(infoBarContainer);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (navigationHandle.g) {
                InfoBarContainer infoBarContainer = InfoBarContainer.this;
                infoBarContainer.o = false;
                InfoBarContainerView infoBarContainerView = infoBarContainer.q;
                if (infoBarContainerView == null) {
                    return;
                }
                infoBarContainerView.setHidden(false);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            InfoBarContainer.b(InfoBarContainer.this);
        }
    }

    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            InfoBarContainerView infoBarContainerView = InfoBarContainer.this.q;
            if (infoBarContainerView == null) {
                return;
            }
            infoBarContainerView.k();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            InfoBarContainerView infoBarContainerView = InfoBarContainer.this.q;
            if (infoBarContainerView == null) {
                return;
            }
            infoBarContainerView.f();
        }
    }

    public class c implements InfoBarContainerView.a {
        public c() {
        }

        @Override // defpackage.tz0
        public final void a(int i) {
            Iterator<tz0> it = InfoBarContainer.this.i.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((tz0) aVar.next()).a(i);
                }
            }
        }

        @Override // defpackage.tz0
        public final void f(xz0 xz0Var) {
            Iterator<tz0> it = InfoBarContainer.this.i.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((tz0) aVar.next()).f(xz0Var);
                }
            }
        }
    }

    public class d implements rq0.a {
        public d() {
        }

        @Override // rq0.a
        public final void a(sq0 sq0Var, Tab tab) {
            int i = InfoBarContainer.u;
            InfoBarContainerView.setIsAllowedToAutoHide(false);
            InfoBarContainer.this.q.setTranslationY(0.0f);
        }

        @Override // rq0.a
        public final void b(Tab tab) {
            int i = InfoBarContainer.u;
            InfoBarContainerView.setIsAllowedToAutoHide(true);
        }
    }

    public interface f {
        void b();

        void c();

        void d(InfoBar infoBar);

        void e();
    }

    static {
        qo.f().a(new C0188h0.b() { // from class: uz0
            @Override // defpackage.C0188h0.b
            public final void y0(boolean z) {
                int i = InfoBarContainer.u;
                InfoBarContainerView.setIsAllowedToAutoHide(!z);
            }
        });
    }

    public InfoBarContainer(Tab tab) {
        a aVar = new a();
        this.e = aVar;
        this.f = new b();
        this.g = new ArrayList<>();
        this.h = new org.chromium.base.b<>();
        this.i = new org.chromium.base.b<>();
        this.j = new c();
        this.k = new d();
        tab.x(aVar);
        this.p = tab.getView();
        this.l = tab;
        Activity activity = (Activity) tab.D().k().get();
        if (activity != null) {
            e(activity);
        }
        this.m = N.MQNiH$D1(this);
    }

    public static void b(InfoBarContainer infoBarContainer) {
        if (infoBarContainer.q == null) {
            return;
        }
        Tab tab = infoBarContainer.l;
        WebContents webContentsC = tab.c();
        if (webContentsC != null && webContentsC != infoBarContainer.q.getWebContents()) {
            infoBarContainer.q.setWebContents(webContentsC);
            long j = infoBarContainer.m;
            if (j != 0) {
                N.Mb3PR8J$(j, infoBarContainer, webContentsC);
            }
        }
        View view = infoBarContainer.p;
        b bVar = infoBarContainer.f;
        if (view != null) {
            view.removeOnAttachStateChangeListener(bVar);
        }
        View view2 = tab.getView();
        infoBarContainer.p = view2;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(bVar);
        }
    }

    public static InfoBarContainer d(Tab tab) {
        return (InfoBarContainer) tab.B().b(InfoBarContainer.class);
    }

    @Override // t41.b
    public final void a(boolean z) {
        boolean z2 = this.q.getVisibility() == 0;
        if (z) {
            if (z2) {
                this.q.setVisibility(4);
            }
        } else {
            if (z2 || this.o) {
                return;
            }
            this.q.setVisibility(0);
        }
    }

    @CalledByNative
    public final void addInfoBar(InfoBar infoBar) {
        if (infoBar == null) {
            return;
        }
        ArrayList<InfoBar> arrayList = this.g;
        if (arrayList.contains(infoBar)) {
            return;
        }
        infoBar.k = this.q.getContext();
        infoBar.i = this;
        Iterator<f> it = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            }
            f fVar = (f) aVar.next();
            arrayList.isEmpty();
            fVar.e();
        }
        arrayList.add(infoBar);
        InfoBarContainerView infoBarContainerView = this.q;
        infoBarContainerView.getClass();
        infoBar.q();
        InfoBarContainerLayout infoBarContainerLayout = infoBarContainerView.q;
        ArrayList<xz0> arrayList2 = infoBarContainerLayout.g;
        int size = 0;
        while (true) {
            if (size >= arrayList2.size()) {
                size = arrayList2.size();
                break;
            } else if (infoBar.e() < arrayList2.get(size).e()) {
                break;
            } else {
                size++;
            }
        }
        arrayList2.add(size, infoBar);
        infoBarContainerLayout.l();
    }

    public final void c() {
        vi viVar;
        org.chromium.chrome.browser.infobar.b bVar = this.r;
        if (bVar != null) {
            this.i.c(bVar);
            this.h.c(this.r);
            this.r = null;
        }
        Tab tab = this.l;
        fj fjVarT = hj.t(tab.D());
        if (fjVarT != null) {
            sk skVar = bq.a;
            if (N.M09VlOh_("InfobarScrollOptimization")) {
                fjVarT.y.c(this.k);
            }
        }
        InfoBarContainerView infoBarContainerView = this.q;
        if (infoBarContainerView != null) {
            infoBarContainerView.setWebContents(null);
            long j = this.m;
            if (j != 0) {
                N.Mb3PR8J$(j, this, null);
            }
            InfoBarContainerView infoBarContainerView2 = this.q;
            kj kjVar = infoBarContainerView2.o;
            if (kjVar != null) {
                sk skVar2 = bq.a;
                if (N.M09VlOh_("InfobarScrollOptimization")) {
                    kjVar.s(infoBarContainerView2);
                }
            }
            infoBarContainerView2.f();
            this.q = null;
        }
        if (((Activity) tab.D().k().get()) != null && (viVar = this.s) != null) {
            this.t.e(viVar);
        }
        tab.D().m().h(this);
        View view = this.p;
        if (view != null) {
            view.removeOnAttachStateChangeListener(this.f);
            this.p = null;
        }
    }

    @Override // defpackage.lk3
    public final void destroy() {
        c();
        this.l.t(this.e);
        long j = this.m;
        if (j != 0) {
            N.MUX1cETi(j, this);
            this.m = 0L;
        }
        this.n = true;
    }

    public final void e(Activity activity) {
        Tab tab = this.l;
        fj fjVarT = hj.t(tab.D());
        this.q = new InfoBarContainerView(activity, this.j, fjVarT, DeviceFormFactor.b(tab.D()));
        if (fjVarT != null) {
            sk skVar = bq.a;
            if (N.M09VlOh_("InfobarScrollOptimization")) {
                qq0 qq0Var = fjVarT.y;
                d dVar = this.k;
                qq0Var.c(dVar);
                qq0Var.h(dVar);
            }
        }
        this.q.addOnAttachStateChangeListener(new e());
        this.q.setHidden(this.o);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(R$id.bottom_container);
        InfoBarContainerView infoBarContainerView = this.q;
        if (infoBarContainerView != null) {
            infoBarContainerView.setParentView(viewGroup);
        }
        org.chromium.chrome.browser.infobar.b bVar = new org.chromium.chrome.browser.infobar.b(new org.chromium.chrome.browser.infobar.a(activity, tab));
        this.r = bVar;
        this.i.a(bVar);
        this.h.a(this.r);
        tab.D().m().a(this);
    }

    @CalledByNative
    public final int getTopInfoBarIdentifier() {
        if (hasInfoBars()) {
            return this.g.get(0).n();
        }
        return -1;
    }

    @CalledByNative
    public boolean hasInfoBars() {
        return !this.g.isEmpty();
    }

    public class e implements View.OnAttachStateChangeListener {

        public class a extends bh0 {
            public a() {
            }

            @Override // defpackage.bh0, defpackage.vi
            public final void d(int i, int i2) {
                e eVar = e.this;
                if (InfoBarContainer.this.l.isHidden()) {
                    return;
                }
                InfoBarContainer.this.q.setVisibility(i == 3 ? 4 : 0);
            }
        }

        public e() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            InfoBarContainer infoBarContainer = InfoBarContainer.this;
            if (infoBarContainer.s == null) {
                infoBarContainer.s = new a();
                ji jiVarA = h.a(infoBarContainer.l.D());
                infoBarContainer.t = jiVarA;
                jiVarA.g(infoBarContainer.s);
            }
            Iterator<f> it = infoBarContainer.h.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                }
                f fVar = (f) aVar.next();
                infoBarContainer.g.isEmpty();
                fVar.b();
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
