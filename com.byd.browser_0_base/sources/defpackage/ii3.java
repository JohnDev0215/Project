package defpackage;

import android.content.Context;
import java.util.List;
import java.util.Locale;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: UndoBarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ii3 implements t13 {
    public final /* synthetic */ nw2 e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ ji3 g;

    public ii3(ji3 ji3Var, js jsVar, Context context) {
        this.g = ji3Var;
        this.e = jsVar;
        this.f = context;
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        if (a(false)) {
            return;
        }
        ji3 ji3Var = this.g;
        ji3Var.g.q0().b(ji3Var, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.t13
    public final void P(boolean z) {
        if (a(false)) {
            return;
        }
        ji3 ji3Var = this.g;
        ji3Var.g.q0().a(ji3Var);
    }

    @Override // defpackage.t13
    public final void Y(List<Tab> list) {
        if (a(false)) {
            return;
        }
        ji3 ji3Var = this.g;
        ji3Var.g.q0().b(ji3Var, list);
    }

    public final boolean a(boolean z) {
        nw2 nw2Var;
        if (z && (nw2Var = this.e) != null && ((Boolean) nw2Var.get()).booleanValue()) {
            return true;
        }
        if (oj2.C(this.f)) {
            return false;
        }
        return qo.f().c() || g90.a(this.g.h);
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        if (a(true)) {
            return;
        }
        int id = tab.getId();
        String title = tab.getTitle();
        ji3 ji3Var = this.g;
        wp2 wp2VarQ0 = ji3Var.g.q0();
        tp2 tp2VarA = tp2.a(title, ji3Var, 0, 11);
        int i = R$string.undo_bar_close_message;
        Context context = ji3Var.h;
        tp2VarA.c = context.getString(i);
        String string = context.getString(R$string.undo);
        Integer numValueOf = Integer.valueOf(id);
        tp2VarA.d = string;
        tp2VarA.e = numValueOf;
        tp2VarA.f = context.getString(R$string.accessibility_undo_closed_tab_announcement_message, title);
        wp2VarQ0.c(tp2VarA);
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        if (a(false)) {
            return;
        }
        ji3 ji3Var = this.g;
        ji3Var.g.q0().b(ji3Var, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.t13
    public final void w0(List<Tab> list, boolean z) {
        if (a(true)) {
            return;
        }
        if (list.size() == 1) {
            h0(list.get(0));
            return;
        }
        ji3 ji3Var = this.g;
        ji3Var.getClass();
        String str = String.format(Locale.getDefault(), "%d", Integer.valueOf(list.size()));
        wp2 wp2VarQ0 = ji3Var.g.q0();
        tp2 tp2VarA = tp2.a(str, ji3Var, 0, z ? 12 : 30);
        tp2VarA.j = z ? 8000 : 3000;
        int i = R$string.undo_bar_close_all_message;
        Context context = ji3Var.h;
        tp2VarA.c = context.getString(i);
        tp2VarA.d = context.getString(R$string.undo);
        tp2VarA.e = list;
        tp2VarA.f = context.getString(R$string.accessibility_undo_multiple_closed_tabs_announcement_message, str);
        wp2VarQ0.c(tp2VarA);
    }
}
