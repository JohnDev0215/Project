package b.a.a.h;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class j extends Handler {
    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        super.handleMessage(message);
        b.a.a.e.a aVar = new b.a.a.e.a(message.what, message.arg1);
        aVar.e(message.obj);
        EventBus.getDefault().post(aVar);
    }
}
