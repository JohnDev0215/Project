package com.amazon.mp3.brush.converters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.converter.model.FindImage;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushDetailedSimpleHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016J$\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0018H\u0014¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushDetailedSimpleHorizontalTileConverter;", "Lcom/amazon/mp3/brush/converters/BrushSimpleHorizontalTileConverter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "convert", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "Lcom/amazon/musicensembleservice/brush/Block;", "metadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "createArtworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "blockRef", "", "drawable", "Landroid/graphics/drawable/Drawable;", "contentType", "", "getAlbumArtworkFrameModel", "item", "Lcom/amazon/musicensembleservice/brush/Album;", "getTrackArtworkFrameModel", "Lcom/amazon/musicensembleservice/brush/Track;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BrushDetailedSimpleHorizontalTileConverter extends BrushSimpleHorizontalTileConverter {
    private static final String TAG = BrushDetailedSimpleHorizontalTileConverter.class.getSimpleName();

    public BrushDetailedSimpleHorizontalTileConverter(Context context) {
        super(context);
    }

    @Override // com.amazon.mp3.brush.converters.BrushSimpleHorizontalTileConverter
    public BaseViewModel convert(Block data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null || entities.isEmpty()) {
            Log.error(TAG, "This converter needs at least 1 entity to convert to a SimpleHorizontalTileModel");
            return null;
        }
        Entity entity = (Entity) CollectionsKt___CollectionsKt.first((List) entities);
        if (entity instanceof Album) {
            return convertAlbum((Album) entity, data, HorizontalTileType.DETAILED);
        }
        if (entity instanceof Playlist) {
            return convertPlaylist((Playlist) entity, data, HorizontalTileType.DETAILED);
        }
        if (entity instanceof Station) {
            return convertStation((Station) entity, data, HorizontalTileType.DETAILED);
        }
        if (entity instanceof Track) {
            return convertTrack((Track) entity, data, metadataList, HorizontalTileType.DETAILED);
        }
        if (entity instanceof UserPlaylist) {
            return convertUserPlaylist((UserPlaylist) entity, data, HorizontalTileType.DETAILED);
        }
        if (entity instanceof Artist) {
            return convertArtist((Artist) entity, HorizontalTileType.DETAILED);
        }
        if (entity instanceof PodcastShow) {
            return convertPodcastShow((PodcastShow) entity, HorizontalTileType.DETAILED);
        }
        if (entity instanceof PodcastEpisode) {
            return convertPodcastEpisode((PodcastEpisode) entity, HorizontalTileType.DETAILED);
        }
        return null;
    }

    @Override // com.amazon.mp3.brush.converters.BrushSimpleHorizontalTileConverter
    protected ArtworkFrameModel getAlbumArtworkFrameModel(Album item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (Intrinsics.areEqual(item.isIsInLibrary(), Boolean.TRUE) && (item.getImage() instanceof FindImage)) {
            Image image = item.getImage();
            if (image != null) {
                return createArtworkFrameModel(item.getBlockRef(), ((FindImage) image).getImageDrawable(), 0);
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.find.converter.model.FindImage");
        }
        return super.getAlbumArtworkFrameModel(item);
    }

    @Override // com.amazon.mp3.brush.converters.BrushSimpleHorizontalTileConverter
    protected ArtworkFrameModel getTrackArtworkFrameModel(Track item) {
        Intrinsics.checkNotNullParameter(item, "item");
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        Intrinsics.checkNotNullExpressionValue(boolIsIsInLibrary, "item.isIsInLibrary");
        if (boolIsIsInLibrary.booleanValue() && (item.getImage() instanceof FindImage)) {
            Image image = item.getImage();
            if (image != null) {
                return createArtworkFrameModel(item.getBlockRef(), ((FindImage) image).getImageDrawable(), 2);
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.find.converter.model.FindImage");
        }
        return super.getTrackArtworkFrameModel(item);
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, Drawable drawable, int contentType) {
        return new ArtworkFrameModel(blockRef, null, Integer.valueOf(contentType), null, null, getPlayIconType(contentType), null, null, null, drawable, null, null, 3546, null);
    }
}
