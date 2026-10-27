package org.chromium.chrome.browser.gesturenav;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.dm0;
import defpackage.l10;
import defpackage.nw2;
import defpackage.pt1;
import defpackage.sn0;
import defpackage.yq2;
import defpackage.zc0;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: HistoryNavigationLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends FrameLayout implements ViewGroup.OnHierarchyChangeListener {
    public final sn0 e;
    public final nw2<Boolean> f;
    public final Callback<Boolean> g;
    public SideSlideLayout h;
    public AndroidUiNavigationGlow i;
    public zc0 j;
    public l10 k;

    public a(Context context, yq2 yq2Var, pt1 pt1Var, dm0 dm0Var) {
        super(context);
        this.f = yq2Var;
        this.e = pt1Var;
        this.g = dm0Var;
        setOnHierarchyChangeListener(this);
        setVisibility(4);
    }

    public final sn0 a() {
        if (!this.f.get().booleanValue()) {
            return this.e;
        }
        if (this.i == null) {
            this.i = new AndroidUiNavigationGlow(this);
        }
        return this.i;
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        if (getChildCount() == 0) {
            setVisibility(4);
        }
    }
}
