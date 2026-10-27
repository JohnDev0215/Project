package p;

import android.os.Bundle;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class f30 implements Callable {
    public final /* synthetic */ long c;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.common.a e;

    public f30(com.google.firebase.crashlytics.internal.common.a aVar, long j) {
        this.e = aVar;
        this.c = j;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Bundle bundle = new Bundle();
        bundle.putInt("fatal", 1);
        bundle.putLong("timestamp", this.c);
        this.e.k.i(bundle);
        return null;
    }
}
