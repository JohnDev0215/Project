package n2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/* JADX INFO: renamed from: n2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1901a extends BroadcastReceiver implements Runnable {
    public final SurfaceHolderCallbackC1920u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f19620m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t4.e f19621n;

    public RunnableC1901a(t4.e eVar, Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u) {
        this.f19621n = eVar;
        this.f19620m = handler;
        this.l = surfaceHolderCallbackC1920u;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            this.f19620m.post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f19621n.f22462m) {
            this.l.l.x1(-1, 3, false);
        }
    }
}
