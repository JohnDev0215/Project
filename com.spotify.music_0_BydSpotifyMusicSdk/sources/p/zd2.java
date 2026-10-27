package p;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class zd2 implements Handler.Callback {
    public final /* synthetic */ gh2 a;

    public zd2(gh2 gh2Var) {
        this.a = gh2Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        gh2 gh2Var = this.a;
        u4.z(message.obj);
        synchronized (gh2Var.e) {
            u4.z(gh2Var.l);
            throw null;
        }
    }
}
