package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.AssetType;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.PageContent;
import com.amazon.music.page.api.model.Playlist;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.page.api.model.Track;
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

/* JADX INFO: compiled from: BrushV3PageSeeMoreConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0018\u0010\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\"\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0016\u0010\u0010\u001a\u00020\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\bR'\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00018BX\u0082\u0084\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3PageSeeMoreConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "Lcom/amazon/music/page/api/model/Entity;", "entity", "", "isVideo", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "getChildrenModels", "Lcom/amazon/music/page/api/model/Widget;", "widgets", "isSeeMorePage", "mNestedModelConverter$delegate", "Lkotlin/Lazy;", "getMNestedModelConverter", "()Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "getMNestedModelConverter$annotations", "()V", "mNestedModelConverter", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3PageSeeMoreConverter implements BaseContainerModelConverter<Page> {

    /* JADX INFO: renamed from: mNestedModelConverter$delegate, reason: from kotlin metadata */
    private final Lazy mNestedModelConverter = LazyKt__LazyJVMKt.lazy(new Function0<BaseContainerModelConverter<Widget>>() { // from class: com.amazon.mp3.brush.v3.converters.BrushV3PageSeeMoreConverter$mNestedModelConverter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseContainerModelConverter<Widget> invoke() {
            BaseModelConverter converter = BrushV3ConverterFactory.INSTANCE.getConverter("sectionGridKey");
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
        List<Entity> entities;
        Entity entity;
        BrushV3PageSeeMoreConverter brushV3PageSeeMoreConverter;
        int tileLayoutType;
        Intrinsics.checkNotNullParameter(data, "data");
        PageContent content = data.getContent();
        List<Widget> widgets = content == null ? null : content.getWidgets();
        if (widgets == null || children == null || !(!children.isEmpty())) {
            return null;
        }
        Widget widget = (Widget) CollectionsKt___CollectionsKt.first((List) widgets);
        WidgetContent content2 = widget.getContent();
        if (content2 == null || (entities = content2.getEntities()) == null) {
            brushV3PageSeeMoreConverter = this;
            entity = null;
        } else {
            entity = (Entity) CollectionsKt___CollectionsKt.first((List) entities);
            brushV3PageSeeMoreConverter = this;
        }
        if (brushV3PageSeeMoreConverter.isVideo(entity)) {
            BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
            RenderingType renderingType = widget.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType, "nestedBlock.renderingType");
            tileLayoutType = brushLayoutTypeUtils.get16X9TileLayoutType(renderingType);
        } else {
            if ((entity == null ? null : entity.getPodcastEpisode()) != null) {
                BrushLayoutTypeUtils brushLayoutTypeUtils2 = BrushLayoutTypeUtils.INSTANCE;
                RenderingType renderingType2 = widget.getRenderingType();
                Intrinsics.checkNotNullExpressionValue(renderingType2, "nestedBlock.renderingType");
                tileLayoutType = brushLayoutTypeUtils2.getPodcastEpisodeTileType(renderingType2);
            } else {
                if ((entity == null ? null : entity.getPodcastShow()) != null) {
                    BrushLayoutTypeUtils brushLayoutTypeUtils3 = BrushLayoutTypeUtils.INSTANCE;
                    RenderingType renderingType3 = widget.getRenderingType();
                    Intrinsics.checkNotNullExpressionValue(renderingType3, "nestedBlock.renderingType");
                    tileLayoutType = brushLayoutTypeUtils3.getPodcastShowTileType(renderingType3);
                } else {
                    BrushLayoutTypeUtils brushLayoutTypeUtils4 = BrushLayoutTypeUtils.INSTANCE;
                    RenderingType renderingType4 = widget.getRenderingType();
                    Intrinsics.checkNotNullExpressionValue(renderingType4, "nestedBlock.renderingType");
                    tileLayoutType = brushLayoutTypeUtils4.getTileLayoutType(renderingType4);
                }
            }
        }
        int i = tileLayoutType;
        String title = data.getTitle();
        String blockRef = data.getBlockRef();
        WidgetContent content3 = widget.getContent();
        return new PageGridViewModel(blockRef, widget.getBlockRef(), title, null, children, i, 0, content3 != null ? content3.getNextToken() : null, null, null, null, 1800, null);
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
        if (!(widgets != null && widgets.size() == 1)) {
            return false;
        }
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        RenderingType renderingType = ((Widget) CollectionsKt___CollectionsKt.first((List) widgets)).getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "widgets.first().renderingType");
        return brushLayoutTypeUtils.isListLayoutType(renderingType);
    }

    private final boolean isVideo(Entity entity) {
        List<AssetType> assetTypes;
        if (entity != null) {
            AssetType assetType = null;
            if (entity.getTrack() != null) {
                AssetType assetType2 = AssetType.VIDEO;
                Track track = entity.getTrack();
                if (assetType2 == (track == null ? null : track.getAssetType())) {
                    return true;
                }
            }
            if (entity.getPlaylist() != null) {
                Playlist playlist = entity.getPlaylist();
                if ((playlist == null ? null : playlist.getAssetTypes()) != null) {
                    AssetType assetType3 = AssetType.VIDEO;
                    Playlist playlist2 = entity.getPlaylist();
                    if (playlist2 != null && (assetTypes = playlist2.getAssetTypes()) != null) {
                        assetType = assetTypes.get(0);
                    }
                    if (assetType3 == assetType) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
