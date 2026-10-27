package defpackage;

import android.content.Context;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CloseAllTabsDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ku {
    public static void a(Context context, aq1 aq1Var, Runnable runnable, boolean z) {
        bi1 bi1Var = (bi1) aq1Var.f;
        ju juVar = new ju(runnable, bi1Var, z);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, juVar);
        bVar.e(di1.c, context.getString(z ? R$string.close_all_tabs_dialog_title_incognito : R$string.close_all_tabs_dialog_title));
        bVar.e(di1.f, context.getString(z ? R$string.close_all_tabs_dialog_message_incognito : R$string.close_all_tabs_dialog_message));
        bVar.e(di1.j, context.getString(R$string.menu_close_all_tabs));
        bVar.e(di1.m, context.getString(R$string.cancel));
        bVar.b(di1.q, true);
        bVar.c(di1.u, 1);
        bi1Var.j(bVar.a(), 1, true);
    }
}
