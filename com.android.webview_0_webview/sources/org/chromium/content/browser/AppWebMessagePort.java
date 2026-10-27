package org.chromium.content.browser;

import J.N;
import android.os.Handler;
import android.os.Looper;
import com.android.webview.R;
import defpackage.AbstractC0823gK;
import defpackage.C0767fK;
import defpackage.C0854gw;
import defpackage.C0910hw;
import defpackage.C0965iw;
import defpackage.C0990jK;
import defpackage.C1045kJ;
import defpackage.C1046kK;
import defpackage.C1213nK;
import defpackage.C1268oK;
import defpackage.Ey;
import defpackage.GM;
import defpackage.KJ;
import defpackage.LJ;
import defpackage.OI;
import defpackage.QJ;
import defpackage.UD;
import defpackage.Uv;
import defpackage.VJ;
import defpackage.WJ;
import defpackage.Wv;
import defpackage.YJ;
import defpackage.ZJ;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AppWebMessagePort implements MessagePort {
    public static final C1045kJ g = new C1045kJ(0);
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public LJ e;
    public OI f;

    public AppWebMessagePort(VJ vj) {
        this.e = vj.z();
        this.f = new OI(vj);
    }

    public static AppWebMessagePort[] f() {
        LJ lj = AbstractC0823gK.a;
        QJ qj = QJ.c;
        CoreImpl coreImpl = (CoreImpl) lj;
        ByteBuffer byteBufferA = coreImpl.a(8);
        byteBufferA.putInt(0, 8);
        byteBufferA.putInt(4, qj.a);
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(coreImpl, byteBufferA);
        if (resultAnd.a == 0) {
            return new AppWebMessagePort[]{new AppWebMessagePort(new C0990jK(coreImpl, ((Integer) ((C0767fK) resultAnd.b).a).intValue())), new AppWebMessagePort(new C0990jK(coreImpl, ((Integer) ((C0767fK) resultAnd.b).b).intValue()))};
        }
        throw new WJ(resultAnd.a);
    }

    private int releaseNativeHandle() {
        this.b = true;
        VJ vjG = this.f.e();
        this.f = null;
        return vjG.t();
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean a() {
        return this.b;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void b(UD ud, Handler handler) {
        if (this.a || this.b) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        this.c = true;
        this.f.v = new Ey(handler == null ? Looper.getMainLooper() : handler.getLooper(), ud);
        if (this.d) {
            return;
        }
        OI oi = this.f;
        ((WatcherImpl) oi.u).a(oi.t, KJ.c, oi.s);
        this.d = true;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean c() {
        return this.a;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void close() {
        if (this.b) {
            throw new IllegalStateException("Port is already transferred");
        }
        if (this.a) {
            return;
        }
        this.a = true;
        this.f.close();
        this.f = null;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public boolean d() {
        return this.c;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public void e(String str, MessagePort[] messagePortArr) {
        if (this.a || this.b) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        VJ[] vjArr = new VJ[messagePortArr == null ? 0 : messagePortArr.length];
        if (messagePortArr != null) {
            for (MessagePort messagePort : messagePortArr) {
                if (messagePort.equals(this)) {
                    throw new IllegalStateException("Source port cannot be transferred");
                }
                if (messagePort.c() || messagePort.a()) {
                    throw new IllegalStateException("Port is already closed or transferred");
                }
                if (messagePort.d()) {
                    throw new IllegalStateException("Port is already started");
                }
            }
            for (int i = 0; i < messagePortArr.length; i++) {
                AppWebMessagePort appWebMessagePort = (AppWebMessagePort) messagePortArr[i];
                appWebMessagePort.b = true;
                VJ vjG = appWebMessagePort.f.e();
                appWebMessagePort.f = null;
                vjArr[i] = vjG;
            }
        }
        this.c = true;
        C0965iw c0965iw = new C0965iw();
        Uv uv = new Uv();
        c0965iw.b = uv;
        byte[] bArrMk6SEKCp = N.Mk6SEKCp(str);
        C1213nK c1213nK = new C1213nK();
        if (bArrMk6SEKCp.length <= R.anim.abc_fade_in) {
            c1213nK.a = 0;
            c1213nK.b = bArrMk6SEKCp;
        } else {
            LJ lj = AbstractC0823gK.a;
            C1268oK c1268oK = new C1268oK();
            YJ yj = YJ.c;
            long length = bArrMk6SEKCp.length;
            CoreImpl coreImpl = (CoreImpl) lj;
            Objects.requireNonNull(coreImpl);
            ByteBuffer byteBufferA = coreImpl.a(8);
            byteBufferA.putInt(0, 8);
            byteBufferA.putInt(4, yj.a);
            ResultAnd resultAnd = (ResultAnd) N.MZ0nRPS0(coreImpl, byteBufferA, length);
            if (resultAnd.a != 0) {
                throw new WJ(resultAnd.a);
            }
            C1046kK c1046kK = new C1046kK(coreImpl, ((Integer) resultAnd.b).intValue());
            c1268oK.b = c1046kK;
            c1268oK.c = bArrMk6SEKCp.length;
            c1046kK.d(0L, bArrMk6SEKCp.length, ZJ.c).put(bArrMk6SEKCp);
            c1213nK.a = 1;
            c1213nK.c = c1268oK;
        }
        uv.b = c1213nK;
        Uv uv2 = c0965iw.b;
        uv2.c = new C0910hw[0];
        uv2.j = new Wv[0];
        uv2.d = null;
        c0965iw.e = new C0854gw[0];
        c0965iw.f = new GM[0];
        c0965iw.c = vjArr;
        c0965iw.d = new VJ[0];
        this.f.q(c0965iw.b(this.e, g));
    }
}
