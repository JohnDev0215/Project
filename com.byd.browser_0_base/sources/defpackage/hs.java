package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import java.util.LinkedHashSet;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.messages.MessageContainer;
import org.chromium.components.messages.b;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hs implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ hs(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                return ((CompositorViewHolder) obj).getDynamicResourceLoader();
            case 1:
                return ((f61) obj).h0;
            case 2:
                return (WindowAndroid) ((p61) obj).get();
            case 3:
                z20 z20Var = ((fj2) obj).i;
                z20Var.c().a(z20Var.c().i());
                return 1;
            case 4:
                LinkedHashSet<Integer> linkedHashSet = i92.u;
                return xg1.a(((Tab) obj).D());
            case 5:
                return ((qq0) obj).t;
            case 6:
                return ((ActivityManager) obj).getAppTasks();
            case 7:
                return ((Activity) obj).getIntent();
            case 8:
                return ((ge0) obj).i;
            case 9:
                a81 a81Var = (a81) obj;
                a81Var.getClass();
                LoadUrlParams loadUrlParams = new LoadUrlParams("https://support.google.com/chrome?p=shared_highlighting", 0);
                y13 y13Var = a81Var.a;
                y13Var.x(loadUrlParams, 0, y13Var.i(), y13Var.r());
                a81Var.b.dismissed("IPH_SharedHighlightingReceiver");
                return 1;
            case 10:
                v53 v53Var = (v53) obj;
                return v53Var.p(v53Var.a);
            case 11:
                return ((LocationBarModel) obj).d();
            default:
                b bVar = (b) obj;
                MessageBannerView messageBannerView = bVar.b;
                MessageContainer messageContainer = bVar.c;
                return new ug1(messageContainer.getChildAt(1 - messageContainer.indexOfChild(messageBannerView)));
        }
    }
}
