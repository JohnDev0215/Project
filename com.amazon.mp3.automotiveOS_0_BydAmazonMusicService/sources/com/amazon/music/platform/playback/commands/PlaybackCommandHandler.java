package com.amazon.music.platform.playback.commands;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackCommandHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "", "handle", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface PlaybackCommandHandler {
    void handle(PlaybackCommandEvent event);
}
