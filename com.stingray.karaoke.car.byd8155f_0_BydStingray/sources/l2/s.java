package l2;

import android.os.ConditionVariable;

/* JADX INFO: loaded from: classes.dex */
public final class s extends Thread {
    public final /* synthetic */ ConditionVariable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t f18494m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(t tVar, ConditionVariable conditionVariable) {
        super("ExoPlayer:SimpleCacheInit");
        this.f18494m = tVar;
        this.l = conditionVariable;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        synchronized (this.f18494m) {
            this.l.open();
            t.a(this.f18494m);
            this.f18494m.f18497b.getClass();
        }
    }
}
