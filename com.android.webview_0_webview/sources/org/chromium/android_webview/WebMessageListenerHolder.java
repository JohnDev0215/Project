package org.chromium.android_webview;

import android.net.Uri;
import defpackage.AbstractC0823gK;
import defpackage.C0990jK;
import defpackage.C1102lK;
import defpackage.InterfaceC0016Aq;
import defpackage.JM;
import defpackage.OM;
import defpackage.Ts;
import defpackage.WM;
import defpackage.YM;
import java.util.Objects;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebMessageListenerHolder {
    public InterfaceC0016Aq a;

    public WebMessageListenerHolder(InterfaceC0016Aq interfaceC0016Aq) {
        this.a = interfaceC0016Aq;
    }

    public void onPostMessage(String str, String str2, boolean z, int[] iArr, JsReplyProxy jsReplyProxy) {
        MessagePort[] messagePortArr = new MessagePort[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            CoreImpl coreImpl = (CoreImpl) AbstractC0823gK.a;
            Objects.requireNonNull(coreImpl);
            messagePortArr[i] = new AppWebMessagePort(new C0990jK(new C1102lK(coreImpl, i2)));
        }
        InterfaceC0016Aq interfaceC0016Aq = this.a;
        Uri uri = Uri.parse(str2);
        YM ym = (YM) interfaceC0016Aq;
        if (JM.b(ym.c, "WEB_MESSAGE_LISTENER")) {
            ym.b.onPostMessage(ym.a, JM.c(new WM(str, messagePortArr)), uri, z, JM.c(new OM(jsReplyProxy)));
        } else {
            Ts.a("WebMsgLtrAdptr", "The AndroidX doesn't have feature: WEB_MESSAGE_LISTENER", new Object[0]);
        }
    }
}
