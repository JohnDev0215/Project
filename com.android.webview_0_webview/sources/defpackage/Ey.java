package defpackage;

import J.N;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.chromium.content.browser.AppWebMessagePort;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ey extends Handler implements InterfaceC1101lJ {
    public final UD s;

    public Ey(Looper looper, UD ud) {
        super(looper);
        this.s = ud;
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 1) {
            throw new IllegalStateException("undefined message");
        }
        Dy dy = (Dy) message.obj;
        String strMWL_OG7s = N.MWL_OG7s(dy.a);
        if (strMWL_OG7s == null) {
            Ts.f("AppWebMessagePort", "Undecodable message received, dropping message", new Object[0]);
        } else {
            this.s.a(strMWL_OG7s, dy.b);
        }
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C0965iw c0965iwC = C0965iw.c(c0989jJ.a().b());
            int length = c0965iwC.c.length;
            AppWebMessagePort[] appWebMessagePortArr = new AppWebMessagePort[length];
            for (int i = 0; i < length; i++) {
                appWebMessagePortArr[i] = new AppWebMessagePort(c0965iwC.c[i]);
            }
            Dy dy = new Dy(null);
            dy.a = AbstractC1158mK.a(c0965iwC.b.b);
            dy.b = appWebMessagePortArr;
            sendMessage(obtainMessage(1, dy));
            return true;
        } catch (TI e) {
            Ts.f("AppWebMessagePort", "Error deserializing message", e);
            return false;
        }
    }
}
