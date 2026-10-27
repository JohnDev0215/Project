package com.amazon.video.sdk.player.playlist;

import com.amazon.avod.playbackclient.presentation.PresentationCache;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.player.PlayerImpl;
import com.google.android.gms.cast.CastStatusCodes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistFeatureFactory.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureFactoryImpl;", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureFactory;", "config", "Lcom/amazon/video/sdk/player/PlayerConfig;", "localContentManager", "Lcom/amazon/video/sdk/content/LocalContentManager;", "presentationCache", "Lcom/amazon/avod/playbackclient/presentation/PresentationCache;", "(Lcom/amazon/video/sdk/player/PlayerConfig;Lcom/amazon/video/sdk/content/LocalContentManager;Lcom/amazon/avod/playbackclient/presentation/PresentationCache;)V", "createPlaylistFeature", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistFeatureFactoryImpl implements PlaylistFeatureFactory {
    private final PlayerConfig config;
    private final LocalContentManager localContentManager;
    private final PresentationCache presentationCache;

    public PlaylistFeatureFactoryImpl(PlayerConfig config, LocalContentManager localContentManager, PresentationCache presentationCache) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(localContentManager, "localContentManager");
        Intrinsics.checkNotNullParameter(presentationCache, "presentationCache");
        this.config = config;
        this.localContentManager = localContentManager;
        this.presentationCache = presentationCache;
    }

    @Override // com.amazon.video.sdk.player.playlist.PlaylistFeatureFactory
    public PlaylistFeatureImpl createPlaylistFeature(PlayerImpl player) {
        Intrinsics.checkNotNullParameter(player, "player");
        return new PlaylistFeatureImpl(player.getSdkConfig(), this.config, this.localContentManager, this.presentationCache, player, null, null, null, null, null, null, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED, null);
    }
}
