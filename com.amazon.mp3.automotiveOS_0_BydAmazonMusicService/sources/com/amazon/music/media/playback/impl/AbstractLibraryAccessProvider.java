package com.amazon.music.media.playback.impl;

import com.amazon.music.media.playback.OnLibraryChangedListener;
import com.amazon.music.media.playback.config.LibraryAccessProvider;
import com.amazon.music.media.playback.util.ListenerSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractLibraryAccessProvider implements LibraryAccessProvider {
    private final ListenerSet<OnLibraryChangedListener> libraryListeners = new ListenerSet<>(OnLibraryChangedListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES);

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public void addOnLibraryChangedListener(OnLibraryChangedListener onLibraryChangedListener) {
        this.libraryListeners.add(onLibraryChangedListener);
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public void removeOnLibraryChangedListener(OnLibraryChangedListener onLibraryChangedListener) {
        this.libraryListeners.remove(onLibraryChangedListener);
    }

    protected void notifyLibraryChanged() {
        this.libraryListeners.proxy().OnLibraryChanged();
    }
}
