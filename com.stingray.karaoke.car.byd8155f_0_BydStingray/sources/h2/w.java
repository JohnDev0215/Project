package h2;

import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Message f15246a;

    public final void a() {
        this.f15246a = null;
        ArrayList arrayList = x.f15247b;
        synchronized (arrayList) {
            if (arrayList.size() < 50) {
                arrayList.add(this);
            }
        }
    }

    public final void b() {
        Message message = this.f15246a;
        message.getClass();
        message.sendToTarget();
        a();
    }
}
