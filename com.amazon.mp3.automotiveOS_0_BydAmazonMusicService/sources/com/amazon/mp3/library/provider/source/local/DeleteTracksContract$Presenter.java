package com.amazon.mp3.library.provider.source.local;

/* JADX INFO: loaded from: classes3.dex */
public interface DeleteTracksContract$Presenter {
    void detachView();

    void executeDeletionOnCurrentThread(String tracksUri);
}
