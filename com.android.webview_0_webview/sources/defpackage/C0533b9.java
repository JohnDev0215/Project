package defpackage;

import android.os.Handler;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: renamed from: b9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0533b9 extends WebMessagePort {
    public MessagePort a;

    public C0533b9(MessagePort messagePort) {
        this.a = messagePort;
    }

    public static WebMessagePort[] a(MessagePort[] messagePortArr) {
        if (messagePortArr == null) {
            return null;
        }
        WebMessagePort[] webMessagePortArr = new WebMessagePort[messagePortArr.length];
        for (int i = 0; i < messagePortArr.length; i++) {
            webMessagePortArr[i] = new C0533b9(messagePortArr[i]);
        }
        return webMessagePortArr;
    }

    public static MessagePort[] b(WebMessagePort[] webMessagePortArr) {
        if (webMessagePortArr == null) {
            return null;
        }
        MessagePort[] messagePortArr = new MessagePort[webMessagePortArr.length];
        for (int i = 0; i < webMessagePortArr.length; i++) {
            messagePortArr[i] = ((C0533b9) webMessagePortArr[i]).a;
        }
        return messagePortArr;
    }

    @Override // android.webkit.WebMessagePort
    public void close() {
        this.a.close();
    }

    @Override // android.webkit.WebMessagePort
    public void postMessage(WebMessage webMessage) {
        this.a.e(webMessage.getData(), b(webMessage.getPorts()));
    }

    @Override // android.webkit.WebMessagePort
    public void setWebMessageCallback(WebMessagePort.WebMessageCallback webMessageCallback) {
        this.a.b(new C0477a9(this, webMessageCallback), null);
    }

    @Override // android.webkit.WebMessagePort
    public void setWebMessageCallback(WebMessagePort.WebMessageCallback webMessageCallback, Handler handler) {
        this.a.b(new C0477a9(this, webMessageCallback), handler);
    }
}
