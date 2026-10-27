package com.amazon.mp3.library.provider;

import android.net.Uri;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface StateProvider<T> {

    public interface Listener<U> {
        void onStateChanged(U item, int ownershipState, int downloadState);
    }

    Uri getAllTracksUri(T item);

    Uri getContentUri(T item);

    boolean isDownloaded(T item);

    boolean isDownloading(T item);

    boolean isInLibrary(T item);

    boolean isPurchased(T item);

    void requestState(T item, @Nullable Listener<T> listener);
}
