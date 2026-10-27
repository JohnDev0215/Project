package com.amazon.mp3.playback.harley;

import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.music.downloads.DMMDownloadCoordinator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyDownloadCoordinatorListenerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListenerConverter;", "", "()V", "convert", "Lcom/amazon/digitalmusicplayback/DownloadCoordinatorListener;", "dmmDownloadCoordinatorListener", "Lcom/amazon/music/downloads/DMMDownloadCoordinator$DMMDownloadListener;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HarleyDownloadCoordinatorListenerConverter {
    public static final HarleyDownloadCoordinatorListenerConverter INSTANCE = new HarleyDownloadCoordinatorListenerConverter();

    private HarleyDownloadCoordinatorListenerConverter() {
    }

    public final DownloadCoordinatorListener convert(final DMMDownloadCoordinator.DMMDownloadListener dmmDownloadCoordinatorListener) {
        Intrinsics.checkNotNullParameter(dmmDownloadCoordinatorListener, "dmmDownloadCoordinatorListener");
        return new DownloadCoordinatorListener() { // from class: com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListenerConverter.convert.1
            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void backgroundDownloadSessionIdentifierSet(String id) {
                Intrinsics.checkNotNullParameter(id, "id");
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void backgroundDownloadsDidFinish() {
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void configUpdated(EngineError p0) {
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void downloadRemoved(String ID, EngineError engineError) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(engineError, "engineError");
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void downloadsStalled(boolean stalled, EngineError engineError) {
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void invalidDownloadDetected(String id, EngineError engineError) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(engineError, "engineError");
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void mergeCompleted(String from, EngineError error) {
                Intrinsics.checkNotNullParameter(from, "from");
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void mergeUpdate(String from, float percent) {
                Intrinsics.checkNotNullParameter(from, "from");
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void downloadComplete(String ID, EngineError engineError, String location) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(engineError, "engineError");
                Intrinsics.checkNotNullParameter(location, "location");
                dmmDownloadCoordinatorListener.downloadComplete(ID, HarleyToDownloadManagerErrorConverter.INSTANCE.convert(engineError), location);
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void downloadResumed(String ID, EngineError engineError) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(engineError, "engineError");
                dmmDownloadCoordinatorListener.downloadResumed(ID, HarleyToDownloadManagerErrorConverter.INSTANCE.convert(engineError));
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void downloadPaused(String ID, EngineError engineError) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(engineError, "engineError");
                dmmDownloadCoordinatorListener.downloadPaused(ID, HarleyToDownloadManagerErrorConverter.INSTANCE.convert(engineError));
            }

            @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
            public void progressUpdate(String ID, float percent) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                dmmDownloadCoordinatorListener.progressUpdate(ID, percent);
            }
        };
    }
}
