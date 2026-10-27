package defpackage;

import J.N;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: renamed from: hv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0909hv extends C1187mv implements InterfaceC0853gv {
    public final Handler l;
    public final boolean m;

    public C0909hv(Handler handler, C1242nv c1242nv, boolean z) {
        super(c1242nv, "SingleThreadTaskRunnerImpl", 2);
        this.l = handler;
        this.m = z;
    }

    @Override // defpackage.InterfaceC0853gv
    public boolean b() {
        synchronized (this.f) {
            f();
        }
        Boolean boolValueOf = this.d == 0 ? null : Boolean.valueOf(N.MdFi6sVQ(this.d));
        return boolValueOf != null ? boolValueOf.booleanValue() : this.l.getLooper().getThread() == Thread.currentThread();
    }

    @Override // defpackage.C1187mv
    public void h() {
        Handler handler = this.l;
        if (handler == null) {
            return;
        }
        if (!this.m) {
            handler.post(this.e);
            return;
        }
        Message messageObtain = Message.obtain(handler, this.e);
        messageObtain.setAsynchronous(true);
        this.l.sendMessageAtFrontOfQueue(messageObtain);
    }
}
