package com.amazon.mp3.library.service.sync;

import com.amazon.mp3.playlist.api.PlaylistChangeEntry;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface PlaylistSyncHandler {
    void onGetChangedPlaylistsSinceCheckpointPageResponseReceived(Collection<PlaylistChangeEntry> changedPlaylists, boolean isForceUpdate);
}
