package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.page.api.model.WidgetContent;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.CarouselModel;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3CarouselConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3CarouselConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Widget;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/CarouselModel;", "convert", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3CarouselConverter implements BaseContainerModelConverter<Widget> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Widget widget, List list) {
        return convert2(widget, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public CarouselModel convert2(Widget data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || !(!children.isEmpty())) {
            return null;
        }
        String blockRef = data.getBlockRef();
        ArrayList arrayList = new ArrayList();
        for (Object obj : children) {
            if (obj instanceof FeatureBarkerModel) {
                arrayList.add(obj);
            }
        }
        return new CarouselModel(blockRef, arrayList);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Widget data) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        RenderingType renderingType = data.getRenderingType();
        WidgetContent content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(entities.size());
        BaseModelConverter converter = BrushV3ConverterFactory.INSTANCE.getConverter(renderingType.name());
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
