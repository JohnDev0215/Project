package com.amazon.mp3.baseviews.model.configuration;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayerModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/PlayerModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "()V", "shouldShowContextMenuDownloadButton", "", "entity", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "shouldShowContextMenuViewPlayQueueButton", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerModelConfiguration extends ModelConfiguration {
    public PlayerModelConfiguration() {
        super(null, 1, null);
    }

    public final boolean shouldShowContextMenuDownloadButton(EntityEligibilitiesProvider entity) {
        if (!Feature.player_context_menu_download_button.isEnabled()) {
            if (!(entity == null ? false : shouldShowContextMenuDownloadUpsellButton(entity))) {
                return false;
            }
        }
        return true;
    }

    public final boolean shouldShowContextMenuViewPlayQueueButton() {
        return Feature.player_context_menu_view_play_queue_button.isEnabled();
    }
}
