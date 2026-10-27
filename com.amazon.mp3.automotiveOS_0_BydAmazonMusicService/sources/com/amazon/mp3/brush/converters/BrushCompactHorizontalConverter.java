package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.musicensembleservice.brush.Entity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushCompactHorizontalConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J2\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushCompactHorizontalConverter;", "Lcom/amazon/mp3/brush/converters/BrushHorizontalTileConverter;", "()V", "convert", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "Lcom/amazon/musicensembleservice/brush/Entity;", "metadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "tags", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushCompactHorizontalConverter extends BrushHorizontalTileConverter {
    @Override // com.amazon.mp3.brush.converters.BrushHorizontalTileConverter
    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseViewModel baseViewModelConvert = super.convert(data, metadataList);
        if (baseViewModelConvert == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.HorizontalTileModel");
        }
        HorizontalTileModel horizontalTileModel = (HorizontalTileModel) baseViewModelConvert;
        horizontalTileModel.setHorizontalTileType(HorizontalTileType.COMPACT);
        horizontalTileModel.setShowNavigationIcon(false);
        return horizontalTileModel;
    }

    @Override // com.amazon.mp3.brush.converters.BrushHorizontalTileConverter
    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList, List<String> tags) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseViewModel baseViewModelConvert = super.convert(data, metadataList, tags);
        if (baseViewModelConvert == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.HorizontalTileModel");
        }
        HorizontalTileModel horizontalTileModel = (HorizontalTileModel) baseViewModelConvert;
        horizontalTileModel.setHorizontalTileType(HorizontalTileType.COMPACT);
        horizontalTileModel.setShowNavigationIcon(false);
        return horizontalTileModel;
    }
}
