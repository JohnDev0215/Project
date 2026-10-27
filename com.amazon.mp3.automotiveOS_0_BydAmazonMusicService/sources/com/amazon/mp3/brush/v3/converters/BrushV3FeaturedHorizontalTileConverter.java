package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Entity;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3FeaturedHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3FeaturedHorizontalTileConverter;", "Lcom/amazon/mp3/brush/v3/converters/BrushV3HorizontalTileConverter;", "()V", "convert", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "Lcom/amazon/music/page/api/model/Entity;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushV3FeaturedHorizontalTileConverter extends BrushV3HorizontalTileConverter {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.brush.v3.converters.BrushV3HorizontalTileConverter, com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseViewModel baseViewModelConvert = super.convert(data);
        if (baseViewModelConvert == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.HorizontalTileModel");
        }
        HorizontalTileModel horizontalTileModel = (HorizontalTileModel) baseViewModelConvert;
        horizontalTileModel.setHorizontalTileType(HorizontalTileType.FEATURED);
        return horizontalTileModel;
    }
}
