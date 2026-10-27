package defpackage;

import J.N;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.d;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class iu1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ PageInfoController f;

    public /* synthetic */ iu1(PageInfoController pageInfoController, int i) {
        this.e = i;
        this.f = pageInfoController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CookieControlsBridge cookieControlsBridge;
        int i = this.e;
        PageInfoController pageInfoController = this.f;
        switch (i) {
            case 0:
                pageInfoController.d();
                break;
            default:
                d dVar = pageInfoController.u;
                if (dVar != null && (cookieControlsBridge = dVar.k) != null) {
                    long j = cookieControlsBridge.a;
                    if (j != 0) {
                        N.MGYjAHK4(j);
                    }
                    break;
                }
                break;
        }
    }
}
