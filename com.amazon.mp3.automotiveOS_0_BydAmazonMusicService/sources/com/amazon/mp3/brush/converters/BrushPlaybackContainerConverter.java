package com.amazon.mp3.brush.converters;

import android.graphics.drawable.Drawable;
import com.amazon.mp3.brush.HotspotExperienceV2;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.PlaybackContainer;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPlaybackContainerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0017\u0010\u0018JQ\u0010\r\u001a\u00020\f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ*\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0011\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u00022\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPlaybackContainerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "", "blockRef", "imageUrl", "", "contentType", "resourceId", "imageSize", "Landroid/graphics/drawable/Drawable;", "imageDrawable", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "block", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "list", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "data", "getChildrenModels", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPlaybackContainerConverter implements BaseContainerModelConverter<Block> {
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Block block, List list) {
        return convert2(block, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(Block block, List<? extends BaseViewModel> list) {
        List<Entity> entities;
        Intrinsics.checkNotNullParameter(block, "block");
        if (list == null || !(!list.isEmpty())) {
            return null;
        }
        Content content = block.getContent();
        Entity entity = (content == null || (entities = content.getEntities()) == null) ? null : entities.get(0);
        if (entity == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.PlaybackContainer");
        }
        PlaybackContainer playbackContainer = (PlaybackContainer) entity;
        BrushLayoutTypeUtils brushLayoutTypeUtils = BrushLayoutTypeUtils.INSTANCE;
        String renderingType = block.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "block.renderingType");
        int tileLayoutType = brushLayoutTypeUtils.getTileLayoutType(renderingType);
        Entity container = playbackContainer.getContainer();
        if (container == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.Playlist");
        }
        String blockRef = block.getBlockRef();
        Image image = ((Playlist) container).getImage();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, blockRef, image == null ? null : image.getUrl(), 1, null, null, null, 56, null);
        String blockRef2 = block.getBlockRef();
        String uri = block.getUri();
        Content content2 = block.getContent();
        ShovelerModel shovelerModel = new ShovelerModel(blockRef2, list, tileLayoutType, block.getTags(), playbackContainer.getPrimaryTitle(), playbackContainer.getSecondaryTitle(), "", content2 != null ? content2.getNextToken() : null, uri, false, artworkFrameModelCreateArtworkFrameModel$default, BrushConverterUtils.INSTANCE.getContainerMetadata(playbackContainer, block.getBlockRef()), null, true, null, 20992, null);
        HotspotExperienceV2.INSTANCE.addHotspot("hotspot-on-demand-playlist-tracks", shovelerModel);
        return shovelerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Block data) {
        List<Entity> entities;
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        String renderingType = data.getRenderingType();
        Content content = data.getContent();
        Entity entity = (content == null || (entities = content.getEntities()) == null) ? null : entities.get(0);
        if (entity == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.PlaybackContainer");
        }
        PlaybackContainer playbackContainer = (PlaybackContainer) entity;
        List<Entity> entities2 = playbackContainer.getEntities();
        ContentMetadata containerMetadata = BrushConverterUtils.INSTANCE.getContainerMetadata(playbackContainer, data.getBlockRef());
        if (containerMetadata == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ContentMetadata");
        }
        BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(renderingType, "renderingType");
        BaseModelConverter converter = brushConverterFactory.getConverter(renderingType);
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        ArrayList arrayList = new ArrayList(entities2.size());
        ArrayList arrayList2 = new ArrayList();
        for (Entity entity2 : entities2) {
            if (singleBaseModelConverter instanceof BrushHorizontalTileConverter) {
                if (entity2 instanceof Track) {
                    baseViewModelConvert = ((BrushHorizontalTileConverter) singleBaseModelConverter).convert(entity2, arrayList2);
                } else {
                    Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                    baseViewModelConvert = ((BrushHorizontalTileConverter) singleBaseModelConverter).convert(entity2);
                }
            } else if (singleBaseModelConverter == null) {
                baseViewModelConvert = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                baseViewModelConvert = singleBaseModelConverter.convert(entity2);
            }
            HorizontalTileModel horizontalTileModel = baseViewModelConvert instanceof HorizontalTileModel ? (HorizontalTileModel) baseViewModelConvert : null;
            if (horizontalTileModel != null && (entity2 instanceof Track)) {
                ContentMetadata metadata = horizontalTileModel.getMetadata();
                TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                if (trackMetadata != null) {
                    trackMetadata.setContainerMetadata(containerMetadata);
                }
                horizontalTileModel.setHorizontalTileType(HorizontalTileType.COMPACT);
                horizontalTileModel.setEnableAdd(false);
                arrayList2.add(horizontalTileModel.getMetadata());
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushPlaybackContainerConverter brushPlaybackContainerConverter, String str, String str2, Integer num, Integer num2, Integer num3, Drawable drawable, int i, Object obj) {
        return brushPlaybackContainerConverter.createArtworkFrameModel(str, str2, num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : drawable);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, Integer contentType, Integer resourceId, Integer imageSize, Drawable imageDrawable) {
        return new ArtworkFrameModel(blockRef, imageUrl, contentType, null, null, null, null, resourceId, imageSize, imageDrawable, null, null, 3160, null);
    }
}
