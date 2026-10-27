package defpackage;

import android.view.View;
import defpackage.le3;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.f;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kg implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ PropertyModel f;

    public /* synthetic */ kg(int i, PropertyModel propertyModel) {
        this.e = i;
        this.f = propertyModel;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        PropertyModel propertyModel = this.f;
        switch (i) {
            case 0:
                ((Callback) propertyModel.j(ig.j)).onResult(BookmarkId.c);
                break;
            case 1:
                int i2 = lv1.z;
                ((Runnable) propertyModel.j(t81.y)).run();
                break;
            case 2:
                ((Runnable) propertyModel.j(gz1.h)).run();
                break;
            case 3:
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
            case 4:
                ((j0.l) propertyModel.j(u0.b)).a(propertyModel.h(u0.a));
                break;
            case 5:
                ((Runnable) propertyModel.j(le3.c.a)).run();
                break;
            case 6:
                ((Runnable) propertyModel.j(gb.c)).run();
                break;
            case 7:
                ((Runnable) propertyModel.j(n51.b)).run();
                break;
            case 8:
                ((Runnable) propertyModel.j(bm3.k)).run();
                break;
            case 9:
                ((Callback) propertyModel.j(gv1.a)).onResult(null);
                ((Callback) propertyModel.j(gv1.d)).onResult(null);
                break;
            default:
                ((Callback) propertyModel.j(ho.a)).onResult(propertyModel);
                break;
        }
    }
}
