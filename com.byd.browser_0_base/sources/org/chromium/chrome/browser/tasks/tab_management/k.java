package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import defpackage.t13;
import defpackage.tp2;
import defpackage.wp2;
import java.util.List;
import java.util.Locale;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.s1;

/* JADX INFO: compiled from: TabGridDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k implements t13 {
    public final /* synthetic */ wp2 e;
    public final /* synthetic */ m f;

    public k(m mVar, wp2 wp2Var) {
        this.f = mVar;
        this.e = wp2Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        if (i == 3) {
            this.f.e(false);
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        s1.d dVar;
        m mVar = this.f;
        mVar.l();
        if (mVar.f.k(q.h) && (dVar = mVar.l) != null) {
            ((o1) dVar).w(mVar.g.u().c(), false, false);
        }
        this.e.b(mVar, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.t13
    public final void P(boolean z) {
        this.e.a(this.f);
    }

    @Override // defpackage.t13
    public final void Y(List<Tab> list) {
        int size = list.size();
        m mVar = this.f;
        wp2 wp2Var = this.e;
        if (size == 1) {
            wp2Var.b(mVar, Integer.valueOf(list.get(0).getId()));
        } else {
            wp2Var.b(mVar, list);
        }
    }

    public final void a(Tab tab) {
        String title = tab.getTitle();
        m mVar = this.f;
        tp2 tp2VarA = tp2.a(title, mVar, 0, 11);
        tp2VarA.c = mVar.e.getString(R$string.undo_bar_close_message);
        String string = mVar.e.getString(R$string.undo);
        Integer numValueOf = Integer.valueOf(tab.getId());
        tp2VarA.d = string;
        tp2VarA.e = numValueOf;
        this.e.c(tp2VarA);
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        m mVar = this.f;
        if (mVar.g.t()) {
            mVar.e(false);
        }
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        if (this.f.f.k(q.h)) {
            a(tab);
        }
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        this.e.b(this.f, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        s1.d dVar;
        int id = tab.getId();
        m mVar = this.f;
        List<Tab> listB = mVar.b(id);
        if (listB.size() == 0) {
            mVar.e(false);
            return;
        }
        if (tab.getId() == mVar.v) {
            mVar.v = listB.get(0).getId();
        }
        mVar.l();
        if (!mVar.f.k(q.h) || (dVar = mVar.l) == null) {
            return;
        }
        ((o1) dVar).w(mVar.g.u().c(), false, false);
    }

    @Override // defpackage.t13
    public final void w0(List<Tab> list, boolean z) {
        m mVar = this.f;
        if (mVar.f.k(q.h)) {
            if (list.size() == 1) {
                a(list.get(0));
                return;
            }
            tp2 tp2VarA = tp2.a(String.format(Locale.getDefault(), "%d", Integer.valueOf(list.size())), mVar, 0, 30);
            int i = R$string.undo_bar_close_all_message;
            Activity activity = mVar.e;
            tp2VarA.c = activity.getString(i);
            tp2VarA.d = activity.getString(R$string.undo);
            tp2VarA.e = list;
            this.e.c(tp2VarA);
        }
    }
}
