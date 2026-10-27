package defpackage;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import defpackage.al2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;
import org.chromium.chrome.browser.password_manager.a;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: o0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ViewOnClickListenerC0202o0 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ ViewOnClickListenerC0202o0(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int iW;
        String str;
        int i = this.e;
        int i2 = this.f;
        Object obj = this.g;
        switch (i) {
            case 0:
                AccountChooserDialog accountChooserDialog = ((a) obj).e;
                accountChooserDialog.m = accountChooserDialog.f[i2];
                AlertDialog alertDialog = accountChooserDialog.o;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                break;
            default:
                vk2 vk2Var = (vk2) obj;
                vk2Var.h.notifyEvent("sharing_hub_link_toggle_clicked");
                int i3 = 0;
                xk2 xk2Var = vk2Var.g;
                if (i2 != 3) {
                    if (vk2Var.m.intValue() == 1) {
                        vk2Var.m = 0;
                        iW = R$string.link_toggle_include_link;
                    } else {
                        vk2Var.m = 1;
                        iW = vk2.w(i2);
                    }
                    vk2Var.A(iW);
                    al2.a aVar = new al2.a(vk2Var.m.intValue(), i2);
                    al2.a(aVar, "InProgress");
                    xk2Var.p(aVar, vk2Var.l);
                } else {
                    int i4 = vk2Var.l;
                    if (i4 == 1) {
                        vk2Var.l = 0;
                        vk2Var.m = 1;
                        i3 = R$string.link_to_text_success_text_toast_message;
                        str = "SharingHubAndroid.LinkGeneration.Text";
                    } else if (i4 == 0) {
                        vk2Var.l = 1;
                        vk2Var.m = 0;
                        i3 = R$string.link_to_text_success_link_toast_message;
                        str = "SharingHubAndroid.LinkGeneration.Link";
                    } else if (i4 == 2) {
                        vk2Var.m = 1;
                        i3 = R$string.link_to_text_failure_toast_message_v2;
                        str = "SharingHubAndroid.LinkGeneration.Failure";
                    } else {
                        str = "";
                    }
                    vk2Var.A(i3);
                    n3.X0(str);
                    xk2Var.p(new al2.a(vk2Var.m.intValue(), 3), vk2Var.l);
                }
                break;
        }
    }
}
