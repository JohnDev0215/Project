package com.amazon.mp3.playback.harley;

import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EngineError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyDownloadCoordinatorListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001aH\u0016¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListener;", "Lcom/amazon/digitalmusicplayback/DownloadCoordinatorListener;", "()V", "backgroundDownloadSessionIdentifierSet", "", "p0", "", "backgroundDownloadsDidFinish", "configUpdated", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "downloadComplete", "ID", "location", "downloadPaused", "downloadRemoved", "downloadResumed", "downloadsStalled", "stalled", "", "invalidDownloadDetected", "id", "mergeCompleted", "mergeUpdate", "sourceDirectory", "p1", "", "progressUpdate", "percentageCompleted", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class HarleyDownloadCoordinatorListener extends DownloadCoordinatorListener {
    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void backgroundDownloadSessionIdentifierSet(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void backgroundDownloadsDidFinish() {
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void configUpdated(EngineError engineError) {
        Intrinsics.checkNotNullParameter(engineError, "engineError");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadComplete(String ID, EngineError engineError, String location) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Intrinsics.checkNotNullParameter(location, "location");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadPaused(String ID, EngineError engineError) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadRemoved(String ID, EngineError engineError) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadResumed(String ID, EngineError engineError) {
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
    public void mergeCompleted(String p0, EngineError engineError) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void mergeUpdate(String sourceDirectory, float p1) {
        Intrinsics.checkNotNullParameter(sourceDirectory, "sourceDirectory");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void progressUpdate(String ID, float percentageCompleted) {
        Intrinsics.checkNotNullParameter(ID, "ID");
    }
}
