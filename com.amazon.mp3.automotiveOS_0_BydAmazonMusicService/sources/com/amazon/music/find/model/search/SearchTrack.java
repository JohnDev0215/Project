package com.amazon.music.find.model.search;

import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.models.AssetQuality;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchTrack.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u001a\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u001a\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0013R\u0014\u0010#\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0006R\u0012\u0010'\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u001aR\u0012\u0010)\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u001a¨\u0006+"}, d2 = {"Lcom/amazon/music/find/model/search/SearchTrack;", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/music/find/model/search/ContentEncodingAware;", "album", "", "getAlbum", "()Ljava/lang/String;", "albumArtist", "getAlbumArtist", "albumAsin", "getAlbumAsin", "artist", "getArtist", "artistAsin", "getArtistAsin", "assetQualities", "", "Lcom/amazon/music/models/AssetQuality;", "getAssetQualities", "()Ljava/util/List;", ContextMappingConstants.CONTENT_ENCODING, "Lcom/amazon/music/contentEncoding/ContentEncoding;", "getContentEncoding", "discNum", "", "getDiscNum", "()I", "duration", "getDuration", "()Ljava/lang/Integer;", BrushV2Converter.KEY_IS_EXPLICIT, "", "()Z", "languageOfPerformances", "getLanguageOfPerformances", "localUri", "getLocalUri", "luid", "getLuid", "size", "getSize", "trackNum", "getTrackNum", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchTrack extends SearchItem, ContentEncodingAware {
    String getAlbum();

    String getAlbumArtist();

    String getAlbumAsin();

    String getArtist();

    String getArtistAsin();

    List<AssetQuality> getAssetQualities();

    @Override // com.amazon.music.find.model.search.ContentEncodingAware
    List<ContentEncoding> getContentEncoding();

    int getDiscNum();

    Integer getDuration();

    List<String> getLanguageOfPerformances();

    String getLocalUri();

    String getLuid();

    int getSize();

    int getTrackNum();

    boolean isExplicit();
}
