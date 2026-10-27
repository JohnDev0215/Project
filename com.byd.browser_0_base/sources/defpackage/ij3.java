package defpackage;

import android.view.View;
import defpackage.he3;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.f;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ij3 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ PropertyModel f;

    public /* synthetic */ ij3(int i, PropertyModel propertyModel) {
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
                ((Runnable) propertyModel.j(gz1.g)).run();
                break;
            case 2:
                MessageCardView.c cVar = (MessageCardView.c) propertyModel.j(f.h);
                if (cVar != null) {
                    cVar.a();
                }
                MessageCardView.c cVar2 = (MessageCardView.c) propertyModel.j(f.k);
                if (cVar2 != null) {
                    cVar2.a();
                }
                MessageCardView.a aVar = (MessageCardView.a) propertyModel.j(f.i);
                if (aVar != null && !propertyModel.k(f.n)) {
                    aVar.a(propertyModel.h(f.a));
                    break;
                }
                break;
            case 3:
                ((j0.l) propertyModel.j(u0.c)).a(propertyModel.h(u0.a));
                break;
            case 4:
                ((j0.l) propertyModel.j(u0.b)).a(propertyModel.h(u0.a));
                break;
            case 5:
                ((j0.l) propertyModel.j(u0.b)).a(propertyModel.h(u0.a));
                break;
            case 6:
                ((Runnable) propertyModel.j(he3.a.f)).run();
                break;
            case 7:
                ((Callback) propertyModel.j(t1.c)).onResult((Account) propertyModel.j(t1.a));
                break;
            case 8:
                int[] iArr = a.a;
                ((b7) propertyModel.j(j7.l)).a(propertyModel);
                break;
            case 9:
                ((Runnable) propertyModel.j(fk0.k)).run();
                break;
            case 10:
                ((Runnable) propertyModel.j(bm3.n)).run();
                break;
            case 11:
                ((Runnable) propertyModel.j(bm3.i)).run();
                break;
            default:
                ((Runnable) propertyModel.j(ih2.h)).run();
                break;
        }
    }
}
