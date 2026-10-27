package com.amazon.mp3.util.extensions;

import com.amazon.music.downloads.DownloadState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Download.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002¨\u0006\u0005"}, d2 = {"isTerminal", "", "Lcom/amazon/music/downloads/DownloadState;", "toAppDownloadState", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class DownloadKt {

    /* JADX INFO: compiled from: Download.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadState.values().length];
            iArr[DownloadState.CANCELED.ordinal()] = 1;
            iArr[DownloadState.ERROR.ordinal()] = 2;
            iArr[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            iArr[DownloadState.PAUSED_PRIORITY.ordinal()] = 4;
            iArr[DownloadState.PAUSED_USER.ordinal()] = 5;
            iArr[DownloadState.DOWNLOADED.ordinal()] = 6;
            iArr[DownloadState.QUEUED.ordinal()] = 7;
            iArr[DownloadState.DOWNLOADING.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean isTerminal(DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(downloadState, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public static final int toAppDownloadState(DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(downloadState, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()]) {
            case 1:
            default:
                return 5;
            case 2:
                return 2;
            case 3:
            case 4:
            case 5:
                return 3;
            case 6:
                return 0;
            case 7:
                return 1;
            case 8:
                return 4;
        }
    }
}
