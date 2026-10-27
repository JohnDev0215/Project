package com.amazon.music.platform.playback.augmentation;

import com.amazon.music.platform.playback.augmentation.models.SeedContextInput;
import kotlin.Metadata;

/* JADX INFO: compiled from: SeedContextAugmentation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/playback/augmentation/SeedContextAugmentation;", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "retrieveSeedContextInput", "Lcom/amazon/music/platform/playback/augmentation/models/SeedContextInput;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface SeedContextAugmentation extends PlaybackAugmentation {
    SeedContextInput retrieveSeedContextInput();
}
