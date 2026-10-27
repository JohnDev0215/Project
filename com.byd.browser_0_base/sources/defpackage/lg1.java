package defpackage;

import android.content.res.Resources;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.messages.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MessageBannerCoordinator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lg1 {
    public final mg1 a;
    public final MessageBannerView b;
    public final PropertyModel c;
    public final ig1 d;
    public final nw2<Long> e;
    public final Runnable f;

    public lg1(MessageBannerView messageBannerView, PropertyModel propertyModel, nw2 nw2Var, Resources resources, es3 es3Var, cx2 cx2Var, zm2 zm2Var, ve3 ve3Var) {
        this.b = messageBannerView;
        this.c = propertyModel;
        j.b(propertyModel, messageBannerView, new ag(23));
        mg1 mg1Var = new mg1(propertyModel, nw2Var, resources, es3Var, cx2Var);
        this.a = mg1Var;
        this.e = zm2Var;
        ig1 ig1Var = new ig1();
        this.d = ig1Var;
        this.f = ve3Var;
        messageBannerView.setSwipeHandler(mg1Var);
        messageBannerView.setPopupMenuShownListener(new kg1(ig1Var, ((Long) zm2Var.get()).longValue(), ve3Var));
    }

    public final void a(int i) {
        String string;
        MessageBannerView messageBannerView = this.b;
        if (i == 1) {
            string = ((String) this.c.j(ng1.f)) + " " + messageBannerView.getResources().getString(R$string.message_screen_position);
        } else {
            string = messageBannerView.getResources().getString(R$string.message_new_actions_available);
        }
        messageBannerView.announceForAccessibility(string);
    }
}
