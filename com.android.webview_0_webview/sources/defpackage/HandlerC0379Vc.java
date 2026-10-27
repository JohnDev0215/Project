package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* JADX INFO: renamed from: Vc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC0379Vc extends HandlerC0347Tg {
    public HandlerC0379Vc(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            Pair pair = (Pair) message.obj;
            try {
                ((InterfaceC0235Nc) pair.first).a((InterfaceC0217Mc) pair.second);
                return;
            } catch (RuntimeException e) {
                throw e;
            }
        }
        if (i == 2) {
            ((BasePendingResult) message.obj).h(Status.y);
            return;
        }
        StringBuilder sb = new StringBuilder(45);
        sb.append("Don't know how to handle message: ");
        sb.append(i);
        Log.wtf("BasePendingResult", sb.toString(), new Exception());
    }
}
