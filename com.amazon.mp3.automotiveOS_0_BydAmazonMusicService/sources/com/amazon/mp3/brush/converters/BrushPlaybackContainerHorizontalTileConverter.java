package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPlaybackContainerHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPlaybackContainerHorizontalTileConverter;", "Lcom/amazon/mp3/brush/converters/BrushHorizontalTileConverter;", "()V", "getPlayIconType", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Ljava/lang/Integer;", "shouldDisplayPlayIcon", "", "shouldShowOverFlow", "showOverFlow", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushPlaybackContainerHorizontalTileConverter extends BrushHorizontalTileConverter {
    @Override // com.amazon.mp3.brush.converters.BrushHorizontalTileConverter
    public Integer getPlayIconType(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return null;
    }

    @Override // com.amazon.mp3.brush.converters.BrushHorizontalTileConverter
    public boolean shouldDisplayPlayIcon() {
        return true;
    }

    @Override // com.amazon.mp3.brush.converters.BrushHorizontalTileConverter
    public boolean shouldShowOverFlow(boolean showOverFlow) {
        return showOverFlow;
    }
}
