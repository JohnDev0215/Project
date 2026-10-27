package defpackage;

import android.view.View;
import defpackage.he3;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.f;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cy0 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ PropertyModel f;

    public /* synthetic */ cy0(int i, PropertyModel propertyModel) {
        this.e = i;
        this.f = propertyModel;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        PropertyModel propertyModel = this.f;
        switch (i) {
            case 0:
                ((b7) propertyModel.j(j7.l)).a(propertyModel);
                break;
            case 1:
                int iH = propertyModel.h(f.a);
                MessageCardView.a aVar = (MessageCardView.a) propertyModel.j(f.i);
                if (aVar != null) {
                    aVar.a(iH);
                }
                MessageCardView.a aVar2 = (MessageCardView.a) propertyModel.j(f.l);
                if (aVar2 != null) {
                    aVar2.a(iH);
                }
                break;
            case 2:
                ((j0.l) propertyModel.j(u0.c)).a(propertyModel.h(u0.a));
                break;
            case 3:
                ((j0.l) propertyModel.j(u0.c)).a(propertyModel.h(u0.a));
                break;
            case 4:
                ((Runnable) propertyModel.j(he3.a.f)).run();
                break;
            case 5:
                ((Runnable) propertyModel.j(zo0.b)).run();
                break;
            case 6:
                ((Runnable) propertyModel.j(og3.c)).run();
                break;
            case 7:
                ((Runnable) propertyModel.j(bm3.o)).run();
                break;
            case 8:
                ((Runnable) propertyModel.j(bm3.l)).run();
                break;
            default:
                ((Runnable) propertyModel.j(ih2.g)).run();
                break;
        }
    }
}
