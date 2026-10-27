package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AlbumSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\r\b&\u0018\u00002\u00020\u0001B×\u0001\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012¢\u0006\u0002\u0010\u001aJ\b\u0010K\u001a\u00020;H&J\u0018\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020;H&J \u0010P\u001a\u00020M2\u0006\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u00102\u0006\u0010S\u001a\u00020\u0010H&J\u0010\u0010.\u001a\u00020M2\u0006\u0010T\u001a\u00020\u0010H&J\u0010\u0010U\u001a\u00020M2\u0006\u0010V\u001a\u00020\u0010H&J\u0018\u0010W\u001a\u00020M2\u0006\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u0010H&R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001c\"\u0004\b)\u0010\u001eR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001c\"\u0004\b+\u0010\u001eR\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b8\u0010\"\"\u0004\b9\u0010$R\u0014\u0010:\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010<R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u00101\"\u0004\b>\u00103R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001c\"\u0004\bD\u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u001c\"\u0004\bF\u0010\u001eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010J¨\u0006Z"}, d2 = {"Lcom/amazon/music/find/model/search/AlbumSearchItem;", "Lcom/amazon/music/find/model/search/SearchAlbum;", "title", "", "asin", "artworkUri", "artist", "artistAsin", "artworkId", "", PageUriUtils.SOURCE_QUERY_PARAM, "id", "contentInfoMap", "", "blockRef", "trackCount", "", ContextMappingConstants.CONTENT_ENCODING, "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "contentAccessSet", "", "Lcom/amazon/music/ContentAccessType;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "languageOfPerformances", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/lang/String;ILjava/util/List;Ljava/util/Set;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;)V", "getArtist", "()Ljava/lang/String;", "setArtist", "(Ljava/lang/String;)V", "getArtistAsin", "setArtistAsin", "getArtworkId", "()Ljava/lang/Long;", "setArtworkId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getArtworkUri", "setArtworkUri", "getAsin", "setAsin", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentEncoding", "()Ljava/util/List;", "setContentEncoding", "(Ljava/util/List;)V", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "getId", "setId", "isInLibrary", "", "()Z", "getLanguageOfPerformances", "setLanguageOfPerformances", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getSource", "setSource", "getTitle", "setTitle", "getTrackCount", "()I", "setTrackCount", "(I)V", "isOwned", "setCatalogStatus", "", "isPrime", "isHawkfire", "setCatalogStatuses", "minCatalogStatus", "maxCatalogStatus", "maxNonPreviousCatalogStatus", "catalogStatus", "setDownloadState", "downloadState", "setOwnershipStatuses", "minOwnershipStatus", "maxOwnershipStatus", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AlbumSearchItem implements SearchAlbum {
    private String artist;
    private String artistAsin;
    private Long artworkId;
    private String artworkUri;
    private String asin;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private List<? extends ContentEncoding> contentEncoding;
    private Map<String, String> contentInfoMap;
    private Long id;
    private List<String> languageOfPerformances;
    private PlaymodeEligibility playmodeEligibility;
    private String source;
    private String title;
    private int trackCount;

    @JvmOverloads
    public AlbumSearchItem() {
        this(null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 32767, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 32766, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, null, null, 0, null, null, null, null, 32764, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3) {
        this(str, str2, str3, null, null, null, null, null, null, null, 0, null, null, null, null, 32760, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null, null, null, null, null, null, 0, null, null, null, null, 32752, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, str5, null, null, null, null, null, 0, null, null, null, null, 32736, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l) {
        this(str, str2, str3, str4, str5, l, null, null, null, null, 0, null, null, null, null, 32704, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6) {
        this(str, str2, str3, str4, str5, l, str6, null, null, null, 0, null, null, null, null, 32640, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2) {
        this(str, str2, str3, str4, str5, l, str6, l2, null, null, 0, null, null, null, null, 32512, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, null, 0, null, null, null, null, 32256, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, str7, 0, null, null, null, null, 31744, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7, int i) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, str7, i, null, null, null, null, 30720, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7, int i, List<? extends ContentEncoding> list) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, str7, i, list, null, null, null, 28672, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7, int i, List<? extends ContentEncoding> list, Set<? extends ContentAccessType> set) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, str7, i, list, set, null, null, 24576, null);
    }

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7, int i, List<? extends ContentEncoding> list, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility) {
        this(str, str2, str3, str4, str5, l, str6, l2, map, str7, i, list, set, playmodeEligibility, null, 16384, null);
    }

    public abstract boolean isOwned();

    public abstract void setCatalogStatus(boolean isPrime, boolean isHawkfire);

    public abstract void setCatalogStatuses(int minCatalogStatus, int maxCatalogStatus, int maxNonPreviousCatalogStatus);

    public abstract void setContentAccessSet(int catalogStatus);

    public abstract void setDownloadState(int downloadState);

    public abstract void setOwnershipStatuses(int minOwnershipStatus, int maxOwnershipStatus);

    @JvmOverloads
    public AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map<String, String> map, String str7, int i, List<? extends ContentEncoding> list, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility, List<String> list2) {
        this.title = str;
        this.asin = str2;
        this.artworkUri = str3;
        this.artist = str4;
        this.artistAsin = str5;
        this.artworkId = l;
        this.source = str6;
        this.id = l2;
        this.contentInfoMap = map;
        this.blockRef = str7;
        this.trackCount = i;
        this.contentEncoding = list;
        this.contentAccessSet = set;
        this.playmodeEligibility = playmodeEligibility;
        this.languageOfPerformances = list2;
    }

    public /* synthetic */ AlbumSearchItem(String str, String str2, String str3, String str4, String str5, Long l, String str6, Long l2, Map map, String str7, int i, List list, Set set, PlaymodeEligibility playmodeEligibility, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : l, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? null : l2, (i2 & 256) != 0 ? null : map, (i2 & 512) != 0 ? null : str7, (i2 & 1024) != 0 ? 0 : i, (i2 & 2048) != 0 ? null : list, (i2 & 4096) != 0 ? null : set, (i2 & 8192) != 0 ? null : playmodeEligibility, (i2 & 16384) == 0 ? list2 : null);
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

    @Override // com.amazon.music.find.model.search.SearchAlbum
    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    @Override // com.amazon.music.find.model.search.SearchAlbum
    public String getArtistAsin() {
        return this.artistAsin;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
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

    @Override // com.amazon.music.find.model.search.SearchAlbum
    public int getTrackCount() {
        return this.trackCount;
    }

    public void setTrackCount(int i) {
        this.trackCount = i;
    }

    @Override // com.amazon.music.find.model.search.SearchAlbum, com.amazon.music.find.model.search.ContentEncodingAware
    public List<ContentEncoding> getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(List<? extends ContentEncoding> list) {
        this.contentEncoding = list;
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

    @Override // com.amazon.music.find.model.search.SearchAlbum
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
