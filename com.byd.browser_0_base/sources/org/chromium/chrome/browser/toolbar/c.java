package org.chromium.chrome.browser.toolbar;

import android.view.View;
import defpackage.q43;
import defpackage.r43;
import defpackage.s43;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: TabSwitcherButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public final PropertyModel a;
    public ThemeColorProvider b;
    public q43 c;
    public a d;
    public b e;

    public c(TabSwitcherButtonView tabSwitcherButtonView) {
        PropertyModel propertyModel = new PropertyModel(r43.f);
        this.a = propertyModel;
        j.b(propertyModel, tabSwitcherButtonView, new s43());
    }

    public void setTabSwitcherListener(View.OnClickListener onClickListener) {
        this.a.p(r43.b, onClickListener);
    }
}
