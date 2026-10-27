package p;

import com.spotify.concurrency.asyncimpl.NativeTimerManagerThreadImpl;

/* JADX INFO: loaded from: classes.dex */
public final class u10 implements r10, sb2 {
    public final NativeTimerManagerThreadImpl a;
    public final t10 b = new t10(this);

    public u10() {
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImplCreate = NativeTimerManagerThreadImpl.Companion.create("Core Thread");
        nativeTimerManagerThreadImplCreate.start();
        this.a = nativeTimerManagerThreadImplCreate;
    }

    @Override // p.sb2
    public final void shutdown() {
        NativeTimerManagerThreadImpl nativeTimerManagerThreadImpl = this.a;
        nativeTimerManagerThreadImpl.stop();
        nativeTimerManagerThreadImpl.destroy();
    }
}
