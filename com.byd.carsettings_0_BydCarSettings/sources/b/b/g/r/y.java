package b.b.g.r;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TimeOutHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class y extends Handler {
    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        super.handleMessage(message);
        if (message == null) {
            return;
        }
        b.b.a.c.a aVar = new b.b.a.c.a(message.what, message.arg1);
        aVar.k(message.obj);
        EventBus.getDefault().post(aVar);
    }
}
