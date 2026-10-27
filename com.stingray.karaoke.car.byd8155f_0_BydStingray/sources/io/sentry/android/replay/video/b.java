package io.sentry.android.replay.video;

import android.media.MediaMuxer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f16446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaMuxer f16447b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f16448e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f16449f;

    public b(String str, float f10) {
        this.f16446a = (long) (TimeUnit.SECONDS.toMicros(1L) / f10);
        this.f16447b = new MediaMuxer(str, 0);
    }
}
