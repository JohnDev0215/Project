package com.amazon.video.sdk.download;

import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadedContentState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentState;", "", "(Ljava/lang/String;I)V", "Downloading", "Downloaded", "Deleting", "Deleted", "Error", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum DownloadedContentState {
    Downloading,
    Downloaded,
    Deleting,
    Deleted,
    Error;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: DownloadedContentState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentState$Companion;", "", "()V", "fromPlaybackDownloadState", "Lcom/amazon/video/sdk/download/DownloadedContentState;", "userDownloadState", "Lcom/amazon/avod/userdownload/UserDownloadState;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadedContentState fromPlaybackDownloadState(UserDownloadState userDownloadState) {
            Intrinsics.checkNotNullParameter(userDownloadState, "userDownloadState");
            if (userDownloadState == UserDownloadState.WAITING) {
                ImmutableSet<PauseCause> downloadWaitingCauses = PlaybackDownloads.getInstance().getDownloadManager().getDownloadWaitingCauses();
                Intrinsics.checkNotNullExpressionValue(downloadWaitingCauses, "getInstance().downloadMa…ger.downloadWaitingCauses");
                DLog.warnf("DownloadOperation WAITING due to cause: %s", Arrays.toString(downloadWaitingCauses.toArray()));
            }
            if (userDownloadState == UserDownloadState.ERROR) {
                return DownloadedContentState.Error;
            }
            if (UserDownloadState.WORK_NEEDED_STATES.contains(userDownloadState)) {
                return DownloadedContentState.Downloading;
            }
            if (UserDownloadState.COMPLETE_STATES.contains(userDownloadState)) {
                return DownloadedContentState.Downloaded;
            }
            if (userDownloadState == UserDownloadState.DELETE_REQUESTED || userDownloadState == UserDownloadState.DELETING) {
                return DownloadedContentState.Deleting;
            }
            if (userDownloadState == UserDownloadState.DELETED) {
                return DownloadedContentState.Deleted;
            }
            throw new UnsupportedOperationException();
        }
    }
}
