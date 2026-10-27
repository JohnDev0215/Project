package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.page.api.model.WidgetContent;
import com.amazon.music.page.api.model.WidgetTag;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.CarModeButtonNavigatorModel;
import com.amazon.music.views.library.models.SimpleShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3SimpleShovelerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3SimpleShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Widget;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3SimpleShovelerConverter implements BaseContainerModelConverter<Widget> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Widget widget, List list) {
        return convert2(widget, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(Widget data, List<? extends BaseViewModel> children) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || children.isEmpty()) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Button button = data.getButton();
        String title2 = button == null ? null : button.getTitle();
        Button button2 = data.getButton();
        String target = button2 == null ? null : button2.getTarget();
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        RenderingType renderingType = data.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "data.renderingType");
        int tileLayoutType = brushLayoutTypeUtils.getTileLayoutType(renderingType);
        List<WidgetTag> tags = data.getTags();
        if (tags == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tags, 10));
            Iterator<T> it = tags.iterator();
            while (it.hasNext()) {
                arrayList2.add(((WidgetTag) it.next()).name());
            }
            arrayList = arrayList2;
        }
        WidgetContent content = data.getContent();
        return new SimpleShovelerModel(blockRef, children, tileLayoutType, arrayList, title, title2, content != null ? content.getNextToken() : null, target);
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
        BaseModelConverter converter = BrushV3ConverterFactory.INSTANCE.getConverter(renderingType.name());
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Entity entity : entities) {
            int i2 = i + 1;
            if (singleBaseModelConverter == null) {
                baseViewModelConvert = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                baseViewModelConvert = singleBaseModelConverter.convert(entity);
            }
            if ((baseViewModelConvert instanceof CarModeButtonNavigatorModel) && i == CollectionsKt__CollectionsKt.getLastIndex(entities)) {
                ((CarModeButtonNavigatorModel) baseViewModelConvert).setLastItem(true);
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
            i = i2;
        }
        return arrayList;
    }
}
