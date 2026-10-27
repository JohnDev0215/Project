package com.amazon.music.media.auto.tab.home.brush;

import android.net.Uri;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.AssetQuality;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Genre;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPageConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J0\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00110\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000fJ\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J4\u0010'\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\u00192\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u00112\b\b\u0002\u0010+\u001a\u00020,H\u0002J\u001a\u0010-\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0019002\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0011H\u0002J\u0012\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\u001c\u001a\u00020(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u00064"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "brushConverterUtil", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;)V", "getContentPlaybackUtil", "()Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "widgetUriMap", "", "", "getWidgetUriMap", "()Ljava/util/Map;", "areAllEntitiesTrackEntities", "", "entities", "", "Lcom/amazon/musicensembleservice/brush/Entity;", "checkEntityValidity", "entity", "convert", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "blocks", "Lcom/amazon/musicensembleservice/brush/Block;", "isHomeTab", "convertAlbum", "item", "Lcom/amazon/musicensembleservice/brush/Album;", "block", "convertArtist", "Lcom/amazon/musicensembleservice/brush/Artist;", "convertGenre", "Lcom/amazon/musicensembleservice/brush/Genre;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Station;", "convertTrack", "Lcom/amazon/musicensembleservice/brush/Track;", "metadataList", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "trackindex", "", "convertUserPlaylist", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "filterUnsupportedBlocks", "", "getTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushPageConverter {
    private static final String TAG = BrushPageConverter.class.getSimpleName();
    private final BrushConverterUtil brushConverterUtil;
    private final ContentPlaybackUtil contentPlaybackUtil;
    private final Map<String, String> widgetUriMap;

    public BrushPageConverter(ContentPlaybackUtil contentPlaybackUtil, BrushConverterUtil brushConverterUtil) {
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        Intrinsics.checkNotNullParameter(brushConverterUtil, "brushConverterUtil");
        this.contentPlaybackUtil = contentPlaybackUtil;
        this.brushConverterUtil = brushConverterUtil;
        this.widgetUriMap = new LinkedHashMap();
    }

    public final ContentPlaybackUtil getContentPlaybackUtil() {
        return this.contentPlaybackUtil;
    }

    public final Map<String, String> getWidgetUriMap() {
        return this.widgetUriMap;
    }

    public static /* synthetic */ Map convert$default(BrushPageConverter brushPageConverter, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return brushPageConverter.convert(list, z);
    }

    public final Map<String, List<AutoMediaItem>> convert(List<? extends Block> blocks, boolean isHomeTab) {
        Object next;
        AutoMediaItem autoMediaItemConvertArtist;
        Intrinsics.checkNotNullParameter(blocks, "blocks");
        List<Block> listFilterUnsupportedBlocks = filterUnsupportedBlocks(blocks);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy() ? 10 : 5;
        HashMap map = new HashMap();
        for (Block block : listFilterUnsupportedBlocks) {
            List<Entity> entities = block.getContent().getEntities();
            Intrinsics.checkNotNullExpressionValue(entities, "block.content.entities");
            if (areAllEntitiesTrackEntities(entities)) {
                ArrayList arrayList = new ArrayList();
                for (Entity entity : block.getContent().getEntities()) {
                    if (entity == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.Track");
                    }
                    TrackMetadata trackMetadata = getTrackMetadata((Track) entity);
                    if (trackMetadata != null) {
                        arrayList.add(trackMetadata);
                    }
                }
                String title = block.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "block.title");
                map.put(title, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = listFilterUnsupportedBlocks.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next2 = it.next();
            List<String> tags = ((Block) next2).getTags();
            if (!(tags == null || tags.isEmpty())) {
                arrayList2.add(next2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            if (((Block) next).getTags().contains("recently-played")) {
                break;
            }
        }
        Block block2 = (Block) next;
        if (block2 != null) {
            listFilterUnsupportedBlocks.remove(block2);
            if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
                listFilterUnsupportedBlocks.add(0, block2);
            }
        }
        for (Block block3 : listFilterUnsupportedBlocks) {
            if (linkedHashMap.size() == i) {
                return linkedHashMap;
            }
            String widgetTitle = block3.getTitle();
            List<Entity> entities2 = block3.getContent().getEntities();
            if (entities2.isEmpty()) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.error(TAG2, "This converter needs at least 1 entity to convert to a AutoMediaItem");
            } else {
                if (isHomeTab) {
                    Map<String, String> map2 = this.widgetUriMap;
                    Intrinsics.checkNotNullExpressionValue(widgetTitle, "widgetTitle");
                    String uri = block3.getUri();
                    Intrinsics.checkNotNullExpressionValue(uri, "block.uri");
                    map2.put(widgetTitle, uri);
                    Intrinsics.checkNotNullExpressionValue(entities2, "entities");
                    entities2 = CollectionsKt___CollectionsKt.take(entities2, AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET());
                }
                int i2 = 0;
                for (Entity entity2 : entities2) {
                    int i3 = i2 + 1;
                    List<? extends ContentMetadata> listEmptyList = (List) map.get(widgetTitle);
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    }
                    if (entity2 instanceof Album) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertAlbum((Album) entity2, block3);
                    } else if (entity2 instanceof Track) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertTrack((Track) entity2, block3, listEmptyList, i2);
                    } else if (entity2 instanceof Playlist) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertPlaylist((Playlist) entity2, block3);
                    } else if (entity2 instanceof Station) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertStation((Station) entity2, block3);
                    } else if (entity2 instanceof UserPlaylist) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertUserPlaylist((UserPlaylist) entity2, block3);
                    } else if (entity2 instanceof Genre) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertGenre((Genre) entity2, block3);
                    } else if (entity2 instanceof Artist) {
                        Intrinsics.checkNotNullExpressionValue(entity2, "entity");
                        autoMediaItemConvertArtist = convertArtist((Artist) entity2, block3);
                    } else {
                        autoMediaItemConvertArtist = null;
                    }
                    if (autoMediaItemConvertArtist != null) {
                        Intrinsics.checkNotNullExpressionValue(widgetTitle, "widgetTitle");
                        Object arrayList3 = linkedHashMap.get(widgetTitle);
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                            linkedHashMap.put(widgetTitle, arrayList3);
                        }
                        ((ArrayList) arrayList3).add(autoMediaItemConvertArtist);
                    }
                    i2 = i3;
                }
            }
        }
        return linkedHashMap;
    }

    private final List<Block> filterUnsupportedBlocks(List<? extends Block> blocks) {
        ArrayList arrayList = new ArrayList();
        for (Block block : blocks) {
            boolean z = true;
            Iterator<Entity> it = block.getContent().getEntities().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Entity entity = it.next();
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                if (!checkEntityValidity(entity)) {
                    z = false;
                    break;
                }
            }
            if (z) {
                arrayList.add(block);
            }
        }
        return arrayList;
    }

    private final boolean checkEntityValidity(Entity entity) {
        if (entity instanceof Album ? true : entity instanceof Playlist ? true : entity instanceof UserPlaylist ? true : entity instanceof Station ? true : entity instanceof Genre ? true : entity instanceof Artist) {
            return true;
        }
        return entity instanceof Track;
    }

    private final TrackMetadata getTrackMetadata(Track item) {
        String str;
        Image image;
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Album album = item.getAlbum();
        String url = item.getImage().getUrl();
        if (url != null) {
            str = url;
        } else if (album == null || (image = album.getImage()) == null) {
            str = null;
        } else {
            url = image.getUrl();
            str = url;
        }
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = album == null ? null : album.getAsin();
        String title2 = album == null ? null : album.getTitle();
        String asin3 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean boolIsIsOwned = item.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue2 = boolIsIsInLibrary != null ? boolIsIsInLibrary.booleanValue() : false;
        ParentalControls parentalControls = item.getParentalControls();
        Boolean boolIsHasExplicitLanguage = parentalControls != null ? parentalControls.isHasExplicitLanguage() : null;
        BrushConverterUtil brushConverterUtil = this.brushConverterUtil;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        Uri trackUri = getContentPlaybackUtil().getTrackUri(asin);
        String assetType = item.getAssetType();
        if (assetType == null) {
            assetType = "AUDIO";
        }
        String str2 = assetType;
        BrushConverterUtil brushConverterUtil2 = this.brushConverterUtil;
        List<AssetQuality> assetQualities = item.getAssetQualities();
        if (assetQualities == null) {
            assetQualities = CollectionsKt__CollectionsKt.emptyList();
        }
        List<com.amazon.music.models.AssetQuality> listConvertAssetQuality = brushConverterUtil2.convertAssetQuality(assetQualities);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        Long duration = item.getDuration();
        return new TrackMetadata(blockRef, trackUri, null, asin, title, str, null, asin2, title2, null, asin3, name, null, null, Boolean.valueOf(zBooleanValue2), Boolean.valueOf(zBooleanValue), null, boolIsHasExplicitLanguage, null, setConvertTiers, str2, listConvertAssetQuality, (int) timeUnit.toMillis(duration == null ? 0L : duration.longValue()), null, null, null, null, null, null, false, null, false, null, null, null, null, -8048060, 15, null);
    }

    private final AutoMediaItem convertUserPlaylist(UserPlaylist item, Block block) {
        String url;
        String title = item.getTitle();
        if (title == null) {
            title = block.getTitle();
        }
        String playlistId = item.getPlaylistId();
        Image image = null;
        Object obj = null;
        if (playlistId == null) {
            return null;
        }
        List<Image> images = item.getImages();
        if (images != null) {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual("ORIGINAL_ART", ((Image) next).getType())) {
                    obj = next;
                    break;
                }
            }
            image = (Image) obj;
        }
        String str = (image == null || (url = image.getUrl()) == null) ? "" : url;
        String blockRef = item.getBlockRef();
        String description = item.getDescription();
        Long tracksCount = item.getTracksCount();
        Boolean isIsOwned = item.isIsOwned();
        Uri userPlaylistUri = getContentPlaybackUtil().getUserPlaylistUri();
        List<String> tags = block.getTags();
        Intrinsics.checkNotNullExpressionValue(isIsOwned, "isIsOwned");
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(blockRef, userPlaylistUri, tags, title, str, description, tracksCount, null, isIsOwned.booleanValue(), playlistId, null, null, null, false, 15488, null);
        String blockRef2 = item.getBlockRef();
        String secondaryTitle = item.getSecondaryTitle();
        String title2 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "title");
        return new AutoMediaItem(title, secondaryTitle, null, userPlaylistMetadata, blockRef2, 1, null, 0, title2, null, 708, null);
    }

    private final AutoMediaItem convertAlbum(Album item, Block block) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        Boolean boolIsIsOwned = item.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue2 = boolIsIsInLibrary != null ? boolIsIsInLibrary.booleanValue() : false;
        BrushConverterUtil brushConverterUtil = this.brushConverterUtil;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, getContentPlaybackUtil().getAlbumUri(artist != null ? artist.getName() : null, item.getTitle(), asin), block.getTags(), asin, title, url, null, asin2, name, Boolean.valueOf(zBooleanValue2), Boolean.valueOf(zBooleanValue), brushConverterUtil.convertTiers(contentTiers), false, null, null, null, null, null, null, null, 1044544, null);
        String blockRef2 = item.getBlockRef();
        String title2 = item.getTitle();
        if (title2 == null) {
            title2 = block.getTitle();
        }
        String secondaryTitle = item.getSecondaryTitle();
        String title3 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "item.title ?: block.title");
        return new AutoMediaItem(title2, secondaryTitle, null, albumMetadata, blockRef2, 0, null, 0, title3, null, 708, null);
    }

    private final AutoMediaItem convertPlaylist(Playlist item, Block block) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue = boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue();
        BrushConverterUtil brushConverterUtil = this.brushConverterUtil;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        Uri playlistUri = getContentPlaybackUtil().getPlaylistUri(asin);
        List<String> contentTypes = item.getContentTypes();
        if (contentTypes == null) {
            contentTypes = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(blockRef, playlistUri, block.getTags(), asin, title, url, null, null, null, null, Boolean.valueOf(zBooleanValue), setConvertTiers, contentTypes, false, null, null, null, null, false, null, false, null, null, false, null, 33547200, null);
        List<String> contentTypes2 = item.getContentTypes();
        int i = Intrinsics.areEqual(contentTypes2 != null ? (String) CollectionsKt___CollectionsKt.firstOrNull((List) contentTypes2) : null, "VIDEO") ? 8 : 1;
        String blockRef2 = item.getBlockRef();
        String title2 = item.getTitle();
        if (title2 == null) {
            title2 = block.getTitle();
        }
        String str = title2;
        String secondaryTitle = item.getSecondaryTitle();
        String title3 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(str, "item.title ?: block.title");
        return new AutoMediaItem(str, secondaryTitle, null, playlistMetadata, blockRef2, i, null, 0, title3, null, 708, null);
    }

    private final AutoMediaItem convertStation(Station item, Block block) {
        String stationKey = item.getStationKey();
        if (stationKey == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        BrushConverterUtil brushConverterUtil = this.brushConverterUtil;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtil.convertTiers(contentTiers);
        BrushConverterUtil brushConverterUtil2 = this.brushConverterUtil;
        Map<String, ParentalControls> parentalControlsPerContentTier = item.getParentalControlsPerContentTier();
        Intrinsics.checkNotNullExpressionValue(parentalControlsPerContentTier, "item.parentalControlsPerContentTier");
        StationMetadata stationMetadata = new StationMetadata(blockRef, getContentPlaybackUtil().getStationUri(stationKey), block.getTags(), stationKey, title, null, null, null, url, brushConverterUtil2.convertExplicitMap(parentalControlsPerContentTier), setConvertTiers, null, null, null, 14560, null);
        String blockRef2 = item.getBlockRef();
        String title2 = item.getTitle();
        if (title2 == null) {
            title2 = block.getTitle();
        }
        String str = title2;
        String secondaryTitle = item.getSecondaryTitle();
        String title3 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(str, "item.title ?: block.title");
        return new AutoMediaItem(str, secondaryTitle, null, stationMetadata, blockRef2, 3, null, 0, title3, null, 708, null);
    }

    private final AutoMediaItem convertTrack(Track item, Block block, List<? extends ContentMetadata> metadataList, int trackindex) {
        if (item.getAsin() == null) {
            return null;
        }
        int i = Intrinsics.areEqual(item.getAssetType(), "VIDEO") ? 7 : 2;
        TrackMetadata trackMetadata = getTrackMetadata(item);
        if (trackMetadata == null) {
            return null;
        }
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        if (title == null) {
            title = block.getTitle();
        }
        String str = title;
        String secondaryTitle = item.getSecondaryTitle();
        String title2 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(str, "item.title ?: block.title");
        return new AutoMediaItem(str, secondaryTitle, null, trackMetadata, blockRef, i, metadataList, trackindex, title2, null, 516, null);
    }

    private final AutoMediaItem convertArtist(Artist item, Block block) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String name = item.getName();
        String contributorAsin = item.getContributorAsin();
        Uri artistUri = getContentPlaybackUtil().getArtistUri();
        Boolean boolIsIsFollowing = item.isIsFollowing();
        ArtistMetadata artistMetadata = new ArtistMetadata(blockRef, artistUri, block.getTags(), null, asin, name, null, url, contributorAsin, null, null, boolIsIsFollowing == null ? false : boolIsIsFollowing.booleanValue(), null, null, false, null, null, false, false, null, 1046088, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        if (primaryTitle == null) {
            primaryTitle = block.getTitle();
        }
        String str = primaryTitle;
        String secondaryTitle = item.getSecondaryTitle();
        String title = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(str, "item.primaryTitle ?: block.title");
        return new AutoMediaItem(str, secondaryTitle, null, artistMetadata, blockRef2, 4, null, 0, title, null, 708, null);
    }

    private final AutoMediaItem convertGenre(Genre item, Block block) {
        if (item.getBrowseId() == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String browseId = item.getBrowseId();
        String title = item.getTitle();
        Uri genreUri = getContentPlaybackUtil().getGenreUri();
        List<String> tags = block.getTags();
        Intrinsics.checkNotNullExpressionValue(browseId, "browseId");
        GenreMetadata genreMetadata = new GenreMetadata(blockRef, genreUri, tags, null, browseId, title, url, null, 136, null);
        String blockRef2 = item.getBlockRef();
        String title2 = item.getTitle();
        if (title2 == null) {
            title2 = block.getTitle();
        }
        String str = title2;
        String secondaryTitle = item.getSecondaryTitle();
        String title3 = block.getTitle();
        Intrinsics.checkNotNullExpressionValue(str, "item.title ?: block.title");
        return new AutoMediaItem(str, secondaryTitle, null, genreMetadata, blockRef2, 5, null, 0, title3, null, 708, null);
    }

    private final boolean areAllEntitiesTrackEntities(List<? extends Entity> entities) {
        if ((entities instanceof Collection) && entities.isEmpty()) {
            return true;
        }
        Iterator<T> it = entities.iterator();
        while (it.hasNext()) {
            if (!(((Entity) it.next()) instanceof Track)) {
                return false;
            }
        }
        return true;
    }
}
