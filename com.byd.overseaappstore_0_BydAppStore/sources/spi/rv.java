package spi;

import android.os.IBinder;

/* JADX INFO: loaded from: classes2.dex */
public final class rv implements IBinder.DeathRecipient {
    public final /* synthetic */ Class a;
    public final /* synthetic */ qv b;

    public rv(qv qvVar, Class cls) {
        this.b = qvVar;
        this.a = cls;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.b.a.remove(this.a.getCanonicalName());
    }
}
