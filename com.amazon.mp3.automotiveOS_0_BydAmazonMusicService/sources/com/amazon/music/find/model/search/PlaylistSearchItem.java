package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.CoroutineScheduler;

/* JADX INFO: compiled from: PlaylistSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0095\u0002\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014¢\u0006\u0002\u0010\"J\u0010\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u001bH&J0\u0010l\u001a\u00020j2\u0006\u0010m\u001a\u00020\u00172\u0006\u0010n\u001a\u00020\u00172\u0006\u0010o\u001a\u00020\u00172\u0006\u0010p\u001a\u00020\u00172\u0006\u0010q\u001a\u00020\u0017H&J\u0010\u0010l\u001a\u00020j2\u0006\u0010r\u001a\u00020\nH&R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010)\"\u0004\b=\u0010+R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010)\"\u0004\b?\u0010+R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b@\u0010$\"\u0004\bA\u0010&R\u0014\u0010B\u001a\u00020\u0017X\u0096D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010CR\u001a\u0010\u0018\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010C\"\u0004\bE\u0010FR\"\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010/\"\u0004\bH\u00101R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010\u0016\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010C\"\u0004\bN\u0010FR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010)\"\u0004\bP\u0010+R\u001a\u0010Q\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010)\"\u0004\bd\u0010+R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010)\"\u0004\bf\u0010+R\u001a\u0010\u000b\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010J\"\u0004\bh\u0010L¨\u0006s"}, d2 = {"Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "Lcom/amazon/music/find/model/search/SearchPlaylist;", "title", "", "asin", "artworkUri", MediaTrack.ROLE_DESCRIPTION, "rating", "", "numReviews", "", "trackCount", "artworkId", "", PageUriUtils.SOURCE_QUERY_PARAM, "id", "contentInfoMap", "", "blockRef", "authors", "", "playlistId", "ownership", "", "isSharedUserPlaylist", "curator", "searchAssetType", "Lcom/amazon/music/find/model/search/SearchAssetType;", "contentAccessSet", "", "Lcom/amazon/music/ContentAccessType;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "languageOfPerformances", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FIILjava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZZLjava/lang/String;Lcom/amazon/music/find/model/search/SearchAssetType;Ljava/util/Set;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;)V", "getArtworkId", "()Ljava/lang/Long;", "setArtworkId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getArtworkUri", "()Ljava/lang/String;", "setArtworkUri", "(Ljava/lang/String;)V", "getAsin", "setAsin", "getAuthors", "()Ljava/util/List;", "setAuthors", "(Ljava/util/List;)V", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "getCurator", "setCurator", "getDescription", "setDescription", "getId", "setId", "isAvailable", "()Z", "isInLibrary", "setSharedUserPlaylist", "(Z)V", "getLanguageOfPerformances", "setLanguageOfPerformances", "getNumReviews", "()I", "setNumReviews", "(I)V", "getOwnership", "setOwnership", "getPlaylistId", "setPlaylistId", "playlistType", "Lcom/amazon/music/find/model/search/PlaylistType;", "getPlaylistType", "()Lcom/amazon/music/find/model/search/PlaylistType;", "setPlaylistType", "(Lcom/amazon/music/find/model/search/PlaylistType;)V", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getRating", "()F", "setRating", "(F)V", "getSearchAssetType", "()Lcom/amazon/music/find/model/search/SearchAssetType;", "setSearchAssetType", "(Lcom/amazon/music/find/model/search/SearchAssetType;)V", "getSource", "setSource", "getTitle", "setTitle", "getTrackCount", "setTrackCount", "setAssetType", "", "assetType", "setCatalogStatus", "isPrime", "isSonicRush", "isHawkfire", "isFree", "isFreeOnDemand", "catalogStatus", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlaylistSearchItem implements SearchPlaylist {
    private Long artworkId;
    private String artworkUri;
    private String asin;
    private List<String> authors;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private Map<String, String> contentInfoMap;
    private String curator;
    private String description;
    private Long id;
    private final boolean isAvailable;
    private boolean isSharedUserPlaylist;
    private List<String> languageOfPerformances;
    private int numReviews;
    private boolean ownership;
    private String playlistId;
    private PlaylistType playlistType;
    private PlaymodeEligibility playmodeEligibility;
    private float rating;
    private SearchAssetType searchAssetType;
    private String source;
    private String title;
    private int trackCount;

    @JvmOverloads
    public PlaylistSearchItem() {
        this(null, null, null, null, 0.0f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097151, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str) {
        this(str, null, null, null, 0.0f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2) {
        this(str, str2, null, null, 0.0f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097148, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3) {
        this(str, str2, str3, null, 0.0f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097144, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, 0.0f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097136, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f) {
        this(str, str2, str3, str4, f, 0, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097120, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i) {
        this(str, str2, str3, str4, f, i, 0, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097088, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2) {
        this(str, str2, str3, str4, f, i, i2, null, null, null, null, null, null, null, false, false, null, null, null, null, null, 2097024, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l) {
        this(str, str2, str3, str4, f, i, i2, l, null, null, null, null, null, null, false, false, null, null, null, null, null, 2096896, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5) {
        this(str, str2, str3, str4, f, i, i2, l, str5, null, null, null, null, null, false, false, null, null, null, null, null, 2096640, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, null, null, null, null, false, false, null, null, null, null, null, 2096128, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, null, null, null, false, false, null, null, null, null, null, 2095104, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, null, null, false, false, null, null, null, null, null, 2093056, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, null, false, false, null, null, null, null, null, 2088960, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, false, false, null, null, null, null, null, 2080768, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, false, null, null, null, null, null, 2064384, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, z2, null, null, null, null, null, 2031616, null);
    }

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2, String str8) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, z2, str8, null, null, null, null, 1966080, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2, String str8, SearchAssetType searchAssetType) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, z2, str8, searchAssetType, null, null, null, 1835008, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2, String str8, SearchAssetType searchAssetType, Set<? extends ContentAccessType> set) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, z2, str8, searchAssetType, set, null, null, 1572864, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2, String str8, SearchAssetType searchAssetType, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility) {
        this(str, str2, str3, str4, f, i, i2, l, str5, l2, map, str6, list, str7, z, z2, str8, searchAssetType, set, playmodeEligibility, null, 1048576, null);
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
    }

    public abstract void setAssetType(SearchAssetType assetType);

    public abstract void setCatalogStatus(int catalogStatus);

    public abstract void setCatalogStatus(boolean isPrime, boolean isSonicRush, boolean isHawkfire, boolean isFree, boolean isFreeOnDemand);

    @JvmOverloads
    public PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map<String, String> map, String str6, List<String> list, String str7, boolean z, boolean z2, String str8, SearchAssetType searchAssetType, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility, List<String> list2) {
        Intrinsics.checkNotNullParameter(searchAssetType, "searchAssetType");
        this.title = str;
        this.asin = str2;
        this.artworkUri = str3;
        this.description = str4;
        this.rating = f;
        this.numReviews = i;
        this.trackCount = i2;
        this.artworkId = l;
        this.source = str5;
        this.id = l2;
        this.contentInfoMap = map;
        this.blockRef = str6;
        this.authors = list;
        this.playlistId = str7;
        this.ownership = z;
        this.isSharedUserPlaylist = z2;
        this.curator = str8;
        this.searchAssetType = searchAssetType;
        this.contentAccessSet = set;
        this.playmodeEligibility = playmodeEligibility;
        this.languageOfPerformances = list2;
        this.isAvailable = true;
        this.playlistType = PlaylistType.PRIME_PLAYLIST;
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

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public float getRating() {
        return this.rating;
    }

    public void setRating(float f) {
        this.rating = f;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public int getNumReviews() {
        return this.numReviews;
    }

    public void setNumReviews(int i) {
        this.numReviews = i;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public int getTrackCount() {
        return this.trackCount;
    }

    public void setTrackCount(int i) {
        this.trackCount = i;
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

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> list) {
        this.authors = list;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public String getPlaylistId() {
        return this.playlistId;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    public boolean getOwnership() {
        return this.ownership;
    }

    public void setOwnership(boolean z) {
        this.ownership = z;
    }

    @Override // com.amazon.music.find.model.search.SearchPlaylist
    /* JADX INFO: renamed from: isSharedUserPlaylist, reason: from getter */
    public boolean getIsSharedUserPlaylist() {
        return this.isSharedUserPlaylist;
    }

    public void setSharedUserPlaylist(boolean z) {
        this.isSharedUserPlaylist = z;
    }

    public final String getCurator() {
        return this.curator;
    }

    public final void setCurator(String str) {
        this.curator = str;
    }

    public /* synthetic */ PlaylistSearchItem(String str, String str2, String str3, String str4, float f, int i, int i2, Long l, String str5, Long l2, Map map, String str6, List list, String str7, boolean z, boolean z2, String str8, SearchAssetType searchAssetType, Set set, PlaymodeEligibility playmodeEligibility, List list2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : str4, (i3 & 16) != 0 ? 0.0f : f, (i3 & 32) != 0 ? 0 : i, (i3 & 64) != 0 ? 0 : i2, (i3 & 128) != 0 ? null : l, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? null : l2, (i3 & 1024) != 0 ? null : map, (i3 & 2048) != 0 ? null : str6, (i3 & 4096) != 0 ? null : list, (i3 & 8192) != 0 ? null : str7, (i3 & 16384) != 0 ? false : z, (i3 & 32768) != 0 ? false : z2, (i3 & 65536) != 0 ? null : str8, (i3 & 131072) != 0 ? SearchAssetType.AUDIO : searchAssetType, (i3 & 262144) != 0 ? null : set, (i3 & 524288) != 0 ? null : playmodeEligibility, (i3 & 1048576) != 0 ? null : list2);
    }

    public final SearchAssetType getSearchAssetType() {
        return this.searchAssetType;
    }

    public final void setSearchAssetType(SearchAssetType searchAssetType) {
        Intrinsics.checkNotNullParameter(searchAssetType, "<set-?>");
        this.searchAssetType = searchAssetType;
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

    @Override // com.amazon.music.find.model.search.SearchPlaylist
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

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable, reason: from getter */
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public final PlaylistType getPlaylistType() {
        return this.playlistType;
    }

    public final void setPlaylistType(PlaylistType playlistType) {
        Intrinsics.checkNotNullParameter(playlistType, "<set-?>");
        this.playlistType = playlistType;
    }
}
