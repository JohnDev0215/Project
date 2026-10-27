package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: LoadProgressCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e91 {
    public final f91 a;

    public e91(zp1<Tab> zp1Var, ToolbarProgressBar toolbarProgressBar, boolean z) {
        PropertyModel propertyModel = new PropertyModel(g91.c);
        this.a = new f91(zp1Var, propertyModel, z);
        final i91 i91Var = new i91();
        j.b(propertyModel, toolbarProgressBar, new j.a() { // from class: d91
            @Override // org.chromium.ui.modelutil.j.a
            public final void b(k kVar, Object obj, Object obj2) {
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                ToolbarProgressBar toolbarProgressBar2 = (ToolbarProgressBar) obj;
                v62 v62Var = (v62) obj2;
                i91Var.getClass();
                PropertyModel.p pVar = g91.a;
                if (v62Var != pVar) {
                    PropertyModel.o oVar = g91.b;
                    if (v62Var == oVar) {
                        toolbarProgressBar2.setProgress(propertyModel2.g(oVar));
                        return;
                    }
                    return;
                }
                int iH = propertyModel2.h(pVar);
                if (iH != 0) {
                    toolbarProgressBar2.c(iH == 1);
                } else {
                    toolbarProgressBar2.f();
                }
            }
        });
    }
}
