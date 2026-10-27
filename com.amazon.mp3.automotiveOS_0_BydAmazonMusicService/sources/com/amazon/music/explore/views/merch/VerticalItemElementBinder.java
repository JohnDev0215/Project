package com.amazon.music.explore.views.merch;

import Touch.WidgetsInterface.v1_0.CircleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import androidx.annotation.NonNull;
import com.amazon.music.explore.widgets.metadata.ExploreMetadata;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalItemElementBinder {
    private VerticalItemElementBinder() {
    }

    public static VerticalTileModel bind(@NonNull VerticalItemElement verticalItemElement, @NonNull String str, @NonNull MethodsDispatcher methodsDispatcher) {
        int i = verticalItemElement instanceof CircleVerticalItemElement ? 4 : 0;
        String strPrimaryText = verticalItemElement.primaryText();
        String strSecondaryText = verticalItemElement.secondaryText();
        String strTertiaryText = verticalItemElement.tertiaryText();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel = createArtworkFrameModel(verticalItemElement, i);
        ExploreMetadata exploreMetadataCreateExploreMetadata = createExploreMetadata(verticalItemElement, str, methodsDispatcher);
        Boolean bool = Boolean.FALSE;
        return new VerticalTileModel(null, strPrimaryText, strSecondaryText, strTertiaryText, artworkFrameModelCreateArtworkFrameModel, null, i, exploreMetadataCreateExploreMetadata, false, bool, bool, null, null, null, bool, bool, null, verticalItemElement.rating(), verticalItemElement.reviewCount());
    }

    private static ArtworkFrameModel createArtworkFrameModel(@NonNull VerticalItemElement verticalItemElement, int i) {
        return new ArtworkFrameModel(null, verticalItemElement.image(), Integer.valueOf(i), null, null, null, null, null, null, null, verticalItemElement.imageDescription());
    }

    private static ExploreMetadata createExploreMetadata(@NonNull VerticalItemElement verticalItemElement, @NonNull String str, @NonNull MethodsDispatcher methodsDispatcher) {
        return new ExploreMetadata(str, methodsDispatcher, verticalItemElement.onItemSelected());
    }
}
