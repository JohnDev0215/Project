package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.CarouselModel;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushCarouselConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\"\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J \u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\t2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0004J\u0018\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushCarouselConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/CarouselModel;", "convert", "", "viewId", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "list", "convertFeatureBarker", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushCarouselConverter implements BaseContainerModelConverter<Block> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Block block, List list) {
        return convert2(block, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public CarouselModel convert2(Block data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (!((children == null ? null : (BaseViewModel) CollectionsKt___CollectionsKt.first((List) children)) instanceof FeatureBarkerModel)) {
            return null;
        }
        String blockRef = data.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(blockRef, "data.blockRef");
        if (children != null) {
            return convertFeatureBarker(blockRef, children);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<com.amazon.music.views.library.models.FeatureBarkerModel>");
    }

    public final CarouselModel convertFeatureBarker(String viewId, List<FeatureBarkerModel> list) {
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        if (list == null || !(!list.isEmpty())) {
            return null;
        }
        return new CarouselModel(viewId, list);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Block data) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        String renderingType = data.getRenderingType();
        Content content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(entities.size());
        BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(renderingType, "renderingType");
        BaseModelConverter converter = brushConverterFactory.getConverter(renderingType);
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        for (Entity entity : entities) {
            if (singleBaseModelConverter == null) {
                baseViewModelConvert = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                baseViewModelConvert = singleBaseModelConverter.convert(entity);
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }
}
