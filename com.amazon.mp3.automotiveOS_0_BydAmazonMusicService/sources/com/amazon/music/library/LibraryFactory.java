package com.amazon.music.library;

/* JADX INFO: loaded from: classes4.dex */
public final class LibraryFactory {
    private LibraryService mService;

    public LibraryFactory(LibraryService libraryService) {
        this.mService = libraryService;
    }

    public PlaylistLibrary getPlaylistLibrary() {
        return new ThinPlaylistLibrary(this.mService);
    }
}
