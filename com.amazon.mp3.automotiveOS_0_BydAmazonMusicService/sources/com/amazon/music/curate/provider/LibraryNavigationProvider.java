package com.amazon.music.curate.provider;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface LibraryNavigationProvider {
    void onNavigationButtonClicked(final String category, final List<Map<String, String>> contentInfo);

    void showCatalogPlaylist(final String playlistId, final List<Map<String, String>> contentInfo);

    void showLibraryPlaylist(final String playlistId, final List<Map<String, String>> contentInfo);
}
