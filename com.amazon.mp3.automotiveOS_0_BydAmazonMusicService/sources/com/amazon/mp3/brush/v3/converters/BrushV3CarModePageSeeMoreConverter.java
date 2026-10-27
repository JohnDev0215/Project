package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.PageContent;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.page.api.model.WidgetContent;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3CarModePageSeeMoreConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0015\u0010\u0013J\"\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\r\u001a\u00020\f2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004R'\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00018BX\u0082\u0084\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3CarModePageSeeMoreConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "getChildrenModels", "Lcom/amazon/music/page/api/model/Widget;", "widgets", "", "isSeeMorePage", "mNestedModelConverter$delegate", "Lkotlin/Lazy;", "getMNestedModelConverter", "()Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "getMNestedModelConverter$annotations", "()V", "mNestedModelConverter", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3CarModePageSeeMoreConverter implements BaseContainerModelConverter<Page> {

    /* JADX INFO: renamed from: mNestedModelConverter$delegate, reason: from kotlin metadata */
    private final Lazy mNestedModelConverter = LazyKt__LazyJVMKt.lazy(new Function0<BaseContainerModelConverter<Widget>>() { // from class: com.amazon.mp3.brush.v3.converters.BrushV3CarModePageSeeMoreConverter$mNestedModelConverter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseContainerModelConverter<Widget> invoke() {
            BaseModelConverter converter = BrushV3ConverterFactory.INSTANCE.getConverter("simpleShovelerKey");
            if (converter != null) {
                return (BaseContainerModelConverter) converter;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.BaseContainerModelConverter<com.amazon.music.page.api.model.Widget>");
        }
    });

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Page page, List list) {
        return convert2(page, (List<? extends BaseViewModel>) list);
    }

    private final BaseContainerModelConverter<Widget> getMNestedModelConverter() {
        return (BaseContainerModelConverter) this.mNestedModelConverter.getValue();
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(Page data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        PageContent content = data.getContent();
        List<Widget> widgets = content == null ? null : content.getWidgets();
        if (widgets != null) {
            if (!(children == null || children.isEmpty())) {
                Widget widget = (Widget) CollectionsKt___CollectionsKt.first((List) widgets);
                BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
                RenderingType renderingType = widget.getRenderingType();
                Intrinsics.checkNotNullExpressionValue(renderingType, "nestedBlock.renderingType");
                int tileLayoutType = brushLayoutTypeUtils.getTileLayoutType(renderingType);
                String title = data.getTitle();
                String blockRef = data.getBlockRef();
                WidgetContent content2 = widget.getContent();
                return new PageGridViewModel(blockRef, widget.getBlockRef(), title, null, children, tileLayoutType, 0, content2 != null ? content2.getNextToken() : null, null, null, null, 1800, null);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        PageContent content = data.getContent();
        List<Widget> widgets = content == null ? null : content.getWidgets();
        if (widgets == null) {
            return null;
        }
        BaseContainerModelConverter<Widget> mNestedModelConverter = getMNestedModelConverter();
        Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) widgets);
        Intrinsics.checkNotNullExpressionValue(objFirst, "widgets.first()");
        return mNestedModelConverter.getChildrenModels(objFirst);
    }

    public final boolean isSeeMorePage(List<? extends Widget> widgets) {
        return (widgets != null && widgets.size() == 1) && ((Widget) CollectionsKt___CollectionsKt.first((List) widgets)).getRenderingType() == RenderingType.CARMODE_THUMBNAIL_LISTING;
    }
}
