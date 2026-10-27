package com.amazon.mp3.library.util;

import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PlaylistUnificationMetrics.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/library/util/PlaylistUnificationMetrics;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistUnificationMetrics {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: PlaylistUnificationMetrics.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/util/PlaylistUnificationMetrics$Companion;", "", "()V", "sendAutoMigratedUserPlaylistsCountEvent", "", "migratedPlaylistsCount", "", "notMigratedPlaylistsCount", "sendDeviceLibraryPlaylistsCountEvent", "devicePlaylistsCount", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void sendDeviceLibraryPlaylistsCountEvent(int devicePlaylistsCount) {
            MetricsLogger.sendEvent(FlexEvent.builder("deviceLibraryPlaylistsCount").withFlexNum1(Float.valueOf(devicePlaylistsCount)).build());
        }

        public final void sendAutoMigratedUserPlaylistsCountEvent(int migratedPlaylistsCount, int notMigratedPlaylistsCount) {
            MetricsLogger.sendEvent(FlexEvent.builder("autoMigratedUserPlaylistsCount").withFlexNum1(Float.valueOf(migratedPlaylistsCount)).withFlexNum2(Float.valueOf(notMigratedPlaylistsCount)).build());
        }
    }
}
