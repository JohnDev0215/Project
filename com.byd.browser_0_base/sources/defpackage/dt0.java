package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: GoogleCloudMessagingV2.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt0 extends Handler {
    public final /* synthetic */ BlockingQueue a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dt0(Looper looper, LinkedBlockingQueue linkedBlockingQueue) {
        super(looper);
        this.a = linkedBlockingQueue;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.a.add((Intent) message.obj);
    }
}
