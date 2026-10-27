package spi;

import android.os.IBinder;

/* JADX INFO: compiled from: RemoteServiceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class md implements IBinder.DeathRecipient {
    public final /* synthetic */ Class a;
    public final /* synthetic */ ld b;

    public md(ld ldVar, Class cls) {
        this.b = ldVar;
        this.a = cls;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.b.a.remove(this.a.getCanonicalName());
    }
}
