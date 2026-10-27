package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import defpackage.b03;
import defpackage.bi1;
import defpackage.c03;
import defpackage.di1;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGridIphDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o implements o1.d {
    public final ViewGroup a;
    public final TabGridIphDialogView b;
    public final PropertyModel c;
    public final bi1 d;
    public final b03 e;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, org.chromium.chrome.browser.tasks.tab_management.TabGridIphDialogView] */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, c03] */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v5, types: [android.view.ViewTreeObserver$OnGlobalLayoutListener, b03] */
    public o(Activity activity, ViewGroup viewGroup, bi1 bi1Var) {
        TraceEvent traceEventH0 = TraceEvent.H0("TabGridIphDialogCoordinator.constructor", null);
        try {
            final ?? r1 = (TabGridIphDialogView) LayoutInflater.from(activity).inflate(R$layout.iph_drag_and_drop_dialog_layout, (ViewGroup) null, false);
            this.b = r1;
            this.d = bi1Var;
            this.a = viewGroup;
            ?? c03Var = new c03(this, bi1Var);
            HashMap mapE = PropertyModel.e(di1.B);
            PropertyModel.l<di1.a> lVar = di1.a;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = c03Var;
            mapE.put(lVar, gVar);
            PropertyModel.n nVar = di1.q;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = true;
            mapE.put(nVar, aVar);
            PropertyModel.r<String> rVar = di1.j;
            ?? string = activity.getResources().getString(R$string.ok);
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = string;
            mapE.put(rVar, gVar2);
            PropertyModel.r<View> rVar2 = di1.h;
            PropertyModel.g gVar3 = new PropertyModel.g();
            gVar3.a = r1;
            mapE.put(rVar2, gVar3);
            this.c = new PropertyModel(mapE);
            r1.setRootView(viewGroup);
            ?? r6 = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: b03
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    r1.a();
                }
            };
            this.e = r6;
            viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(r6);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
