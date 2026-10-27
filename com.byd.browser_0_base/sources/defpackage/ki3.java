package defpackage;

import android.content.Context;
import defpackage.ni3;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: UndoGroupSnackbarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ki3 extends kh0 {
    public final /* synthetic */ ni3 a;

    public ki3(ni3 ni3Var) {
        this.a = ni3Var;
    }

    @Override // defpackage.kh0, g03.a
    public final void e(List list, ArrayList arrayList, ArrayList arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList3.add(new ni3.a((Tab) list.get(i), ((Integer) arrayList.get(i)).intValue(), ((Integer) arrayList2.get(i)).intValue()));
        }
        ni3 ni3Var = this.a;
        tp2 tp2VarA = tp2.a(String.format(Locale.getDefault(), "%d", Integer.valueOf(ni3Var.f.u().c().A(((ni3.a) arrayList3.get(0)).a.getId()).size())), ni3Var, 0, 32);
        int i2 = R$string.undo_bar_group_tabs_message;
        Context context = ni3Var.e;
        tp2VarA.c = context.getString(i2);
        tp2VarA.d = context.getString(R$string.undo);
        tp2VarA.e = arrayList3;
        ni3Var.g.c(tp2VarA);
    }
}
