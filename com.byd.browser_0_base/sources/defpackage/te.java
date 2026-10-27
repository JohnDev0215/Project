package defpackage;

import android.os.MessageQueue;
import org.chromium.base.CollectionUtil;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class te implements MessageQueue.IdleHandler {
    @Override // android.os.MessageQueue.IdleHandler
    public final boolean queueIdle() {
        CollectionUtil.f(ue.d.values());
        return false;
    }
}
