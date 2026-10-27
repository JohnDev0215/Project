package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.RectangleHorizontalItemElement;
import com.amazon.music.explore.widgets.metadata.ExploreMetadata;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;

/* JADX INFO: loaded from: classes4.dex */
public class HorizontalItemModel extends ExploreModel {
    private final HorizontalItemElement item;
    private int parentModelSize;

    public HorizontalItemModel(String str, MethodsDispatcher methodsDispatcher, HorizontalItemElement horizontalItemElement) {
        super(str, methodsDispatcher);
        this.item = horizontalItemElement;
        this.parentModelSize = 1;
    }

    public int getParentModelSize() {
        return this.parentModelSize;
    }

    public void setParentModelSize(int i) {
        this.parentModelSize = i;
    }

    public HorizontalTileModel convertToHorizontalTileModel() {
        return new HorizontalTileModel(null, this.item.label(), this.item.primaryText(), this.item.secondaryText(), this.item.tertiaryText(), createArtworkFrameModel(), null, false, null, null, false, false, null, false, false, HorizontalTileType.FEATURED, 0, createExploreMetadata(), null, this.item instanceof RectangleHorizontalItemElement);
    }

    private ArtworkFrameModel createArtworkFrameModel() {
        return new ArtworkFrameModel(null, this.item.image(), 0, null, null, null, null, null, null);
    }

    private ExploreMetadata createExploreMetadata() {
        return new ExploreMetadata(getOwnerId(), getMethodsDispatcher(), this.item.onItemSelected());
    }
}
