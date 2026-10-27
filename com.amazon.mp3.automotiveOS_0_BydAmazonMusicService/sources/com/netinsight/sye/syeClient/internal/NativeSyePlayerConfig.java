package com.netinsight.sye.syeClient.internal;

import a.b.a.a.o.d.b.k;
import com.netinsight.sye.syeClient.audio.SyeAudioPreferences;
import com.netinsight.sye.syeClient.video.SyeVideoPreferences;

/* JADX INFO: loaded from: classes7.dex */
public final class NativeSyePlayerConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f763a = create(k.b.a());
    public volatile boolean b;

    public final long a() {
        return this.f763a;
    }

    public final void a(boolean z) {
        setAllowSuperBurst(this.f763a, z);
    }

    public final native long create(boolean z);

    public final native void delete(long j);

    public final void finalize() {
        if (this.b) {
            return;
        }
        this.b = true;
        delete(this.f763a);
    }

    public final native SyeAudioPreferences getAudioPreferences(long j);

    public final native int getPlayingStallThresholdMillis(long j);

    public final native int getPresentationDelayMillis(long j);

    public final native int getStallPlayingThresholdMillis(long j);

    public final native SyeVideoPreferences getVideoPreferences(long j);

    public final native void setAllowSuperBurst(long j, boolean z);

    public final native void setAudioPreferences(long j, SyeAudioPreferences syeAudioPreferences);

    public final native void setPlayingStallThresholdMillis(long j, int i);

    public final native void setPresentationDelayMillis(long j, int i);

    public final native void setStallPlayingThresholdMillis(long j, int i);

    public final native void setVideoPreferences(long j, SyeVideoPreferences syeVideoPreferences);
}
