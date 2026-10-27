package com.amazon.avod.content;

import com.amazon.avod.playback.sampling.SampleType;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentSessionEventListener {
    void onAuxiliaryDownloadEligibilityStateChanged(boolean z);

    void onContentFragmentDownloaded(@Nonnull SampleType sampleType, long j, long j2);

    void onContentMetadataAcquired(@Nonnull String str);

    void onDownloadComplete();

    void onDownloadStart(long j);

    void onFatalError(@Nonnull ContentException contentException);

    void onSessionEnding();
}
