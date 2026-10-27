package g4;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: renamed from: g4.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1295A implements Handler.Callback {
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        ((x) message.obj).e();
        return true;
    }
}
