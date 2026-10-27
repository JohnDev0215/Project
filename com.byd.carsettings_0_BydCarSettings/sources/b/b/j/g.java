package b.b.j;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TimeOutHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends Handler {
    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        super.handleMessage(message);
        if (message == null) {
            return;
        }
        f fVar = new f(message.what, message.arg1);
        fVar.b(message.obj);
        EventBus.getDefault().post(fVar);
    }
}
