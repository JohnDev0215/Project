package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.feed.g;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sharing.shared_clipboard.SharedClipboardMessageHandler;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sl0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ sl0(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        String string;
        String string2;
        int i = this.e;
        o8 o8VarB = null;
        Object obj2 = this.h;
        Object obj3 = this.g;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                e eVar = (e) obj4;
                Boolean bool = (Boolean) obj;
                eVar.getClass();
                ((PropertyModel) obj3).l(yg2.b, bool.booleanValue());
                int iA = ((g) obj2).a();
                boolean zBooleanValue = bool.booleanValue();
                c cVar = eVar.n;
                if (iA == 2) {
                    cVar.I = zBooleanValue;
                    return;
                } else {
                    cVar.getClass();
                    return;
                }
            case 1:
                ((xk2) obj4).g((Runnable) obj2, (List) obj3, (List) obj);
                return;
            case 2:
                String str = (String) obj4;
                String str2 = (String) obj3;
                String str3 = (String) obj2;
                Integer num = (Integer) obj;
                if (num.intValue() == 0) {
                    new NotificationManagerProxyImpl(xz.a).d(11, "SharedClipboard");
                    return;
                }
                int iIntValue = num.intValue();
                Resources resources = xz.a.getResources();
                String string3 = resources.getString(R$string.browser_sharing_content_type_text);
                switch (iIntValue) {
                    case 1:
                    case 2:
                    case 4:
                        string = resources.getString(R$string.browser_sharing_error_dialog_title_generic_error, string3);
                        break;
                    case 3:
                        string = resources.getString(R$string.browser_sharing_shared_clipboard_error_dialog_title_payload_too_large);
                        break;
                    case 5:
                    case 6:
                        string = resources.getString(R$string.browser_sharing_error_dialog_title_internal_error, string3);
                        break;
                    default:
                        string = resources.getString(R$string.browser_sharing_error_dialog_title_internal_error, string3);
                        break;
                }
                int iIntValue2 = num.intValue();
                Resources resources2 = xz.a.getResources();
                switch (iIntValue2) {
                    case 1:
                        string2 = resources2.getString(R$string.browser_sharing_error_dialog_text_device_not_found, str);
                        break;
                    case 2:
                        string2 = resources2.getString(R$string.browser_sharing_error_dialog_text_network_error);
                        break;
                    case 3:
                        string2 = resources2.getString(R$string.browser_sharing_shared_clipboard_error_dialog_text_payload_too_large);
                        break;
                    case 4:
                        string2 = resources2.getString(R$string.browser_sharing_error_dialog_text_device_ack_timeout, str);
                        break;
                    case 5:
                    case 6:
                        string2 = resources2.getString(R$string.browser_sharing_error_dialog_text_internal_error);
                        break;
                    default:
                        string2 = resources2.getString(R$string.browser_sharing_error_dialog_text_internal_error);
                        break;
                }
                if (num.intValue() == 4 || num.intValue() == 2) {
                    Context context = xz.a;
                    o8VarB = o8.b(context, 0, new Intent(context, (Class<?>) SharedClipboardMessageHandler.TryAgainReceiver.class).putExtra("android.intent.extra.TEXT", str2).putExtra("SharedClipboard.EXTRA_DEVICE_GUID", str3).putExtra("SharedClipboard.EXTRA_DEVICE_CLIENT_NAME", str), 134217728, false);
                }
                Context context2 = xz.a;
                Resources resources3 = context2.getResources();
                qq qqVarD = n3.D("sharing", new o8(18, 11, "SharedClipboard"));
                qqVarD.k(string);
                NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
                notificationCompat$Builder.p = "SharedClipboard";
                notificationCompat$Builder.u = context2.getColor(R$color.google_red_600);
                notificationCompat$Builder.i = 1;
                qqVarD.p(R$drawable.ic_error_outline_red_24dp);
                qqVarD.j(string2);
                qqVarD.l(-1);
                qqVarD.h(true);
                if (o8VarB != null) {
                    qqVarD.c(o8VarB);
                    qqVarD.b(R$drawable.ic_cancel_circle, resources3.getString(R$string.try_again), o8VarB, 11);
                }
                lp1 lp1VarG = qqVarD.g(string2);
                new NotificationManagerProxyImpl(context2).c(lp1VarG);
                kp1.a.a.b(18, lp1VarG.a);
                return;
            case 3:
                yr3 yr3Var = (yr3) obj4;
                yr3Var.getClass();
                Profile.d();
                yr3Var.getClass();
                yr3Var.getClass();
                new xr3(null, (Callback) obj3, null);
                yr3Var.getClass();
                throw null;
            case 4:
                Map map = (Map) obj4;
                String str4 = (String) obj3;
                s62 s62Var = (s62) obj2;
                if (!((Boolean) obj).booleanValue()) {
                    s62Var.e(null);
                    return;
                } else {
                    map.remove(str4);
                    s62Var.b(null);
                    return;
                }
            default:
                mk2 mk2Var = (mk2) obj4;
                Callback callback = (Callback) obj3;
                Callback callback2 = (Callback) obj2;
                a8.s(mk2Var.e);
                if (((Boolean) obj).booleanValue()) {
                    callback.onResult(mk2Var.f);
                    return;
                } else {
                    callback2.onResult(null);
                    return;
                }
        }
    }
}
