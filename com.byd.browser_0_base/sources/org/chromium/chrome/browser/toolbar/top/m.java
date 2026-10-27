package org.chromium.chrome.browser.toolbar.top;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ad3;
import defpackage.af1;
import defpackage.ao3;
import defpackage.aq1;
import defpackage.ar;
import defpackage.as2;
import defpackage.bn0;
import defpackage.bq;
import defpackage.bs2;
import defpackage.bz0;
import defpackage.cf1;
import defpackage.ck;
import defpackage.cs2;
import defpackage.d92;
import defpackage.df1;
import defpackage.dr;
import defpackage.e7;
import defpackage.fc3;
import defpackage.ff1;
import defpackage.fs;
import defpackage.g90;
import defpackage.gm;
import defpackage.h21;
import defpackage.h43;
import defpackage.ii2;
import defpackage.jb1;
import defpackage.jj;
import defpackage.jj2;
import defpackage.jw1;
import defpackage.k81;
import defpackage.ll;
import defpackage.ms1;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.on0;
import defpackage.pd;
import defpackage.pd3;
import defpackage.pk3;
import defpackage.pr1;
import defpackage.q43;
import defpackage.qb3;
import defpackage.qh1;
import defpackage.r43;
import defpackage.rb2;
import defpackage.s20;
import defpackage.sr;
import defpackage.tr;
import defpackage.tx;
import defpackage.u7;
import defpackage.v62;
import defpackage.vj;
import defpackage.vt;
import defpackage.wm3;
import defpackage.x10;
import defpackage.xd3;
import defpackage.y13;
import defpackage.yo;
import defpackage.yp;
import defpackage.yq2;
import defpackage.z2;
import defpackage.z51;
import defpackage.z6;
import defpackage.z7;
import defpackage.zp1;
import defpackage.zs1;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.NewTabButton;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TopToolbarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements qb3 {
    public final ToolbarLayout a;
    public final h b;
    public final d c;
    public zs1 d;
    public final af1 e;
    public zp1<z6> f;
    public zp1<y13> g;
    public ToolbarControlContainer h;
    public final nw2<ResourceManager> i;
    public pd3 j;
    public boolean k;
    public final i l;

    /* JADX INFO: compiled from: TopToolbarCoordinator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TopToolbarCoordinator.java */
    public interface b {
        void c(int i);
    }

    /* JADX INFO: compiled from: TopToolbarCoordinator.java */
    public interface c {
        void b();
    }

    public m(ToolbarControlContainer toolbarControlContainer, ViewStub viewStub, ToolbarLayout toolbarLayout, LocationBarModel locationBarModel, ad3 ad3Var, pk3 pk3Var, List list, ThemeColorProvider themeColorProvider, u7 u7Var, af1 af1Var, af1 af1Var2, aq1 aq1Var, zp1 zp1Var, aq1 aq1Var2, ck ckVar, on0 on0Var, jj2 jj2Var, fs fsVar, bn0 bn0Var, boolean z, boolean z2, boolean z3, boolean z4, pd pdVar, s20 s20Var, qh1 qh1Var, boolean z5, ll.b bVar, boolean z6, g.a aVar, zp1 zp1Var2, vj vjVar, boolean z7) {
        this.h = toolbarControlContainer;
        this.a = toolbarLayout;
        this.e = af1Var;
        this.d = new zs1(list, pk3Var, toolbarLayout, new yo(15, locationBarModel));
        this.i = fsVar;
        this.g = zp1Var;
        i iVar = new i(toolbarLayout.getContext(), toolbarLayout);
        this.l = iVar;
        toolbarLayout.setToolbarColorObserver(iVar);
        boolean z8 = toolbarLayout instanceof ToolbarPhone;
        if (z8 && z3) {
            this.c = new d(viewStub, pk3Var, ckVar, u7Var, af1Var2, jj2Var, z, z2, z4, bn0Var, bVar, z6, z7, new x10(9, this), iVar);
        } else if (z8 || bq.B.a()) {
            this.b = new h(viewStub, af1Var2, z, z2, bn0Var, iVar);
        }
        Objects.requireNonNull(locationBarModel);
        yq2 yq2Var = new yq2(20, locationBarModel);
        toolbarControlContainer.g = this;
        s20 s20Var2 = new s20(7, toolbarControlContainer);
        ToolbarControlContainer.b bVar2 = (ToolbarControlContainer.b) toolbarControlContainer.h.getResourceAdapter();
        bVar2.q = this;
        bVar2.r = g();
        bVar2.s = new tx(bVar2, aVar, Looper.getMainLooper());
        bVar2.t = yq2Var;
        bVar2.u = zp1Var2;
        zp1Var2.p(bVar2.p);
        bVar2.v = vjVar;
        bVar2.w = s20Var2;
        int i = 1;
        if (toolbarControlContainer.findViewById(R$id.toolbar) instanceof ToolbarTablet) {
            if (bq.f0.a()) {
                Context context = toolbarControlContainer.getContext();
                ColorDrawable colorDrawable = new ColorDrawable(z5 ? yp.b(context, z5) : yp.c(context, R$dimen.default_elevation_0));
                Resources resources = toolbarControlContainer.getContext().getResources();
                int i2 = oj2.F() ? R$drawable.bg_tabstrip_tab_folio : R$drawable.bg_tabstrip_tab_detached;
                Resources.Theme theme = toolbarControlContainer.getContext().getTheme();
                ThreadLocal<TypedValue> threadLocal = rb2.a;
                Drawable drawable = resources.getDrawable(i2, theme);
                Context context2 = toolbarControlContainer.getContext();
                drawable.setTint((bq.d.a() || !z7.I(context2)) ? yp.c(context2, R$dimen.default_elevation_5) : z7.o(ii2.b(context2), yp.c(context2, R$dimen.default_elevation_0), 0.85f, false));
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{colorDrawable, drawable});
                layerDrawable.setPadding(0, 0, 0, 0);
                layerDrawable.setLayerSize(1, ao3.a(toolbarControlContainer.getContext(), 265.0f), toolbarControlContainer.g.g());
                layerDrawable.setLayerGravity(1, 8388611);
                toolbarControlContainer.setBackground(layerDrawable);
            } else {
                Drawable drawableMutate = AppCompatResources.a(toolbarControlContainer.getContext(), R$drawable.toolbar_background).mutate();
                drawableMutate.setTint(yp.a(toolbarControlContainer.getContext(), z5));
                drawableMutate.setTintMode(PorterDuff.Mode.MULTIPLY);
                toolbarControlContainer.setBackground(drawableMutate);
            }
        }
        toolbarLayout.j(locationBarModel, ad3Var, af1Var, pdVar, s20Var, qh1Var);
        toolbarLayout.setThemeColorProvider(themeColorProvider);
        this.f = aq1Var;
        new ms1(aq1Var, new fc3(this, i));
        aq1Var2.p(new dr(21, this));
        toolbarLayout.setInvalidatorCallback(on0Var);
    }

    @Override // defpackage.qb3
    public final int a() {
        return this.a.getToolbarDataProvider().a();
    }

    public void addOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.a.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    @Override // defpackage.qb3
    public final boolean b() {
        return this.a.getVisibility() == 0;
    }

    @Override // defpackage.qb3
    public final boolean c(boolean z) {
        return this.a.A(z);
    }

    @Override // defpackage.qb3
    public final void d(boolean z) {
        this.a.J(z);
    }

    @Override // defpackage.qb3
    public final boolean e() {
        return this.a.C();
    }

    @Override // defpackage.qb3
    public final void f(boolean z) {
        this.a.setHairlineVisibility(z);
    }

    @Override // defpackage.qb3
    public final int g() {
        return this.a.getTabStripHeight();
    }

    @Override // defpackage.qb3
    public final int getHeight() {
        return this.a.getHeight();
    }

    @Override // defpackage.qb3
    public final void h(Rect rect) {
        this.a.f(rect);
    }

    @Override // defpackage.qb3
    public final ToolbarProgressBar i() {
        return this.a.getProgressBar();
    }

    @Override // defpackage.qb3
    public final void j(View view, int[] iArr) {
        ToolbarLayout toolbarLayout = this.a;
        toolbarLayout.getClass();
        ao3.b(view, toolbarLayout, iArr);
    }

    @Override // defpackage.qb3
    public final gm k() {
        return this.a.l();
    }

    @Override // defpackage.qb3
    public final void l(boolean z) {
        this.a.setTextureCaptureMode(z);
    }

    public final void m() {
        this.a.b();
    }

    public final void n(d92 d92Var, sr srVar, sr srVar2, vt vtVar, tr trVar, vt vtVar2, e7 e7Var, z51 z51Var, z2 z2Var, jj jjVar, xd3 xd3Var) {
        final on0 on0Var = new on0(28, e7Var);
        h hVar = this.b;
        if (hVar != null) {
            hVar.setOnTabSwitcherClickHandler(srVar);
            hVar.setOnNewTabClickHandler(srVar2);
            y13 y13Var = this.g.get();
            hVar.f = y13Var;
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = hVar.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.setTabModelSelector(y13Var);
            }
            hVar.c();
            hVar.d();
        } else {
            d dVar = this.c;
            if (dVar != null) {
                dVar.setOnNewTabClickHandler(srVar2);
                y13 y13Var2 = this.g.get();
                dVar.g = y13Var2;
                g gVar = dVar.a;
                gVar.v = y13Var2;
                boolean zT = y13Var2.t();
                PropertyModel propertyModel = gVar.e;
                if (zT && gVar.l.getAsBoolean()) {
                    propertyModel.p(cs2.r, gVar.w);
                    propertyModel.p(cs2.s, gVar.v);
                }
                propertyModel.l(cs2.i, gVar.v.r());
                gVar.d(gVar.i.get());
                gVar.v.m(gVar.n);
                gVar.v.h(gVar.o);
                dVar.setTabSwitcherListener(srVar);
                final as2 as2Var = new as2();
                dVar.setOnTabSwitcherLongClickHandler(new View.OnLongClickListener() { // from class: e43
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        Context context = view.getContext();
                        ListMenuButton listMenuButton = (ListMenuButton) view;
                        ya1 ya1VarB = as2Var.b();
                        final ad0 ad0Var = new ad0(on0Var, 6);
                        nn3 nn3VarN0 = n3.n0(listMenuButton);
                        zd zdVar = new zd(context, ya1VarB, new k81.a() { // from class: f43
                            @Override // k81.a
                            public final void a(PropertyModel propertyModel2) {
                                ad0Var.onResult(Integer.valueOf(propertyModel2.h(o81.f)));
                            }
                        });
                        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.tab_switcher_menu_vertical_padding);
                        ListView listView = zdVar.e;
                        listView.setPaddingRelative(listView.getPaddingStart(), dimensionPixelOffset, listView.getPaddingEnd(), dimensionPixelOffset);
                        listMenuButton.d(new g43(zdVar, nn3VarN0), false);
                        listMenuButton.f();
                        return true;
                    }
                });
                dVar.p = true;
                gVar.B = true;
                org.chromium.chrome.browser.logo.a aVar = gVar.C;
                if (aVar != null) {
                    aVar.b();
                }
            }
        }
        y13 y13Var3 = this.g.get();
        ToolbarLayout toolbarLayout = this.a;
        toolbarLayout.setTabModelSelector(y13Var3);
        toolbarLayout.getLocationBar().p();
        toolbarLayout.setOnTabSwitcherClickHandler(srVar);
        final h43 h43Var = new h43();
        toolbarLayout.setOnTabSwitcherLongClickHandler(new View.OnLongClickListener() { // from class: e43
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                Context context = view.getContext();
                ListMenuButton listMenuButton = (ListMenuButton) view;
                ya1 ya1VarB = h43Var.b();
                final ad0 ad0Var = new ad0(on0Var, 6);
                nn3 nn3VarN0 = n3.n0(listMenuButton);
                zd zdVar = new zd(context, ya1VarB, new k81.a() { // from class: f43
                    @Override // k81.a
                    public final void a(PropertyModel propertyModel2) {
                        ad0Var.onResult(Integer.valueOf(propertyModel2.h(o81.f)));
                    }
                });
                int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.tab_switcher_menu_vertical_padding);
                ListView listView = zdVar.e;
                listView.setPaddingRelative(listView.getPaddingStart(), dimensionPixelOffset, listView.getPaddingEnd(), dimensionPixelOffset);
                listMenuButton.d(new g43(zdVar, nn3VarN0), false);
                listMenuButton.f();
                return true;
            }
        });
        toolbarLayout.setBookmarkClickHandler(vtVar);
        toolbarLayout.setFolderClickHandler(trVar);
        toolbarLayout.setCustomTabCloseClickHandler(vtVar2);
        toolbarLayout.setLayoutUpdater(d92Var);
        toolbarLayout.q();
        if (g90.c().f) {
            Context context = toolbarLayout.getContext();
            ToolbarControlContainer toolbarControlContainer = this.h;
            Objects.requireNonNull(toolbarControlContainer);
            pd3 pd3Var = new pd3(context, z51Var, new jb1(16, toolbarControlContainer), z2Var, jjVar, this.i, xd3Var, 11, false);
            this.j = pd3Var;
            z51Var.h(pd3Var);
            toolbarLayout.setOverlayCoordinator(this.j);
        }
    }

    public final void o(boolean z) {
        this.a.y(z);
    }

    public final void p(z6 z6Var) {
        this.a.setAppMenuButtonHelper(z6Var);
    }

    public final void q(org.chromium.chrome.browser.toolbar.a aVar) {
        this.a.setTabCountProvider(aVar);
        h hVar = this.b;
        if (hVar != null) {
            hVar.e = aVar;
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = hVar.j;
            if (customTabSwitcherModeTopToolbar != null) {
                customTabSwitcherModeTopToolbar.setTabCountProvider(aVar);
            }
        }
        d dVar = this.c;
        if (dVar != null) {
            org.chromium.chrome.browser.toolbar.c cVar = dVar.h;
            if (cVar != null) {
                cVar.d = aVar;
                cVar.a.l(r43.e, true);
                org.chromium.chrome.browser.toolbar.b bVar = new org.chromium.chrome.browser.toolbar.b(cVar);
                cVar.e = bVar;
                cVar.d.a(bVar);
            } else {
                dVar.j = aVar;
            }
            dVar.a.w = aVar;
        }
    }

    public final void r(boolean z, boolean z2, boolean z3) {
        CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar;
        this.a.B(z);
        h hVar = this.b;
        if (hVar != null) {
            if (!z) {
                CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar2 = hVar.j;
                if (customTabSwitcherModeTopToolbar2 != null) {
                    customTabSwitcherModeTopToolbar2.setTabSwitcherMode(z);
                    return;
                }
                return;
            }
            ViewStub viewStub = hVar.a;
            if (DeviceFormFactor.a(viewStub.getContext())) {
                if (!g90.a(viewStub.getContext())) {
                    if (hVar.l == null) {
                        CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar3 = (CustomTabSwitcherModeTopToolbar) hVar.b.inflate();
                        hVar.l = customTabSwitcherModeTopToolbar3;
                        hVar.a(customTabSwitcherModeTopToolbar3, true);
                    }
                    customTabSwitcherModeTopToolbar = hVar.l;
                } else {
                    if (hVar.k == null) {
                        CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar4 = (CustomTabSwitcherModeTopToolbar) viewStub.inflate();
                        hVar.k = customTabSwitcherModeTopToolbar4;
                        hVar.a(customTabSwitcherModeTopToolbar4, false);
                    }
                    customTabSwitcherModeTopToolbar = hVar.k;
                }
                if (hVar.j != customTabSwitcherModeTopToolbar) {
                    hVar.j = customTabSwitcherModeTopToolbar;
                    hVar.c();
                    hVar.d();
                }
            } else if (hVar.j == null) {
                CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar5 = (CustomTabSwitcherModeTopToolbar) viewStub.inflate();
                hVar.j = customTabSwitcherModeTopToolbar5;
                hVar.a(customTabSwitcherModeTopToolbar5, false);
                hVar.c();
                hVar.d();
            }
            hVar.j.setTabSwitcherMode(z);
        }
    }

    public final void s() {
        this.a.F();
        this.d.b();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void t(Integer num, boolean z, Integer num2) {
        d dVar = this.c;
        if (dVar == null || this.a.getToolbarDataProvider() == null) {
            return;
        }
        boolean z2 = false;
        g gVar = dVar.a;
        if (z) {
            if (!(dVar.f != null)) {
                int i = R$layout.start_top_toolbar;
                ViewStub viewStub = dVar.b;
                viewStub.setLayoutResource(i);
                StartSurfaceToolbarView startSurfaceToolbarView = (StartSurfaceToolbarView) viewStub.inflate();
                dVar.f = startSurfaceToolbarView;
                af1 af1Var = dVar.n;
                MenuButton menuButton = (MenuButton) startSurfaceToolbarView.findViewById(R$id.menu_button_wrapper);
                af1Var.e = menuButton;
                org.chromium.ui.modelutil.j jVar = af1Var.f;
                if (jVar != null) {
                    jVar.c();
                }
                af1Var.f = org.chromium.ui.modelutil.j.b(af1Var.b, menuButton, new ff1());
                af1 af1Var2 = dVar.n;
                PropertyModel propertyModel = dVar.c;
                af1Var2.c(propertyModel.k(cs2.k));
                org.chromium.ui.modelutil.j.b(propertyModel, dVar.f, new j.a() { // from class: org.chromium.chrome.browser.toolbar.top.c
                    @Override // org.chromium.ui.modelutil.j.a
                    public final void b(org.chromium.ui.modelutil.k kVar, Object obj, Object obj2) {
                        PropertyModel propertyModel2 = (PropertyModel) kVar;
                        StartSurfaceToolbarView startSurfaceToolbarView2 = (StartSurfaceToolbarView) obj;
                        v62 v62Var = (v62) obj2;
                        PropertyModel.n nVar = cs2.j;
                        if (v62Var == nVar) {
                            propertyModel2.k(nVar);
                            startSurfaceToolbarView2.f.b();
                            return;
                        }
                        PropertyModel.n nVar2 = cs2.n;
                        if (v62Var == nVar2) {
                            boolean zK = propertyModel2.k(nVar2);
                            startSurfaceToolbarView2.e.setClickable(zK);
                            startSurfaceToolbarView2.i.setClickable(zK);
                            return;
                        }
                        PropertyModel.n nVar3 = cs2.p;
                        if (v62Var == nVar3) {
                            startSurfaceToolbarView2.f.setGridTabSwitcherEnabled(propertyModel2.k(nVar3));
                            return;
                        }
                        PropertyModel.n nVar4 = cs2.u;
                        if (v62Var == nVar4) {
                            startSurfaceToolbarView2.i.setVisibility(Boolean.valueOf(propertyModel2.k(nVar4)).booleanValue() ? 0 : 8);
                            return;
                        }
                        PropertyModel.n nVar5 = cs2.t;
                        if (v62Var == nVar5) {
                            propertyModel2.k(nVar5);
                            ((RelativeLayout.LayoutParams) startSurfaceToolbarView2.j.getLayoutParams()).removeRule(16);
                            return;
                        }
                        PropertyModel.r<View.OnClickListener> rVar = cs2.d;
                        if (v62Var == rVar) {
                            startSurfaceToolbarView2.setIdentityDiscClickHandler((View.OnClickListener) propertyModel2.j(rVar));
                            return;
                        }
                        PropertyModel.r<String> rVar2 = cs2.f;
                        if (v62Var == rVar2) {
                            startSurfaceToolbarView2.j.setContentDescription((String) propertyModel2.j(rVar2));
                            return;
                        }
                        PropertyModel.r<Drawable> rVar3 = cs2.e;
                        if (v62Var == rVar3) {
                            startSurfaceToolbarView2.j.setImageDrawable((Drawable) propertyModel2.j(rVar3));
                            return;
                        }
                        PropertyModel.n nVar6 = cs2.g;
                        if (v62Var == nVar6) {
                            startSurfaceToolbarView2.j.setVisibility(propertyModel2.k(nVar6) ? 0 : 8);
                            return;
                        }
                        PropertyModel.r<bz0> rVar4 = cs2.a;
                        if (v62Var == rVar4) {
                            startSurfaceToolbarView2.f.setIncognitoStateProvider((bz0) propertyModel2.j(rVar4));
                            return;
                        }
                        PropertyModel.n nVar7 = cs2.i;
                        if (v62Var == nVar7) {
                            startSurfaceToolbarView2.b(propertyModel2.k(nVar7));
                            return;
                        }
                        PropertyModel.n nVar8 = cs2.h;
                        if (v62Var == nVar8) {
                            boolean zK2 = propertyModel2.k(nVar8);
                            if (zK2 == startSurfaceToolbarView2.l) {
                                return;
                            }
                            startSurfaceToolbarView2.l = zK2;
                            startSurfaceToolbarView2.setVisibility(zK2 ? 0 : 8);
                            return;
                        }
                        PropertyModel.n nVar9 = cs2.k;
                        if (v62Var == nVar9) {
                            startSurfaceToolbarView2.j.setPadding(0, 0, propertyModel2.k(nVar9) ? 0 : startSurfaceToolbarView2.getContext().getResources().getDimensionPixelOffset(R$dimen.start_surface_toolbar_button_padding_to_edge), 0);
                            return;
                        }
                        PropertyModel.r<View.OnClickListener> rVar5 = cs2.b;
                        if (v62Var == rVar5) {
                            startSurfaceToolbarView2.setOnNewTabClickHandler((View.OnClickListener) propertyModel2.j(rVar5));
                            return;
                        }
                        PropertyModel.n nVar10 = cs2.c;
                        if (v62Var == nVar10) {
                            boolean zK3 = propertyModel2.k(nVar10);
                            startSurfaceToolbarView2.f.setEnabled(zK3);
                            if (startSurfaceToolbarView2.e.getVisibility() == 0) {
                                startSurfaceToolbarView2.e.setEnabled(zK3);
                                return;
                            }
                            return;
                        }
                        PropertyModel.n nVar11 = cs2.o;
                        if (v62Var == nVar11) {
                            boolean zK4 = propertyModel2.k(nVar11);
                            NewTabButton newTabButton = startSurfaceToolbarView2.f;
                            if (newTabButton == null) {
                                return;
                            }
                            if (zK4) {
                                wm3.b(newTabButton, new wm3.a(1));
                                return;
                            } else {
                                wm3.a(newTabButton);
                                return;
                            }
                        }
                        PropertyModel.n nVar12 = cs2.l;
                        if (v62Var == nVar12) {
                            startSurfaceToolbarView2.m = propertyModel2.k(nVar12);
                            startSurfaceToolbarView2.a();
                            return;
                        }
                        PropertyModel.n nVar13 = cs2.m;
                        if (v62Var == nVar13) {
                            startSurfaceToolbarView2.g = propertyModel2.k(nVar13);
                            startSurfaceToolbarView2.a();
                            return;
                        }
                        PropertyModel.o oVar = cs2.v;
                        if (v62Var == oVar) {
                            startSurfaceToolbarView2.setTranslationY(propertyModel2.g(oVar));
                            return;
                        }
                        PropertyModel.o oVar2 = cs2.w;
                        if (v62Var == oVar2) {
                            startSurfaceToolbarView2.setAlpha(propertyModel2.g(oVar2));
                            return;
                        }
                        PropertyModel.n nVar14 = cs2.q;
                        if (v62Var == nVar14) {
                            startSurfaceToolbarView2.h.setVisibility(propertyModel2.k(nVar14) ? 0 : 8);
                            return;
                        }
                        PropertyModel.r<org.chromium.chrome.browser.toolbar.a> rVar6 = cs2.r;
                        if (v62Var == rVar6) {
                            org.chromium.chrome.browser.toolbar.a aVar = (org.chromium.chrome.browser.toolbar.a) propertyModel2.j(rVar6);
                            IncognitoToggleTabLayout incognitoToggleTabLayout = startSurfaceToolbarView2.i;
                            if (incognitoToggleTabLayout != null) {
                                incognitoToggleTabLayout.setTabCountProvider(aVar);
                                return;
                            }
                            return;
                        }
                        PropertyModel.r<y13> rVar7 = cs2.s;
                        if (v62Var == rVar7) {
                            y13 y13Var = (y13) propertyModel2.j(rVar7);
                            IncognitoToggleTabLayout incognitoToggleTabLayout2 = startSurfaceToolbarView2.i;
                            if (incognitoToggleTabLayout2 != null) {
                                incognitoToggleTabLayout2.setTabModelSelector(y13Var);
                            }
                        }
                    }
                });
                LogoView logoView = (LogoView) dVar.f.findViewById(R$id.logo);
                if (gVar.t) {
                    org.chromium.chrome.browser.logo.a aVar = new org.chromium.chrome.browser.logo.a(gVar.u, gVar.p, logoView, gVar.r, null, null, gVar.c(), null);
                    gVar.C = aVar;
                    if (gVar.B) {
                        aVar.b();
                    }
                }
                if (CachedFeatureFlags.b(ar.d.b("StartSurfaceAndroid:tab_count_button_on_start_surface"), true)) {
                    TabSwitcherButtonView tabSwitcherButtonView = (TabSwitcherButtonView) dVar.f.findViewById(R$id.start_tab_switcher_button);
                    dVar.i = tabSwitcherButtonView;
                    View.OnLongClickListener onLongClickListener = dVar.m;
                    if (onLongClickListener != null) {
                        tabSwitcherButtonView.setOnLongClickListener(onLongClickListener);
                        dVar.m = null;
                    }
                    org.chromium.chrome.browser.toolbar.c cVar = new org.chromium.chrome.browser.toolbar.c(dVar.i);
                    dVar.h = cVar;
                    ThemeColorProvider themeColorProvider = dVar.k;
                    cVar.b = themeColorProvider;
                    q43 q43Var = new q43(cVar);
                    cVar.c = q43Var;
                    themeColorProvider.i.a(q43Var);
                    cVar.a.p(r43.d, cVar.b.g);
                    org.chromium.chrome.browser.toolbar.a aVar2 = dVar.j;
                    if (aVar2 != null) {
                        org.chromium.chrome.browser.toolbar.c cVar2 = dVar.h;
                        cVar2.d = aVar2;
                        cVar2.a.l(r43.e, true);
                        org.chromium.chrome.browser.toolbar.b bVar = new org.chromium.chrome.browser.toolbar.b(cVar2);
                        cVar2.e = bVar;
                        cVar2.d.a(bVar);
                        dVar.j = null;
                    }
                    View.OnClickListener onClickListener = dVar.l;
                    if (onClickListener != null) {
                        dVar.h.setTabSwitcherListener(onClickListener);
                        dVar.l = null;
                    }
                }
            }
        }
        int iIntValue = num == null ? 0 : num.intValue();
        int iIntValue2 = num2 == null ? 0 : num2.intValue();
        boolean zB = gVar.b();
        gVar.x = iIntValue;
        gVar.y = iIntValue2;
        org.chromium.chrome.browser.logo.a aVar3 = gVar.C;
        if (aVar3 != null) {
            aVar3.a.d(gVar.c(), (!gVar.q ? gVar.x == 0 : !(gVar.c() || gVar.b())) || gVar.b() || gVar.x == 3, false);
        }
        boolean z3 = gVar.h && gVar.c();
        PropertyModel.n nVar = cs2.q;
        PropertyModel propertyModel2 = gVar.e;
        propertyModel2.l(nVar, z3);
        propertyModel2.l(cs2.t, z3);
        gVar.e();
        gVar.g();
        gVar.d(gVar.i.get());
        boolean zB2 = gVar.b();
        af1 af1Var3 = gVar.m;
        cf1 cf1Var = af1Var3.c;
        if (cf1Var != null) {
            cf1Var.p = zB2;
            if (zB2) {
                cf1Var.c(false);
            } else {
                if ((cf1Var.r.get() != null) && cf1Var.k) {
                    cf1Var.d(false);
                }
            }
        }
        PropertyModel.n nVar2 = cs2.h;
        if (propertyModel2.k(nVar2) != z) {
            ObjectAnimator objectAnimator = gVar.D;
            if (objectAnimator != null) {
                objectAnimator.cancel();
                gVar.D = null;
            }
            if (gVar.k && (zB || gVar.b())) {
                z2 = true;
            }
            if (z2) {
                propertyModel2.l(nVar2, true);
                PropertyModel.o oVar = cs2.w;
                propertyModel2.m(oVar, z ? 0.0f : 1.0f);
                ObjectAnimator objectAnimatorA = org.chromium.ui.modelutil.i.a(propertyModel2, oVar, z ? 1.0f : 0.0f);
                gVar.D = objectAnimatorA;
                objectAnimatorA.setDuration(150L);
                gVar.D.setStartDelay(z ? 150L : 0L);
                gVar.D.setInterpolator(h21.f);
                gVar.D.addListener(new bs2(gVar, z));
                if (pr1.b()) {
                    gVar.D.addUpdateListener(new jw1(2, gVar));
                }
                gVar.D.start();
            } else {
                propertyModel2.m(cs2.w, 1.0f);
                propertyModel2.l(nVar2, z);
                gVar.E.onResult(Boolean.valueOf(z));
                gVar.D = null;
            }
        }
        propertyModel2.l(cs2.n, z);
        cf1 cf1Var2 = af1Var3.c;
        if (cf1Var2 != null) {
            cf1Var2.g.l(df1.d, z);
        }
        gVar.h(gVar.A);
        u();
        s();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u() {
        /*
            r7 = this;
            org.chromium.chrome.browser.toolbar.top.d r0 = r7.c
            boolean r1 = r0.e
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L1c
            boolean r1 = r0.p
            if (r1 == 0) goto L1c
            org.chromium.chrome.browser.profiles.Profile r1 = org.chromium.chrome.browser.profiles.Profile.d()
            org.chromium.components.search_engines.TemplateUrlService r1 = defpackage.oj2.q(r1)
            boolean r1 = r1.b()
            if (r1 == 0) goto L1c
            r1 = r3
            goto L1d
        L1c:
            r1 = r2
        L1d:
            int r4 = org.chromium.chrome.R$dimen.toolbar_height_no_shadow
            android.view.ViewStub r5 = r0.b
            android.content.res.Resources r6 = r5.getResources()
            int r4 = r6.getDimensionPixelOffset(r4)
            if (r1 == 0) goto L36
            int r1 = org.chromium.chrome.R$dimen.start_surface_content_logo_height
            android.content.res.Resources r5 = r5.getResources()
            int r1 = r5.getDimensionPixelOffset(r1)
            goto L40
        L36:
            int r1 = org.chromium.chrome.R$dimen.start_surface_fake_search_box_top_margin
            android.content.res.Resources r5 = r5.getResources()
            int r1 = r5.getDimensionPixelOffset(r1)
        L40:
            int r4 = r4 + r1
            org.chromium.chrome.browser.toolbar.top.g r1 = r0.a
            boolean r5 = r1.c()
            org.chromium.ui.modelutil.PropertyModel r6 = r1.e
            if (r5 == 0) goto L55
            org.chromium.ui.modelutil.PropertyModel$n r5 = defpackage.cs2.h
            boolean r5 = r6.k(r5)
            if (r5 != 0) goto L55
            r5 = r3
            goto L56
        L55:
            r5 = r2
        L56:
            if (r5 != 0) goto L8e
            org.chromium.ui.modelutil.PropertyModel$n r5 = defpackage.cs2.h
            boolean r5 = r6.k(r5)
            if (r5 == 0) goto L6e
            org.chromium.ui.modelutil.PropertyModel$o r5 = defpackage.cs2.v
            float r5 = r6.g(r5)
            float r5 = -r5
            float r4 = (float) r4
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 < 0) goto L6e
            r4 = r3
            goto L6f
        L6e:
            r4 = r2
        L6f:
            if (r4 != 0) goto L8e
            boolean r4 = r1.q
            if (r4 == 0) goto L82
            boolean r4 = r1.c()
            if (r4 != 0) goto L88
            boolean r4 = r1.b()
            if (r4 != 0) goto L88
            goto L86
        L82:
            int r4 = r1.x
            if (r4 != 0) goto L88
        L86:
            r4 = r3
            goto L89
        L88:
            r4 = r2
        L89:
            if (r4 == 0) goto L8c
            goto L8e
        L8c:
            r4 = r2
            goto L8f
        L8e:
            r4 = r3
        L8f:
            if (r4 != 0) goto La7
            if (r0 == 0) goto L9d
            org.chromium.chrome.browser.toolbar.top.g r4 = r0.a
            boolean r4 = r4.b()
            if (r4 == 0) goto L9d
            r4 = r3
            goto L9e
        L9d:
            r4 = r2
        L9e:
            if (r4 == 0) goto La5
            boolean r4 = r7.k
            if (r4 != 0) goto La5
            goto La7
        La5:
            r4 = r2
            goto La8
        La7:
            r4 = r3
        La8:
            boolean r1 = r1.c()
            if (r0 == 0) goto Lb7
            org.chromium.chrome.browser.toolbar.top.g r0 = r0.a
            boolean r0 = r0.b()
            if (r0 == 0) goto Lb7
            r2 = r3
        Lb7:
            org.chromium.chrome.browser.toolbar.top.ToolbarLayout r7 = r7.a
            r7.t(r4, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.m.u():void");
    }
}
