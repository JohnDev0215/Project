package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.h1;
import org.chromium.chrome.browser.tasks.tab_management.s;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d03 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ h0.d f;
    public final /* synthetic */ PropertyModel g;
    public final /* synthetic */ ViewLookupCachingFrameLayout h;

    public /* synthetic */ d03(h0.d dVar, PropertyModel propertyModel, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, int i) {
        this.e = i;
        this.f = dVar;
        this.g = propertyModel;
        this.h = viewLookupCachingFrameLayout;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = this.h;
        h0.d dVar = this.f;
        PropertyModel propertyModel = this.g;
        switch (i) {
            case 0:
                h0.c cVar = (h0.c) obj;
                if (dVar == propertyModel.j(u0.f)) {
                    s.e(viewLookupCachingFrameLayout, propertyModel, cVar);
                    break;
                }
                break;
            default:
                h0.c cVar2 = (h0.c) obj;
                if (dVar == propertyModel.j(u0.f)) {
                    h1.b(viewLookupCachingFrameLayout, propertyModel, cVar2.a);
                    propertyModel.l(u0.e, true);
                    break;
                }
                break;
        }
    }
}
