package com.amazon.mp3.brush.converters;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.LiveStream;
import com.amazon.musicensembleservice.brush.Page;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Track;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPageSeeMoreConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\"\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\u00022\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0016J\u0018\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u0002H\u0016J(\u0010\u0015\u001a\u00020\u00052\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\r2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rR!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPageSeeMoreConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Page;", "Lcom/amazon/musicensembleservice/brush/Entity;", "entity", "", "isVideo", "", "renderingType", "", "getPodcastEpisodeTileType", "getPodcastShowTileType", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "getChildrenModels", "Lcom/amazon/musicensembleservice/brush/Block;", "blocks", "isSeeMorePage", "mNestedModelConverter$delegate", "Lkotlin/Lazy;", "getMNestedModelConverter", "()Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "mNestedModelConverter", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPageSeeMoreConverter implements BaseContainerModelConverter<Page> {

    /* JADX INFO: renamed from: mNestedModelConverter$delegate, reason: from kotlin metadata */
    private final Lazy mNestedModelConverter = LazyKt__LazyJVMKt.lazy(new Function0<BaseContainerModelConverter<Block>>() { // from class: com.amazon.mp3.brush.converters.BrushPageSeeMoreConverter$mNestedModelConverter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseContainerModelConverter<Block> invoke() {
            BaseModelConverter converter = BrushConverterFactory.INSTANCE.getConverter("sectionGridKey");
            if (converter != null) {
                return (BaseContainerModelConverter) converter;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.BaseContainerModelConverter<com.amazon.musicensembleservice.brush.Block>");
        }
    });

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Page page, List list) {
        return convert2(page, (List<? extends BaseViewModel>) list);
    }

    private final BaseContainerModelConverter<Block> getMNestedModelConverter() {
        return (BaseContainerModelConverter) this.mNestedModelConverter.getValue();
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(Page data, List<? extends BaseViewModel> children) {
        List<Entity> entities;
        int tileLayoutType;
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        List<Block> blocks = content == null ? null : content.getBlocks();
        if (blocks == null || children == null || !(!children.isEmpty())) {
            return null;
        }
        Block block = blocks.get(0);
        Content content2 = block.getContent();
        Entity entity = (content2 == null || (entities = content2.getEntities()) == null) ? null : (Entity) CollectionsKt___CollectionsKt.first((List) entities);
        if ((entity instanceof LiveStream) || isVideo(entity)) {
            BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
            String renderingType = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType, "nestedBlock.renderingType");
            tileLayoutType = brushLayoutTypeUtils.get16X9TileLayoutType(renderingType);
        } else if (entity instanceof PodcastEpisode) {
            String renderingType2 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType2, "nestedBlock.renderingType");
            tileLayoutType = getPodcastEpisodeTileType(renderingType2);
        } else if ((entity instanceof PodcastShow) && AmazonApplication.getCapabilities().isPodcastRedhoodPlusUXEnabled()) {
            String renderingType3 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType3, "nestedBlock.renderingType");
            tileLayoutType = getPodcastShowTileType(renderingType3);
        } else {
            BrushLayoutTypeUtils brushLayoutTypeUtils2 = BrushLayoutTypeUtils.INSTANCE;
            String renderingType4 = block.getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType4, "nestedBlock.renderingType");
            tileLayoutType = brushLayoutTypeUtils2.getTileLayoutType(renderingType4);
        }
        int i = tileLayoutType;
        String title = data.getTitle();
        String subTitle = data.getSubTitle();
        String blockRef = data.getBlockRef();
        Content content3 = block.getContent();
        return new PageGridViewModel(blockRef, block.getBlockRef(), title, subTitle, children, i, 0, content3 != null ? content3.getNextToken() : null, null, null, null, 1792, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        List<Block> blocks = content == null ? null : content.getBlocks();
        if (blocks == null) {
            return null;
        }
        BaseContainerModelConverter<Block> mNestedModelConverter = getMNestedModelConverter();
        Block block = blocks.get(0);
        Intrinsics.checkNotNullExpressionValue(block, "blocks[0]");
        return mNestedModelConverter.getChildrenModels(block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isSeeMorePage$default(BrushPageSeeMoreConverter brushPageSeeMoreConverter, List list, List list2, int i, Object obj) {
        if ((i & 2) != 0) {
            list2 = null;
        }
        return brushPageSeeMoreConverter.isSeeMorePage(list, list2);
    }

    private final boolean isVideo(Entity entity) {
        if ((entity instanceof Track) && Intrinsics.areEqual("VIDEO", ((Track) entity).getAssetType())) {
            return true;
        }
        if (entity instanceof Playlist) {
            List<String> contentTypes = ((Playlist) entity).getContentTypes();
            if (Intrinsics.areEqual(contentTypes == null ? null : (String) CollectionsKt___CollectionsKt.getOrNull(contentTypes, 0), "VIDEO")) {
                return true;
            }
        }
        return false;
    }

    private final int getPodcastEpisodeTileType(String renderingType) {
        if (Intrinsics.areEqual("DESCRIPTIVE_LISTING", renderingType)) {
            return 2;
        }
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        if (brushLayoutTypeUtils.isListLayoutType(renderingType)) {
            return 2;
        }
        return brushLayoutTypeUtils.getTileLayoutType(renderingType);
    }

    private final int getPodcastShowTileType(String renderingType) {
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        if (brushLayoutTypeUtils.isListLayoutType(renderingType)) {
            return 2;
        }
        return brushLayoutTypeUtils.getTileLayoutType(renderingType);
    }

    public final boolean isSeeMorePage(List<? extends Block> blocks, List<? extends BaseViewModel> children) {
        boolean z;
        if (children == null || children.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it = children.iterator();
            while (it.hasNext()) {
                if (((BaseViewModel) it.next()) instanceof CollectionDetailHeaderModel) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (blocks != null && blocks.size() == 1) {
            BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
            String renderingType = blocks.get(0).getRenderingType();
            Intrinsics.checkNotNullExpressionValue(renderingType, "blocks[0].renderingType");
            if (brushLayoutTypeUtils.isListLayoutType(renderingType) && !z) {
                return true;
            }
        }
        return false;
    }
}
