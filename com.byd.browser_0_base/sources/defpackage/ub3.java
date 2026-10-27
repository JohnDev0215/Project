package defpackage;

import android.content.res.Resources;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ub3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ yb3 f;

    public /* synthetic */ ub3(yb3 yb3Var, int i) {
        this.e = i;
        this.f = yb3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        yb3 yb3Var = this.f;
        switch (i) {
            case 0:
                Resources resources = yb3Var.f.getResources();
                int i2 = R$string.feature_notification_guide_tooltip_message_incognito_tab;
                hw0 hw0Var = new hw0(resources, "IPH_FeatureNotificationGuideIncognitoTabHelpBubble", i2, i2);
                hw0Var.j = yb3Var.k;
                hw0Var.k = new wb3(yb3Var, 0);
                hw0Var.m = new tb3(yb3Var, 1);
                yb3Var.m.a(hw0Var.a());
                break;
            case 1:
                Integer numValueOf = Integer.valueOf(R$id.offline_page_id);
                h7 h7Var = yb3Var.l;
                if (h7Var != null) {
                    ((i7) h7Var).c(numValueOf);
                }
                break;
            default:
                Integer numValueOf2 = Integer.valueOf(R$id.translate_id);
                h7 h7Var2 = yb3Var.l;
                if (h7Var2 != null) {
                    ((i7) h7Var2).c(numValueOf2);
                }
                break;
        }
    }
}
