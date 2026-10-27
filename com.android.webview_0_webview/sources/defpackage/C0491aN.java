package defpackage;

import android.os.Handler;
import java.lang.reflect.InvocationHandler;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageCallbackBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface;

/* JADX INFO: renamed from: aN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0491aN implements WebMessagePortBoundaryInterface {
    public MessagePort a;

    public C0491aN(MessagePort messagePort) {
        this.a = messagePort;
    }

    public static InvocationHandler[] a(MessagePort[] messagePortArr) {
        if (messagePortArr == null) {
            return null;
        }
        int length = messagePortArr.length;
        C0491aN[] c0491aNArr = new C0491aN[length];
        for (int i = 0; i < messagePortArr.length; i++) {
            c0491aNArr[i] = new C0491aN(messagePortArr[i]);
        }
        InvocationHandler[] invocationHandlerArr = new InvocationHandler[length];
        for (int i2 = 0; i2 < length; i2++) {
            invocationHandlerArr[i2] = JM.c(c0491aNArr[i2]);
        }
        return invocationHandlerArr;
    }

    public static MessagePort[] b(InvocationHandler[] invocationHandlerArr) {
        if (invocationHandlerArr == null) {
            return null;
        }
        MessagePort[] messagePortArr = new MessagePort[invocationHandlerArr.length];
        for (int i = 0; i < invocationHandlerArr.length; i++) {
            messagePortArr[i] = ((C0491aN) JM.d(invocationHandlerArr[i])).a;
        }
        return messagePortArr;
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface
    public void close() {
        C0826gN.a(34);
        this.a.close();
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface
    public void postMessage(InvocationHandler invocationHandler) {
        C0826gN.a(35);
        WebMessageBoundaryInterface webMessageBoundaryInterface = (WebMessageBoundaryInterface) JM.a(WebMessageBoundaryInterface.class, invocationHandler);
        this.a.e(webMessageBoundaryInterface.getData(), b(webMessageBoundaryInterface.getPorts()));
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface
    public void setWebMessageCallback(InvocationHandler invocationHandler) {
        C0826gN.a(36);
        this.a.b(new ZM(this, new XM((WebMessageCallbackBoundaryInterface) JM.a(WebMessageCallbackBoundaryInterface.class, invocationHandler))), null);
    }

    @Override // org.chromium.support_lib_boundary.WebMessagePortBoundaryInterface
    public void setWebMessageCallback(InvocationHandler invocationHandler, Handler handler) {
        if (handler != null) {
            C0826gN.a(37);
        }
        this.a.b(new ZM(this, new XM((WebMessageCallbackBoundaryInterface) JM.a(WebMessageCallbackBoundaryInterface.class, invocationHandler))), handler);
    }
}
