package com.amazon.music.platform.playback.capabilities.reduce;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.OfflineStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReducibleOfflineStatus.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleOfflineStatus;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "Lcom/amazon/music/platform/playback/OfflineStatus;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface ReducibleOfflineStatus extends Reducible<OfflineStatus> {

    /* JADX INFO: compiled from: ReducibleOfflineStatus.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static Outcome<OfflineStatus> reduce(ReducibleOfflineStatus reducibleOfflineStatus, Outcome<OfflineStatus> outcome1, Outcome<OfflineStatus> outcome2) {
            Intrinsics.checkNotNullParameter(outcome1, "outcome1");
            Intrinsics.checkNotNullParameter(outcome2, "outcome2");
            Outcome<OfflineStatus> outcomeReduceFailures = ReducibleKt.reduceFailures(reducibleOfflineStatus, outcome1, outcome2);
            if (outcomeReduceFailures != null) {
                return outcomeReduceFailures;
            }
            OfflineStatus offlineStatus = (OfflineStatus) ((Outcome.Success) outcome1).getValue();
            OfflineStatus offlineStatus2 = (OfflineStatus) ((Outcome.Success) outcome2).getValue();
            return new Outcome.Success(new OfflineStatus(offlineStatus.getIsDownloadable() || offlineStatus2.getIsDownloadable(), offlineStatus.getIsDownloaded() || offlineStatus2.getIsDownloaded(), offlineStatus.getIsPlayableOffline() || offlineStatus2.getIsPlayableOffline()));
        }
    }
}
