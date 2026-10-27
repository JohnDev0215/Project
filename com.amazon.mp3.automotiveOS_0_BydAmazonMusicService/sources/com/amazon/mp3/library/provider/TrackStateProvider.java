package com.amazon.mp3.library.provider;

import com.amazon.mp3.prime.browse.metadata.PrimeTrack;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackStateProvider extends StateProvider<PrimeTrack> {
    boolean isAddingToLibrary(PrimeTrack track);

    void monitorDownloadProgress(PrimeTrack track);

    void setAddingToLibrary(PrimeTrack track, boolean isAddingToLibrary);
}
