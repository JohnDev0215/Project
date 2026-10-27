package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.search.RelatedPlaylist;
import com.visualon.OSMPUtils.voOSType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bP\n\u0002\u0010\u0002\n\u0002\b\r\b&\u0018\u00002\u00020\u0001Bë\u0002\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u0012\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0013\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&\u0012\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0013\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0013¢\u0006\u0002\u0010*J\b\u0010w\u001a\u00020\u001cH&J\u0010\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020\u001fH&J\u0018\u0010{\u001a\u00020y2\u0006\u0010|\u001a\u00020\u001c2\u0006\u0010}\u001a\u00020\u001cH&J\u0010\u0010{\u001a\u00020y2\u0006\u0010~\u001a\u00020\u0017H&J\u0010\u0010H\u001a\u00020y2\u0006\u0010~\u001a\u00020\u0017H&J\u0013\u0010\u007f\u001a\u00020y2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0003H&J\u0012\u0010\u0081\u0001\u001a\u00020y2\u0007\u0010\u0082\u0001\u001a\u00020\u0017H&J%\u0010\u0083\u0001\u001a\u00020y2\u0007\u0010\u0084\u0001\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u00032\u0007\u0010\u0085\u0001\u001a\u00020\u0017H&R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010,\"\u0004\b0\u0010.R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010,\"\u0004\b2\u0010.R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010,\"\u0004\b4\u0010.R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010,\"\u0004\b6\u0010.R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0002\u0010;\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010,\"\u0004\b=\u0010.R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010,\"\u0004\b?\u0010.R\"\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010,\"\u0004\bE\u0010.R\"\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010A\"\u0004\bK\u0010CR(\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010\u0019\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0002\u0010;\u001a\u0004\bY\u00108\"\u0004\bZ\u0010:R\u001a\u0010\u001b\u001a\u00020\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010[\"\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b^\u0010[R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010A\"\u0004\b`\u0010CR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010,\"\u0004\bb\u0010.R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010,\"\u0004\bd\u0010.R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\"\u0010'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010A\"\u0004\bj\u0010CR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u001a\u0010\u001a\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010Q\"\u0004\bp\u0010SR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010,\"\u0004\br\u0010.R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010,\"\u0004\bt\u0010.R\u001a\u0010\u0018\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010Q\"\u0004\bv\u0010S¨\u0006\u0086\u0001"}, d2 = {"Lcom/amazon/music/find/model/search/TrackSearchItem;", "Lcom/amazon/music/find/model/search/SearchTrack;", "title", "", "asin", "artworkUri", "artist", "artistAsin", "album", "albumAsin", "albumArtist", "artworkId", "", PageUriUtils.SOURCE_QUERY_PARAM, "id", "contentInfoMap", "", "blockRef", ContextMappingConstants.CONTENT_ENCODING, "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "luid", "duration", "", "trackNum", "discNum", "size", BrushV2Converter.KEY_IS_EXPLICIT, "", "localUri", "searchAssetType", "Lcom/amazon/music/find/model/search/SearchAssetType;", "assetQualities", "Lcom/amazon/music/models/AssetQuality;", "contentAccessSet", "", "Lcom/amazon/music/ContentAccessType;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "relatedPlaylists", "Lcom/amazon/music/search/RelatedPlaylist;", "languageOfPerformances", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;IIIZLjava/lang/String;Lcom/amazon/music/find/model/search/SearchAssetType;Ljava/util/List;Ljava/util/Set;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;Ljava/util/List;)V", "getAlbum", "()Ljava/lang/String;", "setAlbum", "(Ljava/lang/String;)V", "getAlbumArtist", "setAlbumArtist", "getAlbumAsin", "setAlbumAsin", "getArtist", "setArtist", "getArtistAsin", "setArtistAsin", "getArtworkId", "()Ljava/lang/Long;", "setArtworkId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getArtworkUri", "setArtworkUri", "getAsin", "setAsin", "getAssetQualities", "()Ljava/util/List;", "setAssetQualities", "(Ljava/util/List;)V", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentEncoding", "setContentEncoding", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "getDiscNum", "()I", "setDiscNum", "(I)V", "getDuration", "()Ljava/lang/Integer;", "setDuration", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getId", "setId", "()Z", "setExplicit", "(Z)V", "isInLibrary", "getLanguageOfPerformances", "setLanguageOfPerformances", "getLocalUri", "setLocalUri", "getLuid", "setLuid", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getRelatedPlaylists", "setRelatedPlaylists", "getSearchAssetType", "()Lcom/amazon/music/find/model/search/SearchAssetType;", "setSearchAssetType", "(Lcom/amazon/music/find/model/search/SearchAssetType;)V", "getSize", "setSize", "getSource", "setSource", "getTitle", "setTitle", "getTrackNum", "setTrackNum", "isOwned", "setAssetType", "", "assetType", "setCatalogStatus", "isPrime", "isHawkfire", "catalogStatus", "setLyricsState", "lyricsState", "setOwnershipStatus", "ownershipStatus", "setSourceAndDownloadState", "mediaSource", "downloadState", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class TrackSearchItem implements SearchTrack {
    private String album;
    private String albumArtist;
    private String albumAsin;
    private String artist;
    private String artistAsin;
    private Long artworkId;
    private String artworkUri;
    private String asin;
    private List<AssetQuality> assetQualities;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private List<? extends ContentEncoding> contentEncoding;
    private Map<String, String> contentInfoMap;
    private int discNum;
    private Integer duration;
    private Long id;
    private boolean isExplicit;
    private List<String> languageOfPerformances;
    private String localUri;
    private String luid;
    private PlaymodeEligibility playmodeEligibility;
    private List<RelatedPlaylist> relatedPlaylists;
    private SearchAssetType searchAssetType;
    private int size;
    private String source;
    private String title;
    private int trackNum;

    @JvmOverloads
    public TrackSearchItem() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217727, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217726, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217724, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3) {
        this(str, str2, str3, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217720, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217712, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, str5, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217696, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, str6, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217664, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, null, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217600, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this(str, str2, str3, str4, str5, str6, str7, str8, null, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217472, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, null, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134217216, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, null, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134216704, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, null, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134215680, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, null, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134213632, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, null, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134209536, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, null, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134201344, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, null, 0, 0, 0, false, null, null, null, null, null, null, null, 134184960, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, 0, 0, 0, false, null, null, null, null, null, null, null, 134152192, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, 0, 0, false, null, null, null, null, null, null, null, 134086656, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, 0, false, null, null, null, null, null, null, null, 133955584, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, false, null, null, null, null, null, null, null, 133693440, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, null, null, null, null, null, null, null, 133169152, null);
    }

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, null, null, null, null, null, null, 132120576, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, searchAssetType, null, null, null, null, null, 130023424, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List<AssetQuality> list2) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, searchAssetType, list2, null, null, null, null, 125829120, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List<AssetQuality> list2, Set<? extends ContentAccessType> set) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, searchAssetType, list2, set, null, null, null, 117440512, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List<AssetQuality> list2, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, searchAssetType, list2, set, playmodeEligibility, null, null, 100663296, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List<AssetQuality> list2, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility, List<RelatedPlaylist> list3) {
        this(str, str2, str3, str4, str5, str6, str7, str8, l, str9, l2, map, str10, list, str11, num, i, i2, i3, z, str12, searchAssetType, list2, set, playmodeEligibility, list3, null, voOSType.VOOSMP_SRC_FFAUDIO_MIDI, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    public abstract boolean isOwned();

    public abstract void setAssetType(SearchAssetType assetType);

    public abstract void setCatalogStatus(int catalogStatus);

    public abstract void setCatalogStatus(boolean isPrime, boolean isHawkfire);

    public abstract void setContentAccessSet(int catalogStatus);

    public abstract void setLyricsState(String lyricsState);

    public abstract void setOwnershipStatus(int ownershipStatus);

    public abstract void setSourceAndDownloadState(String mediaSource, String localUri, int downloadState);

    @JvmOverloads
    public TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map<String, String> map, String str10, List<? extends ContentEncoding> list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List<AssetQuality> list2, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility, List<RelatedPlaylist> list3, List<String> list4) {
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
        this.title = str;
        this.asin = str2;
        this.artworkUri = str3;
        this.artist = str4;
        this.artistAsin = str5;
        this.album = str6;
        this.albumAsin = str7;
        this.albumArtist = str8;
        this.artworkId = l;
        this.source = str9;
        this.id = l2;
        this.contentInfoMap = map;
        this.blockRef = str10;
        this.contentEncoding = list;
        this.luid = str11;
        this.duration = num;
        this.trackNum = i;
        this.discNum = i2;
        this.size = i3;
        this.isExplicit = z;
        this.localUri = str12;
        this.searchAssetType = searchAssetType;
        this.assetQualities = list2;
        this.contentAccessSet = set;
        this.playmodeEligibility = playmodeEligibility;
        this.relatedPlaylists = list3;
        this.languageOfPerformances = list4;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getAsin() {
        return this.asin;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getArtworkUri() {
        return this.artworkUri;
    }

    public void setArtworkUri(String str) {
        this.artworkUri = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getArtistAsin() {
        return this.artistAsin;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public void setAlbumArtist(String str) {
        this.albumArtist = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getArtworkId() {
        return this.artworkId;
    }

    public void setArtworkId(Long l) {
        this.artworkId = l;
    }

    @Override // com.amazon.music.find.model.search.SearchItem, com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return this.id;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
        this.id = l;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Map<String, String> getContentInfoMap() {
        return this.contentInfoMap;
    }

    public void setContentInfoMap(Map<String, String> map) {
        this.contentInfoMap = map;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getBlockRef() {
        return this.blockRef;
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack, com.amazon.music.find.model.search.ContentEncodingAware
    public List<ContentEncoding> getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(List<? extends ContentEncoding> list) {
        this.contentEncoding = list;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getLuid() {
        return this.luid;
    }

    public void setLuid(String str) {
        this.luid = str;
    }

    public /* synthetic */ TrackSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l, String str9, Long l2, Map map, String str10, List list, String str11, Integer num, int i, int i2, int i3, boolean z, String str12, SearchAssetType searchAssetType, List list2, Set set, PlaymodeEligibility playmodeEligibility, List list3, List list4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? null : str4, (i4 & 16) != 0 ? null : str5, (i4 & 32) != 0 ? null : str6, (i4 & 64) != 0 ? null : str7, (i4 & 128) != 0 ? null : str8, (i4 & 256) != 0 ? null : l, (i4 & 512) != 0 ? null : str9, (i4 & 1024) != 0 ? null : l2, (i4 & 2048) != 0 ? null : map, (i4 & 4096) != 0 ? null : str10, (i4 & 8192) != 0 ? null : list, (i4 & 16384) != 0 ? null : str11, (i4 & 32768) != 0 ? 0 : num, (i4 & 65536) != 0 ? 0 : i, (i4 & 131072) != 0 ? 0 : i2, (i4 & 262144) != 0 ? 0 : i3, (i4 & 524288) == 0 ? z : false, (i4 & 1048576) != 0 ? null : str12, (i4 & 2097152) != 0 ? SearchAssetType.AUDIO : searchAssetType, (i4 & 4194304) != 0 ? null : list2, (i4 & 8388608) != 0 ? null : set, (i4 & 16777216) != 0 ? null : playmodeEligibility, (i4 & voOSType.VOOSMP_SRC_FFAUDIO_WMA) != 0 ? null : list3, (i4 & voOSType.VOOSMP_SRC_FFAUDIO_MIDI) != 0 ? null : list4);
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public int getTrackNum() {
        return this.trackNum;
    }

    public void setTrackNum(int i) {
        this.trackNum = i;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public int getDiscNum() {
        return this.discNum;
    }

    public void setDiscNum(int i) {
        this.discNum = i;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public boolean getIsExplicit() {
        return this.isExplicit;
    }

    public void setExplicit(boolean z) {
        this.isExplicit = z;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public String getLocalUri() {
        return this.localUri;
    }

    public void setLocalUri(String str) {
        this.localUri = str;
    }

    public final SearchAssetType getSearchAssetType() {
        return this.searchAssetType;
    }

    public final void setSearchAssetType(SearchAssetType searchAssetType) {
        Intrinsics.checkNotNullParameter(searchAssetType, "<set-?>");
        this.searchAssetType = searchAssetType;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public void setAssetQualities(List<AssetQuality> list) {
        this.assetQualities = list;
    }

    public final Set<ContentAccessType> getContentAccessSet() {
        return this.contentAccessSet;
    }

    public final void setContentAccessSet(Set<? extends ContentAccessType> set) {
        this.contentAccessSet = set;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public final List<RelatedPlaylist> getRelatedPlaylists() {
        return this.relatedPlaylists;
    }

    public final void setRelatedPlaylists(List<RelatedPlaylist> list) {
        this.relatedPlaylists = list;
    }

    @Override // com.amazon.music.find.model.search.SearchTrack
    public List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    public void setLanguageOfPerformances(List<String> list) {
        this.languageOfPerformances = list;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isInLibrary */
    public boolean getIsInLibrary() {
        return getId() != null;
    }
}
