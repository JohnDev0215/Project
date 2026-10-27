package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.f;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class rg1 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ PropertyModel f;

    public /* synthetic */ rg1(int i, PropertyModel propertyModel) {
        this.e = i;
        this.f = propertyModel;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        PropertyModel propertyModel = this.f;
        switch (i) {
            case 0:
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
            case 1:
                ((Runnable) propertyModel.j(v10.c)).run();
                break;
            case 2:
                ((Runnable) propertyModel.j(n51.a)).run();
                break;
            case 3:
                ((Runnable) propertyModel.j(bm3.m)).run();
                break;
            case 4:
                ((Callback) propertyModel.j(gv1.b)).onResult(null);
                ((Callback) propertyModel.j(gv1.d)).onResult(null);
                break;
            default:
                ((Runnable) propertyModel.j(jf2.f)).run();
                break;
        }
    }
}
