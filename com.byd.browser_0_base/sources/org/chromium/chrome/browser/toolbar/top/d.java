package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import defpackage.af1;
import defpackage.ar;
import defpackage.bn0;
import defpackage.ck;
import defpackage.cs2;
import defpackage.g90;
import defpackage.gc3;
import defpackage.hw0;
import defpackage.i41;
import defpackage.jj2;
import defpackage.ll;
import defpackage.n62;
import defpackage.pk3;
import defpackage.sh;
import defpackage.u7;
import defpackage.x10;
import defpackage.y13;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceToolbarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public final g a;
    public final ViewStub b;
    public final PropertyModel c;
    public final n d;
    public final boolean e;
    public StartSurfaceToolbarView f;
    public y13 g;
    public org.chromium.chrome.browser.toolbar.c h;
    public TabSwitcherButtonView i;
    public org.chromium.chrome.browser.toolbar.a j;
    public ThemeColorProvider k;
    public View.OnClickListener l;
    public View.OnLongClickListener m;
    public af1 n;
    public ll o = new ll();
    public boolean p;

    public d(ViewStub viewStub, pk3 pk3Var, ck ckVar, u7 u7Var, af1 af1Var, jj2 jj2Var, boolean z, boolean z2, boolean z3, bn0 bn0Var, ll.b bVar, boolean z4, boolean z5, x10 x10Var, i iVar) {
        this.b = viewStub;
        HashMap mapE = PropertyModel.e(cs2.x);
        PropertyModel.n nVar = cs2.u;
        sh shVar = StartSurfaceConfiguration.b;
        boolean z6 = !shVar.c();
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z6;
        mapE.put(nVar, aVar);
        PropertyModel.n nVar2 = cs2.k;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = true;
        mapE.put(nVar2, aVar2);
        PropertyModel.n nVar3 = cs2.h;
        PropertyModel.a aVar3 = new PropertyModel.a();
        aVar3.a = false;
        mapE.put(nVar3, aVar3);
        PropertyModel.n nVar4 = cs2.p;
        PropertyModel.a aVar4 = new PropertyModel.a();
        aVar4.a = z;
        mapE.put(nVar4, aVar4);
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.c = propertyModel;
        this.e = z5;
        boolean z7 = z2 && !g90.a(viewStub.getContext());
        Context context = viewStub.getContext();
        n62 n62Var = new n62(this, 19, pk3Var);
        boolean zC = shVar.c();
        i41 i41Var = ar.d;
        this.a = new g(context, propertyModel, n62Var, zC, af1Var, ckVar, jj2Var, CachedFeatureFlags.b(i41Var.b("StartSurfaceAndroid:tab_count_button_on_start_surface"), true), z7, z3, bn0Var, bVar, z4, CachedFeatureFlags.b(i41Var.b("StartSurfaceAndroid:is_doodle_supported"), false), z5, x10Var, iVar);
        this.k = u7Var;
        this.n = af1Var;
        this.d = new n(new gc3(this));
    }

    public static void a(d dVar, pk3 pk3Var, hw0 hw0Var) {
        StartSurfaceToolbarView startSurfaceToolbarView = dVar.f;
        if (startSurfaceToolbarView == null) {
            return;
        }
        hw0Var.j = startSurfaceToolbarView.j;
        pk3Var.a(hw0Var.a());
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
        this.a.setOnNewTabClickHandler(onClickListener);
    }

    public void setOnTabSwitcherLongClickHandler(View.OnLongClickListener onLongClickListener) {
        TabSwitcherButtonView tabSwitcherButtonView = this.i;
        if (tabSwitcherButtonView != null) {
            tabSwitcherButtonView.setOnLongClickListener(onLongClickListener);
        } else {
            this.m = onLongClickListener;
        }
    }

    public void setTabSwitcherListener(View.OnClickListener onClickListener) {
        org.chromium.chrome.browser.toolbar.c cVar = this.h;
        if (cVar != null) {
            cVar.setTabSwitcherListener(onClickListener);
        } else {
            this.l = onClickListener;
        }
    }
}
