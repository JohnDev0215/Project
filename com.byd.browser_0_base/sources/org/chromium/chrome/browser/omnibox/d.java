package org.chromium.chrome.browser.omnibox;

import android.animation.ObjectAnimator;
import defpackage.et2;
import defpackage.xt2;
import java.util.ArrayList;
import org.chromium.chrome.browser.omnibox.c;

/* JADX INFO: compiled from: LocationBarCoordinatorPhone.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements c.a {
    public LocationBarPhone a;
    public et2 b;

    public d(LocationBarPhone locationBarPhone, et2 et2Var) {
        this.a = locationBarPhone;
        this.b = et2Var;
    }

    public final int a() {
        LocationBarPhone locationBarPhone = this.a;
        if (!locationBarPhone.m.isIncognito() || locationBarPhone.s.getVisibility() == 8) {
            return 0;
        }
        return locationBarPhone.s.getMeasuredWidth();
    }

    public final void b(ArrayList arrayList, long j, long j2, float f) {
        et2 et2Var = this.b;
        if (et2Var.j.isIncognito()) {
            ObjectAnimator duration = org.chromium.ui.modelutil.i.a(et2Var.g, xt2.a, f).setDuration(j2);
            duration.setStartDelay(j);
            arrayList.add(duration);
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.c.a
    public final void destroy() {
        this.a = null;
        this.b = null;
    }
}
