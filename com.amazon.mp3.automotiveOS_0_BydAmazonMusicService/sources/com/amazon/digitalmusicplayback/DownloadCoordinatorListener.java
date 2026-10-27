package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DownloadCoordinatorListener {
    public abstract void backgroundDownloadSessionIdentifierSet(String str);

    public abstract void backgroundDownloadsDidFinish();

    public abstract void configUpdated(EngineError engineError);

    public abstract void downloadComplete(String str, EngineError engineError, String str2);

    public abstract void downloadPaused(String str, EngineError engineError);

    public abstract void downloadRemoved(String str, EngineError engineError);

    public abstract void downloadResumed(String str, EngineError engineError);

    public abstract void downloadsStalled(boolean z, EngineError engineError);

    public abstract void invalidDownloadDetected(String str, EngineError engineError);

    public abstract void mergeCompleted(String str, EngineError engineError);

    public abstract void mergeUpdate(String str, float f);

    public abstract void progressUpdate(String str, float f);
}
