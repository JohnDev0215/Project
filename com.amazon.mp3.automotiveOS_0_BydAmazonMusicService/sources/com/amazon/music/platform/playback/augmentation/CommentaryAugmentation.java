package com.amazon.music.platform.playback.augmentation;

import com.amazon.music.platform.playback.augmentation.models.SeedContextInput;
import com.amazon.music.platform.playback.augmentation.models.SeedContextTypeEnum;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import kotlin.Metadata;

/* JADX INFO: compiled from: CommentaryAugmentation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/playback/augmentation/CommentaryAugmentation;", "Lcom/amazon/music/platform/playback/augmentation/SeedContextAugmentation;", "Lcom/amazon/music/platform/playback/augmentation/InteractionPatternAugmentation;", "isTrackSelected", "", "(Z)V", "CONTAINER_AND_TRACK_SEED_CONTEXT_VALUE", "", "TRACK_ONLY_SEED_CONTEXT_VALUE", "retrieveInteractionPattern", "Lcom/amazon/music/platform/playback/data/PlayQueueInteractionPattern;", "isOffline", "retrieveSeedContextInput", "Lcom/amazon/music/platform/playback/augmentation/models/SeedContextInput;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CommentaryAugmentation implements SeedContextAugmentation, InteractionPatternAugmentation {
    private String CONTAINER_AND_TRACK_SEED_CONTEXT_VALUE = "CONTAINER_AND_TRACK";
    private String TRACK_ONLY_SEED_CONTEXT_VALUE = "TRACK_ONLY";
    private final boolean isTrackSelected;

    public CommentaryAugmentation(boolean z) {
        this.isTrackSelected = z;
    }

    @Override // com.amazon.music.platform.playback.augmentation.SeedContextAugmentation
    public SeedContextInput retrieveSeedContextInput() {
        String str;
        SeedContextTypeEnum seedContextTypeEnum = SeedContextTypeEnum.ARTIST_COMMENTARY;
        if (this.isTrackSelected) {
            str = this.TRACK_ONLY_SEED_CONTEXT_VALUE;
        } else {
            str = this.CONTAINER_AND_TRACK_SEED_CONTEXT_VALUE;
        }
        return new SeedContextInput(seedContextTypeEnum, str);
    }

    @Override // com.amazon.music.platform.playback.augmentation.InteractionPatternAugmentation
    public PlayQueueInteractionPattern retrieveInteractionPattern(boolean isOffline) {
        if (isOffline) {
            return null;
        }
        return PlayQueueInteractionPattern.ONLINE;
    }
}
