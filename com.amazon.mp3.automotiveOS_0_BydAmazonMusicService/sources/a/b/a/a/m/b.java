package a.b.a.a.m;

import a.b.a.a.k.d;
import com.netinsight.sye.syeClient.generated.enums.PlaybackType;
import com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ISyeTimelineInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f85a = new a();
    public final PlaybackType b;
    public final long c;
    public final long d;
    public final double e;

    public static final class a {
    }

    public b(d dVar) {
        this.b = dVar.c();
        this.c = dVar.a();
        this.d = dVar.b();
        this.e = dVar.d();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public long getCurrentPosUtcMillis() {
        return this.c;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public long getLivePosUtcMillis() {
        return this.d;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public PlaybackType getPlaybackType() {
        return this.b;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public boolean getShowTimeline() {
        return false;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public double getThumbnailFillrate() {
        return this.e;
    }
}
