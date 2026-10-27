package com.amazon.music.platform.playback.commands;

import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackControls.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0007\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004R\u0014\u0010\u000b\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/playback/commands/PlaybackControls;", "", "Lcom/amazon/music/platform/playback/commands/PlaybackCommand;", "getPlay", "()Lcom/amazon/music/platform/playback/commands/PlaybackCommand;", PageUriUtils.DO_PLAY_VALUE, "getPause", "pause", "getPreviousItem", "previousItem", "getNextItem", "nextItem", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface PlaybackControls {
    PlaybackCommand getNextItem();

    PlaybackCommand getPause();

    PlaybackCommand getPlay();

    PlaybackCommand getPreviousItem();
}
